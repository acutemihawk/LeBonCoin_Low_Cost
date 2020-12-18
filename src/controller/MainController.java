package controller;

import java.util.ArrayList;

import model.*;

// TODO: Auto-generated Javadoc
/**
 * The Class MainController.
 */
public class MainController 
{
	
	/** The my user. */
	private User myUser;
	
	/** The my user DAO. */
	private UserDAO myUserDAO;
	
	/** The my adv. */
	private Advertisment myAdv;
	
	/** The my adv DAO. */
	private AdvertismentDAO myAdvDAO;
	
	/** The my offer. */
	private Offer myOffer;
	
	/** The my of DAO. */
	private OfferDAO myOfDAO;

	
	/**
	 * Instantiates a new main controller.
	 */
	public MainController() 
	{
		myUser = new User();
		myOffer = new Offer();
		myAdv = new Advertisment();
		myAdvDAO = new AdvertismentDAO();
		myUserDAO = new UserDAO();
		myOfDAO = new OfferDAO();
	}
	
	/**
	 * User connect.
	 *
	 * @param username the username
	 * @param password the password
	 * @return true, if successful
	 */
	public boolean userConnect(String username, String password)
	{
		Database myBdd = new Database();
		myBdd.connect();
		
		if(myBdd.authentificate(username, password) == true)
		{
			myUser.setUsername(username);
			myUser.setPassword(password);
			myUser.setIdUser(myUserDAO.getUserId(myUser));
			myUser.setListAdvertisment(myUserDAO.getUserListAdv(myUser));
			myUser.setListOffer(myUserDAO.getUserListPropositions(myUser));
			myUser.setMail(myUserDAO.getUserMail(myUser));
			myUser.setConnected(true);
			myBdd.disconnect();
			return true;
		}
		myBdd.disconnect();
		return false;
	}
	
	/**
	 * Creates the account.
	 *
	 * @param username the username
	 * @param password the password
	 * @param mail the mail
	 * @return true, if successful
	 */
	public boolean createAccount(String username,String password, String mail)
	{
		Database myBdd = new Database();
		myBdd.connect();
		
		if(myUserDAO.usernameInputChecker(username) == true && myUserDAO.mailInputChecker(mail) == true)
		{
			User myUserTmp = new User();
			myUserTmp.setUsername(username);
			myUserTmp.setPassword(password);
			myUserTmp.setMail(mail);
			
			if(myUserDAO.insertUser(myUserTmp) == true)
			{
				myBdd.disconnect();
				System.out.println("User successfully created");
				return true;
			}
			else
				return false;
		}
		else
		{
			myBdd.disconnect();
			return false;	
		}
	}
	
	/**
	 * Adds the user advertisment.
	 *
	 * @param titre the titre
	 * @param localisation the localisation
	 * @param price the price
	 * @param description the description
	 * @param category the category
	 * @return true, if successful
	 */
	/* Ajoute une annonce sur le site */
	public boolean addUserAdvertisment(String titre, String localisation, float price, String description,String category)
	{
		if (testConnection() == false)
			return false;
		
		if(price <0)
        {
            System.out.println("Please put a price above 0...");
            return false;
        }
		
		myAdv.setTitre(titre);
		myAdv.setCategory(category);
		myAdv.setDescription(description);
		myAdv.setIdOwner(myUser.getIdUser());
		myAdv.setPrice(price);
		myAdv.setLocalisation(localisation);
		
		if (myAdvDAO.insertAd(myAdv) == true)
		{
			myAdv.setIdAdvertisment(myAdvDAO.getLastID()); 
			myUser.getListAdvertisment().add((int) myAdv.getIdAdvertisment());
			System.out.println("Advertisment successfully created");
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Del user advertisment.
	 *
	 * @param idAdvToDel the id adv to del
	 * @return true, if successful
	 */
	/* Retire l'annonce sur le site ayant pour id=idAdvToDel */
	@SuppressWarnings("unlikely-arg-type")
	public boolean delUserAdvertisment(long idAdvToDel)
	{
		if (testConnection() == false)
			return false;
		
		Advertisment myAdvToDel = new Advertisment();
		myAdvToDel.setIdAdvertisment(idAdvToDel);
		if(myAdvDAO.deleteAd(myAdvToDel) == true)
		{
			if(myUser.getListAdvertisment().remove(myAdvToDel.getIdAdvertisment()) == false)
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
	/**
	 * Adds the user proposition.
	 *
	 * @param idAdv the id adv
	 * @param newPrice the new price
	 * @return true, if successful
	 */
	/* fait une proposition avec newPrice sur l'annonce ayant idAdv pour id */
	public boolean addUserProposition(long idAdv,float newPrice)
	{
		if (testConnection() == false)
			return false;
		
		if(newPrice <0)
		{
			System.out.println("Please put a price that is above 0$");
			return false;
		}
		
		Advertisment adTmp = new Advertisment();
		adTmp.setIdAdvertisment(idAdv);
		if(myAdvDAO.verify(adTmp) == false)
			return false;
			
		this.myOffer.setIdAdvertisment(idAdv);
		this.myOffer.setNewPrice(newPrice);
		this.myOffer.setIdBuyer(myUser.getIdUser());
		
		if(myOfDAO.insertInformation(myOffer) ==true) 
		{
			myOffer.setIdOffer(myOfDAO.getLastOfferID());
			myUser.getListOffer().add((int) myOffer.getIdOffer());
			myOfDAO.insertOf(myOffer);
			
			System.out.println("Offer successfully created !");
			return true;
		}
		else
			return false;
	}

	/**
	 * Del user proposition.
	 *
	 * @param idOffer the id offer
	 * @return true, if successful
	 */
	/* Supprime une proposition faite sur une annonce avec l'id de la proposition en parametre */
	public boolean delUserProposition(long idOffer)
    {
        if (testConnection() == false)
            return false;

        Offer offerToDel = new Offer();
        offerToDel.setIdOffer(idOffer);
        if(myOfDAO.deleteOf(offerToDel) == true)
        {
            for (Integer loop : myUser.getListOffer())
            {
                if (loop ==offerToDel.getIdOffer())
                {    
                    myUser.getListOffer().remove(loop);
                    return true;
                }
                else
                    return false;
            }
        }
        return false;
    }
	
	/**
	 * Accept offer.
	 *
	 * @param idOffer the id offer
	 * @return true, if successful
	 */
	// Accepte l'offre avec pour id = idOffer -> supprime toutes les autres propositions sur l'offre et retire l'annonce de l'application
	public boolean acceptOffer(long idOffer)
	{
		if (testConnection() == false)
			return false;
		
		myOffer.setIdOffer(idOffer);
		
		for (Integer loop : myUser.getListAdvertisment() )
		{
			if(loop == (myOfDAO.getAdvID(myOffer)))
			{
				Advertisment advToDel = new Advertisment();
				advToDel.setIdAdvertisment(myOfDAO.getAdvID(myOffer));
				myOffer.setIdAdvertisment(myOfDAO.getAdvID(myOffer));
				return myOfDAO.deleteAllOffer(myOffer) && myAdvDAO.deleteAd(advToDel);
			}
		}
		System.out.println("Please accept an offer that is addressed to you ");
		return false;
		
}
	
	/**
	 * Refuse offer.
	 *
	 * @param idOffer the id offer
	 * @return true, if successful
	 */
	// Supprime l'offre associé a l'annonce ayant pour id idOffer 
	public boolean refuseOffer(long idOffer)
	{
		if (testConnection() == false)
			return false;
		
		Offer offerToDel = new Offer();
		offerToDel.setIdOffer(idOffer);
		offerToDel.setIdBuyer(myOfDAO.getUserID(offerToDel));

		for (Integer loop : myUser.getListAdvertisment() )
		{
			if(loop == (myOfDAO.getAdvID(offerToDel)))
			{
				return myOfDAO.deleteOf(offerToDel);
			}
		}
		
		System.out.println("Please accept an offer that is addressed to you ");
		return false;
	}
	
	/**
	 * Gets the user propositions.
	 *
	 * @return the user propositions
	 */
	/* returns an arrayList of offer that represents all of the proposition made by the user to different advertisment */
	public ArrayList<Offer> getUserPropositions()
	{
		if (testConnection() == false)
			return null;
		
		ArrayList<Offer> myArrayToReturn = new ArrayList<Offer>();
		
		for (int tmp : myUser.getListOffer())
		{
			Offer Of = new Offer();
			Of.setIdOffer(tmp);
			Of.setIdAdvertisment(myOfDAO.getAdvID(Of));
			Of.setNewPrice(myOfDAO.getNewPrice(Of));
			myArrayToReturn.add(Of);
		}
		return myArrayToReturn;
	}
	
	/**
	 * Gets the user received offer.
	 *
	 * @return the user received offer
	 */
	/* renvoie une listes des offre recues par l'uitlisateurs */
	public ArrayList<Offer> getUserReceivedOffer()
	{
		if (testConnection() == false)
			return null;
		
		ArrayList<Offer> myArrayToReturn = new ArrayList<Offer>();
		
		for (int loop : myUserDAO.getUserListOffer(myUser))
		{
			Offer myOf = new Offer();
			myOf.setIdOffer(loop);
			myOf.setIdAdvertisment(myOfDAO.getAdvID(myOf));
			myOf.setNewPrice(myOfDAO.getNewPrice(myOf));
			myOf.setIdBuyer(myOfDAO.getUserID(myOf));
			myArrayToReturn.add(myOf);
		}
		return myArrayToReturn;
	}
	
	/**
	 * Gets the my user.
	 *
	 * @return the my user
	 */
	public User getMyUser() 
	{
		return myUser;
	}

	/**
	 * Sets the my user.
	 *
	 * @param myUser the new my user
	 */
	public void setMyUser(User myUser) 
	{
		this.myUser = myUser;
	}
	
	/**
	 * Test connection.
	 *
	 * @return true, if successful
	 */
	private boolean testConnection()
	{
		if(myUser.isConnected() == false)
		{
			System.out.println("You need to register to use this functionnality");
			return false;
		}
		else
			return true;
	}

	/**
	 * Gets the my user DAO.
	 *
	 * @return the my user DAO
	 */
	public UserDAO getMyUserDAO() {
		return myUserDAO;
	}

	/**
	 * Sets the my user DAO.
	 *
	 * @param myUserDAO the new my user DAO
	 */
	public void setMyUserDAO(UserDAO myUserDAO) {
		this.myUserDAO = myUserDAO;
	}

	/**
	 * Gets the my of DAO.
	 *
	 * @return the my of DAO
	 */
	public OfferDAO getMyOfDAO() {
		return myOfDAO;
	}

	/**
	 * Sets the my of DAO.
	 *
	 * @param myOfDAO the new my of DAO
	 */
	public void setMyOfDAO(OfferDAO myOfDAO) {
		this.myOfDAO = myOfDAO;
	}

	/**
	 * Gets the my offer.
	 *
	 * @return the my offer
	 */
	public Offer getMyOffer() 
	{
		return myOffer;
	}

	/**
	 * Sets the my offer.
	 *
	 * @param myOffer the new my offer
	 */
	public void setMyOffer(Offer myOffer) 
	{
		this.myOffer = myOffer;
	}

	/**
	 * Gets the my adv DAO.
	 *
	 * @return the my adv DAO
	 */
	public AdvertismentDAO getMyAdvDAO()
	{
		return myAdvDAO;
	}

	/**
	 * Sets the my adv DAO.
	 *
	 * @param myAdvDAO the new my adv DAO
	 */
	public void setMyAdvDAO(AdvertismentDAO myAdvDAO) 
	{
		this.myAdvDAO = myAdvDAO;
	}

	/**
	 * Gets the my adv.
	 *
	 * @return the my adv
	 */
	public Advertisment getMyAdv()
	{
		return myAdv;
	}

	/**
	 * Sets the my adv.
	 *
	 * @param myAdv the new my adv
	 */
	public void setMyAdv(Advertisment myAdv)
	{
		this.myAdv = myAdv;
	}
}
