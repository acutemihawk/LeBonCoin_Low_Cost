package view;

import java.util.Scanner;

import controller.*;
import model.*;

public class View
{
	private Scanner myScanner = new Scanner(System.in);
	private String[] arrOfStr;
	private MainController mainController = new MainController();
	
	public void mainMenu()
	{
		System.out.println("Choose one option from below to navigate :");
		System.out.println("1 - Sign in");
		System.out.println("2 - Continue without signing in");
		System.out.println("3 - Create account");
		int x = 0;
		if(x == 1)
		{
			System.out.println("met tes id");
			String username = "kaaris";
			
			System.out.println("mdp");
			String password = "password";
			
			if(mainController.userConnect(username, password))
			{
				
			}
			else
			{
				
			}
		}
		
		
	}
	
	public void browse()
	{
		
	}
	
	public void createAdvertisment()
	{
		
	}
	
	public void createOffer()
	{
		
	}
	
	public void displayCategories()
	{
		
	}
	
	public void displayAdvertisments()
	{
		
	}
	
}
