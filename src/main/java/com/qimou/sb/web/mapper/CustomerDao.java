package com.qimou.sb.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qimou.sb.web.entity.CustApplicationMan;
import com.qimou.sb.web.entity.CustInventMan;
import com.qimou.sb.web.entity.CustLinkMan;
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
	
	////////////////////////////////////////////////////////////////////////////
	
	@Select({"<script>",
		"SELECT customerID, customerName",
		" from bs_patent_customer_t where  userID = #{userID} ",
		"<if test='customerName!=null and customerName!=\"\"'> and customerName like CONCAT('%','#{customerName}','%')</if>",
		"</script>"})
    public List<Map<Object, Object>> listSimpleCustomer(Map<Object, Object> conditionMap);
	
	@Select({"<script>",
		"SELECT * ",
		" from bs_patent_cust_applicationman_t where 1=1 ",
		"<if test='customerID!=null and customerID!=\"\"'> and customerID=#{customerID}</if>",
		"limit #{startNum} , #{pageSize}",
		"</script>"})
    public List<CustApplicationMan> listCustApplicationman(Map<Object, Object> conditionMap);
	
	@Select({"<script>",
		    "SELECT count(*) from bs_patent_cust_applicationman_t where 1=1 ",
		    "<if test='customerID!=null and customerID!=\"\"'> and customerID=#{customerID}</if>",
		    "</script>"})
	public int custApplicationmanNum (Map<Object, Object> conditionMap);
	
	@Insert(
            "INSERT INTO bs_patent_cust_applicationman_t(customerID,appID,appCountry,appType,appName,appIDCode,isRecord,recordValidYear,cityAddr,address,zipCode)  "
            + "VALUES(#{customerID},#{appID},#{appCountry},#{appType},#{appName},#{appIDCode},#{isRecord},#{recordValidYear},#{cityAddr},#{address},#{zipCode}) "
            )
	public int addCustApplicationman (Map<Object, Object> conditionMap);
	
	@Update({
		"UPDATE                                      ",
		"  bs_patent_cust_applicationman_t           ",
		"SET                                         ",
		"  customerID = #{customerID},               ",
		"  appCountry = #{appCountry},               ",
		"  appType = #{appType},                     ",
		"  appName = #{appName},                     ",
		"  appIDCode = #{appIDCode},                 ",
		"  isRecord = #{isRecord},                   ",
		"  recordValidYear = #{recordValidYear},     ",
		"  cityAddr = #{cityAddr},                   ",
		"  address = #{address},                     ",
		"  zipCode = #{zipCode}                      ",
		"WHERE appID = #{appID}                      "
	})
	public int updateCustApplicationman(Map<Object, Object> conditionMap);
	
	///////////////
	
	@Select({"<script>",
		"SELECT * ",
		" from bs_patent_cust_inventman_t where 1=1 ",
		"<if test='customerID!=null and customerID!=\"\"'> and customerID=#{customerID}</if>",
		"<if test='inventName!=null and inventName!=\"\"'> and inventName=#{inventName}</if>",
		"limit #{startNum} , #{pageSize}",
		"</script>"})
    public List<CustInventMan> listCustInventman(Map<Object, Object> conditionMap);
	
	@Select({"<script>",
		    "SELECT count(*) from bs_patent_cust_inventman_t where 1=1 ",
		    "<if test='customerID!=null and customerID!=\"\"'> and customerID=#{customerID}</if>",
		    "<if test='inventName!=null and inventName!=\"\"'> and inventName=#{inventName}</if>",
		    "</script>"})
	public int custInventmanNum (Map<Object, Object> conditionMap);
	
	@Insert(
            "INSERT INTO bs_patent_cust_inventman_t(customerID,inventID,inventCountry,inventName,inventIDCode)  "
            + "VALUES(#{customerID},#{inventID},#{inventCountry},#{inventName},#{inventIDCode}) "
            )
	public int addCustInventman (Map<Object, Object> conditionMap);
	
	@Update({
		"UPDATE                                      ",
		"  bs_patent_cust_inventman_t                ",
		"SET                                         ",
		"  customerID = #{customerID},               ",
		"  inventCountry = #{inventCountry},         ",
		"  inventName = #{inventName},               ",
		"  inventIDCode = #{inventIDCode}            ",
		"WHERE inventID = #{inventID}                "
	})
	public int updateCustInventman(Map<Object, Object> conditionMap);
	
///////////////
	
	@Select({"<script>",
	"SELECT * ",
	" from bs_patent_cust_linkman_t where 1=1 ",
	"<if test='customerID!=null and customerID!=\"\"'> and customerID=#{customerID}</if>",
	"<if test='linkName!=null and linkName!=\"\"'> and linkName=#{linkName}</if>",
	"limit #{startNum} , #{pageSize}",
	"</script>"})
	public List<CustLinkMan> listCustLinkman(Map<Object, Object> conditionMap);
	
	@Select({"<script>",
	  "SELECT count(*) from bs_patent_cust_linkman_t where 1=1 ",
	  "<if test='customerID!=null and customerID!=\"\"'> and customerID=#{customerID}</if>",
	  "<if test='linkName!=null and linkName!=\"\"'> and linkName=#{linkName}</if>",
	  "</script>"})
	public int custLinkmanNum (Map<Object, Object> conditionMap);
	
	@Insert(
	  "INSERT INTO bs_patent_cust_linkman_t(customerID,linkID,linkName,nickName,linkCellPhone,linkEmail,linkQQ,linkWeChat,linkTel)  "
	  + "VALUES(#{customerID},#{linkID},#{linkName},#{nickName},#{linkCellPhone},#{linkEmail},#{linkQQ},#{linkWeChat},#{linkTel}) "
	  )
	public int addCustLinkman (Map<Object, Object> conditionMap);
	
	@Update({
		"UPDATE                                       ",
		"  bs_patent_cust_linkman_t                   ",
		"SET                                          ",
		"  customerID = #{customerID},                ",
		"  linkName = #{linkName},                    ",
		"  nickName = #{nickName},                    ",
		"  linkCellPhone = #{linkCellPhone},          ",
		"  linkEmail = #{linkEmail},                  ",
		"  linkQQ = #{linkQQ},                        ",
		"  linkWeChat = #{linkWeChat},                ",
		"  linkTel = #{linkTel}                       ",
		"WHERE linkID = #{linkID}                     "
	})
	public int updateCustLinkman(Map<Object, Object> conditionMap);
}
