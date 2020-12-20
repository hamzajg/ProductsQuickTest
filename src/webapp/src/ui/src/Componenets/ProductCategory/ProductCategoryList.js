import React, { useState, useEffect } from 'react';
import { ProductCategoryApiResources } from '@webapp/app';
import { LinkContainer } from 'react-router-bootstrap';
import { Row, Card, Table, Button } from 'react-bootstrap';

const ProductCategoryList = () => {
    const [list, setList] = useState([])
    const apiReosurces = new ProductCategoryApiResources()

    const fetchData = async () => {
        const items = await apiReosurces.getAllProductCategories()
        setList(items);
    }

    useEffect(() => {
        fetchData();
    }, [list]);

    const deleteItem = async (item) => {
        if (window.confirm("Confirm to delete item: " + item.name)) {
            await apiReosurces.deleteProductCategory(item.id)
            delete list[list.indexOf(item)]
            setList(list);
        }
    }
    return (
        <Card>
            <Card.Header>Category List</Card.Header>
            <Card.Body>
                <Card.Title>Special title treatment</Card.Title>
                <Card.Text>
                    With supporting text below as a natural lead-in to additional content.
                </Card.Text>
                <Row>
                    <LinkContainer to="/product-categories/new">
                        <Button variant="primary" size="sm">New</Button>
                    </LinkContainer>
                </Row>
                <Row>
                    <Table striped bordered hover size="sm">
                        <thead>
                            <td>#</td>
                            <td>Name</td>
                            <td>Actions</td>
                        </thead>
                        <tbody>
                            {
                                list.map((item, index) => {
                                    return (
                                        <tr>
                                            <td>{index + 1}</td>
                                            <td>{item.name}</td>
                                            <td>
                                                <LinkContainer to={`product-categories/${item.id}`}>
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
export default ProductCategoryList;

