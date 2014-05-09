DECLARE
   a number;
   x number;
   y number;
   b number;
   c number;
   d number;
   e_date date:= Sysdate;
   pr_date date:= Sysdate + 1/24;
   c_date date:= Sysdate + 1;
BEGIN
  
  INSERT INTO roles
  (name )
  VALUES
  ('ADMINISTRATOR');

  INSERT INTO roles
  (name )
  VALUES
  ('PE');

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
  ('USER');


   FOR a in 1 .. 1000 LOOP
		insert into users
		(name, email, password, role_id)
		values(CONCAT('user', a), concat('usr', concat(a, '@i.ua')), md5(concat(a,a)), 5);
  END LOOP;
  insert into users
 (name, email, password, role_id)
  values('engineer', concat('engineer', '@i.ua'), md5('pass'), 2);
  insert into users
 (name, email, password, role_id)
  values('supporter', concat('supporter', '@i.ua'), md5('pass'), 4);
  insert into users
 (name, email, password, role_id)
  values('iengineer', concat('iengineer', '@i.ua'), md5('pass'), 3);
  insert into users
 (name, email, password, role_id)
  values('admin', concat('admin', '@i.ua'), md5('pass'), 1);

  insert into provider_locations
  (pos_x, pos_y, name, address)
  values
  (50.467476, 30.413128, 'NyvkiLoc', 'Krasnodarska St, 32 Kyiv, Kyiv city, Ukraine');

  insert into provider_locations
  (pos_x, pos_y, name, address)
  values
  (50.422445, 30.468119, 'SolomyankaLoc', 'Maksyma Kryvonosa St, 14 Kyiv, Kyiv city, Ukraine');

  insert into provider_locations
  (pos_x, pos_y, name, address)
  values
  (50.395804, 30.644089, 'PoznyakiLoc', 'Larysy Rudenko St, 17 Kyiv, Kyiv city, Ukraine');

  insert into provider_locations
  (pos_x, pos_y, name, address)
  values
  (50.526232, 30.6020479, 'TroyeshchinaLoc', 'Radunska St, 32 Kyiv, Kyiv city, Ukraine');

  insert into provider_locations
  (pos_x, pos_y, name, address)
  values
  (50.501261, 30.766728, 'BrovaryLoc', '21A, Shchorsa St, 21А Brovary, Kyivska oblast, Ukraine');

  insert into services 
  (name)
  values 
  ('business internet');

  insert into services 
  (name)
  values 
  ('silver internet');

  insert into services 
  (name)
  values
 ('gold internet');

  insert into services 
  (name)
  values 
  ('plutinum internet');

  insert into services 
  ( name)
  values 
  ('unlimited internet');

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

   x :=50.467476;
   y :=30.413128;
   FOR a in 1 .. 200 LOOP
		x:= (x + 0.000050);
		y:= (y + 0.000100);
		insert into service_locations
		(pos_x, pos_y, name)
		values(x, y, concat('Location', a));
  END LOOP;

   x :=50.422445;
   y :=30.468119;
   FOR a in 201 .. 400 LOOP
		x:= (x + 0.000075);
		y:= (y + 0.000250);
		insert into service_locations
		(pos_x, pos_y, name)
		values(x, y, concat('Location', a));
  END LOOP;
  
   x :=50.395804;
   y :=30.644089;
   FOR a in 401 .. 600 LOOP
		x:= (x + 0.000050);
		y:= (y + 0.000200);
		insert into service_locations
		(pos_x, pos_y, name)
		values(x, y, concat('Location', a));
  END LOOP;

   x :=50.501261;
   y :=30.766728;
   FOR a in 601 .. 800 LOOP
		x:= (x + 0.000040);
		y:= (y - 0.000200);
		insert into service_locations
		(pos_x, pos_y, name)
		values(x, y, concat('Location', a));
  END LOOP;

   x :=50.526232;
   y :=30.602047;
   FOR a in 801 .. 1000 LOOP
		x:= (x - 0.000040);
		y:= (y + 0.000075);
		insert into service_locations
		(pos_x, pos_y, name)
		values(x, y, concat('Location', a));
  END LOOP;
  
  for a in 1..17 loop
    insert into devices (name)
    values (concat('Device', a));
    for b in 1..60 loop
      insert into ports (device_id, name)
      values (a, concat('Port', b));
    end loop;
  end loop;

   FOR a in 1 .. 70 LOOP
		e_date := Sysdate + (a/3)/24 - 60;
		pr_date := Sysdate + (a/3)/24 + 1/24 - 60;
		c_date := Sysdate + (a/3)/24 + 1/24 + 1 - 60;
		insert into service_orders
		(enterdate, procesdate, completedate, user_id, service_id, provider_location_id, service_location_id, status, scenario, service_instance_id, name)
		values
		(e_date, pr_date, c_date, a, 1, 1, a, 'COMPLETED', 'NEW', null, concat('Service_order', a));
		insert into service_instances
		(user_id, service_order_id, status, service_id, name)
		values
		(a, a, 'ACTIVE', 1, concat('Service_instance', a));
		insert into circuits 
		(service_instance_id, port_id, name)
		values 
		(a, a, concat('Circuit', a));
		insert into cables 
		(service_location_id, port_id, name)
		values 
		(a, a, concat('Cable', a));
		insert into tasks 
		(status, type, user_id, role_id, service_orders_id)
		values 
		('COMPLETED', 'CREATE_CIRCUIT', 1001, 2, a);
		insert into tasks 
		(status, type, user_id, role_id, service_orders_id)
		values 
		('COMPLETED', 'SEND_BILL', 1002, 4, a);
		update ports
		set free = 0 where id = a;
		update service_orders set service_instance_id = a where id = a;
  END LOOP;
  FOR a in 71 .. 140 LOOP
		e_date := Sysdate + 3 +(a/3)/24 - 60;
		pr_date := Sysdate + 3 + (a/3)/24 + 1/24 - 60;
		c_date := Sysdate + 3 + (a/3)/24 + 1/24 + 1 - 60;
		insert into service_orders
		(enterdate, procesdate, completedate, user_id, service_id, provider_location_id, service_location_id, status, scenario, service_instance_id, name)
		values
		(e_date, pr_date, c_date, a, 2, 1, a, 'COMPLETED', 'NEW', null, concat('Service_order', a));
		insert into service_instances
		(user_id, service_order_id, status, service_id, name)
		values
		(a, a, 'ACTIVE', 2, concat('Service_instance', a));
		insert into circuits 
		(service_instance_id, port_id, name)
		values 
		(a, a, concat('Circuit', a));
		insert into cables 
		(service_location_id, port_id, name)
		values 
		(a, a, concat('Cable', a));
		insert into tasks 
		(status, type, user_id, role_id, service_orders_id)
		values 
		('COMPLETED', 'SEND_BILL', 1002, 4, a);
		update ports
		set free = 0 where id = a;
		update service_orders set service_instance_id = a where id = a;
  END LOOP;
   FOR a in 141 .. 200 LOOP
		e_date := Sysdate + 8 +(a/3)/24 - 60;
		pr_date := Sysdate + 8 + (a/3)/24 + 1/24 - 60;
		c_date := Sysdate + 8 + (a/3)/24 + 1/24 + 1 - 60;
		insert into service_orders
		(enterdate, procesdate, completedate, user_id, service_id, provider_location_id, service_location_id, status, scenario, service_instance_id, name)
		values
		(e_date, pr_date, c_date, a, 3, 1, a, 'COMPLETED', 'NEW', null, concat('Service_order', a));
		insert into service_instances
		(user_id, service_order_id, status, service_id, name)
		values
		(a, a, 'ACTIVE', 3, concat('Service_instance', a));
		insert into circuits 
		(service_instance_id, port_id, name)
		values 
		(a, a, concat('Circuit', a));
		insert into cables 
		(service_location_id, port_id, name)
		values 
		(a, a, concat('Cable', a));
		insert into tasks 
		(status, type, user_id, role_id, service_orders_id)
		values 
		('COMPLETED', 'SEND_BILL', 1002, 4, a);
		update ports
		set free = 0 where id = a;
		update service_orders set service_instance_id = a where id = a;
  END LOOP;
  FOR a in 201 .. 270 LOOP
		e_date := Sysdate + 9 +(a)/24 - 60;
		pr_date := Sysdate + 9 + (a)/24 + 1/24 - 60;
		c_date := Sysdate + 9 + (a)/24 + 1/24 + 1 - 60;
		insert into service_orders
		(enterdate, procesdate, completedate, user_id, service_id, provider_location_id, service_location_id, status, scenario, service_instance_id, name)
		values
		(e_date, pr_date, c_date, a, 3, 2, a, 'COMPLETED', 'NEW', null, concat('Service_order', a));
		insert into service_instances
		(user_id, service_order_id, status, service_id, name)
		values
		(a, a, 'ACTIVE', 3, concat('Service_instance', a));
		insert into circuits 
		(service_instance_id, port_id, name)
		values 
		(a, a, concat('Circuit', a));
		insert into cables 
		(service_location_id, port_id, name)
		values 
		(a, a, concat('Cable', a));
		insert into tasks 
		(status, type, user_id, role_id, service_orders_id)
		values 
		('COMPLETED', 'SEND_BILL', 1002, 4, a);
		update ports
		set free = 0 where id = a;
		update service_orders set service_instance_id = a where id = a;
  END LOOP;
  FOR a in 271 .. 340 LOOP
		e_date := Sysdate + 20 +(a/3)/24 - 60;
		pr_date := Sysdate + 20 + (a/3)/24 + 1/24 - 60;
		c_date := Sysdate + 20 + (a/3)/24 + 1/24 + 1 - 60;
		insert into service_orders
		(enterdate, procesdate, completedate, user_id, service_id, provider_location_id, service_location_id, status, scenario, service_instance_id, name)
		values
		(e_date, pr_date, c_date, a, 4, 2, a, 'COMPLETED', 'NEW', null, concat('Service_order', a));
		insert into service_instances
		(user_id, service_order_id, status, service_id, name)
		values
		(a, a, 'ACTIVE', 4, concat('Service_instance', a));
		insert into circuits 
		(service_instance_id, port_id, name)
		values 
		(a, a, concat('Circuit', a));
		insert into cables 
		(service_location_id, port_id, name)
		values 
		(a, a, concat('Cable', a));
		insert into tasks 
		(status, type, user_id, role_id, service_orders_id)
		values 
		('COMPLETED', 'SEND_BILL', 1002, 4, a);
		update ports
		set free = 0 where id = a;
		update service_orders set service_instance_id = a where id = a;
  END LOOP;
  FOR a in 341 .. 400 LOOP
		e_date := Sysdate + 25 +(a/3)/24 - 60;
		pr_date := Sysdate + 25 + (a/3)/24 + 1/24 - 60;
		c_date := Sysdate + 25 + (a/3)/24 + 1/24 + 1 - 60;
		insert into service_orders
		(enterdate, procesdate, completedate, user_id, service_id, provider_location_id, service_location_id, status, scenario, service_instance_id, name)
		values
		(e_date, pr_date, c_date, a, 5, 3, a, 'COMPLETED', 'NEW', null, concat('Service_order', a));
		insert into service_instances
		(user_id, service_order_id, status, service_id, name)
		values
		(a, a, 'ACTIVE', 5, concat('Service_instance', a));
		insert into circuits 
		(service_instance_id, port_id, name)
		values 
		(a, a, concat('Circuit', a));
		insert into cables 
		(service_location_id, port_id, name)
		values 
		(a, a, concat('Cable', a));
		insert into tasks 
		(status, type, user_id, role_id, service_orders_id)
		values 
		('COMPLETED', 'SEND_BILL', 1002, 4, a);
		update ports
		set free = 0 where id = a;
		update service_orders set service_instance_id = a where id = a;
  END LOOP;
  FOR a in 401 .. 470 LOOP
		e_date := Sysdate + 28 +(a/3)/24 - 60;
		pr_date := Sysdate + 28 + (a/3)/24 + 1/24 - 60;
		c_date := Sysdate + 28 + (a/3)/24 + 1/24 + 1 - 60;
		insert into service_orders
		(enterdate, procesdate, completedate, user_id, service_id, provider_location_id, service_location_id, status, scenario, service_instance_id, name)
		values
		(e_date, pr_date, c_date, a, 1, 3, a, 'COMPLETED', 'NEW', null, concat('Service_order', a));
		insert into service_instances
		(user_id, service_order_id, status, service_id, name)
		values
		(a, a, 'ACTIVE', 1, concat('Service_instance', a));
		insert into circuits 
		(service_instance_id, port_id, name)
		values 
		(a, a, concat('Circuit', a));
		insert into cables 
		(service_location_id, port_id, name)
		values 
		(a, a, concat('Cable', a));
		insert into tasks 
		(status, type, user_id, role_id, service_orders_id)
		values 
		('COMPLETED', 'SEND_BILL', 1002, 4, a);
		update ports
		set free = 0 where id = a;
		update service_orders set service_instance_id = a where id = a;
  END LOOP;
  FOR a in 471 .. 540 LOOP
		e_date := Sysdate + 30 +(a/2)/24 - 60;
		pr_date := Sysdate + 30 + (a/2)/24 + 1/24 - 60;
		c_date := Sysdate + 30 + (a/2)/24 + 1/24 + 1 - 60;
		insert into service_orders
		(enterdate, procesdate, completedate, user_id, service_id, provider_location_id, service_location_id, status, scenario, service_instance_id, name)
		values
		(e_date, pr_date, c_date, a, 2, 3, a, 'COMPLETED', 'NEW', null, concat('Service_order', a));
		insert into service_instances
		(user_id, service_order_id, status, service_id, name)
		values
		(a, a, 'ACTIVE', 2, concat('Service_instance', a));
		insert into circuits 
		(service_instance_id, port_id, name)
		values 
		(a, a, concat('Circuit', a));
		insert into cables 
		(service_location_id, port_id, name)
		values 
		(a, a, concat('Cable', a));
		insert into tasks 
		(status, type, user_id, role_id, service_orders_id)
		values 
		('COMPLETED', 'SEND_BILL', 1002, 4, a);
		update ports
		set free = 0 where id = a;
		update service_orders set service_instance_id = a where id = a;
  END LOOP;
  FOR a in 541 .. 600 LOOP
		e_date := Sysdate + 32 +(a/3)/24 - 60;
		pr_date := Sysdate + 32 + (a/3)/24 + 1/24 - 60;
		c_date := Sysdate + 32 + (a/3)/24 + 1/24 + 1 - 60;
		insert into service_orders
		(enterdate, procesdate, completedate, user_id, service_id, provider_location_id, service_location_id, status, scenario, service_instance_id, name)
		values
		(e_date, pr_date, c_date, a, 3, 3, a, 'COMPLETED', 'NEW', null, concat('Service_order', a));
		insert into service_instances
		(user_id, service_order_id, status, service_id, name)
		values
		(a, a, 'ACTIVE', 3, concat('Service_instance', a));
		insert into circuits 
		(service_instance_id, port_id, name)
		values 
		(a, a, concat('Circuit', a));
		insert into cables 
		(service_location_id, port_id, name)
		values 
		(a, a, concat('Cable', a));
		insert into tasks 
		(status, type, user_id, role_id, service_orders_id)
		values 
		('COMPLETED', 'SEND_BILL', 1002, 4, a);
		update ports
		set free = 0 where id = a;
		update service_orders set service_instance_id = a where id = a;
  END LOOP;
  FOR a in 601 .. 670 LOOP
		e_date := Sysdate + 34 +(a/3)/24 - 60;
		pr_date := Sysdate + 34 + (a/3)/24 + 1/24 - 60;
		c_date := Sysdate + 34 + (a/3)/24 + 1/24 + 1 - 60;
		insert into service_orders
		(enterdate, procesdate, completedate, user_id, service_id, provider_location_id, service_location_id, status, scenario, service_instance_id, name)
		values
		(e_date, pr_date, c_date, a, 3, 4, a, 'COMPLETED', 'NEW', null, concat('Service_order', a));
		insert into service_instances
		(user_id, service_order_id, status, service_id, name)
		values
		(a, a, 'ACTIVE', 3, concat('Service_instance', a));
		insert into circuits 
		(service_instance_id, port_id, name)
		values 
		(a, a, concat('Circuit', a));
		insert into cables 
		(service_location_id, port_id, name)
		values 
		(a, a, concat('Cable', a));
		insert into tasks 
		(status, type, user_id, role_id, service_orders_id)
		values 
		('COMPLETED', 'SEND_BILL', 1002, 4, a);
		update ports
		set free = 0 where id = a;
		update service_orders set service_instance_id = a where id = a;
  END LOOP;
  FOR a in 671 .. 740 LOOP
		e_date := Sysdate + 40 +(a/3)/24 - 60;
		pr_date := Sysdate + 40 + (a/3)/24 + 1/24 - 60;
		c_date := Sysdate + 40 + (a/3)/24 + 1/24 + 1 - 60;
		insert into service_orders
		(enterdate, procesdate, completedate, user_id, service_id, provider_location_id, service_location_id, status, scenario, service_instance_id, name)
		values
		(e_date, pr_date, c_date, a, 4, 4, a, 'COMPLETED', 'NEW', null, concat('Service_order', a));
		insert into service_instances
		(user_id, service_order_id, status, service_id, name)
		values
		(a, a, 'ACTIVE', 4, concat('Service_instance', a));
		insert into circuits 
		(service_instance_id, port_id, name)
		values 
		(a, a, concat('Circuit', a));
		insert into cables 
		(service_location_id, port_id, name)
		values 
		(a, a, concat('Cable', a));
		insert into tasks 
		(status, type, user_id, role_id, service_orders_id)
		values 
		('COMPLETED', 'SEND_BILL', 1002, 4, a);
		update ports
		set free = 0 where id = a;
		update service_orders set service_instance_id = a where id = a;
  END LOOP;
  FOR a in 741 .. 800 LOOP
		e_date := Sysdate + 45 +(a/3)/24 - 60;
		pr_date := Sysdate + 45 + (a/3)/24 + 1/24 - 60;
		c_date := Sysdate + 45 + (a/3)/24 + 1/24 + 1 - 60;
		insert into service_orders
		(enterdate, procesdate, completedate, user_id, service_id, provider_location_id, service_location_id, status, scenario, service_instance_id, name)
		values
		(e_date, pr_date, c_date, a, 5, 4, a, 'COMPLETED', 'NEW', null, concat('Service_order', a));
		insert into service_instances
		(user_id, service_order_id, status, service_id, name)
		values
		(a, a, 'ACTIVE', 5, concat('Service_instance', a));
		insert into circuits 
		(service_instance_id, port_id, name)
		values 
		(a, a, concat('Circuit', a));
		insert into cables 
		(service_location_id, port_id, name)
		values 
		(a, a, concat('Cable', a));
		insert into tasks 
		(status, type, user_id, role_id, service_orders_id)
		values 
		('COMPLETED', 'SEND_BILL', 1002, 4, a);
		update ports
		set free = 0 where id = a;
		update service_orders set service_instance_id = a where id = a;
  END LOOP;
  FOR a in 801 .. 870 LOOP
		e_date := Sysdate + 48 +(a/3)/24 - 60;
		pr_date := Sysdate + 48 + (a/3)/24 + 1/24 - 60;
		c_date := Sysdate + 48 + (a/3)/24 + 1/24 + 1 - 60;
		insert into service_orders
		(enterdate, procesdate, completedate, user_id, service_id, provider_location_id, service_location_id, status, scenario, service_instance_id, name)
		values
		(e_date, pr_date, c_date, a, 2, 5, a, 'COMPLETED', 'NEW', null, concat('Service_order', a));
		insert into service_instances
		(user_id, service_order_id, status, service_id, name)
		values
		(a, a, 'ACTIVE', 2, concat('Service_instance', a));
		insert into circuits 
		(service_instance_id, port_id, name)
		values 
		(a, a, concat('Circuit', a));
		insert into cables 
		(service_location_id, port_id, name)
		values 
		(a, a, concat('Cable', a));
		insert into tasks 
		(status, type, user_id, role_id, service_orders_id)
		values 
		('COMPLETED', 'SEND_BILL', 1002, 4, a);
		update ports
		set free = 0 where id = a;
		update service_orders set service_instance_id = a where id = a;
  END LOOP;
  FOR a in 871 .. 940 LOOP
		e_date := Sysdate + 49 +(a/3)/24 - 60;
		pr_date := Sysdate + 49 + (a/3)/24 + 1/24 - 60;
		c_date := Sysdate + 49 + (a/3)/24 + 1/24 + 1 - 60;
		insert into service_orders
		(enterdate, procesdate, completedate, user_id, service_id, provider_location_id, service_location_id, status, scenario, service_instance_id, name)
		values
		(e_date, pr_date, c_date, a, 5, 5, a, 'COMPLETED', 'NEW', null, concat('Service_order', a));
		insert into service_instances
		(user_id, service_order_id, status, service_id, name)
		values
		(a, a, 'ACTIVE', 5, concat('Service_instance', a));
		insert into circuits 
		(service_instance_id, port_id, name)
		values 
		(a, a, concat('Circuit', a));
		insert into cables 
		(service_location_id, port_id, name)
		values 
		(a, a, concat('Cable', a));
		insert into tasks 
		(status, type, user_id, role_id, service_orders_id)
		values 
		('COMPLETED', 'SEND_BILL', 1002, 4, a);
		update ports
		set free = 0 where id = a;
		update service_orders set service_instance_id = a where id = a;
  END LOOP;
  FOR a in 941 .. 1000 LOOP
		e_date := Sysdate + 49 +(a/3)/24 - 60;
		pr_date := Sysdate + 49 + (a/3)/24 + 1/24 - 60;
		c_date := Sysdate + 49 + (a/3)/24 + 1/24 + 1 - 60;
		insert into service_orders
		(enterdate, procesdate, completedate, user_id, service_id, provider_location_id, service_location_id, status, scenario, service_instance_id, name)
		values
		(e_date, pr_date, c_date, a, 1, 1, a, 'COMPLETED', 'NEW', null, concat('Service_order', a));
		insert into service_instances
		(user_id, service_order_id, status, service_id, name)
		values
		(a, a, 'ACTIVE', 1, concat('Service_instance', a));
		insert into circuits 
		(service_instance_id, port_id, name)
		values 
		(a, a, concat('Circuit', a));
		insert into cables 
		(service_location_id, port_id, name)
		values 
		(a, a, concat('Cable', a));
		insert into tasks 
		(status, type, user_id, role_id, service_orders_id)
		values 
		('COMPLETED', 'SEND_BILL', 1002, 4, a);
		update ports
		set free = 0 where id = a;
		update service_orders set service_instance_id = a where id = a;
  END LOOP;
		FOR a in 1001 .. 1100 LOOP
		e_date := Sysdate + 55 +(a/3)/24 - 60;
		pr_date := Sysdate + 55 + (a/3)/24 + 1/24 - 60;
		c_date := Sysdate + 55 + (a/3)/24 + 1/24 + 1 - 60;
		b := a - 950 + (a - 1000);
		
		select services.id 
		into c
		from service_orders
		join services on service_orders.service_id = services.id
		where service_orders.id = b;
		
		select provider_locations.id 
		into d
		from service_orders
		join provider_locations on service_orders.provider_location_id = provider_locations.id
		where service_orders.id = b;
		
		insert into service_orders
		(enterdate, procesdate, completedate, user_id, service_id, provider_location_id, service_location_id, status, scenario , service_instance_id,  name)
		values
		(e_date, pr_date, c_date, b, c, d, b, 'COMPLETED', 'DISCONNECT', b, concat('Service_order', a));
		
		update service_instances
		set status = 'DISCONNECT' where id = b;
		
		delete circuits where service_instance_id = b;
		
		delete cables where	service_location_id = b;
		
		update ports
		set free = 1 where id = b;
		
  END LOOP;
  commit;
END;
