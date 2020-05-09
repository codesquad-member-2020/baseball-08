import React from 'react';
import styled from 'styled-components'
import TotalScore from './TotalScore'

const StyledDiv = styled.div`
  display: block;
  position: relative;
  width: 900px;
  height: 45px;
  color: white;
  font-size: 24px;
  padding-left: 10px;
  padding-right: 10px;
  padding-top: 5px;
  padding-bottom: 5px;
  margin-left: 70px;
`;

const TeamName = styled.p`
  width: 200px;
  float: left;
  color: white;
  font-size: 30px;
  padding-left: 10px;
  padding-right: 20px;
  text-align: right;
`;

const InningUL = styled.ul`
  position: relative;
  display: inline;
  color: white;
`;

const InningScore = styled.li`
  float: left;
  color: white;
  font-size: 30px;
  font-weight: bold;
  padding-left: 10px;
  padding-right: 10px;
`;

interface Props {
  teamName: string,
  scores: Array<number>,
  totalScore: number
}

const TeamScore: React.FunctionComponent<Props> = function({teamName, scores, totalScore}) {
  return (
    <StyledDiv className="ScoreBoard">
      <TeamName>{teamName}</TeamName>
      <InningUL>
        {scores.map((score: any, index: any) => (
          <InningScore key={index}>{score}</InningScore>
        ))}
      </InningUL>
      <TotalScore totalScore={totalScore}></TotalScore>
    </StyledDiv>
  );
}

export default TeamScore;
