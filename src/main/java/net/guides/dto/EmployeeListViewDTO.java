package net.guides.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeListViewDTO {
    private String id;
    private String firstName;
    private String lastName;    
}
