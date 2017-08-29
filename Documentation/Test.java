/*
 * PASSLOCK (version 1.0) - A Simple Password Manager APP 
 *
 * @author Nuno Spencer
 *
 * www.nunospencer.com
 * www.nunospencer.com/passlockv1
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
        File file = new File("C:\\Users\\Nuno\\Documents\\my_passwords.txt");   //file to search
        Scanner scan = new Scanner(System.in);                                  //Scanner object to get user input
        String accntToken;                                                      //first string token of a line, i.e. "account"
        String getLine;                                                         //this is the line that contains the password the user is looking for. There may be +1 lines thus I have to return them all.*** must use ArrayList of strings (lines of strings) instead of Array
        ArrayList<String> collectedLines = new ArrayList<>();                   //any matched line(s) is collected to an ArrayList of strings (lines)
        
        String nextOutput;                                                      //line(s) that was collected from ArrayList
        boolean isFound = false;
        int i;                                                                  //delineator... finds index of 1st space " " on that line  (or try ' '), so we can get to the 1st token of a line i.e. "account"
        int v = 0;                                                              //counter... everytime an input is matched to a line, the line is collected to ArrayList... increas    e v... the pane will also display number of accounts found (i.e. v)

        try
        {   
            Scanner scanFile = new Scanner(file);                               //Scanner object to scan file
            while(scanFile.hasNextLine())                                       //Scanner scans file... until end of file
            {
                getLine = scanFile.nextLine();                                  //gets a line
                i = getLine.indexOf(" ");                                       //get index of substring (account) token
                accntToken = getLine.substring(0, i);                           //gets the account (1st string token in the line, after " ")                        
                if(userInput.equalsIgnoreCase(accntToken))                      //if given input account equals the account on the line 
                {     
                    collectedLines.add(getLine);                                //append the line to the ArrayList collectedLines
                    isFound = true;                                             //flag
                    v++;                                                        //increment number of matches found
                }
            }
            System.out.println(v + " password(s) found for \"" + userInput + "\" :"); //tests to see search algorithm is working
            
            if(isFound)                                                         //if passwords found, display them in my custom window
            {
                ListIterator<String> outputMatches =  collectedLines.listIterator(); 
                while(outputMatches.hasNext())
                {
                    nextOutput = (String)outputMatches.next(); 
                    System.out.println(nextOutput);
                }
                outputMatches.remove();
                
            }else
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
