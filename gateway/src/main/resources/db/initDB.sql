CREATE TABLE IF NOT EXISTS users
(
    id SERIAL,
    username TEXT NOT NULL,
    password TEXT NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);