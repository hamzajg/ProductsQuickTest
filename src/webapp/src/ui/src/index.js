import 'react-app-polyfill/ie11'; // For IE 11 support
import 'react-app-polyfill/stable';
import React from 'react';
import ReactDOM from 'react-dom';
import 'core-js';
import './polyfill'
import './index.css';
import App from './App';
import * as serviceWorker from './serviceWorker';
import reportWebVitals from './reportWebVitals';
import { Router } from 'react-router-dom';
import { createBrowserHistory } from 'history';

import 'bootstrap/dist/css/bootstrap.min.css';

export const history = createBrowserHistory();
ReactDOM.render(
  <React.StrictMode>
    <Router history={history}><App /></Router>
  </React.StrictMode>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: http://bit.ly/CRA-PWA
serviceWorker.unregister();
