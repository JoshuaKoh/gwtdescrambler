//package com.example.descrambler;
//
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.swing.JOptionPane;
//
//public class WordIndex {
//
//  private static Map<String,String> index;
//
//  public static void main(String[] args) {
//    try {
//    	// Reading in 10kwords.txt file
//      FileReader fileReader = new FileReader("10kwords.txt");
//      WordIndex idx = new WordIndex(fileReader);
//      
//      int repeat = 0;
//      
//      do {
//      //Take in scrambled user input.
//      String userInput = JOptionPane.showInputDialog
//    			("Enter a scrambled word: ");
//          
//    //End program if user enters 'end'
//    if (userInput.equalsIgnoreCase("END")) {
//    	System.out.println("Program terminated.");
//    	System.exit(0);
//    }
//    
//    //Alphabtize userInput
//    String sorted = sort(userInput);
//    //System.out.println(sorted);
//    
//    String match = lookup(sorted);
//    if (match != null) {
//    System.out.println("You entered '" + userInput + "'");
//    System.out.println("Unscrambled: " + match);
//    System.out.println("--");
//    System.out.println();
//    }
//    else {
//    System.out.println("You entered '" + userInput + "'");
//    System.out.println("Sorry, no match could be found. :-)");
//    System.out.println("--");
//    System.out.println();
//    }
//    
//      } while (repeat == 0);
//    
//      // TODO Allow the user to enter a scrambled word
//      // and print the result. Keep going until they enter END.
//    } catch (FileNotFoundException e) {
//      e.printStackTrace();
//    }
//  }
//  
//  /**
//   * Finds words in the index that match a scrambled word.
//   * 
//   * @param scrambled word
//   * @return matching words from the index
//   */
//  static String lookup(String sorted) {
//    
//	  if (index.get(sorted) != null) {
//		  return index.get(sorted);
//	  }
//	  else {
//	  return null;
//	  }
//  }
//
//  /**
//   * Returns the letters of a String in alphabetized order
//   * 
//   * @param str
//   * @return the alphabetized String
//   */
//  static String sort(String str) {
//    byte[] bytes = str.getBytes();
//    Arrays.sort(bytes);
//    return new String(bytes);
//  }
//
//  public WordIndex(InputStreamReader isr) {
//	  if (index == null) {
//	  index = new HashMap<String,String>();
//			try {
//				BufferedReader bufferedReader = new BufferedReader(isr);
//				String word = bufferedReader.readLine();
//				while (word != null) {
//					addToIndex(word);
//					word = bufferedReader.readLine();
//				}
//		    } catch (IOException e) {
//		      e.printStackTrace();
//		    }
//	  }
//  }
//
//  /**
//   * Adds a word to the index. If the alphabetized word already
//   * exists, the word is appended to a comma-separated list of
//   * matching words. For example:
//   * 
//   * dgo => dog,god
//   * 
//   * @param word
//   */
//  private void addToIndex(String word) {
//    
//    if (index.get(sort(word)) != null) {
//    	index.put(sort(word), index.get(sort(word)) + "," + word);
////    	System.out.println("if = " + index.get(sort(word)));
//    }
//    else {
//    	index.put(sort(word), word);
////    	System.out.println("else = " + index.get(sort(word)));
//
//    }
//
//  }
//  
//}

package com.koh4theking.gwtdescrambler.server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordIndex {

  private static Map<String, String> index;

  public static void main(String[] args) {
    try {
      FileReader fileReader = new FileReader("10kwords.txt");
      WordIndex idx = new WordIndex(fileReader);
      System.out.print("Enter a word: ");
      Scanner sc = new Scanner(System.in);
      String scrambled = sc.next();
      while (!"END".equals(scrambled)) {
        System.out.println(idx.lookup(scrambled));
        System.out.println("Enter a word: ");
        scrambled = sc.next();
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  String lookup(String scrambled) {
    return index.get(sort(scrambled));
  }

  private String sort(String str) {
    byte[] bytes = str.getBytes();
    Arrays.sort(bytes);
    return new String(bytes);
  }

  public WordIndex(InputStreamReader isr) {
    if (index == null) {
      index = new HashMap<String, String>();
      try {
        BufferedReader bufferedReader = new BufferedReader(isr);
        String word = bufferedReader.readLine();
        while (word != null) {
          addToIndex(word);
          word = bufferedReader.readLine();
        }
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

  private void addToIndex(String word) {
    String sorted = sort(word);
    String wordList = index.get(sorted);
    if (wordList == null) {
      index.put(sorted, word);
    } else {
      index.put(sorted, wordList + "," + word);
    }
  }

}
