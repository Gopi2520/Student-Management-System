CREATE DATABASE IF NOT EXISTS student_management;
USE student_management;

CREATE TABLE student (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    email VARCHAR(100) UNIQUE,
    course VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO student (name, age, email, course) VALUES
('Arun Kumar', 20, 'arun.kumar@example.com', 'Computer Science'),
('Priya Sharma', 22, 'priya.sharma@example.com', 'Information Technology');