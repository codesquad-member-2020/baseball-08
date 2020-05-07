import React from 'react';
import styled from 'styled-components'
import StadiumBackground from './publicComponent/StadiumBackground'
import InningBoard from './publicComponent/InningBoard'

const StyledDiv = styled.div`
  position: absolute;
  width: 1280px;
  height: 720px;
  margin: 0 auto;
  z-index: 0;
`;

function GamePlay() {
  const gameDetailObj = {
    "away": "team2",
    "home": "team3",
    "awayTotalScore": 9,
    "homeTotalScore": 10,
    "user": "myGithubId",
    "inning": 2,
    "turn": true,
    "score": {
      "strike": 2,
      "ball": 3,
      "out": 2,
      "base": 2
    },
    "pitcher": {
      "name": "current_pitcher",
      "pitches": 50
    },
    "hitter": {
      "name": "current_hitter",
      "atBat": 1,
      "hit": 0
    },
    "history": [
      {
        "name": "hitter_name1",
        "lineUp": 7,
        "hitLog": [
          "S",
          "B",
          "B",
          "B",
          "S"
        ]
      },
      {
        "name": "hitter_name2",
        "lineUp": 6,
        "hitLog": [
          "S",
          "B",
          "B",
          "H"
        ]
      },
      {
        "name": "hitter_name3",
        "lineUp": 5,
        "hitLog": [
          "S",
          "B",
          "B",
          "O"
        ]
      }
    ]
  };

  return (
    <StyledDiv>
      <StadiumBackground></StadiumBackground>
      <InningBoard 
        awayTeamName={gameDetailObj.away} awayTeamScore={gameDetailObj.awayTotalScore}
        homeTeamName={gameDetailObj.home} homeTeamScore={gameDetailObj.homeTotalScore}
      ></InningBoard>
    </StyledDiv>
  );
}

export default GamePlay;
