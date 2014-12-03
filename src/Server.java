import java.io.*; 
import java.net.*; 
import java.util.ArrayList;

public class Server
{    
    private static Server instance = null;
    
    
	public static void main(String args[])// throws Exception       
	{          
		try{
			DatagramSocket serverSocket = new DatagramSocket(9876);
			byte[] receiveData = new byte[512];
			byte[] sendData = new byte[512];
			int currentPackets = 0;
			ArrayList<String> files = new ArrayList<String>();
			FileManipulation fm = new FileManipulation();
			String data;
			while(true)                
			{
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
				
				while(currentPackets < expectedPackets){
					serverSocket.receive(receivePacket);
					System.out.println(currentPackets+" "+receivePacket.getData().length);
					data = new String(receivePacket.getData());
					if(currentPackets==expectedPackets-1){
						files.add(data.substring(0, fileLength%512));
					}
					else
						files.add(data);
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
		} catch (IOException e){
		
		}
	} 
}