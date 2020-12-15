package view;

import java.util.InputMismatchException;
import java.util.Scanner;

import controller.*;
import model.*;

public class View
{
	private MainController mainController = new MainController();
	private Scanner myScanner = new Scanner(System.in);
	private String given_Str = "";
	private int option_number = 0;
	
	//fonction du démarrage de l'application
	public void mainMenu()
	{
		System.out.println("--------------------------------------------------");
		System.out.println("Choose one option from below and press Enter to navigate :");
		System.out.println("1 - Sign in");
		System.out.println("2 - Continue without signing in");
		System.out.println("3 - Create account");
		System.out.println("--------------------------------------------------");
		
		option_number = myScanner.nextInt();
		
		try
		{
			if(option_number == 1)
			{
				System.out.println("username:");
				given_Str = myScanner.nextLine();
				String username = given_Str;
				
				System.out.println("password:");
				given_Str = myScanner.nextLine();
				String password = given_Str;
				
				if(mainController.userConnect(username, password))
				{
					mainController.getMyUser().setConnected(true);
					connectedUser();
				}
				else
				{
					System.out.println("Could not connect, please verify your username or password.");
					System.out.println("You can also create your account.");
					
					mainMenu();
				}
			}
			else if(option_number == 2)
			{
				System.out.println("Without signing in, you can only browse for advertisments:");
				browse();
			}
			else if(option_number == 3)
			{
				createAccount();
			}
		}
		catch(NumberFormatException myException)
		{
			System.out.println("L'argument de la commande Push doit etre entier !");
		}
		catch(InputMismatchException myException) //à voir si on doit le garder
		{
			System.out.println("L'argument de doit etre un identifiant (nombre entier)");
		}
	}
	
	//fonction parcourir
	public void browse()
	{
		
	}
	
	//fonction creation d'un compte
	public void createAccount()
	{
		
	}
	
	//fonction creation d'une annonce
	public void createAdvertisment()
	{
		
	}
	
	//fonction creation d'une offre
	public void createOffer()
	{
		
	}
	
	//fonction qui affiche les categories
	public void displayCategories()
	{
		
	}
	
	//fonction qui affiche les annonces
	public void displayAdvertisments()
	{
		
	}
	
	//fonction qui affiche le nouveau menu une fois que l'utilisateur s'est co
	public void connectedUser()
	{
		
	}
	
}
