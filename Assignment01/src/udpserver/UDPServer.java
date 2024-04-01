/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package udpserver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tcpserver.Member;

/**
 *
 * @author User
 */
public class UDPServer {

    public static void main(String args[]) throws ClassNotFoundException {
        DatagramSocket aSocket = null;
        byte[] data = null;
        final byte[] buffer;

        //retrieve client message and return member data to client
        try {
            aSocket = new DatagramSocket(2230);
            buffer = new byte[1000];
            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);

                //Server recieves message from client
                System.out.println("Client Request: "
                        + new String(request.getData()));

                //if true will enter and prepare response to client
                if (Arrays.equals(request.getData(), request.getData())) {
                    data = memberDeserialization();
                }

                //reply is sent back
                DatagramPacket reply = new DatagramPacket(data, data.length,
                        request.getAddress(), request.getPort());

                aSocket.send(reply);
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

    //collects memberObjects in arrayList to send as byte to client
    public static byte[] memberDeserialization() throws IOException, ClassNotFoundException {
        //memberList List = new List();
        ArrayList<String> details = new ArrayList<>();

        final String fileName = "memberlistObject";
        Member member = null;
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream in = new ObjectInputStream(fis);
        //int i = 0;

        //loops through to add member Object in file to arraylist
        try {
            //ArrayList<Member> member = (ArrayList<Member>)in.readObject();
            //System.out.println(member);
            
            //member = (Member) in.readObject();
            while (fis.available() != 0) {
                member = (Member) in.readObject();
                if (member != null) {
                    //System.out.println(member.getFirstName());
                    //System.out.println();

                    details.add(member.getFirstName());
                    details.add(member.getLastName());
                    details.add(member.getAddress());
                    details.add(member.getPhNumber());
                    //i++;
                } 
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        //prints last member processed
//        System.out.println("Person First Name: " + member.getFirstName());
//        System.out.println("Person Last Name: " + member.getLastName());
//        System.out.println("Person Address: " + member.getAddress());
//        System.out.println("Person Phone Number: " + member.getPhNumber());

        StringBuilder msg = new StringBuilder();
        //System.out.println(msg);
        for (String eachstring : details) {

            // Each element in ArrayList followed by a colon
            // followed by colon
            msg.append(eachstring).append(":");
        }

        //StringBuilder becomes String
        String message = msg.toString();

        //last colon is removed
        if (message.length() > 0) {
            message = message.substring(0, message.length() - 1);
        }
        //System.out.println(message);

        //message is converted as byte and returned
        byte[] table = message.getBytes();
        in.close();
        return table;
    }
}
