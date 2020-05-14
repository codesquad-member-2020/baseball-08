import React from 'react';
import styled from 'styled-components'

const StyledDiv = styled.div`
  position: absolute;
  top: 140px;
  width: 1000px;
  height: 580px;
  background-image: url("http://dev-angelo.dlinkddns.com/baseball_bg.jpg");
  background-size: 100% 100%;
  border-right: 2px solid white;
  z-index: 0;
`;

function StadiumBackground() {
  return (
    <StyledDiv>
    </StyledDiv>
  );
}

export default StadiumBackground;
