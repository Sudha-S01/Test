package com.mindtree.application;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
//import java.sql.Date;
import com.mindtree.dao.DaoFactory;
import com.mindtree.entity.Covid;
import com.mindtree.dao.CovidDao;
import java.util.regex.Pattern;
import java.util.function.Predicate;
import java.util.InputMismatchException;

import java.util.stream.Collectors;


 class Covd extends Exception{
	Scanner scan = new Scanner(System.in);
	
	CovidDao covidDao = DaoFactory.createCovidDao();
	List<Covid> list = covidDao.findAll();
	List<String> listStates = covidDao.findStates();
	List<String> listDistricts = covidDao.findDistricts(null);
	List<Covid> listConfirmed = covidDao.findByDate(null, null);
	List<Covid> listCompareDtState = covidDao.compareDtState(null, null, null, null);
	
	void getStates()
	{
		
		listStates = covidDao.findStates();
								
		List<String> States = listStates.stream().distinct().sorted().collect(Collectors.toList());
		if (States == null || States.isEmpty()){
			System.out.println("No records fetched.");
		}
		else {
			System.out.println("States : ");
			States.stream().forEach(System.out::println);
					
			}
	}
	
	void getDistricts() {
		
		System.out.print("Please enter state code :");
		Scanner sc1 = new Scanner(System.in);
		String input1 = sc1.nextLine();
		boolean pred;
		
		listStates = covidDao.findStates();
		List<String> States = listStates.stream().distinct().sorted().collect(Collectors.toList());
		
		boolean match = States.stream().anyMatch(s -> s.contains(input1));
		
		listDistricts = covidDao.findDistricts(input1);
				
		List<String> Districts = listDistricts.stream().distinct().sorted().collect(Collectors.toList());
		
		if (match)
		{
			System.out.println("Districts : ");
			Districts.stream().forEach(System.out::println);
		}
		else {
			System.out.println("No records fetched.");
		}				
	}
	
	
	void compareDtStates() throws ParseException
	{
		
		System.out.print("Please enter start date (yyyy-MM-dd) : ");
		Scanner sc1 = new Scanner(System.in);
		String input1 = sc1.nextLine();
							
		Date dt1 =new SimpleDateFormat("yyyy-mm-dd").parse(input1);  
		System.out.print("Please enter end date (yyyy-MM-dd) : ");
		Scanner sc2 = new Scanner(System.in);
		
		String input2 = sc2.next();	
									
		Date dt2 = new SimpleDateFormat("yyyy-mm-dd").parse(input2);
				
		
		
		listConfirmed = covidDao.findByDate(input1, input2);
		
		if (listConfirmed == null || listConfirmed.isEmpty()){
			System.out.println("No records fetched.");
		}
		else 
		{
			
			Covid obj = new Covid();
			System.out.println("Data by state within date range");
			
			System.out.println("       Date|State|Confirmed total ");
			
			listConfirmed.stream().forEach(listC -> System.out.println(listC.getDate() +" |  " + listC.getState() + " | " + listC.getConfirmed()));
			
		}		
	}
	
	
	void compareDtRangeState() throws ParseException
	{
		
		System.out.print("Please enter start date (yyyy-MM-dd) : ");
		Scanner sc1 = new Scanner(System.in);
		String input1 = sc1.nextLine();
		Date dt1 =new SimpleDateFormat("yyyy-mm-dd").parse(input1);  
		
		System.out.print("Please enter end date (yyyy-MM-dd) : ");
		Scanner sc2 = new Scanner(System.in);
		String input2 = sc2.next();	
		Date dt2 = new SimpleDateFormat("yyyy-mm-dd").parse(input2);
							
		System.out.print("Please enter First State code :");
		Scanner sc3 = new Scanner(System.in);
		String input3 = sc3.nextLine();
		
		System.out.print("Please enter Second State code :");
		Scanner sc4 = new Scanner(System.in);
		String input4 = sc4.nextLine();
		
		listCompareDtState = covidDao.compareDtState(input1, input2, input3, input4);
		
		if (listCompareDtState == null || listCompareDtState.isEmpty()){
			System.out.println("No records fetched.");
		}
		else 
		{
			System.out.println("Confirmed cases by comparing two states in the given date range");
			
			System.out.println("       Date|  First State  | First Confirmed total | Second State | Second Confirmed total ");

			listCompareDtState.stream().forEach(listCDS -> System.out.println(listCDS.getDate() +" |       " + listCDS.getfirstState() + "      |        " + listCDS.getfirstConfirmed() + "             |      "+ listCDS.getsecState() + "      |        " + listCDS.getsecConfirmed()));
								
		}
																	
		
}
	
}

public class CovidController {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {
		
		Covd covd = new Covd();
		
		while(true) {			
					
			Scanner sc = new Scanner(System.in);
			try {
			System.out.println("**************************************************************************");
			System.out.println("1. Get State Name");
			System.out.println("2. Get District for given state");
			System.out.println("3. Display Data by state within date range");
			System.out.println("4. Display Confirmed cases by compaing two states for a given date range");
			System.out.println("5. Exit");
			System.out.print("Please select Option :");
			
			int input = sc.nextInt();
			switch(input)
			{
			case 1: covd.getStates();break;
			case 2: covd.getDistricts();break;
			case 3: covd.compareDtStates();break;
			case 4: covd.compareDtRangeState();break;
			case 5: System.out.println("Application Stopped"); System.exit(0);break;
			default: System.out.println("Please enter valid input option (1 to 5) as shown in above menu."); break;
			}
			}catch(InputMismatchException e){
				System.out.println(e);
				System.out.println("Please enter valid input (1 to 5) as shown in above menu.");
			
			}
		}		
	}
}


