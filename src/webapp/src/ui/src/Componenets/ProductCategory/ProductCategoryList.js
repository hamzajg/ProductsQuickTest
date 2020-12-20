import React, { useState, useEffect } from 'react';
import { ProductCategoryApiResources } from '@webapp/app';

const ProductCategoryList = () => {
    const [list, setList] = useState([])
    const apiReosurces = new ProductCategoryApiResources()

    useEffect(() => {
        async function fetchData() {
            const items = await apiReosurces.getAllProductCategories()
            setList(items);
        }
        fetchData();
    }, []);

    const deleteItem = async (item) => {
        alert("Sute to delete item: " + item.name);
        await apiReosurces.getDeleteProductCategory(item.id)
        await fetchData();
    }
    return (
        <div>
            <div><a href="/product-categories/new">New</a></div>
            <table>
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
                                        <a href={`product-categories/${item.id}`}>View</a>
                                        <a href="#" onClick={() => deleteItem(item)}>Delete</a>
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

