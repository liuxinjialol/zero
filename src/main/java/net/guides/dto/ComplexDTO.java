package net.guides.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ComplexDTO {

    String userId;
    String realName;
    String loginName;

    String roleId;
    String roleName;
    String roleCode;

}