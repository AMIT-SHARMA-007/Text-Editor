package corePack;
import javax.swing.*;

public class MainFrame {
	
	static JFrame mfr;
	
	public MainFrame() {
		
		mfr = new JFrame("Editor");
		mfr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mfr.setSize(800, 600);
		mfr.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				MainFrame mf = new MainFrame();
				InternalComponents ic = new InternalComponents(mfr);
			}
			
		});

	}

}
