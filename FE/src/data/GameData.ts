class GameData {

  private static _instance: GameData = new GameData();

  private _isAwayTeam: boolean = false;
  private _teamId: number = -1;
  private _gameId: number = -1;
  private _teamName: string = '';

  constructor() {
      if(GameData._instance){
          throw new Error("Error: Instantiation failed: Use SingletonClass.getInstance() instead of new.");
      }
      GameData._instance = this;
  }

  public static getInstance():GameData
  {
      return GameData._instance;
  }

  public setIsAwayTeam(isAwayTeam: boolean): void
  {
      this._isAwayTeam = isAwayTeam;
  }

  public getIsAwayTeam(): boolean
  {
      return this._isAwayTeam;
  }

  public setTeamId(teamId: number): void
  {
      this._teamId = teamId;
  }

  public getTeamId(): number
  {
      return this._teamId;
  }

  public setGameId(gameId: number): void
  {
      this._gameId = gameId;
  }

  public getGameId(): number
  {
      return this._gameId;
  }

  public setTeamName(teamName: string): void
  {
    this._teamName = teamName;
  }

  public getTeamName(): string
  {
      return this._teamName;
  }
}

export default GameData;