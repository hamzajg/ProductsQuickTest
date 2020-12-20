import './App.css';
import {
  NavBar, Home, ProductCategoryList, ProductCategoryDetails,
  ProductList, ProductDetails, CustomerList, CustomerDetails
} from './Componenets';
import Container from 'react-bootstrap/Container';
import { Route, Switch } from 'react-router-dom';

function App() {
  return (
    <Container className="p-3">
      <NavBar />
      <main>
        <Switch>
          <Route exact path='/' component={Home} />
          <Route exact path='/product-categories' component={ProductCategoryList} />
          <Route exact path='/product-categories/new' component={ProductCategoryDetails} />
          <Route exact path='/product-categories/:id' component={ProductCategoryDetails} />
          <Route exact path='/products' component={ProductList} />
          <Route exact path='/products/new' component={ProductDetails} />
          <Route exact path='/products/:id' component={ProductDetails} />
          <Route exact path='/customers' component={CustomerList} />
          <Route exact path='/customers/new' component={CustomerDetails} />
          <Route exact path='/customers/:id' component={CustomerDetails} />
        </Switch>
      </main>
    </Container>
  );
}

export default App;
