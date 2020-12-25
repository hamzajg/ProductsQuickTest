import React from 'react';
const Home = React.lazy(() => import('./Components/Home/Home'));
const ProductCategoryList = React.lazy(() => import('./Components/ProductCategory/ProductCategoryList'));
const ProductCategoryDetails = React.lazy(() => import('./Components/ProductCategory/ProductCategoryDetails'));
const ProductList = React.lazy(() => import('./Components/Product/ProductList'));
const ProductDetails = React.lazy(() => import('./Components/Product/ProductDetails'));
const CustomerList = React.lazy(() => import('./Components/Customer/CustomerList'));
const CustomerDetails = React.lazy(() => import('./Components/Customer/CustomerDetails'));

const routes = [
  { path: '/', exact: true, name: 'Home' },
  { path: '/overview', name: "Overview", component: Home },
  { path: '/product-categories', exact: true,name: "ProductCategoryList", component: ProductCategoryList },
  { path: '/product-categories/new', exact: true,name: "ProductCategoryDetails", component: ProductCategoryDetails },
  { path: '/product-categories/:id', exact: true,name: "ProductCategoryDetails", component: ProductCategoryDetails },
  { path: '/products', exact: true,name: "ProductList", component: ProductList },
  { path: '/products/new', exact: true,name: "ProductDetails", component: ProductDetails },
  { path: '/products/:id', exact: true,name: "ProductDetails", component: ProductDetails },
  { path: '/customers', exact: true,name: "CustomerList", component: CustomerList },
  { path: '/customers/new', exact: true,name: "CustomerDetails", component: CustomerDetails },
  { path: '/customers/:id', exact: true,name: "CustomerDetails", component: CustomerDetails },
];

export default routes;
