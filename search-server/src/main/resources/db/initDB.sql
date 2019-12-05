CREATE TABLE IF NOT EXISTS cinemas
(
    id SERIAL,
    name TEXT NOT NULL,
    address TEXT NOT NULL,
    CONSTRAINT pk_cinemas PRIMARY KEY (id)
);

CREATE INDEX IF NOT EXISTS idx_cinemas_id ON cinemas(id);

CREATE TABLE IF NOT EXISTS sessions
(
    id SERIAL,
    cinema_id INTEGER NOT NULL,
    movie TEXT NOT NULL,
    start_time TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    hall TEXT NOT NULL,
    CONSTRAINT pk_sessions PRIMARY KEY (id),
    FOREIGN KEY (cinema_id) REFERENCES cinemas(id)
);

CREATE INDEX IF NOT EXISTS idx_sessions_id ON sessions(id);

CREATE TABLE IF NOT EXISTS users
(
    id SERIAL,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    phone_number TEXT NOT NULL,
    email TEXT NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE INDEX IF NOT EXISTS idx_users_id ON users (id);

CREATE TABLE IF NOT EXISTS tickets
(
    id SERIAL,
    place SMALLINT NOT NULL,
    price SMALLINT NOT NULL,
    premium BOOLEAN NOT NULL,
    owner_id INTEGER,
    session_id INTEGER,
    CONSTRAINT pk_tickets PRIMARY KEY (id),
    FOREIGN KEY (owner_id) REFERENCES users(id),
    FOREIGN KEY (session_id) REFERENCES sessions(id)
);

CREATE INDEX IF NOT EXISTS idx_tickets_id ON tickets(id);

CREATE SEQUENCE IF NOT EXISTS seq start 100;