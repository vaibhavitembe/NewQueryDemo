package com.zplus.dto.res;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class NextFollwUpdateResDto {

    private Integer custId;
    private String name;
    private String address;
    private String status;

    public NextFollwUpdateResDto(String name, String address, String status, String mobile, Date custDate) {
        this.name = name;
        this.address = address;
        this.status = status;
        this.mobile = mobile;
        this.custDate = custDate;
    }

    private String mobile;

    public NextFollwUpdateResDto(Integer custId) {
        this.custId = custId;
    }

    private Date custDate;

    public NextFollwUpdateResDto() {

    }
}
