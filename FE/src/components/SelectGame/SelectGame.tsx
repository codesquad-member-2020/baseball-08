import React, { useEffect, useState } from 'react';
import styled from 'styled-components'
import GameTitle from './publicComponent/GameTitle'
import SelectGamePhrase from './publicComponent/SelectGamePhrase'
import Versus from './publicComponent/Versus'
import fetchRequest from '../../util/fetchRequest'
import Confetti from 'react-confetti'
import GameData from '../../data/GameData'
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
  width: 720px;
  height: 480px;
  margin: 0 auto;
  margin-top: 125px;
  color: white;
  background-color: black;
  background-image: url("http://dev-angelo.dlinkddns.com/UMH_L_BASEBALL.gif");
  background-size: 100% 100%;
  font-size: 24px;
  z-index: 1;
`;

type props = RouteComponentProps;

const SelectGame: React.FC<props> = ({history}) => {
  const [waiting, setWaiting] = useState(false);
  const [games, setGames] = useState([]);

  useEffect(() => {
    fetchRequest(process.env.REACT_APP_GAME_LIST, "GET")
    .then((response) => response.json())
    .then((games) => {
      setGames(games);
    })
    .catch((error) => {
      alert("잘못된 응답입니다. (requestGameList");
    });
  }, [])

  function requestGameAvailable(index: number, teamName: string, isAwayTeam: boolean) {
    const url = process.env.REACT_APP_GAME_AVAILABLE;
    const cvtUrl = url?.replace(`{gameId}`, `${index}`).replace(`{teamName}`, teamName);

    fetchRequest(cvtUrl, "GET")
    .then((response) => response.json())
    .then((result) => {
      if (result.available) {
        GameData.getInstance().setIsAwayTeam(isAwayTeam);
        history.push('/gameplay')
      }
      else {
        setTimeout(() => {
          requestGameAvailable(index, teamName, isAwayTeam);
        }, 1000);
      }
    })
    .catch((error) => {
      alert("잘못된 응답입니다. (requestGameAvailable)");
    });
  }

  function onTeamClick(index: number, teamName: string, isAwayTeam: boolean) {
    const url = process.env.REACT_APP_GAME_SELECT;
    const cvtUrl = url?.replace(`{gameId}`, `${index}`).replace(`{teamName}`, teamName);

    fetchRequest(cvtUrl, "GET")
    .then((response) => response.json())
    .then((result) => {
      if (result.available) {
        setWaiting(true);
        requestGameAvailable(index, teamName, isAwayTeam);
      }
      else {
        alert("다른사람에 의해 선택된 팀입니다.");
      }
    })
    .catch((error) => {
      alert("잘못된 응답입니다. (onTeamClick)");
    });
  }

  return (
    <>
    {waiting && <StyledWaitingWrap><StyledWaitingImage>Now Loading...</StyledWaitingImage></StyledWaitingWrap>}
    {waiting !== true && <Confetti
      width={1280}
      height={720}
      opacity={0.5}
      numberOfPieces={30}
    ></Confetti>}
    <StyledDiv>
      <GameTitle title="Baseball Game Service"></GameTitle>
      <SelectGamePhrase title="참가할 게임을 선택하세요"></SelectGamePhrase>
      {games !== undefined && games.map((game:any, index:number) => 
        <Versus key={index} index={game.game} awayTeamName={game.away} homeTeamName={game.home} 
                awayTeamAvailable={game.away_user === null} homeTeamAvailable={game.home_user === null}
                onTeamClick={onTeamClick}>
        </Versus>
        ) 
      }
    </StyledDiv>
    </>
  );
}

export default withRouter(SelectGame);