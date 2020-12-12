package model;

import java.util.ArrayList;

public class User 
{
	private long idUser;
	private String username;
	private String password;
	private String mail;
	private boolean isConnected = false;
	private ArrayList<Integer> listAdvertisment;
	private ArrayList<Integer> listOffer;
	
	
	public User(long idUser,String username,String mail)
	{
		this.idUser = idUser;
		this.username = username;
		this.mail = mail;
		listAdvertisment = new ArrayList<Integer>();
		listOffer = new ArrayList<Integer>();
	}
	
	public User()
	{
		listAdvertisment = new ArrayList<Integer>();
		listOffer = new ArrayList<Integer>();
	}
	
	public long getIdUser() 
	{
		return idUser;
	}
	
	public void setIdUser(long idUser) 
	{
		this.idUser = idUser;
	}
	
	public String getUsername() 
	{
		return username;
	}
	
	public void setUsername(String username) 
	{
		this.username = username;
	}
	
	public String getMail() 
	{
		return mail;
	}
	
	public void setMail(String mail) 
	{
		this.mail = mail;
	}
	
	public boolean isConnected() 
	{
		return isConnected;
	}
	
	public void setConnected(boolean isConnected) 
	{
		this.isConnected = isConnected;
	}
	
	public ArrayList<Integer> getListAdvertisment() 
	{
		return listAdvertisment;
	}
	
	public void setListAdvertisment(ArrayList<Integer> listAdvertisment) 
	{
		this.listAdvertisment = listAdvertisment;
	}
	
	public ArrayList<Integer> getListOffer() 
	{
		return listOffer;
	}
	
	public void setListOffer(ArrayList<Integer> listOffer) 
	{
		this.listOffer = listOffer;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
