

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;



public class Board extends JPanel implements MouseListener, MouseMotionListener{
	ArrayList<Point> memory = new ArrayList<Point>();
	ArrayList<Color> colorMemory = new ArrayList<Color>();
	
	WinPanel winPanel;
	OnlineWinPanel onlinewinPanel;
	
	Point p = new Point(0, 0);
	static Color color = Color.red;
	
	Player p1, p2, p3;
	
	int[][] board = new int[19][19];
	int row, column;
	static int p1Count, p2Count, p3Count;
	int win;
	
	boolean check;
 	public static boolean is3 = false;
 	
 	public boolean isOnline = false;
 	public boolean isMyturn = false;
 	public boolean isBlue = false;
 	public int myCount;
 	
 	PrintWriter writer;
 	
	
	Board() {
		setSize(750, 750);
		setBackground(new Color(255, 207, 165));
		
		board[9][9] = 1;
		colorMemory.add(Color.blue);
		memory.add(new Point(357, 357));
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for(int i = 24; i < this.getWidth(); i+= 39) {
			g.drawLine(i, 24, i, 726);
			g.drawLine(24, i, 726,i);
		}
		
		for(int i = 138; i < this.getWidth(); i += 234) {
			g.fillOval(i, 138, 6, 6);
			g.fillOval(i, 372, 6, 6);
			g.fillOval(i, 606, 6, 6);
		}
		
		for (int i = 0; i < memory.size(); i++) {
			
				g.setColor(colorMemory.get(i));
				g.fillOval(memory.get(i).x, memory.get(i).y, 36, 36);
			}
		
		if(14 <= p.x % 39 && p.x % 39 <= 34 && 14 <= p.y % 39 && p.y % 39 <= 34) {
			column = p.x / 39;
			row = p.y / 39;
			
			if(board[column][row] != 0) {
				check = false;
			}
			else {
				
				
				g.setColor(color);
				g.fillOval(39*column + 6, 39*row + 6, 36, 36);
				
				if(check) {
					
					colorSet();
					memory.add(new Point(39*column + 6, 39*row +6));
					colorMemory.add(color);
					
					if(checkFinish()) {
						if(isOnline) {
							if(win ==1) {
								if(isBlue) {
									add(onlinewinPanel= new OnlineWinPanel(true));
									writer.println("[lose]");
								}else {
									add(onlinewinPanel= new OnlineWinPanel(false));
								}
							}
							
							if(win ==2){
								if(isBlue) {
									add(onlinewinPanel= new OnlineWinPanel(false));
								}else {
									add(onlinewinPanel= new OnlineWinPanel(true));
									writer.println("[lose]");
								}
							}
							return;
						}
						if(win == 1) {
							add(winPanel= new WinPanel(p1));
							if(!is3) {
								Screen3.timer1.stop();
								Screen3.timer2.stop();
								Player1Panel.second = 30;
								Player2Panel.second = 30;
							}
							else {
								Screen3_2.timer1.stop();
								Screen3_2.timer2.stop();
								Player1Panel.second = 30;
								Player2Panel.second = 30;
							}
						}
						if(win == 2) {
							add(winPanel= new WinPanel(p2));
							if(!is3) {
								Screen3.timer1.stop();
								Screen3.timer2.stop();
								Player1Panel.second = 30;
								Player2Panel.second = 30;
							}
							else {
								Screen3_2.timer1.stop();
								Screen3_2.timer2.stop();
								Player1Panel.second = 30;
								Player2Panel.second = 30;
							}
						}
						if(win == 3) {
							add(winPanel= new WinPanel(p3));
							Screen3_2.timer1.stop();
							Screen3_2.timer2.stop();
							Player1Panel.second = 30;
							Player2Panel.second = 30;
						}
					}
					check = false;
				}
			}
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(isOnline && isMyturn) {
			p = e.getPoint();
			check = true;
			repaint();
		}else if(!isOnline ) {
			p = e.getPoint();
			check = true;
			repaint();
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(isOnline && isMyturn) {
			// TODO Auto-generated method stub
			p = e.getPoint();
			repaint();
		}else if(!isOnline) {
			p = e.getPoint();
			repaint();
		}
	}
	
	void colorSet() {
		if(isOnline) {
			if(isMyturn && isBlue) {
				myCount ++;
				board[column][row] = 1;
				writer.println("[SetStone]" + column + " " + row);
				
			}else if (isMyturn && !isBlue) {
				myCount ++;
				board[column][row] = 2;
				writer.println("[SetStone]" + column + " " + row);
			}
			if(myCount ==2) {
				myCount =0;
				isMyturn = false;
				writer.println("[NextTurn]");
			}
				
			return;
		}
		if(color == Color.red) {
			p2Count++;
			board[column][row] = 2;
		}
		else if(color == Color.blue) {
			p1Count++;
			board[column][row] = 1;
		}
		else if(color == Color.yellow) {
			p3Count++;
			board[column][row] = 3;
		}
		
		if(!is3) {
			if(p2Count == 2) {
				color = Color.blue;
				p2Count = 0;
				Screen3.player1.repaint();
				Screen3.player2.repaint();
			}
			
			if(p1Count == 2) {
				color = Color.red;
				p1Count = 0;
				Screen3.player1.repaint();
				Screen3.player2.repaint();
			}
		}else {
			if(p2Count == 2) {
				color = Color.yellow;
				p2Count = 0;
				Screen3_2.player1.repaint();
				Screen3_2.player2.repaint();
				Screen3_2.player3.repaint();
			}
			
			if(p1Count == 2) {
				color = Color.red;
				p1Count = 0;
				Screen3_2.player1.repaint();
				Screen3_2.player2.repaint();
				Screen3_2.player3.repaint();
			}
			
			if(p3Count == 2) {
				color = Color.blue;
				p3Count = 0;
				Screen3_2.player1.repaint();
				Screen3_2.player2.repaint();
				Screen3_2.player3.repaint();
			}
		}
	}

	boolean checkFinish() {
		win = board[column][row];
		int count = 1;
		
		int i = column + 1;
		while (i < 19 && board[i][row] == win) {
			count++;
			i++;
		}
		
		i = column - 1;
		while (i > 0 && board[i][row] == win) {
			count++;
			i--;
		}
		
		if(count >= 6)
			return true;
		else
			count = 1;
		
		i = row + 1;
		while (i < 19 && board[column][i] == win) {
			count++;
			i++;
		}
		
		i = row - 1;
		while (i > 0 && board[column][i] == win) {
			count++;
			i--;
		}
		
		if(count >= 6)
			return true;
		else
			count = 1;
		
		i = 1;
		while (column + i < 19 && row + i < 19 && board[column + i][row + i] == win) {
			count++;
			i++;
		}
		
		i = 1;
		while (column - i > 0 && row - i > 0 && board[column - i][row - i] == win) {
			count++;
			i++;
		}
		
		if(count >= 6)
			return true;
		else
			count = 1;
		
		i = 1;
		while (column + i < 19 && row - i > 0 && board[column + i][row - i] == win) {
			count++;
			i++;
		}
		
		i = 1;
		while (column - i > 0 && row + i < 19 && board[column - i][row + i] == win) {
			count++;
			i++;
		}
		
		if(count >= 6)
			return true;
		else
			return false;
	}
}