-- Delete db
DROP DATABASE IF EXISTS planetary_real_estate_db;

-- Create db
CREATE DATABASE planetary_real_estate_db;

USE planetary_real_estate_db;


-- Create User table
CREATE TABLE USERS
(
    user_id   INT AUTO_INCREMENT PRIMARY KEY,
    name      VARCHAR(50)  NOT NULL,
    last_name VARCHAR(50)  NOT NULL,
    username  VARCHAR(20)  NOT NULL UNIQUE,
    password  VARCHAR(255) NOT NULL,

    -- Adding index for username
    INDEX idx_username (username)
);


-- Create CelestialType table
CREATE TABLE CELESTIAL_TYPES
(
    celestial_type_id INT AUTO_INCREMENT PRIMARY KEY,
    name              VARCHAR(50) NOT NULL UNIQUE,
    description       TEXT
);


-- Create Celestial Body
CREATE TABLE CELESTIAL_BODIES
(
    -- cols
    celestial_body_id        INT AUTO_INCREMENT PRIMARY KEY,
    name                     VARCHAR(50) NOT NULL UNIQUE,
    description              TEXT,
    surface_pressure         DECIMAL(10, 2),
    surface_temperature_min  DECIMAL(10, 2),
    surface_temperature_max  DECIMAL(10, 2),
    core_temperature         DECIMAL(10, 2),
    explored                 BOOLEAN,
    radiation_level          ENUM ('LOW (0-10 mSv/year)', 'MEDIUM (10-100 mSv/year)', 'HIGH (>100 mSv/year)'),
    has_water                BOOLEAN,
    surface_area             DECIMAL(20, 2),
    mass                     DECIMAL(20, 2),
    gravitation_field_height DECIMAL(20, 2),
    moving_speed             DECIMAL(20, 2),
    rotation_speed           DECIMAL(20, 2),

    -- fks
    type_id                  INT         NOT NULL,
    rotates_around_id        INT,

    FOREIGN KEY (type_id) REFERENCES CELESTIAL_TYPES (celestial_type_id),
    FOREIGN KEY (rotates_around_id) REFERENCES CELESTIAL_BODIES (celestial_body_id)
);


-- Create Celestial Pathways connecting the reachable cosmos-points (celestial bodies)
CREATE TABLE CELESTIAL_PATHS
(
    path_id     INT AUTO_INCREMENT,
    body_a_id   INT    NOT NULL,
    body_b_id   INT    NOT NULL,
    distance_km DOUBLE NOT NULL,
    description VARCHAR(1024),

    PRIMARY KEY (path_id, body_a_id, body_b_id),

    FOREIGN KEY (body_a_id) REFERENCES CELESTIAL_BODIES (celestial_body_id),
    FOREIGN KEY (body_b_id) REFERENCES CELESTIAL_BODIES (celestial_body_id)
);


-- Create ELEMENTS table
CREATE TABLE ELEMENTS
(
    element_id     INT AUTO_INCREMENT PRIMARY KEY,
    name           VARCHAR(50) NOT NULL UNIQUE,
    description    TEXT,
    min_percentage DECIMAL(5, 2), -- Minimum percentage for human habitability
    max_percentage DECIMAL(5, 2), -- Maximum percentage for human habitability
    radioactive    BOOLEAN,       -- Is element radioactive
    inert          BOOLEAN
);


CREATE TABLE ATMOSPHERES
(
    atmosphere_id     INT AUTO_INCREMENT PRIMARY KEY,
    celestial_body_id INT UNIQUE,
    max_temperature   DECIMAL(10, 2),
    min_temperature   DECIMAL(10, 2),
    atmosphere_height INT, -- Height of the atmosphere in kilometers
    ampere_pressure   DECIMAL(10, 2),

    FOREIGN KEY (celestial_body_id) REFERENCES CELESTIAL_BODIES (celestial_body_id),

    -- Adding an index for celestial_body_id
    INDEX idx_celestial_body_id (celestial_body_id)
);


-- Create the linking table ATMOSPHERES_ELEMENTS
CREATE TABLE ATMOSPHERES_ELEMENTS
(
    atmosphere_id INT,
    element_id    INT,
    percentage    DECIMAL(5, 2),

    PRIMARY KEY (atmosphere_id, element_id),
    FOREIGN KEY (atmosphere_id) REFERENCES ATMOSPHERES (atmosphere_id),
    FOREIGN KEY (element_id) REFERENCES ELEMENTS (element_id)
);


CREATE TABLE RESIDENTS
(
    resident_id INT AUTO_INCREMENT PRIMARY KEY,
    full_name   VARCHAR(256),
    gender      CHAR(1) NOT NULL CHECK (gender IN ('M', 'F')),
    birth_date  DATE    NOT NULL,
    death_date  DATE
);


CREATE TABLE CELESTIAL_BODY_RESIDENTS
(
    id                INT AUTO_INCREMENT,
    resident_id       INT  NOT NULL,
    celestial_body_id INT  NOT NULL,
    resident_from     DATE NOT NULL,
    resident_until    DATE,
    PRIMARY KEY (id, resident_id, celestial_body_id),
    FOREIGN KEY (resident_id) REFERENCES RESIDENTS (resident_id),
    FOREIGN KEY (celestial_body_id) REFERENCES CELESTIAL_BODIES (celestial_body_id)
);



CREATE TABLE SPACESHIPS
(
    spaceship_id       INT AUTO_INCREMENT PRIMARY KEY,
    name               VARCHAR(50) NOT NULL UNIQUE,
    model              VARCHAR(50),
    passenger_capacity INT,
    fuel_capacity      DECIMAL(10, 2), -- Capacity of the fuel tank
    max_travel_range   DECIMAL(10, 2), -- Maximum travel range in light-years
    traveling_speed    DECIMAL(10, 2), -- Traveling speed in light-years per hour
    manufacturer       VARCHAR(50)
);



CREATE TABLE SPACESHIP_ROOMS
(
    room_id                 INT AUTO_INCREMENT PRIMARY KEY,
    room_number             VARCHAR(200) NOT NULL,
    hibernation_capsules_nb INT,
    max_capacity            INT,
    perks                   TEXT,
    spaceship_id            INT          NOT NULL,
    FOREIGN KEY (spaceship_id) REFERENCES SPACESHIPS (spaceship_id)
);


-- Create Mission table
CREATE TABLE MISSIONS
(
    mission_id       INT AUTO_INCREMENT PRIMARY KEY,
    name             VARCHAR(50) NOT NULL,
    description      TEXT,
    start_date       DATE,
    end_date         DATE,
    completed        BOOLEAN,
    explored_body_id INT         NOT NULL,
    spaceship_id     INT         NOT NULL,
    FOREIGN KEY (explored_body_id) REFERENCES CELESTIAL_BODIES (celestial_body_id),
    FOREIGN KEY (spaceship_id) REFERENCES SPACESHIPS (spaceship_id),

    -- Adding index for launch_date
    INDEX idx_launch_date (start_date)

);


-- Create Departure table
CREATE TABLE DEPARTURES
(
    departure_id             INT AUTO_INCREMENT PRIMARY KEY,
    departure_datetime       DATETIME NOT NULL,
    celestial_origin_id      INT      NOT NULL,
    celestial_destination_id INT      NOT NULL,
    spaceship_id             INT      NOT NULL,


    FOREIGN KEY (celestial_origin_id) REFERENCES CELESTIAL_PATHS (body_a_id),
    FOREIGN KEY (celestial_destination_id) REFERENCES CELESTIAL_PATHS (body_b_id),
    FOREIGN KEY (spaceship_id) REFERENCES SPACESHIPS (spaceship_id),

    INDEX idx_departure_date (departure_datetime)
);


CREATE TABLE TICKETS
(
    ticket_id    INT AUTO_INCREMENT,
    departure_id INT            NOT NULL,
    passenger_id INT, --
    sold         BOOLEAN,
    price        DECIMAL(10, 2) NOT NULL,
    room_id      INT            NOT NULL,
    spaceship_id INT            NOT NULL,

    PRIMARY KEY (ticket_id, departure_id, passenger_id),

    FOREIGN KEY (departure_id) REFERENCES DEPARTURES (departure_id),
    FOREIGN KEY (passenger_id) REFERENCES USERS (user_id),
    FOREIGN KEY (room_id) REFERENCES SPACESHIP_ROOMS (room_id),
    FOREIGN KEY (spaceship_id) REFERENCES SPACESHIPS (spaceship_id),

    INDEX idx_departure_id (departure_id),
    INDEX idx_spaceship_id (spaceship_id)
);


-- Create Property table (Residential Facilities)
CREATE TABLE PROPERTIES
(
    property_id       INT AUTO_INCREMENT PRIMARY KEY,
    property_reg_nb   INT            NOT NULL,
    address           VARCHAR(50)    NOT NULL,
    square_meters     DECIMAL(10, 2) NOT NULL,
    name              VARCHAR(50)    NOT NULL,
    description       TEXT,
    price             DECIMAL(10, 2) NOT NULL,
    celestial_body_id INT            NOT NULL, -- The celestial body that property is located on
    soled_to_user_id  INT,                     -- User who bought the property, if property has not been bought this field is NULL

    FOREIGN KEY (celestial_body_id) REFERENCES CELESTIAL_BODIES (celestial_body_id),
    FOREIGN KEY (soled_to_user_id) REFERENCES USERS (user_id),

    INDEX idx_soled_to_user_id (soled_to_user_id)

);

INSERT INTO CELESTIAL_TYPES (name, description)
VALUES ('Planet',
        'A large, round celestial body that orbits a star and has enough gravity to clear its orbit of most other objects.'),
       ('Satellite', 'A natural object that orbits a planet, dwarf planet, asteroid, or other celestial body.'),
       ('Star',
        'A massive, self-luminous celestial body made up primarily of hydrogen and helium in a state of nuclear fusion.'),
       ('Dwarf Planet',
        'A celestial body that is round and orbits the Sun, but is not massive enough to clear its neighborhood of other objects.'),
       ('Asteroid',
        'A relatively small, rocky celestial body orbiting the Sun, typically in the asteroid belt between Mars and Jupiter.');

-- User table
INSERT INTO USERS (name, last_name, username, password)
VALUES ('Neel ', 'Armstrong', 'neal123', 'password1'),
       ('Jane', 'Smith', 'janesmith', 'password2'),
       ('Alice', 'Williams', 'alicew', 'password3'),
       ('Bob', 'Johnson', 'bob', 'password4'),
       ('Michael', 'Brown', 'mikeb', 'password5'),
       ('Milan', 'Jermeci', 'Milanac123', 'password5');
;


-- Insert other celestial bodies excluding rotates_around_id
INSERT INTO CELESTIAL_BODIES (name, type_id, description, surface_pressure, surface_temperature_min,
                              surface_temperature_max, core_temperature,
                              explored, radiation_level, has_water, surface_area, mass,
                              gravitation_field_height, moving_speed, rotation_speed)
VALUES
-- 1
('Sun', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Star'),
 'The star at the center of our solar system', NULL, NULL, 5778, 15000000, true, 'HIGH (>100 mSv/year)', false,
 608320000000,
 1988500, NULL, NULL, NULL),
-- 2
('Mars', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Planet'),
 'The red planet, known for its canyons and potential for life', 0.636, -63, -63, 1700, true, 'LOW (0-10 mSv/year)',
 true,
 144798500, 0.64171, 210, 24.077, 0.004),
-- 3
('Earth', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Planet'),
 'Our home planet, teeming with life', 101.325, 15, 15, 6000, true, 'LOW (0-10 mSv/year)', true, 510072000, 5.97237,
 100,
 29.78, 0.4651),
-- 4
('Jupiter', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Planet'),
 'The largest planet in our solar system, a gas giant with a Great Red Spot', 1000, -108, -108, 30000, true,
 'HIGH (>100 mSv/year)', false, 61420000000, 1898.19, 1000, 13.07, 0.001),
-- 5
('Saturn', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Planet'),
 'Known for its stunning ring system, another gas giant', 140, -139, -139, 11700, true, 'HIGH (>100 mSv/year)', false,
 42700000000,
 568.34, 1000, 9.68, 0.0009),
-- 6
('Venus', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Planet'),
 'Similar in size to Earth but with a thick, toxic atmosphere and surface temperatures hot enough to melt lead',
 9200, 464, 464, 6000, false, 'HIGH (>100 mSv/year)', false, 460200000, 4.8675, 100, 35.02, -0.002),
-- 7
('Mercury', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Planet'),
 'The closest planet to the Sun, known for its extreme temperature variations', 0, 167, 167, 1800, false,
 'MEDIUM (10-100 mSv/year)',
 false, 74800000, 0.33011, 100, 47.87, 0.001),
-- 8
('Neptune', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Planet'),
 'A blue gas giant, known for its strong winds and large storms', 1000, -201, -201, 7000, false, 'HIGH (>100 mSv/year)',
 false,
 76400000000, 102.413, 200, 5.43, 0.0005),
-- 9
('Uranus', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Planet'),
 'An ice giant with a unique tilted rotation axis', 800, -224, -224, 5000, false, 'HIGH (>100 mSv/year)', false,
 80830000000,
 86.8103, 200, 6.81, -0.0007),
-- 10
('Pluto', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Dwarf Planet'),
 'Once considered the ninth planet, now classified as a dwarf planet in the Kuiper belt', 0, -229, -229, 2400, true,
 'LOW (0-10 mSv/year)', false, 16700000, 0.01303, 200, 4.74, -0.0002),
-- 11
('Ceres', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Dwarf Planet'),
 'The largest object in the asteroid belt between Mars and Jupiter', 0, -105, -105, 200, true, 'LOW (0-10 mSv/year)',
 false, 2791000,
 0.000938, 100, 17.9, 0.003),
-- 12
('Haumea', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Dwarf Planet'),
 'A rapidly spinning elongated dwarf planet in the Kuiper belt', 0, -241, -241, 1700, false, 'MEDIUM (10-100 mSv/year)',
 false, 1170000,
 0.004006, 200, 4.53, -0.0033),
-- 13
('Makemake', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Dwarf Planet'),
 'Another dwarf planet in the Kuiper belt, known for its bright surface', 0, -243, -243, 1600, false,
 'LOW (0-10 mSv/year)', false,
 1240000, 0.0032, 200, 4.41, -0.0014),
-- 14
('Eris', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Dwarf Planet'),
 'A distant dwarf planet, one of the largest known in our solar system', 0, -231, -231, 200, false,
 'LOW (0-10 mSv/year)', false,
 2700000, 0.0167, 100, 3.5, 0.009),
-- 15
('Ganymede', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Satellite'),
 'The largest moon of Jupiter and the largest in the solar system', 0, -163, -163, 1200, true, 'LOW (0-10 mSv/year)',
 true, 87000000,
 0.0148, 200, 10.88, 0.001),
-- 16
('Callisto', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Satellite'),
 'A heavily cratered moon of Jupiter', 0, -139, -139, 1200, true, 'LOW (0-10 mSv/year)', false, 73400000, 0.018, 200,
 8.2,
 0.001),
-- 17
('Io', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Satellite'),
 'A volcanically active moon of Jupiter', 0, -130, -130, 1200, true, 'LOW (0-10 mSv/year)', false, 42000000, 0.015, 200,
 17.3,
 0.001),
-- 18
('Enceladus', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Satellite'),
 'A moon of Saturn with geysers of water ice', 0, -201, -201, 1000, true, 'LOW (0-10 mSv/year)', true, 8000000, 0.0002,
 100,
 12.63, 0.001),
-- 19
('Triton', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Satellite'),
 'The largest moon of Neptune, known for its retrograde orbit', 0, -235, -235, 1000, true, 'LOW (0-10 mSv/year)', false,
 23000000,
 0.021, 200, 4.39, 0.002),
-- 20
('Charon', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Satellite'),
 'The largest moon of Pluto, half the size of its parent dwarf planet', 0, -220, -220, 1000, true,
 'LOW (0-10 mSv/year)', false,
 4600000, 0.001, 200, 4.64, 0.002),
-- 21
('Vesta', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Asteroid'),
 'One of the largest asteroids in the asteroid belt', 0, -20, -20, 1000, true, 'LOW (0-10 mSv/year)', false, 520000,
 0.0003,
 100, 19.34, 0.002),
('Pallas', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Asteroid'),
 'A large asteroid with an irregular shape', 0, -20, -20, 1000, true, 'LOW (0-10 mSv/year)', false, 610000, 0.0003, 100,
 17.65,
 0.002),
-- 22
('Hygeia', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Asteroid'),
 'The fourth largest asteroid in the asteroid belt', 0, -20, -20, 1000, true, 'LOW (0-10 mSv/year)', false, 350000,
 0.0003, 100,
 17.91, 0.002),
-- 23
('Bennu', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Asteroid'),
 'A near-Earth asteroid, the target of the OSIRIS-REx mission', 0, -20, -20, 1000, true, 'LOW (0-10 mSv/year)', false,
 78.8, 0.0000000075, 100, 28.07, 0.004),
-- 24
('Ryugu', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Asteroid'),
 'A near-Earth asteroid, explored by the Hayabusa2 mission', 0, -20, -20, 1000, true, 'LOW (0-10 mSv/year)', false,
 33.5, 0.00000000075, 100, 28.29, 0.004);


-- Update statements to set the rotates_around_id values with constant int values

-- Bodies that rotate around the Sun
UPDATE CELESTIAL_BODIES
SET rotates_around_id = 1
WHERE name IN
      ('Earth', 'Mars', 'Mercury', 'Venus', 'Jupiter', 'Saturn', 'Uranus', 'Neptune', 'Vesta', 'Pallas', 'Hygeia',
       'Bennu', 'Ryugu');

-- Jupiter's moons
UPDATE CELESTIAL_BODIES
SET rotates_around_id = 4
WHERE name IN ('Ganymede', 'Callisto', 'Io');

-- Saturn's moon
UPDATE CELESTIAL_BODIES
SET rotates_around_id = 5
WHERE name = 'Enceladus';

-- Neptune's moon
UPDATE CELESTIAL_BODIES
SET rotates_around_id = 8
WHERE name = 'Triton';

-- Pluto's moon
UPDATE CELESTIAL_BODIES
SET rotates_around_id = 10
WHERE name = 'Charon';

-- Eris's moon
UPDATE CELESTIAL_BODIES
SET rotates_around_id = 14
WHERE name = 'Dysnomia';

INSERT INTO CELESTIAL_PATHS (body_a_id, body_b_id, distance_km, description)
VALUES (1, 2, 57900000, 'Sun to Mercury: ~57.9 million km'),
       (1, 3, 108200000, 'Sun to Venus: ~108.2 million km'),
       (1, 4, 149600000, 'Sun to Earth: ~149.6 million km'),
       (1, 5, 227900000, 'Sun to Mars: ~227.9 million km'),
       (1, 6, 778500000, 'Sun to Jupiter: ~778.5 million km'),
       (1, 7, 1433500000, 'Sun to Saturn: ~1,433.5 million km'),
       (1, 8, 2872500000, 'Sun to Uranus: ~2,872.5 million km'),
       (1, 9, 4495100000, 'Sun to Neptune: ~4,495.1 million km'),
       (1, 10, 5906400000, 'Sun to Pluto: ~5,906.4 million km'),
       (4, 5, 78300000, 'Earth to Mars: ~78.3 million km'),
       (5, 6, 550600000, 'Mars to Jupiter: ~550.6 million km'),
       (6, 7, 655000000, 'Jupiter to Saturn: ~655.0 million km'),
       (7, 8, 1439000000, 'Saturn to Uranus: ~1,439.0 million km'),
       (8, 9, 1623600000, 'Uranus to Neptune: ~1,623.6 million km'),
       (9, 10, 2411300000, 'Neptune to Pluto: ~2,411.3 million km');


INSERT INTO SPACESHIPS (name, model, passenger_capacity, fuel_capacity, max_travel_range, traveling_speed, manufacturer)
VALUES ('Explorer I', 'Model X1', 100, 5000.00, 10000.00, 500.00, 'SpaceX'),
       ('Voyager II', 'Model V2', 120, 6000.00, 12000.00, 550.00, 'NASA'),
       ('Pioneer III', 'Model P3', 80, 4000.00, 8000.00, 400.00, 'Blue Origin'),
       ('Enterprise', 'Model E1', 150, 7000.00, 15000.00, 600.00, 'Virgin Galactic'),
       ('Galactic Cruiser', 'Model G1', 200, 8000.00, 16000.00, 650.00, 'Boeing'),
       ('Starship Alpha', 'Model S1', 90, 4500.00, 9000.00, 450.00, 'Space Adventures'),
       ('Lunar Lander', 'Model L1', 50, 3000.00, 6000.00, 350.00, 'Lockheed Martin'),
       ('Mars Rover', 'Model M1', 110, 5500.00, 11000.00, 520.00, 'SpaceX'),
       ('Jupiter Explorer', 'Model J1', 130, 6500.00, 13000.00, 570.00, 'NASA'),
       ('Saturn Voyager', 'Model S2', 140, 7500.00, 14000.00, 590.00, 'Blue Origin'),
       ('Neptune Navigator', 'Model N1', 60, 3500.00, 7000.00, 370.00, 'Virgin Galactic'),
       ('Astro Traveler', 'Model A1', 170, 8500.00, 17000.00, 620.00, 'Boeing'),
       ('Celestial Chariot', 'Model C1', 190, 9500.00, 18000.00, 640.00, 'Space Adventures'),
       ('Cosmic Cruiser', 'Model C2', 100, 5000.00, 10000.00, 500.00, 'Lockheed Martin'),
       ('Interstellar Explorer', 'Model I1', 200, 10000.00, 20000.00, 700.00, 'SpaceX'),
       ('Solar Voyager', 'Model S3', 150, 7500.00, 15000.00, 600.00, 'NASA'),
       ('Lunar Explorer', 'Model L2', 80, 4000.00, 8000.00, 400.00, 'Blue Origin'),
       ('Martian Rover', 'Model M2', 120, 6000.00, 12000.00, 550.00, 'Virgin Galactic'),
       ('Titan Voyager', 'Model T1', 130, 6500.00, 13000.00, 570.00, 'Boeing');


INSERT INTO DEPARTURES (departure_datetime, celestial_origin_id, celestial_destination_id, spaceship_id)
VALUES ('2024-01-01 10:00:00', 1, 2, 1),
       ('2024-01-02 10:00:00', 1, 3, 2),
       ('2024-01-03 10:00:00', 1, 4, 3),
       ('2024-01-04 10:00:00', 1, 5, 4),
       ('2024-01-05 10:00:00', 1, 6, 5),
       ('2024-01-06 10:00:00', 1, 7, 6),
       ('2024-01-07 10:00:00', 1, 8, 7),
       ('2024-01-08 10:00:00', 1, 9, 8),
       ('2024-01-09 10:00:00', 1, 10, 9),
       ('2024-01-10 10:00:00', 4, 5, 10),
       ('2024-01-11 10:00:00', 5, 6, 11),
       ('2024-01-12 10:00:00', 6, 7, 12),
       ('2024-01-13 10:00:00', 7, 8, 13),
       ('2024-01-14 10:00:00', 8, 9, 14),
       ('2024-01-15 10:00:00', 9, 10, 15);

-- Insert data into ATMOSPHERES table
INSERT INTO ATMOSPHERES (celestial_body_id, atmosphere_height)
VALUES ((SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Earth'), 480),
       ((SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Mars'), 11),
       ((SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Venus'), 250);


-- Insert data into ELEMENTS table
INSERT INTO ELEMENTS (name, description, min_percentage, max_percentage, radioactive, inert)
VALUES ('Nitrogen', 'The most abundant gas in Earth’s atmosphere, crucial for life.', 78.0, 78.0, false, false),
       ('Oxygen', 'Essential for respiration of most terrestrial life forms.', 19.5, 23.5, false, false),
       ('Carbon Dioxide', 'A greenhouse gas, significant for its role in the carbon cycle.', 0.0, 0.04, false, false),
       ('Argon', 'An colorless, odorless and inert gas present in small amounts in the Earth’s atmosphere.', 0.0, 1.0,
        false, true),
       ('Hydrogen', 'The most abundant chemical substance in the universe', 0.0, 100.0, false, false),
       ('Helium', 'A colorless, odorless, tasteless, non-toxic, inert, monatomic gas', 0.0, 100.0, false, true),
       ('Methane', 'A colorless, odorless, flammable gas', 0.0, 5.0, false, false),
       ('Neon', 'A colorless, odorless, inert monatomic gas under standard conditions', 0.0, 0.002, false, true),
       ('Sulfur Dioxide', 'A toxic gas with a pungent, irritating smell', 0.0, 0.0001, false, false),
       ('Nitrogen Dioxide', 'A reddish-brown gas with a characteristic sharp, biting odor', 0.0, 0.002, false, false),
       ('Krypton', 'A colorless, odorless, tasteless noble gas', 0.0, 0.0001, false, true),
       ('Xenon', 'A colorless, dense, odorless noble gas', 0.0, 0.0001, false, true),
       ('Ozone', 'A form of oxygen with three atoms in each molecule', 0.0, 0.001, false, false),
       ('Carbon Monoxide', 'A colorless, odorless, and tasteless gas that is slightly less dense than air', 0.0, 0.001,
        false, false),
       ('Chlorine', 'A yellow-green gas at room temperature', 0.0, 0.0001, false, false),
       ('Fluorine', 'The lightest halogen and exists as a highly toxic pale yellow diatomic gas', 0.0, 0.0001, false,
        false),
       ('Bromine',
        'A fuming red-brown liquid at room temperature that evaporates readily to form a similarly colored gas', 0.0,
        0.0001, false, false),
       ('Iodine', 'A chemical element with symbol I and atomic number 53', 0.0, 0.0001, false, false),
       ('Radon', 'A radioactive, colorless, odorless, tasteless noble gas', 0.0, 0.0001, true, true),
       ('Radium', 'An alkaline earth metal, highly radioactive', 0.0, 0.0001, true, false),
       ('Thorium', 'A weakly radioactive metallic chemical element', 0.0, 0.0001, true, false),
       ('Uranium', 'A silvery-grey metal in the actinide series of the periodic table', 0.0, 0.0001, true, false),
       ('Plutonium', 'A radioactive chemical element with symbol Pu and atomic number 94', 0.0, 0.0001, true, false);


-- Insert data into ATMOSPHERES_ELEMENTS table
INSERT INTO ATMOSPHERES_ELEMENTS (atmosphere_id, element_id, percentage)
VALUES ((SELECT atmosphere_id
         FROM ATMOSPHERES
         WHERE celestial_body_id = (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Earth')),
        (SELECT element_id FROM ELEMENTS WHERE name = 'Nitrogen'), 78),
       ((SELECT atmosphere_id
         FROM ATMOSPHERES
         WHERE celestial_body_id = (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Earth')),
        (SELECT element_id FROM ELEMENTS WHERE name = 'Oxygen'), 21),
       ((SELECT atmosphere_id
         FROM ATMOSPHERES
         WHERE celestial_body_id = (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Earth')),
        (SELECT element_id FROM ELEMENTS WHERE name = 'Carbon Dioxide'), 0.04),
       ((SELECT atmosphere_id
         FROM ATMOSPHERES
         WHERE celestial_body_id = (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Earth')),
        (SELECT element_id FROM ELEMENTS WHERE name = 'Argon'), 0.93),
       ((SELECT atmosphere_id
         FROM ATMOSPHERES
         WHERE celestial_body_id = (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Mars')),
        (SELECT element_id FROM ELEMENTS WHERE name = 'Carbon Dioxide'), 95.32),
       ((SELECT atmosphere_id
         FROM ATMOSPHERES
         WHERE celestial_body_id = (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Mars')),
        (SELECT element_id FROM ELEMENTS WHERE name = 'Nitrogen'), 2.6),
       ((SELECT atmosphere_id
         FROM ATMOSPHERES
         WHERE celestial_body_id = (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Mars')),
        (SELECT element_id FROM ELEMENTS WHERE name = 'Argon'), 1.9),
       ((SELECT atmosphere_id
         FROM ATMOSPHERES
         WHERE celestial_body_id = (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Venus')),
        (SELECT element_id FROM ELEMENTS WHERE name = 'Carbon Dioxide'), 96.5),
       ((SELECT atmosphere_id
         FROM ATMOSPHERES
         WHERE celestial_body_id = (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Venus')),
        (SELECT element_id FROM ELEMENTS WHERE name = 'Nitrogen'), 3.5);



INSERT INTO MISSIONS (name, description, start_date, end_date, completed, explored_body_id, spaceship_id)
VALUES
    ('Mission Apollo', 'First mission to explore Mars', '2024-01-01', '2024-01-10', TRUE,
     (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Mars'),
     (SELECT spaceship_id FROM SPACESHIPS ORDER BY RAND() LIMIT 1)),

    ('Mission Orion', 'Mission to study the atmosphere of Venus', '2024-02-01', '2024-02-15', FALSE,
     (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Venus'),
     (SELECT spaceship_id FROM SPACESHIPS ORDER BY RAND() LIMIT 1)),

    ('Mission Galaxy', 'Exploration mission to the gas giant Jupiter', '2024-03-01', '2024-03-20', TRUE,
     (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Jupiter'),
     (SELECT spaceship_id FROM SPACESHIPS ORDER BY RAND() LIMIT 1)),

    ('Mission Star', 'Study of the ring system of Saturn', '2024-04-01', '2024-04-25', FALSE,
     (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Saturn'),
     (SELECT spaceship_id FROM SPACESHIPS ORDER BY RAND() LIMIT 1)),

    ('Mission Voyager', 'Exploring the outer regions of Uranus', '2024-05-01', '2024-05-30', TRUE,
     (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Uranus'),
     (SELECT spaceship_id FROM SPACESHIPS ORDER BY RAND() LIMIT 1)),

    ('Mission Enterprise', 'Mission to observe Neptune’s moons', '2024-06-01', '2024-06-10', FALSE,
     (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Neptune'),
     (SELECT spaceship_id FROM SPACESHIPS ORDER BY RAND() LIMIT 1)),

    ('Mission Explorer', 'First human mission to Pluto', '2024-07-01', '2024-07-20', TRUE,
     (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Pluto'),
     (SELECT spaceship_id FROM SPACESHIPS ORDER BY RAND() LIMIT 1)),

    ('Mission Discovery', 'Detailed mapping of Earth’s oceans', '2024-08-01', '2024-08-15', FALSE,
     (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Earth'),
     (SELECT spaceship_id FROM SPACESHIPS ORDER BY RAND() LIMIT 1)),

    ('Mission Odyssey', 'Second mission to Mars for sample return', '2024-09-01', '2024-09-25', TRUE,
     (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Mars'),
     (SELECT spaceship_id FROM SPACESHIPS ORDER BY RAND() LIMIT 1)),

    ('Mission Endeavor', 'Long-duration mission to study Venus’s surface', '2024-10-01', '2024-10-20', FALSE,
     (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Venus'),
     (SELECT spaceship_id FROM SPACESHIPS ORDER BY RAND() LIMIT 1)),

    ('Mission Challenger', 'Investigating the Great Red Spot of Jupiter', '2024-11-01', '2024-11-15', TRUE,
     (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Jupiter'),
     (SELECT spaceship_id FROM SPACESHIPS ORDER BY RAND() LIMIT 1)),

    ('Mission Pathfinder', 'Exploration of Saturn’s moon Titan', '2024-12-01', '2024-12-25', FALSE,
     (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Saturn'),
     (SELECT spaceship_id FROM SPACESHIPS ORDER BY RAND() LIMIT 1)),

    ('Mission Horizon', 'Mission to observe the poles of Uranus', '2025-01-01', '2025-01-20', TRUE,
     (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Uranus'),
     (SELECT spaceship_id FROM SPACESHIPS ORDER BY RAND() LIMIT 1)),

    ('Mission Frontier', 'Neptune’s rings and moon Triton exploration', '2025-02-01', '2025-02-15', FALSE,
     (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Neptune'),
     (SELECT spaceship_id FROM SPACESHIPS ORDER BY RAND() LIMIT 1)),

    ('Mission Pioneer', 'Study of the dwarf planet Pluto’s surface', '2025-03-01', '2025-03-25', TRUE,
     (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Pluto'),
     (SELECT spaceship_id FROM SPACESHIPS ORDER BY RAND() LIMIT 1)),

    ('Mission Titan', 'Comprehensive survey of Earth’s atmosphere', '2025-04-01', '2025-04-20', FALSE,
     (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Earth'),
     (SELECT spaceship_id FROM SPACESHIPS ORDER BY RAND() LIMIT 1)),

    ('Mission Atlantis', 'Investigating the polar ice caps of Mars', '2025-05-01', '2025-05-30', TRUE,
     (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Mars'),
     (SELECT spaceship_id FROM SPACESHIPS ORDER BY RAND() LIMIT 1)),

    ('Mission Luna', 'Venusian surface composition analysis', '2025-06-01', '2025-06-10', FALSE,
     (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Venus'),
     (SELECT spaceship_id FROM SPACESHIPS ORDER BY RAND() LIMIT 1)),

    ('Mission Solar', 'Magnetic field study of Jupiter', '2025-07-01', '2025-07-20', TRUE,
     (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Jupiter'),
     (SELECT spaceship_id FROM SPACESHIPS ORDER BY RAND() LIMIT 1)),

    ('Mission Mercury', 'Saturn’s core temperature measurement', '2025-08-01', '2025-08-15', FALSE,
     (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Saturn'),
     (SELECT spaceship_id FROM SPACESHIPS ORDER BY RAND() LIMIT 1));


-- Insert data into RESIDENTS table
INSERT INTO RESIDENTS (gender, full_name, birth_date, death_date)
VALUES ('M', 'John Doe', '1980-05-15', NULL),
       ('F', 'Jane Smith', '1990-11-30', NULL),
       ('M', 'Tom Johnson', '1975-03-10', NULL),
       ('F', 'Alice Brown', '1985-07-25', NULL),
       ('M', 'Bob Williams', '1992-02-14', NULL),
       ('F', 'Mary Jones', '1988-06-05', NULL),
       ('M', 'Chris Garcia', '1981-12-19', NULL),
       ('F', 'Linda Martinez', '1995-09-07', NULL),
       ('M', 'David Rodriguez', '1979-04-22', NULL),
       ('F', 'Karen Lopez', '1982-08-13', NULL),
       ('M', 'Michael Gonzales', '1991-01-27', NULL),
       ('F', 'Patricia Wilson', '1984-05-30', NULL),
       ('M', 'James Clark', '1977-11-04', NULL),
       ('F', 'Susan Lewis', '1993-03-20', NULL),
       ('M', 'Robert Walker', '1986-12-15', NULL),
       ('F', 'Linda Hall', '1978-04-25', NULL),
       ('M', 'Charles Allen', '1992-07-19', NULL),
       ('F', 'Barbara Young', '1981-09-22', NULL),
       ('M', 'Steven King', '1983-03-17', NULL),
       ('F', 'Elizabeth Wright', '1987-11-03', NULL);

-- Insert data into CELESTIAL_BODY_RESIDENTS table
INSERT INTO CELESTIAL_BODY_RESIDENTS (resident_id, celestial_body_id, resident_from, resident_until)
VALUES ((SELECT resident_id FROM RESIDENTS WHERE full_name = 'John Doe'),
        (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Earth'), '2020-01-01', NULL),
       ((SELECT resident_id FROM RESIDENTS WHERE full_name = 'Jane Smith'),
        (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Mars'), '2018-05-10', NULL),
       ((SELECT resident_id FROM RESIDENTS WHERE full_name = 'Tom Johnson'),
        (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Venus'), '2015-07-15', NULL),
       ((SELECT resident_id FROM RESIDENTS WHERE full_name = 'Alice Brown'),
        (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Jupiter'), '2017-03-20', NULL),
       ((SELECT resident_id FROM RESIDENTS WHERE full_name = 'Bob Williams'),
        (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Saturn'), '2019-11-22', NULL),
       ((SELECT resident_id FROM RESIDENTS WHERE full_name = 'Mary Jones'),
        (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Uranus'), '2021-06-01', NULL),
       ((SELECT resident_id FROM RESIDENTS WHERE full_name = 'Chris Garcia'),
        (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Neptune'), '2018-12-13', NULL),
       ((SELECT resident_id FROM RESIDENTS WHERE full_name = 'Linda Martinez'),
        (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Pluto'), '2022-04-05', NULL),
       ((SELECT resident_id FROM RESIDENTS WHERE full_name = 'David Rodriguez'),
        (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Mars'), '2020-08-11', NULL),
       ((SELECT resident_id FROM RESIDENTS WHERE full_name = 'Karen Lopez'),
        (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Venus'), '2019-09-17', NULL),
       ((SELECT resident_id FROM RESIDENTS WHERE full_name = 'Michael Gonzales'),
        (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Jupiter'), '2021-03-23', NULL),
       ((SELECT resident_id FROM RESIDENTS WHERE full_name = 'Patricia Wilson'),
        (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Saturn'), '2020-07-30', NULL),
       ((SELECT resident_id FROM RESIDENTS WHERE full_name = 'James Clark'),
        (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Uranus'), '2019-12-01', NULL),
       ((SELECT resident_id FROM RESIDENTS WHERE full_name = 'Susan Lewis'),
        (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Neptune'), '2022-02-15', NULL),
       ((SELECT resident_id FROM RESIDENTS WHERE full_name = 'Robert Walker'),
        (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Pluto'), '2021-08-10', NULL),
       ((SELECT resident_id FROM RESIDENTS WHERE full_name = 'Linda Hall'),
        (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Mars'), '2018-04-20', NULL),
       ((SELECT resident_id FROM RESIDENTS WHERE full_name = 'Charles Allen'),
        (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Venus'), '2017-10-25', NULL),
       ((SELECT resident_id FROM RESIDENTS WHERE full_name = 'Barbara Young'),
        (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Jupiter'), '2019-05-30', NULL),
       ((SELECT resident_id FROM RESIDENTS WHERE full_name = 'Steven King'),
        (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Saturn'), '2020-09-14', NULL),
       ((SELECT resident_id FROM RESIDENTS WHERE full_name = 'Elizabeth Wright'),
        (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Uranus'), '2021-01-19', NULL);
