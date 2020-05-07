import React from 'react';
import styled, { keyframes } from 'styled-components'
import TeamPlayerList from './publicComponent/TeamPlayerList'

const slidein = keyframes `
  0% { opacity: 0 }
  100% { opacity: 1; }
`;

const StyledDiv = styled.div`
  position: absolute;
  width: 1280px;
  height: 720px;
  z-index: 1;
  background-color: rgba(0, 0, 0, 0.8);
  animation-duration: 0.7s;
  animation-name: ${slidein};
`;

function PlayerList() {
  const teams = [
    {
      "team": "team1",
      "user": "githubId2",
      "players": [
        {
          "name": "player1",
          "atBat": 3,
          "hit": 1,
          "out": 2,
          "average": 0.333
        },
        {
          "name": "player2",
          "atBat": 3,
          "hit": 2,
          "out": 1,
          "average": 0.333
        },
        {
          "name": "player3",
          "atBat": 3,
          "hit": 1,
          "out": 2,
          "average": 0.333
        }
      ],
      "total": {
        "bat": 9,
        "hit": 4,
        "out": 5
      }
    },
    {
      "team": "team2",
      "user": "githubId3",
      "players": [
        {
          "name": "player4",
          "atBat": 2,
          "hit": 0,
          "out": 2,
          "average": 0.333
        },
        {
          "name": "player5",
          "atBat": 2,
          "hit": 1,
          "out": 1,
          "average": 0.333
        },
        {
          "name": "player6",
          "atBat": 2,
          "hit": 1,
          "out": 1,
          "average": 0.333
        }
      ],
      "total": {
        "bat": 6,
        "hit": 2,
        "out": 4
      }
    }
  ];

  return (
    <StyledDiv className="PlayerList">
      <TeamPlayerList playerList={teams[0]}></TeamPlayerList>
      <TeamPlayerList playerList={teams[1]}></TeamPlayerList>
    </StyledDiv>
  );
}

export default PlayerList;