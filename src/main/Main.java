package main;

import controller.MainController;
import view.*;

public class Main 
{

	public static void main(String[] args) 
	{

		MainController MainC1 = new MainController();
	
		MainC1.userConnect("batpiste", "azerty");
		MainC1.getUserOffer();
		//MainC1.getPropositionInformation();
		//MainC1.addUserOffer(3, 350);
		//View myView = new View();
		//myView.displayUserPropositions();
		//MainC1.addUserAdvertisment("SMARTPHONE PAS CHER", "paris",680, "Telephone neuf, récent","iphone");
		//MainC1.acceptOffer(17);
		
		//MainC1.userConnect("batpiste", "azerty");
		//MainC1.refuseOffer(20);
		//MainC1.addUserOffer(21, 500);
		
		//MainC1.userConnect("booba", "12345");
		//MainC1.addUserOffer(21, 750);
		//MainC1.refuseOffer(20);
		
		//System.out.println(MainC1.getMyUser().getListOffer());
		//MainC1.delUserOffer(11);
		//System.out.println(MainC1.getMyUser().getListOffer());
		//MainC1.createAccount("batpiste", "azerty", "batpiste@gmail.com");
		//USR1.userConnect("batiste", "azerty");
		//System.out.println(MainC1.getMyUser().getListAdvertisment());
		//MainC1.delUserAdvertisment(19);
		//System.out.println(MainC1.getMyUser().getListAdvertisment());

		//System.out.println(MainC1.getMyUser().getIdUser());
		//System.out.println(MainC1.getMyUser().getMail());
		//System.out.println(MainC1.getMyUser().getPassword());
		//System.out.println(MainC1.getMyUser().getListAdvertisment());
		//System.out.println(MainC1.getMyUser().getListOffer());
	}

}
