import React, { useState, useEffect } from 'react';
import { CustomerApiResources } from '@webapp/app';
import { LinkContainer } from 'react-router-bootstrap';
import { Row, Card, Table, Button } from 'react-bootstrap';

const CustomerList = () => {
    const [list, setList] = useState([])
    const apiReosurces = new CustomerApiResources()

    useEffect(() => {
        async function fetchData() {
            const items = await apiReosurces.getAllCustomers()
            setList(items);
        }
        fetchData();
    }, []);
    const deleteItem = async (item) => {
        if (window.confirm("Confirm to delete item: " + item.firstName + " " + item.lastName)) {
            await apiReosurces.deleteCustomer(item.id)
            let newList = list.filter(l => l.id != item.id)
            setList(newList);
        }
    }
    return (
        <Card>
            <Card.Header>Customer List</Card.Header>
            <Card.Body>
                <Card.Title>Special title treatment</Card.Title>
                <Card.Text>
                    With supporting text below as a natural lead-in to additional content.
                </Card.Text>
                <Row>
                    <LinkContainer to="/customers/new">
                        <Button variant="primary" size="sm">New</Button>
                    </LinkContainer>
                </Row>
                <Row>
                    <Table striped bordered hover size="sm">
                        <thead>
                            <td>#</td>
                            <td>First Name</td>
                            <td>Last Name</td>
                            <td>Address</td>
                            <td>Email</td>
                            <td>Mobile</td>
                            <td>Actions</td>
                        </thead>
                        <tbody>
                            {
                                list.map((item, index) => {
                                    return (
                                        <tr>
                                            <td>{index + 1}</td>
                                            <td>{item.firstName}</td>
                                            <td>{item.lastName}</td>
                                            <td>{item.address}</td>
                                            <td>{item.email}</td>
                                            <td>{item.mobile}</td>
                                            <td>
                                                <LinkContainer to={`customers/${item.id}`}>
                                                    <Button variant="info" size="sm">View</Button>
                                                </LinkContainer>
                                                {' '}
                                                <Button variant="danger" size="sm" onClick={() => deleteItem(item)}>Delete</Button>
                                            </td>
                                        </tr>
                                    )
                                })
                            }
                        </tbody>
                    </Table>
                </Row>
            </Card.Body>
        </Card>
    )
}

export default CustomerList;
