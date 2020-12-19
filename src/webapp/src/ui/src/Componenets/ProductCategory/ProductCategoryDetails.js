import React, { useState, useEffect } from 'react';
import { ProductCategoryApiResources } from '@webapp/app';

const ProductCategoryDetails = (prop) => {
    const productCategoryId = prop.match.params.id;
    const [category, setCategory] = useState({})
    const apiReosurces = new ProductCategoryApiResources()
    useEffect(() => {
        async function fetchData() {
            const item = await apiReosurces.getProductCategoryById(productCategoryId)
            setCategory(item);
        }
        if (productCategoryId != undefined)
            fetchData();
    }, []);
    const onInputchange = (event) => {
        setCategory({
            name: event.target.value
        });
    }
    const save =  async() => {
        const item = await apiReosurces.createProductCategory(category)
        setCategory(item);
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