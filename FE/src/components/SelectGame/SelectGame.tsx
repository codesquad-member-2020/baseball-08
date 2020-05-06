import React from 'react';
import styled from 'styled-components'

const StyledDiv = styled.div`
  position: relative;
  width: 1280px;
  height: 720px;
  margin: 0 auto;
  background-color: black;
`;

function SelectGame() {
  return (
    <StyledDiv>
      {/* The location will show the match information through the data received from the server. */}
    </StyledDiv>
  );
}

export default SelectGame;