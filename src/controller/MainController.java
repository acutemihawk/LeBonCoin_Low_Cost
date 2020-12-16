package controller;

import model.*;

public class MainController 
{
	private User myUser;
	private UserDAO myUserDAO;
	private Advertisment myAdv;
	private AdvertismentDAO myAdvDAO;
	private Offer myOffer;
	private OfferDAO myOfDAO;

	
	public MainController() 
	{
		myUser = new User();
		myOffer = new Offer();
		myAdv = new Advertisment();
		myAdvDAO = new AdvertismentDAO();
		myUserDAO = new UserDAO();
		myOfDAO = new OfferDAO();
	}
	
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
			myUser.setListOffer(myUserDAO.getUserListOffer(myUser));
			myUser.setMail(myUserDAO.getUserMail(myUser));
			myUser.setConnected(true);
			myBdd.disconnect();
			return true;
		}
		myBdd.disconnect();
		return false;
	}
	
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
			
			if(myUserDAO.insertUser(myUserTmp) == true);
			{
				myBdd.disconnect();
				System.out.println("User successfully created");
				return true;
			}	
		}
		else
		{
			myBdd.disconnect();
			return false;	
		}
	}
	
	/* Ajoute une annonce sur le site */
	public boolean addUserAdvertisment(String titre, String localisation, float price, String description,String category)
	{
		if (testConnection() == false)
			return false;
		
		myAdv.setTitre(titre);
		myAdv.setCategory(category);
		myAdv.setDescription(description);
		myAdv.setIdOwner(myUser.getIdUser());
		myAdv.setPrice(price);
		myAdv.setLocalisation(localisation);
	
		if (myAdvDAO.insertAd(myAdv) == true);
		{
			myAdv.setIdAdvertisment(myAdvDAO.getLastID()); 
			myUser.getListAdvertisment().add((int) myAdv.getIdAdvertisment());
			System.out.println("Advertisment successfully created");
			return true;
		}
	}
	
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
			if(myUser.getListAdvertisment().remove(myAdvToDel.getIdAdvertisment()) == false);
				return true;
		}
		else
			return false;
	}
	
	/* fait une proposition avec newPrice sur l'annonce ayant idAdv pour id */
	public boolean addUserOffer(long idAdv,float newPrice)
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

	/* Supprime une proposition faite sur une annonce avec l'id de la proposition en parametre */
	@SuppressWarnings("unlikely-arg-type")
	public boolean delUserOffer(long idOffer)
	{
		if (testConnection() == false)
			return false;
		
		Offer offerToDel = new Offer();
		offerToDel.setIdOffer(idOffer);
		if(myOfDAO.deleteOf(offerToDel) == true)
		{
			if (myUser.getListOffer().remove(offerToDel.getIdOffer()) == false);
				return true;
		}
			
		else
			return false;
	}
	
	// Accepte la proposition avec pour id = idOffer -> supprime toutes les autres propositions sur l'offre et retire l'annonce de l'application
	public boolean acceptOffer(long idOffer)
	{
		if (testConnection() == false)
			return false;
		
		myOffer.setIdOffer(idOffer);
		
		if (myOfDAO.getAdvID(myOffer) == 0)
		{
			System.out.println("Veuillez accepter une offre qui vous appartient");
			return false;
		}
		
		Advertisment advToDel = new Advertisment();
		advToDel.setIdAdvertisment(myOfDAO.getAdvID(myOffer));
		
		myOffer.setIdOffer(myOfDAO.getAdvID(myOffer));
		
		return myOfDAO.deleteAllOffer(myOffer) && myAdvDAO.deleteAd(advToDel);
			// mettre dans view System.out.println("Offer number "+idOffer+" successfully accepted, Advertisment "+advToDel.getIdAdvertisment()+" will be removed");


	}
	
	// supprime l'offre associé a l'annonce ayant pour id idOffer 
	public boolean refuseOffer(long idOffer)
	{
		if (testConnection() == false)
			return false;
		
		Offer offerToDel = new Offer();
		offerToDel.setIdOffer(idOffer);
		offerToDel.setIdBuyer(myOfDAO.getUserID(offerToDel));

		if(myUser.getIdUser() != offerToDel.getIdBuyer())
		{
			System.out.println("The proposition you wish to refuse isn't yours, please choose one of your proposition ");
			return false;
		}
		return myOfDAO.deleteOf(offerToDel);
	}
	
	
	public User getMyUser() 
	{
		return myUser;
	}

	public void setMyUser(User myUser) 
	{
		this.myUser = myUser;
	}
	
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

	public Offer getMyOffer() 
	{
		return myOffer;
	}

	public void setMyOffer(Offer myOffer) 
	{
		this.myOffer = myOffer;
	}

	public AdvertismentDAO getMyAdvDAO()
	{
		return myAdvDAO;
	}

	public void setMyAdvDAO(AdvertismentDAO myAdvDAO) 
	{
		this.myAdvDAO = myAdvDAO;
	}

	public Advertisment getMyAdv()
	{
		return myAdv;
	}

	public void setMyAdv(Advertisment myAdv)
	{
		this.myAdv = myAdv;
	}
}