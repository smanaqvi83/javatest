package test.java.testjava.controller.pojo;

import java.util.List;

public class AllCustomers {
    public List<Customer> getLstCustomer() {
        return lstCustomer;
    }

    public void setLstCustomer(List<Customer> lstCustomer) {
        this.lstCustomer = lstCustomer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private List<Customer> lstCustomer;
    private String message;

    public AllCustomers(List<Customer> lstCustomer) {
        this.lstCustomer = lstCustomer;
    }
}
