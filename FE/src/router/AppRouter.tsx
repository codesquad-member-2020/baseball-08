import React from 'react';
import {
  BrowserRouter as Router,
  Switch,
  Route
} from "react-router-dom";


import GamePlayRouter from './GamePlayRouter';
import SelectGame from '../components/SelectGame/SelectGame';

function AppRouter() {
  return (
    <Router>
      <Switch>
        <Route exact={true} path="/">
          <SelectGame />
        </Route>
        <Route path="/gameplay">
          <GamePlayRouter />
        </Route>
      </Switch>
    </Router>
  );
}

export default AppRouter;
