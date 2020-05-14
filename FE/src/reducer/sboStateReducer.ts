interface ISBOStateReducer {
  strike: number,
  ball: number,
  out: number
}

type Action =
 | { type: 'setStrike', strike: number }
 | { type: 'setBall', ball: number }
 | { type: 'setOut', out: number };


function sboStateReducer(state: ISBOStateReducer, action: Action) {
  switch (action.type) {
    case 'setStrike': {
      return {...state, strike: action.strike}
    }
    case 'setBall': {
      return {...state, ball: action.ball}
    }
    case 'setOut': {
      return {...state, out: action.out}
    }
  }
}

export default sboStateReducer;