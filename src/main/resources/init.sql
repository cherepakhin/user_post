CREATE TABLE users (
    id INT PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE posts (
    id INT PRIMARY KEY,
    title VARCHAR(255),
    content TEXT,
    author_id INT,
    FOREIGN KEY (author_id) REFERENCES users(id)
);