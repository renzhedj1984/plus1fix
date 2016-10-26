package com.plus1fix.manage.models;

import java.io.Serializable;
import java.util.List;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;

import cn.wizzer.common.base.Model;

/**
 * Malfunction Model
 * @author peter-zhang
 */
@Table("plus_malfunction")
public class PlusMalfunction extends Model implements Serializable {
	private static final long serialVersionUID = -1134674047795836516L;

	@Column
    @Id
    @Comment("id")
    @ColDefine(type = ColType.INT, width = 32)
    private long id;
	
	@Column
    @Comment("phone_type_id")
    @ColDefine(type = ColType.INT, width = 32)
    private long phoneTypeId;
	
	@One(target=PlusPhoneType.class,field="phoneTypeId")
	private PlusPhoneType plusPhoneType;

    @Column
    @Comment("parent_id")
    @ColDefine(type = ColType.INT, width = 32)
    private long parentId;

    @Column
    @Comment("path")
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String path;

    @Column
    @Comment("name")
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String name;
    
    @Column
    @Comment("descption")
    @ColDefine(type = ColType.VARCHAR, width = 200)
    private String descption;

    @Column
    @Comment("icon_path")
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String iconPath;
 
    @Column
	@Comment("probability")
	@ColDefine(type = ColType.FLOAT, width = 3, precision = 2)
    private float probability = 0.0f;

    @Column
    @Comment("has_children")
    @ColDefine(type = ColType.BOOLEAN)
    private boolean hasChildren;
    
    private List<PlusMalfunction> childs;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescption() {
		return descption;
	}

	public void setDescption(String descption) {
		this.descption = descption;
	}

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	public float getProbability() {
		return probability;
	}

	public void setProbability(float probability) {
		this.probability = probability;
	}

	public boolean isHasChildren() {
		return hasChildren;
	}

	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
	}

	public List<PlusMalfunction> getChilds() {
		return childs;
	}

	public void setChilds(List<PlusMalfunction> childs) {
		this.childs = childs;
	}

	public long getPhoneTypeId() {
		return phoneTypeId;
	}

	public void setPhoneTypeId(long phoneTypeId) {
		this.phoneTypeId = phoneTypeId;
	}

	public PlusPhoneType getPlusPhoneType() {
		return plusPhoneType;
	}

	public void setPlusPhoneType(PlusPhoneType plusPhoneType) {
		this.plusPhoneType = plusPhoneType;
	}
}
