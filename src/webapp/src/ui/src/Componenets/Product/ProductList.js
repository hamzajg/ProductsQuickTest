import React from 'react';
import { ProductApiResources } from '@webapp/app';

const ProductList = () => {
    const apiReosurces = new ProductApiResources()
    return (
        <div>
            <table>
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
                        apiReosurces.getAllProducts().map((item, index) => {
                            return (
                                <tr>
                                    <td>{index + 1}</td>
                                    <td>{item.name}</td>
                                    <td>{item.category.name}</td>
                                    <td>{item.unitPrice}</td>
                                    <td>{item.discount}</td>
                                    <td>{item.availableQty}</td>
                                    <td>
                                        <a href="products/{ item.id }">View</a>
                                        <a href="products/{ item.id }">Delete</a>
                                    </td>
                                </tr>
                            )
                        })
                    }
                </tbody>
            </table>
        </div>
    )
}

export default ProductList;

