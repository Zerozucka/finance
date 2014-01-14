package finance;

/**
 * This interface models a simple customer.
 */
public interface Customer {

    /**
     * returns the last name of the customer.
     * @return the last name of the customer.
     */
    String getLastname();

    /**
     * returns the unique customer number.
     * @return the unique customer number.
     */
    int getCustomerNumber();

}