import React, { useEffect, useState } from 'react';
import styled from 'styled-components'
import GameTitle from './publicComponent/GameTitle'
import SelectGamePhrase from './publicComponent/SelectGamePhrase'
import Versus from './publicComponent/Versus'
import fetchRequest from '../../util/fetchRequest'
import Confetti from 'react-confetti'

const StyledDiv = styled.div`
  position: relative;
  width: 1280px;
  height: 720px;
  margin: 0 auto;
  background-color: black;
  background-image: url("http://dev-angelo.dlinkddns.com/select_game.jpg");
  background-size: 100% 100%;
`;

function SelectGame() {
  const [games, setGames] = useState([]);

  useEffect(() => {
    fetchRequest(process.env.REACT_APP_GAME_LIST, "GET")
    .then((response) => response.json())
    .then((games) => {
      setGames(games);
    })
    .catch((error) => {
      alert("주의");
    });
  }, [])


  return (
    <>
    <Confetti
      width={1280}
      height={720}
      opacity={0.5}
      numberOfPieces={30}
    ></Confetti>
    <StyledDiv>
      <GameTitle title="Baseball Game Service"></GameTitle>
      <SelectGamePhrase title="참가할 게임을 선택하세요"></SelectGamePhrase>
      {games !== undefined && games.map((game:any, index:number) => 
        <Versus key={index} index={game.game} awayTeamName={game.away} homeTeamName={game.home}></Versus>
        ) 
      }
    </StyledDiv>
    </>
  );
}

export default SelectGame;