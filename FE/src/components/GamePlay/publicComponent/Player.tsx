import React from 'react';
import styled from 'styled-components'

const FirstRunner = styled.div`
  position: absolute;
  width: 100px;
  height: 60px;
  background-image: url("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSEsyToOQTp-9OzQIXDfbvCSYHfMQuZyY3OvZRDa9wQFI2ud_8q&usqp=CAU");
  background-size: 100% 100%;
  border-right: 2px solid white;
  z-index: 0;
  top: 480px;
  left: 800px;
`;

const SecondRunner = styled.div`
  position: absolute;
  width: 100px;
  height: 60px;
  background-image: url("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSEsyToOQTp-9OzQIXDfbvCSYHfMQuZyY3OvZRDa9wQFI2ud_8q&usqp=CAU");
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
  background-image: url("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSEsyToOQTp-9OzQIXDfbvCSYHfMQuZyY3OvZRDa9wQFI2ud_8q&usqp=CAU");
  background-size: 100% 100%;
  border-right: 2px solid white;
  z-index: 0;
  top: 480px;
  left: 100px;
`;

function Player() {
  return (
    <FirstRunner>
    </FirstRunner>
  );
}

export default Player;