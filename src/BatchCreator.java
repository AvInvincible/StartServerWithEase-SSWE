import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class BatchCreator extends JFrame implements ActionListener {
	FileOutputStream fos;
	DataOutputStream dos;
	JLabel label1, label2, label3;
	JTextField filePathInputField, fileNameInputField;
	JComboBox<String> executableFileName;
	JButton submitButton;
	String jarPath;
	static File destfile = new File("C:/startJob");
	String serverName, serverNameCMD;
	String[] executableFileNameOptions = new String[] { "Jenkins", "Selenium" };

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void main(String args[]) throws IOException {
		if (!destfile.exists()) {
			new BatchCreator();
		} else {
			new JobExecutor();
		}
	}

	public BatchCreator() {
		super("Start any server");
		label1 = new JLabel("Path to your .war or .jar file:");
		label1.setBounds(30, 30, 250, 90);
		filePathInputField = new JTextField();
		filePathInputField.setBounds(95, 85, 250, 20);
		label2 = new JLabel("What's the name of your executable  file:");
		label2.setBounds(30, 90, 250, 90);
		fileNameInputField = new JTextField();
		fileNameInputField.setBounds(95, 145, 250, 20);
		label3 = new JLabel("Select server type:");
		label3.setBounds(30, 150, 145, 90);
		executableFileName = new JComboBox<>(executableFileNameOptions);
		executableFileName.setBounds(160, 185, 185, 20);
		submitButton = new JButton("Submit");
		submitButton.setBounds(120, 250, 100, 30);
		submitButton.addActionListener(this);
		add(label1);
		add(label2);
		add(filePathInputField);
		add(fileNameInputField);
		add(label3);
		add(executableFileName);
		add(submitButton);
		setSize(400, 400);
		setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent action) {
		jarPath = filePathInputField.getText();
		String drive = jarPath.split(":")[0];
		serverName = (String) executableFileName.getSelectedItem();
		serverNameCMD = fileNameInputField.getText();
		try {
			if (!destfile.exists()) {
				destfile.mkdir();
				File batchFile = new File("C:/startJob/" + serverName + ".bat");
				batchFile.createNewFile();
				fos = new FileOutputStream(batchFile);
				dos = new DataOutputStream(fos);
				if (!drive.equalsIgnoreCase("C:")) {
					dos.writeBytes(drive + ":\n");
				}
				dos.writeBytes("cd " + jarPath + "\n");
				if (serverName.equalsIgnoreCase("Jenkins")) {
					if (!serverNameCMD.contains(".war")) {
						dos.writeBytes("java -jar " + serverNameCMD + ".war\n");
					} else if (serverNameCMD.contains(".war")) {
						dos.writeBytes("java -jar " + serverNameCMD + "\n");
					} else {

					}
				} else if (serverName.equalsIgnoreCase("Selenium")) {
					if (!serverNameCMD.contains(".jar")) {
						dos.writeBytes("java -jar " + serverNameCMD + ".jar\n");
					} else if (serverNameCMD.contains(".jar")) {
						dos.writeBytes("java -jar " + serverNameCMD + "\n");
					} else {

					}
				} else {
				}

				System.out.println("Job Created");
			} else {
				System.out.println("Job already created");
			}
		} catch (Exception e) {

		}

	}

}
