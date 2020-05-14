import React, { useEffect, useState } from 'react';
import styled, { keyframes } from 'styled-components'
import Inning from './publicComponent/Inning'
import TeamScore from './publicComponent/TeamScore'
import fetchRequest from '../../util/fetchRequest'
import GameData from '../../data/GameData'
import getCookieData from '../../util/getCookieData'

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
  const [scoreList, setScoreList] = useState<any>(undefined);

  useEffect(() => {
    const url = process.env.REACT_APP_GAME_SCORE;
    const cvtUrl = url?.replace(`{gameId}`, (GameData.getInstance().getGameId()).toString());

    fetchRequest(cvtUrl, "GET", getCookieData('userId'))
    .then((response) => response.json())
    .then((scoreList) => {
      setScoreList(scoreList);
    })
    .catch((error) => {
      alert("주의");
    });
  }, [])
  

  return (
    <StyledDiv className="ScoreBoard">
      <ScoreBoardWrap>
        <Inning />
        {scoreList && scoreList.map((scoreInformation: any, index: any) => (
          <TeamScore key={index} teamName={scoreInformation.team} scores={scoreInformation.score} totalScore={scoreInformation.totalScore}/>
        ))}
      </ScoreBoardWrap>
    </StyledDiv>
  );
}

export default ScoreBoard;
