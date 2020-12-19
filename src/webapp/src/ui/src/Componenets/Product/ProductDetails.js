import React, { useState, useEffect } from 'react';
import { ProductCategoryApiResources, ProductApiResources } from '@webapp/app';

const ProductDetails = (prop) => {
    const productId = prop.match.params.id;
    const [categories, setCategories] = useState([])
    const [product, setProduct] = useState({})
    const apiReosurces = new ProductCategoryApiResources()
    const productApiReosurces = new ProductApiResources()

    useEffect(() => {
        async function fetchData() {
            const items = await apiReosurces.getAllProductCategories()
            setCategories(items);
        }
        fetchData();
        async function fetchProduct() {
            const item = await productApiReosurces.getProductById(productId)
            setProduct(item);
        }
        if (productId != undefined)
            fetchProduct();
    }, []);
    const onInputchange = (event) => {
        const value = event.target.value;
        setProduct({
            ...product,
            [event.target.name]: value
        });
    }
    const save = async () => {
        console.log(product)
        if(product.id == undefined) {
            const item = await productApiReosurces.createProduct(product)
            setProduct(item);
        } else {
        const item = await productApiReosurces.updateProduct(product)
        setProduct(item);
        }
    }
    return (
        <div>
            <div>
                <label>Name: </label>
                <input type='text' name="name" value={product.name} onChange={onInputchange} />
            </div>
            <div>
                <label>Category: </label>
                <select name="categoryId" onChange={onInputchange}>
                    <option></option>
                    {
                        categories.map(item => {
                            return (
                                <option value={item.id} selected={item.id === product.category.id}>{item.name}</option>
                            )
                        })
                    }
                </select>
            </div>
            <div>
                <label>Unit Price: </label>
                <input type='text' name="unitPrice" value={product.unitPrice} onChange={onInputchange} />
            </div>
            <div>
                <label>Discount: </label>
                <input type='text' name="discount" value={product.discount} onChange={onInputchange} />
            </div>
            <div>
                <label>Available Qty: </label>
                <input type='text' name="availableQty" value={product.availableQty} onChange={onInputchange} />
            </div>
            <button onClick={save}>Save</button>
        </div>
    )
}
export default ProductDetails;