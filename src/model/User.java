package model;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class User.
 */
public class User 
{
	
	/** The id user. */
	private long idUser;
	
	/** The username. */
	private String username;
	
	/** The password. */
	private String password;
	
	/** The mail. */
	private String mail;
	
	/** The is connected. */
	private boolean isConnected = false;
	
	/** The list advertisment. */
	private ArrayList<Integer> listAdvertisment;
	
	/** The list offer. */
	private ArrayList<Integer> listOffer;
	
	
	/**
	 * Instantiates a new user.
	 *
	 * @param idUser the id user
	 * @param username the username
	 * @param mail the mail
	 */
	public User(long idUser,String username,String mail)
	{
		this.idUser = idUser;
		this.username = username;
		this.mail = mail;
		listAdvertisment = new ArrayList<Integer>();
		listOffer = new ArrayList<Integer>();
	}
	
	/**
	 * Instantiates a new user.
	 */
	public User()
	{
		listAdvertisment = new ArrayList<Integer>();
		listOffer = new ArrayList<Integer>();
	}
	
	/**
	 * Gets the id user.
	 *
	 * @return the id user
	 */
	public long getIdUser() 
	{
		return idUser;
	}
	
	/**
	 * Sets the id user.
	 *
	 * @param idUser the new id user
	 */
	public void setIdUser(long idUser) 
	{
		this.idUser = idUser;
	}
	
	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() 
	{
		return username;
	}
	
	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) 
	{
		this.username = username;
	}
	
	/**
	 * Gets the mail.
	 *
	 * @return the mail
	 */
	public String getMail() 
	{
		return mail;
	}
	
	/**
	 * Sets the mail.
	 *
	 * @param mail the new mail
	 */
	public void setMail(String mail) 
	{
		this.mail = mail;
	}
	
	/**
	 * Checks if is connected.
	 *
	 * @return true, if is connected
	 */
	public boolean isConnected() 
	{
		return isConnected;
	}
	
	/**
	 * Sets the connected.
	 *
	 * @param isConnected the new connected
	 */
	public void setConnected(boolean isConnected) 
	{
		this.isConnected = isConnected;
	}
	
	/**
	 * Gets the list advertisment.
	 *
	 * @return the list advertisment
	 */
	public ArrayList<Integer> getListAdvertisment() 
	{
		return listAdvertisment;
	}
	
	/**
	 * Sets the list advertisment.
	 *
	 * @param listAdvertisment the new list advertisment
	 */
	public void setListAdvertisment(ArrayList<Integer> listAdvertisment) 
	{
		this.listAdvertisment = listAdvertisment;
	}
	
	/**
	 * Gets the list offer.
	 *
	 * @return the list offer
	 */
	public ArrayList<Integer> getListOffer() 
	{
		return listOffer;
	}
	
	/**
	 * Sets the list offer.
	 *
	 * @param listOffer the new list offer
	 */
	public void setListOffer(ArrayList<Integer> listOffer) 
	{
		this.listOffer = listOffer;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
}