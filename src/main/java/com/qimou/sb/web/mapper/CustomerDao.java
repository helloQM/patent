package com.qimou.sb.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qimou.sb.web.entity.Customer;

public interface CustomerDao {
	
	
	@Select({"<script>",
		"SELECT * ",
		" from bs_patent_customer_t where 1=1 ",
		"<if test='customerCode!=null and customerCode!=\"\"'> and customerCode=#{customerCode}</if>",
		"<if test='customerName!=null and customerName!=\"\"'> and customerName=#{customerName}</if>",
		"<if test='linkMan!=null and linkMan!=\"\"'> and linkMan=#{linkMan}</if>",
		"<if test='linkCellPhone!=null and linkCellPhone!=\"\"'> and linkCellPhone=#{linkCellPhone}</if>",
		"<if test='userName!=null and userName!=\"\"'> and userName=#{userName}</if>",
		"<if test='customerStat!=null and customerStat!=\"\"'> and customerStat=#{customerStat}</if>",
		"<if test='customerType!=null and customerType!=\"\"'> and customerType=#{customerType}</if>",
		"order by createTime desc ",
		"limit #{startNum} , #{pageSize}",
		"</script>"})
    public List<Customer> listCustomer(Map<Object, Object> conditionMap);
	
	@Select({"<script>",
		    "SELECT count(*) from bs_patent_customer_t where 1=1 ",
		    "<if test='customerCode!=null and customerCode!=\"\"'> and customerCode=#{customerCode}</if>",
			"<if test='customerName!=null and customerName!=\"\"'> and customerName=#{customerName}</if>",
			"<if test='linkMan!=null and linkMan!=\"\"'> and linkMan=#{linkMan}</if>",
			"<if test='linkCellPhone!=null and linkCellPhone!=\"\"'> and linkCellPhone=#{linkCellPhone}</if>",
			"<if test='userName!=null and userName!=\"\"'> and userName=#{userName}</if>",
			"<if test='customerStat!=null and customerStat!=\"\"'> and customerStat=#{customerStat}</if>",
			"<if test='customerType!=null and customerType!=\"\"'> and customerType=#{customerType}</if>",
		    "</script>"})
	public int customerNum (Map<Object, Object> conditionMap);
	
	@Insert(
            "INSERT INTO bs_patent_customer_t (customerID,customerCode,userName,userID,customerName,customerType,linkMan,linkCellPhone,linkEmail,communicateType,customerStat)  "
            + "VALUES(#{customerID},#{customerCode},#{userName},#{userID},#{customerName},#{customerType},#{linkMan},#{linkCellPhone},#{linkEmail},#{communicateType},#{customerStat}) "
            )
	public int addCustomer (Map<Object, Object> conditionMap);
	
	@Update({
		"UPDATE  bs_patent_customer_t            ",
		"SET                                     ",
		"  customerCode = #{customerCode},       ",
		"  customerName = #{customerName},       ",
		"  customerType = #{customerType},       ",
		"  linkMan = #{linkMan},                 ",
		"  linkCellPhone = #{linkCellPhone},     ",
		"  linkEmail = #{linkEmail},             ",
		"  communicateType = #{communicateType}, ",
		"  customerStat = #{customerStat}        ",
		"WHERE customerID = #{customerID}        "
	})
	public int updateCustomer(Map<Object, Object> conditionMap);
	
	@Select({"<script>",
		"SELECT customerID, customerName",
		" from bs_patent_customer_t where  userID = #{userID} ",
		"<if test='customerName!=null and customerName!=\"\"'> and customerName like CONCAT('%','#{customerName}','%')</if>",
		"</script>"})
    public List<Map<Object, Object>> listSimpleCustomer(Map<Object, Object> conditionMap);

}
