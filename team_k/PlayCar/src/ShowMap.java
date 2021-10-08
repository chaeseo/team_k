import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class ShowMap extends JPanel{
	public MapSize MS;
	public GameMethod GM;
	public final int StoneSize= 28;
	
	public ShowMap(MapSize ms, GameMethod gm) {
		setBackground(new Color(206,167,61));
		MS =ms;
		setLayout(null);
		this.GM = gm;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		board(g);
		drawStone(g);
	}
	
	public void board(Graphics g) {
		for(int i =1; i<=MS.getSize(); i++) {
			g.drawLine(MS.getCell(), i * MS.getCell(),
					MS.getCell()*MS.getSize(), i * MS.CELL);
			g.drawLine(i * MS.CELL , MS.getCell(), i * MS.getCell(), MS.getCell() * MS.getSize());
		}
	}
	
	public void drawStone(Graphics g) {
			for(int y = 0; y<MS.getSize(); y++) {
				for(int x = 0; x<MS.getSize(); x++) {
					if(GM.GetMap()[y][x]==1)
						drawBlue(g, x, y);
					if(GM.GetMap()[y][x]==2)
						drawRed(g, x, y);
					if(GM.GetMap()[y][x]==3)
						drawYellow(g, x, y);
				}
			}
	}
	
	public void drawBlue(Graphics g,int x, int y) {
		g.setColor(Color.BLUE);
		g.fillOval(x * MS.getCell()+15, y*MS.getCell() -15, StoneSize, StoneSize);
	}
	
	public void drawRed(Graphics g,int x, int y) {
		g.setColor(Color.RED);
		g.fillOval(x * MS.getCell()+15, y*MS.getCell() -15, StoneSize, StoneSize);
	}
	
	public void drawYellow(Graphics g,int x, int y) {
		g.setColor(Color.YELLOW);
		g.fillOval(x * MS.getCell()+15, y*MS.getCell() -15, StoneSize, StoneSize);
	}
	
}
