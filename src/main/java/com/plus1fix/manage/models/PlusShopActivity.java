package com.plus1fix.manage.models;

import java.io.Serializable;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;

/**
 * Shop Activity Model
 * @author peter-zhang
 *
 */
@Table("plus_shop_activity")
public class PlusShopActivity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2241598866297207108L;

	@Column
    @Id
    @Comment("id")
    @ColDefine(type = ColType.INT, width = 32)
    private long id;
	
	@Column
    @Comment("sid")
    @ColDefine(type = ColType.INT, width = 32)
    private long sid;
	
	@One(target = PlusShop.class, field = "sid")
    private PlusShop shop;
	
	@Column
    @Comment("title")
    @ColDefine(type = ColType.VARCHAR,width=100)
    private String title;
	
	@Column
    @Comment("content")
    @ColDefine(type = ColType.VARCHAR,width=200)
    private String content;

	@Column
    @Comment("create_time")
    @ColDefine(type = ColType.INT,width=20)
    private long createTime = System.currentTimeMillis();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getSid() {
		return sid;
	}

	public void setSid(long sid) {
		this.sid = sid;
	}

	public PlusShop getShop() {
		return shop;
	}

	public void setShop(PlusShop shop) {
		this.shop = shop;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
}
