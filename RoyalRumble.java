import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
  How Roman numerals work:
    1. These are the Roman numerals and their numerical equivalents:
        L = 50
        X = 10
        V = 5
        I = 1
      If a letter of lower numerical value comes before one of higher value, it is subtracted from the latter
      e.g. XL (-X + L) = -100 + 1000 = 900
      So  XLVIV = X + (L + V) + (-I + V) = 10 + (50 + 5) + (-1 + 5) =  
          69
    2. There are no more than 3 consecutive copies of the same letter used
*/

public class RoyalRumble {

  public List<String> getSortedList(List<String> names) {
    String[] firstNames = new String[names.size()];     // List of first names
    String[] ordinals = new String[names.size()];       // List of ordinals
    int[] nums = new int[names.size()];   // List of roman ordinals converted to numbers
    
    // For splitting each value in List<String> names
    String name;
    String[] splitName;
    
    List<Integer> illicitIndicesAlpha = new ArrayList<Integer>();   // List of illicit strings' indices
    List<Integer> illicitIndicesNum = new ArrayList<Integer>();   // List of illicit ordinals' indices

    // Add to lists of names and ordinals
    for (int i = 0; i < names.size(); i++) {
      name = names.get(i);
      splitName = name.split(" ");

      firstNames[i] = splitName[0];
      // Check if current first name is between 1 and 20 characters or is entirely ASCII alphabetical characters
      if (firstNames[i].length() > 20 || firstNames[i].length() < 1 || !firstNames[i].matches("^[a-zA-Z]*$")) {illicitIndicesAlpha.add(i);}

      ordinals[i] = splitName[1];
      nums[i] = (romanToInt(ordinals[i]));    // Convert current ordinal to number
      // Check if current ordinal's value is between 1 and 50
      if (nums[i] > 50 || nums[i] < 1) { illicitIndicesNum.add(i); } 
    }
    
    // If illegal input is found, display illegal indices and throw exception
    if (illicitIndicesAlpha.size() > 0 || illicitIndicesNum.size() > 0) {
      System.out.println("ERROR: Capitalised strings of character length between 1 and 20 only!");
      System.out.println("Illicit string input:");
      for(int i: illicitIndicesAlpha) { System.out.println("index "+i+": "+names.get(i)); }
      System.out.println();

      System.out.println("ERROR: Valid (no more than 3 characters per type) Roman numerals of value between 1 and 50 only!");
      System.out.println("Illicit ordinal input:");
      for(int i: illicitIndicesNum) { System.out.println("index "+i+": "+names.get(i)); }
      throw new IllegalArgumentException("Please edit or remove any illegal variables. See above ERRORs for more information. \n");
    }
    // Otherwise sort firstNames alphabetically, along with respective ordinals
    quickSortAlpha(firstNames, ordinals, nums, 0, names.size()-1);    

    List<Integer> toSort = new ArrayList<Integer>();    // indices that indicate subsequent names to sort
    int[] numsToSort = new int[names.size()];   // converted ordinals to sort
    
    for (int i=0; i < names.size(); i++) {
      // mark sequences of subsequently similar firstNames, to sort respective ordinals (w/ error prevention for exceeding index)
      while (i+1 <= names.size()-1) {
        if (firstNames[i].equals(firstNames[i+1])) {
          toSort.add(i);
          i += 1;   // move to the next ordinal
        }
        else {
          toSort.add(i);    // include last index to sort
          break;
        }
      }
      // convert each roman numeral in ordinals[toSort] to number for sorting
      if (toSort.size() != 1) {
        for (int j=0; j < toSort.size(); j++) {
          numsToSort[j] = nums[toSort.get(j)];
        }
        quickSortNum(numsToSort, ordinals, toSort, 0, toSort.size()-1);   // sort numbers along with roman numerals
        numsToSort = new int[names.size()];     // making room for new converted ordinals
      }
      toSort = new ArrayList<Integer>();    // making room for actual ordinal indices to sort
      
    }

    // Stitch sorted lists together
    List<String> sortedList = new ArrayList<String>();    
    for (int i = 0; i < names.size(); i++) {
      sortedList.add(firstNames[i]+" "+ordinals[i]);
    }
    return sortedList;
  }

  private static void quickSortAlpha(String[] firstNames, String[] ordinals, int[] nums, int low, int high) {
    int i = low, j = high;
    String pivot = firstNames[low + (high - low) / 2];
    while (i <= j) {
      while (firstNames[i].compareToIgnoreCase(pivot) < 0) {
        i++;
      }
      while (firstNames[j].compareToIgnoreCase(pivot) > 0) {
        j--;
      }
      if (i <= j) {
        swapAlpha(firstNames, ordinals, nums, i, j);
        i++;
        j--;
      }
    }
    if (low < j) {
      quickSortAlpha(firstNames, ordinals, nums, low, j);
    }
    if (i < high) {
      quickSortAlpha(firstNames, ordinals, nums, i, high);
    }
  }

  private static void swapAlpha(String[] firstNames, String[] ordinals, int[] nums, int i, int j) {
    // Swap firstNames
    String temp = firstNames[i];
    firstNames[i] = firstNames[j];
    firstNames[j] = temp;
    // Swap ordinals
    temp = ordinals[i];
    ordinals[i] = ordinals[j];
    ordinals[j] = temp;
    // Swap converted ordinals
    int temp1 = nums[i];
    nums[i] = nums[j];
    nums[j] = temp1;
  }
  
  private int romanNumeralValue(char r) {
    if (r == 'I') 
        return 1; 
    if (r == 'V') 
        return 5; 
    if (r == 'X') 
        return 10; 
    if (r == 'L') 
        return 50; 
    return -1;
  }

  // Converts Roman numeral to int
  private int romanToInt(String str) { 
    // Initialize result 
    int res = 0; 
    int[] romanNumeralCount = new int[]{0,0,0,0};;

    for (int i = 0; i < str.length(); i++) { 

      // Tally instances of each character s[i] 
      if (str.charAt(i) == 'I') 
        romanNumeralCount[0]++; 
      else if (str.charAt(i) == 'V') 
        romanNumeralCount[1]++; 
      else if (str.charAt(i) == 'X') 
        romanNumeralCount[2]++; 
      else if (str.charAt(i) == 'L') 
        romanNumeralCount[3]++; 
      else return -1;
      
      // Getting value of symbol s[i] 
      int s1 = romanNumeralValue(str.charAt(i)); 

      // Getting value of symbol s[i+1] 
      if (i + 1 < str.length()) { 
        int s2 = romanNumeralValue(str.charAt(i+1)); 

        // Comparing both values 
        if (s1 >= s2) { 
          // Value of current symbol is greater or equal to the next symbol 
          res = res + s1; 
        } 
        else {
          // Value of current symbol is less than the next symbol 
          res = res + s2 - s1; 
          i++; 
        } 
      } 
      else { 
        res = res + s1; 
        i++; 
      } 
    }
    for (int j : romanNumeralCount) {
      if (j > 3) {return -1;}
    } 
    return res; 
  } 

  private static void quickSortNum(int[] nums, String[] ordinals, List<Integer> toSort, int low, int high) {
    int i = low, j = high;
    int pivot = nums[low + (high - low) / 2];
    while (i <= j) {
      while (nums[i] < pivot) {
        i++;
      }
      while (nums[j] > pivot) {
        j--;
      }
      if (i <= j) {
        swapNum(nums, ordinals, toSort, i, j);
        i++;
        j--;
      }
    }
    if (low < j) {
      quickSortNum(nums, ordinals, toSort, low, j);
    }
    if (i < high) {
      quickSortNum(nums, ordinals, toSort, i, high);
    }
  }

  private static void swapNum(int[] nums, String[] ordinals, List<Integer> toSort, int i, int j) {
    // Swap numbers to sort
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
    // Swap corresponding ordinals
    String temp1 = ordinals[toSort.get(i)];
    ordinals[toSort.get(i)] = ordinals[toSort.get(j)];
    ordinals[toSort.get(j)] = temp1;
  }
}

