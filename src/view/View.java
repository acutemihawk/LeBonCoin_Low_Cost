package view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import controller.*;
import model.Advertisment;

public class View
{
	private MainController mainController;
	private Scanner myScanner ;
	private String given_Str;
	private int option_number;
	
	public View()
	{
		mainController = new MainController();
		myScanner = new Scanner(System.in);
		given_Str = "";
		option_number = 0;
		
	}
	
	//fonction du démarrage de l'application
	public void mainMenu()
	{
		System.out.println("----------------------------------------------------------------------");
		System.out.println("Choose one option from below and press Enter to navigate :");
		System.out.println("1 - Sign in");
		System.out.println("2 - Continue without signing in");
		System.out.println("3 - Create account");
		System.out.println("----------------------------------------------------------------------");
		
		try
		{
			option_number = myScanner.nextInt();

			if(option_number == 1)
			{
				System.out.println("username:");
				given_Str = myScanner.next();
				String username = given_Str;
				
				System.out.println("password:");
				given_Str = myScanner.next();
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
			System.out.println("Please enter a number !");
		}
		catch(InputMismatchException myException) //à voir si on doit le garder
		{
			System.out.println("The argument you entered is invalid");
		}
	}
	
	//fonction qui affiche le nouveau menu une fois que l'utilisateur s'est co
	public void connectedUser()
	{
		
	}
	
	//fonction parcourir
	public void browse()
	{
		ArrayList<String> categoriesArray = new ArrayList<String>();
		int numberToDisplay = 0;
		
		categoriesArray = mainController.getMyAdvDAO().getCategory();
		
		System.out.println("----------------------------------------------------------------------");
		System.out.println("Choose one option from below and press Enter to navigate :");
		System.out.println("1 - Search with parameters");
		System.out.println("2 - Return");
		
		for ( int categoryLoop=0 ; categoryLoop < categoriesArray.size(); categoryLoop++)
		{
			numberToDisplay = categoryLoop + 3;
			System.out.println(numberToDisplay + " - " + categoriesArray.get(categoryLoop));
		}
		System.out.println("----------------------------------------------------------------------");	
		
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
			else if(option_number > 2 && option_number < categoriesArray.size()+2)
			{
				//displayCategory(categoriesArray[option_number-3]);
			} 
		}
		catch(NumberFormatException myException)
		{
			System.out.println("Please enter a number !");
		}
		catch(InputMismatchException myException) //à voir si on doit le garder
		{
			System.out.println("The argument you entered is invalid");
		}
	}
	
	//fonction creation d'un compte
	public void createAccount()
	{
		String username;
		String password;
		String mail;
		
		System.out.println("username:");
		given_Str = myScanner.next();
		username = given_Str;
		
		System.out.println("password:");
		given_Str = myScanner.next();
		password = given_Str;

		System.out.println("email address:");
		given_Str = myScanner.next();
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
		int numberToDisplay = 3;
        String category = "";
        String localisation = "";
        float minPrice = 0;
        float maxPrice = 0;
        
        ArrayList<Advertisment> myArrayList = new ArrayList<Advertisment>();
        
        try
        {
            System.out.println("Please, enter some information:");
            
            System.out.println("Category:");
            category = myScanner.next();
            
            System.out.println("Localisation:");
            localisation = myScanner.next();
            
            System.out.println("Minimum price:");
            minPrice = myScanner.nextFloat();

            System.out.println("Maximum price:");
            maxPrice = myScanner.nextFloat();
            
            myArrayList = mainController.getMyAdvDAO().search(category, minPrice, maxPrice, localisation);
            
            
            System.out.println("----------------------------------------------------------------------");
    		System.out.println("Choose one option from below and press Enter to navigate :");
    		System.out.println("1 - Make an offer");
    		System.out.println("2 - Return");
    		
            for( Advertisment advTmp : myArrayList)
            {
            	System.out.println(numberToDisplay+" - "+advTmp.getTitre()+" "+advTmp.getPrice()+" ("+advTmp.getIdAdvertisment()+") ");
            	numberToDisplay++;
            }
            System.out.println("----------------------------------------------------------------------");
            
        }
		catch(NumberFormatException myException)
		{
			System.out.println("Please enter a number !");
		}
		catch(InputMismatchException myException) //à voir si on doit le garder
		{
			System.out.println("The argument you entered is invalid");
		}
    }
	
	
	//fonction qui affiche les categories
/*	public void displayCategory(String category_name)
	{
		String[][] advertismentsArray = null;
		int numberToDisplay = 0;
		int advertismentLoop = 1;
		
		Database myDB = new Database();
		Connection myConnection = myDB.connect();
		
		try
		{
			String sqlCommand = "SELECT idAdvertisment, localisation, price, title FROM advertisment WHERE category=?";
			PreparedStatement myStatement = myConnection.prepareStatement(sqlCommand);
			myStatement.setString(1, category_name);
			ResultSet rs  = myStatement.executeQuery();
			
			while(rs.next())
			{
				advertismentsArray[advertismentLoop-1] = rs.getString(1);
				
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
		System.out.println("1 - Make an offer");
		System.out.println("2 - Return");
		
		for(int advertismentLoop = 0; advertismentLoop < advertismentsArray.length; advertismentLoop++)
		{
			numberToDisplay = advertismentLoop + 3;
			System.out.println(numberToDisplay + " - " + advertismentsArray[advertismentLoop]);
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
			else if(option_number > 2 && option_number < categoriesArray.length+2)
			{
				displayCategory(categoriesArray[option_number-3]);
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
		
	}*/
	
	//fonction creation d'une annonce
	/*public void createAdvertisment()
	{
		
	}
	
	//fonction creation d'une offre
	public void displayUserProposition()
	{
		
	}
	
	
	
	//fonction qui affiche les annonces
	public void displayAdvertisments()
	{
		
	}*/
	
	
	
}