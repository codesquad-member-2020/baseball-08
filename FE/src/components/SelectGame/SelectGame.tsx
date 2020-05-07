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
  const games = [
    {
      game: 1,
      away: "team1",
      home: "team2",
      away_user: null,
      home_user: null,
    },
    {
      game: 2,
      away: "team3",
      home: "team4",
      away_user: "githubId2",
      home_user: "githubId3",
    },
    {
      game: 3,
      away: "team5",
      home: "team6",
      away_user: null,
      home_user: "githubId1",
    },
  ];

  return (
    <StyledDiv>
      <GameTitle title="Baseball Game Service"></GameTitle>
      <SelectGamePhrase title="참가할 게임을 선택하세요"></SelectGamePhrase>
      {games.map((game:any, index:number) => 
        <Versus index={game.game} awayTeamName={game.away} homeTeamName={game.home}></Versus>
        ) 
      }
    </StyledDiv>
  );
}

export default SelectGame;