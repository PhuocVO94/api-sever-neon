
## 🗄️ Database Architecture

### 1. Domain Authentication & User Profile 
### 2. Domain Product Catalog
![ERD Database Auth vs Database Product](./images/API-Service-Neon (1).png")



🚀 E-Commerce API (Spring Boot)
An E-commerce API system built for learning purposes, implementing the latest technologies in the Java Spring ecosystem. This project focuses on high security, automated development workflows (CI/CD), and scalability.

✨ Key Features
Authentication & Authorization: Comprehensive user management and role-based access control.

Security: Integrated Spring Security with JWT (JSON Web Token) for stateless authentication.

Role-based Access Control (RBAC):

Admin: Manage products, orders, users, and view statistics.

User: Browse products, manage shopping carts, and place orders.

Database: PostgreSQL for reliable and persistent data storage.

CI/CD: Automated Build, Test, and Deployment pipeline.

🛠 Tech Stack
Backend: Java 17+, Spring Boot 3.x

Security: Spring Security, JWT

Database: PostgreSQL

ORM: Spring Data JPA (Hibernate)

CI/CD: GitHub Actions

Build Tool: Maven

🏗 System Architecture
The project follows the Layered Architecture pattern:

Controller: Handles incoming HTTP requests and returns responses.

Service: Contains the core business logic.

Repository: Manages communication with the PostgreSQL database.

Entity: Maps Java objects to database tables.

Security Config: Configures JWT filters and endpoint authorization.

🚀 CI/CD Pipeline
Integrated CI/CD workflow ensures code stability before reaching production:

Code Push: Developers push code to the GitHub repository.

Build & Test: GitHub Actions automatically builds the project and runs Unit Tests.

Deploy: Automatically deploys to Azure environments once all checks pass.

⚙️ Installation & Setup
1. Prerequisites
JDK 17 or higher.

PostgreSQL (Local instance or Cloud DB).

Tiếng việt:

🚀 E-Commerce API (Spring Boot)
Dự án API cho hệ thống bán hàng trực tuyến được xây dựng với mục đích học tập và áp dụng các công nghệ mới nhất trong hệ sinh thái Java Spring. Dự án tập trung vào tính bảo mật, quy trình phát triển tự động (CI/CD) và khả năng mở rộng.

✨ Tính năng chính
Authentication & Authorization: Quản lý người dùng và phân quyền dựa trên Role.
Security: Tích hợp Spring Security và định danh qua JWT (JSON Web Token).
Role-based Access Control (RBAC):
Admin: Quản lý sản phẩm, đơn hàng, người dùng và thống kê.
User: Xem sản phẩm, quản lý giỏ hàng và đặt hàng.
Database: Sử dụng PostgreSQL để lưu trữ dữ liệu bền vững.
CI/CD: Tự động hóa quy trình Build, Test và Deploy.
🛠 Công nghệ sử dụng
Backend: Java 17+, Spring Boot 3.x
Security: Spring Security, JWT
Database: PostgreSQL
ORM: Spring Data JPA (Hibernate)
CI/CD: GitHub Actions 
Build Tool: Maven
🏗 Kiến trúc hệ thống
Dự án tuân thủ cấu trúc Layered Architecture:

Controller: Tiếp nhận request và trả về response.
Service: Xử lý logic nghiệp vụ.
Repository: Giao tiếp với Database PostgreSQL.
Entity: Định nghĩa các bảng trong Database.
Security Config: Cấu hình bộ lọc JWT và phân quyền Endpoint.
🚀 Quy trình CI/CD
Dự án được tích hợp luồng CI/CD giúp đảm bảo code luôn ổn định trước khi lên môi trường thật:

Code Push: Developer đẩy code lên GitHub.
Build & Test: Hệ thống tự động kiểm tra lỗi và chạy Unit Test.
Deploy: Tự động Deploy lên môi trường Azure... khi pass các bước trên.

1. Yêu cầu hệ thống
JDK 17 trở lên.
PostgreSQL Cloud DB.
Translate thêm bản tiếng anh
=======
# Wbh_springboot_resfullAPI
