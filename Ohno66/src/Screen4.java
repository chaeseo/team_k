

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Screen4 extends JPanel implements MouseListener{
	BufferedImage background;
	
	Board board = new Board();
	
	static JPanel player1 = new JPanel();
	static JPanel player2 = new JPanel();
	
	static JButton restart = new JButton();
	
	Clip clip;
	static Timer timer1, timer2;
	
	
	Screen4() {
		setLayout(null);
		player1.setLayout(null);
		player2.setLayout(null);
		
		try {
			background = ImageIO.read(new File("image/¹è°æ.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		board.setBounds(345, 12, 750, 750);
		add(board);
		//clip.start();
		Player2Panel.second = 30;

		

	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(background, 0, 0, 1440, 800, null);
		add(player1);
		add(player2);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
	
	
}