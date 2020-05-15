import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import { BrowserRouter } from 'react-router-dom';
import getCookieData from './util/getCookieData'

console.log(getCookieData("userId"))

ReactDOM.render(
  <React.StrictMode>
    <div className="Bg">
    <App />
    </div>
  </React.StrictMode>,
  document.getElementById('root')
);


