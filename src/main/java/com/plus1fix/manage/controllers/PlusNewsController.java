package com.plus1fix.manage.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
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
import cn.wizzer.modules.back.cms.models.Cms_article;
import cn.wizzer.modules.back.sys.models.Sys_user;

import com.plus1fix.manage.models.PlusNews;
import com.plus1fix.manage.services.PlusNewsService;

/**
 * 
 * @author peter-zhang
 *
 */
@IocBean
@At("/private/plus1fix/news")
@Filters({ @By(type = PrivateFilter.class) })
public class PlusNewsController {
	private static final Log log = Logs.get();
	@Inject
	PlusNewsService newsService;

	@At({ "/index", "/index/?" })
	@Ok("beetl:/private/plus1fix/news/index.html")
	@RequiresAuthentication
	public void index(int flag, HttpServletRequest req) {
		if (flag == 0)
			flag = 1;
		req.setAttribute("flag", flag);
	}

	@At({ "/list/", "/list/?" })
	@Ok("json:full")
	@RequiresAuthentication
	public Object list(@Param("flag") int flag, @Param("title") String title,
			@Param("author") String author,
			@Param("startTime") String startTime,
			@Param("endTime") String endTime, @Param("length") int length,
			@Param("start") int start, @Param("draw") int draw,
			@Param("::order") List<DataTableOrder> order,
			@Param("::columns") List<DataTableColumn> columns) {
		Cnd cnd = Cnd.NEW();
		if (flag == 1) {
			cnd.and("delFlag", "=", 0);
		}
		if (flag == 2) {
			cnd.and("delFlag", "=", 1);
		}
		if (!Strings.isBlank(title)) {
			cnd.and("title", "like", "%" + title + "%");
		}
		if (!Strings.isBlank(author)) {
			cnd.and("author", "=", author);
		}
		if (!Strings.isBlank(startTime) && !Strings.isBlank(endTime)) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				long _start = sdf.parse(startTime).getTime();
				long _end = sdf.parse(endTime).getTime();
				cnd.and("publishTime", ">=", _start).and("publishTime", "<=",
						_end);
			} catch (ParseException e) {
			}
		}
		return newsService.data(length, start, draw, order, columns, cnd, null);
	}

	@At
	@Ok("beetl:/private/plus1fix/news/add.html")
	@RequiresAuthentication
	public void add(@Param("channelId") String channelId, HttpServletRequest req) {
		Subject subject = SecurityUtils.getSubject();
		Sys_user user = (Sys_user) subject.getPrincipal();
		req.setAttribute("author", user == null ? "" : user.getNickname());
	}

	@At
	@Ok("json")
	@RequiresPermissions("plus1fix.manager.news.add")
	@SLog(tag = "添加文章", msg = "文章标题:${args[0].title}")
	@AdaptBy(type = WhaleAdaptor.class)
	public Object save(@Param("..") PlusNews news,
			@Param("pubTime") String pubTime, HttpServletRequest req) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			news.setPublishTime(sdf.parse(pubTime).getTime());
			newsService.insert(news);
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
	}

	@At("/edit/?")
	@Ok("beetl:/private/plus1fix/news/edit.html")
	@RequiresAuthentication
	public Object edit(long id, HttpServletRequest req) {
		return newsService.fetch(id);
	}
	
	@At
    @Ok("json")
    @RequiresPermissions("cms.content.article.edit")
    @SLog(tag = "", msg = "")
    @AdaptBy(type = WhaleAdaptor.class)
    public Object update(@Param("..") PlusNews news, @Param("pubTime") String pubTime, HttpServletRequest req) {
        try {
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			news.setPublishTime(sdf.parse(pubTime).getTime());
            newsService.updateIgnoreNull(news);
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

	@At("/show/?")
	@Ok("json")
	@RequiresPermissions("cms.content.article.edit")
	@SLog(tag = "发布", msg = "")
	public Object show(String id, @Param("flag") boolean flag,
			HttpServletRequest req) {
		try {
			newsService.update(org.nutz.dao.Chain.make("isShow", flag),
					Cnd.where("id", "=", id));
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
	}

	@At("/recommend/?")
	@Ok("json")
	@RequiresPermissions("cms.content.article.edit")
	@SLog(tag = "发布", msg = "")
	public Object recommend(String id, @Param("flag") boolean flag,
			HttpServletRequest req) {
		try {
			newsService.update(org.nutz.dao.Chain.make("isRecommend", flag),
					Cnd.where("id", "=", id));
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
	}

	@At("/hot/?")
	@Ok("json")
	@RequiresPermissions("cms.content.article.edit")
	@SLog(tag = "发布", msg = "")
	public Object hot(String id, @Param("flag") boolean flag,
			HttpServletRequest req) {
		try {
			newsService.update(org.nutz.dao.Chain.make("isHot", flag),
					Cnd.where("id", "=", id));
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
	}

	@At({ "/delete" })
	@Ok("json")
	@RequiresPermissions("cms.content.article.delete")
	@SLog(tag = "删除文章", msg = "IDS:${args[1].getAttribute('id')}")
	public Object delete(@Param("ids") String[] ids, HttpServletRequest req) {
		try {
			for (String id : ids) {
				newsService.update(org.nutz.dao.Chain.make("delFlag", true),
						Cnd.where("id", "=", id));
			}
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
	}

	@At("/recover/?")
	@Ok("json")
	@RequiresPermissions("cms.content.article.edit")
	@SLog(tag = "", msg = "文章标题:${args[1].getAttribute('title')}")
	public Object recover(String id, HttpServletRequest req) {
		try {
			newsService.update(org.nutz.dao.Chain.make("delFlag", false),
					Cnd.where("id", "=", id));
			return Result.success("system.success");
		} catch (Exception e) {
			return Result.error("system.error");
		}
	}
}
