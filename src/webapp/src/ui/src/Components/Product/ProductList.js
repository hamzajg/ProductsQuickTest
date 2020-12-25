import React, { useState, useEffect } from 'react';
import { ProductApiResources } from '@webapp/app';
import { LinkContainer } from 'react-router-bootstrap';
import { Row, Card, Table, Button } from 'react-bootstrap';

const ProductList = () => {
    const [list, setList] = useState([])
    const apiReosurces = new ProductApiResources()

    useEffect(() => {
        async function fetchData() {
            const items = await apiReosurces.getAllProducts()
            setList(items);
        }
        fetchData();
    }, []);
    const deleteItem = async (item) => {
        if (window.confirm("Confirm to delete item: " + item.name)) {
            await apiReosurces.deleteProduct(item.id)
            let newList = list.filter(l => l.id != item.id)
            setList(newList);
        }
    }
    return (
        <Card>
            <Card.Header>Product List</Card.Header>
            <Card.Body>
                <Card.Title>Special title treatment</Card.Title>
                <Card.Text>
                    With supporting text below as a natural lead-in to additional content.
                </Card.Text>
                <Row>
                    <LinkContainer to="/products/new">
                        <Button variant="primary" size="sm">New</Button>
                    </LinkContainer>
                </Row>
                <Row>
                    <Table striped bordered hover size="sm">
                        <thead>
                            <td>#</td>
                            <td>Name</td>
                            <td>Category</td>
                            <td>Unit Price</td>
                            <td>Discount</td>
                            <td>Available Qty</td>
                            <td>Actions</td>
                        </thead>
                        <tbody>
                            {
                                list.map((item, index) => {
                                    return (
                                        <tr>
                                            <td>{index + 1}</td>
                                            <td>{item.name}</td>
                                            <td>{item.category.name}</td>
                                            <td>{item.unitPrice}</td>
                                            <td>{item.discount}</td>
                                            <td>{item.availableQty}</td>
                                            <td>
                                                <LinkContainer to={`products/${item.id}`}>
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

export default ProductList;

