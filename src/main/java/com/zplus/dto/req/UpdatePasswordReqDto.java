package com.zplus.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePasswordReqDto {
    private Integer custId;
    private String password;

}
