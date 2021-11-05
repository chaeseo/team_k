

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.net.ServerSocket;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main extends JFrame implements ActionListener{
	Screen1 screen1 = new Screen1();
	Screen2 screen2 = new Screen2();
	Screen2_2 screen2_2 = new Screen2_2();
	Screen2_3 screen2_3 = new Screen2_3();
	Screen3 screen3 = new Screen3();
	Screen3_2 screen3_2 = new Screen3_2();
	Screen3_3 screen3_3 = new Screen3_3();
	Screen3_4 screen3_4 = new Screen3_4();
	Screen4 screen4 = new Screen4();
	
	OnlineWinPanel onlinewinPanel;
	public boolean isOnline = false;
	public boolean isServer = false;
	public boolean isClient = false;

	
	

	Main() {
		setTitle("오노육목");
		setSize(1440, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		screen1.start.addActionListener(this);
		screen1.start_2.addActionListener(this);
		screen1.start_3.addActionListener(this);
		screen2_3.CreateRoom.addActionListener(this);
		screen2_3.EnterRoom.addActionListener(this);

		
		screen2.start.addActionListener(this);
		screen2_2.start.addActionListener(this);
		screen3.restart.addActionListener(this);
		screen3_2.restart.addActionListener(this);
		screen3_3.start.addActionListener(this);
		screen3_4.start.addActionListener(this);

		
		add(screen1);

		setVisible(true);
	}

	public static void main(String args[]){//실
		new Main();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton button = (JButton) e.getSource();

		if(button == screen1.start) {
			remove(screen1);
			add(screen2);
			Board.is3 = false;
			revalidate();
			repaint();
			System.out.printf(Boolean.toString(Board.is3));
		}
		if(button == screen1.start_2) {
			remove(screen1);
			add(screen2_2);
			Board.is3 = true;
			revalidate();
			repaint();
		}
		if(button == screen1.start_3) {
			remove(screen1);
			add(screen2_3);
			revalidate();
			repaint();
		}

		if(button == screen2.start||button == screen2_2.start)
			settingPlayer();
		
		if(button == screen2_3.CreateRoom) {
			remove(screen2_3);
			add(screen3_3);
			revalidate();
			repaint();
			isOnline = true;
		}
		
		if(button == screen2_3.EnterRoom) {
			remove(screen2_3);
			add(screen3_4);
			revalidate();
			repaint();
			isOnline = true;
		}

		if(button == screen3.restart) {
			remove(screen3);
			initScreen3();
			add(screen3);
			revalidate();
			repaint();
		}
		if(button == screen3_2.restart) {
			remove(screen3_2);
			initScreen3();
			add(screen3_2);
			revalidate();
			repaint();
		}
		if(button == screen3_3.start) {
			
			Thread server = new Thread() {
				BufferedReader reader;
			    PrintWriter writer;
			public void run() {
				try { 
					ServerSocket serversocket = new ServerSocket(Integer.parseInt(screen3_3.PortInput.getText()));
					System.out.println("접속 대기중~ ");
					Socket socket = serversocket.accept();
					System.out.println("사용자 접속 했습니다");
			        System.out.println("Client ip :"+ socket.getInetAddress());
					
			        isServer = true;
			        isClient = false;
			        
					remove(screen3_3);
					add(screen4);
					revalidate();
					repaint();
					screen4.board.isOnline = isOnline;
					
			        Random random = new Random();
			        random.setSeed(System.currentTimeMillis());
			         
			        boolean isblue = random.nextBoolean();
			
			        
			        
			        reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			        writer=new PrintWriter(socket.getOutputStream(), true);
					screen4.board.writer = new PrintWriter(socket.getOutputStream(), true);
			        
					String message;
					
					if(isblue == false) {
			        	screen4.board.isMyturn = true;
			        	screen4.board.isBlue = false;
			        	screen4.board.color = Color.red;
			        	writer.println("[Blue]");
			        }else {
			        	screen4.board.isMyturn = false;
			        	screen4.board.isBlue = true;
			        	screen4.board.color = Color.blue;
			        	writer.println("[Red]");
			        }
					
					while((message = reader.readLine()) != null) {
						if(message.startsWith("[NextTurn]")) {
							screen4.board.isMyturn = true;
						}
						if(message.startsWith("[SetStone]")) {
							String temp = message.substring(10);
							
							int column = Integer.parseInt(temp.substring(0,temp.indexOf(" ")));
							int row = Integer.parseInt(temp.substring(temp.indexOf(" ")+1));
							
							if(screen4.board.isBlue) {
								screen4.board.column = column;
								screen4.board.row = row;
								screen4.board.board[column][row] = 2;
								screen4.board.memory.add(new Point(39*column + 6, 39*row +6));
								screen4.board.colorMemory.add(Color.red);
								
							}else {
								screen4.board.column = column;
								screen4.board.row = row;
								screen4.board.board[column][row] = 1;
								screen4.board.memory.add(new Point(39*column + 6, 39*row +6));
								screen4.board.colorMemory.add(Color.blue);
							}

							screen4.board.repaint();
						}
						if(message.startsWith("[lose]")) {
							screen4.board.add(onlinewinPanel= new OnlineWinPanel(false));
						}

					}
			        
			      
			    }
				catch(Exception e1) {
					e1.printStackTrace();
				}
			}
			};
			screen3_3.start.setText("대기중");
			screen3_3.start.setEnabled(false);
			server.start();
		} 
		if(button == screen3_4.start) {
			Thread client = new Thread() {
				BufferedReader reader;
			    PrintWriter writer;
				public void run() {
					try {
						Socket sk = new Socket(screen3_4.IpInput.getText(), Integer.parseInt(screen3_4.PortInput.getText()));
						
						isServer = false;
				        isClient = true;
						
						remove(screen3_4);
						add(screen4);
						revalidate();
						repaint();
						screen4.board.isOnline = isOnline;
				        
				        reader=new BufferedReader(new InputStreamReader(sk.getInputStream()));
				        writer=new PrintWriter(sk.getOutputStream(), true);
				        
				        screen4.board.writer = new PrintWriter(sk.getOutputStream(), true);
				        String message;
				        
				        while((message = reader.readLine()) != null) {
							
							if(message.startsWith("[Blue]")) {
					            screen4.board.isMyturn = false;
					            screen4.board.isBlue = true;
					            screen4.board.color = Color.blue;
					            
							}
							if(message.startsWith("[Red]")) {
								screen4.board.isMyturn = true;
								screen4.board.isBlue = false;
								screen4.board.color = Color.red;
							}
							if(message.startsWith("[NextTurn]")) {
								screen4.board.isMyturn = true;
							}
							if(message.startsWith("[SetStone]")) {
								String temp = message.substring(10);
								
								int column = Integer.parseInt(temp.substring(0,temp.indexOf(" ")));
								int row = Integer.parseInt(temp.substring(temp.indexOf(" ")+1));
								
								if(screen4.board.isBlue) {
									screen4.board.column = column;
									screen4.board.row = row;
									screen4.board.board[column][row] = 2;
									screen4.board.memory.add(new Point(39*column + 6, 39*row +6));
									screen4.board.colorMemory.add(Color.red);
								}else {
									screen4.board.column = column;
									screen4.board.row = row;
									screen4.board.board[column][row] = 1;
									screen4.board.memory.add(new Point(39*column + 6, 39*row +6));
									screen4.board.colorMemory.add(Color.blue);
								}
								
								screen4.board.repaint();
							}
							if(message.startsWith("[lose]")) {
								screen4.board.add(onlinewinPanel= new OnlineWinPanel(false));
							}
						}
				        

					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (UnknownHostException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			};
			screen3_4.start.setText("대기중");
			screen3_4.start.setEnabled(false);
			client.start();	

		}	
	}

	void settingPlayer() {
		if(!Board.is3) {
			if(screen2.name1.getText().equals("") || screen2.name2.getText().equals("") || screen2.name1.getText().equals(screen2.name2.getText()))
				new Notice();			
			else {
				screen2.p1.name = screen2.name1.getText();
				screen2.p2.name = screen2.name2.getText();
	
				switch(screen2.p1image) { 
				case 1: 
					screen2.p1.image = new ImageIcon("image/농담곰_프로필_Small 1.jpg");
					break;
				case 2:
					screen2.p1.image = new ImageIcon("image/농담곰_프로필_Small 2.jpg");
					break;
				case 3: 
					screen2.p1.image = new ImageIcon("image/농담곰_프로필_Small 3.jpg");
					break;
				case 4:
					screen2.p1.image = new ImageIcon("image/농담곰_프로필_Small 4.jpg");
					break;
				}
	
				switch(screen2.p2image) {
				case 1: 
					screen2.p2.image =new ImageIcon("image/농담곰_프로필_Small 1.jpg");
					break;
				case 2:
					screen2.p2.image = new ImageIcon("image/농담곰_프로필_Small 2.jpg");
					break;
				case 3: 
					screen2.p2.image = new ImageIcon("image/농담곰_프로필_Small 3.jpg");
					break;
				case 4:
					screen2.p2.image = new ImageIcon("image/농담곰_프로필_Small 4.jpg");
					break;
				}
				
				screen2.name1.setText("");
				screen2.name2.setText("");
	
				settingScreen3();
				remove(screen2);
				add(screen3);
				revalidate();
				repaint();
			}
		}else {
			if(screen2_2.name1.getText().equals("") || screen2_2.name2.getText().equals("") || screen2_2.name3.getText().equals("")||
					screen2_2.name1.getText().equals(screen2_2.name2.getText())||screen2_2.name1.getText().equals(screen2_2.name3.getText())||
					screen2_2.name2.getText().equals(screen2_2.name3.getText()))
				new Notice();
			else {
				screen2_2.p1.name = screen2_2.name1.getText();
				screen2_2.p2.name = screen2_2.name2.getText();
				screen2_2.p3.name = screen2_2.name3.getText();
	
				switch(screen2_2.p1image) { 
				case 1: 
					screen2_2.p1.image = new ImageIcon("image/농담곰_프로필_Small 1.jpg");
					break;
				case 2:
					screen2_2.p1.image = new ImageIcon("image/농담곰_프로필_Small 2.jpg");
					break;
				case 3: 
					screen2_2.p1.image = new ImageIcon("image/농담곰_프로필_Small 3.jpg");
					break;
				case 4:
					screen2_2.p1.image = new ImageIcon("image/농담곰_프로필_Small 4.jpg");
					break;
				}
	
				switch(screen2_2.p2image) {
				case 1: 
					screen2_2.p2.image =new ImageIcon("image/농담곰_프로필_Small 1.jpg");
					break;
				case 2:
					screen2_2.p2.image = new ImageIcon("image/농담곰_프로필_Small 2.jpg");
					break;
				case 3: 
					screen2_2.p2.image = new ImageIcon("image/농담곰_프로필_Small 3.jpg");
					break;
				case 4:
					screen2_2.p2.image = new ImageIcon("image/농담곰_프로필_Small 4.jpg");
					break;
				}
				
				switch(screen2_2.p3image) {
				case 1: 
					screen2_2.p3.image =new ImageIcon("image/농담곰_프로필_Small 1.jpg");
					break;
				case 2:
					screen2_2.p3.image = new ImageIcon("image/농담곰_프로필_Small 2.jpg");
					break;
				case 3: 
					screen2_2.p3.image = new ImageIcon("image/농담곰_프로필_Small 3.jpg");
					break;
				case 4:
					screen2_2.p3.image = new ImageIcon("image/농담곰_프로필_Small 4.jpg");
					break;
				}
				
				screen2_2.name1.setText("");
				screen2_2.name2.setText("");
				screen2_2.name3.setText("");
	
				settingScreen3();
				remove(screen2_2);
				add(screen3_2);
				revalidate();
				repaint();
			}
		}
	}

	void settingScreen3() {
		if(!Board.is3) {
			Player1Panel panel1 = new Player1Panel(screen2.p1);
			Player2Panel panel2 = new Player2Panel(screen2.p2);
			screen3.player1 = panel1;
			screen3.player2 = panel2;
			//screen3.clip = panel2.sound.clip;
			screen3.timer1 = panel1.timer;
			screen3.timer2 = panel2.timer;
			screen3.board.p1 = screen2.p1;
			screen3.board.p2 = screen2.p2;
		}else {
			Player1Panel panel1 = new Player1Panel(screen2_2.p1);
			Player2Panel panel2 = new Player2Panel(screen2_2.p2);
			Player3Panel panel3 = new Player3Panel(screen2_2.p3);
			screen3_2.player1 = panel1;
			screen3_2.player2 = panel2;
			screen3_2.player3 = panel3;
			//screen3.clip = panel2.sound.clip;
			screen3_2.timer1 = panel1.timer;
			screen3_2.timer2 = panel2.timer;
			screen3_2.board.p1 = screen2_2.p1;
			screen3_2.board.p2 = screen2_2.p2;
			screen3_2.board.p3 = screen2_2.p3;
		}
	}
	
	void initScreen3() {
		if(!Board.is3 && !isOnline) {
			screen3.board.memory.clear();
			screen3.board.colorMemory.clear();
			
			for(int i = 0; i < 19; i++)
				for (int j = 0; j < 19; j++)
					screen3.board.board[i][j] = 0;
	
			screen3.board.remove(screen3.board.winPanel);
			screen3.board.board[9][9] = 1;
			screen3.board.colorMemory.add(Color.blue);
			screen3.board.memory.add(new Point(357, 357));
			screen3.board.color = Color.red;
			screen3.board.p1Count = 0;
			screen3.board.p2Count = 0;
			
			screen3.add(screen3.start);
			screen3.remove(screen3.board);
		}else if(Board.is3 && !isOnline) {
			screen3_2.board.memory.clear();
			screen3_2.board.colorMemory.clear();
			
			for(int i = 0; i < 19; i++)
				for (int j = 0; j < 19; j++)
					screen3_2.board.board[i][j] = 0;
	
			screen3_2.board.remove(screen3_2.board.winPanel);
			screen3_2.board.board[9][9] = 1;
			screen3_2.board.colorMemory.add(Color.blue);
			screen3_2.board.memory.add(new Point(357, 357));
			screen3_2.board.color = Color.red;
			screen3_2.board.p1Count = 0;
			screen3_2.board.p2Count = 0;
			screen3_2.board.p3Count = 0;
			
			screen3_2.add(screen3_2.start);
			screen3_2.remove(screen3_2.board);
		}else if(!Board.is3 && isOnline) {
			screen4.board.memory.clear();
			screen4.board.colorMemory.clear();
			
			for(int i = 0; i < 19; i++)
				for (int j = 0; j < 19; j++)
					screen4.board.board[i][j] = 0;
	
			screen4.board.remove(screen4.board.winPanel);
			screen4.board.board[9][9] = 1;
			screen4.board.colorMemory.add(Color.blue);
			screen4.board.memory.add(new Point(357, 357));
			screen4.board.color = Color.red;
			screen4.board.p1Count = 0;
			screen4.board.p2Count = 0;
			
			screen4.remove(screen4.board);
		}
		
	}

}