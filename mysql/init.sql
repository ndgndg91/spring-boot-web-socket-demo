DROP TABLE IF EXISTS `chat_user`;
CREATE TABLE `chat_user`
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    nickname VARCHAR(255) NOT NULL ,
    username     VARCHAR(255) NOT NULL ,
    password    VARCHAR(255) NOT NULL ,
    last_modified_at datetime DEFAULT NULL,
    created_at datetime NOT NULL DEFAULT NOW()
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;