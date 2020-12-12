package main;

import controller.*;
import model.*;

public class Main {

	public static void main(String[] args) 
	{

		MainController MainC1 = new MainController();
		MainC1.userConnect("batiste", "azerty");
		
		//USR1.userConnect("batpiste", "azerty");
		//MainC1.createAccount("batpiste", "azerty", "baptiste@gmail.com");
		//USR1.userConnect("batiste", "azerty");
		//MainC1.addUserAdvertisment("voiture", "paris",500, "belle voiture","automobile");
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
