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
	
	public InitialMenu() throws IOException {
		setLayout(null);
		setBounds(100, 100, 1024,735);
		
		JButton btnDev = new JButton("Map Designer");
		btnDev.setBounds(430, 380, 185, 90);
		add(btnDev);

		JButton btnPlayer = new JButton("Player");
		btnPlayer.setBounds(430, 241, 185, 90);
		add(btnPlayer);
		btnPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
//				JFrame F = new JFrame();
//				F.getContentPane().add(new PlayerInterface());
//				F.setSize(1024,735);
//				F.setVisible(true);
//				F.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
//				F.removeAll();
//				F.invalidate();
//				F.getContentPane().add(new PlayerInterface());
//				F.getContentPane().revalidate();
//				F.repaint();
				
//				F.add(new PlayerInterface());
//				F.setSize(1024,735);
//				F.setVisible(true);
//				F.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
//				for (Component component : F.getComponents()) {
//			      F.setVisible(false);
//			}
				FrameManager f = new FrameManager();
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


