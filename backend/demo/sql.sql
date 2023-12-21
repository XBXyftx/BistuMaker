-- 创建数据库
create database club;

-- 使用数据库
use club;

-- 用户表
create table user (
                      id int unsigned primary key auto_increment comment 'ID',
                      username varchar(20) not null unique comment '用户名',
                      password varchar(32)  comment '密码',
                      nickname varchar(10)  default '' comment '昵称',
                      email varchar(128) default '' comment '邮箱',
                      user_pic varchar(128) default '' comment '头像',
                      create_time datetime not null comment '创建时间',
                      update_time datetime not null comment '修改时间',
                      roles varchar(20) default 'user' comment '角色'
) comment '用户表';

-- 分类表
create table category(
                         id int unsigned primary key auto_increment comment 'ID',
                         category_name varchar(32) not null comment '分类名称',
                         category_alias varchar(32) not null comment '分类别名',
                         create_user int unsigned not null comment '创建人ID',
                         create_time datetime not null comment '创建时间',
                         update_time datetime not null comment '修改时间',
                         constraint fk_category_user foreign key (create_user) references user(id) -- 外键约束
);

-- 文章表
create table article(
                        id int unsigned primary key auto_increment comment 'ID',
                        title varchar(30) not null comment '文章标题',
                        content text not null comment '文章内容',
                        cover_img varchar(128)   comment '文章封面',
                        state varchar(3) default '草稿' comment '文章状态: 只能是[已发布] 或者 [草稿]',
                        category_id int unsigned comment '文章分类ID',
                        create_user int unsigned not null comment '创建人ID',
                        create_time datetime not null comment '创建时间',
                        update_time datetime not null comment '修改时间',
                        constraint fk_article_category foreign key (category_id) references category(id),-- 外键约束
                        constraint fk_article_user foreign key (create_user) references user(id) -- 外键约束
);

-- 评论表
create table comment
(
    id          int unsigned primary key auto_increment comment 'ID',
    content     text         not null comment '评论内容',
    article_id  int unsigned not null comment '文章ID',
    create_user int unsigned not null comment '创建人ID',
    create_time datetime     not null comment '创建时间',
    update_time datetime     not null comment '修改时间',
    constraint fk_comment_article foreign key (article_id) references article (id)-- 外键约束
);

CREATE TABLE Activities (
                            ActivityID INT PRIMARY KEY,
                            ActivityName VARCHAR(255),
                            ActivityDate DATE,
                            Location VARCHAR(255),
                            Description TEXT
);










-- 创建数据库
create database club;

-- 使用数据库
use club;

-- 创建用户表
CREATE TABLE users (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       username VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       modify_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                       role VARCHAR(50)
);

-- 创建文章表
CREATE TABLE articles (
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          title VARCHAR(255) NOT NULL,
                          content TEXT,
                          cover_image VARCHAR(3000),
                          creator_id INT,
                          create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          modify_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          FOREIGN KEY (creator_id) REFERENCES users(id)
);

-- 创建评价表
CREATE TABLE comments (
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          content TEXT,
                          article_id INT,
                          creator_id INT,
                          create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          FOREIGN KEY (article_id) REFERENCES articles(id),
                          FOREIGN KEY (creator_id) REFERENCES users(id)
);

-- 创建通知表
CREATE TABLE notifications (
                               id INT PRIMARY KEY AUTO_INCREMENT,
                               content TEXT,
                               create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 创建报名表
CREATE TABLE enrollments (
                             id INT PRIMARY KEY AUTO_INCREMENT,
                             name VARCHAR(255) NOT NULL,
                             student_id VARCHAR(20) NOT NULL,
                                 signup_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             signup_reason TEXT,
                             department VARCHAR(50),
                             email VARCHAR(50) NOT NULL,
                             hobbies TEXT
);





