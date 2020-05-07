import React from 'react';
import styled from 'styled-components'

const StyledVersus = styled.div`
  width: 1000px;
  height: 120px;
  position: absolute;
  border-bottom: 2px solid white;
  border-right: 2px solid white;
  color: white;
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
  font-size: 40px;
  margin: 0 auto;
  font-weight: bold;
  text-align: center;
  margin-left: 20px;
`;

const StyledTeamScore = styled.p`
  width: 100px;
  float: left;
  position: relative;
  font-size: 40px;
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
  margin-top: 13px;
`;

function InningBoard(props: any) {
  return (
    <StyledVersus>
      <StyledScoreBoardTitle>BaseballGame Online</StyledScoreBoardTitle>
      <StyledTeamName>{props.awayTeamName}</StyledTeamName>
      <StyledTeamScore>{props.awayTeamScore}</StyledTeamScore>
      <StyledVersusText>VS</StyledVersusText>
      <StyledTeamScore>{props.homeTeamScore}</StyledTeamScore>
      <StyledTeamName>{props.homeTeamName}</StyledTeamName>
    </StyledVersus>
  );
}

export default InningBoard;
