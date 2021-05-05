package com.edu.entity;

import javax.persistence.*;

@Entity
public class User {
	
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", nickName=" + nickName
				+ ", roleId=" + roleId + ", isDelete=" + isDelete + ", createTime=" + createTime + ", lastLoginTime="
				+ lastLoginTime + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 20)
	private String userName;
	
	@Column(length = 20)
	private String password;
	
	@Column(length = 30)
	private String nickName;
	
	
	@Column(length = 2)
	private String roleId;
	
	@Column(length = 1)
	private String isDelete;
	
	@Column(length = 8)
	private String createTime;
	
	@Column(length = 8)
	private String lastLoginTime;
	

	//无参构造方法，这个必须要有，不然会报错
    public User() {
        
    }

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

    
    
	
}
