import java.io.*; 
import java.net.*; 
import java.util.ArrayList;
class Client 
{    
	public static void main(String args[]) throws Exception    
	{       
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Filename: ");
		InetAddress IPAddress = InetAddress.getByName("localhost");       
		byte[] sendData = new byte[512];       
		byte[] receiveData = new byte[512];
		String filename = inFromUser.readLine();
		File file = new File(filename);
		FileManipulation fm = new FileManipulation();
		ArrayList<String> files = fm.convertToBinary(file, 512);
		int toRead = 0, i = 0;
		int fileLength = fm.getFileLength();
		DatagramSocket clientSocket = new DatagramSocket();
		String expectedPackets = new String("Expected Packets: "+files.size()+": File("+filename+") length; "+fileLength+";");
		sendData = expectedPackets.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(expectedPackets.getBytes(), expectedPackets.getBytes().length, IPAddress, 9876);       
		clientSocket.send(sendPacket);
		
		while(i<files.size()){     
			sendData = files.get(i).getBytes();
			toRead = toRead + sendData.length;
			System.out.println("Sending "+sendData.length+" packets to Server: "+i);
			sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);       
			clientSocket.send(sendPacket);       
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);       
			clientSocket.receive(receivePacket);       
			String modifiedSentence = new String(receivePacket.getData());       
			System.out.println("FROM SERVER:" + modifiedSentence);  
			i++;
		}clientSocket.close();
	} 
}