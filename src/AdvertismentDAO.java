
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
		//� faire
		return true;
	}
	
	public boolean updateAd(Advertisment Ad)
	{
		//� faire
		return true;
	}
	
	public long getLastID()
	{
		//� faire
		return 0;
	}
	
	
}
