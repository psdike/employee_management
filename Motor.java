
import java.sql.*;
import java.io.*;
import javax.sql.*;

class Motor {

    static final String DB_URL = "jdbc:mysql://localhost/XE";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "sairam";

    public static void main(String args[]) {
        Connection con;
        Statement state;
        ResultSet rs;
        int ch;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver Loaded");
            con = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Statement object created");

            do {
                System.out.println("\n");
                System.out.println("Menu:");
                System.out.println("1.Show all the Records ");
                System.out.println("2.Insert a new Record");
                System.out.println("3.Update a Record.");                
                System.out.println("4.Delete a Records ");
                System.out.println("5.Add new column in the table ");
                System.out.println("6.Create a table ");
                System.out.println("7.Exit");
                System.out.println("Enter your choice: ");

                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                ch = Integer.parseInt(br.readLine());

                switch (ch) {
                    case 1:
                        state = con.createStatement();
                        String sql = "select * from Employee_Motors";
                        rs = state.executeQuery(sql);
                        while (rs.next()) {
                            System.out.println("\n");
                            System.out.print("\t" + rs.getInt(1));
                            System.out.print("\t" + rs.getString(2));
                            System.out.print("\t" + rs.getString(3));
                            System.out.print("\t" + rs.getInt(4));
                            System.out.print("\t" + rs.getInt(5));
                        }
                        break;


                    case 2:
                        System.out.println("Enter Employee Number: ");
                        int no = Integer.parseInt(br.readLine());
                        System.out.println("Enter Employee Name: ");
                        String name = br.readLine();
                        System.out.println("Enter Employee Address: ");
                        String address = br.readLine();
                        System.out.println("Enter Employee Salary: ");
                        int sal = Integer.parseInt(br.readLine());
                        System.out.println("Enter Employee Dept No.: ");
                        int deptno = Integer.parseInt(br.readLine());
                        sql = "insert into Employee_Motors values(?,?,?,?,?)";
                        PreparedStatement p = con.prepareStatement(sql);
                        p.setInt(1, no);
                        p.setString(2, name);
                        p.setString(3, address);
                        p.setInt(4, sal);
                        p.setInt(5, deptno);

                        p.executeUpdate();
                        System.out.println("Record Added");
                        //p.close();
                        //con.close();
                        break;

                    case 3:
                        System.out.println("Enter Employee Number for the record you wish to Update: ");
                        no = Integer.parseInt(br.readLine());
                        System.out.println("Enter new Salary: ");
                        sal = Integer.parseInt(br.readLine());
                        sql = "update Employee_Motors set empsalary=? where empid=?";
                        p = con.prepareStatement(sql);
                        p.setInt(1, sal);
                        p.setInt(2, no);
                        p.executeUpdate();
                        System.out.println("Record Updated");
                        //p.close();
                        //con.close();
                        break;

                    
                    
                    case 4:
                        System.out.println("Enter Employee Number for the record you wish to Delete: ");
                        no = Integer.parseInt(br.readLine());
                        sql = "delete from Employee_Motors  where empid=?";
                        p = con.prepareStatement(sql);
                        p.setInt(1, no);
                        p.executeUpdate();
                        System.out.println("Record Deleted");
                        break;

                    //System.exit(0);
                    case 5:
                        //System.out.println("Enter column name: ");
                        //String columnname = br.readLine();
                        sql = "alter table Employee_Motors add commission int(4)";
                        p = con.prepareStatement(sql);
                        //p.setString(1, columnname);
                        p.executeUpdate();
                        System.out.println("Table altered");
                        break;

                    case 6:
                    //create table
                    case 7:
                        System.exit(0);
                    default:
                        System.out.println("Invalid Choice");
                        break;
                }
            } while (ch != 7);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
