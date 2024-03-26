/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tcpserver;

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
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPServer {

    public static void main(String args[]) {
        int interval = 2000; //Print to file every 2 seconds

        java.util.Timer timer = new java.util.Timer(); // using timer from util package

        //schedule timer to write to file after interval and repeat every interval
        timer.schedule(new UpdateMemberlistObject(), interval, interval);

        try {
            int serverPort = 1130;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            while (true) {
                //listening for client
                Socket clientSocket = listenSocket.accept();
                Connection c = new Connection(clientSocket);
                System.out.printf("\nServer waiting on: %d for client from %d " + "\n",
                        listenSocket.getLocalPort(), clientSocket.getPort());
            }
        } catch (IOException e) {
            System.out.println("Listen :" + e.getMessage());
        }
    }

}

class UpdateMemberlistObject extends TimerTask {

    //this method is called automatically when the task is scheduled
    public void run() {
        try {
            String member = Member.memberSerialization();
        } catch (IOException ex) {
            Logger.getLogger(UpdateMemberlistObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

//This class is to be used by TcpServer class, not public.
class Connection extends Thread {

    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;

    public Connection(Socket aClientSocket) {
        try {
            clientSocket = aClientSocket;
            in = new DataInputStream(
                    clientSocket.getInputStream());
            out = new DataOutputStream(
                    clientSocket.getOutputStream());
            this.start();
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        }
    }

    public void run() {
        try { // an echo server
            //data recieved
            String data = in.readUTF();

            //data saved to file
            writeToFile(data);

            //data is sent back to client
            out.writeUTF("Save Data of the member number: ");

        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {/*close failed*/
            }
        }
    }

    public void writeToFile(String data) throws IOException {
        Writer output;
        output = new BufferedWriter(new FileWriter("memberList.txt", true));
        output.append(data + "\n");
        output.close();
    }
}
