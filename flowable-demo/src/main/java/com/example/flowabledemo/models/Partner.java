package com.example.flowabledemo.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Partner implements Serializable {
    private String firstName;
    private String lastName;
    private int age;
    private Boolean executeDropoutCheck;
    private String dropoutCheckStatus;
    private Boolean executeCosimaCheck;
    private String cosimaCheckStatus;

    public Partner(String firstName, String lastName, int age, boolean executeDropoutCheck, boolean executeCosimaCheck) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.executeDropoutCheck = executeDropoutCheck;
        this.executeCosimaCheck = executeCosimaCheck;
        this.dropoutCheckStatus = "NOT_EXECUTED";
        this.cosimaCheckStatus = "NOT_EXECUTED";
    }

    @Override
    public String toString() {
        return "Partner{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", dropoutCheckStatus='" + dropoutCheckStatus + '\'' +
                ", cosimaCheckStatus='" + cosimaCheckStatus + '\'' +
                '}';
    }
}
