package net.guides.dto;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SysUserUpdateDTO implements Serializable{
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