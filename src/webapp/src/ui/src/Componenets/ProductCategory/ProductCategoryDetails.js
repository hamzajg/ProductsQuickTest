import React, { useState, useEffect } from 'react';
import { ProductCategoryApiResources } from '@webapp/app';
import { Form, Card, Button } from 'react-bootstrap';
import { LinkContainer } from 'react-router-bootstrap';

const ProductCategoryDetails = (prop) => {
    const [category, setCategory] = useState({ id: prop.match.params.id })
    const apiReosurces = new ProductCategoryApiResources()
    useEffect(() => {
        async function fetchData() {
            const item = await apiReosurces.getProductCategoryById(category.id)
            setCategory(item);
        }
        if (category.id != undefined)
            fetchData();
    }, []);
    const onInputchange = (event) => {
        setCategory({
            ...category,
            name: event.target.value
        });
    }
    const save = async () => {
        if (category.id == undefined) {
            const item = await apiReosurces.createProductCategory(category)
            setCategory(item);
        } else {
            const item = await apiReosurces.updateProductCategory(category)
            setCategory(item);
        }
    }
    return (
        <Card>
            <Card.Header>Category Details</Card.Header>
            <Card.Body>
                <Card.Title>Special title treatment</Card.Title>
                <Card.Text>
                    With supporting text below as a natural lead-in to additional content.
                </Card.Text>
                <Form>
                    <Form.Group controlId="formName">
                        <Form.Label>Name: </Form.Label>
                        <Form.Control type="text" placeholder="Enter name" value={category.name} onChange={onInputchange} required />
                    </Form.Group>
                    <LinkContainer to="/product-categories">
                        <Button variant="default" size="sm">Back</Button>
                    </LinkContainer>
                    {' '}
                    <Button variant="primary" onClick={save} type="button" size="sm">{category.id == undefined ? 'Save' : 'Update'}</Button>
                </Form>
            </Card.Body>
        </Card>
    )
}
export default ProductCategoryDetails;