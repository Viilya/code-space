/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
public class Main_5597
{
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean check[] = new boolean[31];
		for(int k = 0 ; k < 28 ; k ++){
			int a = sc.nextInt();
			check[a] = true;
		}
		
		for(int k = 1; k < 31 ; k ++){
			if(!check[k] ){
				System.out.println(k + "");
			}
		}
	}
}
