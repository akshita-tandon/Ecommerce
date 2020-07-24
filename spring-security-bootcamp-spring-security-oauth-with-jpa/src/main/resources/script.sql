create table if not exists oauth_access_token (
  token_id VARCHAR(255),
  token LONG VARBINARY,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication LONG VARBINARY,
  refresh_token VARCHAR(255)
);

create table if not exists oauth_refresh_token (
  token_id VARCHAR(255),
  token LONG VARBINARY,
  authentication LONG VARBINARY
);

use pro;

desc address;
desc buyer;
desc cart;
desc cart_product_variations;
desc category;
desc oauth_access_token;
desc oauth_refresh_token;
desc order_class;
desc order_product;
desc order_status;
desc product;
desc product_review;
desc product_variation;
desc role;
desc seller;
desc seller_user_id;
desc sequence_table;
desc user;
desc users_role;


select * from address;
select * from buyer;
select * from cart;
select * from cart_product_variations;
select * from category;
select * from oauth_access_token;
select * from oauth_refresh_token;
select * from order_class;
select * from order_product;
select * from order_status;
select* from product;
select * from product_review;
select * from product_variation;
select * from role;
select * from seller;
select * from seller_user_id;
select * from sequence_table;
select * from user;
select * from users_role;

delete from address;
delete from buyer;
delete from cart;
delete from cart_product_variations;
delete from category;
delete from oauth_access_token;
delete from oauth_refresh_token;
delete from order_class;
delete from order_product;
delete from order_status;
delete from product;
delete from product_review;
delete from product_variation;
delete from role;
delete from seller;
delete from seller_user_id;
delete from sequence_table;
delete from user;
delete from users_role;


drop table address;
drop table buyer;
drop table cart;
drop table cart_product_variations;
drop table category;
drop table oauth_access_token;
drop table oauth_refresh_token;
drop table order_class;
drop table order_product;
drop table order_status;
drop table product;
drop table product_review;
drop table product_variation;
drop table role;
drop table seller;
drop table seller_user_id;
drop table sequence_table;
drop table user;
drop table users_role;