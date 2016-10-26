package com.plus1fix.manage.models;

import java.io.Serializable;
import java.util.List;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Many;
import org.nutz.dao.entity.annotation.Table;

import cn.wizzer.common.base.Model;

/**
 * Phone Brand Model
 * 
 * @author peter-zhang
 *
 */
@Table("plus_phone_brand")
public class PlusPhoneBrand extends Model implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1153621266628759998L;

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
	@Comment("show_index")
	@ColDefine(type = ColType.INT)
	private int showIndex;

	@Column
	@Comment("create_time")
	@ColDefine(type = ColType.INT, width = 20)
	private long createTime = System.currentTimeMillis();

	@Many(target = PlusPhoneType.class, field = "bid")
	private List<PlusPhoneType> plusPhoneTypes;

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

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public List<PlusPhoneType> getPlusPhoneTypes() {
		return plusPhoneTypes;
	}

	public void setPlusPhoneTypes(List<PlusPhoneType> plusPhoneTypes) {
		this.plusPhoneTypes = plusPhoneTypes;
	}

	public int getShowIndex() {
		return showIndex;
	}

	public void setShowIndex(int showIndex) {
		this.showIndex = showIndex;
	}
}
