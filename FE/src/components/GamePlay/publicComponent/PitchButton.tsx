import React from 'react';
import styled, { keyframes } from 'styled-components'

const PitchButtonAnimation = keyframes `
  0% { background-color: lightgray; }
  100% { background-color: gray; }
`;

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
    color: #cc0000;
    cursor: pointer;
  }

  &:active {
    color: #cc0000;
    background-color: gray;
    animation-duration: 0.2s;
    animation-name: ${PitchButtonAnimation};
  }

  &:focus {
    outline: none;
    box-shadow: none;
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