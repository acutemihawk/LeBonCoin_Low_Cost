package controller;
import java.sql.*;
import model.*;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class UserDAO.
 */
public class UserDAO 
{
	
	/**
	 * Instantiates a new user DAO.
	 */
	public UserDAO() {}
	
	/**
	 * Gets the user id.
	 *
	 * @param myUser the my user
	 * @return the user id
	 */
	/* renvoie l'id de l'utilisateur passé en parametere */
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
			myStatement.close();
			myBdd.disconnect();
			return id;
		}
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
			myBdd.disconnect();
			return -1;
		}
		
	}
	
	/**
	 * Gets the user name.
	 *
	 * @param myUser the my user
	 * @return the user name
	 */
	public String getUserName(User myUser)
    {
        Database myBdd = new Database();
        Connection myConnection = myBdd.connect();
        String name = "";
        
        try
        {
            String SQL = " SELECT username FROM USER WHERE iduser = ?";
            
            PreparedStatement myStatement = myConnection.prepareStatement(SQL);
            myStatement.setLong(1, myUser.getIdUser());
            ResultSet rs  = myStatement.executeQuery();
            
            if(rs.next() != false)
            {
                name = rs.getString(1);
            }
            myStatement.close();
            myBdd.disconnect();
            return name;
        }
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
            myBdd.disconnect();
            return name;
        }    
    }
	
	/**
	 * Gets the user list adv.
	 *
	 * @param myUser the my user
	 * @return the user list adv
	 */
	/* renvoie la liste d'advertisment de l'utilisateur passé en parametre*/
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
			myStatement.close();
			myBdd.disconnect();
			return myList;

		}
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
			myBdd.disconnect();
			return myList;
		}
	}
	
	/**
	 * Gets the user list offer.
	 *
	 * @param myUser the my user
	 * @return the user list offer
	 */
	public ArrayList<Integer> getUserListOffer(User myUser)
	{
		Database myBdd = new Database();
		Connection myConnection = myBdd.connect();
		ArrayList<Integer> myList = new ArrayList<Integer>();
		try
		{
			String SQL = "SELECT offer.idoffer FROM advertisment "
					+ "INNER JOIN OFFER ON advertisment.idAdvertisment= offer.idAdvertisment "
					+ "INNER JOIN offerinfo USING(idoffer)"
					+ " WHERE advertisment.iduser= ?"; 
			
			PreparedStatement myStatement = myConnection.prepareStatement(SQL);
			myStatement.setLong(1, myUser.getIdUser());
			ResultSet rs  = myStatement.executeQuery();
			
			while(rs.next())
			{
				myList.add((int) rs.getLong(1));
			}
			
			myStatement.close();
			myBdd.disconnect();
			return myList;
		}
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
			myBdd.disconnect();
			return myList;
		}
	}
	/*
	public ArrayList<Offer> getUserListOffer(User myUser)
	{
		Database myBdd = new Database();
		Connection myConnection = myBdd.connect();
		ArrayList<Offer> myList = new ArrayList<Offer>();
		
		try
		{
			String SQL = "SELECT advertisment.idAdvertisment,offer.iduser,offerinfo.price_offer, offer.idoffer FROM advertisment "
					+ "INNER JOIN OFFER ON advertisment.idAdvertisment= offer.idAdvertisment "
					+ "INNER JOIN offerinfo USING(idoffer)"
					+ " WHERE advertisment.iduser= ?"; 
			
			PreparedStatement myStatement = myConnection.prepareStatement(SQL);
			myStatement.setLong(1, myUser.getIdUser());
			ResultSet rs  = myStatement.executeQuery();
			
			while(rs.next())
			{
				Offer Of = new Offer();
				Of.setIdAdvertisment(rs.getLong(1));
				Of.setNewPrice(rs.getFloat(3));
				Of.setIdBuyer(rs.getLong(2));
				Of.setIdOffer(rs.getLong(4));
				myList.add(Of);
			}
			myStatement.close();
			myBdd.disconnect();
			return myList;
		}
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
			myBdd.disconnect();
			return myList;
		}
	}*/
	
	/**
	 * Gets the user list propositions.
	 *
	 * @param myUser the my user
	 * @return the user list propositions
	 */
	/* renvoie la liste des propositions faites par l'utilisateur passé en parametre */
	public ArrayList<Integer> getUserListPropositions(User myUser)
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
			myStatement.close();
			myBdd.disconnect();
			return myList;
		}
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
			myBdd.disconnect();
			return myList;
		}
	}
	
	/**
	 * Gets the user mail.
	 *
	 * @param myUser the my user
	 * @return the user mail
	 */
	/*renvoie le mail de l'utilsiateur passé en parametre */
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
			myStatement.close();
			myBdd.disconnect();
			return mailTmp;
		}
		catch (SQLException e) 
		{
			myBdd.disconnect();
			System.out.println(e.getMessage());
			return mailTmp;
		}	
	}
	
	/**
	 * Insert user.
	 *
	 * @param userToCreate the user to create
	 * @return true, if successful
	 */
	public boolean insertUser(User userToCreate)
	{
		Database myBdd = new Database();
		Connection myConnection = myBdd.connect();
		try
		{
			String SQL = " INSERT INTO USER (username,password,mail) VALUES (?,?,?)";
			PreparedStatement myStatement = myConnection.prepareStatement(SQL,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			myStatement.setString(1, userToCreate.getUsername());
			myStatement.setString(2, userToCreate.getPassword());
			myStatement.setString(3, userToCreate.getMail());
			myStatement.executeUpdate();
			myStatement.close();
			return true;
		}
		catch (SQLException e) 
		{
			myBdd.disconnect();
			System.out.println(e.getMessage());
			return false;
		}
		
	}
	
	/**
	 * Username input checker.
	 *
	 * @param username the username
	 * @return true, if successful
	 */
	/* renvoie false si le nom username est deja utilisé, sinon vrai*/
	public boolean usernameInputChecker(String username)
	{
		Database myBdd = new Database();
		Connection myConnection = myBdd.connect();
		
		try
		{
			String SQL = " SELECT COUNT(iduser) FROM USER WHERE username = ?";
			
			PreparedStatement myStatement = myConnection.prepareStatement(SQL);
			myStatement.setString(1, username );
			ResultSet myResult = myStatement.executeQuery();
			
			if(myResult.next() != false)
			{
				if(myResult.getInt(1) == 0)
				{
					myBdd.disconnect();
					myStatement.close();
					return true;
				}
				else
				{
					System.out.println("username is already in use");
					myBdd.disconnect();
					myStatement.close();
					return false;	
				}
			}
			return false;
		}
		catch (SQLException e) 
		{
			myBdd.disconnect();
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	/**
	 * Mail input checker.
	 *
	 * @param mail the mail
	 * @return true, if successful
	 */
	/* renvoie false si le nom username est deja utilisé, sinon vrai*/
	public boolean mailInputChecker(String mail)
	{
		Database myBdd = new Database();
		Connection myConnection = myBdd.connect();
		
		try
		{
			String SQL = " SELECT COUNT(iduser) FROM USER WHERE mail = ?";
			
			PreparedStatement myStatement = myConnection.prepareStatement(SQL);
			myStatement.setString(1, mail );
			ResultSet myResult = myStatement.executeQuery();
			
			if(myResult.next() != false)
			{
				if(myResult.getInt(1) == 0)
				{	
					myBdd.disconnect();
					myStatement.close();
					return true;
				}
				else
				{
					System.out.println("Mail is already in use");
					myBdd.disconnect();
					myStatement.close();
					return false;	
				}
			}
			return false;
		}
		catch (SQLException e) 
		{
			myBdd.disconnect();
			System.out.println(e.getMessage());
			return false;
		}
	}
}