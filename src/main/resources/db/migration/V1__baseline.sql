# Simply describes the list. `list_id` is intended to hold a combination of 'publication' and 'list name', i.e. `fd/home.fd4.opening`
CREATE TABLE `article_list_meta`
(
    `id`          INT UNSIGNED                 NOT NULL AUTO_INCREMENT, # numerical ID for smaller index
    `publication` CHAR(4) CHARACTER SET latin1 NOT NULL,                # 4-byte text is easier to read than 4-byte int representing publication
    `name`        VARCHAR(255)                 NOT NULL,
    `version`     INT UNSIGNED                 NOT NULL DEFAULT 0,
    `max`         TINYINT UNSIGNED                      DEFAULT NULL,   # Max can be no more than 255
    PRIMARY KEY (`id`),
    UNIQUE INDEX IX_list_publication_name (`publication`, `name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

# Ordered list of articles belonging to a `list_id`
CREATE TABLE `article_list_entry`
(
    `list_id`    INT UNSIGNED     NOT NULL,
    `seq`        TINYINT UNSIGNED NOT NULL, # Effectively limits the list size to 255 entries
    `article_id` INT              NOT NULL,
    PRIMARY KEY (`list_id`, `seq`),
    CONSTRAINT `FK_article_list_entry_article_list_meta` FOREIGN KEY (`list_id`) REFERENCES `article_list_meta` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
