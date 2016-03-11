//-----------------------------------------------------------------------------

// FindAnagrams.java

// Authors: Joseph Dizon (jrdizon), Kyle O'Connor (kyjoconn)

//-----------------------------------------------------------------------------



import java.util.*;

import java.io.*;



class FindAnagrams {



    public static void main(String[] args) throws FileNotFoundException {

    	

    	// create letter prime mapping (these are global)

    	char letter[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',

    						'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    	int prime[] = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41,

    					  43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};

    	



	    // declare scanner and take in word list

	    Scanner list = new Scanner(new FileInputStream(args[0]));

	    int size = list.nextInt(); // first item is the number of words





	    // declare arrays

	    String[] wordArray = new String[size];

	    long[] semiprimeArray = new long[size];





	    // convert word list into 2 arrays (word array, semiprime array)

	    for(int i=0; i<size; i++){ // word array for-loop

	    	wordArray[i] = list.next();

	    	String aString = wordArray[i];

	    	int wordLength = aString.length();

	    	char charArray[] = aString.toCharArray();

	    	long product = 1;

	    	for(int j=wordLength-1; j>=0; j--){ // semiprimearray for-loop

	    		product *= lookup(letter, prime, charArray[j]);

	    	}

	    	semiprimeArray[i]=product;

	    }

	    int wordArraylength = wordArray.length;

	    int semiprimeArraylength = semiprimeArray.length;





	  	// declare scanner and prompt user and takes in string of letters

	  	Scanner userinput = new Scanner(System.in);

	  	System.out.println("Type a string of letters");

	  	String str = userinput.next();

	  	int userwordLength = str.length();

	  	char usercharArray[] = str.toCharArray();

	  	long userproduct = 1;

	  	for(int j=userwordLength-1; j>=0; j--){ // semiprimearray for-loop

	    		userproduct *= lookup(letter, prime, usercharArray[j]);

	    	}    

	    for(int q=0; q<size; q++){

	    	int result = str.compareTo(wordArray[q]);

	    	if(result!=0 && userproduct==semiprimeArray[q]){

	    		System.out.println(wordArray[q]/*+" "+result*/);

	    	}

	    }





	  	// asks user if do another

	  	System.out.println("Do another (y/n)?");

		str = userinput.next();





		// prompt for valid input

		/* too fancy

		while(!str.equals("y") && !str.equals("n")){

			System.out.println("please enter (y/n).");

			str = userinput.next();

		} */





		// if user wants to do another prompt for string of letters

		while(str.equals("y")){

			System.out.println("Type a string of letters");

			str = userinput.next();

			userwordLength = str.length();

		  	char usercharArray2[] = str.toCharArray();

		  	userproduct = 1;

		  	for(int j=userwordLength-1; j>=0; j--){ // semiprimearray for-loop

	    		userproduct *= lookup(letter, prime, usercharArray2[j]);

		    } 

		    for(int q=0; q<size; q++){

		    	int result = str.compareTo(wordArray[q]);

		    	if(result!=0 && userproduct==semiprimeArray[q]){

		    		System.out.println(wordArray[q]/*+" "+result*/);

		    	}

		    }

			System.out.println("Do another (y/n)?");

			str = userinput.next();



			// prompt for valid input

			/* too fancy

			while(!str.equals("y") && !str.equals("n")){

				System.out.println("please enter (y/n).");

				str = userinput.next();

			} */

		}





		// if user doesn't want to do another exit

		while(str.equals("n")){

			System.exit(0);

		}





	} // end of main





	// letter to prime lookup function

	public static int lookup(char[] a, int[] b, char c){

		int value=0;

		for(int i=0; i<26; i++){

			char key = a[i];

			if(c==key){

				value = b[i];

			}

		}

		return value;

	}



} // end of class
