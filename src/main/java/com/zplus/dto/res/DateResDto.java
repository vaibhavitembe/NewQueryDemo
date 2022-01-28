package com.zplus.dto.res;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

public class DateResDto {

    private Integer custId;
    private String custName;
    private Integer point;
    private String mobileNo;
    private String password;
    private Integer otp;
    private String emailId;
    private String status;
    private Date custJoiningDate;

    public DateResDto(Integer custId, String custName, Integer point, String mobileNo, String password, Integer otp, String emailId, String status, Date custJoiningDate) {
        this.custId = custId;
        this.custName = custName;
        this.point = point;
        this.mobileNo = mobileNo;
        this.password = password;
        this.otp = otp;
        this.emailId = emailId;
        this.status = status;
        this.custJoiningDate = custJoiningDate;
    }
}
