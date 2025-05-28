package net.guides.bean.entity;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "sys_user")
@Data
@NoArgsConstructor
public class SysUser implements Serializable{
	@Id
	private String id;
	private String loginName;
	private String loginPassword;
	private String realName;
	private String mobile;
	private Date createdAt;
	private Date updatedAt;
	private Date deletedAt;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private int status;
		
}
