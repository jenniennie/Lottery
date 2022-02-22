/* Name: Jennifer Nguyen
 * Dr. Andrew Steinberg
 * COP3503 Fall 2021
 * Programming Assignment 1
 */

package lottery;

import java.util.Random;

public class Lottery {
	private String ticket;
	
	public Lottery(Random rn) {
		ticket = ticket(rn);
	}
	
	// default constructor that sets ticket to an empty string.
	public String ticket(String ticket)
	{
		ticket = "";
		return ticket;
	}
	
	// overloaded that takes a Random class reference.
	public String ticket(Random rn) {
		int [] number;
		number = new int[6];
		
		for(int i = 0; i < 6; ++i) {
			number[i] = rn.nextInt(9) + 1;
		}
		String ticket = number.toString();
		return ticket;
	}
	
	// accessor method to retrieve the ticket attribute
	public String GetTicket()
	   {
	      return ticket;
	   }
	
	// sort the tickets using Quicksort
	public static void swap(Lottery [] ticketscollection, int i, int j)
	{
		String temp = ticketscollection[i].ticket;  
		ticketscollection[i].ticket = ticketscollection[j].ticket;  
		ticketscollection[j].ticket = temp;  
	}
	
	public static int partition(Lottery [] ticketscollection, int L, int R)
	{
	    Lottery pivot = ticketscollection[R]; 
	    
	    int i = (L - 1); 
	    
	    for(int j = L; j <= R - 1; j++)
	    {
	    	if(ticketscollection[j].GetTicket().compareTo(pivot.GetTicket()) < 0)   
	        {
	            i++; 
	            swap(ticketscollection, i, j);
	        }
	    }
	    swap(ticketscollection, i + 1, R);
	    return (i + 1);
	}
	
	public static void Sorting(Lottery [] ticketscollection, int L, int R) {
		if (L < R) 
	    {
	        int part = partition(ticketscollection, L, R);
	        
	        Sorting(ticketscollection, L, part - 1);
	        Sorting(ticketscollection, part + 1, R);
	    }
	}
	public static void Sort(Lottery [] ticketscollection)
	{
		Sorting(ticketscollection, 0, ticketscollection.length - 1);
	    
	}

	// Random winner
	public static String GenerateRandomWinner(Random rn) {
		int [] number;
		number = new int[6];
		
		for(int i = 0; i<6; ++i) {
			number[i] = rn.nextInt(9) + 1;
		}
		StringBuilder builder = new StringBuilder();

		for (int i : number) {
		    if (builder.length() > 0) {
		        builder.append(" ");
		    }
		    builder.append(i);
		}
		String ticket = builder.toString();
		return ticket;
	}
	
	// Select winner
	public static int GenerateSelectWinner(int max, Random rn) {
		int winnerIndex = rn.nextInt(max) + 1;
		return winnerIndex;
			
	}

	// Solution 1
	public static boolean Solution1(Lottery [] ticketscollection, String test, int max) {
		boolean check = false;
		for(int i = 0; i < max; ++i) {
			check = test.equals(ticketscollection[i].GetTicket());
			if (check == true) 
				return check;
		}
		return check;
	}
	
	// Solution 2
	public static boolean Solution2(Lottery [] ticketscollection, int left, int right, String test) {
		boolean check = false;
		if (right >= left) {
            int mid = left + (right - left) / 2;
  
            // if winning ticket is in the mid point
            if (check = test.equals(ticketscollection[mid].GetTicket()))
                return check;
  
            // if winning ticket is less than the mid
            if (ticketscollection[mid].GetTicket().compareTo(test) > 0)
                return Solution2(ticketscollection, left, mid - 1, test);
            
            // if winning ticket is greater than the mid
            return Solution2(ticketscollection, mid + 1, right, test);
        }
		return check;
	}
	
}


