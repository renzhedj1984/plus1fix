package com.plus1fix.manage.controllers;

import java.util.List;

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
import cn.wizzer.common.page.DataTableColumn;
import cn.wizzer.common.page.DataTableOrder;

import com.plus1fix.manage.models.PlusComment;
import com.plus1fix.manage.services.PlusCommentService;

/**
 * 
 * @author peter-zhang
 *
 */
@IocBean
@At("/private/plus1fix/comment")
@Filters({ @By(type = PrivateFilter.class) })
public class PlusCommentController {
	private static final Log log = Logs.get();
	@Inject
	PlusCommentService commentService;

	@At({ "/index", "/index/?" })
	@Ok("beetl:/private/plus1fix/comment/index.html")
	@RequiresAuthentication
	public void index(int flag, HttpServletRequest req) {
		if (flag == 0)
			flag = 1;
		req.setAttribute("flag", flag);
	}

	@At({ "/list/", "/list/?" })
	@Ok("json:full")
	@RequiresAuthentication
	public Object list(int flag, @Param("customerAccount") String customerAccount,
			@Param("length") int length, @Param("start") int start,
			@Param("draw") int draw,
			@Param("::order") List<DataTableOrder> order,
			@Param("::columns") List<DataTableColumn> columns) {
		Cnd cnd = Cnd.NEW();
		if (flag == 1) {
			cnd.and("statusFlag", "=", "1");
		}
		if (flag == 2) {
			cnd.and("statusFlag", "=", "2");
		}
		if (!Strings.isBlank(customerAccount)) {
			cnd.and("customerAccount", "like", "%" + customerAccount + "%");
		}
		return commentService.data(length, start, draw, order, columns, cnd,
				null);
	}

	@At("/detail/?")
	@Ok("beetl:/private/plus1fix/comment/detail.html")
	@RequiresAuthentication
	public Object detail(long id) {
		return commentService.fetch(id);
	}

	@At("/delete/?")
	@Ok("json")
	@RequiresPermissions("plus1fix.manager.comment.del")
	@SLog(tag = "删除", msg = "菜单名称:${args[1].getAttribute('menuName')},id:${args[0]}")
	public Object delete(long id, HttpServletRequest req) {
		try {
			PlusComment comment = commentService.fetch(id);
			comment.setStatusFlag(PlusComment.STATUS_FLAG_DEL);
			commentService.updateIgnoreNull(comment);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
	}
	
	@At("/recover/?")
	@Ok("json")
	@RequiresPermissions("plus1fix.manager.comment.recover")
	@SLog(tag = "恢复", msg = "菜单名称:${args[1].getAttribute('menuName')},id:${args[0]}")
	public Object recover(long id, HttpServletRequest req) {
		try {
			PlusComment comment = commentService.fetch(id);
			comment.setStatusFlag(PlusComment.STATUS_FLAG_INUSE);
			commentService.updateIgnoreNull(comment);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
	}
}
