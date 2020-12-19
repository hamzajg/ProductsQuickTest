import React, { useState, useEffect } from 'react';
import { CustomerApiResources } from '@webapp/app';

const CustomerDetails = (prop) => {
    const customerId = prop.match.params.id;
    const [customer, setCustomer] = useState({})
    const apiReosurces = new CustomerApiResources()

    useEffect(() => {
        async function fetchData() {
            const item = await apiReosurces.getCustomerById(customerId)
            setCustomer(item);
        }
        if (customerId != undefined)
            fetchData();
    }, []);
    const onInputchange = (event) => {
        const value = event.target.value;
        setCustomer({
            ...customer,
            [event.target.name]: value
        });
    }
    const save = async () => {
        if (customer.id == undefined) {
            const item = await apiReosurces.createCustomer(customer)
            setCustomer(item);
        } else {
            const item = await apiReosurces.updateCustomer(customer)
            setCustomer(item);
        }
    }
    return (
        <div>
            <div>
                <label>First Name: </label>
                <input type='text' name="firstName" value={customer.firstName} onChange={onInputchange} />
            </div>
            <div>
                <label>Last Name: </label>
                <input type='text' name="lastName" value={customer.lastName} onChange={onInputchange} />
            </div>
            <div>
                <label>Address: </label>
                <input type='text' name="address" value={customer.address} onChange={onInputchange} />
            </div>
            <div>
                <label>Email: </label>
                <input type='text' name="email" value={customer.email} onChange={onInputchange} />
            </div>
            <div>
                <label>Mobile: </label>
                <input type='text' name="mobile" value={customer.mobile} onChange={onInputchange} />
            </div>
            <button onClick={save}>Save</button>
        </div>
    )
}
export default CustomerDetails;