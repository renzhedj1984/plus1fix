package com.plus1fix.manage.models;

import java.io.Serializable;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * Provider Model
 * @author peter-zhang
 *
 */
@Table("plus_provider")
public class PlusProvider implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1774528567985738156L;
	public static final int STATUS_FLAG_INUSE = 1;
	public static final int STATUS_FLAG_FORBID = 2;
	public static final int STATUS_FLAG_DEL = 3;
	public static final int PROCESS_FLAG_APPLY = 1;
	public static final int PROCESS_FLAG_REFUSE = 2;
	public static final int PROCESS_FLAG_PASS = 3;

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
    @Comment("company_name")
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String companyName;
    
    @Column
    @Comment("password")
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String password;
    
    @Column
    @Comment("password_salt")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String salt;
    
    @Column
    @Comment("contact_person")
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String contactPerson;
    
    @Column
    @Comment("phone")
    @ColDefine(type = ColType.VARCHAR, width = 25)
    private String phone;
    
    @Column
    @Comment("email")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String email;
    
    @Column
    @Comment("bus_license_path")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String busLicensePath;
    
    @Column
    @Comment("create_time")
    @ColDefine(type = ColType.INT,width=20)
    private long createTime = System.currentTimeMillis();
    
    @Column
    @Comment("status_flag:{1:inuse,2:forbid,3:del}")
    @ColDefine(type = ColType.INT,width=1)
    private int statusFlag = 1;
    
    @Column
    @Comment("process_flag:{1:apply,2:refuse,3:pass}")
    @ColDefine(type = ColType.INT,width=1)
    private int processFlag = 1;
    
    @Column
    @Comment("address")
    @ColDefine(type = ColType.TEXT)
    private String address;
    
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
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

	public String getBusLicensePath() {
		return busLicensePath;
	}

	public void setBusLicensePath(String busLicensePath) {
		this.busLicensePath = busLicensePath;
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

	public int getProcessFlag() {
		return processFlag;
	}

	public void setProcessFlag(int processFlag) {
		this.processFlag = processFlag;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
}
