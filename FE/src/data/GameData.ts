class GameData {

  private static _instance: GameData = new GameData();

  private _isAwayTeam: boolean = false;

  private _awayTeamScore: number = 0;
  private _homeTeamScore: number = 0;

  private _onBaseCount: number = 0;

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

  public setAwayTeamScore(awayTeamScore: number): void
  {
      this._awayTeamScore = awayTeamScore;
  }

  public getAwayTeamScore(): number
  {
      return this._awayTeamScore;
  }

  public setHomeTeamScore(homeTeamScore: number): void
  {
      this._homeTeamScore = homeTeamScore;
  }

  public getHomeTeamScore(): number
  {
      return this._homeTeamScore;
  }

  public setOnBaseCount(onBaseCount: number): void
  {
      this._onBaseCount = onBaseCount;
  }

  public getOnBaseCount(): number
  {
      return this._onBaseCount;
  }
}

export default GameData;