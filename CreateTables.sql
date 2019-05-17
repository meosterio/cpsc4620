CREATE TABLE Discounts
(
  Name  VARCHAR(30) NOT NULL,
  Percent_off FLOAT CHECK (Percent_off > 0 AND Percent_off < 100),
  Dollar_off  FLOAT,
  PRIMARY KEY (Name)
);

CREATE TABLE Base_Prices
(
  Size  VARCHAR(15) NOT NULL,
  Crust VARCHAR(15) NOT NULL,
  Price FLOAT NOT NULL CHECK (Price >= 0),
  Cost  FLOAT,
  PRIMARY KEY (Size, Crust)
);

CREATE TABLE Orders
(
  Order_Number INT NOT NULL,
  PRIMARY KEY (Order_Number)
);

CREATE TABLE Customer
(
  ID  INT NOT NULL,
  Phone_Number VARCHAR(10),
  Name VARCHAR(30),
  Address VARCHAR(50),
  PRIMARY KEY (ID)
);

CREATE TABLE Toppings
(
  Name VARCHAR(30) NOT NULL,
  Price FLOAT,
  Cost_per_unit FLOAT,
  Inventory INT,
  small FLOAT,
  medium FLOAT,
  large FLOAT,
  x_large FLOAT,
  PRIMARY KEY (Name)
);

CREATE TABLE Pizza
(
  Name VARCHAR(30) NOT NULL,
  Price FLOAT CHECK (Price >= 0),
  Cost FLOAT,
  Completion_Status BOOLEAN NOT NULL,
  Timestamp TIMESTAMP NOT NULL,
  Crust VARCHAR(15) NOT NULL,
  Size VARCHAR(15) NOT NULL,
  Order_Number INT NOT NULL,
  PRIMARY KEY (Name),
  FOREIGN KEY (Order_Number) REFERENCES Orders(Order_Number)
);

CREATE TABLE Dine_In
(
  Table_Number INT,
  Order_Number INT NOT NULL,
  PRIMARY KEY (Order_Number),
  FOREIGN KEY (Order_Number) REFERENCES Orders(Order_Number)
);

CREATE TABLE Seat_Number
(
  Seat INT NOT NULL,
  Order_Number INT NOT NULL,
  PRIMARY KEY (Order_Number, Seat),
  FOREIGN KEY (Order_Number) REFERENCES Dine_In(Order_Number)
);

CREATE TABLE Pickup
(
  Customer_ID INT NOT NULL,
  Order_Number INT NOT NULL,
  PRIMARY KEY (Order_Number),
  FOREIGN KEY (Order_Number) REFERENCES Orders(Order_Number),
  FOREIGN KEY (Customer_ID) REFERENCES Customer(ID)
);

CREATE TABLE Delivery
(
  Customer_ID INT NOT NULL,
  Order_Number INT NOT NULL,
  PRIMARY KEY (Order_Number),
  FOREIGN KEY (Order_Number) REFERENCES Orders(Order_Number),
  FOREIGN KEY (Customer_ID) REFERENCES Customer(ID)
);

CREATE TABLE Has_Topping
(
  Pizza_Name VARCHAR(30) NOT NULL,
  Topping_Name VARCHAR(30) NOT NULL,
  Extra BOOLEAN,
  Amount FLOAT,
  PRIMARY KEY (Pizza_Name, Topping_Name),
  FOREIGN KEY (Pizza_Name) REFERENCES Pizza(Name),
  FOREIGN KEY (Topping_Name) REFERENCES Toppings(Name)
);

CREATE TABLE Pizza_Discount
(
  Pizza_Name VARCHAR(30) NOT NULL,
  Discount_Name VARCHAR(30) NOT NULL,
  PRIMARY KEY (Pizza_Name, Discount_Name),
  FOREIGN KEY (Pizza_Name) REFERENCES Pizza(Name),
  FOREIGN KEY (Discount_Name) REFERENCES Discounts(Name)
);

CREATE TABLE Order_Discount
(
  Order_Number INT NOT NULL,
  Discount_Name VARCHAR(30) NOT NULL,
  PRIMARY KEY (Order_Number, Discount_Name),
  FOREIGN KEY (Order_Number) REFERENCES Orders(Order_Number),
  FOREIGN KEY (Discount_Name) REFERENCES Discounts(Name)
);
