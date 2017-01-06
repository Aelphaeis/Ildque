CREATE TABLE dnestnotice_tracker (
    id            INTEGER  PRIMARY KEY
                           UNIQUE
                           NOT NULL,
    notice_number INTEGER  UNIQUE
                           NOT NULL,
    notice_date   DATETIME
);
