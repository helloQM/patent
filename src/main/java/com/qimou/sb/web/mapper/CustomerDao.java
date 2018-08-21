package com.qimou.sb.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qimou.sb.web.entity.Customer;

public interface CustomerDao {
	
	
	@Select("select  * from bs_patent_customer_t order by createTime desc limit #{startNum} , #{pageSize}")
    public List<Customer> listCustomer(@Param("startNum")int startNum,@Param("pageSize")int pageSize);
	
	@Select("select count(*) from bs_patent_customer_t ")
	public int customerNum ();
	
	@Select("select * from bs_patent_customer_t where customerID=#{customerID}")
	public Customer singleCustomer(String customerID);
	
	
	@Insert(
            "INSERT INTO bs_patent_customer_t  (customerID,  userID,  customerName,  inventMan,  applicationMan,  linkMan,  linkCellPhone,  linkEmail,  customerBalance) "
            + "values(#{customerID},#{userID},#{customerName},#{inventMan},#{applicationMan},#{linkMan},#{linkCellPhone},#{linkEmail},#{customerBalance})"
            )
	public int addCustomer (Customer customer);
	
	@Update("UPDATE  bs_patent_customer_t "
			+ "set inventMan = #{inventMan},"
			+ "applicationMan = #{applicationMan},"
			+ "linkMan = #{linkMan},"
			+ "linkCellPhone = #{linkCellPhone},"
			+ "linkEmail = #{linkEmail},"
			+ "customerBalance = #{customerBalance} "
			+ "where customerID = #{customerID}")
	public int updateCustomer(Customer customer);
	

}
