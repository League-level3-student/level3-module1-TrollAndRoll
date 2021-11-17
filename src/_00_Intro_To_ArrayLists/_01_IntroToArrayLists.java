package _00_Intro_To_ArrayLists;

import java.util.ArrayList;

public class _01_IntroToArrayLists {
    public static void main(String[] args) {
        // 1. Create an array list of Strings
        //    Don't forget to import the ArrayList class
    	ArrayList<String> strings = new ArrayList<String>();
    	
        // 2. Add five Strings to your list
    	strings.add("one");
    	strings.add("two");
    	strings.add("three");
    	strings.add("four");
    	strings.add("five");
    	
        // 3. Print all the Strings using a standard for-loop
    	System.out.print("Strings in list: ");
    	for(int i = 0; i < strings.size(); i++) {
    		System.out.print(strings.get(i) + " ");
    	}
    	System.out.println("\n");
    	
        // 4. Print all the Strings using a for-each loop
    	System.out.print("(using for-each loop) Strings in list: ");
    	for(String s : strings) {
    		System.out.print(s + " ");
    	}
    	System.out.println("\n");
    	
        // 5. Print only the even numbered elements in the list.
    	System.out.print("Even numbered elements in list: ");
    	for(int i = 0; i < strings.size(); i++) {
    		if(i % 2 == 0) {
    			System.out.print(strings.get(i) + " ");
    		}
    	}
    	System.out.println("\n");
    	
        // 6. Print all the Strings in reverse order.
    	System.out.print("Strings list in reverse: ");
    	for(int i = strings.size() - 1; i >= 0; i--) {
    		System.out.print(strings.get(i) + " ");
    	}
    	System.out.println("\n");
    	
        // 7. Print only the Strings that have the letter 'e' in them.
    	System.out.print("Strings in list that have 'e' in them: ");
    	for(String s : strings) {
    		if(s.contains("e")) {
    			System.out.print(s + " ");
    		}
    	}
    	System.out.println("\n");
    }
}
