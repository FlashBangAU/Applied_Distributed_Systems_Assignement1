/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tcpclient;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package TCPConnection;
/**
 *
 * @author Mary
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class TCPClient {

    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);

        boolean exit = false;
        String strAns = null;

        ArrayList<String> details = new ArrayList<>();

        //arguments supply message and hostname of destination
        //if running from a command prompt
        Socket s = null;
        String hostName = "localhost";
        String message = "cheese";
        int i = 1;

        try {
            while (exit == false) {
                details.clear();

                //acquire member details
                System.out.println("Enter Detail for Member Number:" + i);
                System.out.println("Enter your First Name:");
                strAns = input.nextLine();
                details.add(strAns);

                System.out.println("Enter your Last Name:");
                strAns = input.nextLine();
                details.add(strAns);

                System.out.println("Enter your Address:");
                strAns = input.nextLine();
                details.add(strAns);

                System.out.println("Enter your Phone Number:");
                strAns = input.nextLine();
                details.add(strAns);

                StringBuilder str = new StringBuilder("");

                // Traversing the ArrayList
                for (String eachstring : details) {

                    // Each element in ArrayList is appended
                    // followed by comma
                    str.append(eachstring).append(":");
                }

                message = str.toString();

                if (message.length() > 0) {
                    message = message.substring(0, message.length() - 1);
                }

                int serverPort = 7896;

                s = new Socket(hostName, serverPort);
                DataInputStream in = new DataInputStream(s.getInputStream());
                DataOutputStream out = new DataOutputStream(s.getOutputStream());

                //send message
                out.writeUTF(message);
                System.out.println("Sending Data to Server..............");

                //server response
                String data = in.readUTF();
                System.out.println("Server Response: " + data + i);

                System.out.println("Exit Program Y/N: ");
                strAns = input.nextLine();
                if("y".equals(strAns) || "Y".equals(strAns))
                    exit = true;
                System.out.println("-----------------------------------");

                i++;
            }
        } catch (UnknownHostException e) {
            System.out.println("Sock:" + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } finally {
            if (s != null)
	     try {
                s.close();
            } catch (IOException e) {
                System.out.println("close:" + e.getMessage());
            }
        }
    }
}
