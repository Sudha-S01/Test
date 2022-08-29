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

import java.util.stream.Collectors;

public class CovidAnalysis {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {

		//Scanner sc = new Scanner(System.in);
		CovidDao covidDao = DaoFactory.createCovidDao();
		List<Covid> list = covidDao.findAll();
		List<String> listStates = covidDao.findStates();
		List<String> listDistricts = covidDao.findDistricts(null);
		List<Covid> listConfirmed = covidDao.findByDate(null, null);
		List<Covid> listCompareDtState = covidDao.compareDtState(null, null, null, null);
		
		Scanner sc = new Scanner(System.in);
		System.out.println("1. Get State Name");
		System.out.println("2. Get District for given state");
		System.out.println("3. Display Data by state within date range");
		System.out.println("4. Display Confirmed cases by compaing two states for a given date range");
		System.out.println("5. Exit");
		System.out.print("Please select Option :");
		
		while(true) {
			
			int input = sc.nextInt();
			
			
			if(input == 5) {
				System.exit(0);
			}else if (input == 1 ) {
				System.out.println("States : ");
				listStates = covidDao.findStates();
										
				List<String> States = listStates.stream().distinct().sorted().collect(Collectors.toList());
				
				for(String lst : States) {
					System.out.println(lst);
				}
				
				}
				else if(input == 2 )
				{
					System.out.print("Please enter state code :");
					Scanner sc1 = new Scanner(System.in);
					String input1 = sc1.nextLine();
					System.out.println("Districts: ");
					listDistricts = covidDao.findDistricts(input1);
					
					List<String> Districts = listDistricts.stream().distinct().sorted().collect(Collectors.toList());
					
					for (String str : Districts) {
						System.out.println(str);
					}
					
				}
				else if(input == 3 )
				{
					System.out.print("Please enter start date (yyyy-MM-dd) : ");
					Scanner sc1 = new Scanner(System.in);
					String input1 = sc1.nextLine();
										
					Date dt1 =new SimpleDateFormat("yyyy-mm-dd").parse(input1);  
					System.out.print("Please enter end date (yyyy-MM-dd) : ");
					Scanner sc2 = new Scanner(System.in);
					
					String input2 = sc2.next();	
												
					Date dt2 = new SimpleDateFormat("yyyy-mm-dd").parse(input2);
					System.out.println("       Date|State|Confirmed total ");
					
					listConfirmed = covidDao.findByDate(input1, input2);
					
										
					for (Covid obj : listConfirmed) {
						
						System.out.println(obj.getDate() +" |  " + obj.getState() + " | " + obj.getConfirmed());
														
					}
					
				}
				else if(input == 4 )
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
					
					System.out.println("       Date|  First State  | First Confirmed total | Second State | Second Confirmed total ");
					listCompareDtState = covidDao.compareDtState(input1, input2, input3, input4);
																				
					for (Covid obj : listCompareDtState) {
						
						System.out.println(obj.getDate() +" |       " + obj.getfirstState() + "      |        " + obj.getfirstConfirmed() + "        |        "+ obj.getsecState() + "        |        " + obj.getsecConfirmed());
					}
				}
				
				else {
					System.out.print("\r[Invalid option entered]\r");
				}
				}
			
				
		}
}


