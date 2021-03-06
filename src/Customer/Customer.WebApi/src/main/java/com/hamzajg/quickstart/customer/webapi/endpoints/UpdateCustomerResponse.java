package com.hamzajg.quickstart.customer.webapi.endpoints;

public class UpdateCustomerResponse {
    public String id;
    public String firstName;
    public String lastName;
    public String address;
    public String email;
    public String mobile;

    public UpdateCustomerResponse(String id, String firstName, String lastName, String address, String email, String mobile) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.mobile = mobile;
    }
}
