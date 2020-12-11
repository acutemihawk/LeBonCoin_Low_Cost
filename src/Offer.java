
public class Offer
{
	private long idOffer;
	private long idAdvertisment;
	private long idBuyer;
	private float newPrice;
	private OfferDAO myOfferDao;
	
	public Offer(long idOff, long idAd, long idBuy, float Offer_newPrice, OfferDAO OfferDAO)
	{
		idOffer = idOff;
		idAdvertisment = idAd;
		idBuyer = idBuy;
		newPrice = Offer_newPrice;
		myOfferDao = OfferDAO;
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
		return myOfferDao;
	}
	
	public void setMyOfferDao(OfferDAO myOfferDao)
	{
		this.myOfferDao = myOfferDao;
	}
	
	public void addOffer()
	{
		//à faire
	}
	
	
	
}
