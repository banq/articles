CREATE TABLE owner (
  id   Integer IDENTITY PRIMARY KEY,
  name VARCHAR(80)
);
CREATE TABLE ordersample (
  id   Integer IDENTITY PRIMARY KEY,
);
CREATE TABLE order_item (
  ordersample_key Integer IDENTITY PRIMARY KEY,
  product_vo VARCHAR(80),
  qty INTEGER,
  ordersample INTEGER
);
CREATE TABLE address (
  id   Integer IDENTITY PRIMARY KEY,
  ordersample   Integer,
  street VARCHAR(80),
  zipcode VARCHAR(80)
);
CREATE TABLE Order_Owner(
  id   Integer IDENTITY PRIMARY KEY,
 owner_id Integer,
 ordersample Integer
)


