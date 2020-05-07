import React from 'react';
import styled from 'styled-components'

const StyledDiv = styled.div`
  position: absolute;
  width: 1280px;
  height: 720px;
  z-index: 1;
  background-color: rgba(155, 155, 155, 0.5);
`;

function PlayerList() {
  return (
    <StyledDiv className="PlayerList">
      PlayerList
    </StyledDiv>
  );
}

export default PlayerList;