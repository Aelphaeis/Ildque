CREATE TABLE dnestnotice_subscribers (
    id                 PRIMARY KEY
                       NOT NULL
                       UNIQUE,
    subscriber INTEGER NOT NULL
                       UNIQUE
                       REFERENCES users (id) 
);
