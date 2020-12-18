package model;

// TODO: Auto-generated Javadoc
/**
 * The Class Offer.
 */
public class Offer
{
	
	/** The id offer. */
	private long idOffer;
	
	/** The id advertisment. */
	private long idAdvertisment;
	
	/** The id buyer. */
	private long idBuyer;
	
	/** The new price. */
	private float newPrice;
	
	
	/**
	 * Instantiates a new offer.
	 *
	 * @param idAd the id ad
	 * @param idBuy the id buy
	 * @param Offer_newPrice the offer new price
	 */
	public Offer(long idAd, long idBuy, float Offer_newPrice)
	{
		idAdvertisment = idAd;
		idBuyer = idBuy;
		newPrice = Offer_newPrice;
	}
	
	/**
	 * Instantiates a new offer.
	 */
	public Offer()
	{
	}
	
	/**
	 * Gets the id offer.
	 *
	 * @return the id offer
	 */
	public long getIdOffer()
	{
		return idOffer;
	}
	
	/**
	 * Sets the id offer.
	 *
	 * @param idOffer the new id offer
	 */
	public void setIdOffer(long idOffer)
	{
		this.idOffer = idOffer;
	}
	
	/**
	 * Gets the id advertisment.
	 *
	 * @return the id advertisment
	 */
	public long getIdAdvertisment()
	{
		return idAdvertisment;
	}
	
	/**
	 * Sets the id advertisment.
	 *
	 * @param idAdvertisment the new id advertisment
	 */
	public void setIdAdvertisment(long idAdvertisment)
	{
		this.idAdvertisment = idAdvertisment;
	}
	
	/**
	 * Gets the id buyer.
	 *
	 * @return the id buyer
	 */
	public long getIdBuyer()
	{
		return idBuyer;
	}
	
	/**
	 * Sets the id buyer.
	 *
	 * @param idBuyer the new id buyer
	 */
	public void setIdBuyer(long idBuyer)
	{
		this.idBuyer = idBuyer;
	}
	
	/**
	 * Gets the new price.
	 *
	 * @return the new price
	 */
	public float getNewPrice()
	{
		return newPrice;
	}
	
	/**
	 * Sets the new price.
	 *
	 * @param newPrice the new new price
	 */
	public void setNewPrice(float newPrice)
	{
		this.newPrice = newPrice;
	}
	
	
}