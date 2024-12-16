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

   **Sơ đồ Diagrams**

   ![Screenshot 2024-12-16 213118](https://github.com/user-attachments/assets/e085cd79-7b67-40e6-8614-f10cd9e1e78d)
   
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
 1. Giao diện khi vừa mở lên
    
    ![Screenshot 2024-12-16 100131](https://github.com/user-attachments/assets/cd4c3160-28c5-4677-a1bb-bf9f7504a170)
 
 2. Đăng nhập
    
    ![Screenshot 2024-12-16 100657](https://github.com/user-attachments/assets/6c2233b0-bb1c-4793-8153-2caeb70527b4)
 
 3. Đăng kí

    Đăng kí dưới quyền ứng viên
    
    ![Screenshot 2024-12-16 143905](https://github.com/user-attachments/assets/4de09744-9e2e-4e93-a467-fa49e46a196e)

    Đăng kí dưới quyền công ty

    ![Screenshot 2024-12-16 143935](https://github.com/user-attachments/assets/78dece2a-be11-4071-b490-ba9917e5a006)

---

 4. Đăng nhập với tư cách `ứng viên`
    
    4.1 Giao diện của ứng viên, hiển thị trước các công việc phù hợp với kỹ năng mình có

    ![Screenshot 2024-12-16 144408](https://github.com/user-attachments/assets/4b05441a-e10f-419d-92c0-9fc43bb101a8)
    
    4.2 Giao diện cá nhân gồm có các `thông tin cá nhân`, `kỹ năng`, `kinh nghiệm `

    ![Screenshot 2024-12-16 144524](https://github.com/user-attachments/assets/2ead4659-b95c-4cea-aae4-74e5d1145966)

    4.3 Thêm kỹ năng
    
    ![Screenshot 2024-12-16 144603](https://github.com/user-attachments/assets/67f7af44-1b7f-4970-8f9e-1451db9ff2a4)

    4.4 Cập nhật thông tin

    ![Screenshot 2024-12-16 144725](https://github.com/user-attachments/assets/45c9a599-5fa1-4cfc-9782-917efa06bff0)

    4.5 Tìm kiếm công việc khi nhập vào `UI/UX Designer` và nhấn tìm

    ![Screenshot 2024-12-16 144804](https://github.com/user-attachments/assets/d34457ef-0957-494b-96c3-0e124db7a58e)

    Kết quả tìm kiếm sẽ được trả về
    
    ![Screenshot 2024-12-16 144902](https://github.com/user-attachments/assets/bd92d87c-4f67-4b1b-85c8-5bcdbd570fa8)

    `Vẫn còn 1 số chức năng sẽ bổ sung thêm`

---

 5. Đăng nhập với tư cách `công ty`

    5.1 Giao diện của công ty, hiển thị các ứng viên
    
    ![Screenshot 2024-12-16 144959](https://github.com/user-attachments/assets/66490d57-49a9-4cbc-b7dd-5b390119173c)

    5.2 Trang quản lí

    ![Screenshot 2024-12-16 145048](https://github.com/user-attachments/assets/c1acfa1e-3ada-417e-99e8-dc328f158ab4)

    5.3 Danh sách các ứng viên

    ![Screenshot 2024-12-16 145126](https://github.com/user-attachments/assets/79416484-5321-4ab5-b20b-6d580857fd55)

    5.4 Thông tin của 1 ứng viên thi click vào xem, nếu cảm thấy ứng viên có những kỹ năng và kinh nghiệm phù hợp thì sẽ gửi mail đến ứng viên đó

    ![Screenshot 2024-12-16 145152](https://github.com/user-attachments/assets/2a13addc-f008-47f7-a960-3a2edf4bd0af)

    5.5 Mail được gửi về ứng viên

    ![Screenshot 2024-12-16 145957](https://github.com/user-attachments/assets/d1b39490-6bcd-46d2-832c-c79ffce2f492)

    5.6 Đăng bài tuyển dụng

    ![Screenshot 2024-12-17 030847](https://github.com/user-attachments/assets/25d4ba56-e2e8-452d-8446-4d0ffbeb9752)

    Sau khi đăng bài thành công thì sẽ hiển thị lên cho các ứng viên nhìn thấy

    ![Screenshot 2024-12-17 030538](https://github.com/user-attachments/assets/6c860cd9-3147-4b71-8f11-b7fe3a833a0a)

    `Vẫn còn 1 số chức năng chưa hoàn thành` 
    
