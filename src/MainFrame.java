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
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import java.awt.event.AdjustmentListener;
import java.awt.event.AdjustmentEvent;


public class MainFrame extends JFrame
{
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
		  window.setVisible(true);
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
	setTitle("UDP File Transfer");
	setBounds(100, 100, 450, 357);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	JMenuBar menuBar = new JMenuBar();
	setJMenuBar(menuBar);
	
	JLabel lblNoFileIs = new JLabel("No file is selected.");
	lblNoFileIs.setForeground(Color.RED);
	
	JFileChooser fileChooser = new JFileChooser();
	
	JMenu mnFile = new JMenu("File");
	menuBar.add(mnFile);
	
	JMenuItem mntmOpenFile = new JMenuItem("Open File");
	mntmOpenFile.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) 
		{
		  int returnVal = fileChooser.showOpenDialog(getParent());
		  
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
		  setVisible(false);
		}
	});
	mnEdit.add(mntmPreferences);
	
	JTextArea textArea = new JTextArea();
	
	JLabel lblServerIsNot = new JLabel("Server is not running.");
	lblServerIsNot.setForeground(Color.RED);
	
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
			  JOptionPane.showMessageDialog(getParent(), "File is empty.", "Error", JOptionPane.ERROR_MESSAGE);
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
	
	JScrollPane scrollPane = new JScrollPane();
	
	GroupLayout groupLayout = new GroupLayout(getContentPane());
	groupLayout.setHorizontalGroup(
		groupLayout.createParallelGroup(Alignment.LEADING)
			.addGroup(groupLayout.createSequentialGroup()
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
					.addComponent(scrollPane, Alignment.LEADING)
					.addComponent(btnSendFile, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
					.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
						.addGap(235)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblServerIsNot)
							.addComponent(lblNoFileIs)
							.addComponent(lblConnectedTo))))
				.addContainerGap(25, Short.MAX_VALUE))
	);
	groupLayout.setVerticalGroup(
		groupLayout.createParallelGroup(Alignment.LEADING)
			.addGroup(groupLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
					.addComponent(btnSendFile)
					.addComponent(lblServerIsNot))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(lblNoFileIs)
				.addGap(18)
				.addComponent(lblConnectedTo)
				.addGap(81)
				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(22, Short.MAX_VALUE))
	);
	
	
	scrollPane.setViewportView(textArea);
	getContentPane().setLayout(groupLayout);
	setResizable(false);
	setLocationRelativeTo(null);
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
	setVisible(value);
  }
}
