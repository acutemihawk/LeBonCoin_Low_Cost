package controller;
import java.sql.*;

public class Database 
{
	private Connection myCon;
	
	public Database() {}
	
	public Connection connect()
	{
		try 
		{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
		catch (ClassNotFoundException e) 
		{
            System.out.println(e);
            System.exit(1);
            return null;
		}
		try
		{
			myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/bddsgbd?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC","root", "");
			//System.out.println("Succesful connection !");
			return myCon;
		}
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public boolean disconnect()
	{
		try
		{
			myCon.close();
			//System.out.println("Succesfully disconnected from database !");
			return true;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public boolean authentificate(String username, String password)
	{
		if(myCon == null)
		{
			System.out.println("Error, could not reach to database");		
		}
		try
		{	
			String SQL = " SELECT username,password FROM USER WHERE username = ? AND password = ?";
			
			PreparedStatement myStatement = myCon.prepareStatement(SQL);
			myStatement.setString(1, username );
			myStatement.setString(2, password );
			ResultSet myResult = myStatement.executeQuery();
			
			if(myResult.next() == false)
			{
				System.out.println("Username ou/et mot de passe erroné(s), vérifiez que vous disposez bien d'un compte !");
				return false;
			}
			else
			{
				System.out.println("Logged in !");
				return true;
			}
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			return false;
		}
	
	}

}