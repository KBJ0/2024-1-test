CREATE DATABASE gittest;

CREATE TABLE user
(
    user_id    BIGINT       NOT NULL AUTO_INCREMENT COMMENT '유저 PK',
    email      VARCHAR(50)  NOT NULL COMMENT '유저 이메일',
    password   VARCHAR(100) NOT NULL COMMENT '유저 PW',
    nickname   VARCHAR(8)   NOT NULL COMMENT '유저 닉네임',
    created_at DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '유저 회원가입 일자',
    updated_at DATETIME     ON UPDATE CURRENT_TIMESTAMP COMMENT '유저 업데이트 일자',
    PRIMARY KEY (user_id)
) COMMENT '유저 테이블';

CREATE TABLE pet
(
    pet_id         BIGINT       NOT NULL AUTO_INCREMENT COMMENT '반려동물 PK',
    user_id        BIGINT       NOT NULL COMMENT '유저 PK',
    pet_name       VARCHAR(15)  NOT NULL COMMENT '반려동물 이름',
    pet_category   VARCHAR(10)  COMMENT '반려동물 카테고리',
    pet_image      VARCHAR(100) COMMENT '반려동물 이미지',
    pet_icon       TINYINT      NOT NULL COMMENT '반려동물 아이콘',
    pet_back_color TINYINT      NOT NULL COMMENT '반려동물 배경색상',
    created_at     DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '반려동물 작성일자',
    PRIMARY KEY (pet_id),
    FOREIGN KEY (user_id) REFERENCES user(user_id)
) COMMENT '반려동물 테이블';

CREATE TABLE calendar
(
    calendar_id   BIGINT   NOT NULL AUTO_INCREMENT COMMENT '캘린더 PK',
    user_id 		 BIGINT   NOT NULL COMMENT '유저 PK',
    pet_id			 BIGINT 	 NOT NULL COMMENT '펫 PK',
    title         TEXT     NOT NULL COMMENT '캘린더 제목',
    content       TEXT     NULL     COMMENT '캘린더 내용',
    start_date    DATE     NOT NULL DEFAULT CURRENT_DATE COMMENT '캘린더 시작일자',
    start_time    TIME     NOT NULL DEFAULT CURRENT_TIME COMMENT '캘린더 시작시간',
    created_at    DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '캘린더 작성일자',
    updated_at    DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '캘린더 업데이트 일자',
    PRIMARY KEY (calendar_id),
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (pet_id) REFERENCES pet(pet_id)
) COMMENT '캘린더 테이블';

CREATE TABLE todolist
(
    list_id      BIGINT      NOT NULL AUTO_INCREMENT COMMENT '리스트 PK',
    user_id        BIGINT       NOT NULL COMMENT '유저 PK',
    content      VARCHAR(30) NOT NULL COMMENT '리스트 내용',
    is_completed TINYINT     NULL     DEFAULT 0 COMMENT '리스트 체크박스',
    is_favorite  TINYINT     NULL     COMMENT '중요한 일정 체크',
    created_at   DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '리스트 작성일자',
    updated_at   DATETIME    ON UPDATE CURRENT_TIMESTAMP COMMENT '리스트 업데이트 일자',
    PRIMARY KEY (list_id),
    FOREIGN KEY (user_id) REFERENCES user(user_id)
) COMMENT '투두리스트 테이블';

CREATE TABLE event_banner
(
    image_id   BIGINT       NOT NULL AUTO_INCREMENT COMMENT '이벤트 배너 이미지 PK',
    image_url  VARCHAR(100) COMMENT '이벤트 배너 이미지 URL',
    created_at DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '이벤트 배너 이미지 업로드 일자',
    PRIMARY KEY (image_id)
) COMMENT '이벤트배너 이미지 테이블';

CREATE TABLE main_banner
(
    image_id   BIGINT       NOT NULL AUTO_INCREMENT COMMENT '메인 배너 이미지 PK',
    image_url  VARCHAR(100) COMMENT '메인 배너 이미지 URL',
    created_at DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '메인 배너 이미지 업로드 일자',
    PRIMARY KEY (image_id)
) COMMENT '메인배너 이미지 테이블';