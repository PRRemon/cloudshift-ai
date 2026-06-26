# Domain Model

## Organization

Represents a customer organization.

Relationships

- Owns many Users
- Owns many Projects

---

## User

Represents a platform user.

Relationships

- Belongs to one Organization
- Has one Role

---

## Project

Represents a cloud migration project.

Relationships

- Belongs to Organization
- Owns Applications
- Owns Servers
- Owns Databases
- Owns Recommendations

---

## Application

Represents a business application.

Relationships

- Belongs to Project

---

## Server

Represents an on-premise server.

Relationships

- Belongs to Project

---

## Recommendation

Represents AWS migration recommendation.

Relationships

- Belongs to Project