import React, { useState, useEffect } from 'react';
import { CustomerApiResources } from '@webapp/app';

const CustomerList = () => {
    const [list, setList] = useState([])
    const apiReosurces = new CustomerApiResources()

    useEffect(() => {
        async function fetchData() {
            const items = await apiReosurces.getAllCustomers()
            setList(items);
        }
        fetchData();
    }, [list]);
    const deleteItem = async (item) => {
        if (window.confirm("Confirm to delete item: " + item.name)) {
            await apiReosurces.deleteCustomer(item.id)
            delete list[list.indexOf(item)]
            setList(list);
        }
    }
    return (
        <div>
            <div><a href="/customers/new">New</a></div>
            <table>
                <thead>
                    <td>#</td>
                    <td>First Name</td>
                    <td>Last Name</td>
                    <td>Address</td>
                    <td>Email</td>
                    <td>Mobile</td>
                    <td>Actions</td>
                </thead>
                <tbody>
                    {
                        list.map((item, index) => {
                            return (
                                <tr>
                                    <td>{index + 1}</td>
                                    <td>{item.firstName}</td>
                                    <td>{item.lastName}</td>
                                    <td>{item.address}</td>
                                    <td>{item.email}</td>
                                    <td>{item.mobile}</td>
                                    <td>
                                        <a href={`customers/${item.id}`}>View</a>
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

export default CustomerList;
