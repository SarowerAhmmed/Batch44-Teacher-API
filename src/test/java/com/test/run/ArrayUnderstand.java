package com.test.run;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class ArrayUnderstand {
	
	public static void main(String[] args) {
	Integer [] myArray = {2,3,5,2,6,7,6};
	
	Set<Integer> mySet= new LinkedHashSet<>(Arrays.asList(myArray));
	System.out.println(mySet);
	
	Object[] nodup =Arrays.stream(myArray).distinct().toArray();
	System.out.println(Arrays.toString(nodup));
	
	String input ="Sarower".toLowerCase();
	for(int i=0;i<input.length();i++) {
		boolean isDup=false;
		for(int j=i+1;j<input.length();j++) {
			//System.out.println("Index = "+j);
		if(input.charAt(i)==input.charAt(j)) {
			
			isDup=true;
			break;
		}
			
		}
		if(isDup) {
			System.out.println("No dup No/unique ="+input.charAt(i));
			
		}
	}
	}

}
