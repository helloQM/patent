package com.qimou.sb.web.entity;

import java.io.Serializable;

/**
 SELECT CONCAT(
          'private ',
          CASE
             WHEN data_type = 'varchar' THEN 'String '
             WHEN data_type = 'timestamp' THEN 'String '
             WHEN data_type = 'date' THEN 'String '
             WHEN data_type = 'float' THEN 'float '
             WHEN data_type = 'int' THEN 'int '
             ELSE ''
          END,
          LOWER(SUBSTRING(column_name, 1, 1)),
          SUBSTRING(column_name, 2, LENGTH(column_name)),
          CASE
             WHEN data_type = 'varchar' THEN ' = null ;'
             WHEN data_type = 'timestamp' THEN ' = null ;'
             WHEN data_type = 'date' THEN ' = null ;'
             WHEN data_type = 'float' THEN ' = 0 ;'
             WHEN data_type = 'int' THEN ' = 0 ;'
             ELSE ''
          END,
          '//',
          column_comment)
          aaa
  FROM information_schema.columns
 WHERE table_schema = 'mybatis' AND table_name = 't_policy'
 * 
 */
public class Policy implements Serializable{
	private String policyID = null;// 招商政策ID
	private String parkID = null;//
	private String title = null;// 招商政策标题
	private String policyStr = null;// 招商政策内容
	private String newTime = null;// 创建时间

	public String getPolicyID() {
		return policyID;
	}

	public void setPolicyID(String policyID) {
		this.policyID = policyID;
	}

	public String getParkID() {
		return parkID;
	}

	public void setParkID(String parkID) {
		this.parkID = parkID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPolicyStr() {
		return policyStr;
	}

	public void setPolicyStr(String policyStr) {
		this.policyStr = policyStr;
	}

	public String getNewTime() {
		return newTime;
	}

	public void setNewTime(String newTime) {
		this.newTime = newTime;
	}

	@Override
	public String toString() {
		return "Policy [policyID=" + policyID + ", parkID=" + parkID + ", title=" + title + ", policyStr=" + policyStr + ", newTime=" + newTime + "]";
	}
	
}
