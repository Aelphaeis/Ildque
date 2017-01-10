CREATE TABLE users (
    id         INTEGER   PRIMARY KEY ON CONFLICT ROLLBACK AUTOINCREMENT
                         UNIQUE
                         NOT NULL,
    discord_id TEXT (40) UNIQUE
);