
DROP DATABASE IF EXISTS planetary_real_estate_db;

CREATE DATABASE planetary_real_estate_db;

-- Work with created db
USE planetary_real_estate_db;


-- Create User table
CREATE TABLE PERSON
(
    person_id INT AUTO_INCREMENT PRIMARY KEY,
    name      VARCHAR(50)  NOT NULL,
    last_name VARCHAR(50)  NOT NULL,
    username  VARCHAR(20)  NOT NULL UNIQUE,
    password  VARCHAR(255) NOT NULL
);

-- Explicitly adding an index for the username column
CREATE INDEX idx_username ON PERSON (username);

-- Create CelestialType table
CREATE TABLE CELESTIAL_TYPES
(
    celestial_type_id INT AUTO_INCREMENT PRIMARY KEY,
    name              VARCHAR(50) NOT NULL UNIQUE
);

-- Create Celestial Body table (Planets and Satellites) with additional columns
CREATE TABLE CELESTIAL_BODIES
(
    celestial_body_id          INT AUTO_INCREMENT PRIMARY KEY,
    name                       VARCHAR(50) NOT NULL UNIQUE,
    type                       INT         NOT NULL,
    description                TEXT,
    surface_pressure           DECIMAL(10, 2),
    surface_temperature        DECIMAL(10, 2),
    core_temperature           DECIMAL(10, 2),
    has_been_explored          BOOLEAN,
    radiation_levels           VARCHAR(255),
    has_water                  BOOLEAN,
    surface_area               DECIMAL(20, 2),
    is_surface_hard            BOOLEAN,
    mass                       DECIMAL(20, 2),
    gravitational_field_height DECIMAL(20, 2),
    rotates_around_id          INT,
    moving_speed               DECIMAL(20, 2),
    rotation_speed             DECIMAL(20, 2),
    FOREIGN KEY (type) REFERENCES CELESTIAL_TYPES (celestial_type_id),
    FOREIGN KEY (rotates_around_id) REFERENCES CELESTIAL_BODIES (celestial_body_id)
);

-- Create Mission table
CREATE TABLE MISSIONS
(
    mission_id        INT AUTO_INCREMENT PRIMARY KEY,
    name              VARCHAR(50) NOT NULL,
    launch_date       DATE,
    celestial_body_id INT         NOT NULL,
    FOREIGN KEY (celestial_body_id) REFERENCES CELESTIAL_BODIES (celestial_body_id)
);

-- Create Property table (Residential Facilities)
CREATE TABLE PROPERTIES
(
    property_id       INT AUTO_INCREMENT PRIMARY KEY,
    name              VARCHAR(50)    NOT NULL,
    description       TEXT,
    price             DECIMAL(10, 2) NOT NULL,
    celestial_body_id INT            NOT NULL,
    FOREIGN KEY (celestial_body_id) REFERENCES CELESTIAL_BODIES (celestial_body_id)
);

-- Create ELEMENTS table
CREATE TABLE ELEMENTS
(
    element_id     INT AUTO_INCREMENT PRIMARY KEY,
    name           VARCHAR(50) NOT NULL UNIQUE,
    min_percentage DECIMAL(5, 2), -- Minimum percentage for human habitability
    max_percentage DECIMAL(5, 2)  -- Maximum percentage for human habitability
);

-- Modify the ATMOSPHERES table (remove element columns)
CREATE TABLE ATMOSPHERES
(
    atmosphere_id     INT AUTO_INCREMENT PRIMARY KEY,
    celestial_body_id INT UNIQUE,
    FOREIGN KEY (celestial_body_id) REFERENCES CELESTIAL_BODIES (celestial_body_id),
    atmosphere_height INT -- Height of the atmosphere in kilometers
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

-- Create Departure table
CREATE TABLE DEPARTURES
(
    departure_id   INT AUTO_INCREMENT PRIMARY KEY,
    departure_date DATETIME NOT NULL,
    passenger_id   INT      NOT NULL,
    FOREIGN KEY (passenger_id) REFERENCES PERSON (person_id)
);

CREATE INDEX idx_departure_date ON DEPARTURES (departure_date);

-- Populate CelestialType table with data (optional)
INSERT INTO CELESTIAL_TYPES (name)
VALUES ('Planet'),
       ('Satellite'),
       ('Star'),
       ('Dwarf Planet'),
       ('Asteroid');

-- Sample Data (consider adjusting based on your needs)
-- User table
INSERT INTO PERSON (name, last_name, username, password)
VALUES ('Neel ', 'Armstrong', 'neal123', 'password1'),
       ('Jane', 'Smith', 'janesmith', 'password2'),
       ('Alice', 'Williams', 'alicew', 'password3'),
       ('Bob', 'Johnson', 'bob', 'password4'),
       ('Michael', 'Brown', 'mikeb', 'password5');


-- Insert other celestial bodies excluding rotates_around_id
INSERT INTO CELESTIAL_BODIES (name, type, description, surface_pressure, surface_temperature, core_temperature,
                              has_been_explored, radiation_levels, has_water, surface_area, is_surface_hard, mass,
                              gravitational_field_height, moving_speed, rotation_speed)

VALUES
-- 1
('Sun', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Star'),
 'The star at the center of our solar system', NULL, 5778, 15000000, true, 'High', false, 608320000000,
 false,
 1988500, NULL, NULL, NULL),
-- 2
('Mars', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Planet'),
 'The red planet, known for its canyons and potential for life', 0.636, -63, 1700, true, 'Low', true,
 144798500,
 true, 0.64171, 210, 24.077, 0.004),
-- 3
('Earth', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Planet'),
 'Our home planet, teeming with life', 101.325, 15, 6000, true, 'Low', true, 510072000, true, 5.97237, 100, -- 3
 29.78, 0.4651),
-- 4
('Jupiter', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Planet'),
 'The largest planet in our solar system, a gas giant with a Great Red Spot', 1000, -108, 30000, true,
 'High', false, 61420000000, false, 1898.19, 1000, 13.07, 0.001),
-- 5
('Saturn', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Planet'),
 'Known for its stunning ring system, another gas giant', 140, -139, 11700, true, 'High', false, 42700000000,
 false, 568.34, 1000, 9.68, 0.0009),
-- 6
('Venus', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Planet'),
 'Similar in size to Earth but with a thick, toxic atmosphere and surface temperatures hot enough to melt lead',
 9200, 464, 6000, false, 'High', false, 460200000, true, 4.8675, 100, 35.02, -0.002),
-- 7
('Mercury', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Planet'),
 'The closest planet to the Sun, known for its extreme temperature variations', 0, 167, 1800, false, 'Medium',
 false, 74800000, true, 0.33011, 100, 47.87, 0.001),
-- 8
('Neptune', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Planet'),
 'A blue gas giant, known for its strong winds and large storms', 1000, -201, 7000, false, 'High', false,
 76400000000, false, 102.413, 200, 5.43, 0.0005),
-- 9
('Uranus', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Planet'),
 'An ice giant with a unique tilted rotation axis', 800, -224, 5000, false, 'High', false, 80830000000, false,
 86.8103, 200, 6.81, -0.0007),
-- 10
('Pluto', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Dwarf Planet'),
 'Once considered the ninth planet, now classified as a dwarf planet in the Kuiper belt', 0, -229, 2400, true,
 'Low', false, 16700000, true, 0.01303, 200, 4.74, -0.0002),
-- 11
('Ceres', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Dwarf Planet'),
 'The largest object in the asteroid belt between Mars and Jupiter', 0, -105, 200, true, 'Low', false, 2791000,
 true, 0.000938, 100, 17.9, 0.003),
-- 12
('Haumea', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Dwarf Planet'),
 'A rapidly spinning elongated dwarf planet in the Kuiper belt', 0, -241, 1700, false, 'Medium', false, 1170000,
 true, 0.004006, 200, 4.53, -0.0033),
-- 13
('Makemake', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Dwarf Planet'),
 'Another dwarf planet in the Kuiper belt, known for its bright surface', 0, -243, 1600, false, 'Low', false,
 1240000, true, 0.0032, 200, 4.41, -0.0014),
-- 14
('Eris', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Dwarf Planet'),
 'A distant dwarf planet, one of the largest known in our solar system', 0, -231, 200, false, 'Low', false,
 2700000, true, 0.0167, 100, 3.5, 0.009),
-- 15
('Ganymede', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Satellite'),
 'The largest moon of Jupiter and the largest in the solar system', 0, -163, 1200, true, 'Low', true, 87000000,
 true, 0.0148, 200, 10.88, 0.001),
-- 16
('Callisto', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Satellite'),
 'A heavily cratered moon of Jupiter', 0, -139, 1200, true, 'Low', false, 73400000, true, 0.018, 200, 8.2,
 0.001),
-- 17
('Io', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Satellite'),
 'A volcanically active moon of Jupiter', 0, -130, 1200, true, 'Low', false, 42000000, true, 0.015, 200, 17.3,
 0.001),
-- 18
('Enceladus', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Satellite'),
 'A moon of Saturn with geysers of water ice', 0, -201, 1000, true, 'Low', true, 8000000, true, 0.0002, 100,
 12.63, 0.001),
-- 19
('Triton', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Satellite'),
 'The largest moon of Neptune, known for its retrograde orbit', 0, -235, 1000, true, 'Low', false, 23000000,
 true, 0.021, 200, 4.39, 0.002),
-- 20
('Charon', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Satellite'),
 'The largest moon of Pluto, half the size of its parent dwarf planet', 0, -220, 1000, true, 'Low', false,
 4600000, true, 0.001, 200, 4.64, 0.002),
-- 21
('Vesta', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Asteroid'),
 'One of the largest asteroids in the asteroid belt', 0, -20, 1000, true, 'Low', false, 520000, true, 0.0003,
 100, 19.34, 0.002),
('Pallas', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Asteroid'),
 'A large asteroid with an irregular shape', 0, -20, 1000, true, 'Low', false, 610000, true, 0.0003, 100, 17.65,
 0.002),
-- 22
('Hygeia', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Asteroid'),
 'The fourth largest asteroid in the asteroid belt', 0, -20, 1000, true, 'Low', false, 350000, true, 0.0003, 100,
 17.91, 0.002),
-- 23
('Bennu', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Asteroid'),
 'A near-Earth asteroid, the target of the OSIRIS-REx mission', 0, -20, 1000, true, 'Low', false, 78.8, true,
 0.0000000075, 100, 28.07, 0.004),
-- 24
('Ryugu', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Asteroid'),
 'A near-Earth asteroid, explored by the Hayabusa2 mission', 0, -20, 1000, true, 'Low', false, 33.5, true,
 0.00000000075, 100, 28.29, 0.004);

-- Update statements to set the rotates_around_id values with constant int values

-- Bodies that rotate around the Sun
UPDATE CELESTIAL_BODIES
SET rotates_around_id = 1
WHERE name IN ('Earth', 'Mars', 'Mercury', 'Venus', 'Jupiter', 'Saturn', 'Uranus', 'Neptune', 'Vesta', 'Pallas', 'Hygeia', 'Bennu', 'Ryugu');

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


-- Insert data into ATMOSPHERES table
INSERT INTO ATMOSPHERES (celestial_body_id, atmosphere_height)
VALUES ((SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Earth'), 480),
       ((SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Mars'), 11),
       ((SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Venus'), 250);

-- Insert data into ELEMENTS table
INSERT INTO ELEMENTS (name, min_percentage, max_percentage)
VALUES ('Nitrogen', 78, 78),
       ('Oxygen', 19.5, 23.5),
       ('Carbon Dioxide', 0, 0.04),
       ('Argon', 0, 1);

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