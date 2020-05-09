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

const StyledAwayTeamName = styled.p`
  width: 250px;
  float: left;
  position: relative;
  font-size: 30px;
  margin: 0 auto;
  color: black;
  font-weight: bold;
  text-align: center;
  margin-left: 20px;

  &:hover {
    color: #cc0000;
    cursor: pointer;
  }
`;

const StyledHomeTeamName = styled.p`
  width: 250px;
  float: right;
  position: relative;
  font-size: 30px;
  margin: 0 auto;
  color: black;
  font-weight: bold;
  text-align: center;
  margin-right: 20px;

  &:hover {
    color: #cc0000;
    cursor: pointer;
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
  index: number,
  awayTeamName: string,
  homeTeamName: string
}

const Versus: React.FunctionComponent<Props> = function({index, awayTeamName, homeTeamName}) {
  return (
    <StyledVersus>
      <StyledGameTitle>Game {index}</StyledGameTitle>
      <StyledAwayTeamName>{awayTeamName}</StyledAwayTeamName>
      <StyledVersusText>VS</StyledVersusText>
      <StyledHomeTeamName>{homeTeamName}</StyledHomeTeamName>
    </StyledVersus>
  );
}

export default Versus;