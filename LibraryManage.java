package Book_Library;
import java.sql.*;
import java.util.Scanner;


public class LibraryManage {
	
		
	protected void list_books(Statement st) throws SQLException {
		String query = "select name from Books";
		
		ResultSet rs= st.executeQuery(query);
		
		System.out.println("Books Available:");
		
		while(rs.next()) {
		System.out.println(rs.getString(1));
		}
		
	}
	
	protected void add_book(Statement st, int id, String name, String type, float price) throws SQLException{
		System.out.println("name: "+name);
		String query = "INSERT INTO Books VALUES (" + id + ", '" + name + "', '" +type +"', "+ price +")";
		int rows_effected = st.executeUpdate(query);
		
		
      System.out.println("Rows effected!" + rows_effected);
	}

	protected void update_book(Statement st, int id) throws SQLException {
		System.out.println("What you wanna update? 1. id\t 2.name\t 3.Category\t 4.price\t 5.Exit");
		Scanner sc = new Scanner(System.in);
		int ch = sc.nextInt();
		while(ch != 0) {
			
			switch(ch) {
			case 1:
				System.out.print("Enter the new id please:");
				int newid = sc.nextInt();
				String q = "UPDATE Books SET IDs = " + newid + " WHERE IDs = " + id + "";
				int rows_effected = st.executeUpdate(q);
				System.out.println("Number of Rows effected: "+rows_effected);
				break;
			case 2:
				System.out.print("Enter a new name: ");
				sc.nextLine();
				String name = sc.nextLine();
				q = "UPDATE Books SET name = " + name + " WHERE IDs = " + id + "";
				rows_effected = st.executeUpdate(q);
				System.out.println("Number of Rows effected: "+rows_effected);
				break;
			case 3:
				System.out.print("Enter a new category for the book: ");
				sc.nextLine();
				String cat = sc.nextLine();
				q = "UPDATE Books SET category = " + cat + " WHERE IDs = " + id + "";
				rows_effected = st.executeUpdate(q);
				System.out.println("Number of Rows effected: "+rows_effected);
				break;
			case 4:
				System.out.print("Enter a new name: ");
				sc.nextLine();
				float prc = sc.nextInt();
				q = "UPDATE Books SET price = " + prc + " WHERE IDs = " + id + "";
				rows_effected = st.executeUpdate(q);
				System.out.println("Number of Rows effected: "+rows_effected);
				break;
			default:
				System.out.println("Something went wrong buddy!!");
			}
			
			
			System.out.println("What else you wanna update? 1. id\\t 2.name\\t 3.Category\\t 4.price\\t 5.Exit");
			ch = sc.nextInt();
			if (ch == 5)
				System.out.println("Have a nice day sir.");
				System.exit(0);
			
		}
		
	}
	
	protected void delete_books(Statement st,int id) throws SQLException {
		String q = "DELETE FROM Books WHERE IDs = " + id + "";
		int rows_effected = st.executeUpdate(q);
		System.out.println("Done!! Num of rows effected: "+ rows_effected);
	}
	
	protected void search_book(Statement st,int id) throws SQLException {
		String q = "SELECT name FROM Books WHERE IDs = " + id + "";
		try {
			ResultSet rs = st.executeQuery(q);
			rs.next();
			String name = rs.getString(1);
			System.out.println("Here it is:"+name);
		}
		catch(SQLException e) {
			System.out.println("It seems there is no book with this ID.");
		}
	}

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		
		LibraryManage lb = new LibraryManage();
		Scanner sc = new Scanner(System.in);
		
		String url = "jdbc:mysql://localhost:3306/booklib";
		String uname = "root";
		String pass = "852456";
		
		// connect
		Connection con=DriverManager.getConnection(url, uname, pass);
		
		// Load and Register driver.
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        // Create the Statement
        Statement st=con.createStatement();
        
        System.out.println("################### Operations ###################");
        System.out.println("1. List Books");
        System.out.println("2. Add Book");
        System.out.println("3. Update Book");
        System.out.println("4. Delete Book");
        System.out.println("5. Search Books");
        System.out.println("6. Exit");
        
        
        System.out.print("Choose your action: ");
        int choice = sc.nextInt();
        while(choice != 0)
        {   
        	
        	switch(choice) {
		        case 1:
		        	lb.list_books(st);
		        	break; 
		        case 2:
		        	sc.nextLine();
		        	System.out.println("Enter book name:");
		        	String name = sc.nextLine();
		        	System.out.println("Enter book Id:");
		        	int id = sc.nextInt();
		        	System.out.println("Enter book type:");
		        	sc.nextLine();
		        	String type = sc.nextLine();
		        	System.out.println("Enter book price:");
		        	float price = sc.nextFloat();
		        	
		        	lb.add_book(st, id, name, type, price);
		            break;
		        case 3:
		        	System.out.print("Enter the book id that you wanna update: ");
		        	lb.update_book(st, sc.nextInt());
		        	break;
		        	
		        case 4:
		        	System.out.print("Enter the book id that you wannna delete: ");
		        	lb.delete_books(st, sc.nextInt());
		        	break;
		            
		        case 5:
		        	System.out.print("Enter the book id that you wanna search: ");		        	
		        	lb.search_book(st, sc.nextInt());
		        	break;
		     
		        case 6:
		        	System.out.println("Have a nice day sir.");
		        	System.exit(0);
		        	
		        default:
		        	System.out.println("None of the specified actions matched!!");
	        	
	        }
        	System.out.print("Choose your next action: ");
            choice = sc.nextInt();
        }
        
	}

}