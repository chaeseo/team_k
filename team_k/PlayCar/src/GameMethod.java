
public class GameMethod {
	public int MapSize =20;
	public int Map[][] = new int[MapSize][MapSize];
	public int GamePlayerCount = 2;
	public int CurrentPlayerTurn = 1;
	public boolean isSecond = true;
	public boolean isStart = false;
	
	public void ClearMap() {
		for(int i=0; i < MapSize; i++) {
			for(int j =0; j<MapSize; j++) {
				Map[i][j] = 0;
			}
		}
		
	}
	
	public void nextPlayer(int currentplayer) {
		if(!isSecond) {
			isSecond = true;
			return;
		}
		currentplayer ++;
		isSecond = false;
		if(currentplayer > GamePlayerCount) {
			CurrentPlayerTurn =1;
		}else {
			CurrentPlayerTurn = currentplayer;
		}
	}
	
	public boolean EndGameCheck(Word w) {
		
		int currentColor = w.GetColor();
		
		int dir[][] = {{ 1 ,0 },{ -1 , 0 },{ 0 , 1 },{ 0 , -1 },
				{ -1, 1 }, { 1, -1 }, { -1, -1 }, { 1, 1 }};
		
		for(int i=0; i<8; i+=2) {//2씩 증가
			int EndCount = 1;
			int cunX = w.GetX();
			int cunY = w.GetY();
			
			for (int j =0; j<5; j++) {
				cunX += dir[i][1];
				cunY += dir[i][0]; 
				
				if(cunY < 0||cunY >= MapSize||cunX<0||cunX >= MapSize) 
					break;
				if(currentColor != Map[cunY][cunX])
					break;
					
				EndCount ++;
			}
			if(EndCount==6) {
				return true;//6개 오목 성공
			}
		
		}
		return false;
	}
	
	public void setWord(Word w) {
		Map[w.GetY()][w.GetX()] = w.GetColor();
	}
	
	public boolean isWord(int x, int y) {
		if(Map[y][x] != 0 || y<0 || y >= MapSize || x<0 || x>= MapSize) {
			return false;
			}
		
		return true;
			
	}
	
	public int[][] GetMap(){
		return Map;
	}
	
	public int GetCurrentPlayerTurn() {
		return CurrentPlayerTurn;
	}
	
	public void setGameMode(int max) {
		this.GamePlayerCount = max;
	}
	
	public void setStart() {
		Word w = new Word(9,10,GetCurrentPlayerTurn());
		setWord(w);
		nextPlayer(CurrentPlayerTurn);
	}
	
}
