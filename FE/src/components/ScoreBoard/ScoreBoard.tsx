import React from 'react';
import styled, { keyframes } from 'styled-components'

import Inning from './publicComponent/Inning'
import TeamScore from './publicComponent/TeamScore'

const slidein = keyframes `
  0% { opacity: 0 }
  100% { opacity: 1; }
`;

const StyledDiv = styled.div`
  position: absolute;
  width: 1280px;
  height: 720px;
  z-index: 1;
  background-color: rgba(0, 0, 0, 0.8);
  animation-duration: 0.7s;
  animation-name: ${slidein};
`;

const ScoreBoardWrap = styled.div`
  position: relative;
  width: 1000px;
  height: 200px;
  margin: 0 auto;
  margin-top: 20px;
  z-index: 1;
  border: 2px solid white;
  border-radius: 10px;
  background-color: rgba(0, 0, 0, 0.8);
`;

function ScoreBoard() {
  const gameScoreObj = [
    {
      "team": "team1",
      "score": [
        1,
        2,
        3,
        4
      ],
      "totalScore": 10,
      "user": "githubId2",
      "turn": true
    },
    {
      "team": "team2",
      "score": [
        2,
        3,
        4,
        5
      ],
      "totalScore": 14,
      "user": "githubId3",
      "turn": false
    }
  ];

  return (
    <StyledDiv className="ScoreBoard">
      <ScoreBoardWrap>
        <Inning />
        {gameScoreObj.map((scoreInformation: any, index: any) => (
          <TeamScore key={index} teamName={scoreInformation.team} scores={scoreInformation.score} totalScore={scoreInformation.totalScore}/>
        ))}
      </ScoreBoardWrap>
    </StyledDiv>
  );
}

export default ScoreBoard;
