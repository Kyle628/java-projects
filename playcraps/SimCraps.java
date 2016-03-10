/*
  This program was completed using pair programming by me
  (Kyle O'Connor) and my partner (Joe Dizon).
  I acknowledge that each partner in a programming pair should "drive"
  roughly 50% of the time the pair is working together, and at most 25%
  of an individual's effort for an assignment should be spent working
  alone. Any work done by a solitary programmer must be reviewed by the
  partner. The object is to work together, learning from each other, not
  to divide the work into two pieces with each partner working on a
  different piece.
  I spent 4 HOURS working alone.
  I spent 2 HOURS working with my partner on this assignment.
  I estimate that of the time spent with my partner, I "drove" 50
  PERCENT of the time. 

  Put any comment or explanation about variations from the 
  expected pair programming practice here. E.g. if you didn't complete
  the assignment with your partner, this is the place to explain why
  not. 
*/

 /**
 * Assignment #3. 
 * This program generates a craps simulation.
 * 
 * Authors: Joseph Dizon (jrdizon@ucsc.edu) 
 *          and Kyle O'Connor (kyjoconn@ucsc.edu)
 */

import java.util.*;
import java.lang.*;

public class SimCraps {

    public static int rollTwoDice() {
    	// generate random integers
    	Random rand = new Random();
    	int x = rand.nextInt(6) + 1;
    	int y = rand.nextInt(6) + 1;
        return x + y;
    }

    public static boolean wins() {
        int x = rollTwoDice();
        if (x == 7 || x == 11){ // win
        	return true;
        }
        if (x == 2 || x == 3 || x == 12){ // lose
        	return false;
        }
        while (true) {
            int y = rollTwoDice(); // sit and spin until win or lose
            if (y == 7) return false;
            if (y == x) return true;
        } 
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);		// number of simulations
        int wins = 0;							// number of wins
        for (int i = 0; i < N; i++)
            if (wins()) wins++;
        System.out.println("Prob of Winning = " + 1.0 * wins / N);
    }

}