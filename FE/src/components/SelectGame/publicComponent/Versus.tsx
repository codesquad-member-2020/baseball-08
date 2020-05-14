import React from 'react';
import styled from 'styled-components'

const StyledVersus = styled.div`
  width: 600px;
  height: 90px;
  position: relative;
  margin: 0 auto;
  background-color: lightgray;
  border-radius: 10px;
  margin-top: 30px;
  margin-bottom: 30px;
`;

const StyledGameTitle = styled.p`
  width: 600px;
  position: relative;
  font-size: 22px;
  margin: 0 auto;
  color: #cc0000;
  font-weight: bold;
  text-align: center;
`;

interface TeamNamePropsType {
  available?: boolean;
}

const StyledAwayTeamName = styled.p<TeamNamePropsType>`
  width: 250px;
  float: left;
  position: relative;
  font-size: 30px;
  margin: 0 auto;
  color: ${props => props.available ? "black" : "gray"};
  font-weight: bold;
  text-align: center;
  margin-left: 20px;

  &:hover {
    color: ${props => props.available ? "#cc0000" : "gray"};
    cursor: ${props => props.available ? "pointer" : "default"};
  }
`;

const StyledHomeTeamName = styled.p<TeamNamePropsType>`
  width: 250px;
  float: right;
  position: relative;
  font-size: 30px;
  margin: 0 auto;
  color: ${props => props.available ? "black" : "gray"};
  font-weight: bold;
  text-align: center;
  margin-right: 20px;

  &:hover {
    color: ${props => props.available ? "#cc0000" : "gray"};
    cursor: ${props => props.available ? "pointer" : "default"};
  }
`;

const StyledVersusText = styled.p`
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
  font-size: 20px;
  margin: 0 auto;
  color: gray;
  font-weight: bold;
  margin-top: 10px;
`;

interface Props {
  gameId: number,
  awayTeamName: string,
  homeTeamName: string,
  awayTeamId: number,
  homeTeamId: number,
  awayTeamAvailable: boolean,
  homeTeamAvailable: boolean,
  onTeamClick(gameId: number, teamId: number, isAwayTeam: boolean, teamName: string): void,
}

const Versus: React.FunctionComponent<Props> = function ({
  gameId,
  awayTeamName,
  homeTeamName,
  awayTeamAvailable,
  homeTeamAvailable,
  awayTeamId,
  homeTeamId,
  onTeamClick,
}) {
  return (
    <StyledVersus>
      <StyledGameTitle>Game {gameId}</StyledGameTitle>
      <StyledAwayTeamName
        {...(awayTeamAvailable && {
          onClick: () => onTeamClick(gameId, awayTeamId, true, awayTeamName),
        })}
        available={awayTeamAvailable}
      >
        {awayTeamName}
      </StyledAwayTeamName>
      <StyledVersusText>VS</StyledVersusText>
      <StyledHomeTeamName
        {...(homeTeamAvailable && {
          onClick: () => onTeamClick(gameId, homeTeamId, false, homeTeamName),
        })}
        available={homeTeamAvailable}
      >
        {homeTeamName}
      </StyledHomeTeamName>
    </StyledVersus>
  );
};

export default Versus;