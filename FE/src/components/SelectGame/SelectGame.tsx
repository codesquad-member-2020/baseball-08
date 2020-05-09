import React, { useEffect, useState } from 'react';
import styled from 'styled-components'
import GameTitle from './publicComponent/GameTitle'
import SelectGamePhrase from './publicComponent/SelectGamePhrase'
import Versus from './publicComponent/Versus'
import fetchRequest from '../../util/fetchRequest'

const StyledDiv = styled.div`
  position: relative;
  width: 1280px;
  height: 720px;
  margin: 0 auto;
  background-color: black;
`;

function SelectGame() {
  const [games, setGames] = useState([]);

  useEffect(() => {
    fetchRequest("https://dev-angelo.dlinkddns.com:8100/game", "GET")
    .then((response) => response.json())
    .then((games) => {
      setGames(games);
    })
    .catch((error) => {
      alert("주의");
    });
  }, [])


  return (
    <StyledDiv>
      <GameTitle title="Baseball Game Service"></GameTitle>
      <SelectGamePhrase title="참가할 게임을 선택하세요"></SelectGamePhrase>
      {games !== undefined && games.map((game:any, index:number) => 
        <Versus index={game.game} awayTeamName={game.away} homeTeamName={game.home}></Versus>
        ) 
      }
    </StyledDiv>
  );
}

export default SelectGame;