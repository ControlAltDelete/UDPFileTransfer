import java.io.*; 
import java.net.*; 
import java.util.ArrayList;
import java.util.Scanner;

public class Server
{    
    private static Server instance = null;
    private boolean isRunning;
    private int byteSize;
   
    private Server()
    {
      byteSize = 512;
    }
    
    public static void main(String[] args)
    {
      Server serv = new Server();
      serv.runServer();
    }
    
    public int getAnInt(int something)
    {
      int wow = 0;
      wow = something;
      return wow;
    }
    
	public void runServer()       
	{   
//	    boolean fuckThisBoolean;
//	    fuckThisBoolean = true;
	  
	  	System.out.print("Enter new byte size: ");
	  	Scanner scan = new Scanner(System.in);
	  	byteSize = scan.nextInt();
	  	int newByteSize = byteSize;
	  	System.out.println(newByteSize);
	  	
		try
		{ 
			DatagramSocket serverSocket = new DatagramSocket(9876);
			byte[] receiveData = new byte[byteSize];
			byte[] sendData = new byte[byteSize];
			int currentPackets = 0;
			ArrayList<String> files = new ArrayList<String>();
			FileManipulation fm = new FileManipulation();
			String data;
			
			while(true)                
			{
			  	isRunning = true;
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				serverSocket.receive(receivePacket);                   
				
				String sentence = new String(receivePacket.getData());
				int expectedPackets = Integer.parseInt(sentence.substring(sentence.indexOf(":")+2, sentence.lastIndexOf(":")));
				int fileLength = Integer.parseInt(sentence.substring(sentence.indexOf(";")+2, sentence.lastIndexOf(";")));
				String filename = sentence.substring(sentence.indexOf("(")+1, sentence.indexOf(")"));
				filename = "received-".concat(filename);
				System.out.println("RECEIVED: Expected Packets - " + expectedPackets);
				System.out.println("RECEIVED: FileSize - " + fileLength);
				System.out.println("Incoming file will be saved as "+filename+".");
				
				while(currentPackets < expectedPackets)
				{
					serverSocket.receive(receivePacket);
					System.out.println(currentPackets+" "+receivePacket.getData().length);
					data = new String(receivePacket.getData());
					
					if(currentPackets==expectedPackets-1)
					{
						files.add(data.substring(0, fileLength%byteSize));
					}
					
					else
					{
						files.add(data);
					}
					
					InetAddress IPAddress = receivePacket.getAddress();                   
					int port = receivePacket.getPort();                   
					String capitalizedSentence = new String("Received segment "+currentPackets); 
					sendData = capitalizedSentence.getBytes();                   
					DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
					serverSocket.send(sendPacket);      
					currentPackets++;
				}
				
				fm.convertToImage(files, filename);
			}  
		} 
		
		catch (IOException e)
		{
		
		}
	}
	
	public static Server getInstance()
	{
	  if (instance == null)
	  {
		instance = new Server();
	  }	 
	  
	  return instance;
	}
	
	public boolean serverIsRunning()
	{
	  return isRunning;
	}
	
	public int getByteSize()
	{
	  return byteSize;
	}
}