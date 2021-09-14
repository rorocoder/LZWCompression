import java.util.*;
import java.io.*;
public class Encoder {
	
	public HashMap<String, Integer> dictionary;
	public ArrayList<Integer> outputValues;
	public String output;
	
	/**
	 * Constructor: initializes dictionary and calls the makeDictionary method
	 */
	public Encoder() {
		output="";
		dictionary = new HashMap<String, Integer>();
		this.makeDictionary();
		outputValues = new ArrayList<Integer>();
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
	
	public String getOutput() {
		return output;
	}

	
	public String encode(String fileName) throws IOException{
		output = "";
		BufferedReader reader = new BufferedReader(new FileReader(new File(fileName))); //makes a reader
		
		int current = reader.read(); //gets first char as an int
		String currentString = ""+(char)current; //makes variable to be the current as a String rather than int
		
		int dictValue = 255; //what value we give things we put into the dictionary
		
		while(current!=-1) { //when we reach end of file .read() returns -1
			
			int next = reader.read(); //gets next char of file as an int
			if(next == -1) { //checks if we are at end of file
				break; //breaks out of while loop
			}
			String nextString = ""+(char)next; //makes the next variable a String rather than an int 
			
			String combined = "" + currentString+nextString; //combines the current and next together as a string
			
			if(dictionary.containsKey(combined)) { //if the dictionary contains our combined
				currentString = combined; //set our current = to the combined value
			}
			else {
				output += currentString + " ";  //if dictionary doesnt have it then we output current
				dictionary.put(combined, dictValue+=1); //put combined inside the dictionary 
				outputValues.add(dictionary.get(currentString));
				currentString = nextString; //set the currentString equal to the nextString
			}
			
			
			
			
		}
		
		
		this.makeBinary(); //makes our output into a binary file called output.dat
		
		
		this.bitToByte(); //makes our binary file into a file of bytes called byteOutput.txt

		
		return output;
	}
	
	public void bitToByte() throws IOException {
		BufferedReader reader2 = new BufferedReader(new FileReader(new File("output.dat")));
		
		ByteOutputStream b = new ByteOutputStream(new File("byteOutput.txt"));
		int i=-2;
		char c;
		byte d;
		
		while(i!=-1) {
			i = reader2.read();
			c=(char)i;
			if(c == (char)48) {
				d = (byte)0;
			}
			else{
				d = (byte)1;
			}
			
			b.write(d);
		}
	}
	
	public String toBinary(int x, int len)
    {
        if (len > 0)
        {
            return String.format("%" + len + "s",
                            Integer.toBinaryString(x)).replaceAll(" ", "0");
        }
 
        return null;
    }
 
	
	public void makeBinary() throws IOException {
		
		try {
		    // create a writer
		    FileOutputStream fos = new FileOutputStream(new File("output.dat"));
		    BufferedOutputStream writer = new BufferedOutputStream(fos);

		    // write data to file
		    for(Integer number: outputValues) {
		    	writer.write(this.toBinary(number,12).getBytes());
		    }

		    // flush remaining bytes
		    writer.flush();
		    
		    // close the writer
		    writer.close();

		} catch (IOException ex) {
		    ex.printStackTrace();
		}
	}
}
	
	