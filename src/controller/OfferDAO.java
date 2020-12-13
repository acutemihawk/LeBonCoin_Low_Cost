package controller;

import java.sql.*;

import model.Offer;

public class OfferDAO
{
	public boolean insertOf(Offer Of)
	{
		Database myDB = new Database();
		Connection myConnection = myDB.connect();
		
		try
		{
			String sqlCommand = "INSERT INTO offer (idoffer,idAdvertisment, iduser) VALUES (?,?,?)";
			PreparedStatement myStatement = myConnection.prepareStatement(sqlCommand, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			myStatement.setLong(1, Of.getIdOffer());
			myStatement.setLong(2, Of.getIdAdvertisment());
			myStatement.setLong(3, Of.getIdBuyer());
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
	
	public boolean deleteOf(Offer Of)
	{
		Database myDB = new Database();
		Connection myConnection = myDB.connect();
		
		try
		{
			String sqlCommand = "DELETE FROM offer WHERE Idoffer='" + Of.getIdOffer() + "';";
			Statement myStatement = myConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			myStatement.executeUpdate(sqlCommand);
			deleteOfferInfo(Of);
			
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
	
	private boolean deleteOfferInfo(Offer Of)
	{
		Database myDB = new Database();
		Connection myConnection = myDB.connect();
		try
		{
			String sqlCommand = "DELETE FROM offerinfo WHERE Idoffer='" + Of.getIdOffer() + "';";
			Statement myStatement = myConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
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
	
	/*public boolean updateOf(Offer Of)
	{
		return true;
	}*/
	
	/* renvoie l'id offer de l'offer appelant */
	public long getLastID()
	{
		Database myDB = new Database();
		Connection myConnection = myDB.connect();
		long id = 0;
		
		try
		{
			String sqlCommand = " SELECT idoffer FROM offerinfo ORDER BY idoffer DESC";
			
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

	public boolean insertInformation(Offer Of) 
	{
		Database myDB = new Database();
		Connection myConnection = myDB.connect();
		
		try
		{
			String sqlCommand =" INSERT INTO offerinfo (idoffer,price_offer) VALUES (?,?)";
			PreparedStatement myStatement = myConnection.prepareStatement(sqlCommand, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			myStatement.setLong(1, Of.getIdOffer());
			myStatement.setFloat(2, Of.getNewPrice());
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
}