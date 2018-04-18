/*
 * PASSLOCK (version 1.0) - A Simple Password Manager APP 
 *
 * @author Nuno Spencer
 *
 * www.nunospencer.com
 * www.nunospencer.com/passlockv1
 *
 *Test searching passwords -w comments- LOTS of them
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Test {
    public static void main(String[] args)
    {
        String givenAccnt;

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter an account...");
        givenAccnt = scan.nextLine();
        findPasswords(givenAccnt);
    } 

    private static void findPasswords(String userInput)
    {
        //File file = new File("A:\\...");   //file to be searched
        Scanner scan = new Scanner(System.in);                                  //Scanner object to get user input
        String accntToken;                                                      //first string token of a line, i.e. "account"
        String getLine;                                                         //this is the line that contains the password the user is searching
        ArrayList<String> collectedLines = new ArrayList<>();                   //any matched line(s) is collected to an ArrayList of strings (lines)...There may be +1 lines thus I have to return them all collected in ArrayList which resizes as needed, rather than using Array which is fixed in size
        
        String nextOutput;                                                      //line(s) collected from ArrayList
        boolean isFound = false;                                                //flag
        int i;                                                                  //delineator finds index of 1st space " " on that line, so we can get to the 1st string on that line i.e. "account"
        int v = 0;                                                              //counter everytime an input is matched to a line, the line is collected to ArrayList then v++ ... (v will be used by dialog box to display number of matches found)

        try
        {   
            Scanner scanFile = new Scanner(file);                               //Scanner object to scan file
            while(scanFile.hasNextLine())                                       //Scanner scans file...line by line until EOF
            {
                getLine = scanFile.nextLine();                                  //get each line
                i = getLine.indexOf(" ");                                       //get index of substring (account) token on the line
                accntToken = getLine.substring(0, i);                           //save the 1st substring in the line (i.e account) to the variable                        
                if(userInput.equalsIgnoreCase(accntToken))                      //if given input account matches the account on the line...
                {     
                    collectedLines.add(getLine);                                //collect the line(s) to the ArrayList
                    isFound = true;                                             //flag as found
                    v++;                                                        //increment number of matches found
                }
            }
            System.out.println(v + " password(s) found for \"" + userInput + "\" :"); // is working?
            
            if(isFound)                                                         //if passwords were found, output
            {
                ListIterator<String> outputMatches =  collectedLines.listIterator();    //iterator for ArrayList...
                while(outputMatches.hasNext())                                          //while ArrayList not empty
                {
                    nextOutput = (String)outputMatches.next();                          //add found passwords/ lines containing password) to the string 
                    System.out.println(nextOutput);                                     //print lines to screen
                }
                outputMatches.remove();                                                 //do away iterator
                
            }else                                                                //else no matches found...
            {
                System.out.println("No match found!");                         
            }
        }catch (FileNotFoundException ex) 
        {
            Logger.getLogger(PassLock_Main.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error processing file!");
        }
    }
}
