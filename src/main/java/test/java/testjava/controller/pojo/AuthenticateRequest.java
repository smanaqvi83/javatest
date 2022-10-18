package test.java.testjava.controller.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthenticateRequest implements Serializable {

    private String userName;
    private String password;

}
