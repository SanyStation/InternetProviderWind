DECLARE
   a number;
   x number;
   y number;
   b number;
   c number;
   e_date date:= Sysdate;
   pr_date date:= Sysdate + 1/24;
   c_date date:= Sysdate + 1;
BEGIN
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


   FOR a in 1 .. 1000 LOOP
		insert into users
		(name, email, password, role_id)
		values(CONCAT('user', a), concat('usr', concat(a, '@i.ua')), concat(a,a), 5);
  END LOOP;
  insert into users
 (name, email, password, role_id)
  values('engineer', concat('engineer', '@i.ua'), 'pass', 2);
  insert into users
 (name, email, password, role_id)
  values('supporter', concat('supporter', '@i.ua'), 'pass', 4);

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
		(pos_x, pos_y)
		values(x, y);
  END LOOP;

   x :=50.422445;
   y :=30.468119;
   FOR a in 201 .. 400 LOOP
		x:= (x + 0.000075);
		y:= (y + 0.000250);
		insert into service_locations
		(pos_x, pos_y)
		values(x, y);
  END LOOP;
  
   x :=50.395804;
   y :=30.644089;
   FOR a in 401 .. 600 LOOP
		x:= (x + 0.000050);
		y:= (y + 0.000200);
		insert into service_locations
		(pos_x, pos_y)
		values(x, y);
  END LOOP;

   x :=50.501261;
   y :=30.766728;
   FOR a in 601 .. 800 LOOP
		x:= (x + 0.000040);
		y:= (y - 0.000200);
		insert into service_locations
		(pos_x, pos_y)
		values(x, y);
  END LOOP;

   x :=50.526232;
   y :=30.602047;
   FOR a in 801 .. 1000 LOOP
		x:= (x - 0.000040);
		y:= (y + 0.000075);
		insert into service_locations
		(pos_x, pos_y)
		values(x, y);
  END LOOP;
  
  for a in 1..17 loop
    insert into devices (id)
    values (a);
    for b in 1..60 loop
      c := (a * 60) - (60 - b);
      insert into ports (id, device_id)
      values (c, a);
    end loop;
  end loop;

   FOR a in 1 .. 70 LOOP
		insert into service_orders
		(enterdate, procesdate, completedate, user_id, service_id, provider_location_id, service_location_id, status, scenario, service_instance_id)
		values
		(e_date, pr_date, c_date, a, 1, 1, a, 'completed', 'New', null);
		insert into service_instances
		(user_id, service_order_id, status, service_id)
		values
		(a, a, 'active', 1);
		insert into circuits 
		(service_instance_id, port_id)
		values 
		(a, a);
		insert into cables 
		(service_location_id, port_id)
		values 
		(a, a);
		insert into tasks 
		(status, type, user_id, role_id, service_orders_id)
		values 
		('completed', 'create circuit', 1001, 2, a);
		insert into tasks 
		(status, type, user_id, role_id, service_orders_id)
		values 
		('completed', 'sending bill', 1002, 4, a);
		update ports
		set free = 0 where id = a;
  END LOOP;
  FOR a in 71 .. 140 LOOP
		insert into service_orders
		(enterdate, procesdate, completedate, user_id, service_id, provider_location_id, service_location_id, status, scenario, service_instance_id)
		values
		(e_date, pr_date, c_date, a, 2, 1, a, 'completed', 'New', null);
		insert into service_instances
		(user_id, service_order_id, status, service_id)
		values
		(a, a, 'active', 2);
		insert into circuits 
		(service_instance_id, port_id)
		values 
		(a, a);
		insert into cables 
		(service_location_id, port_id)
		values 
		(a, a);
		insert into tasks 
		(status, type, user_id, role_id, service_orders_id)
		values 
		('completed', 'create circuit', 1001, 2, a);
		insert into tasks 
		(status, type, user_id, role_id, service_orders_id)
		values 
		('completed', 'sending bill', 1002, 4, a);
		update ports
		set free = 0 where id = a;
  END LOOP;
   FOR a in 141 .. 200 LOOP
		insert into service_orders
		(enterdate, procesdate, completedate, user_id, service_id, provider_location_id, service_location_id, status, scenario, service_instance_id)
		values
		(e_date, pr_date, c_date, a, 3, 1, a, 'completed', 'New', null);
		insert into service_instances
		(user_id, service_order_id, status, service_id)
		values
		(a, a, 'active', 3);
		insert into circuits 
		(service_instance_id, port_id)
		values 
		(a, a);
		insert into cables 
		(service_location_id, port_id)
		values 
		(a, a);
		insert into tasks 
		(status, type, user_id, role_id, service_orders_id)
		values 
		('completed', 'create circuit', 1001, 2, a);
		insert into tasks 
		(status, type, user_id, role_id, service_orders_id)
		values 
		('completed', 'sending bill', 1002, 4, a);
		update ports
		set free = 0 where id = a;
  END LOOP;
  FOR a in 201 .. 270 LOOP
		insert into service_orders
		(enterdate, procesdate, completedate, user_id, service_id, provider_location_id, service_location_id, status, scenario, service_instance_id)
		values
		(e_date, pr_date, c_date, a, 3, 2, a, 'completed', 'New', null);
		insert into service_instances
		(user_id, service_order_id, status, service_id)
		values
		(a, a, 'active', 3);
		insert into circuits 
		(service_instance_id, port_id)
		values 
		(a, a);
		insert into cables 
		(service_location_id, port_id)
		values 
		(a, a);
		insert into tasks 
		(status, type, user_id, role_id, service_orders_id)
		values 
		('completed', 'create circuit', 1001, 2, a);
		insert into tasks 
		(status, type, user_id, role_id, service_orders_id)
		values 
		('completed', 'sending bill', 1002, 4, a);
		update ports
		set free = 0 where id = a;
  END LOOP;
  FOR a in 271 .. 340 LOOP
		insert into service_orders
		(enterdate, procesdate, completedate, user_id, service_id, provider_location_id, service_location_id, status, scenario, service_instance_id)
		values
		(e_date, pr_date, c_date, a, 4, 2, a, 'completed', 'New', null);
		insert into service_instances
		(user_id, service_order_id, status, service_id)
		values
		(a, a, 'active', 4);
		insert into circuits 
		(service_instance_id, port_id)
		values 
		(a, a);
		insert into cables 
		(service_location_id, port_id)
		values 
		(a, a);
		insert into tasks 
		(status, type, user_id, role_id, service_orders_id)
		values 
		('completed', 'create circuit', 1001, 2, a);
		insert into tasks 
		(status, type, user_id, role_id, service_orders_id)
		values 
		('completed', 'sending bill', 1002, 4, a);
		update ports
		set free = 0 where id = a;
  END LOOP;
  FOR a in 341 .. 400 LOOP
		insert into service_orders
		(enterdate, procesdate, completedate, user_id, service_id, provider_location_id, service_location_id, status, scenario, service_instance_id)
		values
		(e_date, pr_date, c_date, a, 5, 2, a, 'completed', 'New', null);
		insert into service_instances
		(user_id, service_order_id, status, service_id)
		values
		(a, a, 'active', 5);
		insert into circuits 
		(service_instance_id, port_id)
		values 
		(a, a);
		insert into cables 
		(service_location_id, port_id)
		values 
		(a, a);
		insert into tasks 
		(status, type, user_id, role_id, service_orders_id)
		values 
		('completed', 'create circuit', 1001, 2, a);
		insert into tasks 
		(status, type, user_id, role_id, service_orders_id)
		values 
		('completed', 'sending bill', 1002, 4, a);
		update ports
		set free = 0 where id = a;
  END LOOP;
  FOR a in 401 .. 470 LOOP
		insert into service_orders
		(enterdate, procesdate, completedate, user_id, service_id, provider_location_id, service_location_id, status, scenario, service_instance_id)
		values
		(e_date, pr_date, c_date, a, 1, 3, a, 'completed', 'New', null);
		insert into service_instances
		(user_id, service_order_id, status, service_id)
		values
		(a, a, 'active', 1);
		insert into circuits 
		(service_instance_id, port_id)
		values 
		(a, a);
		insert into cables 
		(service_location_id, port_id)
		values 
		(a, a);
		insert into tasks 
		(status, type, user_id, role_id, service_orders_id)
		values 
		('completed', 'create circuit', 1001, 2, a);
		insert into tasks 
		(status, type, user_id, role_id, service_orders_id)
		values 
		('completed', 'sending bill', 1002, 4, a);
		update ports
		set free = 0 where id = a;
  END LOOP;
  FOR a in 471 .. 540 LOOP
		insert into service_orders
		(enterdate, procesdate, completedate, user_id, service_id, provider_location_id, service_location_id, status, scenario, service_instance_id)
		values
		(e_date, pr_date, c_date, a, 2, 3, a, 'completed', 'New', null);
		insert into service_instances
		(user_id, service_order_id, status, service_id)
		values
		(a, a, 'active', 2);
		insert into circuits 
		(service_instance_id, port_id)
		values 
		(a, a);
		insert into cables 
		(service_location_id, port_id)
		values 
		(a, a);
		insert into tasks 
		(status, type, user_id, role_id, service_orders_id)
		values 
		('completed', 'create circuit', 1001, 2, a);
		insert into tasks 
		(status, type, user_id, role_id, service_orders_id)
		values 
		('completed', 'sending bill', 1002, 4, a);
		update ports
		set free = 0 where id = a;
  END LOOP;
  FOR a in 541 .. 600 LOOP
		insert into service_orders
		(enterdate, procesdate, completedate, user_id, service_id, provider_location_id, service_location_id, status, scenario, service_instance_id)
		values
		(e_date, pr_date, c_date, a, 3, 3, a, 'completed', 'New', null);
		insert into service_instances
		(user_id, service_order_id, status, service_id)
		values
		(a, a, 'active', 3);
		insert into circuits 
		(service_instance_id, port_id)
		values 
		(a, a);
		insert into cables 
		(service_location_id, port_id)
		values 
		(a, a);
		insert into tasks 
		(status, type, user_id, role_id, service_orders_id)
		values 
		('completed', 'create circuit', 1001, 2, a);
		insert into tasks 
		(status, type, user_id, role_id, service_orders_id)
		values 
		('completed', 'sending bill', 1002, 4, a);
		update ports
		set free = 0 where id = a;
  END LOOP;
  FOR a in 601 .. 670 LOOP
		insert into service_orders
		(enterdate, procesdate, completedate, user_id, service_id, provider_location_id, service_location_id, status, scenario, service_instance_id)
		values
		(e_date, pr_date, c_date, a, 3, 4, a, 'completed', 'New', null);
		insert into service_instances
		(user_id, service_order_id, status, service_id)
		values
		(a, a, 'active', 3);
		insert into circuits 
		(service_instance_id, port_id)
		values 
		(a, a);
		insert into cables 
		(service_location_id, port_id)
		values 
		(a, a);
		insert into tasks 
		(status, type, user_id, role_id, service_orders_id)
		values 
		('completed', 'create circuit', 1001, 2, a);
		insert into tasks 
		(status, type, user_id, role_id, service_orders_id)
		values 
		('completed', 'sending bill', 1002, 4, a);
		update ports
		set free = 0 where id = a;
  END LOOP;
  FOR a in 671 .. 740 LOOP
		insert into service_orders
		(enterdate, procesdate, completedate, user_id, service_id, provider_location_id, service_location_id, status, scenario, service_instance_id)
		values
		(e_date, pr_date, c_date, a, 4, 4, a, 'completed', 'New', null);
		insert into service_instances
		(user_id, service_order_id, status, service_id)
		values
		(a, a, 'active', 4);
		insert into circuits 
		(service_instance_id, port_id)
		values 
		(a, a);
		insert into cables 
		(service_location_id, port_id)
		values 
		(a, a);
		insert into tasks 
		(status, type, user_id, role_id, service_orders_id)
		values 
		('completed', 'create circuit', 1001, 2, a);
		insert into tasks 
		(status, type, user_id, role_id, service_orders_id)
		values 
		('completed', 'sending bill', 1002, 4, a);
		update ports
		set free = 0 where id = a;
  END LOOP;
  FOR a in 741 .. 800 LOOP
		insert into service_orders
		(enterdate, procesdate, completedate, user_id, service_id, provider_location_id, service_location_id, status, scenario, service_instance_id)
		values
		(e_date, pr_date, c_date, a, 5, 4, a, 'completed', 'New', null);
		insert into service_instances
		(user_id, service_order_id, status, service_id)
		values
		(a, a, 'active', 5);
		insert into circuits 
		(service_instance_id, port_id)
		values 
		(a, a);
		insert into cables 
		(service_location_id, port_id)
		values 
		(a, a);
		insert into tasks 
		(status, type, user_id, role_id, service_orders_id)
		values 
		('completed', 'create circuit', 1001, 2, a);
		insert into tasks 
		(status, type, user_id, role_id, service_orders_id)
		values 
		('completed', 'sending bill', 1002, 4, a);
		update ports
		set free = 0 where id = a;
  END LOOP;
  FOR a in 801 .. 870 LOOP
		insert into service_orders
		(enterdate, procesdate, completedate, user_id, service_id, provider_location_id, service_location_id, status, scenario, service_instance_id)
		values
		(e_date, pr_date, c_date, a, 1, 5, a, 'completed', 'New', null);
		insert into service_instances
		(user_id, service_order_id, status, service_id)
		values
		(a, a, 'active', 1);
		insert into circuits 
		(service_instance_id, port_id)
		values 
		(a, a);
		insert into cables 
		(service_location_id, port_id)
		values 
		(a, a);
		insert into tasks 
		(status, type, user_id, role_id, service_orders_id)
		values 
		('completed', 'create circuit', 1001, 2, a);
		insert into tasks 
		(status, type, user_id, role_id, service_orders_id)
		values 
		('completed', 'sending bill', 1002, 4, a);
		update ports
		set free = 0 where id = a;
  END LOOP;
  FOR a in 871 .. 940 LOOP
		insert into service_orders
		(enterdate, procesdate, completedate, user_id, service_id, provider_location_id, service_location_id, status, scenario, service_instance_id)
		values
		(e_date, pr_date, c_date, a, 2, 5, a, 'completed', 'New', null);
		insert into service_instances
		(user_id, service_order_id, status, service_id)
		values
		(a, a, 'active', 2);
		insert into circuits 
		(service_instance_id, port_id)
		values 
		(a, a);
		insert into cables 
		(service_location_id, port_id)
		values 
		(a, a);
		insert into tasks 
		(status, type, user_id, role_id, service_orders_id)
		values 
		('completed', 'create circuit', 1001, 2, a);
		insert into tasks 
		(status, type, user_id, role_id, service_orders_id)
		values 
		('completed', 'sending bill', 1002, 4, a);
		update ports
		set free = 0 where id = a;
  END LOOP;
  FOR a in 941 .. 1000 LOOP
		insert into service_orders
		(enterdate, procesdate, completedate, user_id, service_id, provider_location_id, service_location_id, status, scenario)
		values
		(e_date, pr_date, c_date, a, 5, 5, a, 'completed', 'New');
		insert into service_instances
		(user_id, service_order_id, status, service_id)
		values
		(a, a, 'active', 5);
		insert into circuits 
		(service_instance_id, port_id)
		values 
		(a, a);
		insert into cables 
		(service_location_id, port_id)
		values 
		(a, a);
		insert into tasks 
		(status, type, user_id, role_id, service_orders_id)
		values 
		('completed', 'create circuit', 1001, 2, a);
		insert into tasks 
		(status, type, user_id, role_id, service_orders_id)
		values 
		('completed', 'sending bill', 1002, 4, a);
		update ports
		set free = 0 where id = a;
  END LOOP;
END;