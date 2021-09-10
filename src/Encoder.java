import java.util.*;
public class Encoder {
	
	public HashMap<String, Integer> dictionary;
	
	/**
	 * Constructor: initializes dictionary and calls the makeDictionary method
	 */
	public Encoder() {
		dictionary = new HashMap<String, Integer>();
		this.makeDictionary();
	}
	
	/**
	 * makes our dictionary of all of the ascii values
	 */
	public void makeDictionary() { 
		for(int i = 0; i<256; i++) {
			dictionary.put("" + (char)i, i);
		}
	}
	
	/**
	 * returns a string of our dictionary 
	 */
	public String getDictionary() {
		return dictionary.toString();
	}
	
	/**
	 * 
	 * @param input - the string we provide to compress
	 */
	public String encode(String input) {
		String output = "";
		String current = "";
		String next = "";
		int nextValue = 255;
		boolean isMatch = false; 
		int currentLength = 1;
		
		for(int i = 0; i<=input.length(); i++) {
			
			if(i == input.length()) {
				//System.out.println("last");
				next = "";
				output += current; 
			}
			else {
				if(isMatch == false) {
					current = "" + input.charAt(i);
					next = "" + input.charAt(i+1);	
				}
				else {
					next = "" + input.charAt(i+1);
				}
				String combined = "" + current + next;
				
				if(dictionary.containsKey(combined)) {
					current = combined; 
					isMatch = true;
					currentLength = combined.length();
					i = i + currentLength-2;
				}
				else {
					nextValue = nextValue+1; //increments nextValue to make key, value pair correct
					dictionary.put("" + current + next, nextValue); //puts the two into dictionary
					output += current + " "; //outputs the value and adds a space (for clarity)
					System.out.println(current + " ");
					isMatch = false;
					currentLength = 1; 
				}
			}
			
			
			
		}
		
		return output;
	}
}
