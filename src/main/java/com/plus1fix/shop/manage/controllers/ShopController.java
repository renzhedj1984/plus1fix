package com.plus1fix.shop.manage.controllers;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;

import cn.wizzer.common.filter.PrivateFilter;
import cn.wizzer.modules.back.sys.models.Sys_user;

import com.plus1fix.manage.services.PlusShopService;

/**
 * Created by wizzer on 2016/6/28.
 */
@IocBean
@At("/private/shop/info")
@Filters({@By(type = PrivateFilter.class)})
public class ShopController {
    private static final Log log = Logs.get();
    @Inject
    PlusShopService plusShopService;

    @At("")
    @Ok("beetl:/private/shop/info/index.html")
    @RequiresAuthentication
    public Object index() {
    	Subject subject = SecurityUtils.getSubject();
        Sys_user user = (Sys_user) subject.getPrincipal();
        return plusShopService.fetch(Cnd.where("account", "=", user.getLoginname()));
    }
}
