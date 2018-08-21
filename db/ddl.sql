drop table if exists bs_patent_accounterDetail_t;

drop table if exists bs_patent_agent_t;

drop table if exists bs_patent_authority_t;

drop table if exists bs_patent_cityCodeDict_t;

drop table if exists bs_patent_contract_t;

drop table if exists bs_patent_customer_t;

drop table if exists bs_patent_roleAuthority_t;

drop table if exists bs_patent_roleDict_t;

drop table if exists bs_patent_servicePriceDict_t;

drop table if exists bs_patent_user_t;

drop table if exists bs_patent_usertask_t;

/*==============================================================*/
/* Table: bs_patent_accounterDetail_t                           */
/*==============================================================*/
create table bs_patent_accounterDetail_t
(
   customerID           varchar(36) comment '客户ID',
   userID               varchar(20) comment '用户ID',
   optionTime           timestamp default CURRENT_TIMESTAMP comment '操作时间',
   moneyNum             int(11) comment '资金数值【单位：元】',
   moneyDirection       char comment '资金流动方向【+：入账； -：出账】',
   descReason           varchar(200) comment '资金流动的原因描述'
);

alter table bs_patent_accounterDetail_t comment '账户流水明细表';

/*==============================================================*/
/* Table: bs_patent_agent_t                                     */
/*==============================================================*/
create table bs_patent_agent_t
(
   userID               varchar(20) comment '用户ID，用作登录账号',
   skillArea            varchar(30) comment '技术领域：电子‘机械，生物医药等',
   jobNum               varchar(30) comment '营业执照号'
);

alter table bs_patent_agent_t comment '代理人详情信息表';

drop table if exists bs_patent_authority_t;

/*==============================================================*/
/* Table: bs_patent_authority_t                                 */
/*==============================================================*/
create table bs_patent_authority_t
(
   id                   int(3) not null comment '目录ID',
   pId                  int(3) comment '父目录ID',
   name                 varchar(20) comment '目录名称',
   path                 varchar(50) comment '目录相对路径',
   description          varchar(100) comment '目录功能介绍',
   open                 int(1) comment '是否展开标识：0标识不展开，1标识展开',
   primary key (id)
);

alter table bs_patent_authority_t comment '页面权限表';



/*==============================================================*/
/* Table: bs_patent_cityCodeDict_t                              */
/*==============================================================*/
create table bs_patent_cityCodeDict_t
(
   cityCode             varchar(6) not null comment '编码【六位一体】',
   pid                  varchar(6) comment '父一级编码【六位一体】',
   cityName             varchar(20) comment '名称',
   primary key (cityCode)
);

alter table bs_patent_cityCodeDict_t comment '城市编码字典表';

/*==============================================================*/
/* Table: bs_patent_contract_t                                  */
/*==============================================================*/
create table bs_patent_contract_t
(
   contractID           varchar(36) comment '合同ID【一个合同下有一个或多个任务】',
   contractName         varchar(30) comment '合同名称',
   taskID               varchar(36) not null comment '任务ID',
   taskName             varchar(30) comment '任务名称',
   userID               varchar(20) comment '用户ID【创建人】',
   customerID           varchar(36) comment '客户ID',
   stat                 int(2) comment '任务状态【10：新建任务；11：审核通过；12：获得交底书；
                                20：分配中；14：撰写中；15：撰写审核通过；
                                30：递交中【形成五书】；31：获得受理通知书；32：首次驳回；33：第二次驳回；34：第三次驳回；36：国家专利局审核通过】',
   pathJDS              varchar(100) comment '交底书存放的相对路径',
   createTime           timestamp default CURRENT_TIMESTAMP comment '合同创建时间',
   primary key (taskID)
);

alter table bs_patent_contract_t comment '合同状态表';

/*==============================================================*/
/* Table: bs_patent_customer_t                                  */
/*==============================================================*/
create table bs_patent_customer_t
(
   customerID           varchar(36) not null comment '客户ID',
   userID               varchar(20) comment '用户ID【创建人：销售角色】',
   customerName         varchar(100) comment '客户名称',
   inventMan            varchar(30) comment '发明人姓名',
   applicationMan       varchar(30) comment '申请人姓名',
   linkMan              varchar(30) comment '联系人姓名',
   linkCellPhone        varchar(11) comment '联系人手机号',
   linkEmail            varchar(30) comment '联系人邮箱地址',
   customerBalance      int(11) default 0 comment '客户余额',
   createTime           timestamp default CURRENT_TIMESTAMP comment '客户创建时间',
   primary key (customerID)
);

alter table bs_patent_customer_t comment '客户信息表';

/*==============================================================*/
/* Table: bs_patent_roleAuthority_t                             */
/*==============================================================*/
create table bs_patent_roleAuthority_t
(
   id                   int(3) comment '页面ID',
   roleID               varchar(2) comment '角色ID'
);

alter table bs_patent_roleAuthority_t comment '角色权限中间i表';

/*==============================================================*/
/* Table: bs_patent_roleDict_t                                  */
/*==============================================================*/
create table bs_patent_roleDict_t
(
   roleID               varchar(2) not null comment '角色ID',
   roleName             varchar(20) comment '角色名称',
   bak                  varchar(50) comment '备注说明',
   primary key (roleID)
);

alter table bs_patent_roleDict_t comment '角色字典表';

/*==============================================================*/
/* Table: bs_patent_servicePriceDict_t                          */
/*==============================================================*/
create table bs_patent_servicePriceDict_t
(
   serviceID            varchar(36) not null comment '服务ID',
   cityCode             varchar(6) comment '城市编码【六位一体】',
   serviceCode          varchar(16) comment '服务编码',
   serviceName          varchar(30) comment '服务名称',
   serviceBak           varchar(50) comment '服务介绍',
   serviceType          varchar(50) comment '服务类型',
   priceNumMin          int(10) comment '价格下限数值【单位：元】',
   priceNumMax          int(11) comment '价格上限数值【单位：元】',
   primary key (serviceID)
);

alter table bs_patent_servicePriceDict_t comment '业务模块-专利-服务价格字典表【按照城市区分】';

/*==============================================================*/
/* Table: bs_patent_user_t                                      */
/*==============================================================*/
create table bs_patent_user_t
(
   userID               varchar(20) not null comment '用户ID，用作登录账号',
   userName             varchar(30) comment '用户名称',
   pwd                  varchar(16) default 'qimou123456' comment '登录密码',
   userRole             varchar(10) comment '用户角色【0：超级管理员；1：销售人员；2：专利代理人【全职】；3：专利代理人【兼职】4：财务人员；5：流程人】',
   userEmail            varchar(30) comment '用户邮箱地址',
   userCellPhone        varchar(11) comment '用户手机',
   userStat             int(1) comment '用户状态【1：正常；2：注销】',
   userDepartment       varchar(20) comment '用户部门',
   userGender           int(1) comment '用户性别：0，表示女；1，表示男',
   createTime           timestamp comment '创建时间',
   primary key (userID)
);

alter table bs_patent_user_t comment '用户信息表';

/*==============================================================*/
/* Table: bs_patent_usertask_t                                  */
/*==============================================================*/
create table bs_patent_usertask_t
(
   taskID               varchar(36) comment '任务ID',
   userID               varchar(20) comment '用户ID',
   fenpeiTime           timestamp default CURRENT_TIMESTAMP comment '任务分配时间'
);

alter table bs_patent_usertask_t comment '用户任务中间表';

alter table bs_patent_accounterDetail_t add constraint FK_Reference_1 foreign key (customerID)
      references bs_patent_customer_t (customerID) on delete restrict on update restrict;

alter table bs_patent_accounterDetail_t add constraint FK_Reference_2 foreign key (userID)
      references bs_patent_user_t (userID) on delete restrict on update restrict;

alter table bs_patent_agent_t add constraint FK_Reference_9 foreign key (userID)
      references bs_patent_user_t (userID) on delete restrict on update restrict;

alter table bs_patent_contract_t add constraint FK_Reference_4 foreign key (userID)
      references bs_patent_user_t (userID) on delete restrict on update restrict;

alter table bs_patent_contract_t add constraint FK_Reference_8 foreign key (customerID)
      references bs_patent_customer_t (customerID) on delete restrict on update restrict;

alter table bs_patent_customer_t add constraint FK_Reference_5 foreign key (userID)
      references bs_patent_user_t (userID) on delete restrict on update restrict;

alter table bs_patent_roleAuthority_t add constraint FK_Reference_11 foreign key (id)
      references bs_patent_authority_t (id) on delete restrict on update restrict;

alter table bs_patent_roleAuthority_t add constraint FK_Reference_12 foreign key (roleID)
      references bs_patent_roleDict_t (roleID) on delete restrict on update restrict;

alter table bs_patent_servicePriceDict_t add constraint FK_Reference_10 foreign key (cityCode)
      references bs_patent_cityCodeDict_t (cityCode) on delete restrict on update restrict;

alter table bs_patent_usertask_t add constraint FK_Reference_6 foreign key (taskID)
      references bs_patent_contract_t (taskID) on delete restrict on update restrict;

alter table bs_patent_usertask_t add constraint FK_Reference_7 foreign key (userID)
      references bs_patent_user_t (userID) on delete restrict on update restrict;
