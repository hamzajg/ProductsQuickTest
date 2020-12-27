import React, { useState, useEffect } from 'react';
import { CustomerApiResources } from '@webapp/app';
import { Link } from 'react-router-dom'
import { CRow, CCard, CCardHeader, CCardTitle, CCardBody, CDataTable, CButton, CAlert } from '@coreui/react'

const CustomerList = () => {
    const [list, setList] = useState([])
    const [visibility, setVisibility] = useState(false)
    const apiReosurces = new CustomerApiResources()

    useEffect(() => {
        async function fetchData() {
            const items = await apiReosurces.getAllCustomers()
            setList(items);
            let currentCount = localStorage.getItem("customersCount") == undefined ? 0 : localStorage.getItem("customersCount")
            setVisibility(currentCount < items.length)
            localStorage.setItem("customersCount", items.length)
        }
        fetchData();
    }, []);
    const deleteItem = async (item) => {
        if (window.confirm("Confirm to delete item: " + item.firstName + " " + item.lastName)) {
            await apiReosurces.deleteCustomer(item.id)
            let newList = list.filter(l => l.id != item.id)
            setList(newList);
            localStorage.setItem("customersCount", list.length)
        }
    }
    const addedAlert = () => {
        return (
            <div className="mt-2">
                <CAlert color="info" closeButton>
                    Customer added successfully!
              </CAlert>
            </div>
        )
    }
    const fields = [
        { key: 'number', _style: { width: '5%' } },
        { key: 'firstName', _style: { width: '15%' } },
        { key: 'lastName', _style: { width: '15%' } },
        { key: 'address', _style: { width: '20%' } },
        { key: 'email', _style: { width: '15%' } },
        { key: 'mobile', _style: { width: '10%' } },
        {
            key: 'actions', _style: { width: '20%' },
            sorter: false,
            filter: false
        }
    ]
    return (
        <CCard>
            <CCardHeader>Customers List</CCardHeader>
            <CCardBody>
                <CRow>
                    {visibility && addedAlert()}
                </CRow>
                <CRow>
                    <Link to="/customers/new">
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
                                            <Link to={`customers/${item.id}`}>
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

export default CustomerList;
