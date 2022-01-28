package com.zplus.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

public class CustDto {

    private Integer custId;
    private String custName;
    private Date custJoiningDate;
    private Integer point;
    private String mobileNo;
    private String password;
    private Integer otp;
    private String email;
    private String status;

}
