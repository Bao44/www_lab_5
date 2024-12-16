# ✏️ **Bài tập lớn WWW Lab 5**

## Thông tin
#### **Họ tên**: Trương Quốc Bảo
#### **MSSV**: 21017351  
#### **email**: tqbao44@gmail.com
#### **Giảng viên hướng dẫn**: Võ Văn Hải
---
## Cài đặt dự án

### 1. Yêu cầu bắt buộc
- **Java (JDK)** phiên bản >= 17
- **Công cụ build**: Gradle
- **MariaDB**
- **IDE**: IntelliJ IDEA
- **Hệ điều hành**: Windows, MacOS
- **Email**: Xác thực 2 yếu tố

### 2. Cách cài đặt dự án
1. **Clone repository**:
    ```bash
    git clone https://github.com/Bao44/www_lab_5.git
    ```
2. **Cấu hình cơ sở dữ liệu**:
   - Mở file `application.properties`.
   - Cập nhật thông tin kết nối database:
       ```properties
       spring.datasource.url=jdbc:mariadb://localhost:3306/works
       spring.datasource.username= {your username}
       spring.datasource.password= {your password}
       ```
3. **Chạy dự án**:
   Chạy dự án trên IDE.
4. **Truy cập**:
   - Mở trình duyệt và truy cập [http://localhost:8080](http://localhost:8080).
   - Tài khoản demo:
     - **USER:**
       -   Email: tqbao44@gmail.com
       - Password: 123
     - **ADMIN:**
       - Email: teo@gmail.com
       - Password: 123456
---

## Chức năng
### 1. Ứng vi
