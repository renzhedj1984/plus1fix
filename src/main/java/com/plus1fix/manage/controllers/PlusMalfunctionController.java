package com.plus1fix.manage.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.adaptor.WhaleAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import cn.wizzer.common.annotation.SLog;
import cn.wizzer.common.base.Result;
import cn.wizzer.common.filter.PrivateFilter;

import com.plus1fix.manage.models.PlusMalfunction;
import com.plus1fix.manage.services.PlusMalfunctionService;
import com.plus1fix.manage.services.PlusPhoneTypeService;

/**
 * Created by wizzer on 2016/6/24.
 */
@IocBean
@At("/private/plus1fix/malfunction")
@Filters({ @By(type = PrivateFilter.class) })
public class PlusMalfunctionController {
	private static final Log log = Logs.get();
	@Inject
	PlusMalfunctionService malfunctionService;
	@Inject
	PlusPhoneTypeService phoneTypeService;

	@At({ "/index", "/index/?" })
	@Ok("beetl:/private/plus1fix/malfunction/index.html")
	@RequiresAuthentication
	public Object index(long phoneTypeId) {
		return malfunctionService.query(Cnd.where("parentId", "=", "")
				.or("parentId", "is", null)
				.and("phoneTypeId", "=", phoneTypeId).asc("path"));
	}

	@At
	@Ok("beetl:/private/plus1fix/malfunction/add.html")
	@RequiresAuthentication
	public void add(@Param("pid") long pid, HttpServletRequest req) {
		req.setAttribute("phoneTypes", phoneTypeService.query());
		req.setAttribute("obj", pid == 0 ? null : malfunctionService.fetch(pid));
	}

	@At
	@Ok("json")
	@RequiresPermissions("sys.manager.unit.add")
	@AdaptBy(type = WhaleAdaptor.class)
	@SLog(tag = "", msg = "")
	public Object addDo(@Param("..") PlusMalfunction malfunction,
			@Param("parentId") long parentId, HttpServletRequest req) {
		try {
			malfunctionService.save(malfunction, parentId);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
	}

	@At("/child/?")
	@Ok("beetl:/private/plus1fix/malfunction/child.html")
	@RequiresAuthentication
	public Object child(long id) {
		return malfunctionService.query(Cnd.where("parentId", "=", id).asc(
				"path"));
	}

	@At("/edit/?")
	@Ok("beetl:/private/plus1fix/malfunction/edit.html")
	@RequiresAuthentication
	public void edit(long id, HttpServletRequest req) {
		PlusMalfunction malfunction = malfunctionService.fetch(id);
		if (malfunction != null) {
			req.setAttribute("parentMalfunction",
					malfunctionService.fetch(malfunction.getParentId()));
			req.setAttribute("phoneTypeId", malfunction.getPhoneTypeId());
		}
		req.setAttribute("obj", malfunction);
		req.setAttribute("phoneTypes", phoneTypeService.query());
	}

	@At
	@Ok("json")
	@RequiresPermissions("sys.manager.unit.edit")
	@SLog(tag = "编辑单位", msg = "单位名称:${args[0].name}")
	public Object editDo(@Param("..") PlusMalfunction malfunction,
			@Param("parentId") long parentId, HttpServletRequest req) {
		try {
			malfunction.setOpBy(Strings.sNull(req.getAttribute("uid")));
			malfunction.setOpAt((int) (System.currentTimeMillis() / 1000));
			malfunctionService.updateIgnoreNull(malfunction);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
	}

	@At("/delete/?")
	@Ok("json")
	@RequiresPermissions("sys.manager.unit.delete")
	@SLog(tag = "删除单位", msg = "单位名称:${args[1].getAttribute('name')}")
	public Object delete(long id, HttpServletRequest req) {
		try {
			PlusMalfunction malfunction = malfunctionService.fetch(id);
			req.setAttribute("name", malfunction.getName());
			if ("0001".equals(malfunction.getPath())) {
				return Result.error("system.not.allow");
			}
			malfunctionService.deleteAndChild(malfunction);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
	}

	@At("/detail/?")
    @Ok("json")
    @RequiresAuthentication
    public Object detail(long id) {
        return malfunctionService.fetch(id);
    }
	
	@At
	@Ok("json")
	@RequiresAuthentication
	public Object tree(@Param("pid") long pid) {
		List<PlusMalfunction> list = malfunctionService.query(Cnd.where(
				"parentId", "=", Strings.sBlank(pid)).asc("path"));
		List<Map<String, Object>> tree = new ArrayList<>();
		for (PlusMalfunction malfunction : list) {
			Map<String, Object> obj = new HashMap<>();
			obj.put("id", malfunction.getId());
			obj.put("text", malfunction.getName());
			obj.put("children", malfunction.isHasChildren());
			tree.add(obj);
		}
		return tree;
	}
}
