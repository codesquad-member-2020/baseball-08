import React, { useEffect } from 'react';
import styled from 'styled-components'
import Cookies from 'universal-cookie';
import { RouteComponentProps, withRouter } from 'react-router-dom';

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
  margin-top: 126px;
  margin-left: 340px;
`;

const StyledStartGameButton = styled.button`
  position: relative;
  width: 240px;
  height: 60px;
  border: 1px solid black;
  border-radius: 20px;
  font-size: 28px;
  margin-top: 570px;
  margin-left: 520px;

  &:hover {
    color: #cc0000;
    cursor: pointer;
  }
`;

type props = RouteComponentProps;

const Intro: React.FC<props> = ({history}) => {
// function Intro() {
  useEffect(() => {
    const cookies = new Cookies();
    console.log(cookies.get('userId'));
  })

  function onClickGameStartButton() {
    history.push('/gameselect');
  }

  return (
    <StyledDiv>
      <StyledIntroMovie>
        <audio autoPlay={true} src="http://dev-angelo.dlinkddns.com/movie480.mp3"></audio>
        <video id="test" muted autoPlay width={600}>
          <source src="http://dev-angelo.dlinkddns.com/baseball_08.mp4" type="video/mp4" />
        </video>
      </StyledIntroMovie>
      <StyledStartGameButton onClick={onClickGameStartButton}>게임하기</StyledStartGameButton>
    </StyledDiv>
  );
}

export default withRouter(Intro);