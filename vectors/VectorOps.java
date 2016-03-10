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

import java.util.*;
import java.io.*;
import java.lang.*;
import java.text.*;

public class VectorOps {
    //instance fields
    private double x = 0;
    private double y = 0;
    
    //constructors
    public VectorOps(){}
    public VectorOps(double x, double y) {
		this.x = x;
		this.y = y;
    }
	
    // accessors
    public String toString() {
		return "(" + x + ", " + y + ")";
    }

    // VectorOps operations
    public VectorOps add(VectorOps a) {
		return(new VectorOps(a.x + x, a.y + y));
    }
    public VectorOps difference(VectorOps a) {
		return(new VectorOps(x - a.x, y - a.y));
    }
    public double magnitude() {
		return Math.sqrt((x*x) + (y*y));
    }
    public VectorOps scalarMult(double scalar) {
		return(new VectorOps(x*scalar, y*scalar));
    }
    public double dotProduct(VectorOps a) {
		return (x*a.x) + (y*a.y);
    }
    public double findAngle(VectorOps a) {
		double dot = (x*a.x) + (y*a.y);
		double magA = Math.sqrt((x*x) + (y*y));
		double magB = Math.sqrt((a.x*a.x) +(a.y*a.y));
		double magProduct = magA * magB;
		return Math.acos(dot / magProduct);
    }

	static void printOptions() {
		System.out.println("What would you like to find?");
		System.out.println("1. Sum of two Vectors");
		System.out.println("2. Difference of two Vectors");
		System.out.println("3. Magnitude of a Vectors");
		System.out.println("4. Scalar Product of a Vectors");
		System.out.println("5. Dot product of two Vectors");
		System.out.println("6. Angle between two Vectors");
		return;
	}

	public static void main(String[] args) {
		// some initializations
		DecimalFormat df = new DecimalFormat("#.000");
		boolean cont = true;

		// print VectorOps operations and take in which the user wants
		printOptions();
		
		while(cont == true){
			// ask for choice
			System.out.println("Enter your choice...");
			Scanner scan = new Scanner(System.in);
			int input = scan.nextInt();

			// sum two VectorOpss given by the user
			if (input == 1) {
				System.out.println("Enter first Vector:");
				VectorOps a = new VectorOps(scan.nextInt(), scan.nextInt());
				System.out.println("Enter second Vector:");
				VectorOps b = new VectorOps(scan.nextInt(), scan.nextInt());
				VectorOps sum = a.add(b);
				System.out.println("Vector Sum: " + sum);
			}

			// take the difference of two VectorOpss given by the user
			if (input == 2) {
				System.out.println("Enter first Vector:");
				VectorOps a = new VectorOps(scan.nextInt(), scan.nextInt());
				System.out.println("Enter second Vector:");
				VectorOps b = new VectorOps(scan.nextInt(), scan.nextInt());
				VectorOps diff = a.difference(b);
				System.out.println("Vector Difference: " + diff);
			}

			// find the magnitude of a VectorOps given by the user
			if (input == 3) {
				System.out.println("Enter Vector:");
				VectorOps a = new VectorOps(scan.nextInt(), scan.nextInt());
				double mag = a.magnitude();
				System.out.println("Magnitude: " + df.format(mag));
			}

			// get a scalar and VectorOps from user, and return scalar multiple
			if (input == 4) {
				System.out.println("Enter Vector:");
				VectorOps a = new VectorOps(scan.nextInt(), scan.nextInt());
				System.out.println("Enter Scalar");
				int scalar = scan.nextInt();
				VectorOps b = a.scalarMult(scalar);
				System.out.println(a + " * " + scalar + ": " + b);
			}

			// get dot product of two VectorOpss given by user
			if (input == 5) {
				System.out.println("Enter first Vector:");
				VectorOps a = new VectorOps(scan.nextInt(), scan.nextInt());
				System.out.println("Enter second Vector:");
				VectorOps b = new VectorOps(scan.nextInt(), scan.nextInt());
				double dot = a.dotProduct(b);
				System.out.println("Dot Product: " + df.format(dot));
			}

			// get angle between two VectorOpss given by user
			if (input == 6) {
				System.out.println("Enter first Vector:");
				VectorOps a = new VectorOps(scan.nextInt(), scan.nextInt());
				System.out.println("Enter second Vector:");
				VectorOps b = new VectorOps(scan.nextInt(), scan.nextInt());
				double angle = a.findAngle(b);
				System.out.println("Angle: " + df.format(angle) + " radians");
			}

			// ask if user would like to continue
			System.out.println("Do you want to continue?");
			char c = scan.next().charAt(0);
			if(c == 'y'){
				cont = true;
			}
			if(c == 'n'){
				cont = false;
			}
		}
	} // end of main

} // end of class