package controller;

import java.sql.*;
import java.util.ArrayList;

import model.Offer;

// TODO: Auto-generated Javadoc
/**
 * The Class OfferDAO.
 */
public class OfferDAO
{
	
	/**
	 * Insert an offer into the database.
	 *
	 * @param Of the offer
	 * @return true, if successful
	 */
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
	
	/**
	 * Insert the information of an offer into the database.
	 *
	 * @param Of the offer
	 * @return true, if successful
	 */
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

	/**
	 * Delete an offer in the database.
	 *
	 * @param Of the offer you want to delete
	 * @return true, if successful
	 */
	public boolean deleteOf(Offer Of)
	{
		Database myDB = new Database();
		Connection myConnection = myDB.connect();
		
		try
		{
			String sqlCommand = "DELETE FROM offer WHERE idoffer= ?";
			PreparedStatement myStatement = myConnection.prepareStatement(sqlCommand,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			myStatement.setLong(1, Of.getIdOffer());
			myStatement.executeUpdate();
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
	
	/**
	 * Delete offer information of an offer.
	 *
	 * @param Of the offer
	 * @return true, if successful
	 */
	private boolean deleteOfferInfo(Offer Of)
	{
		Database myDB = new Database();
		Connection myConnection = myDB.connect();
		try
		{
			String sqlCommand = "DELETE FROM offerinfo WHERE idoffer= ?";
			PreparedStatement myStatement = myConnection.prepareStatement(sqlCommand,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			myStatement.setLong(1, Of.getIdOffer());
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
	
	/**
	 * Delete all offers of an advertisment in the database.
	 *
	 * @param Of the offer
	 * @return true, if successful
	 */
	public boolean deleteAllOffer(Offer Of)
	{
		Database myDB = new Database();
		Connection myConnection = myDB.connect();
		
		try
		{
			String sqlCommand = "DELETE FROM offer WHERE idAdvertisment= ?";
			PreparedStatement myStatement = myConnection.prepareStatement(sqlCommand,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			myStatement.setLong(1, Of.getIdAdvertisment());
			myStatement.executeUpdate();
			
			ArrayList<Integer> arrayTmpDel= new ArrayList<Integer>();
			arrayTmpDel = selectAllOfferInfo(Of);
			
			for( Integer offerId : arrayTmpDel)
			{
				deleteAllOfferInfo(offerId);
			}
			
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
	
	/**
	 * Delete all offer information of the offers of an advertisment.
	 *
	 * @param idOf the id of
	 * @return true, if successful
	 */
	private boolean deleteAllOfferInfo(long idOf)
	{
		Database myDB = new Database();
		Connection myConnection = myDB.connect();
		try
		{
			String sqlCommand = "DELETE FROM offerinfo WHERE idoffer= ?";
			PreparedStatement myStatement = myConnection.prepareStatement(sqlCommand,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			myStatement.setLong(1, idOf);
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
	
	/**
	 * Select all offer information of an idvertisment from the database.
	 *
	 * @param Of the of
	 * @return the array list
	 */
	private ArrayList<Integer> selectAllOfferInfo(Offer Of)
	{
		Database myDB = new Database();
		Connection myConnection = myDB.connect();
		try
		{
			String sqlCommand = "SELECT idoffer FROM offer WHERE idAdvertisment= ?";
			PreparedStatement myStatement = myConnection.prepareStatement(sqlCommand,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			myStatement.setLong(1, Of.getIdAdvertisment());
			ResultSet rs = myStatement.executeQuery();
			
			ArrayList<Integer> arrayTmpDel= new ArrayList<Integer>();
			
			while(rs.next())
			{
				arrayTmpDel.add((int) rs.getLong(1));
			}
			
			myStatement.close();
			myDB.disconnect();
			
			return arrayTmpDel;
		}
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
			myDB.disconnect();
			return null;
		}
	}
	
	/**
	 * Gets the ID of the alst offer created.
	 *
	 * @return the last offer ID
	 */
	public long getLastOfferID()
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
	
	/**
	 * Gets the advertisment ID corresponding to an offer.
	 *
	 * @param Of the offer
	 * @return the advertisment ID
	 */
	public long getAdvID(Offer Of)
	{
		Database myDB = new Database();
		Connection myConnection = myDB.connect();
		long id = 0;
		
		try
		{
			String sqlCommand = " SELECT idAdvertisment FROM offer WHERE idoffer= ?";
			
			PreparedStatement myStatement = myConnection.prepareStatement(sqlCommand,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			myStatement.setLong(1, Of.getIdOffer());
			ResultSet rs = myStatement.executeQuery();
			
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
	
	/**
	 * Gets the id of the user you has created the offer
	 *
	 * @param Of the offer
	 * @return the user ID
	 */
	public long getUserID(Offer Of)
	{
		Database myDB = new Database();
		Connection myConnection = myDB.connect();
		long id = 0;
		
		try
		{
			String sqlCommand = " SELECT iduser FROM offer WHERE idoffer= ?";
			
			PreparedStatement myStatement = myConnection.prepareStatement(sqlCommand,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			myStatement.setLong(1, Of.getIdOffer());
			ResultSet rs = myStatement.executeQuery();
			
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
	

	/**
	 * Gets the new price of an advertisment's item from the offer made.
	 *
	 * @param Of the offer
	 * @return the new price from the offer
	 */
	public float getNewPrice(Offer Of) 
	{
		Database myDB = new Database();
		Connection myConnection = myDB.connect();
		float newPrice =0;
		try
		{
			String sqlCommand = " SELECT price_offer FROM offerinfo WHERE idoffer= ?";
			
			PreparedStatement myStatement = myConnection.prepareStatement(sqlCommand,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			myStatement.setLong(1, Of.getIdOffer());
			ResultSet rs = myStatement.executeQuery();
			
			if(rs.next())
			{
				newPrice = rs.getFloat(1);
			}
			
			myStatement.close();
			myDB.disconnect();
			return newPrice;
		}
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
			myDB.disconnect();
			return 0;
		}
	}
	
}