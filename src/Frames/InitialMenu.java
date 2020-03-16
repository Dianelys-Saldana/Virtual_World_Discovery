package Frames;

import javax.swing.JPanel;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

/**Dianelys Saldana 03/07/2020
 ** Class for initial frame of the game
 */
public class InitialMenu extends JPanel {

	private FrameManager f;

	public InitialMenu(FrameManager f) {
		setLayout(null);
		setBounds(100, 100, 1024,735);
		this.f = f;

		JButton btnDev = new JButton("Map Designer");
		btnDev.setBounds(430, 380, 185, 90);
		add(btnDev);
		btnDev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					f.hide2();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		

		JButton btnPlayer = new JButton("Player");
		btnPlayer.setBounds(430, 241, 185, 90);
		add(btnPlayer);
		btnPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					f.hideInitialFrame();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		JLabel lblNewLabel = new JLabel("Background");
		lblNewLabel.setIcon(new ImageIcon(InitialMenu.class.getResource("/Image/Game-background.png")));
		lblNewLabel.setBounds(0, 0, 1024, 735);
		add(lblNewLabel);
	}
}


