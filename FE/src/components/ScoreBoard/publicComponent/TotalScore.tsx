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

interface Props {
  totalScore: number
}

const TotalScore: React.FunctionComponent<Props> = function({totalScore}) {
  return (
    <StyledDiv>
      {totalScore}
    </StyledDiv>
  );
}

export default TotalScore;
