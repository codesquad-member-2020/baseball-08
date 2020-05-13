import React from 'react';
import styled from 'styled-components'

const StyledDiv = styled.div`
  width: 200px;
  height: 150px;
  top: 155px;
  left: 15px;
  position: absolute;
  border: 1px solid white;
  color: white;
  background-color: black;
`;

const StyledStrikeCountArea = styled.div`
  width: 200px;
  height: 50px;
  position: relative;
  color: white;
  background-color: black;
`;

const StyledSBOTextArea = styled.p`
  width: 50px;
  height: 50px;
  position: relative;
  float: left;
  color: white;
  text-align: center;
  font-size: 24px;
  line-height: 50px;
`;

const StyledStrike = styled.p`
  width: 30px;
  height: 30px;
  margin: 10px;
  position: relative;
  float: left;
  background-color: yellow;
  border-radius: 50%;
`;

const StyledBall = styled.p`
  width: 30px;
  height: 30px;
  margin: 10px;
  position: relative;
  float: left;
  background-color: green;
  border-radius: 50%;
`;

const StyledOut = styled.p`
  width: 30px;
  height: 30px;
  margin: 10px;
  position: relative;
  float: left;
  background-color: red;
  border-radius: 50%;
`;

interface Props {
  strikeCount: number,
  ballCount: number,
  outCount: number
}

const SBOBoard: React.FunctionComponent<Props> = function({strikeCount, ballCount, outCount}) {
  const strikeElements = [];
  for (let index = 0 ; index < strikeCount ; ++index) {
    strikeElements.push(<StyledStrike key={index} />);
  }

  const ballElement = [];
  for (let index = 0 ; index < ballCount ; ++index) {
    ballElement.push(<StyledBall key={index} />);
  }

  const outElements = [];
  for (let index = 0 ; index < outCount ; ++index) {
    outElements.push(<StyledOut key={index} />);
  }

  return (
    <StyledDiv>
        <StyledStrikeCountArea>
          <StyledSBOTextArea>S</StyledSBOTextArea>
          {strikeElements}
        </StyledStrikeCountArea>
        <StyledStrikeCountArea>
          <StyledSBOTextArea>B</StyledSBOTextArea>
          {ballElement}
        </StyledStrikeCountArea>
        <StyledStrikeCountArea>
          <StyledSBOTextArea>O</StyledSBOTextArea>
          {outElements}
        </StyledStrikeCountArea>
    </StyledDiv>
  )
}

export default SBOBoard;
