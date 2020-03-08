package Frames;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

// Dianelys Saldana 3/7/2020
public class InitialMenu extends JPanel {

	public InitialMenu() {
		setLayout(null);

		JButton btnDev = new JButton("Developer");
		btnDev.setBounds(66, 111, 125, 35);
		add(btnDev);

		JButton btnPlayer = new JButton("Player");
		btnPlayer.setBounds(242, 111, 89, 35);
		add(btnPlayer);
		btnPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JFrame F = new JFrame();
				F.getContentPane().add(new PlayerInterface());
				F.setSize(1460,735);
				 
				F.setVisible(true);
				F.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});

	}

}


