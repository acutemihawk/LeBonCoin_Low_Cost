package controller;

import java.sql.*;
import java.util.ArrayList;

import model.Offer;

public class OfferDAO
{
	/* insere dans la table offer l'offre passée en parametre */
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
	
	/* delete de la table offre l'offre passée en parametre puis appelle la fonction deleteOfferInfo */
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
	
	/* delete dans la table offreinfo les informations de l'offre passée en parametre dans la base de données */
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
	
	/* supprimes toutes les offres sur l'id de l'annonce passé en parametre puis appelle deleteAllOfferInfo*/
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
	
	private ArrayList<Integer> selectAllOfferInfo(Offer Of)
	{
		Database myDB = new Database();
		Connection myConnection = myDB.connect();
		try
		{
			String sqlCommand = "SELECT idoffer FROM offer WHERE idAdvertisment= ?";
			PreparedStatement myStatement = myConnection.prepareStatement(sqlCommand,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			myStatement.setLong(1, Of.getIdOffer());
			ResultSet rs = myStatement.executeQuery();
			
			ArrayList<Integer> arrayTmpDel= new ArrayList<Integer>();
			
			while(rs.next() != false)
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
	
	/* renvoie l'id offer de l'offer appelant */
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
	
	/* renvoie l'id de l'utilisateur qui a fait l'offre passé en parametre */
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
	
}