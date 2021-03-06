INSERT INTO sessions VALUES(1, 'Alpha', 'The Silence of the Lambs', '2019-11-08 16:30:00', 'Red') ON CONFLICT DO NOTHING;
INSERT INTO sessions VALUES(2, 'Alpha', 'Django Unchained', '2019-11-08 18:30:00', 'Blue') ON CONFLICT DO NOTHING;
INSERT INTO sessions VALUES(3, 'Alpha', 'Dunkirk', '2019-11-08 10:00:00', 'Orange') ON CONFLICT DO NOTHING;
INSERT INTO sessions VALUES(4, 'Omega', 'Shape of Water', '2019-11-08 16:30:00', 'Red') ON CONFLICT DO NOTHING;
INSERT INTO sessions VALUES(5, 'Omega', 'Saw', '2019-11-08 18:30:00', 'Blue') ON CONFLICT DO NOTHING;
INSERT INTO sessions VALUES(6, 'Omega', 'Se7en', '2019-11-08 10:00:00', 'Green') ON CONFLICT DO NOTHING;

INSERT INTO users VALUES(1, 'Olha', 'Orel', '+380380380380', 'olha@orel.com') ON CONFLICT DO NOTHING;
INSERT INTO users VALUES(2, 'Mykyta', 'Yukhta', '+381381381381', 'mykyta@yukhta.com') ON CONFLICT DO NOTHING;
INSERT INTO users VALUES(3, 'Arthur', 'Humenchuk', '+382382382382', 'mykyta@yukhta.com') ON CONFLICT DO NOTHING;

-- -- Silence of Lambs tickets
-- INSERT INTO tickets VALUES(1, 11, 100, NULL, 1) ON CONFLICT DO NOTHING;
-- INSERT INTO tickets VALUES(2, 21, 200, NULL, 1) ON CONFLICT DO NOTHING;
-- INSERT INTO tickets VALUES(3, 31, 200, NULL, 1) ON CONFLICT DO NOTHING;
-- INSERT INTO tickets VALUES(4, 41, 250, NULL, 1) ON CONFLICT DO NOTHING;
-- INSERT INTO tickets VALUES(5, 51, 100, NULL, 1) ON CONFLICT DO NOTHING;
--
-- -- Django tickets
-- INSERT INTO tickets VALUES(6, 11, 100, NULL, 2) ON CONFLICT DO NOTHING;
-- INSERT INTO tickets VALUES(7, 21, 150, NULL, 2) ON CONFLICT DO NOTHING;
--
-- -- Dunkirk tickets (apparently VIP hall)
-- INSERT INTO tickets VALUES(8, 11, 1000, NULL, 3) ON CONFLICT DO NOTHING;
--
-- -- Shape of Water tickets
-- INSERT INTO tickets VALUES(9, 11, 200, NULL, 4) ON CONFLICT DO NOTHING;
-- INSERT INTO tickets VALUES(10, 21, 100, NULL, 4) ON CONFLICT DO NOTHING;
-- INSERT INTO tickets VALUES(11, 31, 150, NULL, 4) ON CONFLICT DO NOTHING;
--
-- -- Saw tickets
-- INSERT INTO tickets VALUES(12, 11, 200, NULL, 5) ON CONFLICT DO NOTHING;
-- INSERT INTO tickets VALUES(13, 21, 100, NULL, 5) ON CONFLICT DO NOTHING;
-- INSERT INTO tickets VALUES(14, 31, 200, NULL, 5) ON CONFLICT DO NOTHING;
--
-- -- Se7en tickets
-- INSERT INTO tickets VALUES(15, 11, 50, NULL, 6) ON CONFLICT DO NOTHING;
-- INSERT INTO tickets VALUES(16, 21, 80, NULL, 6) ON CONFLICT DO NOTHING;
-- INSERT INTO tickets VALUES(17, 31, 100, NULL, 6) ON CONFLICT DO NOTHING;