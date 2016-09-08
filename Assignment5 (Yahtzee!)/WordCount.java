/**
 * WordCount.java
 * 
 * This program shows ....
 */

import acm.program.*;

import acm.util.*; // For ErrorException constructor
import java.io.*; // For BufferedReader, FileReader constructor


public class WordCount extends ConsoleProgram { 
    
    public void run() {
        int num_line, num_word, num_char;
        BufferedReader rd = null;
        
        num_line = num_word = num_char = 0;
        rd = openFileReader("File: ");
        
        try {
        	
            while(true) {
                 String line  = rd.readLine();
                 if (line == null) break;
                 num_line++; // count lines 
                 num_char +=  line.length(); // count chars
                 num_word += countWord(line); // count words; local state  between methods
            }
            
            rd.close();
        } catch(IOException ex) {
            println("An I/O exception has occurred");
        }  
        
   
        println("Lines: " + num_line);
        println("words: " + num_word);
        println("Chars: " + num_char);
        
    }
    
    /**
     * Asks the user for the name of an input file and returns a
     * BufferedReader attached to its contents. If the file does
     * not exist, the user is given another chance to try.
     */
     private BufferedReader openFileReader(String prompt) {
            BufferedReader rd = null;
            
            while (rd == null) {
                 String name = readLine(prompt);
         
                 try {
                     rd = new BufferedReader(new FileReader(name));
                 } catch (IOException ex) {
                     println("Can't open that file.");
                 }
            }
         
            return rd;
     } 
    
    /**
     * Count the number of chars & words for one line 
     */
    private int countWord(String line) {
        int count = 0;
        boolean state = false;
        
        for (int i=0; i<line.length(); i++) {
            char c = line.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                    state = true;       
            } else if(state) {
                count++;
                state = false;
            } 
            
        }
        
        if (state) count++;
        
        return count;  
        
    }


}
