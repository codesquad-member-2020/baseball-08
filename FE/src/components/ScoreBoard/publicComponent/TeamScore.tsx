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
  padding-right: 10px;
  padding-top: 5px;
  padding-bottom: 5px;
`;

const TeamName = styled.p`
  width: 240px;
  float: left;
  color: white;
  font-size: 30px;
  padding-right: 20px;
  text-align: center;
`;

interface SelectedTeamProps {
  isSelectedTeam?: boolean;
}

const BaseballImage = styled.div<SelectedTeamProps>`
  width: 45px;
  height: 45px;
  float: left;
  background-image: url("http://dev-angelo.dlinkddns.com/baseball.png");
  background-size: 100% 100%;
  opacity: ${props => props.isSelectedTeam ? "1" : "0"};
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
  totalScore: number,
  isSelectedTeam: boolean
}

const TeamScore: React.FunctionComponent<Props> = function({teamName, scores, totalScore, isSelectedTeam}) {
  return (
    <StyledDiv className="ScoreBoard">
      <BaseballImage isSelectedTeam={isSelectedTeam} />
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
