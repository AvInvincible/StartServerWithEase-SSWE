import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;


public class JobExecutor extends JFrame implements ActionListener {
	
	JButton start, close;
	public JobExecutor() {
			super("Start Server");
			start = new JButton("Start");
			start.setBounds(120, 100, 100, 30);
			start.addActionListener(this);
			close = new JButton("Stop");
			close.setBounds(120, 250, 100, 30);
			close.addActionListener(this);
			add(start);
			add(close);
			
			setSize(400, 400);
			setLayout(null);
			setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Start bat file.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	void startBatFile() throws IOException {
		String cmd = "cmd /c start c:\\startJob\\sample.bat";
		Runtime r = Runtime.getRuntime();
		try {
			Process pr = r.exec(cmd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
