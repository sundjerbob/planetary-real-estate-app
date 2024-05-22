-- Drop db if exists (consider data backup)
DROP DATABASE IF EXISTS planetary_real_estate_db;

-- Create new db
CREATE DATABASE planetary_real_estate_db;

-- Work with created db
USE planetary_real_estate_db;

-- Create User table
CREATE TABLE USERS
(
    user_id   INT AUTO_INCREMENT PRIMARY KEY,
    name      VARCHAR(50)  NOT NULL,
    last_name VARCHAR(50)  NOT NULL,
    username  VARCHAR(20)  NOT NULL UNIQUE,
    password  VARCHAR(255) NOT NULL
);

-- Create CelestialType table
CREATE TABLE CELESTIAL_TYPES
(
    celestial_type_id INT AUTO_INCREMENT PRIMARY KEY,
    name              VARCHAR(50) NOT NULL UNIQUE
);

-- Create Celestial Body table (Planets and Satellites)
CREATE TABLE CELESTIAL_BODIES
(
    celestial_body_id INT AUTO_INCREMENT PRIMARY KEY,
    name              VARCHAR(50) NOT NULL UNIQUE,
    type              INT         NOT NULL,
    description       TEXT,
    FOREIGN KEY (type) REFERENCES CELESTIAL_TYPES (celestial_type_id)
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
    user_id        INT      NOT NULL,
    FOREIGN KEY (user_id) REFERENCES USERS (user_id)
);

-- Populate CelestialType table with data (optional)
INSERT INTO CELESTIAL_TYPES (name)
VALUES ('Planet'),
       ('Satellite'),
       ('Star'),
       ('Dwarf Planet'),
       ('Asteroid');

-- Sample Data (consider adjusting based on your needs)

-- User table
INSERT INTO USERS (name, last_name, username, password)
VALUES ('John', 'Doe', 'johndoe', 'password1'),
       ('Jane', 'Smith', 'janesmith', 'password2'),
       ('Alice', 'Williams', 'alicew', 'password3'),
       ('Bob', 'Johnson', 'bob', 'password4'),
       ('Michael', 'Brown', 'mikeb', 'password5');

-- CelestialBody table with type referencing CelestialType
INSERT INTO CELESTIAL_BODIES (name, type, description)
VALUES ('Mars', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Planet'),
        'The red planet, known for its canyons and potential for life'),
       ('Earth', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Planet'),
        'Our home planet, teeming with life'),
       ('Moon', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Satellite'),
        'Earth\'s natural satellite, with evidence of past water'),
       ('Europa', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Satellite'),
        'Moon of Jupiter, with a suspected subsurface ocean'),
       ('Titan', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Satellite'),
        'Moon of Saturn, with a thick atmosphere and lakes'),
       ('Jupiter', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Planet'),
        'The largest planet in our solar system, a gas giant with a Great Red Spot'),
       ('Saturn', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Planet'),
        'Known for its stunning ring system, another gas giant'),
       ('Venus', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Planet'),
        'Similar in size to Earth but with a thick, toxic atmosphere and surface temperatures hot enough to melt lead'),
       ('Mercury', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Planet'),
        'The closest planet to the Sun, known for its extreme temperature variations'),
       ('Neptune', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Planet'),
        'A blue gas giant, known for its strong winds and large storms'),
       ('Uranus', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Planet'),
        'An ice giant with a unique tilted rotation axis'),
       ('Pluto', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Dwarf Planet'),
        'Once considered the ninth planet, now classified as a dwarf planet in the Kuiper belt'),
       ('Ceres', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Dwarf Planet'),
        'The largest object in the asteroid belt between Mars and Jupiter'),
       ('Haumea', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Dwarf Planet'),
        'A dwarf planet known for its elongated shape and rapid rotation'),
       ('Makemake', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Dwarf Planet'),
        'A dwarf planet in the Kuiper belt, known for its lack of atmosphere'),
       ('Eris', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Dwarf Planet'),
        'A distant dwarf planet, one of the largest known in our solar system'),
       ('Ganymede', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Satellite'),
        'The largest moon of Jupiter and the largest in the solar system'),
       ('Callisto', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Satellite'),
        'A heavily cratered moon of Jupiter'),
       ('Io', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Satellite'),
        'A volcanically active moon of Jupiter'),
       ('Enceladus', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Satellite'),
        'A moon of Saturn with geysers of water ice'),
       ('Triton', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Satellite'),
        'The largest moon of Neptune, known for its retrograde orbit'),
       ('Charon', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Satellite'),
        'The largest moon of Pluto, half the size of its parent dwarf planet'),
       ('Vesta', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Asteroid'),
        'One of the largest asteroids in the asteroid belt'),
       ('Pallas', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Asteroid'),
        'A large asteroid with an irregular shape'),
       ('Hygeia', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Asteroid'),
        'The fourth largest asteroid in the asteroid belt'),
       ('Bennu', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Asteroid'),
        'A near-Earth asteroid, the target of the OSIRIS-REx mission'),
       ('Ryugu', (SELECT celestial_type_id FROM CELESTIAL_TYPES WHERE name = 'Asteroid'),
        'A near-Earth asteroid, explored by the Hayabusa2 mission');


-- Mission table
INSERT INTO MISSIONS (name, launch_date, celestial_body_id)
VALUES ('Viking 1', '1976-08-20', (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Mars')),
       ('Apollo 11', '1969-07-16', (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Moon')),
       ('Galileo', '1989-10-18', (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Europa')),
       ('Cassini-Huygens', '1997-10-15', (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Titan'));

-- Property table
INSERT INTO PROPERTIES (name, description, price, celestial_body_id)
VALUES ('Mars Colony Alpha', 'A state-of-the-art colony on Mars with advanced life support systems.', 2500000.00,
        (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Mars')),
       ('Lunar Base 1', 'A lunar base equipped for scientific research and habitation.', 1500000.00,
        (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Moon')),
       ('Europa Research Station', 'A research station designed to explore the subsurface ocean of Europa.', 2000000.00,
        (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Europa')),
       ('Titan Habitat', 'A habitat on Titan, designed to withstand its dense atmosphere and low temperatures.',
        3000000.00, (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Titan'));

-- Populate ELEMENTS table with some sample data
INSERT INTO ELEMENTS (name, min_percentage, max_percentage)
VALUES ('Nitrogen', 78.08, 78.08),
       ('Oxygen', 19.50, 23.50),
       ('Argon', 0.93, 0.93),
       ('Carbon Dioxide', 0.04, 0.10),
       ('Neon', 0, 0.0018),
       ('Helium', 0, 0.00052),
       ('Methane', 0, 0.00017),
       ('Krypton', 0, 0.0001),
       ('Hydrogen', 0, 0.00005),
       ('Xenon', 0, 0.0000087),
       ('Carbon Monoxide', 0, 0.00001),
       ('Water Vapor', 0, 0.4),
       ('Ammonia', 0, 0),
       ('Sulfur Dioxide', 0, 0),
       ('Ozone', 0, 0.000007),
       ('Phosphine', 0, 0),
       ('Ethane', 0, 0.0005),
       ('Ethylene', 0, 0.00007),
       ('Acetylene', 0, 0.00001),
       ('Hydrogen Sulfide', 0, 0),
       ('Deuterium', 0, 0),
       ('Formaldehyde', 0, 0),
       ('Formic Acid', 0, 0);

-- Sample data for ATMOSPHERES table
INSERT INTO ATMOSPHERES (celestial_body_id, atmosphere_height)
VALUES ((SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Earth'), 100),
       ((SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Mars'), 11),
       ((SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Titan'), 1500),
       ((SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Venus'), 60);

-- Retrieve the generated atmosphere_id values for use in the ATMOSPHERES_ELEMENTS table
-- This is a necessary step to ensure atmosphere_id values are not null
-- Sample data for ATMOSPHERES_ELEMENTS table
INSERT INTO ATMOSPHERES_ELEMENTS (atmosphere_id, element_id, percentage)
VALUES ((SELECT atmosphere_id
         FROM ATMOSPHERES
         WHERE celestial_body_id = (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Earth')),
        (SELECT element_id FROM ELEMENTS WHERE name = 'Nitrogen'), 78.08),
       ((SELECT atmosphere_id
         FROM ATMOSPHERES
         WHERE celestial_body_id = (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Earth')),
        (SELECT element_id FROM ELEMENTS WHERE name = 'Oxygen'), 20.95),
       ((SELECT atmosphere_id
         FROM ATMOSPHERES
         WHERE celestial_body_id = (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Earth')),
        (SELECT element_id FROM ELEMENTS WHERE name = 'Argon'), 0.93),
       ((SELECT atmosphere_id
         FROM ATMOSPHERES
         WHERE celestial_body_id = (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Earth')),
        (SELECT element_id FROM ELEMENTS WHERE name = 'Carbon Dioxide'), 0.04),
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
        (SELECT element_id FROM ELEMENTS WHERE name = 'Argon'), 1.6),
       ((SELECT atmosphere_id
         FROM ATMOSPHERES
         WHERE celestial_body_id = (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Mars')),
        (SELECT element_id FROM ELEMENTS WHERE name = 'Oxygen'), 0.13),
       ((SELECT atmosphere_id
         FROM ATMOSPHERES
         WHERE celestial_body_id = (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Titan')),
        (SELECT element_id FROM ELEMENTS WHERE name = 'Nitrogen'), 95.0),
       ((SELECT atmosphere_id
         FROM ATMOSPHERES
         WHERE celestial_body_id = (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Titan')),
        (SELECT element_id FROM ELEMENTS WHERE name = 'Methane'), 5.0),
       ((SELECT atmosphere_id
         FROM ATMOSPHERES
         WHERE celestial_body_id = (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Venus')),
        (SELECT element_id FROM ELEMENTS WHERE name = 'Carbon Dioxide'), 96.5),
       ((SELECT atmosphere_id
         FROM ATMOSPHERES
         WHERE celestial_body_id = (SELECT celestial_body_id FROM CELESTIAL_BODIES WHERE name = 'Venus')),
        (SELECT element_id FROM ELEMENTS WHERE name = 'Nitrogen'), 3.5);


INSERT INTO DEPARTURES (departure_date, user_id)
VALUES ('2025-06-01 08:00:00', (SELECT user_id FROM USERS WHERE username = 'johndoe')),
       ('2024-12-15 09:30:00', (SELECT user_id FROM USERS WHERE username = 'janesmith')),
       ('2025-03-22 10:45:00', (SELECT user_id FROM USERS WHERE username = 'alicew')),
       ('2025-08-10 07:15:00', (SELECT user_id FROM USERS WHERE username = 'bob')),
       ('2025-11-05 06:00:00', (SELECT user_id FROM USERS WHERE username = 'mikeb'));
