

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Screen1 extends JPanel{
	BufferedImage background;
	
	JLabel title = new JLabel("오노육목");
	
	JButton start = new JButton("2인");
	JButton start_2 = new JButton("3인");
	JButton start_3 = new JButton("온라인");
	
	Screen1() {
		setLayout(null);
		
		try {
			background = ImageIO.read(new File("image/농담곰 6.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		title.setBounds(520, 80, 800, 250);
		title.setForeground(new Color(255, 195, 35));
		title.setFont(new Font("HanS", Font.BOLD, 100));
		
		start.setBounds(290, 620, 240, 80);
		start.setForeground(new Color(206, 124, 24));
		start.setFont(new Font("DX새날B", Font.PLAIN, 45));
		
		start_2.setBounds(600, 620, 240, 80);
		start_2.setForeground(new Color(206, 124, 24));
		start_2.setFont(new Font("DX새날B", Font.PLAIN, 45));
		
		start_3.setBounds(900, 620, 240, 80);
		start_3.setForeground(new Color(206, 124, 24));
		start_3.setFont(new Font("DX새날B", Font.PLAIN, 45));
		
		add(title);
		add(start);
		add(start_2);
		add(start_3);
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0, 1440, 800, null);
	}
}