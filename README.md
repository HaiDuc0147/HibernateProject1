## Link video demo Youtube: https://www.youtube.com/watch?v=GNnEb0cZoiA
## Để chạy được đồ án, thầy cần tạo một cơ sở dữ liệu có tên StudentDB, username='postgres', password='zxcvbnmA8'
## Các tính năng:
1. (5d) Giáo vụ và sinh viên đăng nhập vào hệ thống, đăng xuất ra khỏi hệ thống.
Giáo vụ và sinh viên xem thông tin tài khoản của mình, thay đổi thông tin tài khoản, thay đổi
mật khẩu.
Các chức năng của giáo vụ
2. (5d) Xem danh sách, tìm kiếm tài khoản (account) giáo vụ. Thêm tài khoản giáo vụ
mới. Cập nhật thông tin tài khoản giáo vụ. Reset mật khẩu tài khoản giáo vụ. Xóa một tài
khoản giáo vụ khỏi hệ thống.
3. (5d) Xem danh sách, tìm kiếm môn học (subject). Thêm môn học mới. Cập nhật thông
tin môn học. Xóa môn học khỏi hệ thống. Thông tin môn học gồm: mã môn học, tên môn học,
số tín chỉ.
4. (5d) Xem danh sách các học kì (semester) mà hệ thống đang quản lí. Thêm một học
kì mới. Xóa một học kì. Set một học kì là học kì hiện tại, khi đó các thao tác phía sau sẽ tính
cho học kì đó. Một học kì gồm các thông tin: tên học kì (HK1, HK2, HK3), năm học, ngày bắt
đầu và kết thúc học kì đó.
5. (10d) Xem danh sách các lớp học / lớp sinh hoạt (class) mà hệ thống đang quản lí.
Thêm một lớp học mới. Xóa một lớp học. Cần hiển thị các thông tin cơ bản cho mỗi lớp học
như: tổng số sinh viên, tổng số nam, tổng số nữ trong từng lớp.
6. (10d) Xem danh sách, tìm kiếm sinh viên (student) trong một lớp học, đánh dấu x vào
các môn mà sinh viên có đăng kí học. Thêm một sinh viên mới vào lớp học. Cập nhật thông
tin sinh viên. Reset mật khẩu cho sinh viên.
7. (15d) Xem danh sách các kì đăng kí học phần (course registration session). Tạo một
kì đăng kí học phần mới cho học kì hiện tại, trong đó qui định ngày bắt đầu và kết thúc
đăng kí học phần.
8. (15d) Xem danh sách, tìm kiếm các học phần (course) được mở trong học kì hiện tại.
Thêm và xóa một học phần. Thông tin của mỗi học phần bao gồm: mã môn, tên môn, số tín
chỉ, giáo viên lí thuyết, tên phòng học, học ngày thứ mấy trong tuần, học ca thứ mấy trong
ngày (qui ước có 4 ca: 7:30 – 9:30, 9:30 – 11:30, 13:30 – 15:30 và 15:30 – 17:30), số slot tối
đa.
9. (10d) Xem danh sách, tìm kiếm sinh viên đăng kí trong một học phần. Danh sách này
cần có MSSV, họ tên, mã môn học, tên môn học, tên giáo viên lí thuyết, thời gian học, thời
gian sinh viên đăng kí học phần.
Các chức năng của sinh viên
10. (15d) Đăng kí học phần. Mỗi sinh viên đăng kí tối đa 8 môn. Sinh viên không được
đăng kí 2 môn trùng giờ.
11. (10d) Xem danh sách các học phần mà mình đã đăng kí.
12. (5d) Xóa một đăng kí học phần nếu còn hạn đăng kí.
## Điểm tự đánh giá: 110/100
## Phần trăm thảo khảo của đồ án: 10%, chủ yếu là các cách sửa lỗi từ StackoverFlow, hình ảnh, font chữ, định dạng, căn chỉnh text.
## Sau cùng, em xin dành lời cảm ơn chân thành tới những bài giảng của các thầy đã giúp em hoàn thành đồ án lần này!
