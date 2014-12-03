import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.codec.binary.Base64;


public class FileManipulation {
        private int strLen;
        
        public ArrayList<String> convertToBinary(File file, int partition){
        	ArrayList<String> files = new ArrayList<String>();
        	int currentPos = 0;
        	int read = partition;
        	int bytesToRead = 0;
            try{
                
			/*
			 * Reading a Image file from file system
			 */
			FileInputStream imageInFile = new FileInputStream(file);
			byte imageData[] = new byte[(int)file.length()];
			imageInFile.read(imageData);
			
			/*
			 * Converting Image byte array into Base64 String 
			 */
			String imageDataString = encodeImage(imageData);
			bytesToRead = imageDataString.length();
			strLen = bytesToRead;
			read = bytesToRead;
			while(currentPos<bytesToRead){
				files.add(imageDataString.substring(currentPos, currentPos+partition));
				currentPos = currentPos+partition;
				read = read - partition;
				if(read<partition)
					partition = read;
				
			}
                        
            return files;
			
            } catch (FileNotFoundException e) {
			System.out.println("File not found" + e);
	    } catch (IOException ioe) {
			System.out.println("Exception while reading the File " + ioe);
            }
            
            return null;
        }
        
        public void convertToImage(ArrayList<String> files, String filename){
            try{
                //Converting a Base64 String into Image byte array 
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i<files.size(); i++){
				sb.append(files.get(i));
			}
			String imageDataString = sb.toString();
			byte[] imageByteArray = decodeImage(imageDataString);
			
			
			// Write a image byte array into file system  
			 
			FileOutputStream imageOutFile = new FileOutputStream(System.getProperty("user.dir")+"/src/"+filename,true);
			imageOutFile.write(imageByteArray);
			
			
			imageOutFile.close();
			
			System.out.println("File Successfully Manipulated!");
		} catch (FileNotFoundException e) {
			System.out.println("File not found" + e);
		} catch (IOException ioe) {
			System.out.println("File while reading the Image " + ioe);
		}
            }
        

            public String encodeImage(byte[] imageByteArray){		
                        return Base64.encodeBase64URLSafeString(imageByteArray);		
                }

                /**
                 * Decodes the base64 string into byte array
                 * @param imageDataString - a {@link java.lang.String} 
                 * @return byte array
                 */
                public byte[] decodeImage(String imageDataString){		
                        return Base64.decodeBase64(imageDataString);
                }
        /*
	public static void main(String[] args) {
		File file = new File("C:/Users/cinnamon/Desktop/jelli.jpg");
		
		try {
			
			 // Reading a Image file from file system
			 
                         
			FileInputStream imageInFile = new FileInputStream(file);
			byte imageData[] = new byte[(int)file.length()];
			imageInFile.read(imageData);
			
			
			 //Converting Image byte array into Base64 String 
			
			String imageDataString = encodeImage(imageData);
			
			
			 //Converting a Base64 String into Image byte array 
			
			byte[] imageByteArray = decodeImage(imageDataString);
			
			
			// Write a image byte array into file system  
			 
			FileOutputStream imageOutFile = new FileOutputStream("C:/Users/cinnamon/Desktop/jelli-after-convert.jpg");
			imageOutFile.write(imageByteArray);
			
			imageInFile.close();
			imageOutFile.close();
			
			System.out.println("Image Successfully Manipulated!");
		} catch (FileNotFoundException e) {
			System.out.println("Image not found" + e);
		} catch (IOException ioe) {
			System.out.println("Exception while reading the Image " + ioe);
		}

	}*/
        
        
	
	/**
	 * Encodes the byte array into base64 string
	 * @param imageByteArray - byte array
	 * @return String a {@link java.lang.String}
	 */
	public int getFileLength(){
		return strLen;
	}

}
