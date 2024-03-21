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
import java.util.Scanner;

/**
 *
 * @author User
 */
public class UDPClient {

    private static String Username;
    private static String hostName;

    public static void main(String args[]) {
        DatagramSocket aSocket = null;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your name: ");
        Username = input.nextLine();
        //you can uncomment and read the input to set hostname
        // System.out.print("Enter the Hostname,the computer IPaddress:");
        // hostName = input.nextLine();
        String message = "Hello to server from ";
        StringBuilder str = new StringBuilder();
        str.append(message);
        str.append(Username);
        hostName = "localhost";
        try {
            aSocket = new DatagramSocket();
            byte[] m = str.toString().getBytes();
            InetAddress aHost = InetAddress.getByName(hostName);
            int serverPort = 6789;
            //packet to send 		                                                 
            DatagramPacket request = new DatagramPacket(m,
                    str.length(), aHost, serverPort);
            aSocket.send(request);
            byte[] buffer = new byte[1000];
            // packet to receive message, specify, array and array size
            DatagramPacket reply = new DatagramPacket(buffer,
                    buffer.length);
            aSocket.receive(reply);
            System.out.println("Server Response: " + "\n" 
                    + "|First Name    |Last Name      |Address        |Phone Number   |" + "\n"
                    + "================================================================" + "\n"
                    + new String(reply.getData()));
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }

    
//        finally {
//              if (aSocket != null) {
//            aSocket.close();
//        }
//    }
}
