import React, { useState, useEffect } from 'react';
import { ProductApiResources } from '@webapp/app';
import { Link } from 'react-router-dom'
import { CRow, CCard, CCardHeader, CCardTitle, CCardBody, CDataTable, CButton, CAlert } from '@coreui/react'

const ProductList = () => {
    const [list, setList] = useState([])
    const [visibility, setVisibility] = useState(false)
    const apiReosurces = new ProductApiResources()

    useEffect(() => {
        async function fetchData() {
            const items = await apiReosurces.getAllProducts()
            setList(items);
            let currentCount = localStorage.getItem("productsCount") == undefined ? 0 : localStorage.getItem("productsCount")
            setVisibility(currentCount < items.length)
            localStorage.setItem("productsCount", items.length)
        }
        fetchData();
    }, []);
    const deleteItem = async (item) => {
        if (window.confirm("Confirm to delete item: " + item.name)) {
            await apiReosurces.deleteProduct(item.id)
            let newList = list.filter(l => l.id != item.id)
            setList(newList);
            localStorage.setItem("productsCount", list.length)
        }
    }
    const addedAlert = () => {
        return (
            <div className="mt-2">
                <CAlert color="info" closeButton>
                    Product added successfully!
              </CAlert>
            </div>
        )
    }
    const fields = [
        { key: 'number', _style: { width: '10%' } },
        { key: 'name', _style: { width: '20%' } },
        'category',
        { key: 'unitPrice', _style: { width: '10%' } },
        { key: 'discount', _style: { width: '10%' } },
        { key: 'availableQty', _style: { width: '10%' } },
        {
            key: 'actions', _style: { width: '20%' },
            sorter: false,
            filter: false
        }
    ]
    return (
        <CCard>
            <CCardHeader>Products List</CCardHeader>
            <CCardBody>
                <CRow>
                    {visibility && addedAlert()}
                </CRow>
                <CRow>
                    <Link to="/products/new">
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
                            'category':
                                (item, index) => {
                                    return (
                                        <td className="py-2">
                                            {item.category.name}
                                        </td>
                                    )
                                },
                            'actions':
                                (item) => {
                                    return (
                                        <td className="py-2">
                                            <Link to={`products/${item.id}`}>
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

export default ProductList;

