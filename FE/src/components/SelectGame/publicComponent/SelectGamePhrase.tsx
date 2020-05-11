import React from 'react';
import styled from 'styled-components'

const StyledP = styled.p`
  position: relative;
  padding-top: 10px;
  padding-bottom: 40px;
  font-size: 24px;
  text-align: center;
  color: white;
`;

interface Props {
  title: string
}

const SelectGamePhrase: React.FunctionComponent<Props> = function({title}) {
  return (
    <>
      <StyledP>{title}</StyledP>
    </>
  );
};

export default SelectGamePhrase;