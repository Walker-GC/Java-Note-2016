/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/11/27 0:23:56                           */
/*==============================================================*/


drop table if exists hr_answer;

drop table if exists hr_campus;

drop table if exists hr_candidate;

drop table if exists hr_city;

drop table if exists hr_position;

drop table if exists hr_region;

drop table if exists hr_role;

drop table if exists hr_source;

drop table if exists hr_state;

drop table if exists hr_user;

/*==============================================================*/
/* Table: hr_answer                                             */
/*==============================================================*/
create table hr_answer
(
   answer_id            int not null auto_increment,
   candidate_id         int,
   question_id          int,
   answer               char(1),
   pass                 char(1),
   primary key (answer_id)
);

alter table hr_answer comment '应聘人添写的答案';

/*==============================================================*/
/* Table: hr_campus                                             */
/*==============================================================*/
create table hr_campus
(
   campus_id            int not null,
   campus_name          varchar(100),
   location             varchar(200),
   city_id              int,
   primary key (campus_id)
);

alter table hr_campus comment '校区';

/*==============================================================*/
/* Table: hr_candidate                                          */
/*==============================================================*/
create table hr_candidate
(
   candidate_id         int not null,
   name                 varchar(100),
   gender               varbinary(100),
   day_of_birth         date,
   position_id          int,
   mobile               varchar(50),
   phone                varchar(50),
   email                varchar(50),
   education            varchar(50),
   collage              varchar(100),
   school               varchar(100),
   major                varchar(100),
   graduation           date,
   qq                   varchar(50),
   source_id            int,
   state_id             int,
   user_id              int,
   city_id              int,
   result_1             int,
   result_2             int,
   result_3             int,
   result_4             int,
   primary key (candidate_id)
);

/*==============================================================*/
/* Table: hr_city                                               */
/*==============================================================*/
create table hr_city
(
   city_id              int not null,
   city_name            varchar(50),
   region_id            int,
   primary key (city_id)
);

alter table hr_city comment '城市';

/*==============================================================*/
/* Table: hr_position                                           */
/*==============================================================*/
create table hr_position
(
   position_id          int not null auto_increment,
   name                 varchar(100),
   description          varchar(200),
   primary key (position_id)
);

alter table hr_position comment '最高学历';

/*==============================================================*/
/* Table: hr_region                                             */
/*==============================================================*/
create table hr_region
(
   region_id            int not null,
   region_name          varchar(50),
   description          varchar(200),
   primary key (region_id)
);

alter table hr_region comment '销售片区';

/*==============================================================*/
/* Table: hr_role                                               */
/*==============================================================*/
create table hr_role
(
   role_id              int not null,
   role_name            varchar(100),
   primary key (role_id)
);

/*==============================================================*/
/* Table: hr_source                                             */
/*==============================================================*/
create table hr_source
(
   source_id            int not null,
   name                 varchar(100),
   primary key (source_id)
);

alter table hr_source comment '招聘信息来源';

/*==============================================================*/
/* Table: hr_state                                              */
/*==============================================================*/
create table hr_state
(
   state_id             int not null,
   state_name           varchar(100),
   primary key (state_id)
);

alter table hr_state comment '目前在职状态';

/*==============================================================*/
/* Table: hr_user                                               */
/*==============================================================*/
create table hr_user
(
   user_id              int not null,
   user_name            varchar(100),
   user_password        varchar(100),
   user_sure_name       varchar(100),
   user_mobile          varchar(50),
   user_email           varchar(50),
   role_id              int,
   campus_id            int,
   primary key (user_id)
);

alter table hr_user comment '系统用户';

alter table hr_answer add constraint FK_Reference_10 foreign key (candidate_id)
      references hr_candidate (candidate_id) on delete restrict on update restrict;

alter table hr_campus add constraint FK_Reference_9 foreign key (city_id)
      references hr_city (city_id) on delete restrict on update restrict;

alter table hr_candidate add constraint FK_Reference_1 foreign key (position_id)
      references hr_position (position_id) on delete restrict on update restrict;

alter table hr_candidate add constraint FK_Reference_2 foreign key (source_id)
      references hr_source (source_id) on delete restrict on update restrict;

alter table hr_candidate add constraint FK_Reference_4 foreign key (state_id)
      references hr_state (state_id) on delete restrict on update restrict;

alter table hr_candidate add constraint FK_Reference_6 foreign key (user_id)
      references hr_user (user_id) on delete restrict on update restrict;

alter table hr_candidate add constraint FK_Reference_8 foreign key (city_id)
      references hr_city (city_id) on delete restrict on update restrict;

alter table hr_city add constraint FK_Reference_12 foreign key (region_id)
      references hr_region (region_id) on delete restrict on update restrict;

alter table hr_user add constraint FK_Reference_11 foreign key (campus_id)
      references hr_campus (campus_id) on delete restrict on update restrict;

alter table hr_user add constraint FK_Reference_5 foreign key (role_id)
      references hr_role (role_id) on delete restrict on update restrict;

