import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;


public class WheelofFortune {

	public static void main(String[] args) throws FileNotFoundException {
		
		
		int numberofcountries = 0;
		Scanner scanner = new Scanner(new File("countries.txt"));
		
		while (scanner.hasNext()) 
		{
			String country = (String) scanner.nextLine(); //Counting how many lines are in the countries.txt file.
			if (country.length()>0) 
			{
				numberofcountries++;
			}
		}
		scanner.close();
		/////////////////////////////////////////////////////////////////////////////////////////////////
		Stack s1 = new Stack(numberofcountries);
		Stack tempstack = new Stack(numberofcountries);
		
		Scanner scanner1 = new Scanner(new File("countries.txt"));//Reading the countries in the countries.txt file and removing the spaces between them and throwing them into the tempstack.
		while (scanner1.hasNext()) 
		{
			String country = (String) scanner1.nextLine();
			if(country.contains(" "))
			{
				country = country.replaceAll("\\s","");
			}
			tempstack.push(country);
		}
		scanner1.close();
		///////////////////////////////////////////////////////////////////////////////////////////
		while (tempstack.isEmpty() == false) 
		{
			String tmp = (String) tempstack.pop();
			
			while (s1.isEmpty() == false && ((String) s1.peek()).compareTo(tmp) < 0)
			{
				tempstack.push(s1.pop()); // Sort operations are performed using tempstack and s1 stacks.
			}
			s1.push(tmp);
		}
		Random rnd = new Random();
		moneywheel = rnd.nextInt(1,9);
		Wheel();
		///////////////////////////////////////////////////////////////////////////////////
		Stack s2 = new Stack(26);
		String alphabet = "ZYXWVUTSRQPONMLKJİHGFEDCBA";
		
		for (int i = 0; i < alphabet.length(); i++) //adding the alphabet to the s2 stack
		{
			s2.push(alphabet.charAt(i));
		}
		//////////////////////////////////////////////////////////////////////////////////
		
		int alphabetnumber = 26;
		int randomcountry = rnd.nextInt(0,numberofcountries);
		int randomletter = rnd.nextInt(0,alphabetnumber);
		
		System.out.println("Randomly generated number: " + (randomcountry+1));
		
		for (int i = 0; i < randomcountry; i++) //Selecting a random country from the s1 stack
		{
			s1.pop();
		}
		CircularQueue q1 = new CircularQueue(((String) s1.peek()).length());
		CircularQueue q2 = new CircularQueue(((String) s1.peek()).length());
		CircularQueue tempq1 = new CircularQueue(((String) s1.peek()).length());
		CircularQueue tempq2 = new CircularQueue(((String) s1.peek()).length());
		
		
		for (int i = 0; i < ((String) s1.peek()).length(); i++) //adding the chosen random country to the q1 queue.
		{
			q1.enqueue(((String) s1.peek()).charAt(i));
		}
		
		for (int i = 0; i < ((String) s1.peek()).length(); i++) //creating the q2.
		{
			q2.enqueue("-");
		}
		for (int i = 0; i < ((String) s1.peek()).length(); i++) //Writing the q2.
		{
			System.out.print(q2.dequeue());
		}
		System.out.println("                   Step: 0" + "     Score: " + score);
		
		for (int i = 0; i < ((String) s1.peek()).length(); i++) //q2 is regenerated after being written to the screen.
		{
			q2.enqueue("-");
		}
		//////////////////////////////////////////////////////////////////////////////////////
		Stack tempS2 = new Stack(26);
		
		
		for (int i = 0; i < randomletter-1; i++) 
		{
			tempS2.push(s2.pop());
		}
		String guess = s2.pop().toString();
		
		System.out.println("Wheel: " + wheel);
		System.out.print("Guess: " + guess + "                                                   Letters: ");
		
		while (!tempS2.isEmpty())                // Removing the randomly selected letter from s2.
		{
			s2.push(tempS2.pop());
		}
		while (!s2.isEmpty()) 
		{
			System.out.print(s2.peek());
			tempS2.push(s2.pop());
		}
		System.out.println("");
		while (!tempS2.isEmpty()) 
		{
			s2.push(tempS2.pop());
		}
		
////////////////////////////////////////////////////////////////////////////////
		int counter = 0;
		boolean flag = true;
		int stepcounter = 0;
		
		
		while (flag)    //gameloop
		{
			stepcounter++;
			
		    for (int i = 0; i < q1.size(); i++) 
		    {
		        String countryname = q1.dequeue().toString();
		        if (countryname.equalsIgnoreCase(guess)) 
		        {
		        	q2.dequeue();
		        	q2.enqueue(countryname);
		        	q1.enqueue(countryname);     //Comparing the randomly selected letter with q1 and adding it to its place in q2, if any.
		        	counter++;
		        	Score();
		        } 
		        else 
		        {
		        	q2.enqueue(q2.dequeue());
		        	q1.enqueue(countryname);
		        	if (moneywheel == 8) {
						score = 0;
					}
		        }
		    }
		       
		    System.out.println(" ");    
		    System.out.println(" ");    
		    
			
		    while (!q2.isEmpty()) 
		    {
				System.out.print(q2.peek());
				tempq2.enqueue(q2.dequeue());    // reprinting q2.
			}
		    System.out.println("                   Step: " + stepcounter + "     Score: " + score);
		    
			while (!tempq2.isEmpty()) 
			{
				q2.enqueue(tempq2.dequeue());
			}
			if (counter == q1.size()) 
			{
		        	flag = false;             //ending the loop if all the words are found.
		        	break;
		    }
			
			randomletter = rnd.nextInt(0,alphabetnumber-1);
			alphabetnumber -=1;
			
			for (int i = 0; i < randomletter-1; i++)      // selection of new letters from the alphabet.
			{
				tempS2.push(s2.pop());
			}
			guess = s2.pop().toString();
			
			
			
			System.out.println("Wheel: " + wheel);
			System.out.print("Guess: " + guess + "                                                   "); 
			
			while (!tempS2.isEmpty()) 
			{
				s2.push(tempS2.pop());
			}
			System.out.print("Letters: ");                   //Removing the randomly selected letter from s2.
			
			while (!s2.isEmpty()) 
			{
				System.out.print(s2.peek());
				tempS2.push(s2.pop());
			}
			while (!tempS2.isEmpty()) 
			{
				s2.push(tempS2.pop());
			}
			if (alphabetnumber == 1) 
			{
				flag = false;
			}
			moneywheel = rnd.nextInt(1,9);
			Wheel();
		    
		}
		
		int linenumber = 0;
		Scanner scanner2 = new Scanner(new File("HighScoreTable.txt"));
		
		while (scanner2.hasNext()) 
		{
			String players = (String) scanner2.nextLine();
			if (players.length()>0) 
			{
				linenumber++;
			}
		}
		scanner2.close();
		/////////////////////////////////////////////////////////////////////////////////////////////////
		Stack s3 = new Stack(linenumber);
		Stack tempstack3 = new Stack(linenumber);
		Stack s4 = new Stack(linenumber);
		Stack tempstack4 = new Stack(linenumber);
		Stack s5 = new Stack(linenumber);
		Stack s6 = new Stack(linenumber);
		Stack tempstack5 = new Stack(linenumber);
		
		Scanner scanner3 = new Scanner(new File("HighScoreTable.txt"));
		while (scanner3.hasNext()) 
		{
			String players = (String) scanner3.nextLine();
			tempstack3.push(players);
			
		}
		scanner3.close();
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		Scanner scanner4 = new Scanner(new File("HighScoreTable.txt"));
		while (scanner4.hasNext()) 
		{
			String players = (String) scanner4.nextLine();
			tempstack4.push(players);
			
		}
		scanner4.close();
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		while (tempstack3.isEmpty() == false) 
		{
			String tmp =  tempstack3.peek().toString().split(" ")[1];
			String tmp2 = (String) tempstack3.pop();
			
			while (s6.isEmpty() == false && s6.peek().toString().split(" ")[1].compareTo(tmp) < 0)
			{
				tempstack3.push(s6.pop());
			}
			s6.push(tmp2);
		}
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////
		while (tempstack4.isEmpty() == false) 
		{
			String tmp3 =  tempstack4.peek().toString().split(" ")[1];
			String tmp4 = (String) tempstack4.pop();
			
			while (s5.isEmpty() == false && s5.peek().toString().split(" ")[1].compareTo(tmp3) < 0)
			{
				tempstack3.push(s5.pop());
			}
			s5.push(tmp4);
		}
		/////////////////////////////////////////////////////////////////////////////////////////////////
		while (!s6.isEmpty()) {
			s4.push(s6.peek().toString().split(" ")[1]);
			tempstack5.push(s6.pop().toString().split(" ")[1]);
		}
		while (!tempstack5.isEmpty()) {
			s6.push(tempstack5.pop());
		}
		while (!tempstack4.isEmpty()) {
			s3.push(s6.pop().toString().split(" ")[0]);
		}
		//////////////////////
		
		
		
		
		
		
	}
	static int score = 0 ;
	static String wheel = null;
	static int moneywheel;
	
	public static String Wheel() {
		if (moneywheel == 1) {
			wheel = "10";
		}
		else if (moneywheel == 2) {
			wheel = "50";
		}
		else if (moneywheel == 3) {
			wheel = "100";
		}
		else if (moneywheel == 4) {
			wheel = "250";
		}
		else if (moneywheel == 5) {
			wheel = "500";
		}
		else if (moneywheel == 6) {
			wheel = "1000";
		}
		else if (moneywheel == 7) {
			wheel = "Double Money";
		}
		else if (moneywheel == 8) {
			wheel = "Bankrupt";
			
		}
		return wheel;
	}
	public static int Score() {
		if (moneywheel == 1) {
			score = score + 10; 
		}
		else if (moneywheel == 2) {
			score = score + 50;
		}
		else if (moneywheel == 3) {
			score = score + 100;
		}
		else if (moneywheel == 4) {
			score = score + 250;
		}
		else if (moneywheel == 5) {
			score = score + 500;
		}
		else if (moneywheel == 6) {
			score = score + 1000;
		}
		else if (moneywheel == 7) {
			score = score * 2;
		}
		else if (moneywheel == 8) {
			score = 0;
		}
		return score;
	}
}
