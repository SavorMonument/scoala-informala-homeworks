package ro.siit.java10.evp;

import java.util.ArrayList;

public class GreenBonus {

    private static int currentBudget = 10000000;
    private static ArrayList<Invoice> invoices = new ArrayList<Invoice>();


    public static boolean hasEnoughBudget(){

        return  (currentBudget > 10000);
    }

    public static void addCompletedInvoice(Invoice completed_invoice){

        invoices.add(completed_invoice);
        currentBudget -= 10000;
    }
}
