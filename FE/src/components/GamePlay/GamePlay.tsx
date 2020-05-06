import React from 'react';
import styled from 'styled-components'

const StyledDiv = styled.div`
  position: absolute;
  width: 1280px;
  height: 720px;
  margin: 0 auto;
  background-image: url("https://s3-us-west-2.amazonaws.com/mfbrowndesign.com/img/baseball+diamond.jpg");
  background-size: 100% 100%;
  z-index: 0;
`;

function GamePlay() {
  return (
    <StyledDiv>
      GamePlay
    </StyledDiv>
  );
}

export default GamePlay;
