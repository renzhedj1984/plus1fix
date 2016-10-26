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
 * Comment Model
 * 
 * @author peter-zhang
 *
 */
@Table("plus_comment")
public class PlusComment implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1455473046362208855L;
	public static final int STATUS_FLAG_INUSE = 1;
	public static final int STATUS_FLAG_DEL = 2;

	@Column
	@Id
	@Comment("id")
	@ColDefine(type = ColType.INT, width = 32)
	private long id;

	@Column
	@Comment("cid")
	@ColDefine(type = ColType.INT, width = 32)
	private long cid;

	@One(target = PlusCustomer.class, field = "cid")
	private PlusCustomer customer;

	@Column
	@Comment("customerAccount")
	@ColDefine(type = ColType.INT, width = 32)
	private String customerAccount;

	@Column
	@Comment("oid")
	@ColDefine(type = ColType.INT, width = 32)
	private long oid;

	@One(target = PlusOrder.class, field = "oid")
	private PlusOrder order;

	@Column
	@Comment("service_score:1.0~5.0")
	@ColDefine(type = ColType.FLOAT, width = 2)
	private float serviceScore = 0.0f;

	@Column
	@Comment("speed_score:1.0~5.0")
	@ColDefine(type = ColType.FLOAT, width = 2, precision = 1)
	private float speedScore = 0.0f;

	@Column
	@Comment("skill_score:1.0~5.0")
	@ColDefine(type = ColType.FLOAT, width = 2, precision = 1)
	private float skillScore = 0.0f;

	@Column
	@Comment("price_score:1.0~5.0")
	@ColDefine(type = ColType.FLOAT, width = 2, precision = 1)
	private float priceScore = 0.0f;

	@Column
	@Comment("total_score:1.0~5.0")
	@ColDefine(type = ColType.FLOAT, width = 2, precision = 1)
	private float totalScore = 0.0f;

	@Column
	@Comment("type_flag:{1:new,2:flow}")
	@ColDefine(type = ColType.INT, width = 1)
	private int typeFlag;

	@Column
	@Comment("status_flag:{1:inuse,2:del}")
	@ColDefine(type = ColType.INT, width = 1)
	private int statusFlag = 1;

	@Column
	@Comment("content")
	@ColDefine(type = ColType.VARCHAR, width = 100)
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

	public long getCid() {
		return cid;
	}

	public void setCid(long cid) {
		this.cid = cid;
	}

	public PlusCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(PlusCustomer customer) {
		this.customer = customer;
	}

	public long getOid() {
		return oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public PlusOrder getOrder() {
		return order;
	}

	public void setOrder(PlusOrder order) {
		this.order = order;
	}

	public float getServiceScore() {
		return serviceScore;
	}

	public void setServiceScore(float serviceScore) {
		this.serviceScore = serviceScore;
	}

	public float getSpeedScore() {
		return speedScore;
	}

	public void setSpeedScore(float speedScore) {
		this.speedScore = speedScore;
	}

	public float getSkillScore() {
		return skillScore;
	}

	public void setSkillScore(float skillScore) {
		this.skillScore = skillScore;
	}

	public float getPriceScore() {
		return priceScore;
	}

	public void setPriceScore(float priceScore) {
		this.priceScore = priceScore;
	}

	public float getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(float totalScore) {
		this.totalScore = totalScore;
	}

	public int getTypeFlag() {
		return typeFlag;
	}

	public void setTypeFlag(int typeFlag) {
		this.typeFlag = typeFlag;
	}

	public int getStatusFlag() {
		return statusFlag;
	}

	public void setStatusFlag(int statusFlag) {
		this.statusFlag = statusFlag;
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

	public String getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(String customerAccount) {
		this.customerAccount = customerAccount;
	}
}
