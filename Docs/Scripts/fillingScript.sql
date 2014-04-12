
INSERT INTO roles
(name )
VALUES
('Administrator');

INSERT INTO roles
(name )
VALUES
('PI');

INSERT INTO roles
(name )
VALUES
('IE');

INSERT INTO roles
(name )
VALUES
('CSE');

INSERT INTO roles
(name )
VALUES
('User');

DECLARE
   a number(1);
BEGIN
   FOR a in 1 .. 600 LOOP
		insert into users
		(name, email, password, role_id)
		values(CONCAT('user', a), concat('user', concat(a, '@i.ua')), concat(a,a), 5);
  END LOOP;
END;

insert into provider_locations
(pos_x, pos_y)
values
(50.467476, 30.413128);

insert into provider_locations
(pos_x, pos_y)
values
(50.422445, 30.468119);

insert into provider_locations
(pos_x, pos_y)
values
(50.395804, 30.644089);

insert into provider_locations
(pos_x, pos_y)
values
(50.526232, 30.6020479);

insert into provider_locations
(pos_x, pos_y)
values
(50.501261, 30.766728);

insert into services 
(name)
values ('business internet');

insert into services 
(name)
values ('silver internet');

insert into services 
(name)
values ('gold internet');

insert into services 
(name)
values ('plutinum internet');

insert into services 
(name)
values ('unlimited internet');

insert into prices
(provider_location_id, service_id, price)
values
(1, 1, 10);

insert into prices
(provider_location_id, service_id, price)
values
(1, 2, 20);

insert into prices
(provider_location_id, service_id, price)
values
(1, 3, 35);

insert into prices
(provider_location_id, service_id, price)
values
(2, 3, 30);

insert into prices
(provider_location_id, service_id, price)
values
(2, 4, 45);

insert into prices
(provider_location_id, service_id, price)
values
(2, 5, 55);


insert into prices
(provider_location_id, service_id, price)
values
(3, 1, 15);

insert into prices
(provider_location_id, service_id, price)
values
(3, 2, 30);

insert into prices
(provider_location_id, service_id, price)
values
(3, 3, 40);

insert into prices
(provider_location_id, service_id, price)
values
(4, 3, 40);

insert into prices
(provider_location_id, service_id, price)
values
(4, 4, 45);

insert into prices
(provider_location_id, service_id, price)
values
(4, 5, 80);


insert into prices
(provider_location_id, service_id, price)
values
(5, 1, 8);

insert into prices
(provider_location_id, service_id, price)
values
(5, 2, 25);

insert into prices
(provider_location_id, service_id, price)
values
(5, 5, 75);

DECLARE
   a number(1);
   x number :=50.467476;
   y number :=30.413128;
BEGIN
   FOR a in 1 .. 120 LOOP
		x:= (x + 0.000100);
		y:= (y + 0.000200);
		insert into service_locations
		(pos_x, pos_y)
		values(x, y);
  END LOOP;
END;

DECLARE
   a number(1);
   x number :=50.422445;
   y number :=30.468119;
BEGIN
   FOR a in 121 .. 240 LOOP
		x:= (x + 0.000150);
		y:= (y + 0.000500);
		insert into service_locations
		(pos_x, pos_y)
		values(x, y);
  END LOOP;
END;

DECLARE
   a number(1);
   x number :=50.395804;
   y number :=30.644089;
BEGIN
   FOR a in 241 .. 360 LOOP
		x:= (x + 0.000100);
		y:= (y + 0.000400);
		insert into service_locations
		(pos_x, pos_y)
		values(x, y);
  END LOOP;
END;


DECLARE
   a number(1);
   x number :=50.501261;
   y number :=30.766728;
BEGIN
   FOR a in 361 .. 480 LOOP
		x:= (x + 0.000080);
		y:= (y - 0.000400);
		insert into service_locations
		(pos_x, pos_y)
		values(x, y);
  END LOOP;
END;

DECLARE
   a number(1);
   x number :=50.526232;
   y number :=30.602047;
BEGIN
   FOR a in 481 .. 600 LOOP
		x:= (x - 0.000080);
		y:= (y + 0.000150);
		insert into service_locations
		(pos_x, pos_y)
		values(x, y);
  END LOOP;
END;

--DECLARE
--   a number(1);
--   e_date date:= Sysdate;
--   pr_date date:= Sysdate + 1/24;
--   c_date date:= Sysdate + 1;
--BEGIN
--   FOR a in 1 .. 40 LOOP
--		insert into service_orders
--		(id, enterdate, procesdate, completedate, user_id, service_id, provider_location_id, service_location_id, status, scenario, service_instance_id)
--		values(a, e_date, pr_date, c_date, a, 1, 1, a, 'completed', 'new', a);
--  END LOOP;
--END;