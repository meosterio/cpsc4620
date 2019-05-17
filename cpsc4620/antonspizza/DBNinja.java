package cpsc4620.antonspizza;

import java.io.*;
import java.sql.*;
import java.util.*;

/*
This file is where most of your code changes will occur
You will write the code to retrieve information from the database, or save
information to the database

The class has several hard coded static variables used for the connection,
you will need to change those to your connection information

This class also has static string variables for pickup, delivery and dine-in.
If your database stores the strings differently (i.e "pick-up" vs "pickup")
changing these static variables will ensure that the comparison is checking for
the right string in other places in the program. You will also need to use these
strings if you store this as boolean fields or an integer.

*/

/**
 * A utility class to help add and retrieve information from the database
 */

public final class DBNinja {
    //enter your user name here
    private static String user = "upe3xiv7";
    //enter your password here
    private static String password = "Hello_Kevin";
    //enter your database name here
    private static String database_name = "CPSC_4620_Project_3_e2gl";
    //Do not change the port. 3306 is the default MySQL port
    private static String port = "3306";
    private static Connection conn;

    //Change these variables to however you record dine-in, pick-up and
    //delivery, and sizes and crusts
    public final static String pickup = "Pickup";
    public final static String delivery = "Delivery";
    public final static String dine_in = "Dine-In";

    public final static String size_s = "small";
    public final static String size_m = "medium";
    public final static String size_l = "Large";
    public final static String size_xl = "X-Large";

    public final static String crust_thin = "Thin";
    public final static String crust_orig = "Original";
    public final static String crust_pan = "Pan";
    public final static String crust_gf = "Gluten-Free";



    /**
     * This function will handle the connection to the database
     * @return true if the connection was successfully made
     * @throws SQLException
     * @throws IOException
     */
    private static boolean connect_to_db() throws SQLException, IOException
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println ("Could not load the driver");

            System.out.println("Message     : " + e.getMessage());


            return false;
        }

        conn = DriverManager.getConnection("jdbc:mysql://mysql1.cs.clemson.edu:"+port+"/"+database_name, user, password);
        return true;
    }

    /**
     *
     * @param o order that needs to be saved to the database
     * @throws SQLException
     * @throws IOException
     * @requires o is not NULL. o's ID is -1, as it has not been assigned yet.
     * The pizzas do not exist in the database yet, and the topping inventory
     * will allow for these pizzas to be made
     * @ensures o will be assigned an id and added to the database, along with
     * all of it's pizzas. Inventory levels will be updated appropriately
     */
    public static void addOrder(Order o) throws SQLException, IOException
    {
        connect_to_db();
  		/* add code to add the order to the DB. Remember to add the pizzas and
      discounts as well, which will involve multiple tables. Customer should
      already exist. Toppings will need to be added to the pizzas.

  		It may be beneficial to define more functions to add an individual pizza
      to a database, add a topping to a pizza, etc.

  		Note: the order ID will be -1 and will need to be replaced to be a fitting
      primary key.

  		You will also need to add timestamps to your pizzas/orders in your database.
      Those timestamps are not stored in this program, but you can get the current
      time before inserting into the database

  		Remember, when a new order comes in the ingredient levels for the topping
      need to be adjusted accordingly. Remember to check for "extra" of a topping
      here as well.

  		You do not need to check to see if you have the topping in stock before
      adding to a pizza. You can just let it go negative.
  		*/
      int ID = -5;
      String query = "Select MAX(Order_Number) From Orders;";
      Statement stmt = conn.createStatement();
      try {
          ResultSet rset = stmt.executeQuery(query);
          while(rset.next())
          {
            ID = rset.getInt(1);
          }
      }
      catch (SQLException e) {
          System.out.println("Error getting max customer ID");
          while (e != null) {
              System.out.println("Message     : " + e.getMessage());
              e = e.getNextException();
          }
          //don't leave your connection open!
          conn.close();
      }
      ID+=1;
      String query2 = "INSERT INTO Orders VALUES ("+ID+");";
      Statement stmt2 = conn.createStatement();
      try {
        int rset2 = stmt2.executeUpdate(query2);
      }
      catch (SQLException e) {
          System.out.println("Error adding customer");
          while (e != null) {
              System.out.println("Message     : " + e.getMessage());
              e = e.getNextException();
          }
          //don't leave your connection open!
          conn.close();
      }
          conn.close();

    }

    /**
     *
     * @param c the new customer to add to the database
     * @throws SQLException
     * @throws IOException
     * @requires c is not null. C's ID is -1 and will need to be assigned
     * @ensures c is given an ID and added to the database
     */
    public static void addCustomer(ICustomer c) throws SQLException, IOException
    {
        connect_to_db();
		      /*add code to add the customer to the DB.
		        Note: the ID will be -1 and will need to be replaced to be a fitting
            primary key
		        Note that the customer is an ICustomer data type, which means c
            could be a dine in, carryout or delivery customer
		      */
        int ID = -5;
        String query = "Select MAX(ID) From Customer;";
        Statement stmt = conn.createStatement();
        try {
            ResultSet rset = stmt.executeQuery(query);
            while(rset.next())
            {
              ID = rset.getInt(1);
            }
        }
        catch (SQLException e) {
            System.out.println("Error getting max customer ID");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }
            //don't leave your connection open!
            conn.close();
        }
        ID+=1;
        if (c instanceof DeliveryCustomer) {
          //is a delivery customer
          //need to cast
          DeliveryCustomer cust = (DeliveryCustomer) c;
          //now can call DeliveryCustomer methods on cust
          String add = cust.getAddress();
          //String phone = cust.phone;
          //String name = cust.name;
          String addCust = "INSERT INTO Customer VALUES ("+ID+", '', '', '"+add+"');";
          Statement stmt2 = conn.createStatement();
          try {
            int rset2 = stmt2.executeUpdate(addCust);
          }
          catch (SQLException e) {
              System.out.println("Error adding customer");
              while (e != null) {
                  System.out.println("Message     : " + e.getMessage());
                  e = e.getNextException();
              }
              //don't leave your connection open!
              conn.close();
          }
        }
        else if(c instanceof DineOutCustomer) {
          //is a pickup customer
          //need to cast
          DineOutCustomer cust = (DineOutCustomer) c;
          //now can call DineOutCustomer methods on cust
          String phone = cust.getPhone();
          String name = cust.getName();
          String addCust = "INSERT INTO Customer VALUES ("+ID+", '"+phone+"', '"+name+"', '');";
          Statement stmt2 = conn.createStatement();
          try {
            int rset2 = stmt2.executeUpdate(addCust);
          }
          catch (SQLException e) {
              System.out.println("Error adding customer");
              while (e != null) {
                  System.out.println("Message     : " + e.getMessage());
                  e = e.getNextException();
              }
              //don't leave your connection open!
              conn.close();
          }
        }
        else if(c instanceof DineInCustomer) {
          //is a delivery customer
          //need to cast
          DineInCustomer cust = (DineInCustomer) c;
          //now can call DineInCustomer methods on cust
          String addCust = "INSERT INTO Customer VALUES ("+ID+",'','','');";
          Statement stmt2 = conn.createStatement();
          try {
            int rset2 = stmt2.executeUpdate(addCust);
          }
          catch (SQLException e) {
              System.out.println("Error adding customer");
              while (e != null) {
                  System.out.println("Message     : " + e.getMessage());
                  e = e.getNextException();
              }
              //don't leave your connection open!
              conn.close();
          }
          cust.getTableNum();
        }
        conn.close();
    }

    /**
     *
     * @param o the order to mark as complete in the database
     * @throws SQLException
     * @throws IOException
     * @requires the order exists in the database
     * @ensures the order will be marked as complete
     */
    public static void CompleteOrder(Order o) throws SQLException, IOException
    {
        connect_to_db();
		      /*add code to mark an order as complete in the DB. You may have a
          boolean field for this, or maybe a completed time timestamp.
          However you have it, */
          int ID = o.getID();
          ArrayList<Pizza> pz = new ArrayList<Pizza>();
          pz = o.getPizzas();

          Statement stmt = conn.createStatement();
          try {
            for (Pizza p: pz) {
              int PID = p.getID();
              //NEED THE PIZZA NAME NOT THE ID
              //IN MY DATABASE THE PRIMARY KEY IS THE NAME, NOT A PIZZA ID
              String query = "Update Pizza Set Completion_Status = true where Name = '" + PID + "';";
              int rset = stmt.executeUpdate(query);
            }
          }
          catch (SQLException e) {
              System.out.println("Error completing order");
              while (e != null) {
                  System.out.println("Message     : " + e.getMessage());
                  e = e.getNextException();
              }
              //don't leave your connection open!
              conn.close();
          }
        conn.close();
    }

    /**
     *
     * @param t the topping whose inventory is being replenished
     * @param toAdd the amount of inventory of t to add
     * @throws SQLException
     * @throws IOException
     * @requires t exists in the database and toAdd > 0
     * @ensures t's inventory level is increased by toAdd
     */
    public static void AddToInventory(Topping t, double toAdd) throws SQLException, IOException
    {
        connect_to_db();
		      /*add code to add toAdd to the inventory level of T. This is not adding a
          new topping, it is adding a certain amount of stock for a topping. This
          would be used to show that an order was made to replenish the restaurants
          supply of pepperoni, etc*/
        String ID = t.getName();
        String query = "Update Toppings Set Inventory = Inventory +" + toAdd + "where Name = '" + ID + "';";

        Statement stmt = conn.createStatement();
        try {
            int rset = stmt.executeUpdate(query);
          }

        catch (SQLException e) {
            System.out.println("Error updating inventory");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }
            //don't leave your connection open!
            conn.close();
        }
        conn.close();
        //execute a query
    }


    /*
        A function to get the list of toppings and their inventory levels.
        I have left this code "complete" as an example of how to use JDBC to
        get data from the database. This query will not work on your database
        if you have different field or table names, so it will need to be
        changed

        Also note, this is just getting the topping ids and then calling
        getTopping() to get the actual topping. You will need to complete
        this on your own

        You don't actually have to use and write the getTopping() function,
        but it can save some repeated code if the program were to expand,
        and it keeps the functions simpler, more elegant and easy to read.
        Breaking up the queries this way also keeps them simpler.
        I think it's a better way to do it, and many people in the industry
        would agree, but its a suggestion, not a requirement.
    */

    /**
     *
     * @return the List of all toppings in the database
     * @throws SQLException
     * @throws IOException
     * @ensures the returned list will include all toppings and accurate
           inventory levels
     */
    public static ArrayList<Topping> getInventory() throws SQLException, IOException
    {
        //start by connecting
        connect_to_db();
        ArrayList<Topping> ts = new ArrayList<Topping>();
        //create a string with out query, this one is an easy one
        String query = "Select Name From Toppings;";

        Statement stmt = conn.createStatement();
        try {
            ResultSet rset = stmt.executeQuery(query);
            //even if you only have one result, you still need to call
            //ResultSet.next() to load the first tuple
            while(rset.next())
            {
					/*Use getInt, getDouble, getString to get the actual value.
          You can use the column number starting with 1, or use the column
          name as a string

					NOTE: You want to use rset.getInt() instead of
          Integer.parseInt(rset.getString()), not just because it's shorter,
          but because of the possible NULL values. A NUll would cause parseInt
           to fail

					If there is a possibility that it could return a NULL value you
          need to check to see if it was NULL. In this query we won't get
          nulls, so I didn't. If I was going to I would do:

					int ID = rset.getInt(1);
					if(rset.wasNull())
					{
						//set ID to what it should be for NULL, and whatever you need to do.
					}

					NOTE: you can't check for NULL until after you have read the value
          using one of the getters.

					*/
                String ID = rset.getString(1);
                //Now I'm just passing my primary key to this function to
                //get the topping itself individually
                ts.add(getTopping(ID));
            }
        }
        catch (SQLException e) {
            System.out.println("Error loading inventory");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }

            //don't leave your connection open!
            conn.close();
            return ts;
        }


        //end by closing the connection
        conn.close();
        return ts;
    }

    /**
     *
     * @return a list of all orders that are currently open in the kitchen
     * @throws SQLException
     * @throws IOException
     * @ensures all currently open orders will be included in the returned list.
     */
    public static ArrayList<Order> getCurrentOrders() throws SQLException, IOException
    {
        connect_to_db();
        ArrayList<Order> os = new ArrayList<Order>();
		      /*add code to get a list of all open orders. Only return Orders that
          have not been completed. If any pizzas are not completed, then the
          order is open.*/
        String query = "Select Order_Number From Orders;";

        Statement stmt = conn.createStatement();
        try {
            ResultSet rset = stmt.executeQuery(query);
            while(rset.next()) {
              int ID = rset.getInt(1);
              String query2 = "Select Completion_Status from Pizza where Order_Number = " + ID + ";";
              Statement stmt2 = conn.createStatement();
              try {
                ResultSet rset2 = stmt2.executeQuery(query2);
                boolean iscomplete = true;
                while(rset2.next()) {
                  boolean pc = rset2.getBoolean(1);
                  if (pc == false) {
                    iscomplete = false;
                  }
                }
                if (!iscomplete) {
                  os.add(getOrder(ID));
                }
              }
              catch (SQLException e) {
                  System.out.println("Error loading pizza for order complete");
                  while (e != null) {
                      System.out.println("Message     : " + e.getMessage());
                      e = e.getNextException();
                  }
                  //don't leave your connection open!
                  conn.close();
                  return os;
              }
            }
        }
        catch (SQLException e) {
            System.out.println("Error loading orders");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }
            //don't leave your connection open!
            conn.close();
            return os;
        }
        conn.close();
        return os;
    }

    /**
     *
     * @param size the pizza size
     * @param crust the type of crust
     * @return the base price for a pizza with that size and crust
     * @throws SQLException
     * @throws IOException
     * @requires size = size_s || size_m || size_l || size_xl AND crust = crust_thin || crust_orig || crust_pan || crust_gf
     * @ensures the base price for a pizza with that size and crust is returned
     */
    public static double getBasePrice(String size, String crust) throws SQLException, IOException
    {
        connect_to_db();
        double bp = 0.0;
        //add code to get the base price for that size and crust pizza
        //Depending on how you store size and crust in your database,
        //you may have to do a conversion
        String query = "Select Price from Base_Prices where Size = '" + size + "' and Crust = '" + crust + "';";

        Statement stmt = conn.createStatement();
        try {
            ResultSet rset = stmt.executeQuery(query);
            while(rset.next()) {
              bp = rset.getDouble(1);
            }
        }
        catch (SQLException e) {
            System.out.println("Error loading base price");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }

            //don't leave your connection open!
            conn.close();
            return bp;
        }

        conn.close();
        return bp;
    }

    /**
     *
     * @return the list of all discounts in the database
     * @throws SQLException
     * @throws IOException
     * @ensures all discounts are included in the returned list
     */
    public static ArrayList<Discount> getDiscountList() throws SQLException, IOException
    {
        ArrayList<Discount> discs = new ArrayList<Discount>();
        connect_to_db();
        //add code to get a list of all discounts
        String query = "Select Name from Discounts;";

        Statement stmt = conn.createStatement();
        try {
            ResultSet rset = stmt.executeQuery(query);

            while(rset.next()) {
              String ID = rset.getString(1);
              discs.add(getDiscount(ID));
            }
          }

        catch (SQLException e) {
            System.out.println("Error loading discounts");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }

            //don't leave your connection open!
            conn.close();
            return discs;
        }

        conn.close();
        return discs;
    }

    /**
     *
     * @return the list of all delivery and carry out customers
     * @throws SQLException
     * @throws IOException
     * @ensures the list contains all carryout and delivery customers in the database
     */
    public static ArrayList<ICustomer> getCustomerList() throws SQLException, IOException
    {
        ArrayList<ICustomer> custs = new ArrayList<ICustomer>();
        connect_to_db();
        //add code to get a list of all customers
        String query = "Select ID from Customer;";
        Statement stmt = conn.createStatement();
        try {
            ResultSet rset = stmt.executeQuery(query);
            while(rset.next())
            {
              int ID = rset.getInt(1);
              custs.add(getCustomer(ID));
            }
          }
          catch (SQLException e) {
              System.out.println("Error getting customer list");
              while (e != null) {
                  System.out.println("Message     : " + e.getMessage());
                  e = e.getNextException();
              }
              //don't leave your connection open!
              conn.close();
              return custs;
          }
        conn.close();
        return custs;
    }



	/*
	Note: The following incomplete functions are not strictly required, but could
  make your DBNinja class much simpler. For instance, instead of writing one
  query to get all of the information about an order, you can find the primary
  key of the order, and use that to find the primary keys of the pizzas on that
  order, then use the pizza primary keys individually to build your pizzas.
  We are no longer trying to get everything in one query, so feel free to break
  them up as much as possible

	You could also add functions that take in a Pizza object and add that to the
  database, or take in a pizza id and a topping id and add that topping to the
  pizza in the database, etc. I would recommend this to keep your addOrder
  function much simpler

	These simpler functions should still not be called from our menu class.
  That is why they are private

	We don't need to open and close the connection in these, since they are only
  called by a function that has opened the connection and will close it after
	*/


    private static Topping getTopping(String ID) throws SQLException, IOException
    {
        //add code to get a topping
		    //the java compiler on unix does not like that t could be null,
        //so I created a fake topping that will be replaced
        Topping t = new Topping("fake", 0.25, 100.0, 4);
		    String query = "Select Price, Inventory From Toppings where Name ='" + ID + "';";

        Statement stmt = conn.createStatement();
        try {
            ResultSet rset = stmt.executeQuery(query);
            //even if you only have one result, you still need to call
            //ResultSet.next() to load the first tuple
            while(rset.next())
            {
					    //String tname = rset.getString(1);
					    double price = rset.getDouble(1);
					    int inv = rset.getInt(2);


					    t = new Topping(ID, price, inv, 4);
			      }

		        }
		    catch (SQLException e) {
            System.out.println("Error loading Topping");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }

            //don't leave your connection open!
            conn.close();
            return t;
        }

        return t;

    }

    private static Discount getDiscount(String ID)  throws SQLException, IOException
    {

        //add code to get a discount

        Discount D = new Discount("fake", 0.25, 100.0, 4);
		    String query = "Select Name, Percent_off, Dollar_off From Discounts where Name = '" + ID + "';";

        Statement stmt = conn.createStatement();
        try {
            ResultSet rset = stmt.executeQuery(query);
            //even if you only have one result, you still need to call
            //ResultSet.next() to load the first tuple
            while(rset.next())
            {
					    String tname = rset.getString(1);
					    double percent = rset.getDouble(2);
					    double dollar = rset.getDouble(3);
					    D = new Discount(tname, percent, dollar, 4);
			      }

		        }
		    catch (SQLException e) {
            System.out.println("Error loading Discount");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }
            //don't leave your connection open!
            conn.close();
            return D;
        }

        return D;
    }

    private static Pizza getPizza(String ID)  throws SQLException, IOException
    {

        //add code to get Pizza
        //Remember, a Pizza has toppings and discounts on it
        Pizza P = new Pizza(4, "fake", "fake", 4);
		    String query = "Select Name, Size, Crust, Price From Pizza where Name = '" + ID + "';";

        Statement stmt = conn.createStatement();
        try {
            ResultSet rset = stmt.executeQuery(query);
            //even if you only have one result, you still need to call
            //ResultSet.next() to load the first tuple
            while(rset.next())
            {
					    String tname = rset.getString(1);
					    String s = rset.getString(2);
					    String c = rset.getString(3);
              double p = rset.getDouble(4);

					    P = new Pizza(4, s, c, p);
			      }
		        }
		    catch (SQLException e) {
            System.out.println("Error loading Pizza");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }

            //don't leave your connection open!
            conn.close();
            return P;
        }
        /*Statement stmt6 = conn.createStatement();
        try {
            String query6 = "Select Topping_Name from Has_Topping where Pizza_Name = "+ID+";";
            ResultSet rset6 = stmt6.executeQuery(query6);
            while(rset6.next())
            {
              String DID = rset6.getString(1);
              P.addTopping(getTopping(DID));
          }
        }
        catch (SQLException e) {
            System.out.println("Error pizza topping");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }

            //don't leave your connection open!
            conn.close();
            return P;
        }
        Statement stmt7 = conn.createStatement();
        try {
            String query7 = "Select Discount_Name from Pizza_Discount where Pizza_Name = "+ID+";";
            ResultSet rset7 = stmt7.executeQuery(query7);
            while(rset7.next())
            {
              String aID = rset7.getString(1);
              P.addDiscount(getDiscount(aID));
          }
        }
        catch (SQLException e) {
            System.out.println("Error applying discount to order");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }

            //don't leave your connection open!
            conn.close();
            return P;
        }*/

        return P;
    }

    private static ICustomer getCustomer(int ID)  throws SQLException, IOException
    {
        //add code to get customer
        ICustomer C = new DeliveryCustomer(-1, "fake", "", "");
        String query = "Select Phone_Number, Name, Address From Customer where ID = " + ID + ";";
        Statement stmt = conn.createStatement();
        try {
            ResultSet rset = stmt.executeQuery(query);
            while(rset.next())
            {
              String phone = rset.getString(1);
              String name = rset.getString(2);
              String add = rset.getString(3);
              if(phone == "" && add == "")
              {
                //C = new DineInCustomer(ID);
              }
              else if(add != "")
              {
                C = new DeliveryCustomer(ID, name, phone, add);
              }
              else
              {
                C = new DineOutCustomer(ID, name, phone);
              }
            }
        }
        catch (SQLException e) {
            System.out.println("Error getting customer");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }
            //don't leave your connection open!
            conn.close();
            return C;
        }
        return C;
    }

    private static Order getOrder(int ID)  throws SQLException, IOException
    {

        //add code to get an order. Remember, an order has pizzas,
        //a customer, and discounts on it
        boolean cat = false;
        String type = "fake";
        ICustomer c = new DeliveryCustomer(-1, "fake", "", "");
        Order O = new Order(-1, c, "fake");
      if (!cat) {
        String query = "Select COUNT(*) From Dine_In where Order_Number = " + ID + ";";
        Statement stmt = conn.createStatement();
        try {
            ResultSet rset = stmt.executeQuery(query);
            while(rset.next())
            {
              int cnt = rset.getInt(1);
              if (cnt > 0) {
                //c = new DineInCustomer(ID);
                cat = true;
                type = dine_in;
              }
            }
          }
          catch (SQLException e) {
              System.out.println("Error with customer for order");
              while (e != null) {
                  System.out.println("Message     : " + e.getMessage());
                  e = e.getNextException();
              }

              //don't leave your connection open!
              conn.close();
              return O;
          }
        }
      if (!cat) {
        String query2 = "Select COUNT(*), Customer_ID From Pickup where Order_Number = " + ID + ";";
        Statement stmt2 = conn.createStatement();
        try {
            ResultSet rset2 = stmt2.executeQuery(query2);
            while(rset2.next())
            {
              int cnt = rset2.getInt(1);
              int CID = rset2.getInt(2);
              if (cnt > 0) {
                String query3 = "Select Phone_Number, Name From Customer where ID = " + CID + ";";
                Statement stmt3 = conn.createStatement();
                try {
                    ResultSet rset3 = stmt3.executeQuery(query3);
                    while(rset3.next())
                    {
                      String phone = rset3.getString(1);
                      String name = rset3.getString(2);
                      c = new DineOutCustomer(ID, name, phone);
                      cat = true;
                      type = pickup;
                    }
                }
                catch (SQLException e) {
                    System.out.println("Error with customer for order");
                    while (e != null) {
                        System.out.println("Message     : " + e.getMessage());
                        e = e.getNextException();
                    }
                    //don't leave your connection open!
                    conn.close();
                    return O;
                }
              }
            }
          }
          catch (SQLException e) {
              System.out.println("Error with customer for order");
              while (e != null) {
                  System.out.println("Message     : " + e.getMessage());
                  e = e.getNextException();
              }
              //don't leave your connection open!
              conn.close();
              return O;
          }
        }
      if (!cat) {
        String query4 = "Select COUNT(*), Customer_ID From Delivery where Order_Number = " + ID + ";";
        Statement stmt4 = conn.createStatement();
        try {
            ResultSet rset4 = stmt4.executeQuery(query4);
            while(rset4.next())
            {
              int cnt = rset4.getInt(1);
              int CID = rset4.getInt(2);
              if (cnt > 0) {
                String query5 = "Select Phone_Number, Name, Address From Customer where ID = " + CID + ";";
                Statement stmt5 = conn.createStatement();
                try {
                    ResultSet rset5 = stmt5.executeQuery(query5);
                    while(rset5.next())
                    {
                      String phone = rset5.getString(1);
                      String name = rset5.getString(2);
                      String add = rset5.getString(3);
                      c = new DeliveryCustomer(ID, name, phone, add);
                      cat = true;
                      type = delivery;
                    }
                }
                catch (SQLException e) {
                    System.out.println("Error with customer for order");
                    while (e != null) {
                        System.out.println("Message     : " + e.getMessage());
                        e = e.getNextException();
                    }
                    //don't leave your connection open!
                    conn.close();
                    return O;
                }
              }
            }
          }
          catch (SQLException e) {
              System.out.println("Error with customer for order");
              while (e != null) {
                  System.out.println("Message     : " + e.getMessage());
                  e = e.getNextException();
              }
              //don't leave your connection open!
              conn.close();
              return O;
          }
        }
      O = new Order(ID, c, type);
      Statement stmt6 = conn.createStatement();
      try {
          String query6 = "Select Discount_Name from Order_Discount where Order_Number = "+ID+";";
          ResultSet rset6 = stmt6.executeQuery(query6);
          while(rset6.next())
          {
            String DID = rset6.getString(1);
            O.addDiscount(getDiscount(DID));
        }
      }
      catch (SQLException e) {
          System.out.println("Error applying discount to order");
          while (e != null) {
              System.out.println("Message     : " + e.getMessage());
              e = e.getNextException();
          }

          //don't leave your connection open!
          conn.close();
          return O;
      }
      Statement stmt7 = conn.createStatement();
      try {
          String query7 = "Select Name from Pizza where Order_Number = "+ID+";";
          ResultSet rset7 = stmt7.executeQuery(query7);
          while(rset7.next())
          {
            String PID = rset7.getString(1);
            O.addPizza(getPizza(PID));
        }
      }
      catch (SQLException e) {
          System.out.println("Error applying discount to order");
          while (e != null) {
              System.out.println("Message     : " + e.getMessage());
              e = e.getNextException();
          }
          //don't leave your connection open!
          conn.close();
          return O;
      }

      return O;
    }

}
