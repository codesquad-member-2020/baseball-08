import React from 'react';
import styled from 'styled-components'

const StyledVersus = styled.div`
  width: 400px;
  height: 90px;
  position: relative;
  margin: 0 auto;
  background-color: lightgray;
  border-radius: 10px;
  margin-top: 30px;
  margin-bottom: 30px;
`;

const StyledGameTitle = styled.p`
  width: 400px;
  position: relative;
  font-size: 20px;
  margin: 0 auto;
  color: #cc0000;
  font-weight: bold;
  text-align: center;
`;

const StyledAwayTeamName = styled.p`
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

function Versus(props: any) {
  return (
    <StyledVersus>
      <StyledGameTitle>Game {props.index}</StyledGameTitle>
      <StyledAwayTeamName>{props.awayTeamName}</StyledAwayTeamName>
      <StyledVersusText>VS</StyledVersusText>
      <StyledHomeTeamName>{props.homeTeamName}</StyledHomeTeamName>
    </StyledVersus>
  );
}

export default Versus;