import java.io.*; 
import java.net.*; 
import java.util.ArrayList;
class Client 
{    
  
    private static Client instance = null;
    private String filename = null;
    private ArrayList<String> files;
    private int fileLength;
    private String expectedPackets;
    private boolean fileIsOpened;
    private String toBePrinted;
    private int byteSize;
    
    private Client()
    {

    }
    
    public void openFile(File file) throws Exception
    {
      filename = file.getName();
      FileManipulation fm = new FileManipulation();
      files = fm.convertToBinary(file, byteSize);
      fileLength = fm.getFileLength();      
      fileIsOpened = true;
    }
    
	public void runClient() throws Exception    
	{       
		InetAddress IPAddress = InetAddress.getByName("localhost");       
		byte[] sendData = new byte[byteSize];       
		byte[] receiveData = new byte[byteSize];
		int toRead = 0, i = 0;
//		int fileLength = fm.getFileLength();
		
		DatagramSocket clientSocket = new DatagramSocket();
		expectedPackets = new String("Expected Packets: "+files.size()+": File("+filename+") length; "+fileLength+";");
		sendData = expectedPackets.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(expectedPackets.getBytes(), expectedPackets.getBytes().length, IPAddress, 9876);       
		clientSocket.send(sendPacket);
		
		while (i < files.size())
		{     
			sendData = files.get(i).getBytes();
			toRead = toRead + sendData.length;
			toBePrinted = "Sending "+sendData.length+" packets to Server: "+i;
			System.out.println(toBePrinted);
			
			sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);       
			clientSocket.send(sendPacket);       
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);       
			clientSocket.receive(receivePacket);       
			String modifiedSentence = new String(receivePacket.getData());       
			System.out.println("FROM SERVER:" + modifiedSentence);  
			i++;
		}
		
		clientSocket.close();
	}
	
	public static Client getInstance()
	{
	  if (instance == null)
	  {
		instance = new Client();
	  }
	  
	  return instance;
	}
	
	public boolean isOpened()
	{
	  return fileIsOpened;
	}
	
	public String getToBePrinted()
	{
	  return toBePrinted;
	}
	
	public void setByteSize(int value)
	{
	  byteSize = value;
	}
	
//	File file = new File(filename);
//	FileManipulation fm = new FileManipulation();
//	ArrayList<String> files = fm.convertToBinary(file, 512);
}