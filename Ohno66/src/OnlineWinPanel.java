

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class OnlineWinPanel extends JPanel{
	JPanel back = new JPanel();
	JLabel message = new JLabel();
	JLabel image = new JLabel();

	OnlineWinPanel(boolean isWin) {
		setSize(750, 750);
		setBackground(new Color(255, 207, 165));
		
		back.setLayout(null);
		back.setBounds(175, 100, 400, 550);
		back.setBackground(new Color(224, 140, 58));
		
		message.setBounds(90, 200, 300, 100);
		if(isWin)
			message.setText(" ½Â¸®!");
		else
			message.setText(" ÆÐ¹è...");
		message.setFont(new Font("HanS", Font.BOLD, 50));
		
		
		back.add(message);
		
		add(back);
		
	}
	
}