package controller;
import java.sql.*;
import model.*;
import java.util.ArrayList;

public class UserDAO 
{
	public UserDAO() {}
	
	public int getUserId(User myUser)
	{
		Database myBdd = new Database();
		Connection myConnection = myBdd.connect();
		int id = -1;
		
		try
		{
			String SQL = " SELECT iduser FROM USER WHERE username = ?";
			
			PreparedStatement myStatement = myConnection.prepareStatement(SQL);
			myStatement.setString(1, myUser.getUsername());
			ResultSet rs  = myStatement.executeQuery();
			
			if(rs.next() != false)
			{
				 id = rs.getInt(1);
			}
			myBdd.disconnet();
			return id;
		}
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
			System.exit(-1);
			myBdd.disconnet();
			return -1;
		}
		
	}
	
	
	public ArrayList<Integer> getUserListAdv(User myUser)
	{
		Database myBdd = new Database();
		Connection myConnection = myBdd.connect();
		ArrayList<Integer> myList = new ArrayList<Integer>();
		
		try
		{
			String SQL = " SELECT idAdvertisment FROM ADVERTISMENT WHERE iduser=?";
			PreparedStatement myStatement = myConnection.prepareStatement(SQL);
			myStatement.setLong(1, myUser.getIdUser());
			ResultSet rs  = myStatement.executeQuery();
			
			while(rs.next())
			{
				myList.add(rs.getInt(1));
			}
			myBdd.disconnet();
			return myList;

		}
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
			myBdd.disconnet();
			return myList;
		}
	}
	
	public ArrayList<Integer> getUserListOffer(User myUser)
	{
		Database myBdd = new Database();
		Connection myConnection = myBdd.connect();
		ArrayList<Integer> myList = new ArrayList<Integer>();
		
		try
		{
			String SQL = " SELECT idoffer FROM OFFER WHERE iduser=?";
			PreparedStatement myStatement = myConnection.prepareStatement(SQL);
			myStatement.setLong(1, myUser.getIdUser());
			ResultSet rs  = myStatement.executeQuery();
			
			while(rs.next())
			{
				myList.add(rs.getInt(1));
			}
			myBdd.disconnet();
			return myList;
		}
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
			myBdd.disconnet();
			return myList;
		}
	}
	
	public String getUserMail(User myUser)
	{
		Database myBdd = new Database();
		Connection myConnection = myBdd.connect();
		String mailTmp ="";
		try
		{
			String SQL = " SELECT mail FROM USER WHERE iduser=?";
			PreparedStatement myStatement = myConnection.prepareStatement(SQL);
			myStatement.setLong(1, myUser.getIdUser());
			ResultSet rs  = myStatement.executeQuery();
			
			while(rs.next())
			{
				mailTmp = rs.getNString(1);
			}
			myBdd.disconnet();
			return mailTmp;
		}
		catch (SQLException e) 
		{
			myBdd.disconnet();
			System.out.println(e.getMessage());
			return mailTmp;
		}
		
	}
	
	

	public boolean usernameInputChecker(String username)
	{
		Database myBdd = new Database();
		Connection myConnection = myBdd.connect();
		int countTmp = -1;
		
		try
		{
			String SQL = " SELECT COUNT(iduser) FROM USER WHERE username = ?";
			
			PreparedStatement myStatement = myConnection.prepareStatement(SQL);
			myStatement.setString(1, username );
			ResultSet myResult = myStatement.executeQuery();
			
			if(myResult.next() != false)
			{
				countTmp = myResult.getInt(1);
			}
			if(countTmp == 1)
				return true;
			else
			{
				return false;	
			}
					
		}
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
			return false;
		}
		
	}
}
