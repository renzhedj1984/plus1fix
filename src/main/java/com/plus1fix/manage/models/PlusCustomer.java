package com.plus1fix.manage.models;

import java.io.Serializable;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * Customer Model
 * @author peter-zhang
 *
 */
@Table("plus_customer")
public class PlusCustomer implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1774528567985738156L;
	public static final int STATUS_FLAG_INUSE = 1;
	public static final int STATUS_FLAG_FORBID = 2;
	public static final int STATUS_FLAG_DEL = 3;

	@Column
    @Id
    @Comment("id")
    @ColDefine(type = ColType.INT, width = 32)
    private long id;
	
	@Column
    @Comment("account")
    @ColDefine(type = ColType.VARCHAR, width = 20)
    private String account;

    @Column
    @Comment("real_name")
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String realName;
    
    @Column
    @Comment("password")
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String password;
    
    @Column
    @Comment("password_salt")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String salt;
    
    @Column
    @Comment("phone")
    @ColDefine(type = ColType.VARCHAR, width = 25)
    private String phone;
    
    @Column
    @Comment("email")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String email;
    
    @Column
    @Comment("create_time")
    @ColDefine(type = ColType.INT,width=20)
    private long createTime = System.currentTimeMillis();
    
    @Column
    @Comment("status_flag:{1:inuse,-1:forbid,-2:del}")
    @ColDefine(type = ColType.INT,width=1)
    private int statusFlag = 1;
    
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public int getStatusFlag() {
		return statusFlag;
	}

	public void setStatusFlag(int statusFlag) {
		this.statusFlag = statusFlag;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
}
