INSERT INTO Discounts
VALUES ('Employee', 15, NULL);

INSERT INTO Discounts
VALUES ('Lunch Special Medium', NULL, 1);

INSERT INTO Discounts
VALUES ('Lunch Special Large', NULL, 2);

INSERT INTO Discounts
VALUES ('Specialty Pizza', NULL, 1.5);

INSERT INTO Discounts
VALUES ('Gameday Special', 20, NULL);

INSERT INTO Base_Prices
VALUES ('small', 'Thin', 3, 0.5);

INSERT INTO Base_Prices
VALUES ('small', 'Original', 3, 0.75);

INSERT INTO Base_Prices
VALUES ('small', 'Pan', 3.5, 1);

INSERT INTO Base_Prices
VALUES ('small', 'Gluten-Free', 4, 2);

INSERT INTO Base_Prices
VALUES ('medium', 'Thin', 5, 1);

INSERT INTO Base_Prices
VALUES ('medium', 'Original', 5, 1.5);

INSERT INTO Base_Prices
VALUES ('medium', 'Pan', 6, 2.25);

INSERT INTO Base_Prices
VALUES ('medium', 'Gluten-Free', 6.25, 3);

INSERT INTO Base_Prices
VALUES ('Large', 'Thin', 8, 1.25);

INSERT INTO Base_Prices
VALUES ('Large', 'Original', 8, 2);

INSERT INTO Base_Prices
VALUES ('Large', 'Pan', 9, 3);

INSERT INTO Base_Prices
VALUES ('Large', 'Gluten-Free', 9.5, 4);

INSERT INTO Base_Prices
VALUES ('X-Large', 'Thin', 10, 2);

INSERT INTO Base_Prices
VALUES ('X-Large', 'Original', 10, 3);

INSERT INTO Base_Prices
VALUES ('X-Large', 'Pan', 11.5, 4.5);

INSERT INTO Base_Prices
VALUES ('X-Large', 'Gluten-Free', 12.5, 6);

INSERT INTO Toppings
VALUES ('Pepperoni', 1.25, 0.2, 100, 2, 2.75, 3.5, 4.5);

INSERT INTO Toppings
VALUES ('Sausage', 1.25, 0.15, 100, 2.5, 3, 3.5, 4.25);

INSERT INTO Toppings
VALUES ('Ham', 1.5, 0.15, 78, 2, 2.5, 3.25, 4);

INSERT INTO Toppings
VALUES ('Chicken', 1.75, 0.25, 56, 1.5, 2, 2.25, 3);

INSERT INTO Toppings
VALUES ('Green Pepper', 0.5, 0.02, 79, 1, 1.5, 2, 2.25);

INSERT INTO Toppings
VALUES ('Onion', 0.5, 0.02, 85, 1, 1.5, 2, 2.75);

INSERT INTO Toppings
VALUES ('Roma tomato', 0.75, 0.03, 86, 2, 3, 3.5, 4.5);

INSERT INTO Toppings
VALUES ('Mushrooms', 0.75, 0.1, 52, 1.5, 2, 2.5, 3);

INSERT INTO Toppings
VALUES ('Black Olives', 0.6, 0.1, 39, 0.75, 1, 1.5, 2);

INSERT INTO Toppings
VALUES ('Pineapple', 1, 0.25, 15, 1, 1.25, 1.75, 2);

INSERT INTO Toppings
VALUES ('Jalapenos', 0.5, 0.05, 64, 0.5, .75, 1.25, 1.75);

INSERT INTO Toppings
VALUES ('Banana Peppers', 0.5, 0.05, 36, 0.6, 1, 1.3, 1.75);

INSERT INTO Toppings
VALUES ('Regular Cheese', 1.5, 0.12, 250, 2, 3.5, 5, 7);

INSERT INTO Toppings
VALUES ('Four Cheese Blend', 2, 0.15, 150, 2, 3.5, 5, 7);

INSERT INTO Toppings
VALUES ('Feta Cheese', 2, 0.18, 75, 1.75, 3, 4, 5.5);

INSERT INTO Toppings
VALUES ('Goat Cheese', 2, 0.2, 54, 1.6, 2.75, 4, 5.5);

INSERT INTO Toppings
VALUES ('Bacon', 1.5, 0.25, 89, 1, 1.5, 2, 3);


INSERT INTO Orders
VALUES (1);

INSERT INTO Pizza
VALUES ('Pizza 1', 13.50, 3.68, true, '2019-03-05 12:03:00', 'Thin', 'Large', 1);

INSERT INTO Dine_In
VALUES (14, 1);

INSERT INTO Has_Topping
VALUES ('Pizza 1', 'Regular Cheese', true, 5);

INSERT INTO Has_Topping
VALUES ('Pizza 1', 'Pepperoni', false, 3.5);

INSERT INTO Has_Topping
VALUES ('Pizza 1', 'Sausage', false, 3.5);

INSERT INTO Pizza_Discount
VALUES ('Pizza 1', 'Lunch Special Large');

INSERT INTO Seat_Number
VALUES (1,1);

INSERT INTO Seat_Number
VALUES (2,1);

INSERT INTO Seat_Number
VALUES (3,1);


INSERT INTO Orders
VALUES (2);

INSERT INTO Pizza
VALUES ('Pizza 2', 10.60, 3.23, true, '2019-03-03 12:05:00', 'Pan', 'medium', 2);

INSERT INTO Dine_In
VALUES (4, 2);

INSERT INTO Has_Topping
VALUES ('Pizza 2', 'Feta Cheese', false, 3);

INSERT INTO Has_Topping
VALUES ('Pizza 2', 'Black Olives', false, 1);

INSERT INTO Has_Topping
VALUES ('Pizza 2', 'Roma tomato', false, 3);

INSERT INTO Has_Topping
VALUES ('Pizza 2', 'Mushrooms', false, 2);

INSERT INTO Has_Topping
VALUES ('Pizza 2', 'Banana Peppers', false, 1);

INSERT INTO Pizza_Discount
VALUES ('Pizza 2', 'Lunch Special Medium');

INSERT INTO Pizza_Discount
VALUES ('Pizza 2', 'Specialty Pizza');


INSERT INTO Orders
VALUES (3);

INSERT INTO Pizza
VALUES ('Pizza 3', 6.75, 1.40, true, '2019-03-03 12:05:00', 'Original', 'small', 3);

INSERT INTO Dine_In
VALUES (4, 3);

INSERT INTO Has_Topping
VALUES ('Pizza 3', 'Regular Cheese', false, 2);

INSERT INTO Has_Topping
VALUES ('Pizza 3', 'Chicken', false, 1.5);

INSERT INTO Has_Topping
VALUES ('Pizza 3', 'Banana Peppers', false, 0.6);

INSERT INTO Seat_Number
VALUES (1,2);

INSERT INTO Seat_Number
VALUES (2,3);


INSERT INTO Orders
VALUES (4);

INSERT INTO Customer
VALUES (1, '8642545861', 'Andrew Wilkes-Krier', NULL);

INSERT INTO Pickup
VALUES (1, 4);

INSERT INTO Pizza
VALUES ('Pizza 4', 10.75, 3.30, false, '2019-03-03 09:30:00', 'Original', 'Large', 4);

INSERT INTO Has_Topping
VALUES ('Pizza 4', 'Regular Cheese', false, 5);

INSERT INTO Has_Topping
VALUES ('Pizza 4', 'Pepperoni', false, 3.5);

INSERT INTO Pizza
VALUES ('Pizza 5', 10.75, 3.30, true, '2019-03-03 09:30:00', 'Original', 'Large', 4);

INSERT INTO Has_Topping
VALUES ('Pizza 5', 'Regular Cheese', false, 5);

INSERT INTO Has_Topping
VALUES ('Pizza 5', 'Pepperoni', false, 3.5);

INSERT INTO Pizza
VALUES ('Pizza 6', 10.75, 3.30, true, '2019-03-03 09:30:00', 'Original', 'Large', 4);

INSERT INTO Has_Topping
VALUES ('Pizza 6', 'Regular Cheese', false, 5);

INSERT INTO Has_Topping
VALUES ('Pizza 6', 'Pepperoni', false, 3.5);

INSERT INTO Pizza
VALUES ('Pizza 7', 10.75, 3.30, true, '2019-03-03 09:30:00', 'Original', 'Large', 4);

INSERT INTO Has_Topping
VALUES ('Pizza 7', 'Regular Cheese', false, 5);

INSERT INTO Has_Topping
VALUES ('Pizza 7', 'Pepperoni', false, 3.5);

INSERT INTO Pizza
VALUES ('Pizza 8', 10.75, 3.30, true, '2019-03-03 09:30:00', 'Original', 'Large', 4);

INSERT INTO Has_Topping
VALUES ('Pizza 8', 'Regular Cheese', false, 5);

INSERT INTO Has_Topping
VALUES ('Pizza 8', 'Pepperoni', false, 3.5);

INSERT INTO Pizza
VALUES ('Pizza 9', 10.75, 3.30, true, '2019-03-03 09:30:00', 'Original', 'Large', 4);

INSERT INTO Has_Topping
VALUES ('Pizza 9', 'Regular Cheese', false, 5);

INSERT INTO Has_Topping
VALUES ('Pizza 9', 'Pepperoni', false, 3.5);


INSERT INTO Orders
VALUES (5);

INSERT INTO Delivery
VALUES (1, 5);

UPDATE Customer
SET Address = '115 Party Blvd, Anderson SC 29621'
WHERE ID = 1;

INSERT INTO Pizza
VALUES ('Pizza 10', 14.50, 5.59, true, '2019-03-05 07:11:00', 'Original', 'X-Large', 5);

INSERT INTO Has_Topping
VALUES ('Pizza 10', 'Four Cheese Blend', false, 7);

INSERT INTO Has_Topping
VALUES ('Pizza 10', 'Pepperoni', false, 4.6);

INSERT INTO Has_Topping
VALUES ('Pizza 10', 'Sausage', false, 4.25);

INSERT INTO Pizza
VALUES ('Pizza 11', 17, 5.59, true, '2019-03-05 07:11:00', 'Original', 'X-Large', 5);

INSERT INTO Has_Topping
VALUES ('Pizza 11', 'Four Cheese Blend', false, 7);

INSERT INTO Has_Topping
VALUES ('Pizza 11', 'Ham', true, 4);

INSERT INTO Has_Topping
VALUES ('Pizza 11', 'Pineapple', true, 2);

INSERT INTO Pizza
VALUES ('Pizza 12', 14, 5.68, true, '2019-03-05 07:11:00', 'Original', 'X-Large', 5);

INSERT INTO Has_Topping
VALUES ('Pizza 12', 'Four Cheese Blend', false, 7);

INSERT INTO Has_Topping
VALUES ('Pizza 12', 'Jalapenos', false, 1.75);

INSERT INTO Has_Topping
VALUES ('Pizza 12', 'Bacon', false, 3);

INSERT INTO Order_Discount
VALUES (5, 'Gameday Special');

INSERT INTO Pizza_Discount
VALUES ('Pizza 11', 'Specialty Pizza');


INSERT INTO Orders
VALUES (6);

INSERT INTO Customer
VALUES (2, '8644749953', 'Matt Engers', NULL);

INSERT INTO Pickup
VALUES (2, 6);

INSERT INTO Pizza
VALUES ('Pizza 13', 16.85, 7.85, true, '2019-03-02 05:30:00', 'Gluten-Free', 'X-Large', 6);

INSERT INTO Has_Topping
VALUES ('Pizza 13', 'Green Pepper', false, 2.5);

INSERT INTO Has_Topping
VALUES ('Pizza 13', 'Onion', false, 2.75);

INSERT INTO Has_Topping
VALUES ('Pizza 13', 'Roma tomato', false, 4.5);

INSERT INTO Has_Topping
VALUES ('Pizza 13', 'Mushrooms', false, 3);

INSERT INTO Has_Topping
VALUES ('Pizza 13', 'Black Olives', false, 2);

INSERT INTO Has_Topping
VALUES ('Pizza 13', 'Goat Cheese', false, 5.5);

INSERT INTO Pizza_Discount
VALUES ('Pizza 13', 'Specialty Pizza');


INSERT INTO Orders
VALUES (7);

INSERT INTO Customer
VALUES (3, '8642328944', 'Frank Turner', '6745 Wessex St Anderson SC 29621');

INSERT INTO Delivery
VALUES (3, 7);

INSERT INTO Pizza
VALUES ('Pizza 14', 13.25, 3.20, true, '2019-03-02 06:17:00', 'Thin', 'Large', 7);

INSERT INTO Has_Topping
VALUES ('Pizza 14', 'Chicken', false, 2.25);

INSERT INTO Has_Topping
VALUES ('Pizza 14', 'Green Pepper', false, 2);

INSERT INTO Has_Topping
VALUES ('Pizza 14', 'Onion', false, 2);

INSERT INTO Has_Topping
VALUES ('Pizza 14', 'Mushrooms', false, 2.5);

INSERT INTO Has_Topping
VALUES ('Pizza 14', 'Four Cheese Blend', false, 5);


INSERT INTO Orders
VALUES (8);

INSERT INTO Customer
VALUES (4, '8648785679', 'Milo Auckerman', '8879 Suburban Home, Anderson, SC 29621');

INSERT INTO Delivery
VALUES (4, 8);

INSERT INTO Pizza
VALUES ('Pizza 15', 12, 3.75, true, '2019-03-06 07:22:00', 'Thin', 'Large', 8);

INSERT INTO Has_Topping
VALUES ('Pizza 15', 'Four Cheese Blend', true, 5);

INSERT INTO Pizza
VALUES ('Pizza 16', 12, 2.55, true, '2019-03-06 07:22:00', 'Thin', 'Large', 8);

INSERT INTO Has_Topping
VALUES ('Pizza 16', 'Regular Cheese', false, 5);

INSERT INTO Has_Topping
VALUES ('Pizza 16', 'Pepperoni', true, 3.5);

INSERT INTO Order_Discount
VALUES (8, 'Employee');
