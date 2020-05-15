import React from 'react';
import {
  BrowserRouter as Router,
  Switch,
  Route
} from "react-router-dom";

import GamePlayRouter from './GamePlayRouter';
import Intro from '../components/Intro/Intro'
import SelectGame from '../components/SelectGame/SelectGame';
import getCookieData from '../util/getCookieData'

function AppRouter() {
  console.log(getCookieData("userId"));

  return (
    <Router>
      <Switch>
        <Route exact={true} path="/">
          <Intro />
        </Route>
        <Route path="/gameselect">
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
