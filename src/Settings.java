import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.UnknownHostException;


public class Settings
{

  private static Settings instance = null;
  private JFrame frame;
  private JTextField byteSizeTxtFild;
  private JTextField textField_1;
  private JTextField textField_2;

  /**
   * Launch the application.
   */
  
  private Settings()
  {
	initialize();
  }
  
  public void show()
  {
	EventQueue.invokeLater(new Runnable()
	{
	  public void run()
	  {
		try
		{
		  Settings window = new Settings();
		  window.frame.setVisible(true);
		} catch (Exception e)
		{
		  e.printStackTrace();
		}
	  }
	});
  }
  
  public void setVisibility(boolean val)
  {
	frame.setVisible(val);
  }

  /**
   * Create the application.
   */

  /**
   * Initialize the contents of the frame.
   */
  private void initialize()
  {
	frame = new JFrame();
	frame.setBounds(100, 100, 450, 300);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	JLabel lblChunksInBytes = new JLabel("Chunks in bytes:");
	
	byteSizeTxtFild = new JTextField();
	byteSizeTxtFild.setColumns(10);
	
	JLabel lblSequenceNumber = new JLabel("Sequence number:");
	
	textField_1 = new JTextField();
	textField_1.setColumns(10);
	
	JLabel lblNewLabel = new JLabel("Loss Probability:");
	
	textField_2 = new JTextField();
	textField_2.setColumns(10);
	
	JButton btnSave = new JButton("Save");
	btnSave.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
		  try
		  {
			frame.setVisible(false);
			MainFrame.getInstance().setVisiblility(true);
			Client.getInstance().setByteSize(Integer.parseInt(byteSizeTxtFild.getText()));
			System.out.println(Integer.parseInt(byteSizeTxtFild.getText()));
			Server.getInstance().setByteSize(Integer.parseInt(byteSizeTxtFild.getText()));
		  } 
		  
		  catch (UnknownHostException e)
		  {
			e.printStackTrace();
		  }
		}
	});
	GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
	groupLayout.setHorizontalGroup(
		groupLayout.createParallelGroup(Alignment.LEADING)
			.addGroup(groupLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
					.addComponent(lblChunksInBytes)
					.addComponent(lblSequenceNumber)
					.addComponent(lblNewLabel))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
					.addComponent(textField_2, 0, 0, Short.MAX_VALUE)
					.addComponent(byteSizeTxtFild, 0, 0, Short.MAX_VALUE)
					.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE))
				.addContainerGap(242, Short.MAX_VALUE))
			.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
				.addContainerGap(326, Short.MAX_VALUE)
				.addComponent(btnSave)
				.addGap(19))
	);
	groupLayout.setVerticalGroup(
		groupLayout.createParallelGroup(Alignment.LEADING)
			.addGroup(groupLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
					.addComponent(lblChunksInBytes)
					.addComponent(byteSizeTxtFild, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
					.addComponent(lblSequenceNumber)
					.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
					.addComponent(lblNewLabel)
					.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
				.addComponent(btnSave)
				.addContainerGap())
	);
	frame.getContentPane().setLayout(groupLayout);
  }
  
  public static Settings getInstance()
  {
	if (instance == null)
	{
	  instance = new Settings();
	}
	
	return instance;
  }
}
