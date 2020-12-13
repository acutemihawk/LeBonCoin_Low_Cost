
import controller.*;

public class Main {

	public static void main(String[] args) 
	{

		UserController USR1 = new UserController();
		USR1.userConnect("kaaris", "12345");
		
		System.out.println(USR1.getMyUser().getIdUser());
		System.out.println(USR1.getMyUser().getMail());
		//System.out.println(USR1.getMyUser().getPassword());
		//System.out.println(USR1.getMyUser().getListAdvertisment());
		//System.out.println(USR1.getMyUser().getListOffer());




	}

}