# Ambula
Entities: The project defines User and Location entities to manage user roles and geographical points.

API Endpoints: RESTful APIs enable CRUD operations for ADMIN users on users and locations, while READER users can only retrieve data.

Database: The project integrates with a database (e.g., H2) using Spring Data JPA for data storage.

Haversine Formula: Utilizes the Haversine formula to calculate distances between geographical points accurately.

Nearest Location Retrieval: Offers a dedicated endpoint (get_users/N) to fetch N nearest locations from the origin.

Security: Implements Spring Security for role-based access control, ensuring secure interactions and data protection.
