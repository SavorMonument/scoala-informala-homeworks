package ro.siit.java10.evp;

import java.util.ArrayList;

public class GreenBonus {

    private static int currentBudget = 10000000;
    private static ArrayList<Invoice> invoices = new ArrayList<Invoice>();


    public synchronized static boolean hasEnoughBudget(){

        return  (currentBudget > 10000);
    }

    public synchronized static void subtractMoneyFromBudget(){

        int bonusAmount = 10000;

        if (currentBudget < bonusAmount)
            throw new IllegalStateException("Not enough budget");

        currentBudget -= bonusAmount;
    }

    public synchronized static void addCompletedInvoice(Invoice completed_invoice){

        invoices.add(completed_invoice);
    }
}
