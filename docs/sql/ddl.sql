CREATE TABLE IF NOT EXISTS `Song`
(
    `song_id`      INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `song_name`    TEXT,
    `artist`       TEXT,
    `album`        TEXT,
    `songDuration` INTEGER                           NOT NULL,
    `user_id`      INTEGER                           NOT NULL,
    FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`) ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE UNIQUE INDEX IF NOT EXISTS `index_Song_song_name_artist_album` ON `Song` (`song_name`, `artist`, `album`);

CREATE INDEX IF NOT EXISTS `index_Song_user_id` ON `Song` (`user_id`);

CREATE TABLE IF NOT EXISTS `Task`
(
    `task_id`              INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `task_name`            TEXT                              NOT NULL,
    `description`          TEXT                              NOT NULL,
    `duration`             INTEGER                           NOT NULL,
    `start`                INTEGER                           NOT NULL,
    `end`                  INTEGER                           NOT NULL,
    `break`                INTEGER                           NOT NULL,
    `spotify_playlist_key` TEXT,
    `user_id`              INTEGER                           NOT NULL,
    `task_type_id`         INTEGER                           NOT NULL,
    FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`) ON UPDATE NO ACTION ON DELETE CASCADE,
    FOREIGN KEY (`task_type_id`) REFERENCES `TaskType` (`task_type_id`) ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS `index_Task_start` ON `Task` (`start`);

CREATE INDEX IF NOT EXISTS `index_Task_end` ON `Task` (`end`);

CREATE INDEX IF NOT EXISTS `index_Task_spotify_playlist_key` ON `Task` (`spotify_playlist_key`);

CREATE INDEX IF NOT EXISTS `index_Task_user_id` ON `Task` (`user_id`);

CREATE INDEX IF NOT EXISTS `index_Task_task_type_id` ON `Task` (`task_type_id`);

CREATE TABLE IF NOT EXISTS `TaskType`
(
    `task_type_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `name`         TEXT,
    `description`  TEXT                              NOT NULL,
    `duration`     INTEGER                           NOT NULL
);

CREATE UNIQUE INDEX IF NOT EXISTS `index_TaskType_name` ON `TaskType` (`name`);

CREATE TABLE IF NOT EXISTS `User`
(
    `user_id`      INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `oauth_key`    TEXT                              NOT NULL,
    `account_name` TEXT                              NOT NULL
);

CREATE UNIQUE INDEX IF NOT EXISTS `index_User_oauth_key` ON `User` (`oauth_key`);

CREATE INDEX IF NOT EXISTS `index_User_account_name` ON `User` (`account_name`);
