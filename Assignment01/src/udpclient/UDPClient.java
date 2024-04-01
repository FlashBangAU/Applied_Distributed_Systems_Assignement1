/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package udpclient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author User
 */
public class UDPClient {

    private static String hostName;

    //client sends request to retrieve data for member table
    public static void main(String args[]) {
        DatagramSocket aSocket = null;

        final String message = "memberlistObject";
        final int serverPort = 2230;
        final byte[] buffer;
        StringBuilder str = new StringBuilder();
        str.append(message);
        hostName = "localhost";
        
        final String fName = "First Name";
        final String lName = "Last Name";
        final String address = "Address";
        final String phNumber = "Phone Number";
        
        try {
            aSocket = new DatagramSocket();
            byte[] m = str.toString().getBytes();
            InetAddress aHost = InetAddress.getByName(hostName);
            //packet to send 		                                                 
            DatagramPacket request = new DatagramPacket(m,
                    str.length(), aHost, serverPort);
            aSocket.send(request);
            buffer = new byte[1000];
            // packet to receive message, specify, array and array size
            DatagramPacket reply = new DatagramPacket(buffer,
                    buffer.length);
            //reply is recieved
            aSocket.receive(reply);
            
            String msg = new String(reply.getData(), reply.getOffset(), reply.getLength());
            
            //test String to verify if table format works with more 'members'
//            msg = "fName1:lName1:adress1:phNumber1:"
//                    + "fName2:lName2:adress2:phNumber2:"
//                    + "fName3:lName3:adress3:phNumber3";
            
            String[] memberArray = msg.split(":");
            
            //Server response printed as table
            System.out.printf("Server Response: \n" +
                    "|%-15s |%-15s |%-20s |%-12s |" + "\n" +
                    "=======================================================================" + "\n", fName, lName, address, phNumber);
            for (int i = 0; i < memberArray.length;){
                System.out.printf("|%-15s ", memberArray[i]);
                System.out.printf("|%-15s ", memberArray[i+1]);
                System.out.printf("|%-20s ", memberArray[i+2]);
                System.out.printf("|%-12s |" + "\n", memberArray[i+3]);
                i = i + 4;
            }
            
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (aSocket != null) {
                aSocket.close();
            }
        }
    }
}
