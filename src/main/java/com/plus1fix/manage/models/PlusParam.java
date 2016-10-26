package com.plus1fix.manage.models;

import java.io.Serializable;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import cn.wizzer.common.base.Model;

/**
 * Param Model
 * 
 * @author peter-zhang
 *
 */
@Table("plus_param")
public class PlusParam extends Model implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1455473046362208855L;

	@Column
	@Id
	@Comment("id")
	@ColDefine(type = ColType.INT, width = 32)
	private long id;

	@Column
	@Comment("name")
	@ColDefine(type = ColType.VARCHAR, width = 20)
	private String name;

	@Column
	@Comment("content")
	@ColDefine(type = ColType.VARCHAR, width = 50)
	private String content;
	
	@Column
	@Comment("create_time")
	@ColDefine(type = ColType.INT, width = 20)
	private long createTime = System.currentTimeMillis();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
