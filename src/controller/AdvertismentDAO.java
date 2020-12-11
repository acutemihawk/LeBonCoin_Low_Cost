package controller;

import java.sql.*;
import model.Advertisment;

public class AdvertismentDAO
{
	public boolean insertAd(Advertisment ad)
	{
		Database myDB = new Database();
		Connection myConnection = myDB.connect();
		
		try
		{
			String sqlCommand = "INSERT INTO advertisment (type, localisation, price,description,category,iduser) VALUES (?,?,?,?,?,?)";
			PreparedStatement myStatement = myConnection.prepareStatement(sqlCommand,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			myStatement.setString(1, ad.getType());
			myStatement.setString(2, ad.getLocalisation());
			myStatement.setFloat(3, ad.getPrice());
			myStatement.setString(4, ad.getDescription());
			myStatement.setString(5, ad.getCategory());
			myStatement.setLong(6, ad.getIdOwner());
			myStatement.executeUpdate();
			
			myStatement.close();
			myDB.disconnect();
			
			return true;
		}
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
			myDB.disconnect();
			return false;
		}
	}
	
	public boolean deleteAd(Advertisment ad)
	{
		Database myDB = new Database();
		Connection myConnection = myDB.connect();
		
		try
		{
			String sqlCommand = "DELETE FROM advertisment WHERE IdAdvertisment='" + ad.getIdAdvertisment() + "';";
			Statement myStatement = myConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			myStatement.executeUpdate(sqlCommand);
			
			myStatement.close();
			myDB.disconnect();
			
			return true;
		}
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
			myDB.disconnect();
			return false;
		}
	}
	
	/*public boolean updateAd(Advertisment ad)
	{
		Database myDB = new Database();
		Connection myConnection = myDB.connect();
		
		try
		{
			String sqlCommand;
			Statement myStatement = myConnection.createStatement();
			
			sqlCommand = "UPDATE advertisment SET type='" + ad.getType() + "' WHERE idAdvertisment='" + ad.getIdAdvertisment() + "';";
			myStatement.executeUpdate(sqlCommand);
			sqlCommand = "UPDATE advertisment SET localisation='" + ad.getLocalisation() + "' WHERE idAdvertisment='" + ad.getIdAdvertisment() + "';";
			myStatement.executeUpdate(sqlCommand);
			sqlCommand = "UPDATE advertisment SET price='" + ad.getPrice() + "' WHERE idAdvertisment='" + ad.getIdAdvertisment() + "';";
			myStatement.executeUpdate(sqlCommand);
			sqlCommand = "UPDATE advertisment SET description='" + ad.getDescription() + "' WHERE idAdvertisment='" + ad.getIdAdvertisment() + "';";
			myStatement.executeUpdate(sqlCommand);
			sqlCommand = "UPDATE advertisment SET category='" + ad.getCategory() + "' WHERE idAdvertisment='" + ad.getIdAdvertisment() + "';";
			myStatement.executeUpdate(sqlCommand);
			
			myStatement.close();
			myDB.disconnect();
			
			return true;
		}
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
			myDB.disconnect();
			return false;
		}
	}*/
	
	public long getLastID()
	{
		Database myDB = new Database();
		Connection myConnection = myDB.connect();
		long id = 0;
		
		try
		{
			String sqlCommand = " SELECT idAdvertisment FROM advertisment ORDER BY idAdvertisment DESC ";
			
			Statement myStatement = myConnection.createStatement();
			
			ResultSet rs  = myStatement.executeQuery(sqlCommand);
			
			if(rs.next())
			{
				id = rs.getInt(1);
			}
			
			myStatement.close();
			myDB.disconnect();
			return id;
		}
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
			myDB.disconnect();
			return 0;
		}
	}
	
	
}