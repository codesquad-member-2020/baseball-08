import React from 'react';
import styled from 'styled-components'

const StyledRecord = styled.div`
  width: 280px;
  height: 580px;
  left: 1000px;
  top: 140px;
  position: absolute;
  color: white;
  background-color: black;
  overflow: hidden;
  border: 1px solid white;
  font-size: 18px;

  &:hover {
    cursor: pointer;
  }
`;

const StyledRecordWrap = styled.div`
  width: 280px;
  position: relative;
  color: black;
  background-color: white;
  box-shadow: 0 0 0 3px gray inset; 
  border-radius: 15px;
  margin-bottom: 5px;
`;

const StyledPlayerInfo = styled.div`
  width: 280px;
  position: relative;
  color: #cc0000;
  text-align: center;
  padding-top: 5px;
  padding-bottom: 5px;
`;

const StyledHistoryWrap = styled.div`
  width: 280px;
  position: relative;
`;

const StyledHistory = styled.div`
  width: 280px;
  position: relative;
  text-align: center;
`;


interface History {
  name: string,
  lineUp: number,
  hitLog: Array<string>,
}

interface Props {
  logs: Array<History>,
  onRecordClick(): void
}

const Record: React.FunctionComponent<Props> = function({logs, onRecordClick}) {
  let strike = 0;
  let ball = 0;

  const convertSBOtoString = (abc: string) => {
    switch (abc) {
      case 'S': {
        strike++;
        return '스트라이크';
      }
      case 'B': {
        ball++;
        return '볼';
      }
      case 'O': {
        return '아웃!';
      }
      case 'H': {
        return '안타!';
      }
    }
  }

  return (
    <StyledRecord onClick={onRecordClick}>
      {logs.map((log: History, index: number) => (
        <StyledRecordWrap key={index}>
          <StyledPlayerInfo>{log.lineUp}타자 {log.name}</StyledPlayerInfo>  
          <StyledHistoryWrap>
            {log.hitLog.map((detail: string, index: number) => (
              <StyledHistory key={index}>{convertSBOtoString(detail)}</StyledHistory>
             ))}
          </StyledHistoryWrap>
        </StyledRecordWrap>
      ))}
    </StyledRecord>
  );
}

export default Record;