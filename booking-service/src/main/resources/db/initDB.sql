CREATE TABLE IF NOT EXISTS orders
(
    id SERIAL,
    place SMALLINT NOT NULL,
    cinema TEXT not null,
    firstname TEXT not null,
    lastname TEXT not null,
    phone TEXT not null,
    email TEXT not null,
    CONSTRAINT pk_tickets PRIMARY KEY (id)
);