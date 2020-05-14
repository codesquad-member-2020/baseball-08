import React from 'react';
import styled from 'styled-components'

const StyledP = styled.p`
  position: relative;
  padding-top: 40px;
  padding-bottom: 40px;
  font-size: 64px;
  font-weight: bold;
  text-align: center;
  color: black;
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