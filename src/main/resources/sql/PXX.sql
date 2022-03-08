DROP DATABASE if exists PXX;

CREATE DATABASE PXX character set utf8 COLLATE utf8_general_ci;

use PXX;

CREATE TABLE `user`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `userCode`     varchar(15) COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户编码',
    `userName`     varchar(15) COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名称',
    `userPassword` varchar(15) COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户密码',
    `gender`       int(10)                             DEFAULT NULL COMMENT '性别（1:女、 2:男）',
    `birthday`     date                                DEFAULT NULL COMMENT '出生日期',
    `phone`        varchar(15) COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机',
    `address`      varchar(30) COLLATE utf8_general_ci DEFAULT NULL COMMENT '地址',
    `userRole`     bigint(20)                          DEFAULT NULL COMMENT '用户角色（取自角色表-角色id）',
    `createdBy`    bigint(20)                          DEFAULT NULL COMMENT '创建者（userId）',
    `creationDate` datetime                            DEFAULT NULL COMMENT '创建时间',
    `modifyBy`     bigint(20)                          DEFAULT NULL COMMENT '更新者（userId）',
    `modifyDate`   datetime                            DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_general_ci;

CREATE TABLE `user_role`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `roleCode`     bigint(20)                          DEFAULT NULL COMMENT '角色编码',
    `roleName`     varchar(15) COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色名称',
    `createdBy`    bigint(20)                          DEFAULT NULL COMMENT '创建者',
    `creationDate` datetime                            DEFAULT NULL COMMENT '创建时间',
    `modifyBy`     bigint(20)                          DEFAULT NULL COMMENT '修改者',
    `modifyDate`   datetime                            DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_general_ci;

CREATE TABLE `good`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `goodName`     varchar(20) COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品名',
    `goodCode`     varchar(15) COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品编号',
    `goodType`     bigint(20)                          DEFAULT NULL COMMENT '商品类别',
    `inventory`    bigint(20)                          DEFAULT NULL COMMENT '商品库存',
    `owner`        bigint(20)                          DEFAULT NULL COMMENT '商品拥有者(取自用户表)',
    `createdBy`    bigint(20)                          DEFAULT NULL COMMENT '创建者',
    `creationDate` datetime                            DEFAULT NULL COMMENT '创建时间',
    `modifyBy`     bigint(20)                          DEFAULT NULL COMMENT '修改者',
    `modifyDate`   datetime                            DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_general_ci;

CREATE TABLE `good_type`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `typeCode`     bigint(20)                          DEFAULT NULL COMMENT '商品类别编号',
    `typeName`     varchar(20) COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品类别名称',
    `createdBy`    bigint(20)                          DEFAULT NULL COMMENT '创建者',
    `creationDate` datetime                            DEFAULT NULL COMMENT '创建时间',
    `modifyBy`     bigint(20)                          DEFAULT NULL COMMENT '修改者',
    `modifyDate`   datetime                            DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_general_ci;

CREATE TABLE `bill`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `billCode`      varchar(20) COLLATE utf8_general_ci DEFAULT NULL COMMENT '订单号',
    `goodCode`      varchar(15) COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品编号',
    `quantity`      bigint(20)                          DEFAULT NULL COMMENT '购买数量',
    `goodPrice`     double                              DEFAULT NULL COMMENT '商品单价',
    `totalPrice`    double                              DEFAULT NULL COMMENT '总价',
    `customerCode`  varchar(20) COLLATE utf8_general_ci DEFAULT NULL COMMENT '顾客编号',
    `address`       varchar(20) COLLATE utf8_general_ci DEFAULT NULL COMMENT '配送地址',
    `billTime`      datetime                            DEFAULT NULL COMMENT '下单时间',
    `paymentMethod` bigint(10)                          DEFAULT NULL COMMENT '支付方式',
    `deliveryTime`  datetime                            DEFAULT NULL COMMENT '配送时间',
    `createdBy`     bigint(20)                          DEFAULT NULL COMMENT '创建者',
    `creationDate`  datetime                            DEFAULT NULL COMMENT '创建时间',
    `modifyBy`      bigint(20)                          DEFAULT NULL COMMENT '修改者',
    `modifyDate`    datetime                            DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_general_ci;

CREATE TABLE `payment_method`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `typeCode`     bigint(20)                          DEFAULT NULL COMMENT '支付方式编号',
    `typeName`     varchar(20) COLLATE utf8_general_ci DEFAULT NULL COMMENT '支付方式名称',
    `createdBy`    bigint(20)                          DEFAULT NULL COMMENT '创建者',
    `creationDate` datetime                            DEFAULT NULL COMMENT '创建时间',
    `modifyBy`     bigint(20)                          DEFAULT NULL COMMENT '修改者',
    `modifyDate`   datetime                            DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_general_ci;

insert into user_role
values (0, '0', 'admin', null, null, null, null);
INSERT INTO pxx.user_role (id, roleCode, roleName, createdBy, creationDate, modifyBy, modifyDate)
VALUES (2, '1', 'user', null, null, null, null);
INSERT INTO pxx.user_role (id, roleCode, roleName, createdBy, creationDate, modifyBy, modifyDate)
VALUES (3, '2', 'business', null, null, null, null);
insert into user
values (0, '000000', 'admin', '123', null, '2001-02-09 09:22:57', null, null, 1, null, '2022-03-04 09:22:57', null,
        '2022-03-04 09:22:57');
INSERT INTO pxx.user (id, userCode, userName, userPassword, gender, birthday, phone, address, userRole, createdBy,
                      creationDate, modifyBy, modifyDate)
VALUES (2, '100001', 'seller', '123', null, '2001-08-12 09:22:57', null, null, 2, null, '2022-03-04 09:22:57', null,
        '2022-03-04 09:22:57');
INSERT INTO pxx.user (id, userCode, userName, userPassword, gender, birthday, phone, address, userRole, createdBy,
                      creationDate, modifyBy, modifyDate)
VALUES (3, '100002', 'customer', '123', null, '1919-08-10 09:22:57', null, null, 3, null, '2022-03-04 09:22:57', null,
        '2022-03-04 09:22:57');
INSERT INTO pxx.payment_method (id, typeCode, typeName, createdBy, creationDate, modifyBy, modifyDate)
VALUES (1, '001', '微信', null, '2022-03-04 09:22:57', null, '2022-03-04 09:22:57');
INSERT INTO pxx.payment_method (id, typeCode, typeName, createdBy, creationDate, modifyBy, modifyDate)
VALUES (2, '002', '支付宝', null, '2022-03-04 09:22:57', null, '2022-03-04 09:22:57');
INSERT INTO pxx.payment_method (id, typeCode, typeName, createdBy, creationDate, modifyBy, modifyDate)
VALUES (3, '003', '信用卡', null, '2022-03-04 09:22:57', null, '2022-03-04 09:22:57');
INSERT INTO pxx.bill (id, billCode, goodCode, quantity, goodPrice, totalPrice, customerCode, address,
                      billTime, paymentMethod, deliveryTime, createdBy, creationDate, modifyBy, modifyDate)
VALUES (1, '12313', '1', 11, 10, 110, '3', null, '2022-03-04 09:22:57', 1, '2022-03-04 09:22:57',
        null, '2022-03-04 09:22:57', null, '2022-03-04 09:22:57');
INSERT INTO pxx.bill (id, billCode, goodCode, quantity, goodPrice, totalPrice, customerCode, address,
                      billTime, paymentMethod, deliveryTime, createdBy, creationDate, modifyBy, modifyDate)
VALUES (2, '12', '2', 2, 5, 10, '3', null, '2022-03-04 09:22:57', 1, '2022-03-04 09:22:57',
        null, '2022-03-04 09:22:57', null, '2022-03-04 09:22:57');
INSERT INTO pxx.good_type (id, typeCode, typeName, createdBy, creationDate, modifyBy, modifyDate)
VALUES (1, 1, '饮料', null, null, null, null);
INSERT INTO pxx.good_type (id, typeCode, typeName, createdBy, creationDate, modifyBy, modifyDate)
VALUES (2, 2, '食物', null, null, null, null);
INSERT INTO pxx.good (id, goodName, goodCode, goodType, inventory, owner, createdBy, creationDate, modifyBy, modifyDate)
VALUES (1, '脉动', '1', 1, 50, 2, null, null, null, null);
INSERT INTO pxx.good (id, goodName, goodCode, goodType, inventory, owner, createdBy, creationDate, modifyBy, modifyDate)
VALUES (2, '辣条', '2', 2, 100, 2, null, null, null, null);
