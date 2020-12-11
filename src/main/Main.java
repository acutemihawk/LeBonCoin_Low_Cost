package main;

import java.util.ArrayList;

import controller.*;
import model.*;

public class Main {

	public static void main(String[] args) 
	{

		UserController USR1 = new UserController();
	//	USR1.userConnect("kaaris", "12345");
		//USR1.createAccount("batiste", "azerty", "batiste@gmail.com");
		USR1.userConnect("batiste", "azerty");
		//USR1.addUserAdvertisment("voiture", "paris",500, "belle voiture","automobile");
		System.out.println(USR1.getMyUser().getListAdvertisment());
		USR1.delUserAdvertisment(14);
		System.out.println(USR1.getMyUser().getListAdvertisment());

		//System.out.println(USR1.getMyUser().getIdUser());
		//System.out.println(USR1.getMyUser().getMail());
		//System.out.println(USR1.getMyUser().getPassword());
		//System.out.println(USR1.getMyUser().getListAdvertisment());
		//System.out.println(USR1.getMyUser().getListOffer());

	}

}
