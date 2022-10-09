package test.java.testjava.controller.pojo;

import lombok.Data;

import java.util.List;

@Data
public class AllCustomers {

    private List<Customer> lstCustomer;
    private String message;

    public AllCustomers(List<Customer> lstCustomer) {
        this.lstCustomer = lstCustomer;
    }
}
