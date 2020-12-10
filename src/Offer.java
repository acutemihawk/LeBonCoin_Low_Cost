
public class Offer
{
	private int idOffer;
	private int idAdvertisment;
	private int idBuyer;
	private int newPrice;
	
	
	public Offer(int idOff, int idAd, int idBuy, int newP)
	{
		idOffer = idOff;
		idAdvertisment = idAd;
		idBuyer = idBuy;
		newPrice = newP;
	}
	
	public int getIdOffer()
	{
		return idOffer;
	}
	
	public void setIdOffer(int idOffer)
	{
		this.idOffer = idOffer;
	}
	
	public int getIdAdvertisment()
	{
		return idAdvertisment;
	}
	
	public void setIdAdvertisment(int idAdvertisment)
	{
		this.idAdvertisment = idAdvertisment;
	}
	
	public int getIdBuyer()
	{
		return idBuyer;
	}
	
	public void setIdBuyer(int idBuyer)
	{
		this.idBuyer = idBuyer;
	}
	
	public int getNewPrice()
	{
		return newPrice;
	}
	
	public void setNewPrice(int newPrice)
	{
		this.newPrice = newPrice;
	}
	
	
	
	
	
}
