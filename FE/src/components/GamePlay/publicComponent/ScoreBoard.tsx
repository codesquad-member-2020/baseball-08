import React from 'react';
import styled from 'styled-components'

const StyledVersus = styled.div`
  width: 1000px;
  height: 140px;
  position: absolute;
  border-bottom: 2px solid white;
  border-right: 2px solid white;
  color: white;

  &:hover {
    cursor: pointer;
  }
`;

const StyledScoreBoardTitle = styled.p`
  width: 400px;
  position: relative;
  font-size: 28px;
  margin: 0 auto;
  font-weight: bold;
  text-align: center;
  margin-top: 5px;
  margin-bottom: 5px;
`;

const StyledTeamName = styled.p`
  width: 350px;
  float: left;
  position: relative;
  font-size: 33px;
  margin: 0 auto;
  font-weight: bold;
  text-align: center;
  margin-left: 20px;
`;

const StyledTeamScore = styled.p`
  width: 100px;
  float: left;
  position: relative;
  font-size: 33px;
  margin: 0 auto;
  font-weight: bold;
  text-align: center;
  margin-left: 20px;
`;

const StyledVersusText = styled.p`
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
  font-size: 26px;
  margin: 0 auto;
  color: gray;
  font-weight: bold;
  margin-top: 7px;
`;

interface PlayerTextType {
  isAwayTeam?: boolean;
}

const StyledPlayerText = styled.p<PlayerTextType>`
  width: 350px;
  float: left;
  position: relative;
  font-size: 26px;
  font-weight: bold;
  color: #cc0000;
  text-align: center;
  /* margin-left: 20px; */
  margin-left: ${props => props.isAwayTeam ? "20px" : "630px"};
`;

interface Props {
  awayTeamName: string,
  awayTeamScore: number,
  homeTeamName: string,
  homeTeamScore: number,
  isAwayTeam: boolean,
  onScoreBoardClick(): void
}

const ScoreBoard: React.FunctionComponent<Props> = function({awayTeamName, awayTeamScore, homeTeamName, homeTeamScore, isAwayTeam, onScoreBoardClick}) {
  return (
    <StyledVersus onClick={onScoreBoardClick}>
      <StyledScoreBoardTitle>BaseballGame Online</StyledScoreBoardTitle>
      <StyledTeamName>{awayTeamName}</StyledTeamName>
      <StyledTeamScore>{awayTeamScore}</StyledTeamScore>
      <StyledVersusText>VS</StyledVersusText>
      <StyledTeamScore>{homeTeamScore}</StyledTeamScore>
      <StyledTeamName>{homeTeamName}</StyledTeamName>
      <StyledPlayerText isAwayTeam={isAwayTeam}>Player</StyledPlayerText>
    </StyledVersus>
  );
}

export default ScoreBoard;
