import React, { useEffect, useState } from 'react';
import styled, { keyframes } from 'styled-components'
import Inning from './publicComponent/Inning'
import TeamScore from './publicComponent/TeamScore'
import fetchRequest from '../../util/fetchRequest'

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
    fetchRequest("https://4ea8bf16-a9c4-4101-8626-a7c53c0b1e89.mock.pstmn.io/detail/1/score", "GET")
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
