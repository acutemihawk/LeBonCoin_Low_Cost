import java.util.ArrayList;

public class Advertisment
{
	private	long idAdvertisment;
	private long idOwner;
	private float price;
	private String type;
	private String category;
	private String localisation;
	private String description;
	private AdvertismentDAO myAdvDAO;
	private ArrayList<Offer> listMyOffer;
	
	
	public Advertisment(long idOwn, String Ad_type, String Ad_category, String Ad_localisation, float Ad_price, String Ad_desc)
	{
		idOwner = idOwn;
		type = Ad_type;
		category = Ad_category;
		localisation = Ad_localisation;
		price = Ad_price;
		description = Ad_desc;
		listMyOffer = new ArrayList<Offer>();
		myAdvDAO = new AdvertismentDAO();
	}
	
	public Advertisment() 
    {
        listMyOffer = new ArrayList<Offer>();
        myAdvDAO = new AdvertismentDAO();
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
	
	public String getType()
	{
		return type;
	}
	
	public void setType(String type)
	{
		this.type = type;
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
	
	public AdvertismentDAO getMyAdvDAO()
	{
		return myAdvDAO;
	}
	
	public void setMyAdvDAO(AdvertismentDAO myAdvDAO)
	{
		this.myAdvDAO = myAdvDAO;
	}
	
	public ArrayList<Offer> getListMyOffer()
	{
		return listMyOffer;
	}
	
	public void setListMyOffer(ArrayList<Offer> listMyOffer)
	{
		this.listMyOffer = listMyOffer;
	}
	
	public boolean publishAdvertisment()
	{
		if(myAdvDAO.insertAd(this) == true)
		{
			idAdvertisment = myAdvDAO.getLastID();
			
			return true;
		}
		else
			return false;
	}
	
	public boolean removeAdvertisment()
	{
		if(myAdvDAO.deleteAd(this)==true)
			return true;
		else
			return false;
	}
	
	
	
}
