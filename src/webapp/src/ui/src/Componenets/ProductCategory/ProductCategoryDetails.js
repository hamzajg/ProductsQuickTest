import React, { useState, useEffect } from 'react';
import { ProductCategoryApiResources } from '@webapp/app';

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
        <div>
            <div>
                <label>Name: </label>
                <input type='text' value={category.name} onChange={onInputchange} />
            </div>
            <button onClick={save}>Save</button>
        </div>
    )
}
export default ProductCategoryDetails;