

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Player2Panel extends JPanel implements ActionListener{
	JLabel name = new JLabel();
	JLabel image = new JLabel();
	//JLabel time = new JLabel(new ImageIcon("image/clock.png"), JLabel.LEFT);
	
	Timer timer = new Timer(1000, this);
	
	//BackgroundSound sound = new BackgroundSound();
	
	static int second = 30;

	Player2Panel(Player p) {
		if(!Board.is3)
			setBounds(1120, 0, 320, 800);
		if(Board.is3)
			setBounds(1120, 0, 320, 400);
		setBackground(new Color(224, 140, 58));
		setLayout(null);
		
		name.setBounds(95, 50, 300, 100);
		name.setText("Name : " + p.name);
		name.setFont(new Font("Serif", Font.BOLD, 20));
		
		image.setBounds(96, 120, 128, 140);
		image.setBackground(new Color(255, 244, 164));
		image.setOpaque(true);
		image.setIcon(p.image);
		
		//time.setBounds(40, 420, 140, 32);
		
		add(name);
		add(image);
		//add(sound);
		//add(time);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.red);
		g.fillOval(135, 300, 50, 50);
		
		Graphics2D g2 = (Graphics2D) g;
		
		if(Board.color == Color.red) {
			g.setColor(new Color(255, 244, 164));
			//g.fillRect(80,  426, second * 6, 20);
			g2.setStroke(new BasicStroke(5));
			g2.setColor(Color.red);
			g2.drawRect(65, 75, 188, 290);
		}
		else {
			g.setColor(new Color(255, 244, 164));
			//g.fillRect(80,  426, 180, 20);
			second = 30;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(timer == e.getSource()) {
			second--;
			if(second == 0) {
				Board.color = Color.black;
				Board.p1Count = 0;
				Board.p2Count = 0;
			}
			repaint();
		}
	}
}