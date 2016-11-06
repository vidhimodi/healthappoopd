package main;

import java.util.Scanner;
import view.Healthapp;
/*
 * this will start the healthapp
 */
public class Runner {

	private static Scanner in2;

	/* 
	 * The 
	 * main 
	 * method
	 */
	public static void main(String[] args) {
		Healthapp healthapp = new Healthapp();
		String func;
		in2 = new Scanner(System.in);
		while(true){
			System.out.println("type Login or Register or exit");
			func=in2.next();
			if(func.equals("Login")){
				healthapp.login();
			}
			else if(func.equals("Register")){
				healthapp.register(healthapp);
			}
			else{
				break;
			}
		}	
	}

}
