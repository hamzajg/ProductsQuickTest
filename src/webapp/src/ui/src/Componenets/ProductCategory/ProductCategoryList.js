import React from 'react';
import { ProductCategoryApiResources } from '@webapp/app';

const ProductCategoryList = () => {
    const apiReosurces = new ProductCategoryApiResources()
    return (
        <div>
            <table>
                <thead>
                    <td>#</td>
                    <td>Name</td>
                    <td>Actions</td>
                </thead>
                <tbody>
                    {
                        apiReosurces.getAllProductCategories().map((item, index) => {
                            return (
                                <tr>
                                    <td>{index + 1}</td>
                                    <td>{item.name}</td>
                                    <td>
                                        <a href="product-categories/{item.id  }">View</a>
                                        <a href="product-categories/{item.id }">Delete</a>
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
export default ProductCategoryList;

