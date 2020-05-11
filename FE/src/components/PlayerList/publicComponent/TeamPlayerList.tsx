import React from 'react';
import styled from 'styled-components'

const TeamPlayerListWrap = styled.div`
  position: relative;
  width: 640px;
  height: 600px;
  top: 50%;
  transform: translateY(-50%);
  color: #fff;
  float: left;
  box-shadow: 0 0 0 2px #fff inset;
`;

const TeamName = styled.h1`
  font-size: 30px;
  text-align: center;
  font-weight: bold;
`;

const RecordArea = styled.div`
  width: 640px;
  height: 30px;
  position: relative;
  color: gray;
  padding-top: 10px;
  padding-bottom: 10px;
  font-size: 24px;
`;

const BatterTitle = styled.p`
  width: 150px;
  position: relative;
  float: left;
  text-align: center;
`;

const SectionTitle = styled.p`
  width: 70px;
  position: relative;
  float: left;
  text-align: center;
`;

const AverageTitle = styled.p`
  width: 140px;
  position: relative;
  float: left;
  text-align: center;
`;

const PlayerArea = styled.div`
  width: 640px;
  height: 30px;
  position: relative;
  color: white;
  padding-top: 10px;
  padding-bottom: 10px;
  font-size: 24px;
`;

const Batter = styled.p`
  width: 150px;
  position: relative;
  float: left;
  text-align: center;
  overflow: hidden;
`;

const Section = styled.p`
  width: 70px;
  position: relative;
  float: left;
  text-align: center;
`;

const Average = styled.p`
  width: 140px;
  position: relative;
  float: left;
  text-align: center;
`;

const StyledUL = styled.ul`
  width: 640px;
  height: 536px;
  color: #fff;
`;

interface Players {
  name: string,
  atBat: number,
  hit: number,
  out: number,
  average: number
}

interface Props {
  teamName: string,
  playerInfo: Array<Players>,
  totalBat: number,
  totalHit: number,
  totalOut: number
}

const TeamPlayerList: React.FunctionComponent<Props> = function({teamName, playerInfo, totalBat, totalHit, totalOut}) {
  return (
    <TeamPlayerListWrap>
      <TeamName>{teamName}</TeamName>
      <RecordArea>
        <BatterTitle>타자</BatterTitle>
        <SectionTitle>타석</SectionTitle>
        <SectionTitle>안타</SectionTitle>
        <SectionTitle>아웃</SectionTitle>
        <AverageTitle>이번 경기</AverageTitle>
        <AverageTitle>시즌 평균</AverageTitle>
      </RecordArea>
      <StyledUL>
        {playerInfo.map((player:any, index:any) => 
        <li key={index}>
          <PlayerArea key={index}>
            <Batter>{player.name}</Batter>
            <Section>{player.atBat}</Section>
            <Section>{player.hit}</Section>
            <Section>{player.out}</Section>
            <Average>{player.atBat > 0 ? (player.hit / player.atBat).toFixed(3) : 0}</Average>
            <Average>{player.average}</Average>
          </PlayerArea>
          </li>
          ) 
        }
      </StyledUL>
    </TeamPlayerListWrap>
  );
}

export default TeamPlayerList;
