package com.test.run;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ArrayUnderstand {
	
	public static void main(String[] args) {
		//K,V
		Map<String,Integer> myMap = new HashMap<>();//Map = interface ==> instantiation =Y/N =N
		// Map relation = Hash Map, LinkedHashMap,Tree map
		//HashMap = no order , faster
		//LinkedHashMap = insertion order
		//TreeMap = ASC
		myMap.put("Salary", 5000);
		myMap.put("ID", 101);
		
		System.out.println(myMap);
		// map looping
		// basic for loop = not good for(start;end:i++)
		//Map Can use Enhance for loop = for(DataType anyName: Collection/ArrayName)
		
//		  for (Entry<String, Integer> i:myMap.entrySet()) {
//	            
//	           System.out.println(i.getKey());
//	           System.out.println(i.getValue());
//	        }
		//best option = Java 8 forEach loop
		  
		myMap.forEach((k,v)->{
			//code
			System.out.println(k);
			System.out.println(v);
		});
		
	}

}
