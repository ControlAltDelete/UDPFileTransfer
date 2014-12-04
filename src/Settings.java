import java.awt.EventQueue;

import javax.swing.ButtonGroup;
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

import javax.swing.JRadioButton;


public class Settings
{

  private static Settings instance = null;
  private JFrame frame;
  private JTextField textField_1;
  private JTextField textField_2;
  private JTextField textField;

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
		  } 
		  
		  catch (UnknownHostException e)
		  {
			e.printStackTrace();
		  }
		}
	});
	
	JRadioButton rdbtnStopAndWait = new JRadioButton("Stop and Wait");
	
	JRadioButton rdbtnGobackn = new JRadioButton("Go-Back-N");
	
	ButtonGroup bGroup = new ButtonGroup();
	bGroup.add(rdbtnStopAndWait);
	bGroup.add(rdbtnGobackn);
	
	textField = new JTextField();
	textField.setColumns(10);
	GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
	groupLayout.setHorizontalGroup(
		groupLayout.createParallelGroup(Alignment.TRAILING)
			.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addComponent(rdbtnGobackn)
						.addContainerGap())
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btnSave)
							.addGap(37))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(rdbtnStopAndWait)
							.addContainerGap(319, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addGap(30)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, 74, Short.MAX_VALUE))
										.addComponent(textField, GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblSequenceNumber)
									.addGap(18)
									.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)))
							.addGap(242)))))
	);
	groupLayout.setVerticalGroup(
		groupLayout.createParallelGroup(Alignment.LEADING)
			.addGroup(groupLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
					.addComponent(lblSequenceNumber)
					.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(26)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
					.addComponent(lblNewLabel)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(10)
				.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGap(13)
				.addComponent(rdbtnStopAndWait)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(rdbtnGobackn)
				.addPreferredGap(ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
				.addComponent(btnSave)
				.addGap(20))
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
