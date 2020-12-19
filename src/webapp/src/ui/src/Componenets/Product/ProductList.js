import React, { useState, useEffect } from 'react';
import { ProductApiResources } from '@webapp/app';

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
    return (
        <div>
            <div><a href="/products/new">New</a></div>
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
                                        <a href={`products/${item.id}`}>View</a>
                                        <a href={`products/${item.id}`}>Delete</a>
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

