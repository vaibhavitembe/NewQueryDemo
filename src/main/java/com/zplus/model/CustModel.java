package com.zplus.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "custquerydemo")
public class CustModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(length = 20)
    private Integer custId;

    @Column(length = 200)
    private String custName;

    @Column(length = 400)
    @Temporal(TemporalType.DATE)
    private Date custJoiningDate;

    @Column(length = 100)
    private Integer point;

    @Column(length = 500)
    private String mobileNo;

    @Column(length = 200)
    private String password;

    @Column(length = 200)
    private Integer otp;

    @Column(length = 250)
    private String emailId;

    @Column(length = 200)
    private String status;


//
//    @Column
//    @Temporal(TemporalType.DATE)
//    private Date nextFollowUpdate;
//

}
