

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

public class Screen2_3 extends JPanel{
	BufferedImage background;
	
	JLabel title = new JLabel("온라인");
	
	JButton CreateRoom = new JButton("방 만들기");
	JButton EnterRoom = new JButton("방 입장하기");
	
	Screen2_3() {
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
		
		CreateRoom.setBounds(440, 620, 240, 80);
		CreateRoom.setForeground(new Color(206, 124, 24));
		CreateRoom.setFont(new Font("DX�깉�궇B", Font.PLAIN, 45));
		
		EnterRoom.setBounds(750, 620, 240, 80);
		EnterRoom.setForeground(new Color(206, 124, 24));
		EnterRoom.setFont(new Font("DX�깉�궇B", Font.PLAIN, 45));
		
		
		add(title);
		add(CreateRoom);
		add(EnterRoom);
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0, 1440, 800, null);
	}
}