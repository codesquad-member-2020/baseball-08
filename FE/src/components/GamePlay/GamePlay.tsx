import React, { useState, useEffect } from 'react';
import styled from 'styled-components'
import StadiumBackground from './publicComponent/StadiumBackground'
import ScoreBoard from './publicComponent/ScoreBoard'
import SBOBoard from './publicComponent/SBOBoard';
import PAandNP from './publicComponent/PAandNP';
import Record from './publicComponent/Record';
import Inning from './publicComponent/Inning';
import PitchButton from './publicComponent/PitchButton';
import fetchRequest from '../../util/fetchRequest'
import { withRouter, RouteComponentProps } from 'react-router-dom';
import GameData from '../../data/GameData'

const StyledDiv = styled.div`
  position: absolute;
  width: 1280px;
  height: 720px;
  margin: 0 auto;
  z-index: 0;
`;

const StyledWaitingWrap = styled.div`
  position: absolute;
  width: 1280px;
  height: 720px;
  margin: 0 auto;
  color: white;
  background-color: rgba(0, 0, 0, 0.8);
  z-index: 1;
`;

const StyledWaitingImage = styled.div`
  position: relative;
  width: 540px;
  height: 400px;
  margin: 0 auto;
  margin-top: 125px;
  background-color: black;
  background-image: url("http://dev-angelo.dlinkddns.com/giphy_2.gif");
  background-size: 100% 100%;
  font-size: 24px;
  z-index: 1;
  color: black;
`;

const StyledIntroMovieWrap = styled.div`
  position: absolute;
  width: 720px;
  height: 480px;
  margin-top: 193px;
  margin-left: 374px;
`;

interface Score {
  strike: number,
  ball: number,
  out: number,
  base: number
}

type props = RouteComponentProps;

const GamePlay: React.FunctionComponent<props> = function({history}) {
  const [gameDetailObj, setGameDetailObj] = useState<any>(undefined);
  const [waiting, setWaiting] = useState(true);
  const [isDefence, setIsDefence] = useState(false);

  function requestPitch() {
    setIsDefence(false);
    const url = process.env.REACT_APP_GAME_PITCH;
    const cvtUrl = url?.replace(`{teamId}`, (GameData.getInstance().getTeamId()).toString());

    fetchRequest(cvtUrl, "GET")
    .then((response) => response.json())
    .then((games) => {
      requestCurrentStatus();
    })
    .catch((error) => {
      alert("주의");
    });
  }

  function requestCurrentStatus() {
    const url = process.env.REACT_APP_GAME_STATUS;
    const cvtUrl = url?.replace(`{gameId}`, (GameData.getInstance().getGameId()).toString());

    console.log(cvtUrl);

    fetchRequest(cvtUrl, "GET")
    .then((response) => response.json())
    .then((games) => {
      setGameDetailObj(games);

      if ( (GameData.getInstance().getIsAwayTeam() && games.turn === "초") ||
           (!GameData.getInstance().getIsAwayTeam() && games.turn === "말") ) {
        setTimeout(() => {
          setIsDefence(false);
          requestCurrentStatus();
        }, 1000);
      }
      else {
        setIsDefence(true);
        console.log("체인지");
      }
    })
    .catch((error) => {
      alert("주의");
    });
  }

  useEffect(() => {
    requestCurrentStatus();
    setWaiting(false);
  }, [])

  function onScoreBoardClick() {
    history.push('/gameplay/scoreboard');
  }

  function onRecordClick() {
    history.push('/gameplay/playerlist');
  }

  return (
    <StyledDiv>
      {waiting && <StyledWaitingWrap><StyledWaitingImage>게임을 불러오는중입니다...</StyledWaitingImage></StyledWaitingWrap>}
      <StadiumBackground></StadiumBackground>
      <StyledIntroMovieWrap>
        <video id="test" muted autoPlay loop width={270}>
          <source src="http://dev-angelo.dlinkddns.com/logo.mp4" type="video/mp4" />
        </video>
      </StyledIntroMovieWrap>
      {gameDetailObj && 
      <>
      <ScoreBoard onScoreBoardClick={onScoreBoardClick}
        awayTeamName={gameDetailObj.away}
        awayTeamScore={gameDetailObj.awayTotalScore}
        homeTeamName={gameDetailObj.home}
        homeTeamScore={gameDetailObj.homeTotalScore}
        isAwayTeam={GameData.getInstance().getIsAwayTeam()}
      ></ScoreBoard>
      <SBOBoard
        strikeCount={gameDetailObj.score.strike}
        ballCount={gameDetailObj.score.ball}
        outCount={gameDetailObj.score.out}
      />
      <PAandNP 
        pitcherName={gameDetailObj.pitcher.name}
        pitches={gameDetailObj.pitcher.pitches}
        batterName={gameDetailObj.hitter.name}
        atBat={gameDetailObj.hitter.atBat}
        hit={gameDetailObj.hitter.hit}
      />
      <Record onRecordClick={onRecordClick}
        logs={gameDetailObj.history}
      />
      <Inning
        inningText={gameDetailObj.inning + `회 ` + gameDetailObj.turn}
      />
      {isDefence &&
      <PitchButton
        pitchText="PITCH!"
        onRequestButtonClick={requestPitch}
      />
      }
      </>
      }
    </StyledDiv>
  );
}

export default withRouter(GamePlay);
