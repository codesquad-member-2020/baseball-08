import React from 'react';
import styled, { keyframes } from 'styled-components'

const blinker = keyframes `
  0% {
    opacity: 0;
  }
  50% {
    opacity: 1;
  }
  100% {
    opacity: 0;
  }
`;

const BlinkTextWrap = styled.p`
  position: relative;
  width: 500px;
  background-color: rgba(0, 0, 0, 0.8);
  top: 50%;
  left: 250px;
  transform: translateY(-50);
  font-size: 30px;
  color: white;
  text-align: center;
  animation: ${blinker} 2s infinite;
`

interface Props {
  blinkText: string,
}

const BlinkText: React.FunctionComponent<Props> = function({blinkText}) {
  return (
    <BlinkTextWrap>
      {blinkText}
    </BlinkTextWrap>
  );
}

export default BlinkText;