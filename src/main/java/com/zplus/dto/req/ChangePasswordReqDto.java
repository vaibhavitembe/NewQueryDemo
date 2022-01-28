package com.zplus.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ChangePasswordReqDto {


    private String newPassword;
    private Integer custId;
    private String oldPassword;

}
