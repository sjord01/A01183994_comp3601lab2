package a01183994;

import a01183994.io.CustomerReader;
import a01183994.io.CustomerReport;

public class Lab2 {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please provide at least one argument!");
            return;
        }

        String input = args[0];

        // Use CustomerReader to parse the input string
        CustomerReader.readCustomers(input);

        // Use CustomerReport to print the formatted report
        CustomerReport.generateReport(input);
    }
}