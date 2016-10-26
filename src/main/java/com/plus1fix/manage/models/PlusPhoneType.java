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
 * Phone Type Model
 * @author peter-zhang
 *
 */
@Table("plus_phone_type")
public class PlusPhoneType extends Model implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3197124349888705363L;

	@Column
    @Id
    @Comment("id")
    @ColDefine(type = ColType.INT, width = 32)
    private long id;
	
	@Column
    @Comment("bid")
    @ColDefine(type = ColType.INT, width = 32)
    private long bid;
	
	@Column
    @Comment("name")
    @ColDefine(type = ColType.VARCHAR,width=50)
    private String name;
	
	@Column
	@Comment("show_index")
	@ColDefine(type = ColType.INT)
	private int showIndex;
	
	@Column
    @Comment("icon_path")
    @ColDefine(type = ColType.VARCHAR,width=50)
    private String iconPath;

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

	public long getBid() {
		return bid;
	}

	public void setBid(long bid) {
		this.bid = bid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public int getShowIndex() {
		return showIndex;
	}

	public void setShowIndex(int showIndex) {
		this.showIndex = showIndex;
	}
}
