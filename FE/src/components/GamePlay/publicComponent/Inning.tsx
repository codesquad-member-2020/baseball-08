import React from 'react';
import styled from 'styled-components'

const InningText = styled.p`
  position: absolute;
  background-color: rgba(0, 0, 0, 0.8);
  top: 150px;
  left: 860px;
  font-size: 30px;
  color: white;
  text-align: center;
`;

interface Props {
  inningText: string,
}

const Inning: React.FunctionComponent<Props> = function({inningText}) {
  return (
    <InningText>
      {inningText}
    </InningText>
  );
}

export default Inning;