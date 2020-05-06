import React from 'react';
import styled from 'styled-components'
import GameTitle from './publicComponent/GameTitle'
import SelectGamePhrase from './publicComponent/SelectGamePhrase'
import Versus from './publicComponent/Versus'

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
      <GameTitle title="Baseball Game Service"></GameTitle>
      <SelectGamePhrase title="참가할 게임을 선택하세요"></SelectGamePhrase>
      {/* The location will show the match information through the data received from the server. */}
    </StyledDiv>
  );
}

export default SelectGame;