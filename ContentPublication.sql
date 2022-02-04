USE contentpublication;


DROP TABLE IF EXISTS role;
CREATE TABLE role
(
    role_id   int          NOT NULL,
    role_name varchar(255) NOT NULL,
    PRIMARY KEY (role_id)
);

DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    user_id           int          NOT NULL AUTO_INCREMENT,
    login_name        varchar(255) NOT NULL,
    password          varchar(255) NOT NULL,
    profile_completed TINYINT(1),
    created_date datetime DEFAULT NULL,
    last_login_date datetime DEFAULT NULL,
    profile_completed_date datetime DEFAULT NULL,
    PRIMARY KEY (user_id)
);



DROP TABLE IF EXISTS user_detail;
CREATE TABLE user_detail
(
    user_id     int NOT NULL,
    country     varchar(100)  DEFAULT NULL,
    description varchar(1000) DEFAULT NULL,
    name        varchar(250)  DEFAULT NULL,
    PRIMARY KEY (user_id),
    FOREIGN KEY (user_id) REFERENCES user (user_id)
);

DROP TABLE IF EXISTS user_role;
CREATE TABLE user_role
(
    user_id int NOT NULL,
    role_id int NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES user (user_id),
    FOREIGN KEY (role_id) REFERENCES role (role_id)
);

DROP TABLE IF EXISTS content_category;
CREATE TABLE content_category
(
    category_id   int NOT NULL,
    category_name varchar(255) DEFAULT NULL,
    PRIMARY KEY (category_id)
);


DROP TABLE IF EXISTS content;
CREATE TABLE content
(
    content_id   int NOT NULL AUTO_INCREMENT,
    detail       varchar(10000) DEFAULT NULL,
    publish_date datetime       DEFAULT NULL,
    summary      varchar(250)   DEFAULT NULL,
    title        varchar(50)    DEFAULT NULL,
    category_id  int            DEFAULT NULL,
    is_published bit(1)         DEFAULT NULL,
    edit_date datetime DEFAULT NULL,
    user_id      int            DEFAULT NULL,

    PRIMARY KEY (content_id),
    FOREIGN KEY (user_id) REFERENCES user (user_id),
    FOREIGN KEY (category_id) REFERENCES content_category (category_id)

);


DROP TABLE IF EXISTS content_commet;
CREATE TABLE content_commet
(
    content_commet_id int NOT NULL AUTO_INCREMENT,
    comment           varchar(255) DEFAULT NULL,
    content_id        int          DEFAULT NULL,
    user_id           int          DEFAULT NULL,

    PRIMARY KEY (content_commet_id),
    FOREIGN KEY (user_id) REFERENCES user (user_id),
    FOREIGN KEY (content_id) REFERENCES content (content_id)

);

DROP TABLE IF EXISTS user_subscription;
CREATE TABLE user_subscription
(
    user_id     int NOT NULL,
    category_id int NOT NULL,
    PRIMARY KEY (user_id, category_id),
    FOREIGN KEY (user_id) REFERENCES user (user_id),
    FOREIGN KEY (category_id) REFERENCES content_category (category_id)
);

INSERT INTO role VALUES (1,'READ'),(2,'WRITE');
INSERT INTO content_category VALUES (1,'ML/AL'),(2,'Big Data'),(3,'Micro-services');
