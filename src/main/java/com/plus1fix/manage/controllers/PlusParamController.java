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

import com.plus1fix.manage.models.PlusParam;
import com.plus1fix.manage.services.PlusParamService;

/**
 * 
 * @author peter-zhang
 *
 */
@IocBean
@At("/private/plus1fix/param")
@Filters({ @By(type = PrivateFilter.class) })
public class PlusParamController {
	private static final Log log = Logs.get();
	@Inject
	PlusParamService paramService;

	@At("/index")
    @Ok("beetl:/private/plus1fix/param/index.html")
    @RequiresAuthentication
    public Object index() {
        return paramService.query(Cnd.where("1", "=", "1").asc("createTime"));
    }
	
	@At("/detail/?")
    @Ok("beetl:/private/plus1fix/param/detail.html")
    @RequiresAuthentication
    public Object detail(String id) {
        return paramService.fetch(id);
    }

	@At("/edit/?")
	@Ok("beetl:/private/plus1fix/param/edit.html")
	@RequiresAuthentication
	public Object edit(long id, HttpServletRequest req) {
		return paramService.fetch(id);
	}
	
	@At
    @Ok("json")
    @RequiresPermissions("sys.manager.unit.edit")
    @SLog(tag = "", msg = "")
    public Object update(@Param("..") PlusParam param, HttpServletRequest req) {
        try {
        	param.setOpBy(Strings.sNull(req.getAttribute("uid")));
        	param.setOpAt((int) (System.currentTimeMillis() / 1000));
        	paramService.updateIgnoreNull(param);
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }
}
