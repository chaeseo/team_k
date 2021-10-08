
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class RunGame extends JFrame {
	private Container c;
	MapSize MS = new MapSize();
	GameMethod GM = new GameMethod();
	ShowMap m;

	public RunGame(String title) {
		setTitle(title);
		createMenu();
		setBounds(200, 20, 650, 750);
		c = getContentPane();
		c.setLayout(null);
		m = new ShowMap(MS, GM);
		setContentPane(m);
		MouseAction Mc = new MouseAction(GM, MS, m, this);
		addMouseListener(Mc);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	class MenuActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			switch (cmd) { // �޴� �������� ���� ����
			case "2��":
				GM.setGameMode(2);
				GM.ClearMap();
				GM.setStart();
				m.repaint();
				GM.isStart = true;
				break;
			case "3��":
				GM.setGameMode(3);
				GM.ClearMap();
				GM.setStart();
				m.repaint();
				GM.isStart = true;
				break;
			}
		}
	}

	public void createMenu() {
		JMenuBar mb = new JMenuBar(); // �޴��� ����
		JMenuItem[] menuItem = new JMenuItem[2];
		String[] itemTitle = { "2��", "3��" };
		JMenu screenMenu = new JMenu("���Ӹ�弱��");
		MenuActionListener listener = new MenuActionListener();
		for (int i = 0; i < menuItem.length; i++) {
			menuItem[i] = new JMenuItem(itemTitle[i]);
			menuItem[i].addActionListener(listener);
			screenMenu.add(menuItem[i]);
		}
		mb.add(screenMenu);
		setJMenuBar(mb);
	}

}