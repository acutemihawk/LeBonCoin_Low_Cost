package controller;

import model.*;

public class MainController 
{
	private User myUser;
	private Offer myOffer;
	private Advertisment myAdv;
	private AdvertismentDAO myAdvDAO;
	private UserDAO myUserDAO;
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
	
	public boolean addUserAdvertisment(String type, String localisation, float price, String description,String category)
	{
		if (testConnection() == false)
			return false;
		
		myAdv.setType(type);
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
	
	public boolean addUserOffer(long idAdv,float newPrice)
	{
		if (testConnection() == false)
			return false;
		
		this.myOffer.setIdAdvertisment(idAdv);
		this.myOffer.setNewPrice(newPrice);
		this.myOffer.setIdBuyer(myUser.getIdUser());
		
		
		if(myOfDAO.insertOf(myOffer) == true) 
		{
			myOffer.setIdOffer(myOfDAO.getLastID());
			myUser.getListOffer().add((int) myOffer.getIdOffer());
			myOfDAO.insertInformation(myOffer);
				
			System.out.println("Offer successfully created");
			return true;
		}
		else
			return false;
	}
	
	public boolean delUserOffer(long idAdv)
	{
		if (testConnection() == false)
			return false;
		
		Offer offerToDel = new Offer();
		offerToDel.setIdAdvertisment(idAdv);
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