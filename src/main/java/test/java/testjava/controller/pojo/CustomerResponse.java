package test.java.testjava.controller.pojo;

public class CustomerResponse {

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    private String message;
    private Customer customer;

    public CustomerResponse() {

    }

    public CustomerResponse(String message) {
        this.message = message;
    }

    public CustomerResponse(String message, Customer customer) {
        this.message = message;
        this.customer = customer;
    }

}
