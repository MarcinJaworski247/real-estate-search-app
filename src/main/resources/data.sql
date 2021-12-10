INSERT INTO localizations (city, voivodeship) VALUES ('Gdańsk', 'POMORSKIE') ON CONFLICT DO NOTHING
INSERT INTO localizations (city, voivodeship) VALUES ('Szczecin', 'ZACHODNIO_POMORSKIE') ON CONFLICT DO NOTHING
INSERT INTO localizations (city, voivodeship) VALUES ('Olsztyn', 'WARMINSKO_MAZURSKIE') ON CONFLICT DO NOTHING
INSERT INTO localizations (city, voivodeship) VALUES ('Białystok', 'PODLASKIE') ON CONFLICT DO NOTHING
INSERT INTO localizations (city, voivodeship) VALUES ('Toruń', 'KUJAWSKO_POMORSKIE') ON CONFLICT DO NOTHING
INSERT INTO localizations (city, voivodeship) VALUES ('Poznań', 'WIELKOPOLSKIE') ON CONFLICT DO NOTHING
INSERT INTO localizations (city, voivodeship) VALUES ('Zielona Góra', 'LUBUSKIE') ON CONFLICT DO NOTHING
INSERT INTO localizations (city, voivodeship) VALUES ('Wrocław', 'DOLNOSLASKIE') ON CONFLICT DO NOTHING
INSERT INTO localizations (city, voivodeship) VALUES ('Łódź', 'LODZKIE') ON CONFLICT DO NOTHING
INSERT INTO localizations (city, voivodeship) VALUES ('Kielce', 'SWIETOKRZYSKIE') ON CONFLICT DO NOTHING
INSERT INTO localizations (city, voivodeship) VALUES ('Lublin', 'LUBELSKIE') ON CONFLICT DO NOTHING
INSERT INTO localizations (city, voivodeship) VALUES ('Opole', 'OPOLSKIE') ON CONFLICT DO NOTHING
INSERT INTO localizations (city, voivodeship) VALUES ('Katowice', 'SLASKIE') ON CONFLICT DO NOTHING
INSERT INTO localizations (city, voivodeship) VALUES ('Kraków', 'MALOPOLSKIE') ON CONFLICT DO NOTHING
INSERT INTO localizations (city, voivodeship) VALUES ('Rzeszów', 'PODKARPACKIE') ON CONFLICT DO NOTHING

INSERT INTO users (id, username, password, phone_number, role_name) VALUES ('84e3ff3d-f6e9-434b-83c3-73e70ee86621', 'admin', '$2a$10$IgIGCmzMW5flnhzQ4PkD1e6yyxNNsf9Gis8dYK6kzFUuiJX1Yisw2', 000000000, 'ADMIN') ON CONFLICT DO NOTHING