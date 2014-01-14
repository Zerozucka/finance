package finance.banking;

import finance.Customer;

/**
 * This class implements a simple bank customer.
 *
 * @author ...
 */
public class BankCustomer implements Customer {

    /**
     * Stores the customer number of this customer
     */
    private int customerNumber;
    /**
     * Stores the last name of the customer
     */
    private String lastname;
    /**
     * Stores the first name of the customer
     */
    private String name;

    /**
     * Stores the date of birth as string
     */
    private String birthday;
    /**
     * Stores the address of this customer
     */
    private String address;

    /**
     * Constructor to create a finance.banking.BankCustomer.
     *
     * @param name           the name of the customer
     * @param lastname       the last name of the customer
     * @param birthday       the birthday
     * @param address        the address
     */
    public BankCustomer(String name, String lastname, String birthday, String address) {
        this.customerNumber = customerNumber;
        this.name = name;
        this.lastname = lastname;
        this.birthday = birthday;
        this.address = address;
    }

    public String getLastname() {
        return this.lastname;
    }

    public int getCustomerNumber() {
        return this.customerNumber;
    }

    /**
     * Returns the first name of this customer.
     *
     * @return the first name of this customer
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the date of birth of this customer as string.
     *
     * @return the date of birth
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * Returns the address of this customer.
     *
     * @return the address of this customer
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the customer number for this customer
     *
     * @param customerNumber the new customer number
     */
    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    /**
     * Sets the last name of this customer.
     *
     * @param lastname the new last name
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Sets the first name of this customer.
     *
     * @param name the new first name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the new birthday of this customer.
     *
     * @param birthday the new birthday
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * Sets the new address of this customer.
     *
     * @param address the new address
     */
    public void setAddress(String address) {
        this.address = address;
    }
}
