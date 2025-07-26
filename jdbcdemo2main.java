package jdbcdem02;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Scanner;

public class jdbcdemo2main {
	
	public static int menu(Scanner sc) {
		System.out.println("1. Sign Up");
		System.out.println("2. Sign In");
		System.out.println("3. Sign Out");
		System.out.println("4. Admin - Add Product");
		System.out.println("5. Admin - Delete Product");
		System.out.println("6. Admin - Update Product");
		System.out.println("7. Admin - Change Order Status");
		System.out.println("8. Customer - Display All Products");
		System.out.println("9. Customer - Find Product");
		System.out.println("10. Customer - Place Order");
		System.out.println();
		System.out.print("Enter the choice :");
		return sc.nextInt();
	}

		public static Scanner sc=new Scanner(System.in);
		public static User curuser=null;
		
		public static void signUp() {
			System.out.println("User Registration");
			System.out.print("Name: ");
			String name = sc.next();
			System.out.print("Email: ");
			String email = sc.next();
			System.out.print("Passwd: ");
			String passwd = sc.next();
			System.out.print("Addr: ");
			String addr = sc.next();
			sc.nextLine();
			 
			try(Connection con=DbUtil.getConnection()){
				String sq1="INSERT INTO users(id,name,email,passwd,addr) VALUES (default,?,?,?,?)";
				try(PreparedStatement stem=con.prepareStatement(sq1)){
					stem.setString(1, name);
					stem.setString(2, email);
					stem.setString(3, passwd);
					stem.setString(4, addr);
					int count=stem.executeUpdate();
					System.out.println("User Register :"+count);

				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	 
// -----------------------------------------------------------------------------
		public static void signIn() {
			System.out.print("Email: ");
			String email = sc.next();
			System.out.print("Passwd: ");
			String passwd = sc.next();
			try(Connection con=DbUtil.getConnection()){
				String sq1="SELECT * FROM users WHERE email=? AND passwd=?";
				try(PreparedStatement stem=con.prepareStatement(sq1)){
					stem.setString(1, email);
					stem.setString(2, passwd);
					try(ResultSet rs=stem.executeQuery()){
						if(rs.next()) {
							int id=rs.getInt("id");
							String name=rs.getString("name");
							String addr=rs.getString("addr");
							curuser=new User(id,name,email,passwd,addr);
							System.out.println("Login Successful.");
						}else {
							System.out.println("Login Failed.");
						}
					}
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
// ---------------------------------------------------------------------------------------
		public static void signOut() {
			curuser=null;
		}
		public static void addProduct() {
			if(curuser == null) {
				System.out.println("user is not logged in.");
				return;
			}
			if(! curuser.getName().equals("Admin")) {
				System.out.println("you are not admin ");
				return;
			}
			try(Connection con=DbUtil.getConnection()){
				String sq1="INSERT INTO product VALUES (default,?,?)";
				try(PreparedStatement stem=con.prepareStatement(sq1)){
					System.out.println("Enter product name:");
					String name=sc.next();
					System.out.println("Enter the price:");
					double price=sc.nextDouble();
					stem.setString(1, name);
					stem.setDouble(2, price);
					int count=stem.executeUpdate();
					System.out.println("row insered :"+count);

				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
//--------------------------------------------------------------------------------------------------------
		public static void updateProduct() {
			if(curuser == null) {
				System.out.println("user is not logged in.");
				return;
			}
			if(! curuser.getName().equals("Admin")) {
				System.out.println("you are not admin ");
				return;
			}
			try(Connection con=DbUtil.getConnection()){
				String sq1="UPDATE product SET name=?,price=? WHERE id=?";
				try(PreparedStatement stem=con.prepareStatement(sq1)){
					System.out.println("Enter the id:");
					int id=sc.nextInt();
					System.out.println("Enter product new name:");
					String name=sc.next();
					System.out.println("Enter the new price:");
					double price=sc.nextDouble();
					stem.setString(1, name);
					stem.setDouble(2, price);
					stem.setInt(3, id);
					int count=stem.executeUpdate();
					System.out.println("row update :"+count);

				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
//--------------------------------------------------------------------------------------
		public static void deleteProduct() {
			if(curuser == null) {
				System.out.println("user is not logged in.");
				return;
			}
			if(! curuser.getName().equals("Admin")) {
				System.out.println("you are not admin ");
				return;
			}
			try(Connection con=DbUtil.getConnection()){
				String sq1="DELETE FROM product WHERE id=?";
				try(PreparedStatement stem=con.prepareStatement(sq1)){
					System.out.println("Enter product ID:");
					int id=sc.nextInt();
					stem.setInt(1, id);
					int count=stem.executeUpdate();
					System.out.println("row Deleted :"+count);

				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
//-----------------------------------------------------------------------------------------
		public static void changeorderStatus() {
		    if (curuser == null) {
		        System.out.println("User is not logged in.");
		        return;
		    }

		    if (!curuser.getName().equals("Admin")) {
		        System.out.println("You are not admin.");
		        return;
		    }

		    try (Connection con = DbUtil.getConnection()) {
		        String sql = "UPDATE orders SET status=? WHERE id=?";
		        try (PreparedStatement stmt = con.prepareStatement(sql)) {
		            System.out.print("Enter Order ID to update: ");
		            int orderId = sc.nextInt();
		            sc.nextLine(); // consume newline
		            System.out.print("Enter new status (e.g., shipped, delivered): ");
		            String newStatus = sc.nextLine();

		            stmt.setString(1, newStatus);
		            stmt.setInt(2, orderId);
		            int count = stmt.executeUpdate();

		            if (count > 0) {
		                System.out.println("Order status updated successfully.");
		            } else {
		                System.out.println("Order not found.");
		            }
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}

//------------------------------------------------------------------------------------------------------
		public static void displayorderStatus() {
			if(curuser == null) {
				System.out.println("user is not logged in.");
				return;
			}
			
			try(Connection con=DbUtil.getConnection()){
				String sq1="SELECT * FROM product";
				try(PreparedStatement stem=con.prepareStatement(sq1)){
					try(ResultSet rs=stem.executeQuery()){
						while(rs.next()) {
							int id=rs.getInt("id");
							String name=rs.getString("name");
							double price=rs.getDouble("price");
							System.out.println(id + ","+name+","+price);
						}
					}

				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
//-------------------------------------------------------------------------------------------------------
		public static void findProductByname() {
			if(curuser == null) {
				System.out.println("user is not logged in.");
				return;
			}
			
			try(Connection con=DbUtil.getConnection()){
				String sq1="SELECT * FROM product WHERE name LIKE ?";
				System.out.println("Enter the name:");
				String findname=sc.next();
				try(PreparedStatement stem=con.prepareStatement(sq1)){
					stem.setString(1,"%"+findname+"%");
					try(ResultSet rs=stem.executeQuery()){
						boolean found=false;
						while(rs.next()) {
							found=true;
							int id=rs.getInt("id");
							String name=rs.getString("name");
							double price=rs.getDouble("price");
							System.out.println(id + ","+name+","+price);
						}
						if(!found) {
							System.out.println("Product Not Found");
						}
					}

				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
//---------------------------------------------------------------------------------------------
		public static void findProductByprice() {
			if(curuser == null) {
				System.out.println("user is not logged in.");
				return;
			}
			
			try(Connection con=DbUtil.getConnection()){
				String sq1="SELECT * FROM product WHERE price LIKE ?";
				System.out.println("Enter the price:");
				double findprice=sc.nextDouble();
				try(PreparedStatement stem=con.prepareStatement(sq1)){
					stem.setString(1,"%"+findprice+"%");
					try(ResultSet rs=stem.executeQuery()){
						boolean found=false;
						while(rs.next()) {
							found=true;
							int id=rs.getInt("id");
							String name=rs.getString("name");
							double price=rs.getDouble("price");
							System.out.println(id + ","+name+","+price);
						}
						if(!found) {
							System.out.println("Product Not Found");
						}
					}

				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
//--------------------------------------------------------------------------------------------
		public static void placeOrder() {
			if(curuser == null) {
				System.out.println("user is not logged in.");
				return;
			}
			if(! curuser.getName().equals("Admin")) {
				System.out.println("you are not admin ");
				return;
			}
			System.out.print("Enter Product Id to Purchase: ");
			int prodId = sc.nextInt();
			try(Connection con=DbUtil.getConnection()){
				String sq1="INSERT INTO orders VALUES (default,?,?,NOW(),'pending')";
				try(PreparedStatement stem=con.prepareStatement(sq1)){
					stem.setInt(1, curuser.getId());
					stem.setInt(2, prodId);
					int count=stem.executeUpdate();
					System.out.println("Orders Placed :"+count);

				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
//------------------------------------------------------------------------------------------------
		public static void showCustomerOrders() {
			if(curuser == null) {
				System.out.println("user is not logged in.");
				return;
			}
			if(! curuser.getName().equals("Admin")) {
				System.out.println("you are not admin ");
				return;
			}
			
			try(Connection con=DbUtil.getConnection()){
				String sql = "SELECT p.id, p.name, p.price, o.order_dt, o.status FROM products p INNER JOIN orders o ON o.prod_id = p.id WHERE o.user_id=?";
				try(PreparedStatement stem=con.prepareStatement(sql)){
					stem.setInt(1, curuser.getId());
					try(ResultSet rs=stem.executeQuery()){
						while(rs.next()) {
							int id=rs.getInt("id");
							String name=rs.getString("name");
							double price=rs.getDouble("price");
							Timestamp ordDate=rs.getTimestamp("order_dt");
							String status=rs.getString("status");
							System.out.println(id + ", " + name + ", " + price + ", " + ordDate + ", " + status);
						}
					}

				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
//--------------------------------------------------------------------------------------------
		public static void main(String[] args) {
		    Scanner sc = new Scanner(System.in);
		    boolean exit = false;

		    while (!exit) {
		        int choice = menu(sc); 

		        switch (choice) {
		            case 1:
		                signUp();
		                break;
		            case 2:
		                signIn();
		                break;
		            case 3:
		                signOut();
		                break;
		            case 4:
		                addProduct();
		                break;
		            case 5:
		                deleteProduct();
		                break;
		            case 6:
		                updateProduct();
		                break;
		            case 7:
		                changeorderStatus();
		                break;
		            case 8:
		                displayorderStatus(); // shows all products
		                break;
		            case 9:
		                findProductByname();
		                break;
		            case 10:
		                placeOrder();
		                break;
		            case 11:
		                showCustomerOrders();
		                break;
		            case 0:
		                exit = true;
		                System.out.println("Exiting the application.");
		                break;
		            default:
		                System.out.println("Invalid choice. Please try again.");
		        }

		        System.out.println("-----------------------------------\n");
		    }
		}
}
