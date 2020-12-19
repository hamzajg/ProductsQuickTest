package com.hamzajg.quicktest.customer.application.usecases

import com.hamzajg.quicktest.customer.application.UnitOfWork
import spock.lang.Specification
import spock.lang.Unroll

class CreateCustomerUseCaseTest extends Specification {

    @Unroll
    def 'Can Create New Customer'() {
        given:
        def unitOfWork = new UnitOfWork(new InMemoryCustomerRepository())
        when:
        new CreateCustomerUseCase(unitOfWork).execute(FirstName, LastName, Address, Email, Mobile)
        then:
        def customer = unitOfWork.customerRepository().getAll()[0]
        customer.id != null
        customer.firstName == FirstName
        customer.lastName == LastName
        customer.address == Address
        customer.email == Email
        customer.mobile == Mobile
        where:
        FirstName       | LastName        | Address        | Email         | Mobile
        "Test Customer" | "Test Customer" | "Test Address" | "xxx@yyy.zzz" | "123456789"
    }
}
