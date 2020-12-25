import React from 'react'
import './App.css';
import './scss/style.scss';

import { HashRouter, Route, Switch } from 'react-router-dom';

// Containers
const DefaultLayout = React.lazy(() => import('./containers/DefaultLayout/DefaultLayout'));

const loading = (
  <div className="pt-3 text-center">
    <div className="sk-spinner sk-spinner-pulse"></div>
  </div>
)
const App = () => {
  return (
    <HashRouter>
      <React.Suspense fallback={loading}>
        <Switch>
          <Route path="/" name="Home" render={props => <DefaultLayout {...props} />} />
        </Switch>
      </React.Suspense>
    </HashRouter>
  );
}

export default App;
