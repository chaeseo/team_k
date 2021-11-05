

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
import javax.swing.JTextField;

public class Screen3_3 extends JPanel{
	BufferedImage background;
	
	JLabel PortID = new JLabel("Port");
	JLabel title = new JLabel("방만들기");
	
	JTextField PortInput = new JTextField();
	JButton start = new JButton("만들기");

	
	Screen3_3() {
		setLayout(null);
		
		try {
			background = ImageIO.read(new File("image/배경2.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PortID.setBounds(420, 425, 400, 60);
		PortID.setFont(new Font("HanS", Font.BOLD, 40));
	    title.setBounds(590, 200, 400, 100);
		title.setFont(new Font("HanS", Font.BOLD, 60));

		
		PortInput.setBounds(515, 425, 440, 60);
		
		PortInput.setFont(new Font("Serif", Font.PLAIN, 40));
		
		start.setBounds(600, 500, 200, 60);
		start.setFont(new Font("Serif", Font.BOLD, 40));

				
		add(title);
		add(PortID);
		add(PortInput);
		add(start);
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0, 1440, 800, null);
	}
}