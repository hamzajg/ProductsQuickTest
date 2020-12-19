import React from 'react';
import { ProductCategoryApiResources } from '@webapp/app';

const ProductDetails = () => {
    const apiReosurces = new ProductCategoryApiResources()
    return (
        <div>
            <form>
                <div>
                    <label>Name: </label>
                    <input type='text' />
                </div>
                <div>
                    <label>Category: </label>
                    <select>
                        {
                            apiReosurces.getAllProductCategories().map(item => {
                                return (
                                    <option value="{item.id}">{item.name}</option>
                                )
                            })
                        }
                    </select>
                </div>
                <div>
                    <label>Unit Price: </label>
                    <input type='text' />
                </div>
                <div>
                    <label>Discount: </label>
                    <input type='text' />
                </div>
                <div>
                    <label>Available Qty: </label>
                    <input type='text' />
                </div>
                <button>Save</button>
            </form>
        </div>
    )
}
export default ProductDetails;