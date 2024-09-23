package com.main.model;

import com.main.entity.Organization;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizerAccountModel {

    private String userName;
    private String emailId;
    private String password;
    private String organizationName;
    private Long mobileNumber;


}
