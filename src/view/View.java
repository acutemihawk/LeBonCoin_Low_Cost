package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		
		try
		{
			option_number = myScanner.nextInt();

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
					System.out.println("Error: Could not connect, please verify your username or password.");
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
	
	//fonction qui affiche le nouveau menu une fois que l'utilisateur s'est co
	public void connectedUser()
	{
		
	}
	
	//fonction parcourir
	public void browse()
	{
		String[] categoryArray = null;
		
		Database myDB = new Database();
		Connection myConnection = myDB.connect();
		
		try
		{
			String sqlCommand = "SELECT DISTINCT catégorie FROM advertisment";
			Statement myStatement = myConnection.createStatement();
			ResultSet rs  = myStatement.executeQuery(sqlCommand);
			
			if(rs.next())
			{
				for(int categoryLoop = 1; categoryLoop < rs.getFetchSize(); categoryLoop++)
				{
					categoryArray[categoryLoop-1] = rs.getString(categoryLoop);
				}
			}
			
			myStatement.close();
			myDB.disconnect();
		}
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
			myDB.disconnect();
		}
		
		System.out.println("--------------------------------------------------");
		System.out.println("Choose one option from below and press Enter to navigate :");
		System.out.println("1 - Search with parameters");
		System.out.println("2 - Return");
		
		int numberToDisplay = 0;
		
		for(int categoryLoop = 0; categoryLoop < categoryArray.length; categoryLoop++)
		{
			numberToDisplay = categoryLoop + 3;
			System.out.println(numberToDisplay + " - " + categoryArray[categoryLoop]);
		}
		System.out.println("--------------------------------------------------");
		
		try
		{
			option_number = myScanner.nextInt();
			
			if(option_number == 1)
			{
				search();
			}
			else if(option_number == 2)
			{
				if(mainController.getMyUser().isConnected())
					connectedUser();
				else
					mainMenu();
			}
			else if(option_number > 2)
			{
				//check si la valeur entrée est supérieure à 2 
				//et inférieure au nb de catégories
				//ensuite on appelle la méthode display qui display les advertisments par catégories
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
	
	//fonction creation d'un compte
	public void createAccount()
	{
		String username;
		String password;
		String mail;
		
		System.out.println("username:");
		given_Str = myScanner.nextLine();
		username = given_Str;
		
		System.out.println("password:");
		given_Str = myScanner.nextLine();
		password = given_Str;

		System.out.println("email address:");
		given_Str = myScanner.nextLine();
		mail = given_Str;
		
		mainController.createAccount(username, password, mail);
		
		if(mainController.userConnect(username, password))
		{
			mainController.getMyUser().setConnected(true);
			connectedUser();
		}
		else
		{
			System.out.println("Error: Could not connect, please verify your username or password.");
			mainMenu();
		}
	}
	
	//fonction search avec paramètres
	public void search()
	{
		String category = null;
		String localisation = null;
		String minPrice = null;
		String maxPrice = null;
		//*******************************************penser à changer prix en long osef des float***

		try
		{
			option_number = myScanner.nextInt();
			
			if(option_number == 1)
			{
				System.out.println("Category:");
				given_Str = myScanner.nextLine();
				category = given_Str;
			}
			else if(option_number == 2)
			{
				System.out.println("Localisation:");
				given_Str = myScanner.nextLine();
				localisation = given_Str;
			}
			else if(option_number == 3)
			{
				System.out.println("Minimum price:");
				given_Str = myScanner.nextLine();
				minPrice = given_Str;
			}
			else if(option_number == 4)
			{
				System.out.println("Maximum price:");
				given_Str = myScanner.nextLine();
				maxPrice = given_Str;
			}
			else if(option_number == 5)
			{
				browse();
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
		
		String[] arrayToSearch = {category, localisation, minPrice, maxPrice};
		
		Database myDB = new Database();
		Connection myConnection = myDB.connect();
		
		try
		{
			String sqlCommand = "SELECT  FROM advertisment";
			
			for(int commandLoop = 0; commandLoop < arrayToSearch.length; commandLoop++)
			{
				if(arrayToSearch[commandLoop] != null)
				{
					switch(commandLoop)
					{
						case 0:
							sqlCommand += " WHERE category=?";
						case 1:
							
					}
				}
			}
			
			Statement myStatement = myConnection.createStatement();
			ResultSet rs  = myStatement.executeQuery(sqlCommand);
			
			if(rs.next())
			{
				
			}
			
			myStatement.close();
			myDB.disconnect();
		}
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
			myDB.disconnect();
		}
		
	}
	
	
	//fonction creation d'une annonce
	/*public void createAdvertisment()
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
		
	}*/
	
	
	
}
