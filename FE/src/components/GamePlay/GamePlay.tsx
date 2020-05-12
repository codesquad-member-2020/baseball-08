import React, { useState, useEffect } from 'react';
import styled from 'styled-components'
import StadiumBackground from './publicComponent/StadiumBackground'
import InningBoard from './publicComponent/InningBoard'
import SBOBoard from './publicComponent/SBOBoard';
import PAandNP from './publicComponent/PAandNP';
import Record from './publicComponent/Record';
import fetchRequest from '../../util/fetchRequest'

const StyledDiv = styled.div`
  position: absolute;
  width: 1280px;
  height: 720px;
  margin: 0 auto;
  z-index: 0;
`;

interface Score {
  strike: number,
  ball: number,
  out: number,
  base: number
}

interface GameData {
  away: string,
  home: string,
  awayTotalScore: string,
  homeTotalScore: number,
  user: string,
  inning: number,
  turn: boolean,
  score: Score,
}

function GamePlay() {
  const [gameDetailObj, setGameDetailObj] = useState<any>(undefined);

  useEffect(() => {
    fetchRequest(process.env.REACT_APP_GAME_STATUS, "GET")
    .then((response) => response.json())
    .then((games) => {
      setGameDetailObj(games);
    })
    .catch((error) => {
      alert("주의");
    });
  }, [])

  return (
    <StyledDiv>
      <StadiumBackground></StadiumBackground>
      {gameDetailObj && 
      <>
      <InningBoard
        awayTeamName={gameDetailObj.away}
        awayTeamScore={gameDetailObj.awayTotalScore}
        homeTeamName={gameDetailObj.home}
        homeTeamScore={gameDetailObj.homeTotalScore}
      ></InningBoard>
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
      <Record 
        logs={gameDetailObj.history}
      />
      </>
      }
    </StyledDiv>
  );
}

export default GamePlay;
