CREATE SCHEMA IF NOT EXISTS user_management;
CREATE TABLE user_management.t_users(
    id SERIAL PRIMARY KEY,
    c_username VARCHAR NOT NULL UNIQUE,
    c_password VARCHAR
);
CREATE TABLE user_management.t_roles(
    id SERIAL PRIMARY KEY,
    c_role VARCHAR NOT NULL UNIQUE
);
CREATE TABLE user_management.t_users_roles(
    id SERIAL PRIMARY KEY,
    id_user INT NOT NULL REFERENCES user_management.t_users(id),
    id_role INT NOT NULL REFERENCES user_management.t_roles(id)
)