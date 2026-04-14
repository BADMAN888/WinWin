## Run project

docker compose up --build

## Register
POST /api/auth/register

## Login
POST /api/auth/login

## Process (secured)
POST /api/process
Authorization: Bearer <token>