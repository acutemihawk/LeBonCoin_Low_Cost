
public class AdvertismentDAO
{
	public boolean insertAd(Advertisment Ad)
	{
		Database myDB = new Database();
		myDB.connect();
		
		
		return true;
	}
	
	public boolean deleteAd(Advertisment Ad)
	{
		//à faire
		return true;
	}
	
	public boolean updateAd(Advertisment Ad)
	{
		//à faire
		return true;
	}
	
	public long getLastID()
	{
		//à faire
		return 0;
	}
	
	
}
