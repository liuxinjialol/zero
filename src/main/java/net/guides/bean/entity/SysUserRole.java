package net.guides.bean.entity;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "sys_user_role")
@Data
@NoArgsConstructor
public class SysUserRole implements Serializable{
	@Id
	private String id;
	private String userId;
	private String roleId;
	private Date createdAt;
	private String createdBy;
}
