import React, { useState, useEffect } from 'react';
import { ProductCategoryApiResources } from '@webapp/app';

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

