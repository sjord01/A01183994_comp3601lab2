package a01183994.io;

import java.util.ArrayList;
import java.util.List;

import a01183994.data.Customer;
import a01183994.data.util.Validator;

/**
 * CustomerReader is responsible for parsing input strings and creating Customer
 * objects. It uses specific delimiters to separate records and fields within
 * the input string.
 * 
 * @author Samson James Ordonez
 * @version 1.0
 */
public class CustomerReader {
	// Delimiters for parsing the input string
	private static final String FIELD_DELIMITER = "\\|";
	private static final String RECORD_DELIMITER = ":";

	// Constants for the expected structure of each customer record
	private static final int TOTAL_LENGTH_OF_RECORD = 9;
	private static final int POSITION_ID = 0;
	private static final int POSITION_FIRSTNAME = 1;
	private static final int POSITION_LASTNAME = 2;
	private static final int POSITION_STREETNAME = 3;
	private static final int POSITION_CITY = 4;
	private static final int POSITION_POSTALCODE = 5;
	private static final int POSITION_PHONE = 6;
	private static final int POSITION_EMAIL = 7;
	private static final int POSITION_JOINDATE = 8;

	/**
	 * Reads customer data from a string input and returns a list of Customer
	 * objects.
	 * 
	 * @param input A string containing customer data, with records separated by
	 *              colons and fields within each record separated by pipes.
	 * @return A List of Customer objects created from the input data.
	 */
	public static List<Customer> readCustomers(String input) {
		List<Customer> customers = new ArrayList<>();
		// Split the input string into individual customer records
		String[] customerStrings = input.split(RECORD_DELIMITER);

		for (String customerString : customerStrings) {
			// Split each customer record into its constituent fields
			String[] records = customerString.split(FIELD_DELIMITER);
			// Check if the record has the expected number of fields
			if (records.length >= TOTAL_LENGTH_OF_RECORD) {
				// Validate and potentially modify the email address
				String email = Validator.validateEmail(records[POSITION_EMAIL]);
				// Create a new Customer object using the Builder pattern
				Customer customer = new Customer.Builder(records[POSITION_ID], records[POSITION_PHONE])
						.firstName(records[POSITION_FIRSTNAME]).lastName(records[POSITION_LASTNAME])
						.streetName(records[POSITION_STREETNAME]).city(records[POSITION_CITY])
						.postalCode(records[POSITION_POSTALCODE]).email(email).joinDate(records[POSITION_JOINDATE])
						.build();
				// Add the newly created Customer to the list
				customers.add(customer);
			}
		}

		return customers;
	}
}