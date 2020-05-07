import React from 'react';
import styled from 'styled-components'

const StyledP = styled.p`
  position: relative;
  padding-top: 30px;
  padding-bottom: 30px;
  font-size: 36px;
  font-weight: bold;
  text-align: center;
  color: white;
`;

interface Props {
  title: string
}

const GameTitle: React.FunctionComponent<Props> = function({title}) {
  return (
    <>
      <StyledP>{title}</StyledP>
    </>
  );
};

export default GameTitle;