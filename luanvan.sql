use luanvan;

select * from category;
select * from province;

INSERT INTO category(id,name,parent_id,status) VALUES (1,'Cây trồng',0,1);
INSERT INTO category(id,name,parent_id,status) VALUES (2,'Sản phẩm tái sử dụng',0,1);
INSERT INTO category(id,name,parent_id,status) VALUES (3,'Sản phẩm dễ phân hủy',0,1);
INSERT INTO category(id,name,parent_id,status) VALUES (4,'Gia dụng',0,1);
INSERT INTO category(id,name,parent_id,status) VALUES (5,'Nội thất xanh',0,1);
INSERT INTO category(id,name,parent_id,status) VALUES (6,'Bách hóa hữu cơ',0,1);
INSERT INTO category(id,name,parent_id,status) VALUES (7,'Thời trang xanh',0,1);
INSERT INTO category(id,name,parent_id,status) VALUES (8,'Hộp đựng thực phẩm',3,1);
INSERT INTO category(id,name,parent_id,status) VALUES (9,'Bao bì',3,1);
INSERT INTO category(id,name,parent_id,status) VALUES (10,'Túi',3,1);
INSERT INTO category(id,name,parent_id,status) VALUES (11,'Ống hút',3,1);
INSERT INTO category(id,name,parent_id,status) VALUES (12,'Khay đĩa',4,1);
INSERT INTO category(id,name,parent_id,status) VALUES (13,'Thìa',4,1);
INSERT INTO category(id,name,parent_id,status) VALUES (14,'Bàn ghế',5,1);
INSERT INTO category(id,name,parent_id,status) VALUES (15,'Kệ tủ',5,1);
INSERT INTO category(id,name,parent_id,status) VALUES (16,'Chất tẩy rửa hữu cơ',6,1);
INSERT INTO category(id,name,parent_id,status) VALUES (17,'Xà phòng hữu cơ',6,1);
INSERT INTO category(id,name,parent_id,status) VALUES (18,'Than tre hoạt tính',6,1);
INSERT INTO category(id,name,parent_id,status) VALUES (19,'Giày sneaker',7,1);
select * from attribute;

INSERT INTO attribute(id,code,name,status) values (1,'color','màu sắc',1);
INSERT INTO attribute(id,code,name,status) values (2,'size','size',1);
INSERT INTO attribute(id,code,name,status) values (3,'number','kích cỡ',1);

INSERT INTO origin(id,name,status) VALUES (1,'Việt Nam',1);
INSERT INTO origin(id,name,status) VALUES (2,'Thái Lan',1);
INSERT INTO origin(id,name,status) VALUES (3,'Nhật Bản',1);
INSERT INTO origin(id,name,status) VALUES (4,'Philippines',1);
INSERT INTO origin(id,name,status) VALUES (5,'Lào',1);
INSERT INTO origin(id,name,status) VALUES (6,'Malaysia',1);
INSERT INTO origin(id,name,status) VALUES (7,'Singapore',1);


INSERT INTO material(id,name,status) VALUES (1,'Cỏ bàng',1);
INSERT INTO material(id,name,status) VALUES (2,'Tre',1);
INSERT INTO material(id,name,status) VALUES (3,'Giấy',1);
INSERT INTO material(id,name,status) VALUES (4,'Thủy tinh',1);
INSERT INTO material(id,name,status) VALUES (5,'Gạo',1);
INSERT INTO material(id,name,status) VALUES (6,'Thép không gỉ',1);
INSERT INTO material(id,name,status) VALUES (7,'vật liệu tái chế',1);

INSERT INTO user values(1,'trinhthenguyen123@gmail.com','123456');

