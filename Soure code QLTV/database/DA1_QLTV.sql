use master
go
drop database DA1_QLTV
go
create database DA1_QLTV
go
use DA1_QLTV
Create table Admin(
	Username varchar(50) primary key,
	Password varchar(50),
	Ten nvarchar(50),
)
Create table SinhVien(
	MaSV varchar(50) primary key,
	Password varchar(50),
	HoTen nvarchar(50),
	NgaySinh date,
	GioiTinh bit,
	DiaChi nvarchar(50),
	SDT varchar(11),
	Email nvarchar(50)
)
Create table Sach(
	MaSach varchar(10) primary key,
	TenSach nvarchar(50),
	MaTheLoai varchar(10),
	TacGia nvarchar(50),
	SoLuong int,
	NXB nvarchar(50),
	NgayNhap date,
	NDTT nvarchar(100),
	Hinh nvarchar(50)
)
Create table TheLoaiSach(
	MaTheLoai varchar(10) primary key,
	TenTheLoai nvarchar(50),
	ViTri nvarchar(50),
)
Create table PhieuMuon(
	MaPhieuMuon int IDENTITY (1,1) primary key,
	MaSV varchar(50),
	MaSach varchar(10),
	SoLuong int,
	NgayMuon date DEFAULT GETDATE(),
	NgayHenTra date DEFAULT (GETDATE() + 10)
)

alter table PhieuMuon add CONSTRAINT FK_PM_MaSV FOREIGN KEY (MaSV) REFERENCES SinhVien (MaSV)
alter table Sach add CONSTRAINT FK_Sach_TheLoai FOREIGN KEY (MaTheLoai) REFERENCES TheLoaiSach (MaTheLoai)
alter table PhieuMuon add CONSTRAINT FK_PM_MaSach FOREIGN KEY (MaSach) REFERENCES Sach (MaSach)


-- Insert dữ liệu
-- Bảng Admin
Insert into Admin values('admin','admin','ADMIN')

-- Bảng SinhVien
Insert into SinhVien values ('PS001','123',N'Đỗ Bùi Quý','2/22/2000',0,N'Quận 1','0933662633','Quydbps00389@fpt.edu.vn')
Insert into SinhVien values ('PS002','123',N'Bùi Thị Kim Hà','2/2/2000',1,N'Quận 2','0930000000','HaKim@yahoo.com.vn')
Insert into SinhVien values ('PS003','123',N'Đỗ Huyền Trân','5/22/2000',1,N'Quận 3','0931111111','huyentran@yahoo.com.vn')
Insert into SinhVien values ('PS004','123',N'Đỗ Văn Phú','6/25/2000',0,N'Quận 4','0932222222','vanphu@yahoo.com.vn')
Insert into SinhVien values ('PS005','123',N'Bùi Thị Kim Hoa','1/12/2000',0,N'Quận 6','0933334444','kimhoa@yahoo.com.vn')
Insert into SinhVien values ('PS006','123',N'Nguyễn Thị Xuân','2/29/2000',0,N'Quận Tân Bình','0932156482','nguyenxuan@gmail.com')
Insert into SinhVien values ('PS007','123',N'Trương Mỹ Châu','4/15/2000',0,N'Quận 12','0935811169','chautruong@gmail.com')
Insert into SinhVien values ('PS008','123',N'Phan Tấn Trung','7/30/2000',0,N'Quận 9','0969169169','susan0175@gmail.com')
Insert into SinhVien values ('PS009','123',N'Bùi Viện','9/2/2000',0,N'Quận Thủ Đức','0938149159','vienbui@gmail.com')
Insert into SinhVien values ('PS010','123',N'Bùi Xuân Viết','3/28/2000',0,N'Quận 5','0989189189','xuanvietbui@gmail.com')

-- Bảng Thể Loại Sách
Insert into TheLoaiSach values ('MTL1',N'Truyện Tranh',N'Tủ số 1')
Insert into TheLoaiSach values ('MTL2',N'Ẩm thực',N'Tủ số 2')
Insert into TheLoaiSach values ('MTL3',N'Cổ tích',N'Tủ số 3')
Insert into TheLoaiSach values ('MTL4',N'Công nghệ thông tin',N'Tủ số 4')
Insert into TheLoaiSach values ('MTL5',N'Kỹ năng sống',N'Tủ số 5')
Insert into TheLoaiSach values ('MTL6',N'Kinh Tế',N'Tủ số 5')
Insert into TheLoaiSach values ('MTL7',N'Trẻ Em',N'Tủ số 6')
Insert into TheLoaiSach values ('MTL8',N'Trinh Thám',N'Tủ số 8')
Insert into TheLoaiSach values ('MTL9',N'Kinh dị',N'Tủ số 8')
Insert into TheLoaiSach values ('MTL10',N'Khám Phá Thế Giới',N'Tủ số 7')


-- Bảng Sách
Insert into Sach values ('MS1',N'Kingdom Vương giả thiên hạ','MTL1','Hara Yasuhisa',3,'NXB1','02/15/2019',N'abc','ms1.jpg')
Insert into Sach values ('MS2',N'Những món cơm ngon','MTL2',N'Tiêu Quỳnh',8,'NXB2','02/20/2019',N'abc','ms2.jpg')	
Insert into Sach values ('MS3',N'Truyện kể tây tạng','MTL3',N'Nhiều tác giả',1,'NXB3','02/22/2019',N'abc','ms3.jpg')
Insert into Sach values ('MS4',N'Giải thuật và lập trình','MTL4',N'Lê Minh Hoàng',7,'NXB4','02/24/2019',N'abc','ms4.jpg')
Insert into Sach values ('MS5',N'Biết hài lòng','MTL5','Leo Babauta',4,'NXB5','02/27/2019',N'abc','ms5.png')
Insert into Sach values ('MS6',N'Điểm mù','MTL6','Max H',2,'NXB1','02/27/2019',N'abc','ms6.jpg')
Insert into Sach values ('MS7',N'Kinh Tế Học','MTL6',N'Nhiều Tác Giả',6,N'NXB Đông A','02/27/2019',N'abc','ms7.jpg')
Insert into Sach values ('MS8',N'Đắc Nhân Tâm','MTL5','Dale Carnegie',10,N'NXB Tổng Hợp','02/27/2019',N'abc','ms8.jpg')
Insert into Sach values ('MS9',N'SherLock Holmes','MTL9','Arthur Conan Doyle',6,N'NXB Thế Giới','02/27/2019',N'abc','ms9.jpg')
Insert into Sach values ('MS10',N'Khám Phá Thế Giới Diệu Kì','MTL10','Marfé Ferguson Delano',7,N'NXB Văn Hóa','02/27/2019',N'abc','ms10.jpg')

---- Bảng Phiếu Mượn
--Insert into PhieuMuon (MaSV,MaSach,SoLuong) values ('PS001','MS1','1')
Insert into PhieuMuon (MaSV,MaSach,SoLuong,NgayMuon, NgayHenTra) values ('PS001','MS1','1','12/1/2019','12/11/2019')
Insert into PhieuMuon (MaSV,MaSach,SoLuong,NgayMuon, NgayHenTra) values ('PS002','MS2','2','12/5/2019','12/15/2019')
Insert into PhieuMuon (MaSV,MaSach,SoLuong,NgayMuon, NgayHenTra) values ('PS003','MS3','1','12/6/2019','12/16/2019')
Insert into PhieuMuon (MaSV,MaSach,SoLuong,NgayMuon, NgayHenTra) values ('PS004','MS4','1','12/11/2019','12/21/2019')
Insert into PhieuMuon (MaSV,MaSach,SoLuong,NgayMuon, NgayHenTra) values ('PS005','MS5','2','12/14/2019','12/24/2019')
Insert into PhieuMuon (MaSV,MaSach,SoLuong,NgayMuon, NgayHenTra) values ('PS006','MS6','1','12/15/2019','12/25/2019')
Insert into PhieuMuon (MaSV,MaSach,SoLuong,NgayMuon, NgayHenTra) values ('PS007','MS7','4','12/12/2019','12/22/2019')
Insert into PhieuMuon (MaSV,MaSach,SoLuong,NgayMuon, NgayHenTra) values ('PS008','MS8','2','12/2/2019','12/12/2019')
Insert into PhieuMuon (MaSV,MaSach,SoLuong,NgayMuon, NgayHenTra) values ('PS009','MS9','3','12/8/2019','12/18/2019')
Insert into PhieuMuon (MaSV,MaSach,SoLuong,NgayMuon, NgayHenTra) values ('PS010','MS10','5','12/10/2019','12/20/2019')
Insert into PhieuMuon (MaSV,MaSach,SoLuong,NgayMuon, NgayHenTra) values ('PS001','MS7','2','12/10/2019','12/20/2019')
Insert into PhieuMuon (MaSV,MaSach,SoLuong,NgayMuon, NgayHenTra) values ('PS001','MS6','1','12/10/2019','12/12/2019')
Insert into PhieuMuon (MaSV,MaSach,SoLuong,NgayMuon, NgayHenTra) values ('PS005','MS4','2','12/10/2019','12/20/2019')
Insert into PhieuMuon (MaSV,MaSach,SoLuong,NgayMuon, NgayHenTra) values ('PS004','MS10','2','12/10/2019','12/20/2019')
Insert into PhieuMuon (MaSV,MaSach,SoLuong,NgayMuon, NgayHenTra) values ('PS005','MS2','3','12/10/2019','12/20/2019')
Select * from SinhVien
Select * from Sach
Select * from PhieuMuon
Select * from TheLoaiSach
Select * from Sach where TenSach like '%D%'



-- Stored 1
go

CREATE PROC sp_MaTheLoaiSach(@MaTheLoai varchar(10)) 
AS BEGIN 
 	Select Tls.MaTheLoai, TenTheLoai, ViTri, MaSach, TenSach, SoLuong
	from TheLoaiSach tls join Sach s on tls.MaTheLoai = s.MaTheLoai 
	where tls.MaTheLoai = @MaTheLoai
	order by SoLuong desc
END 
go

exec sp_MaTheLoaiSach 'MTL5'

-- Stored 2
go

CREATE PROC sp_TheLoaiSach
AS BEGIN 
 	Select Tls.MaTheLoai, TenTheLoai, ViTri, MaSach, TenSach, SoLuong
	from TheLoaiSach tls join Sach s on tls.MaTheLoai = s.MaTheLoai 
	order by SoLuong desc
END 
go

exec sp_TheLoaiSach 
-- Stored 3
go

CREATE PROC sp_ViTriTheLoaiSach(@ViTri nvarchar(50)) 
AS BEGIN 
 	Select Tls.MaTheLoai, TenTheLoai, ViTri, MaSach, TenSach, SoLuong
	from TheLoaiSach tls join Sach s on tls.MaTheLoai = s.MaTheLoai 
	where ViTri = @ViTri
	order by SoLuong desc
END 
go

exec sp_ViTriTheLoaiSach  'Tu so 2'
	


Select SUM(SoLuong) 
	from Sach
	where MaTheLoai = 'MTL5'
--Stored 4
go

	CREATE PROC sp_soSachTLS(@MaTheLoai varchar(10)) 
AS BEGIN 
 	Select SUM(SoLuong) tongSachTLS
	from Sach
	where MaTheLoai = @MaTheLoai
END 
go
exec sp_soSachTLS  'MTL6'
--Stored 5	
go

	CREATE PROC sp_soSachViTriTLS(@ViTri nvarchar(50)) 
AS BEGIN 
 	Select SUM(SoLuong) tongSachViTriTLS
from TheLoaiSach tls join Sach s on tls.MaTheLoai = s.MaTheLoai 
	where ViTri = @ViTri
END 
go
exec sp_soSachViTriTLS  'Tu so 5'
--Stored 6
go

	CREATE PROC sp_soTheLoaiTLS(@MaTheLoai varchar(10)) 
AS BEGIN 
 	Select COUNT(tls.MaTheLoai) soTheLoaiTLS
from TheLoaiSach tls join Sach s on tls.MaTheLoai = s.MaTheLoai 
	where tls.MaTheLoai = @MaTheLoai
END 
go
exec sp_soTheLoaiTLS  'MTL6'
--Stored 7
go
	CREATE PROC sp_soTheLoaiViTriTLS(@ViTri nvarchar(50)) 
AS BEGIN 
 	Select count(MaTheLoai) soTheLoaiViTriTLS
	from TheLoaiSach 
	where ViTri = @ViTri
END 
go
exec sp_soTheLoaiViTriTLS  'Tu so 5'
--Stored 8
go
	CREATE PROC sp_TongTheLoaiTLS
AS BEGIN 
 	Select count(MaTheLoai) TongTheLoaiTLS from TheLoaiSach
END 
go
exec sp_TongTheLoaiTLS
--Stored 8
go
	CREATE PROC sp_GioiTinhSV(@GioiTinh bit)
AS BEGIN 
 	Select * from SinhVien where GioiTinh = @GioiTinh
END 
go
exec sp_GioiTinhSV 0
--Stored 9
go
	CREATE PROC sp_tongGioiTinhSV(@GioiTinh bit)
AS BEGIN 
 	Select COUNT(GioiTinh) tongGioiTinhSV
	from SinhVien where GioiTinh = @GioiTinh
END 
go
exec sp_tongGioiTinhSV 1
--Stored 10
go
	CREATE PROC sp_tongSV
AS BEGIN 
 	Select COUNT(MaSV) tongSV
	from SinhVien 
END 
go
exec sp_tongSV
--Stored 11
go
	CREATE PROC sp_NgaySachGiam(@ngayBD varchar(50), @ngayKT varchar(50))
AS BEGIN 
 	Select * from Sach
	where ngaynhap >= @ngayBD and ngaynhap <= @ngayKT
	order by SoLuong desc
END 
go
exec sp_NgaySachGiam '2019-02-15', '2019-02-22'
--Stored 12
go
	CREATE PROC sp_NgaySachTang(@ngayBD varchar(50), @ngayKT varchar(50))
AS BEGIN 
 	Select * from Sach
	where ngaynhap >= @ngayBD and ngaynhap <= @ngayKT
	order by SoLuong asc
END 
go
exec sp_NgaySachTang '2019-02-15', '2019-02-22'
--Stored 13
go
	CREATE PROC sp_TongSachNgaySach(@ngayBD varchar(50), @ngayKT varchar(50))
AS BEGIN 
 	Select sum(SoLuong) TongNgaySach
	from Sach
	where ngaynhap >= @ngayBD and ngaynhap <= @ngayKT
END 
go
exec sp_TongSachNgaySach '2019-02-15', '2019-02-22'
--Stored 14
go
	CREATE PROC sp_TongSach
AS BEGIN 
 	Select sum(SoLuong) TongSach from Sach
END 
go
exec sp_TongSach
--Stored 15
go
	CREATE PROC sp_PhieuMuon
AS BEGIN 
 	Select MaPhieuMuon, pm.MaSV, sv.HoTen, pm.MaSach, s.TenSach, pm.SoLuong, NgayMuon, NgayHenTra
	from PhieuMuon pm join SinhVien sv on pm.MaSV = sv.MaSV
	join Sach s on pm.MaSach = s.MaSach
END 
go
exec sp_PhieuMuon
-- Stored 16
go

CREATE PROC sp_MaSVPM(@MaSV varchar(50)) 
AS BEGIN 
 	Select MaPhieuMuon, pm.MaSV, sv.HoTen, pm.MaSach, s.TenSach, pm.SoLuong, NgayMuon, NgayHenTra
	from PhieuMuon pm join SinhVien sv on pm.MaSV = sv.MaSV
	join Sach s on pm.MaSach = s.MaSach
	where sv.MaSV = @MaSV
END 
go
exec sp_MaSVPM 'PS001'
-- Stored 17
go

CREATE PROC sp_soSachSVPM(@MaSV varchar(50)) 
AS BEGIN 
 	Select sum(SoLuong) soSachSVPM, MaSV from PhieuMuon
	where MaSV = @MaSV
	group by MaSV
END 
go
exec sp_soSachSVPM 'PS001'
-- Stored 18
go

CREATE PROC sp_MaSachPM(@MaSach varchar(10)) 
AS BEGIN 
 	Select MaPhieuMuon, pm.MaSV, sv.HoTen, pm.MaSach, s.TenSach, pm.SoLuong, NgayMuon, NgayHenTra
	from PhieuMuon pm join SinhVien sv on pm.MaSV = sv.MaSV
	join Sach s on pm.MaSach = s.MaSach
	where s.MaSach = @MaSach
END 
go
exec sp_MaSachPM 'MS5'
-- Stored 19
go
CREATE PROC sp_soSVMuonSachPM(@MaSach varchar(10)) 
AS BEGIN 
 	Select COUNT(MaSV) tongSVMuonSach
	from PhieuMuon
	where MaSach = @MaSach
END 
go
exec sp_soSVMuonSachPM 'MS10'
-- Stored 20
go
	CREATE PROC sp_TongSachSVMuon
AS BEGIN 
 	Select sum(SoLuong) TongSachSVMuon from PhieuMuon
END 
go
exec sp_TongSachSVMuon
-- Stored 21
go
CREATE PROC sp_SVMuonNhieuSachNhat
AS BEGIN 
 	Select  pm.MaSV, sv.HoTen, sum(pm.SoLuong) SVMuonNhieuSachNhat
	from PhieuMuon pm join SinhVien sv on pm.MaSV = sv.MaSV
	join Sach s on pm.MaSach = s.MaSach
	group by pm.MaSV, sv.HoTen
	order by sum(pm.SoLuong) desc
END 
go
exec sp_SVMuonNhieuSachNhat
-- Stored 22
go
CREATE PROC sp_SVMuonItSachNhat
AS BEGIN 
 	Select  pm.MaSV, sv.HoTen, sum(pm.SoLuong) SVMuonItSachNhat
	from PhieuMuon pm join SinhVien sv on pm.MaSV = sv.MaSV
	join Sach s on pm.MaSach = s.MaSach
	group by pm.MaSV, sv.HoTen
	order by sum(pm.SoLuong) asc
END 
go
exec sp_SVMuonItSachNhat
-- Stored 23
go
CREATE PROC sp_SachMuonNhieuNhat
AS BEGIN 
 	Select  pm.MaSach, s.TenSach, sum(pm.SoLuong) SachMuonNhieuNhat
	from PhieuMuon pm join Sach s on pm.MaSach = s.MaSach
	group by pm.MaSach,  s.TenSach
	order by sum(pm.SoLuong) desc
END 
go
exec sp_SachMuonNhieuNhat
-- Stored 24
go
CREATE PROC sp_SachMuonItNhat
AS BEGIN 
 	Select  pm.MaSach, s.TenSach, sum(pm.SoLuong) SachMuonItNhat
	from PhieuMuon pm join Sach s on pm.MaSach = s.MaSach
	group by pm.MaSach,  s.TenSach
	order by sum(pm.SoLuong) asc
END 
go
exec sp_SachMuonItNhat
-- Stored 25
go
	CREATE PROC sp_SVConHanTraSach
AS BEGIN 
 	Select MaPhieuMuon, pm.MaSV, sv.HoTen, pm.MaSach, s.TenSach, pm.SoLuong, NgayMuon, NgayHenTra
	from PhieuMuon pm join SinhVien sv on pm.MaSV = sv.MaSV
	join Sach s on pm.MaSach = s.MaSach
	where NgayHenTra > (select GETDATE())
END 
go
exec sp_SVConHanTraSach
-- Stored 26
go
	CREATE PROC sp_SVQuaHanTraSach
AS BEGIN 
 	Select MaPhieuMuon, pm.MaSV, sv.HoTen, pm.MaSach, s.TenSach, pm.SoLuong, NgayMuon, NgayHenTra
	from PhieuMuon pm join SinhVien sv on pm.MaSV = sv.MaSV
	join Sach s on pm.MaSach = s.MaSach
	where NgayHenTra < (select GETDATE())
END 
go
exec sp_SVQuaHanTraSach
-- Stored 27
go
	CREATE PROC sp_TongSVConHanTraSach
AS BEGIN 
 	Select COUNT(DISTINCT MaSV) TongSVConHanTraSach 
	from PhieuMuon
	where NgayHenTra > (select GETDATE())
END 
go
exec sp_TongSVConHanTraSach
-- Stored 28
go
	CREATE PROC sp_TongSVQuaHanTraSach
AS BEGIN 
 	Select COUNT(DISTINCT MaSV) TongSVQuaHanTraSach
	from PhieuMuon
	where NgayHenTra < (select GETDATE())
END 
go
exec sp_TongSVQuaHanTraSach
-- Stored 29
go
	CREATE PROC sp_TongSoPhieuConHanTraSach
AS BEGIN 
 	Select COUNT(MaPhieuMuon) TongSoPhieuConHanTraSach
	from PhieuMuon
	where NgayHenTra > (select GETDATE())
END 
go
exec sp_TongSoPhieuConHanTraSach
-- Stored 30
go
	CREATE PROC sp_TongSoPhieuQuaHanTraSach
AS BEGIN 
 	Select COUNT(MaPhieuMuon) TongSoPhieuQuaHanTraSach
	from PhieuMuon
	where NgayHenTra < (select GETDATE())
END 
go
exec sp_TongSoPhieuQuaHanTraSach
-- Stored 31
go
	CREATE PROC sp_TongPM
AS BEGIN 
 	Select COUNT(MaPhieuMuon) TongPM
	from PhieuMuon
END 
go
exec sp_TongPM

-- Stored 32
go
	CREATE PROC sp_TongSoPhieuQuaHanTraSach
AS BEGIN 
 	Select COUNT(MaPhieuMuon) TongSoPhieuQuaHanTraSach
	from PhieuMuon
	where NgayHenTra < (select GETDATE())
END 
go
exec sp_TongSoPhieuQuaHanTraSach
-- Stored 33
go
	CREATE PROC sp_SVChuaMuonSach
AS BEGIN 
 	Select sv.MaSV, Password, HoTen, NgaySinh, GioiTinh, DiaChi, SDT, Email
	from SinhVien sv full join PhieuMuon pm on sv.MaSV = pm.MaSV
	where pm.MaSV IS NULL
END 
go
exec sp_SVChuaMuonSach
-- Stored 34
go
	CREATE PROC sp_SVDaMuonSach
AS BEGIN 
 	Select DISTINCT pm.MaSV, Password, HoTen, NgaySinh, GioiTinh, DiaChi, SDT, Email
	from SinhVien sv join PhieuMuon pm on sv.MaSV = pm.MaSV
END 
go
exec sp_SVDaMuonSach
-- Stored 35
go
	CREATE PROC sp_TongSVChuaMuonSach
AS BEGIN 
 	Select COUNT(sv.MaSV) TongSVChuaMuonSach
	from SinhVien sv full join PhieuMuon pm on sv.MaSV = pm.MaSV
	where pm.MaSV IS NULL
END 
go
exec sp_TongSVChuaMuonSach
-- Stored 36
go
	CREATE PROC sp_TongSVDaMuonSach
AS BEGIN 
 	Select COUNT(DISTINCT pm.MaSV) TongSVDaMuonSach
	from SinhVien sv join PhieuMuon pm on sv.MaSV = pm.MaSV
END 
go
exec sp_TongSVDaMuonSach
-- Stored 37
go
CREATE PROC sp_SachMuonNhieuNhatSVUI
AS BEGIN 
 	Select  pm.MaSach, s.TenSach, sum(pm.SoLuong) SachMuonNhieuNhatSVUI
	from PhieuMuon pm join Sach s on pm.MaSach = s.MaSach
	group by pm.MaSach,  s.TenSach
	order by sum(pm.SoLuong) desc
END 
go
exec sp_SachMuonNhieuNhat
-- Stored 38
go
CREATE PROC sp_SachMuonItNhatSVUI
AS BEGIN 
 	Select  pm.MaSach, s.TenSach, sum(pm.SoLuong) SachMuonItNhatSVUI
	from PhieuMuon pm join Sach s on pm.MaSach = s.MaSach
	group by pm.MaSach,  s.TenSach
	order by sum(pm.SoLuong) asc
END 
go
exec sp_SachMuonItNhatSVUI