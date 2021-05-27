drop database if exists managementmotel;
create database managementmotel;
use managementmotel;
create table role(
    roleId int auto_increment primary key,
    roleName varchar(20) not null,
    createAt timestamp default current_timestamp,
    modifiedAt timestamp default current_timestamp
     on update current_timestamp
);
create table users(
    userId int auto_increment primary key,
    fullName nvarchar(50),
    email varchar(50) unique not null,
    SDT varchar(20),
    roleId int not null,
    createAt timestamp default current_timestamp,
    modifiedAt timestamp default current_timestamp
      on update current_timestamp,
    constraint fk_users_role foreign key(roleId) references role(roleId)
      on delete cascade on update cascade
);

create table account(
    accountId int not null,
    username varchar(50) not null,
    password varchar(50) not null,
    createAt timestamp default current_timestamp,
    modifiedAt timestamp default current_timestamp
        on update current_timestamp,
    constraint pk_account_users primary key(accountId),
    constraint fk_account_users foreign key(accountId) references users(userId)
);

create table district(
    districtId int auto_increment primary key,
    districtName nvarchar(50) not null,
    createAt timestamp default current_timestamp,
    modifiedAt timestamp default current_timestamp
     on update current_timestamp
);

create table village(
    villageId int auto_increment primary key,
    villageName nvarchar(50) not null,
    districtId int not null,
    createAt timestamp default current_timestamp,
    modifiedAt timestamp default current_timestamp
        on update current_timestamp,
    constraint fk_district_village foreign key(districtId) references district(districtId)
        on delete cascade on update cascade
);

create table post(
    postId int auto_increment primary key,
    title nvarchar(100) not null,
    description text not null,
    linkImages varchar(255) not null,
    price decimal(10,3) not null,
    square int not null,
    address nvarchar(100) not null,
    villageId int not null,
    userId int not null,
    postSlug varchar(150) not null unique,
    statusPost bit not null,
    statusRental bit not null,
    publishedAt timestamp default current_timestamp,
    createAt timestamp default current_timestamp,
    modifiedAt timestamp default current_timestamp
        on update current_timestamp,
    constraint fk_users_post foreign key(userId) references users(userId)
        on delete cascade on update cascade,
    constraint fk_village_post foreign key(villageId) references village(villageId)
     on delete cascade on update cascade
);

create table comment(
    commentId int auto_increment primary key,
    content nvarchar(255) not null,
    postId int not null,
    userId int not null,
    createAt timestamp default current_timestamp,
    modifiedAt timestamp default current_timestamp
        on update current_timestamp,
    constraint fk_users_comment foreign key(userId) references users(userId)
        on delete cascade on update cascade,
    constraint fk_post_comment foreign key(postId) references post(postId)
        on delete cascade on update cascade
);

create table message(
    messageId int auto_increment primary key,
    body nvarchar(255) not null,
    fromUserId int not null,
    toUserId int not null,
    isSeen bit not null,
    createAt timestamp default current_timestamp,
    modifiedAt timestamp default current_timestamp
        on update current_timestamp,
    constraint fk_fromUsers_message foreign key(fromUserId) references users(userId)
        on delete cascade on update cascade,
    constraint fk_toUsers_message foreign key(toUserId) references users(userId)
        on delete cascade on update cascade
)

DROP TRIGGER IF EXISTS after_statusPost_update;
DELIMITER $$
CREATE TRIGGER `statusPost_update`
    BEFORE UPDATE ON `post`
    FOR EACH ROW
BEGIN
    IF OLD.statusPost <> new.statusPost THEN
    SET new.publishedAt = NOW();
END IF;
END$$
DELIMITER ;