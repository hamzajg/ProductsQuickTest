import logo from './logo.svg';
import './App.css';
import { NavBar, Home, ProductCategoryList, ProductList, CustomerList } from './Componenets';
import { Route, Switch } from 'react-router-dom';

function App() {
  return (
    <div className="App">
      <NavBar />
      <main>
        <Switch>
          <Route exact path='/' component={Home} />
          <Route exact path='/product-categories' component={ProductCategoryList} />
          <Route exact path='/products' component={ProductList} />
          <Route exact path='/customers' component={CustomerList} />
        </Switch>
      </main>
    </div>
  );
}

export default App;
