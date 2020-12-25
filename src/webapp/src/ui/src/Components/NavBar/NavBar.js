import React from 'react';
import { LinkContainer } from 'react-router-bootstrap';
import { Button, ButtonToolbar } from 'react-bootstrap';

const NavBar = () => {
    return (
        <nav>
            <ButtonToolbar className="custom-btn-toolbar">
                <LinkContainer to="/">
                    <Button>Home</Button>
                </LinkContainer>
                <LinkContainer to="/product-categories">
                    <Button>Product Categories</Button>
                </LinkContainer>
                <LinkContainer to="/products">
                    <Button>Products</Button>
                </LinkContainer>
                <LinkContainer to="/customers">
                    <Button>Customers</Button>
                </LinkContainer>
            </ButtonToolbar>
        </nav>
    )
}
export default NavBar;

