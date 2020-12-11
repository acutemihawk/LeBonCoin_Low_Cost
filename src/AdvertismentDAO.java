import java.sql.*;

public class AdvertismentDAO
{
	public boolean insertAd(Advertisment ad)
	{
		Database myDB = new Database();
		Connection myConnection = myDB.connect();
		
		try
		{
			String sqlCommand = "INSERT INTO advertisment VALUES ('"
								+ ad.getLocalisation() + "', '"
								+ ad.getPrice() + "', '"
								+ ad.getDescription() + "', '"
								+ ad.getCategory() + "', '"
								+ ad.getIdOwner() + "');";
			Statement myStatement = myConnection.createStatement();
			myStatement.executeUpdate(sqlCommand);
			
			myStatement.close();
			myDB.disconnect();
			
			return true;
		}
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
			myDB.disconnect();
			return false;
		}
	}
	
	public boolean deleteAd(Advertisment ad)
	{
		Database myDB = new Database();
		Connection myConnection = myDB.connect();
		
		try
		{
			String sqlCommand = "DELETE FROM advertisment WHERE IdAdvertisment='" + ad.getIdAdvertisment() + "';";
			Statement myStatement = myConnection.createStatement();
			myStatement.executeUpdate(sqlCommand);
			
			myStatement.close();
			myDB.disconnect();
			
			return true;
		}
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
			myDB.disconnect();
			return false;
		}
	}
	
	public boolean updateAd(Advertisment ad)
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
