package pushbox;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

/**
 *
 * @author srb
 */
public class MainFrame extends JFrame {

	public MainFrame() {
		initComponents();
		myinit();
	}

	public void myinit() {
		setSize(820, 620);
		setResizable(false);
		gamePannel = new GamePannel();

		add(gamePannel);
		
		try {
			setIconImage(ImageIO.read(MainFrame.class.getResource("mainicon.jpg")));
		} catch (IOException e) {
			Logger.getLogger(MainFrame.class.getName()).log(Level.WARNING, null, e);
		}
		setVisible(true);
	}

	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	public static void main(String args[]) {

		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException ex) {
			Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
		}

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainFrame();
			}
		});
	}

	// my variables
	private GamePannel gamePannel;
	// Variables declaration - do not modify//GEN-BEGIN:variables
	// End of variables declaration//GEN-END:variables
}
