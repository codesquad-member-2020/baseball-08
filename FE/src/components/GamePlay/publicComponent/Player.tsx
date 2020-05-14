import React from 'react';
import styled from 'styled-components'

const FirstRunner = styled.div`
  position: absolute;
  width: 100px;
  height: 60px;
  background-image: url("http://dev-angelo.dlinkddns.com/runner_1.png");
  background-size: 100% 100%;
  z-index: 0;
  top: 480px;
  left: 800px;
`;

const SecondRunner = styled.div`
  position: absolute;
  width: 100px;
  height: 60px;
  background-image: url("http://dev-angelo.dlinkddns.com/runner_23.png");
  background-size: 100% 100%;
  border-right: 2px solid white;
  z-index: 0;
  top: 450px;
  left: 450px;
`;


const ThirdRunner = styled.div`
  position: absolute;
  width: 100px;
  height: 60px;
  background-image: url("http://dev-angelo.dlinkddns.com/runner_23.png");
  background-size: 100% 100%;
  border-right: 2px solid white;
  z-index: 0;
  top: 480px;
  left: 100px;
`;

interface IPlayer {
  baseIndex: number
}

const Player: React.FunctionComponent<IPlayer> = function({baseIndex}) {
  return (
    <FirstRunner></FirstRunner>
  );
}

export default Player;