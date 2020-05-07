import React from 'react';
import styled from 'styled-components'

const StyledDiv = styled.p`
  float: right;
  color: red;
  font-size: 30px;
  font-weight: bold;
  padding-left: 5px;
  padding-right: 129px;
`;

function TotalScore(props:any) {
  return (
    <StyledDiv>
      {props.totalScore}
    </StyledDiv>
  );
}

export default TotalScore;
