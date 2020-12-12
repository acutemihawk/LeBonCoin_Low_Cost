
public class Offer
{
	private long idOffer;
	private long idAdvertisment;
	private long idBuyer;
	private float newPrice;
	private OfferDAO myOfDAO;
	
	public Offer(long idAd, long idBuy, float Offer_newPrice)
	{
		idAdvertisment = idAd;
		idBuyer = idBuy;
		newPrice = Offer_newPrice;
		myOfDAO = new OfferDAO();
	}
	
	public Offer()
	{
		myOfDAO = new OfferDAO();
	}
	
	public long getIdOffer()
	{
		return idOffer;
	}
	
	public void setIdOffer(long idOffer)
	{
		this.idOffer = idOffer;
	}
	
	public long getIdAdvertisment()
	{
		return idAdvertisment;
	}
	
	public void setIdAdvertisment(long idAdvertisment)
	{
		this.idAdvertisment = idAdvertisment;
	}
	
	public long getIdBuyer()
	{
		return idBuyer;
	}
	
	public void setIdBuyer(long idBuyer)
	{
		this.idBuyer = idBuyer;
	}
	
	public float getNewPrice()
	{
		return newPrice;
	}
	
	public void setNewPrice(float newPrice)
	{
		this.newPrice = newPrice;
	}
	
	public OfferDAO getMyOfferDao()
	{
		return myOfDAO;
	}
	
	public void setMyOfferDao(OfferDAO myOfferDao)
	{
		this.myOfDAO = myOfferDao;
	}
	
	public boolean addOffer()
	{
		if(myOfDAO.insertOf(this) == true)
		{
			idOffer = myOfDAO.getLastID();
			
			return true;
		}
		else
			return false;
	}
	
	public boolean removeOffer()
	{
		if(myOfDAO.deleteOf(this)==true)
			return true;
		else
			return false;
	}
	
	
	
}
