import React from 'react';
import styled from 'styled-components'

const StyledDiv = styled.div`
  position: relative;
  width: 1280px;
  height: 720px;
  margin: 0 auto;
  background-color: black;
  background-image: url("http://dev-angelo.dlinkddns.com/select_game.jpg");
  background-size: 100% 100%;
`;

const StyledIntroMovie = styled.div`
  position: absolute;
  width: 720px;
  height: 480px;
  /* margin: 0 auto; */
  margin-top: 195px;
  margin-left: 395px;
`;

function SelectGame() {
  return (
    <StyledDiv>
      <StyledIntroMovie>
        <video id="test" muted autoPlay width={480}>
          <source src="http://dev-angelo.dlinkddns.com/baseball_08.mp4" type="video/mp4" />
        </video>
      </StyledIntroMovie>
    </StyledDiv>
  );
}

export default SelectGame;