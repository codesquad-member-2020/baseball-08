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
`;

const StyledRecordWrap = styled.div`
  width: 280px;
  position: relative;
  color: white;
  background-color: black;
`;

const StyledPlayerInfo = styled.div`
  width: 280px;
  position: relative;
  color: #cc0000;
  background-color: black;
  text-align: center;
`;

const StyledHistoryWrap = styled.div`
  width: 280px;
  position: relative;
  color: white;
  background-color: black;
`;

const StyledHistory = styled.div`
  width: 280px;
  position: relative;
  color: white;
  background-color: black;
  text-align: center;
`;


interface History {
  name: string,
  lineUp: number,
  hitLog: Array<string>,
}

interface Props {
  logs: Array<History>,
}

const Record: React.FunctionComponent<Props> = function({logs}) {
// function Record() {
  console.log(logs);

  const convertSBOtoString = (abc: string) => {
    switch (abc) {
      case 'S': {
        return '스트라이크';
      }
      case 'B': {
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
    <StyledRecord>
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