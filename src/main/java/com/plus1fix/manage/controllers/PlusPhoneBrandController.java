package com.plus1fix.manage.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import cn.wizzer.common.annotation.SLog;
import cn.wizzer.common.base.Result;
import cn.wizzer.common.filter.PrivateFilter;

import com.plus1fix.manage.models.PlusPhoneBrand;
import com.plus1fix.manage.services.PlusPhoneBrandService;

/**
 * @author peter-zhang
 */
@IocBean
@At("/private/plus1fix/phonebrand")
@Filters({ @By(type = PrivateFilter.class) })
public class PlusPhoneBrandController {
	private static final Log log = Logs.get();
	@Inject
	PlusPhoneBrandService brandService;

	@At("/index")
	@Ok("beetl:/private/plus1fix/phonebrand/index.html")
	@RequiresAuthentication
	public Object index(HttpServletRequest req) {
		return brandService.query(Cnd.where("1", "=", "1").asc("showIndex"));
	}

	@At
	@Ok("beetl:/private/plus1fix/phonebrand/add.html")
	@RequiresAuthentication
	public void add() {
	}

	@At
	@Ok("json")
	@RequiresPermissions("cms.content.channel.add")
	@SLog(tag = "", msg = "")
	public Object save(@Param("..") PlusPhoneBrand brand,
			HttpServletRequest req) {
		try {
			brandService.insert(brand);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
	}

	@At("/edit/?")
	@Ok("beetl:/private/plus1fix/phonebrand/edit.html")
	@RequiresAuthentication
	public Object edit(long id, HttpServletRequest req) {
		return brandService.fetch(id);
	}

	@At
	@Ok("json")
	@RequiresPermissions("cms.content.channel.edit")
	@SLog(tag = "", msg = "")
	public Object update(@Param("..") PlusPhoneBrand brand,
			HttpServletRequest req) {
		try {
			brand.setOpBy(Strings.sNull(req.getAttribute("uid")));
			brand.setOpAt((int) (System.currentTimeMillis() / 1000));
			brandService.updateIgnoreNull(brand);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
	}

	@At("/delete/?")
	@Ok("json")
	@RequiresPermissions("cms.content.channel.delete")
	@SLog(tag = "", msg = "")
	public Object delete(long id, HttpServletRequest req) {
		try {
			brandService.delete(id);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
	}
}
