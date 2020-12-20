import React, { useState, useEffect } from 'react';
import { CustomerApiResources } from '@webapp/app';
import { Form, Card, Button } from 'react-bootstrap';
import { LinkContainer } from 'react-router-bootstrap';

const CustomerDetails = (prop) => {
    const [customer, setCustomer] = useState({id: prop.match.params.id})
    const apiReosurces = new CustomerApiResources()

    useEffect(() => {
        async function fetchData() {
            const item = await apiReosurces.getCustomerById(customer.id)
            setCustomer(item);
        }
        if (customer.id != undefined)
            fetchData();
    }, []);
    const onInputchange = (event) => {
        const value = event.target.value;
        setCustomer({
            ...customer,
            [event.target.name]: value
        });
    }
    const onSubmit = async (e) => {
        e.preventDefault();
        if (customer.id == undefined) {
            const item = await apiReosurces.createCustomer(customer)
            setCustomer(item);
        } else {
            const item = await apiReosurces.updateCustomer(customer)
            setCustomer(item);
        }
    }
    return (
        <Card>
            <Card.Header>Customer Details</Card.Header>
            <Card.Body>
                <Card.Title>Special title treatment</Card.Title>
                <Card.Text>
                    With supporting text below as a natural lead-in to additional content.
                </Card.Text>
                <Form onSubmit={onSubmit}>
                    <Form.Group controlId="formFirstName">
                        <Form.Label>First Name: </Form.Label>
                        <Form.Control type="text" name="firstName" value={customer.firstName} onChange={onInputchange} required />
                    </Form.Group>
                    <Form.Group controlId="formLastName">
                        <Form.Label>Last Name: </Form.Label>
                        <Form.Control type="text" name="lastName" value={customer.lastName} onChange={onInputchange} required />
                    </Form.Group>
                    <Form.Group controlId="formAddress">
                        <Form.Label>Address: </Form.Label>
                        <Form.Control type="text" name="address" value={customer.address} onChange={onInputchange} required />
                    </Form.Group>
                    <Form.Group controlId="formEmail">
                        <Form.Label>Email: </Form.Label>
                        <Form.Control type="text" name="email" value={customer.email} onChange={onInputchange} required />
                    </Form.Group>
                    <Form.Group controlId="formMobile">
                        <Form.Label>Mobile: </Form.Label>
                        <Form.Control type="text" name="mobile" value={customer.mobile} onChange={onInputchange} required />
                    </Form.Group>
                    <LinkContainer to="/customers">
                        <Button variant="default" size="sm">Back</Button>
                    </LinkContainer>
                    {' '}
                    <Button variant="primary" type="submit" size="sm">{customer.id == undefined ? 'Save' : 'Update'}</Button>
                </Form>
            </Card.Body>
        </Card>
    )
}
export default CustomerDetails;