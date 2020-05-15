import React from 'react';
import './lib/style/reset.css'
import './App.css';

import AppRouter from './router/AppRouter'
import getCookieData from './util/getCookieData'

function App() {
  console.log(getCookieData("userId"));

  return (
      <div className="App">
        <AppRouter />
      </div>
  );
}

export default App;
