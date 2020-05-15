import React from 'react';
import {
  BrowserRouter as Router,
  Route
} from "react-router-dom";

import GamePlay from '../components/GamePlay/GamePlay'
import ScoreBoard from '../components/ScoreBoard/ScoreBoard';
import PlayerList from '../components/PlayerList/PlayerList';

function GamePlayRouter() {
  return (
    <Router>
      <Route path="/gameplay" component={GamePlay} />
      <Route path="/gameplay/scoreboard" component={ScoreBoard} />
      <Route path="/gameplay/playerlist" component={PlayerList} />
    </Router>
  );
}

export default GamePlayRouter;
