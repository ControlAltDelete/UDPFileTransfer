import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JProgressBar;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MainFrame
{

  private JFrame frmUdpFileTransfer;

  /**
   * Launch the application.
   */
  public static void main(String[] args)
  {
	EventQueue.invokeLater(new Runnable()
	{
	  public void run()
	  {
		try
		{
		  MainFrame window = new MainFrame();
		  window.frmUdpFileTransfer.setVisible(true);
		} catch (Exception e)
		{
		  e.printStackTrace();
		}
	  }
	});
  }

  /**
   * Create the application.
   */
  public MainFrame()
  {
	initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize()
  {
	frmUdpFileTransfer = new JFrame();
	frmUdpFileTransfer.setTitle("UDP File Transfer");
	frmUdpFileTransfer.setBounds(100, 100, 450, 365);
	frmUdpFileTransfer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	JMenuBar menuBar = new JMenuBar();
	frmUdpFileTransfer.setJMenuBar(menuBar);
	
	JMenu mnFile = new JMenu("File");
	menuBar.add(mnFile);
	
	JMenuItem mntmOpenFile = new JMenuItem("Open File");
	mnFile.add(mntmOpenFile);
	
	JMenuItem mntmViewDownloaded = new JMenuItem("View Downloaded");
	mnFile.add(mntmViewDownloaded);
	
	JMenu mnEdit = new JMenu("Edit");
	menuBar.add(mnEdit);
	
	JMenuItem mntmPreferences = new JMenuItem("Preferences");
	mnEdit.add(mntmPreferences);
	
	JTextPane textPane = new JTextPane();
	
	JLabel lblServerIsNot = new JLabel("Server is not running.");
	lblServerIsNot.setForeground(Color.RED);
	
	JLabel lblNoFileIs = new JLabel("No file is selected.");
	lblNoFileIs.setForeground(Color.RED);
	
	JButton btnOpenServer = new JButton("Open Server");
	btnOpenServer.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		}
	});
	
	JButton btnSendFile = new JButton("Send File");
	
	JLabel lblConnectedTo = new JLabel("Connected to: 127.0.0.1");
	GroupLayout groupLayout = new GroupLayout(frmUdpFileTransfer.getContentPane());
	groupLayout.setHorizontalGroup(
		groupLayout.createParallelGroup(Alignment.LEADING)
			.addGroup(groupLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addComponent(btnSendFile, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnOpenServer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.RELATED, 186, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblServerIsNot)
							.addComponent(lblNoFileIs)
							.addComponent(lblConnectedTo))
						.addContainerGap(41, Short.MAX_VALUE))
					.addGroup(groupLayout.createSequentialGroup()
						.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 410, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(14, Short.MAX_VALUE))))
	);
	groupLayout.setVerticalGroup(
		groupLayout.createParallelGroup(Alignment.TRAILING)
			.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(19)
						.addComponent(btnOpenServer))
					.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblServerIsNot)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(lblNoFileIs)))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
					.addComponent(btnSendFile)
					.addComponent(lblConnectedTo))
				.addPreferredGap(ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
				.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
				.addGap(34))
	);
	frmUdpFileTransfer.getContentPane().setLayout(groupLayout);
	frmUdpFileTransfer.setResizable(false);
	frmUdpFileTransfer.setLocationRelativeTo(null);
  }
}
