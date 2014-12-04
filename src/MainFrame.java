import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
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
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JTextArea;


public class MainFrame
{

  private JFrame frmUdpFileTransfer;
  private static MainFrame instance = null;
  
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

  private MainFrame() throws UnknownHostException
  {
	initialize();
  }

  private void initialize() throws UnknownHostException
  {
	frmUdpFileTransfer = new JFrame();
	frmUdpFileTransfer.setTitle("UDP File Transfer");
	frmUdpFileTransfer.setBounds(100, 100, 450, 365);
	frmUdpFileTransfer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	JMenuBar menuBar = new JMenuBar();
	frmUdpFileTransfer.setJMenuBar(menuBar);
	
	JLabel lblNoFileIs = new JLabel("No file is selected.");
	lblNoFileIs.setForeground(Color.RED);
	
	JFileChooser fileChooser = new JFileChooser();
	
	JMenu mnFile = new JMenu("File");
	menuBar.add(mnFile);
	
	JMenuItem mntmOpenFile = new JMenuItem("Open File");
	mntmOpenFile.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) 
		{
		  int returnVal = fileChooser.showOpenDialog(frmUdpFileTransfer);
		  
		  if (returnVal == fileChooser.APPROVE_OPTION)
		  {
			File file = fileChooser.getSelectedFile();
			
			try
			{
			  Client.getInstance().openFile(file);
			  lblNoFileIs.setText(file.getName());
			  lblNoFileIs.setForeground(Color.black);
			} 
			
			catch (Exception e1)
			{
			  // TODO Auto-generated catch block
			  e1.printStackTrace();
			}
		  }
			
		}
	});
	mnFile.add(mntmOpenFile);
	
	JMenuItem mntmViewDownloaded = new JMenuItem("View Downloaded");
	mnFile.add(mntmViewDownloaded);
	
	JMenu mnEdit = new JMenu("Edit");
	menuBar.add(mnEdit);
	
	JMenuItem mntmPreferences = new JMenuItem("Preferences");
	mntmPreferences.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) 
		{
		  Settings.getInstance().setVisibility(true);
		  frmUdpFileTransfer.setVisible(false);
		}
	});
	mnEdit.add(mntmPreferences);
	
	JLabel lblServerIsNot = new JLabel("Server is not running.");
	lblServerIsNot.setForeground(Color.RED);
	
	JTextArea textArea = new JTextArea();
	
	if (Server.getInstance().serverIsRunning())
	{
	  lblServerIsNot.setText("Server is running.");
	  lblServerIsNot.setForeground(Color.GREEN);
	}
	JButton btnSendFile = new JButton("Send File");
	btnSendFile.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) 
		{
		  ArrayList<String> strings = Client.getInstance().getStrings();
		  try
		  {
			if (Client.getInstance().isOpened())
			{
			  Client.getInstance().runClient();	
			  
			  for (int cntr = 0; cntr < strings.size() ; cntr++)
			  {
				textArea.append(strings.get(cntr) + "\n");
			  }
			}
			
			else
			{
			  JOptionPane.showMessageDialog(frmUdpFileTransfer.getParent(), "File is empty.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		  }
		  
		  catch (Exception e1)
		  {
			e1.printStackTrace();
		  }
		}
	});
	
	JLabel lblConnectedTo = new JLabel();
	
	lblConnectedTo.setText("Connected to: " + InetAddress.getByName("localhost").toString());
	
	GroupLayout groupLayout = new GroupLayout(frmUdpFileTransfer.getContentPane());
	groupLayout.setHorizontalGroup(
		groupLayout.createParallelGroup(Alignment.LEADING)
			.addGroup(groupLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
						.addComponent(btnSendFile, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblServerIsNot)
							.addComponent(lblNoFileIs)
							.addComponent(lblConnectedTo))
						.addContainerGap())
					.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 407, GroupLayout.PREFERRED_SIZE)
						.addGap(21))))
	);
	groupLayout.setVerticalGroup(
		groupLayout.createParallelGroup(Alignment.LEADING)
			.addGroup(groupLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addComponent(lblServerIsNot)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(lblNoFileIs)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(lblConnectedTo))
					.addComponent(btnSendFile))
				.addGap(41)
				.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(22, Short.MAX_VALUE))
	);
	frmUdpFileTransfer.getContentPane().setLayout(groupLayout);
	frmUdpFileTransfer.setResizable(false);
	frmUdpFileTransfer.setLocationRelativeTo(null);
  }
  
  public static MainFrame getInstance() throws UnknownHostException
  {
	if (instance == null)
	{
	  instance = new MainFrame();
	}
	
	return instance;
  }
  
  public void setVisiblility(boolean value)
  {
	frmUdpFileTransfer.setVisible(value);
  }
}
