import React from 'react';
import styled from 'styled-components'

const StyledPAandNP = styled.div`
  width: 280px;
  height: 140px;
  left: 1000px;
  position: absolute;
  border: 1px solid white;
  color: white;
  background-color: black;
  box-shadow: 0 0 0 3px gray inset; 
  border-radius: 15px;
`;

const StyledPitcherArea = styled.div`
  width: 280px;
  height: 70px;
  position: relative;
  color: white;
  font-size: 22px;
  line-height: 35px;
  padding-left: 5px;
`;

const StyledPitcherTitle = styled.p`
  width: 280px;
  height: 35px;
  position: relative;
  color: #ff7733;
`;

const StyledPitcherName = styled.p`
  height: 35px;
  position: relative;
  float: left;
  color: white;
`;

const StyledPitches = styled.p`
  height: 35px;
  position: relative;
  float: left;
  color: steelblue;
  padding-left: 10px;
`;

const StyledBatterArea = styled.div`
  width: 280px;
  height: 70px;
  position: relative;
  color: white;
  font-size: 22px;
  line-height: 35px;
  padding-left: 5px;
`;

const StyledBatterTitle = styled.p`
  width: 280px;
  height: 35px;
  position: relative;
  color: #009900;
`;

const StyledBatterName = styled.p`
  height: 35px;
  position: relative;
  float: left;
  color: white;
`;

const StyledAtBat = styled.p`
  height: 35px;
  position: relative;
  float: left;
  color: steelblue;
  padding-left: 10px;
`;

const StyledHit = styled.p`
  height: 35px;
  position: relative;
  float: left;
  color: steelblue;
`;

interface Props {
  pitcherName: string,
  pitches: number,
  batterName: string,
  atBat: number,
  hit: number
}

const PAandNP: React.FunctionComponent<Props> = function({pitcherName, pitches, batterName, atBat, hit}) {
  return (
    <StyledPAandNP>
      <StyledPitcherArea>
        <StyledPitcherTitle>투수</StyledPitcherTitle>
        <StyledPitcherName>{pitcherName}</StyledPitcherName><StyledPitches>#{pitches}</StyledPitches>
      </StyledPitcherArea>
      <StyledBatterArea>
        <StyledBatterTitle>타자</StyledBatterTitle>
        <StyledBatterName>{batterName}</StyledBatterName><StyledAtBat>#{atBat}타석</StyledAtBat><StyledHit>#{hit}안타</StyledHit>
      </StyledBatterArea>

    </StyledPAandNP>
  )
}

export default PAandNP;
