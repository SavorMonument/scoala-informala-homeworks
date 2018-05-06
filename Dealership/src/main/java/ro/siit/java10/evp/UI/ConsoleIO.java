package ro.siit.java10.evp.UI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;

public class ConsoleIO {

    private BufferedWriter bWriter;
    private BufferedReader bReader;
    private Scanner scanner;

    public ConsoleIO(BufferedWriter bWriter, BufferedReader bReader) {

        this.bWriter = bWriter;
        this.bReader = bReader;
        scanner = new Scanner(bReader);
    }

    public void getKeyboardInput(){

        readString();
    }


    public void printString(String toPrintString){

        try {
            bWriter.write(toPrintString);
            bWriter.flush();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private String readString(){

        String str = "";

        try {
            str = bReader.readLine();
        } catch (IOException e){
            e.printStackTrace();
        }

        return str;
    }

    /**
     * Attempts to read a non empty String for console
     *
     * @return String or null if can't read non empty
     */
    public String readLine(){

        String input;

        input = readString();

        if (input.isEmpty()){
            printString("Can't be empty, 1 to try again\n");

            if (tryAgain())
                return readLine();
            else
                return null;
        }

        return input;
    }

    public int readCondInt(int min, int max){

        int option = -1;
        String line;

        line = readString();

        try{
            option = Integer.parseInt(line);

        } catch (NumberFormatException e){}

        if ((option >= min) && (option <= max)){
            return option;
        }

        printString("Not a valid option, 1 to try again\n");
        if (tryAgain())
            return readCondInt(min, max);
        else
            return (-1);


    }

    private boolean tryAgain(){

        String option = readString();

        try{
            if(Integer.parseInt(option) == 1)
                return true;

        } catch (NumberFormatException e){ }

        return false;
    }

    public void close(){

        try {
            if (null != scanner)
                scanner.close();

            if (null != bWriter)
                bWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
