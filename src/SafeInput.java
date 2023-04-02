import java.util.Scanner;

public class SafeInput {
    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a String response that is not zero length
     */
    public static String getNonZeroLenString(Scanner pipe, String prompt)
    {
        String retString = "";  // Set this to zero length. Loop runs until it isn’t
        do
        {
            System.out.print("\n" + prompt + ": "); // show prompt add space
            retString = pipe.nextLine(); // store the input
        }while(retString.length() == 0); // program continues to loop until String length is not zero (something is entered)

        return retString;

    }

    public static int getInt(Scanner pipe, String prompt)
    {
        int retInt = 0;
        boolean done = false;
        String trash = " ";

        do
        {
            System.out.print("\n" + prompt + ": "); // show the prompt and add a space
            if(pipe.hasNextInt()) // input is an int
            {
                done = true; // input is valid, program does not continue to loop
                retInt = pipe.nextInt(); // store the input
                pipe.nextLine(); // clear the buffer
            }
            else // invalid input - not an int
            {
                trash = pipe.nextLine(); // stores invalid input
                System.out.println("You must enter a valid integer: " + trash); // invalid input is echoed back to use
            }
        }while(!done); // program loops until a valid input is entered

        return retInt;
    }

    public static double getDouble(Scanner pipe, String prompt)
    {
        double retDouble = 0;
        boolean done = false;
        String trash = " ";

        do
        {
            System.out.print("\n" + prompt + ": "); // show the prompt and add a space
            if(pipe.hasNextDouble()) // input is a double
            {
                done = true; // input is valid, program does not continue to loop
                retDouble = pipe.nextDouble(); // store the input
                pipe.nextLine(); // clear the buffer
            }
            else // invalid input - not a double
            {
                trash = pipe.nextLine(); // stores invalid input
                System.out.println("You must enter a valid number: " + trash); // invalid input is echoed back to user
            }
        }while(!done); // program loops until a valid input is entered

        return retDouble;
    }

    public static int getRangedInt(Scanner pipe, String prompt, int low, int high)
    {
        int rangedInt = 0;
        boolean done = false;
        String trash = "";

        do
        {
            System.out.print(prompt + " [" + low + " - " + high + "]: "); // show the prompt and the range
            if(pipe.hasNextInt()) // input is an integer
            {
                rangedInt = pipe.nextInt(); // stores the input
                pipe.nextLine(); // clear the buffer
                if(rangedInt >= low && rangedInt <= high) // input is an int and is in range
                {
                    done = true; // input is valid, program will not continue to loop
                }
                else // input is an int but out of range
                {
                    System.out.println("You must enter a value that is in range [" + low + " - " + high + "]: " + rangedInt); // echoes invalid response back to user and re-states the range
                }
            }
            else // input is invalid - not an int
            {
                trash = pipe.nextLine(); // stores input in trash so that it can be echoed back to user
                System.out.println("You must enter a valid number [" + low + " - " + high + "]: " + trash); // invalid input is echoed back to the user and range is re-stated
            }

        } while(!done); // program loops until a valid input is entered

        return rangedInt;
    }

    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high)
    {
        double rangedDouble = 0;
        boolean done = false;
        String trash = "";

        do
        {
            System.out.print(prompt + " [" + low + " - " + high + "]: "); // show the prompt and the range
            if(pipe.hasNextDouble()) // input is a double
            {
                rangedDouble = pipe.nextDouble(); // stores the input
                pipe.nextLine(); // clear the buffer
                if(rangedDouble >= low && rangedDouble <= high) // valid input - double that is in range
                {
                    done = true; // input is valid, the program will not continue to loop
                }
                else // a double was entered but it is out of range
                {
                    System.out.println("You must enter a value that is in range [" + low + " - " + high + "]: " + rangedDouble); // echoes invalid response back to user and re-states the range
                }
            }
            else // anything other than a valid double is entered (invalid response)
            {
                trash = pipe.nextLine(); //stores invalid input in trash so it can be read back to the user
                System.out.println("You must enter a valid number [" + low + " - " + high + "]: " + trash); // echoes invalid response back to user and re-states the range
            }

        } while(!done); // program loops until a valid input is entered

        return rangedDouble;
    }

    public static boolean getYNConfirm(Scanner pipe, String prompt)
    {
        String YNInput = "";
        boolean YNConfirm = false;
        boolean done = false;

        do
        {
            System.out.print(prompt + " [Y/N]: "); // show the prompt
            YNInput = pipe.nextLine(); // input the data
            if(YNInput.equalsIgnoreCase("Y"))
            {
                YNConfirm = true; // if Y or y are entered, the program returns true
                done = true; // since this is a valid input, program does not need to continue to prompt the user
            }
            else if(YNInput.equalsIgnoreCase("N"))
            {
                YNConfirm = false; // if N or n are entered, the program returns false
                done = true; // since this is a valid input, program does not need to continue to prompt the user
            }
            else // anything other than YyNn are entered (invalid response)
            {
                System.out.println("You must enter a valid input [Y/N]: " + YNInput); // if user enters an invalid response, the output echoes this back to them and continues to prompt user for a valid input
            }
        } while(!done); // program loops until a valid input is entered

        return YNConfirm;
    }

    public static String getRegExString(Scanner pipe, String prompt, String regEx)
    {
        String regExString = "";
        boolean done = false;

        do
        {
            System.out.print(prompt + ": "); // show the prompt
            regExString = pipe.nextLine(); // input the data
            if (regExString.matches(regEx)) // test to see if input is correct
            {
                done = true; // we have a match, program does not continue to loop
            }
            else
            {
                System.out.println("\nInvalid input: " + regExString); // if input does not match regEx, output echoes the invalid response back to the user and continues to prompt user for a valid input
            }
        }while(!done); // program loops until a valid input is entered

        return regExString;

    }

    public static void prettyHeader(String msg)
    {
        final int CHAR_COUNT = 60; // max characters allowed is 60 per line
        int totalSpaces = CHAR_COUNT - 6 - msg.length(); // this is how many spaces are left after message and 3 stars on each side
        int leftSpaces, rightSpaces;
        if (totalSpaces % 2 == 0 ) // testing to see if the number of spaces is even or odd. If remainder is zero/totalSpaces is even, the if block runs
        {
            leftSpaces = rightSpaces = totalSpaces / 2; // dividing total spaces by 2 gives equal spaces on each side to center the message
        }
        else // if there is an odd number of spaces the message will not be perfectly centered
        {
            rightSpaces = totalSpaces / 2; // divide total spaces by 2 gives us the space on each side of the message
            leftSpaces = rightSpaces + 1; // left will have one more space since we can't center it perfectly
        }

        // print 60 stars above the message
        for (int ch = 0; ch < 60; ch++)
            System.out.print("*");
        System.out.println();

        // message line
        System.out.print("***"); // three stars on the left side of the message
        for(int ch = 0; ch < leftSpaces; ch++)
            System.out.print(" "); // print left spaces
        System.out.print(msg); // print msg that was entered as input
        for (int ch = 0; ch < rightSpaces; ch++) // print right spaces
            System.out.print(" ");
        System.out.print("***"); // three stars on the right side of the message

        // print 60 stars below the message
        System.out.println();
        for (int ch = 0; ch < 60; ch++)
        System.out.print("*");


    }


}
