import java.util.ArrayList;

public class Advertisment
{
	private	int idAdvertisment;
	private int idOwner;
	private String type;
	private String category;
	private float price;
	private String description;
	private ArrayList<Offer> listMyOffer;
	
	
	public Advertisment(int idAd, int idOwn, String ty, String categ, float pri, String desc, ArrayList<Offer> offerList)
	{
		idAdvertisment = idAd;
		idOwner = idOwn;
		type = ty;
		category = categ;
		price = pri;
		description = desc;
		listMyOffer = offerList;
	}
	
	public int getIdAdvertisment()
	{
		return idAdvertisment;
	}
	
	public void setIdAdvertisment(int idAdvertisment)
	{
		this.idAdvertisment = idAdvertisment;
	}
	
	public int getIdOwner()
	{
		return idOwner;
	}
	
	public void setIdOwner(int idOwner)
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
	
	public ArrayList<Offer> getListMyOffer()
	{
		return listMyOffer;
	}
	
	public void setListMyOffer(ArrayList<Offer> listMyOffer)
	{
		this.listMyOffer = listMyOffer;
	}
	
	
	
	
}
