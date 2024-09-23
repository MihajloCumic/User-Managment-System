# User Management System

## Overview
This project is a web application made in Spring for managing users and their permissions. It supports CRUD operations on users and ensures request authorization based on a defined set of permissions.

### Permissions
The following permissions can be assigned to users:
- `can_create_users`
- `can_read_users`
- `can_update_users`
- `can_delete_users`

User requests for create/read/update/delete operations are only permitted if the authenticated user has the corresponding permission.

### Authentication
User authentication is implemented using JWT (JSON Web Token). Users must provide their email address and password to authenticate. Upon successful authentication, a JWT is returned and must be included in the header of subsequent requests.

### User Entity
The user entity includes:
- First Name
- Last Name
- Email (unique)
- Password (hashed)
- Permissions (optional)

All attributes except permissions are mandatory. Duplicate email addresses are not allowed. Passwords must be stored securely using a hashing algorithm.

## Frontend Repository
The frontend of this project can be found here: [User Management System Frontend](https://github.com/MihajloCumic/User-Mangment-System_Frontend)
### Frontend Features
The frontend, developed using Angular 2+, consists of four main pages:
1. **Login Page**: Allows users to log in.
2. **User Display Page**: A table showing all users, including their names, emails, and permissions.
3. **User Creation Page**: A form for adding new users with required fields.
4. **User Edit Page**: Editable fields for existing user details.

### Permission-Based Behavior
The frontend adapts its behavior based on user permissions:
- **can_read_users**: Access to the user table is granted.
- **can_create_users**: Link to add users is visible; access to the page is granted.
- **can_update_users**: Clickable email addresses and edit access granted.
- **can_delete_users**: Delete buttons for each user are visible.

If a user lacks any permissions, they will be alerted upon successful login.

