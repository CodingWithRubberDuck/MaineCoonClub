DROP TABLE IF EXISTS cat;

DROP TABLE IF EXISTS member;

CREATE TABLE member (
                        member_id INT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        email VARCHAR(100) NOT NULL UNIQUE,
                        password_hash VARCHAR(400) NOT NULL
);

CREATE TABLE cat (
                     cat_id INT AUTO_INCREMENT PRIMARY KEY,
                     member_id INT,
                     FOREIGN KEY (member_id) REFERENCES member (member_id) ON DELETE SET NULL,
                     name VARCHAR(200) NOT NULL,
                     date_of_birth DATE NOT NULL,
                     date_of_death DATE,
                     sex ENUM ('FEMALE', 'MALE') NOT NULL,
                     ems_code ENUM ('MCO_GR1', 'MCO_GR2', 'MCO_GR3', 'MCO_GR4', 'MCO_GR5', 'MCO_GR6', 'MCO_GR7', 'MCO_GR8', 'MCO_GR9') NOT NULL,
                     fertile BOOLEAN NOT NULL,

                     breeder VARCHAR(100),
                     father_cat VARCHAR(200),
                     mother_cat VARCHAR(200)
);