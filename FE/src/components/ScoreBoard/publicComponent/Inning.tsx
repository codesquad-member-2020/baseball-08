import React from 'react';
import styled from 'styled-components'

const StyledDiv = styled.div`
  position: relative;
  width: 700px;
  color: white;
  font-size: 24px;
  padding: 10px;
`;

const InningUL = styled.ul`
  width: 600px;
  margin-left: 300px;
  height: 46px;
  position: relative;
  color: white;
`;

const InningText = styled.li`
  float: left;
  color: white;
  font-size: 30px;
  font-weight: bold;
  padding-left: 10px;
  padding-right: 10px;
  border-bottom: 2px solid white;
`;

function Inning() {
  return (
    <StyledDiv className="ScoreBoard">
      <InningUL>
        <InningText>1</InningText>
        <InningText>2</InningText>
        <InningText>3</InningText>
        <InningText>4</InningText>
        <InningText>5</InningText>
        <InningText>6</InningText>
        <InningText>7</InningText>
        <InningText>8</InningText>
        <InningText>9</InningText>
        <InningText>10</InningText>
        <InningText>11</InningText>
        <InningText>12</InningText>
        <InningText>R</InningText>
      </InningUL>
    </StyledDiv>
  );
}

export default Inning;
