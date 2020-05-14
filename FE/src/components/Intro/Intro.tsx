import React, { useEffect } from 'react';
import styled, { keyframes } from 'styled-components'
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
  margin-top: 126px;
  margin-left: 340px;
`;

const IntroButtonAnimation = keyframes `
  0% { background-color: lightgray; }
  100% { background-color: gray; }
`;

const IntroButton = styled.button`
  position: relative;
  display: block;
  width: 240px;
  height: 60px;
  border: 3px solid black;
  border-radius: 20px;
  font-size: 28px;
  top: 510px;
  left: 520px;
  margin-bottom: 20px;

  &:hover {
    color: #cc0000;
    cursor: pointer;
  }

  &:active {
    color: #cc0000;
    background-color: gray;
    animation-duration: 0.1s;
    animation-name: ${IntroButtonAnimation};
  }

  &:focus {
    outline: none;
    box-shadow: none;
  }
`;

type props = RouteComponentProps;

const Intro: React.FC<props> = ({history}) => {
  useEffect(() => {
    const cookies = new Cookies();
    console.log(cookies.get('userId'));
  })

  function onClickGameStartButton() {
    history.push('/gameselect');
  }

  function onClickLoginButton() {
    const cookies = new Cookies();
    const userId: string = cookies.get('userId');

    if (!userId) {
      alert("로그인이 필요한 서비스입니다.")
      
      const url: string | undefined= process.env.REACT_APP_OAUTH

      if (url !== undefined) {
        const cvtUrl:string = url;
        window.location.href = cvtUrl;
      }
    }
  }

  return (
    <StyledDiv>
      <StyledIntroMovie>
        <video id="test" muted autoPlay loop width={600}>
          <source src="http://dev-angelo.dlinkddns.com/baseball_08.mp4" type="video/mp4" />
        </video>
      </StyledIntroMovie>
      <IntroButton onClick={onClickLoginButton}>로그인</IntroButton>
      <IntroButton onClick={onClickGameStartButton}>게임하기</IntroButton>
    </StyledDiv>
  );
}

export default withRouter(Intro);