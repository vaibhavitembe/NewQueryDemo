package com.zplus.dao;

import com.zplus.dto.res.DateResDto;
import com.zplus.model.CustModel;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;


public interface CustDao extends CrudRepository<CustModel, Integer> {

    @Query("From CustModel as cm where cm.custJoiningDate BETWEEN date(:custStartDate) AND date(:custEndDate)")
    List findByCustomerDate(@Param("custStartDate") Date custStartDate, @Param("custEndDate") Date custEndDate);

    @Query("From CustModel as cm order by cm.custName desc")
    List findCustomerNameDesc();

    @Query("From CustModel as cm WHERE cm.point < 80  ")
    List findByPoint();

    @Query("From CustModel as cm WHERE cm.custName LIKE %:searchtext%")
    List custNameWiseSearch(@Param("searchtext") String searchtext);

    @Transactional
    @Modifying
    @Query("update CustModel as cm set cm.custName=:custName where cm.custId=:custId")
    Integer UpdateCustomerIdWiseCustName(@Param("custId") Integer custId, @Param("custName") String custName);

    CustModel findAllByMobileNo(String userName);


    @Transactional
    @Modifying
    @Query("update CustModel as cm set cm.password=:newPassword where cm.custId=:custId")
    Integer updatePassword(@Param("custId") Integer custId, @Param("newPassword") String newPassword);

    @Query("select cm.password as password from CustModel as cm where cm.custId=:custId")
    String findOldPasswordByCustId(@Param("custId") Integer custId);

    CustModel findByMobileNo(String mobileNO);

//    @Transactional
//    @Modifying
//    @Query("update CustModel as cm set cm.otp=:otp where cm.custId=:custId")
//    Integer updateOtp(@Param("custId") Integer custId, @Param("otp") Integer otp);

    @Transactional
    @Modifying
    @Query("update CustModel as cm set cm.custId=:custId where cm.password=:password")
    Integer updatePassword(@Param("custId") Integer custId, @Param("password") Integer password);

    @Transactional
    @Modifying
    @Query("update CustModel as cm set cm.status=:status where cm.custId=:custId")
    Integer updateCustIdWiseCustStatus(@Param("custId") Integer custId, @Param("status") String status);

    @Query("Select new com.zplus.dto.res.DateResDto(cm.custId as custId,cm.custName as custName,cm.point as point,cm.mobileNo as mobileNo,cm.password as password,cm.otp as otp,cm.emailId as emailId,cm.status as status,cm.custJoiningDate as custJoiningDate) from CustModel cm where cm.custJoiningDate=:custJoiningDate")
    DateResDto displayAllRecordOnDate(@Param("custJoiningDate") Date custJoiningDate);

    @Query("select count(cm.status) from CustModel as cm where cm.status='Active'")
    Integer getByCountActiveStatus();

    CustModel findByEmailId(String emailId);

    @Transactional
    @Modifying
    @Query("update CustModel as cm set cm.otp=:otp where cm.custId=:custId")
    Integer updateOtp(@Param("custId") Integer custId, @Param("otp") Integer otp);


}
