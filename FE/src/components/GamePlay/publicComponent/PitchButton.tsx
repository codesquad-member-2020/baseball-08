import React from 'react';
import styled from 'styled-components'

const StyledPitchButton = styled.button`
  position: absolute;
  background-color: lightgray;
  margin: 0 auto;
  width: 200px;
  top: 535px;
  left: 400px;
  font-size: 30px;
  color: black;
  text-align: center;
  font-weight: bold;
  border-radius: 50px;

  &:hover {
    cursor: pointer;
    background-color: gray;
  }

  &:active {
    background-color: gray;
    color: #cc0000;
  }
`;

interface Props {
  pitchText: string,
  onRequestButtonClick(): void
}

const PitchButton: React.FunctionComponent<Props> = function({pitchText, onRequestButtonClick}) {
  return (
    <StyledPitchButton onClick={onRequestButtonClick}>
      {pitchText}
    </StyledPitchButton>
  );
}

export default PitchButton;