package a01183994.io;

import java.util.List;
import java.util.stream.IntStream;

import a01183994.data.Customer;

/**
 * CustomerReport class generates a formatted report of customer information.
 * This class uses the Builder pattern for report generation and provides
 * a clean, extensible way to create customer reports.
 *
 * @author Your Name
 * @version 1.0
 */
public class CustomerReport {
    // Report title constant
    private static final String REPORT_TITLE = "Customer Report";
    
    // System-specific line separator
    private static final String LINE_SEPARATOR = System.lineSeparator();
    
    // Common string constants
    private static final String EMPTY_STRING = "";
    private static final String SPACE = " ";
    private static final String ELLIPSIS = "...";
    private static final String SEPARATOR_CHAR = "-";
    
    // Column header constants
    private static final String ID_HEADER = "#. ID";
    private static final String FIRST_NAME_HEADER = "First name";
    private static final String LAST_NAME_HEADER = "Last name";
    private static final String STREET_HEADER = "Street";
    private static final String CITY_HEADER = "City";
    private static final String POSTAL_CODE_HEADER = "Postal Code";
    private static final String PHONE_HEADER = "Phone";
    private static final String EMAIL_HEADER = "Email";
    private static final String JOIN_DATE_HEADER = "Join Date";
    
    // Column width constants
    private static final int ID_WIDTH = 12;
    private static final int FIRST_NAME_WIDTH = 13;
    private static final int LAST_NAME_WIDTH = 13;
    private static final int STREET_WIDTH = 26;
    private static final int CITY_WIDTH = 13;
    private static final int POSTAL_CODE_WIDTH = 15;
    private static final int PHONE_WIDTH = 16;
    private static final int EMAIL_WIDTH = 24;
    private static final int JOIN_DATE_WIDTH = 11;
    
    // Formatting constants
    private static final String ID_FORMAT = "%06d";
    private static final String PHONE_FORMAT = "(%s) %s-%s";
    private static final String ROW_FORMAT = "%-" + ID_WIDTH + "s%-" + FIRST_NAME_WIDTH + "s%-" + LAST_NAME_WIDTH + "s%-" + STREET_WIDTH + "s%-" + CITY_WIDTH + "s%-" + POSTAL_CODE_WIDTH + "s%-" + PHONE_WIDTH + "s%-" + EMAIL_WIDTH + "s%-" + JOIN_DATE_WIDTH + "s";
    
    // Other constants
    private static final int TRUNCATE_OFFSET = 3;
    private static final int PHONE_AREA_CODE_LENGTH = 3;
    private static final int PHONE_EXCHANGE_CODE_LENGTH = 3;
    private static final int PHONE_SUBSCRIBER_NUMBER_LENGTH = 4;
    private static final int EXPECTED_PHONE_LENGTH = PHONE_AREA_CODE_LENGTH + PHONE_EXCHANGE_CODE_LENGTH + PHONE_SUBSCRIBER_NUMBER_LENGTH;

    /**
     * Generates and prints a formatted customer report.
     *
     * @param input A string containing customer data
     */
    public static void generateReport(String input) {
        List<Customer> customers = CustomerReader.readCustomers(input);
        ReportBuilder reportBuilder = new ReportBuilder();

        reportBuilder.addLine(REPORT_TITLE)
                     .addSeparator()
                     .addHeaders()
                     .addSeparator()
                     .addCustomers(customers);

        System.out.println(reportBuilder.build());
    }

    /**
     * ReportBuilder class uses the Builder pattern to construct the report.
     */
    private static class ReportBuilder {
        private final StringBuilder report = new StringBuilder();

        /**
         * Adds a line to the report.
         *
         * @param line The line to add
         * @return This ReportBuilder instance for method chaining
         */
        ReportBuilder addLine(String line) {
            report.append(line).append(LINE_SEPARATOR);
            return this;
        }

        /**
         * Adds a separator line to the report.
         *
         * @return This ReportBuilder instance for method chaining
         */
        ReportBuilder addSeparator() {
            return addLine(ReportColumn.createSeparator());
        }

        /**
         * Adds the header row to the report.
         *
         * @return This ReportBuilder instance for method chaining
         */
        ReportBuilder addHeaders() {
            return addLine(ReportColumn.formatRow(ReportColumn.getHeaders()));
        }

        /**
         * Adds formatted customer data to the report.
         *
         * @param customers List of Customer objects
         * @return This ReportBuilder instance for method chaining
         */
        ReportBuilder addCustomers(List<Customer> customers) {
            IntStream.range(0, customers.size())
                     .mapToObj(i -> CustomerFormatter.format(i + 1, customers.get(i)))
                     .forEach(this::addLine);
            return this;
        }

        /**
         * Builds and returns the complete report as a string.
         *
         * @return The formatted report string
         */
        String build() {
            return report.toString();
        }
    }

    /**
     * Enum representing the columns in the report.
     * This makes it easy to add, remove, or modify columns in the future.
     */
    private enum ReportColumn {
        ID(ID_HEADER, ID_WIDTH),
        FIRST_NAME(FIRST_NAME_HEADER, FIRST_NAME_WIDTH),
        LAST_NAME(LAST_NAME_HEADER, LAST_NAME_WIDTH),
        STREET(STREET_HEADER, STREET_WIDTH),
        CITY(CITY_HEADER, CITY_WIDTH),
        POSTAL_CODE(POSTAL_CODE_HEADER, POSTAL_CODE_WIDTH),
        PHONE(PHONE_HEADER, PHONE_WIDTH),
        EMAIL(EMAIL_HEADER, EMAIL_WIDTH),
        JOIN_DATE(JOIN_DATE_HEADER, JOIN_DATE_WIDTH);

        private final String header;
        private final int width;

        ReportColumn(String header, int width) {
            this.header = header;
            this.width = width;
        }

        /**
         * Returns an array of column headers.
         *
         * @return Array of column header strings
         */
        static String[] getHeaders() {
            return java.util.Arrays.stream(values())
                                   .map(col -> col.header)
                                   .toArray(String[]::new);
        }

        /**
         * Creates a separator line for the report.
         *
         * @return A string of dashes matching the total width of all columns
         */
        static String createSeparator() {
            return SEPARATOR_CHAR.repeat(java.util.Arrays.stream(values()).mapToInt(col -> col.width).sum());
        }

        /**
         * Formats a row of data according to column specifications.
         *
         * @param fields The data fields to format
         * @return A formatted string representing a row in the report
         */
        static String formatRow(String... fields) {
            return String.format(ROW_FORMAT, (Object[]) fields);
        }

        /**
         * Truncates a string if it exceeds the specified maximum length.
         *
         * @param s The string to truncate
         * @param maxLength The maximum allowed length
         * @return The truncated string, or the original if it's short enough
         */
        private static String truncate(String s, int maxLength) {
            if (s == null) return EMPTY_STRING;
            return s.length() <= maxLength ? s : s.substring(0, maxLength - TRUNCATE_OFFSET) + ELLIPSIS;
        }
    }

    /**
     * CustomerFormatter class handles the formatting of individual customer data.
     */
    private static class CustomerFormatter {
        /**
         * Formats a customer's data into a report row.
         *
         * @param index The index of the customer in the list
         * @param c The Customer object to format
         * @return A formatted string representing the customer's data
         */
        static String format(int index, Customer customer) {
            return ReportColumn.formatRow(
                formatId(index, customer.getId()),
                customer.getFirstName(),
                customer.getLastName(),
                truncateAndReplace(customer.getStreetName(), STREET_WIDTH),
                customer.getCity(),
                customer.getPostalCode(),
                formatPhoneNumber(customer.getPhone()),
                customer.getEmail(),
                customer.getJoinDate()
            );
        }

        /**
         * Formats the customer ID.
         *
         * @param index The index of the customer in the list
         * @param id The customer's ID
         * @return A formatted ID string
         */
        private static String formatId(int index, String id) {
            return String.format("%d. %s", index, String.format(ID_FORMAT, Integer.parseInt(id)));
        }

        /**
         * Truncates and replaces multiple spaces in a string.
         *
         * @param stringInput The string to process
         * @param maxLength The maximum allowed length
         * @return The processed string
         */
        private static String truncateAndReplace(String stringInput, int maxLength) {
            if (stringInput == null) return EMPTY_STRING;
            return ReportColumn.truncate(stringInput.replaceAll("\\s+", SPACE), maxLength);
        }

        /**
         * Formats a phone number string.
         *
         * @param phone The phone number to format
         * @return A formatted phone number string
         */
        private static String formatPhoneNumber(String phone) {
            String digitsOnly = phone.replaceAll("\\D", EMPTY_STRING);
            return digitsOnly.length() == EXPECTED_PHONE_LENGTH
                ? String.format(PHONE_FORMAT, 
                    digitsOnly.substring(0, PHONE_AREA_CODE_LENGTH),
                    digitsOnly.substring(PHONE_AREA_CODE_LENGTH, PHONE_AREA_CODE_LENGTH + PHONE_EXCHANGE_CODE_LENGTH),
                    digitsOnly.substring(PHONE_AREA_CODE_LENGTH + PHONE_EXCHANGE_CODE_LENGTH))
                : phone;
        }
    }
}