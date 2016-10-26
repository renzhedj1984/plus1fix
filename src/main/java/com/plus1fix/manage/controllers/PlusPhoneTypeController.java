package com.plus1fix.manage.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import cn.wizzer.common.page.DataTableColumn;
import cn.wizzer.common.page.DataTableOrder;

import com.plus1fix.manage.models.PlusPhoneBrand;
import com.plus1fix.manage.models.PlusPhoneType;
import com.plus1fix.manage.services.PlusPhoneBrandService;
import com.plus1fix.manage.services.PlusPhoneTypeService;

/**
 * @author peter-zhang
 */
@IocBean
@At("/private/plus1fix/phonetype")
@Filters({ @By(type = PrivateFilter.class) })
public class PlusPhoneTypeController {
	private static final Log log = Logs.get();
	@Inject
	PlusPhoneTypeService phoneTypeService;
	@Inject
	PlusPhoneBrandService phoneBrandService;

	@At("/index")
	@Ok("beetl:/private/plus1fix/phonetype/index.html")
	@RequiresAuthentication
	public void index() {
	}

	@At
	@Ok("json")
	@RequiresAuthentication
	public Object tree() {
		List<PlusPhoneBrand> list = phoneBrandService.query(Cnd.where("1", "=",
				1).desc("createTime"));
		List<Map<String, Object>> tree = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		map.put("id", "0");
		map.put("text", "所有品牌");
		map.put("children", false);
		tree.add(map);
		for (PlusPhoneBrand brand : list) {
			Map<String, Object> obj = new HashMap<>();
			obj.put("id", brand.getId());
			obj.put("text", brand.getName());
			obj.put("children", false);
			tree.add(obj);
		}
		return tree;
	}

	@At
	@Ok("json:full")
	@RequiresAuthentication
	public Object list(@Param("bid") String bid, @Param("name") String name,
			@Param("length") int length, @Param("start") int start,
			@Param("draw") int draw,
			@Param("::order") List<DataTableOrder> order,
			@Param("::columns") List<DataTableColumn> columns) {
		Cnd cnd = Cnd.NEW();
		if (!Strings.isBlank(bid) && !"0".equals(bid)) {
			cnd.and("bid", "=", bid);
		}
		if (!Strings.isBlank(name)) {
			cnd.and("name", "like", "%" + name + "%");
		}
		return phoneTypeService.data(length, start, draw, order, columns, cnd,
				null);
	}

	@At
	@Ok("beetl:/private/plus1fix/phonetype/add.html")
	@RequiresAuthentication
	public void add(@Param("bid") long bid, HttpServletRequest req) {
		req.setAttribute("brand", bid != 0 ? phoneBrandService.fetch(bid)
				: null);
	}

	@At
	@Ok("json")
	@RequiresPermissions("cms.content.article.add")
	@SLog(tag = "添加文章", msg = "文章标题:${args[0].name}")
	@AdaptBy(type = WhaleAdaptor.class)
	public Object addDo(@Param("..") PlusPhoneType phoneType,HttpServletRequest req) {
		try {
			phoneType.setCreateTime(new Date().getTime());
			phoneTypeService.insert(phoneType);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
	}

	@At("/edit/?")
	@Ok("beetl:/private/plus1fix/phonetype/edit.html")
	@RequiresAuthentication
	public Object edit(String id, HttpServletRequest req) {
		return phoneTypeService.fetch(id);
	}

	@At
	@Ok("json")
	@RequiresPermissions("cms.content.article.edit")
	@SLog(tag = "添加文章", msg = "文章标题:${args[0].title}")
	@AdaptBy(type = WhaleAdaptor.class)
	public Object editDo(@Param("..") PlusPhoneType phoneType,
			@Param("at") String at, HttpServletRequest req) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			phoneType.setCreateTime(sdf.parse(at).getTime());
			phoneTypeService.updateIgnoreNull(phoneType);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
	}

	@At({ "/delete/?", "/delete" })
	@Ok("json")
	@RequiresPermissions("cms.content.article.delete")
	@SLog(tag = "删除文章", msg = "ID:${args[2].getAttribute('id')}")
	public Object delete(String oneId, @Param("ids") String[] ids,
			HttpServletRequest req) {
		try {
			if (ids != null && ids.length > 0) {
				phoneTypeService.delete(ids);
				req.setAttribute("id",
						org.apache.shiro.util.StringUtils.toString(ids));
			} else {
				phoneTypeService.delete(oneId);
				req.setAttribute("id", oneId);
			}
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
	}
}
