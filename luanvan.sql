use luanvan;

select * from material;
select * from province;

INSERT INTO category(id,name,parent_id,status) VALUES (1,'Cây trồng',0,1);
INSERT INTO category(id,name,parent_id,status) VALUES (2,'Gia dụng',0,1);
INSERT INTO category(id,name,parent_id,status) VALUES (3,'Bách hóa hữu cơ',0,1);
INSERT INTO category(id,name,parent_id,status) VALUES (8,'Hộp đựng thực phẩm',3,1);
INSERT INTO category(id,name,parent_id,status) VALUES (9,'Bao bì',4,1);
INSERT INTO category(id,name,parent_id,status) VALUES (10,'Túi',4,1);
INSERT INTO category(id,name,parent_id,status) VALUES (11,'Ống hút',4,1);
INSERT INTO category(id,name,parent_id,status) VALUES (12,'Khay đĩa',4,1);
INSERT INTO category(id,name,parent_id,status) VALUES (13,'Thìa',4,1);
INSERT INTO category(id,name,parent_id,status) VALUES (16,'Chất tẩy rửa hữu cơ',6,1);
INSERT INTO category(id,name,parent_id,status) VALUES (17,'Than tre hoạt tính',6,1);

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
INSERT INTO material(id,name,status) VALUES (8,'Thép không gỉ',1);
INSERT INTO material(id,name,status) VALUES (9,'Vải - sợi tự nhiên',1);
INSERT INTO material(id,name,status) VALUES (10,'Vật liệu tự nhiên',1);
INSERT INTO material(id,name,status) VALUES (20,'Vật liệu khác',1);

INSERT INTO user values(1,'trinhthenguyen123@gmail.com','123456');
INSERT INTO user values(2,'trinhthenguyen123@gmail.com','123456');

INSERT INTO order_status(name) values ('Đặt hàng'),
                                ('Cửa hàng duyệt'),
                                ('Đang vận chuyển'),
                                ('Đã thanh toán'),
                                ('Đã giao hàng'),
                                ('Khách hàng hủy'),
                                ('Cửa hàng hủy');

