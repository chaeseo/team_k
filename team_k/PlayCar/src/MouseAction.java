import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

public class MouseAction extends MouseAdapter{
	public GameMethod GM;
	public MapSize MS;
	public ShowMap SM;
	public RunGame RG;
	
	public MouseAction(GameMethod gm, MapSize ms, ShowMap sm, RunGame rg) {
		this.GM = gm;
		this.MS = ms;
		this.SM = sm;
		this.RG = rg;
	}
	
	@Override
	public void mousePressed(MouseEvent ME) {
		if(!GM.isStart)
			return;
		int x = (int)Math.round(ME.getX()/(double) MS.getCell())-1;
		int y = (int)Math.round(ME.getY()/(double) MS.getCell())-2;
		
		if(GM.isWord(x, y)==false) {
			return;
		}
		Word w = new Word(x,y,GM.GetCurrentPlayerTurn());
		GM.setWord(w);
		GM.nextPlayer(GM.GetCurrentPlayerTurn());
		SM.repaint();
		if(GM.EndGameCheck(w)==true) {
			String text = "";
			if(w.GetColor()==1) {
				text="ÆÄ¶õµ¹ ½Â¸®";
			}else if(w.GetColor()==2) {
				text = "»¡°£µ¹ ½Â¸®";
			}else if(w.GetColor()==3){
				text = "³ë¶õµ¹ ½Â¸®";
			}
			showWin(text);
			GM.ClearMap();
			SM.repaint();
		}
	}
	public void showWin(String text) {
		JOptionPane.showMessageDialog(RG, text,"",JOptionPane.INFORMATION_MESSAGE);
	}

}
