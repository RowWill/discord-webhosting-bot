insert into customer(id, discord_id, discord_username, email) values('cust-1', 'testDiscordId-1', 'Test User 1', 'test@test.com');
insert into customer(id, discord_id, discord_username, email) values('cust-2', 'testDiscordId-2', 'Test User 2', 'test@test.com');

insert into environment(id, ha_enabled, region, short_domain, ssl_enabled, issued_service_id) values(1, false, 'LON-1', 'testdomain-1', false, null);
insert into environment(id, ha_enabled, region, short_domain, ssl_enabled, issued_service_id) values(2, true, 'LON-1', 'testdomain-2', false, null);
insert into environment(id, ha_enabled, region, short_domain, ssl_enabled, issued_service_id) values(3, false, 'LON-1', 'testdomain-3', true, null);

insert into node(id, disklimit, display_name, extip, extipv6, fixed_cloudlets, flexible_cloudlets, node_group, node_type, restart_delay, scaling_mode, tag, issued_service_id)values(1, 50, 'Test Node 1', 0, 0, 1, 4, 'cp1', 1, 30, 1, 'test-os-tag', null);

insert into issued_service(id, api_type, description, instance_type, customer_id, environment_id, node_id) values(1, 1, 'Test service 1', 1, 'cust-1', 1, null);
insert into issued_service(id, api_type, description, instance_type, customer_id, environment_id, node_id) values(2, 1, 'Test service 2', 1, 'cust-1', 1, null);
insert into issued_service(id, api_type, description, instance_type, customer_id, environment_id, node_id) values(3, 1, 'Test service 3', 1, 'cust-2', 1, null);

update environment set issued_service_id = 1 where id = 1;
update environment set issued_service_id = 2 where id = 2;
update environment set issued_service_id = 3 where id = 3;

update node set issued_service_id = 2 where id = 1;

update issued_service set environment_id = 2 where id = 1;
update issued_service set node_id = 1 where id = 2;
update issued_service set environment_id = 3 where id = 3;