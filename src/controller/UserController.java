package controller;

import model.*;

public class UserController 
{
	private User myUser;
	private UserDAO myUserDAO;

	
	public UserController() 
	{
		myUserDAO = new UserDAO();
	}
	
	public boolean userConnect(String username, String password)
	{
		Database myBdd = new Database();
		myBdd.connect();
		
		if(myBdd.authentificate(username, password) == true)
		{
			myUser = new User();
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
		Advertisment myAdv = new Advertisment();
		myAdv.setType(type);
		myAdv.setCategory(category);
		myAdv.setDescription(description);
		myAdv.setIdOwner(myUser.getIdUser());
		myAdv.setPrice(price);
		myAdv.setLocalisation(localisation);
	
		if (myAdv.publishAdvertisment() == true);
		{
			myUser.getListAdvertisment().add((int) myAdv.getIdAdvertisment());
			System.out.println("Advertisment successfully created");
			return true;
		}
		// ne pas oublier de set idAdv, add dans la listADv
	}
	
	public boolean delUserAdvertisment(long idAdvToDel)
	{
		Advertisment myAdvToDel = new Advertisment();
		myAdvToDel.setIdAdvertisment(idAdvToDel);
		if(myAdvToDel.removeAdvertisment() == true)
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
	
	/*public boolean addUserOffer(long idAdv,float newPrice)
	{
		Offer myOffer = new Offer();
	}*/
	
	public User getMyUser() 
	{
		return myUser;
	}

	public void setMyUser(User myUser) 
	{
		this.myUser = myUser;
	}

}
