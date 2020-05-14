import React, { useState, useEffect } from 'react';
import styled, { keyframes } from 'styled-components'
import TeamPlayerList from './publicComponent/TeamPlayerList'
import fetchRequest from '../../util/fetchRequest'
import GameData from '../../data/GameData'
import getCookieData from '../../util/getCookieData'

const slidein = keyframes `
  0% { opacity: 0 }
  100% { opacity: 1; }
`;

const StyledDiv = styled.div`
  position: absolute;
  width: 1280px;
  height: 720px;
  z-index: 1;
  background-color: rgba(0, 0, 0, 0.9);
  animation-duration: 0.7s;
  animation-name: ${slidein};
`;

function PlayerList() {
  const [playerList, setPlayerList] = useState<any>(undefined);

  useEffect(() => {
    const url = process.env.REACT_APP_GAME_PLAYER;
    const cvtUrl = url?.replace(`{gameId}`, (GameData.getInstance().getGameId()).toString());

    fetchRequest(cvtUrl, "GET", getCookieData('userId'))
    .then((response) => response.json())
    .then((playerList) => {
      setPlayerList(playerList);
    })
    .catch((error) => {
      alert("주의");
    });
  }, [])
  
  return (
    <StyledDiv className="PlayerList">
      {playerList &&
        playerList.map((teamInfo: any, index: any) => (
          <TeamPlayerList key={index}
            teamName={teamInfo.team}
            playerInfo={teamInfo.players}
            totalBat={teamInfo.total.bat}
            totalHit={teamInfo.total.hit}
            totalOut={teamInfo.total.out}
          ></TeamPlayerList>
        ))}
    </StyledDiv>
  );
}

export default PlayerList;