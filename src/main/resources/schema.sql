CREATE DATABASE gittest;


CREATE TABLE user (
    user_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    uid VARCHAR(20) NOT NULL UNIQUE,
    email VARCHAR(255) NULL,
    upw VARCHAR(100) NOT NULL,
    nickname VARCHAR(10) NOT NULL,
    profile_pic VARCHAR(255) DEFAULT NULL,
    sign_up_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    profile_update DATETIME DEFAULT NULL
);

CREATE TABLE image (
    image_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    image VARCHAR(30) NULL
);

CREATE TABLE todolist (
    user_id BIGINT NOT NULL,
    list_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(30) NOT NULL,
    content VARCHAR(255) NOT NULL,
    write_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    start_date DATETIME DEFAULT NULL,
    deadline_date DATETIME DEFAULT NULL,
    is_completed TINYINT NOT NULL DEFAULT 0,
    favorite TINYINT NOT NULL DEFAULT 0,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
);


CREATE TABLE todolist_image (
    list_id BIGINT NOT NULL,
    image_id BIGINT NOT NULL AUTO_INCREMENT,
    content_image VARCHAR(50) NULL,
    PRIMARY KEY (list_id, image_id),
    FOREIGN KEY (list_id) REFERENCES todolist(list_id),
    FOREIGN KEY (image_id) REFERENCES image(image_id)
);