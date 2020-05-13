class GameData {

  private static _instance: GameData = new GameData();

  private _isAwayTeam: boolean = false;
  private _teamId: number = -1;

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
}

export default GameData;