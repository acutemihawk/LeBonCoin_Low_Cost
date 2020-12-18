package controller;

import java.sql.*;
import java.util.ArrayList;

import model.Advertisment;

// TODO: Auto-generated Javadoc
/**
 * The Class AdvertismentDAO.
 */
public class AdvertismentDAO
{
	
	/**
	 * Insert ad.
	 *
	 * @param ad the ad
	 * @return true, if successful
	 */
	/* créer l'annonce passée en parametere dans la base de donnée */
	public boolean insertAd(Advertisment ad)
	{
		Database myDB = new Database();
		Connection myConnection = myDB.connect();
		
		try
		{
			String sqlCommand = "INSERT INTO advertisment (titre, localisation, price,description,category,iduser) VALUES (?,?,?,?,?,?)";
			PreparedStatement myStatement = myConnection.prepareStatement(sqlCommand,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			myStatement.setString(1, ad.getTitre());
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
	
	/**
	 * Delete ad.
	 *
	 * @param ad the ad
	 * @return true, if successful
	 */
	/* delete de la base de donnée l'annonce passée en parametre */
	public boolean deleteAd(Advertisment ad)
	{
		Database myDB = new Database();
		Connection myConnection = myDB.connect();
		
		try
		{
			String sqlCommand = "DELETE FROM advertisment WHERE IdAdvertisment=?";
			PreparedStatement myStatement = myConnection.prepareStatement(sqlCommand,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			myStatement.setLong(1, ad.getIdAdvertisment());
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
	 * Gets the last ID.
	 *
	 * @return the last ID
	 */
	/*renvoie le dernier id de l'annonce inserée */
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
	
	/**
	 * Search.
	 *
	 * @param category the category
	 * @param minPrice the min price
	 * @param maxPrice the max price
	 * @param localisation the localisation
	 * @return the array list
	 */
	/* envoie le resultat de la recherche avec les parametre titre, prix min, prix max, category */
	public ArrayList<Advertisment> search(String category, float minPrice, float maxPrice,String localisation)
	{
		Database myDB = new Database();
		Connection myConnection = myDB.connect();
		ArrayList<Advertisment> myArrayList = new ArrayList<Advertisment>();
		
		try
		{
			//l'id, le titre, le prix, et le titre
			String sqlCommand = "SELECT idAdvertisment,localisation,price,category,titre FROM advertisment WHERE LOWER(category) LIKE ? AND price > ? and price < ? AND LOWER(localisation) LIKE ? " ;
			
			PreparedStatement myStatement = myConnection.prepareStatement(sqlCommand,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			myStatement.setString(4, "%"+localisation.toLowerCase()+"%");
			myStatement.setString(1, "%"+category.toLowerCase()+"%");
			myStatement.setFloat(2, minPrice);
			myStatement.setFloat(3, maxPrice);
			ResultSet myResultSet = myStatement.executeQuery();
			
			while(myResultSet.next())
			{
				Advertisment myAdvToReturn = new Advertisment();
				myAdvToReturn.setIdAdvertisment(myResultSet.getLong(1));
				myAdvToReturn.setLocalisation(myResultSet.getString(2));	
				myAdvToReturn.setPrice(myResultSet.getFloat(3));
				myAdvToReturn.setCategory(myResultSet.getString(4));
				myAdvToReturn.setTitre(myResultSet.getString(5));
				myArrayList.add(myAdvToReturn);
			}
			
			myStatement.close();
			myDB.disconnect();

			return myArrayList;
		}
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
			myDB.disconnect();
			return null;
		}
	}
	
	/**
	 * Gets the category.
	 *
	 * @return the category
	 */
	public ArrayList<String> getCategory()
	{
		Database myDB = new Database();
		Connection myConnection = myDB.connect();
		
		ArrayList<String> arrayToReturn = new ArrayList<String>();
		
		try
		{
			String sqlCommand = "SELECT DISTINCT category FROM advertisment";
			Statement myStatement = myConnection.createStatement();
			ResultSet rs  = myStatement.executeQuery(sqlCommand);
			
			while(rs.next())
			{
				arrayToReturn.add(rs.getString(1));
			}				
			
			myStatement.close();
			myDB.disconnect();
			
			return arrayToReturn;
		}
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
			myDB.disconnect();
			return null;
		}		
	}
	
	/**
	 * Gets the advertisments from category.
	 *
	 * @param category the category
	 * @return the advertisments from category
	 */
	public ArrayList<Advertisment> getAdvertismentsFromCategory(String category)
	{
		Database myDB = new Database();
		Connection myConnection = myDB.connect();
		ArrayList<Advertisment> myArrayList = new ArrayList<Advertisment>();
		
		try
		{
			String sqlCommand = "SELECT idAdvertisment,localisation,price,category,titre FROM advertisment WHERE LOWER(category) LIKE ?" ;
			
			PreparedStatement myStatement = myConnection.prepareStatement(sqlCommand,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			myStatement.setString(1, "%"+category.toLowerCase()+"%");
			ResultSet myResultSet = myStatement.executeQuery();
			
			while(myResultSet.next())
			{
				Advertisment myAdvToReturn = new Advertisment();
				myAdvToReturn.setIdAdvertisment(myResultSet.getLong(1));
				myAdvToReturn.setLocalisation(myResultSet.getString(2));	
				myAdvToReturn.setPrice(myResultSet.getFloat(3));
				myAdvToReturn.setCategory(myResultSet.getString(4));
				myAdvToReturn.setTitre(myResultSet.getString(5));
				myArrayList.add(myAdvToReturn);
			}
			
			myStatement.close();
			myDB.disconnect();

			return myArrayList;
		}
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
			myDB.disconnect();
			return null;
		}
	}
	
	/**
	 * Gets the user advertisments.
	 *
	 * @param idUser the id user
	 * @return the user advertisments
	 */
	public ArrayList<Advertisment> getUserAdvertisments(long idUser)
	{
		Database myDB = new Database();
		Connection myConnection = myDB.connect();
		ArrayList<Advertisment> myArrayList = new ArrayList<Advertisment>();
		
		try
		{
			String sqlCommand = "SELECT idAdvertisment,localisation,price,category,titre FROM advertisment WHERE iduser=?";
			
			PreparedStatement myStatement = myConnection.prepareStatement(sqlCommand,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			myStatement.setLong(1, idUser);
			ResultSet myResultSet = myStatement.executeQuery();
			
			while(myResultSet.next())
			{
				Advertisment myAdvToReturn = new Advertisment();
				myAdvToReturn.setIdAdvertisment(myResultSet.getLong(1));
				myAdvToReturn.setLocalisation(myResultSet.getString(2));	
				myAdvToReturn.setPrice(myResultSet.getFloat(3));
				myAdvToReturn.setCategory(myResultSet.getString(4));
				myAdvToReturn.setTitre(myResultSet.getString(5));
				myArrayList.add(myAdvToReturn);
			}
			
			myStatement.close();
			myDB.disconnect();

			return myArrayList;
		}
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
			myDB.disconnect();
			return null;
		}
	}
	
	/**
	 * Verify.
	 *
	 * @param ad the ad
	 * @return true, if successful
	 */
	/*verifie que l'annonce passée en parametre existe dans la base de donnée */
	public boolean verify(Advertisment ad) 
	{
		Database myDB = new Database();
		Connection myConnection = myDB.connect();
		
		try
		{
			String sqlCommand = " SELECT COUNT(*) FROM advertisment where idAdvertisment=?";
			
			PreparedStatement myStatement = myConnection.prepareStatement(sqlCommand);
			myStatement.setLong(1, ad.getIdAdvertisment());
			ResultSet myResult  = myStatement.executeQuery();
			
			if(myResult.next() != false)
			{
				if(myResult.getInt(1) == 1)
				{	
					myDB.disconnect();
					myStatement.close();
					return true;
				}
				else
				{
					System.out.println("The advertisment does not exist");
					myDB.disconnect();
					myStatement.close();
					return false;	
				}
			}
			return false;
		}
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
			myDB.disconnect();
			return false;
		}	
	}
}