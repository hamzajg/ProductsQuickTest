import React, { useState, useEffect } from 'react';
import { ProductCategoryApiResources, ProductApiResources } from '@webapp/app';
import { Link } from 'react-router-dom'
import { CRow, CCard, CCardHeader, CCardTitle, CCardBody, CDataTable, CButton, CAlert } from '@coreui/react'

const ProductCategoryList = () => {
    const [list, setList] = useState([])
    const [visibility, setVisibility] = useState(false)
    const apiReosurces = new ProductCategoryApiResources()
    const productApiReosurces = new ProductApiResources()

    const fetchData = async () => {
        const items = await apiReosurces.getAllProductCategories()
        setList(items);
        let currentCount = localStorage.getItem("categoriesCount") == undefined ? 0 : localStorage.getItem("categoriesCount")
        setVisibility(currentCount < items.length)
        localStorage.setItem("categoriesCount", items.length)
    }

    useEffect(() => {
        fetchData();
    }, []);

    const deleteItem = async (item) => {
        if (window.confirm("Confirm to delete item: " + item.name)) {
            var items = await productApiReosurces.getAllProductsByCategoryId(item.id)
            if (Array.isArray(items) && items.length) {
                alert('Cannot delete category having products.')
                return
            }
            await apiReosurces.deleteProductCategory(item.id)
            let newList = list.filter(l => l.id != item.id)
            setList(newList);
            localStorage.setItem("categoriesCount", list.length)
        }
    }
    const addedAlert = () => {
        return (
            <div className="mt-2">
                <CAlert color="info" closeButton>
                    Category added successfully!
              </CAlert>
            </div>
        )
    }
    const fields = [
        { key: 'number', _style: { width: '20%' } },
        { key: 'name', _style: { width: '60%' } },
        {
            key: 'actions', _style: { width: '20%' },
            sorter: false,
            filter: false
        }
    ]
    return (
        <CCard>
            <CCardHeader>Category List</CCardHeader>
            <CCardBody>
                <CRow>
                    {visibility && addedAlert()}
                </CRow>
                <CRow>
                    <Link to="/product-categories/new">
                        <CButton color='primary' className="m-2">New</CButton>
                    </Link>
                </CRow>
                <CRow>
                    <CDataTable items={list}
                        fields={fields}
                        tableFilter
                        itemsPerPageSelect
                        itemsPerPage={5}
                        hover
                        sorter
                        pagination
                        scopedSlots={{
                            'number':
                                (item, index) => {
                                    return (
                                        <td className="py-2">
                                            {index + 1}
                                        </td>
                                    )
                                },
                            'actions':
                                (item) => {
                                    return (
                                        <td className="py-2">
                                            <Link to={`product-categories/${item.id}`}>
                                                <CButton color='info' className="m-2">Update</CButton>
                                            </Link>
                                            {' '}
                                            <CButton color='danger' className="m-2" onClick={() => deleteItem(item)}>Delete</CButton>

                                        </td>
                                    )
                                },
                        }}
                    />
                </CRow>
            </CCardBody>
        </CCard>
    )
}
export default ProductCategoryList;

