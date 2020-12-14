package main;

import controller.*;

public class Main 
{

	public static void main(String[] args) 
	{

		MainController MainC1 = new MainController();
		//MainC1.userConnect("batpiste", "azerty");
		//MainC1.addUserAdvertisment("Smartphone", "Lyon",150, "Telephone neuf, récent","Android");
		//MainC1.acceptOffer(17);
		
		MainC1.userConnect("batpiste", "azerty");
		MainC1.refuseOffer(20);
		//MainC1.addUserOffer(21, 500);
		
		//MainC1.userConnect("booba", "12345");
		//MainC1.addUserOffer(21, 750);
		
		//System.out.println(MainC1.getMyUser().getListOffer());
		//MainC1.delUserOffer(11);
		//System.out.println(MainC1.getMyUser().getListOffer());
		//MainC1.createAccount("batpiste", "azerty", "baptiste@gmail.com");
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
