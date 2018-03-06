import java.util.ArrayList;

public class GreenBonus {

    private static int currentbudget = 10000000;
    private static ArrayList<Invoice> invoices = new ArrayList<>();


    public static boolean enoughBudget(){

        return  (currentbudget > 10000);
    }

    public static void addCompletedInvoice(Invoice completed_invoice){

        invoices.add(completed_invoice);
        currentbudget -= 10000;
    }
}
