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

function SelectGamePhrase(props:any) {
  return (
    <>
      <StyledP>{props.title}</StyledP>
    </>
  );
};

export default SelectGamePhrase;