import './App.css';
import {
  NavBar, Home, ProductCategoryList, ProductCategoryDetails,
  ProductList, ProductDetails, CustomerList, CustomerDetails
} from './Componenets';
import { Route, Switch } from 'react-router-dom';

function App() {
  return (
    <div>
      <NavBar />
      <main className="App">
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
    </div>
  );
}

export default App;
