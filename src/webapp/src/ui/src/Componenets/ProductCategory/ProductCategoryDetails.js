import React from 'react';
import { ProductCategoryApiResources } from '@webapp/app';

const ProductCategoryDetails = (prop) => {
    const productCategoryId = prop.match.params.id;
    console.log(productCategoryId);
    const apiReosurces = new ProductCategoryApiResources()
    var category = apiReosurces.getProductCategoryById(productCategoryId)
    console.log(category);
    return (
        <div>
            <form>
                <div>
                    <label>Name: </label>
                    <input type='text' value={category.name}/>
                </div>
                <button>Save</button>
            </form>
        </div>
    )
}
export default ProductCategoryDetails;