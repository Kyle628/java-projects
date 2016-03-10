/*
  This program was completed using pair programming by me
  (PUT YOUR NAME HERE) and my partner (PUT YOUR PARTNER'S NAME HERE).
  I acknowledge that each partner in a programming pair should "drive"
  roughly 50% of the time the pair is working together, and at most 25%
  of an individual's effort for an assignment should be spent working
  alone. Any work done by a solitary programmer must be reviewed by the
  partner. The object is to work together, learning from each other, not
  to divide the work into two pieces with each partner working on a
  different piece.
  I spent _____2_ HOURS working alone.
  I spent ______3 HOURS working with my partner on this assignment.
  I estimate that of the time spent with my partner, I "drove" ___50__
  PERCENT of the time. 

  Put any comment or explanation about variations from the 
  expected pair programming practice here. E.g. if you didn't complete
  the assignment with your partner, this is the place to explain why
  not. 
*/

/**
 * Assignment #1.
 * This program generates an anagram randomly from a list of words
 * 
 * Authors: Kyle O'Connor (kyjoconn@ucsc.edu) 
 *          and Joe Dizon (jrdizon@ucsc.edu)
 */

import java.util.*;
import java.io.*;

class AnagramPuzzle {
    public static void main(String[] args) throws FileNotFoundException {
  
    // takes in command line argument
    Scanner in = new Scanner(new FileInputStream(args[0]));

    // initialize variables
    int size = in.nextInt(); // first item is the number of words
    Random rand = new Random();
    String pickedWord = new String();
    int pickedNumber = rand.nextInt(size)+1;
    int i = 1;

    // read through text file until picked line (Conceptual help from TA Lev)
    while ( i < pickedNumber ) {
        pickedWord = in.next();
        i++;
    }

    // scramble word with string buffer (For Loop parameter values from StackOverflow)
    StringBuffer sb = new StringBuffer(pickedWord);;
    for (int j = sb.length()- 1; j > 1; j--) {
        Random randIndex = new Random();
        int chosenIndex = randIndex.nextInt(j);
        char ltrAtIndex = sb.charAt(chosenIndex);
        char ltrAtEnd = sb.charAt(j);
        sb.setCharAt(j, ltrAtIndex);
        sb.setCharAt(chosenIndex, ltrAtEnd);
    }

    // print out scrambled word
    System.out.println(sb);
    
    }
}
