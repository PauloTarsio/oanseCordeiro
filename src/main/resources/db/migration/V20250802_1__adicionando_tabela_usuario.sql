CREATE TABLE usuario (
    u_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    u_login VARCHAR(20) NOT NULL UNIQUE,
    u_senha VARCHAR(255) NOT NULL,
    u_roles TEXT[] NOT NULL
);