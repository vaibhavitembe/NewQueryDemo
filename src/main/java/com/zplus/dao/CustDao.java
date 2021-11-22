package com.zplus.dao;

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
}
