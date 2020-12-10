import java.util.ArrayList;

public class Advertisment
{
	private	long idAdvertisment;
	private long idOwner;
	private float price;
	private String type;
	private String category;
	private String description;
	private AdvertismentDAO myAdvDAO;
	private ArrayList<Offer> listMyOffer;
	
	
	public Advertisment(long idAd, long idOwn, String Ad_type, String Ad_category, float Ad_price, String Ad_desc)
	{
		idAdvertisment = idAd;
		idOwner = idOwn;
		type = Ad_type;
		category = Ad_category;
		price = Ad_price;
		description = Ad_desc;
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
	
	public void publishAdvertisment()
	{
		//à faire
	}
	
	
	
}
