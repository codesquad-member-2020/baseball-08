import React from 'react';
import {
  BrowserRouter as Router,
  Switch,
  Route
} from "react-router-dom";

import GamePlayRouter from './GamePlayRouter';
import Intro from '../components/Intro/Intro'
import SelectGame from '../components/SelectGame/SelectGame';

function AppRouter() {
  return (
    <Router>
      <Switch>
        <Route exact={true} path="/api">
          <Intro />
        </Route>
        <Route path="/api/gameselect">
          <SelectGame />
        </Route>
        <Route path="/api/gameplay">
          <GamePlayRouter />
        </Route>
      </Switch>
    </Router>
  );
}

export default AppRouter;
