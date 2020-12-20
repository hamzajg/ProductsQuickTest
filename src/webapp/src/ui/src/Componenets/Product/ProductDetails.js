import React, { useState, useEffect } from 'react';
import { ProductCategoryApiResources, ProductApiResources } from '@webapp/app';
import { Form, Card, Button } from 'react-bootstrap';
import { LinkContainer } from 'react-router-bootstrap';

const ProductDetails = (prop) => {
    const [categories, setCategories] = useState([])
    const [product, setProduct] = useState({ id: prop.match.params.id })
    const apiReosurces = new ProductCategoryApiResources()
    const productApiReosurces = new ProductApiResources()

    useEffect(() => {
        async function fetchData() {
            const items = await apiReosurces.getAllProductCategories()
            setCategories(items);
        }
        fetchData();
        async function fetchProduct() {
            const item = await productApiReosurces.getProductById(product.id)
            setProduct(item);
        }
        if (product.id != undefined)
            fetchProduct();
    }, []);
    const onInputchange = (event) => {
        const value = event.target.value;
        setProduct({
            ...product,
            [event.target.name]: value
        });
    }
    const onSubmit = async (e) => {
        e.preventDefault();
        if (product.id == undefined) {
            const item = await productApiReosurces.createProduct(product)
            setProduct(item);
        } else {
            if (product.categoryId == undefined)
                product.categoryId = product.category.id
            const item = await productApiReosurces.updateProduct(product)
            setProduct(item);
        }
    }
    return (
        <Card>
            <Card.Header>Product Details</Card.Header>
            <Card.Body>
                <Card.Title>Special title treatment</Card.Title>
                <Card.Text>
                    With supporting text below as a natural lead-in to additional content.
                </Card.Text>
                <Form onSubmit={onSubmit}>
                    <Form.Group controlId="formName">
                        <Form.Label>Name: </Form.Label>
                        <Form.Control type="text" placeholder="Enter name" value={product.name} onChange={onInputchange} required />
                    </Form.Group>
                    <Form.Group controlId="formCategory">
                        <Form.Label>Category: </Form.Label>
                        <Form.Control as="select" name="categoryId" onChange={onInputchange} required >
                            <option></option>
                            {
                                categories.map(item => {
                                    return (
                                        <option value={item.id} selected={product.category != undefined && item.id === product.category.id}>{item.name}</option>
                                    )
                                })
                            }
                        </Form.Control>
                    </Form.Group>

                    <Form.Group controlId="formUnitPrice">
                        <Form.Label>Unit Price: </Form.Label>
                        <Form.Control type="number" name="unitPrice" value={product.unitPrice} onChange={onInputchange} required />
                    </Form.Group>
                    <Form.Group controlId="formDiscount">
                        <Form.Label>Discount: </Form.Label>
                        <Form.Control type="number" name="discount" value={product.discount} onChange={onInputchange} required />
                    </Form.Group>
                    <Form.Group controlId="formName">
                        <Form.Label>Available Qty: </Form.Label>
                        <Form.Control type="number" name="availableQty" value={product.availableQty} onChange={onInputchange} required />
                    </Form.Group>
                    <LinkContainer to="/products">
                        <Button variant="default" size="sm">Back</Button>
                    </LinkContainer>
                    {' '}
                    <Button variant="primary" type="submit" size="sm">{product.id == undefined ? 'Save' : 'Update'}</Button>
                </Form>
            </Card.Body>
        </Card>
    )
}
export default ProductDetails;