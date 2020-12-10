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
			myBdd.disconnet();
			return true;
		}
		myBdd.disconnet();
		return false;
		
	}

	public User getMyUser() 
	{
		return myUser;
	}

	public void setMyUser(User myUser) 
	{
		this.myUser = myUser;
	}

/*	public void load() 
	{
		Database myBdd2 = new Database();
		myBdd2.connect();
		
		myUserDAO.getUserId(myUser);
		
		
		myBdd2.disconnet();
	}*/

}
