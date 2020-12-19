import React from 'react';

const CustomerDetails = () => {
    return (
        <div>
            <form>
                <div>
                    <label>First Name: </label>
                    <input type='text' />
                </div>
                <div>
                    <label>Last Name: </label>
                    <input type='text' />
                </div>
                <div>
                    <label>Address: </label>
                    <input type='text' />
                </div>
                <div>
                    <label>Email: </label>
                    <input type='text' />
                </div>
                <div>
                    <label>Mobile: </label>
                    <input type='text' />
                </div>
                <button>Save</button>
            </form>
        </div>
    )
}
export default CustomerDetails;