package com.main.model;

import com.main.entity.Organization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateMemberDTO {

    private int id;
    private String email;
    private String name;
    private long phoneNumber;
    private String password;
    private Organization organisationId;

}
