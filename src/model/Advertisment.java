package model;

import java.util.ArrayList;

public class Advertisment
{
	private	long idAdvertisment;
	private long idOwner;
	private float price;
	private String titre;
	private String category;
	private String localisation;
	private String description;
	private ArrayList<Offer> listMyOffer;
	
	public Advertisment(long idOwn, String Ad_titre, String Ad_category, String Ad_localisation, float Ad_price, String Ad_desc)
	{
		idOwner = idOwn;
		titre = Ad_titre;
		category = Ad_category;
		localisation = Ad_localisation;
		price = Ad_price;
		description = Ad_desc;
		listMyOffer = new ArrayList<Offer>();
	}
	
	public Advertisment() 
    {
        listMyOffer = new ArrayList<Offer>();
    }
	
	public long getIdAdvertisment()
	{
		return idAdvertisment;
	}
	
	public void setIdAdvertisment(long idAdvertisment)
	{
		this.idAdvertisment = idAdvertisment;
	}
	
	public long getIdOwner()
	{
		return idOwner;
	}
	
	public void setIdOwner(long idOwner)
	{
		this.idOwner = idOwner;
	}
	
	public String getTitre()
	{
		return titre;
	}
	
	public void setTitre(String titre)
	{
		this.titre = titre;
	}
	
	public String getCategory()
	{
		return category;
	}
	
	public void setCategory(String category)
	{
		this.category = category;
	}
	
	public float getPrice()
	{
		return price;
	}
	
	public void setPrice(float price)
	{
		this.price = price;
	}
	
	public String getLocalisation()
	{
		return localisation;
	}
	
	public void setLocalisation(String localisation)
	{
		this.localisation = localisation;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public ArrayList<Offer> getListMyOffer()
	{
		return listMyOffer;
	}
	
	public void setListMyOffer(ArrayList<Offer> listMyOffer)
	{
		this.listMyOffer = listMyOffer;
	}
	

	
	
	
}