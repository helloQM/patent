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
   customerID           varchar(36) comment '�ͻ�ID',
   userID               varchar(20) comment '�û�ID',
   optionTime           timestamp default CURRENT_TIMESTAMP comment '����ʱ��',
   moneyNum             int(11) comment '�ʽ���ֵ����λ��Ԫ��',
   moneyDirection       char comment '�ʽ���������+�����ˣ� -�����ˡ�',
   descReason           varchar(200) comment '�ʽ�������ԭ������'
);

alter table bs_patent_accounterDetail_t comment '�˻���ˮ��ϸ��';

/*==============================================================*/
/* Table: bs_patent_agent_t                                     */
/*==============================================================*/
create table bs_patent_agent_t
(
   userID               varchar(20) comment '�û�ID��������¼�˺�',
   skillArea            varchar(30) comment '�������򣺵��ӡ���е������ҽҩ��',
   jobNum               varchar(30) comment 'Ӫҵִ�պ�'
);

alter table bs_patent_agent_t comment '������������Ϣ��';

drop table if exists bs_patent_authority_t;

/*==============================================================*/
/* Table: bs_patent_authority_t                                 */
/*==============================================================*/
create table bs_patent_authority_t
(
   id                   int(3) not null comment 'Ŀ¼ID',
   pId                  int(3) comment '��Ŀ¼ID',
   name                 varchar(20) comment 'Ŀ¼����',
   path                 varchar(50) comment 'Ŀ¼���·��',
   description          varchar(100) comment 'Ŀ¼���ܽ���',
   open                 int(1) comment '�Ƿ�չ����ʶ��0��ʶ��չ����1��ʶչ��',
   primary key (id)
);

alter table bs_patent_authority_t comment 'ҳ��Ȩ�ޱ�';



/*==============================================================*/
/* Table: bs_patent_cityCodeDict_t                              */
/*==============================================================*/
create table bs_patent_cityCodeDict_t
(
   cityCode             varchar(6) not null comment '���롾��λһ�塿',
   pid                  varchar(6) comment '��һ�����롾��λһ�塿',
   cityName             varchar(20) comment '����',
   primary key (cityCode)
);

alter table bs_patent_cityCodeDict_t comment '���б����ֵ��';

/*==============================================================*/
/* Table: bs_patent_contract_t                                  */
/*==============================================================*/
create table bs_patent_contract_t
(
   contractID           varchar(36) comment '��ͬID��һ����ͬ����һ����������',
   contractName         varchar(30) comment '��ͬ����',
   taskID               varchar(36) not null comment '����ID',
   taskName             varchar(30) comment '��������',
   userID               varchar(20) comment '�û�ID�������ˡ�',
   customerID           varchar(36) comment '�ͻ�ID',
   stat                 int(2) comment '����״̬��10���½�����11�����ͨ����12����ý����飻
                                20�������У�14��׫д�У�15��׫д���ͨ����
                                30���ݽ��С��γ����顿��31���������֪ͨ�飻32���״β��أ�33���ڶ��β��أ�34�������β��أ�36������ר�������ͨ����',
   pathJDS              varchar(100) comment '�������ŵ����·��',
   createTime           timestamp default CURRENT_TIMESTAMP comment '��ͬ����ʱ��',
   primary key (taskID)
);

alter table bs_patent_contract_t comment '��ͬ״̬��';

/*==============================================================*/
/* Table: bs_patent_customer_t                                  */
/*==============================================================*/
create table bs_patent_customer_t
(
   customerID           varchar(36) not null comment '�ͻ�ID',
   userID               varchar(20) comment '�û�ID�������ˣ����۽�ɫ��',
   customerName         varchar(100) comment '�ͻ�����',
   inventMan            varchar(30) comment '����������',
   applicationMan       varchar(30) comment '����������',
   linkMan              varchar(30) comment '��ϵ������',
   linkCellPhone        varchar(11) comment '��ϵ���ֻ���',
   linkEmail            varchar(30) comment '��ϵ�������ַ',
   customerBalance      int(11) default 0 comment '�ͻ����',
   createTime           timestamp default CURRENT_TIMESTAMP comment '�ͻ�����ʱ��',
   primary key (customerID)
);

alter table bs_patent_customer_t comment '�ͻ���Ϣ��';

/*==============================================================*/
/* Table: bs_patent_roleAuthority_t                             */
/*==============================================================*/
create table bs_patent_roleAuthority_t
(
   id                   int(3) comment 'ҳ��ID',
   roleID               varchar(2) comment '��ɫID'
);

alter table bs_patent_roleAuthority_t comment '��ɫȨ���м�i��';

/*==============================================================*/
/* Table: bs_patent_roleDict_t                                  */
/*==============================================================*/
create table bs_patent_roleDict_t
(
   roleID               varchar(2) not null comment '��ɫID',
   roleName             varchar(20) comment '��ɫ����',
   bak                  varchar(50) comment '��ע˵��',
   primary key (roleID)
);

alter table bs_patent_roleDict_t comment '��ɫ�ֵ��';

/*==============================================================*/
/* Table: bs_patent_servicePriceDict_t                          */
/*==============================================================*/
create table bs_patent_servicePriceDict_t
(
   serviceID            varchar(36) not null comment '����ID',
   cityCode             varchar(6) comment '���б��롾��λһ�塿',
   serviceCode          varchar(16) comment '�������',
   serviceName          varchar(30) comment '��������',
   serviceBak           varchar(50) comment '�������',
   serviceType          varchar(50) comment '��������',
   priceNumMin          int(10) comment '�۸�������ֵ����λ��Ԫ��',
   priceNumMax          int(11) comment '�۸�������ֵ����λ��Ԫ��',
   primary key (serviceID)
);

alter table bs_patent_servicePriceDict_t comment 'ҵ��ģ��-ר��-����۸��ֵ�����ճ������֡�';

/*==============================================================*/
/* Table: bs_patent_user_t                                      */
/*==============================================================*/
create table bs_patent_user_t
(
   userID               varchar(20) not null comment '�û�ID��������¼�˺�',
   userName             varchar(30) comment '�û�����',
   pwd                  varchar(16) default 'qimou123456' comment '��¼����',
   userRole             varchar(10) comment '�û���ɫ��0����������Ա��1��������Ա��2��ר�������ˡ�ȫְ����3��ר�������ˡ���ְ��4��������Ա��5�������ˡ�',
   userEmail            varchar(30) comment '�û������ַ',
   userCellPhone        varchar(11) comment '�û��ֻ�',
   userStat             int(1) comment '�û�״̬��1��������2��ע����',
   userDepartment       varchar(20) comment '�û�����',
   userGender           int(1) comment '�û��Ա�0����ʾŮ��1����ʾ��',
   createTime           timestamp comment '����ʱ��',
   primary key (userID)
);

alter table bs_patent_user_t comment '�û���Ϣ��';

/*==============================================================*/
/* Table: bs_patent_usertask_t                                  */
/*==============================================================*/
create table bs_patent_usertask_t
(
   taskID               varchar(36) comment '����ID',
   userID               varchar(20) comment '�û�ID',
   fenpeiTime           timestamp default CURRENT_TIMESTAMP comment '�������ʱ��'
);

alter table bs_patent_usertask_t comment '�û������м��';

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
