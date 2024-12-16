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
- **Email**: Xác thực 2 yếu tố và được bật

### 2. Cách cài đặt dự án
1. **Clone repository**:
    ```bash
    git clone https://github.com/Bao44/www_lab_5.git
    ```
2. **Cấu trúc dự án**:

    ![Screenshot 2024-12-16 083220](https://github.com/user-attachments/assets/546d6596-f9a0-461a-b161-f0d8c2cac7a3)
   
4. **Cấu hình cơ sở dữ liệu**:
   - Mở file `application.properties`.
   - Cập nhật thông tin kết nối database:
       ```properties
       spring.datasource.url=jdbc:mariadb://localhost:3306/works
       spring.datasource.username= {your username}
       spring.datasource.password= {your password}

       spring.mail.host=smtp.gmail.com
       spring.mail.port=587
       spring.mail.username= {email của bạn}
       spring.mail.password= {mật khẩu xác thực 2 yếu tố}
       spring.mail.properties.mail.smtp.auth=true
       spring.mail.properties.mail.smtp.starttls.enable=true
       ```
3. **Chạy dự án**:
   Chạy dự án trên IDE.
4. **Truy cập**:
   - Mở trình duyệt và truy cập [http://localhost:8080](http://localhost:8080).
   - Tài khoản demo:
     - **Ứng viên:**
       -   Email: tqbao44@gmail.com
       - Password: 123
     - **Công ty:**
       - Email: teo@gmail.com
       - Password: 123456
---

## Chức năng

### 1. Ứng viên
- Đăng ký/Đăng nhập
- Xem và tìm các bài tuyển dụng
- Gợi ý các công việc phù hợp với kỹ năng
- Cập nhật trang cá nhân
- Ứng tuyển công việc

### 2. Công ty
- Đăng ký/Đăng nhập
- Đăng bài tuyển dụng
- Xem và tìm kiếm ứng viên
- Gợi ý các ứng viên phù hợp với công việc
- Gửi email mời ứng viên
  
---

## Giao diện


