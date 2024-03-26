/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tcpserver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import static java.lang.System.out;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class Member implements Serializable {

    String firstName;
    String lastName;
    String address;
    String phNumber;

    public Member(String firstName, String lastName, String address, String phNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phNumber = phNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhNumber() {
        return phNumber;
    }

    public void setPhNumber(String phNumber) {
        this.phNumber = phNumber;
    }

    public static String memberSerialization() throws IOException {
        int i = 0;
        ObjectOutputStream out = null;

        String filename = "memberlistObject";
        
        FileOutputStream fos = null;

        //out.reset();

        try {
            File myObj = new File("memberList.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                if (i==0){
                fos = new FileOutputStream(filename, false);
                }else{
                    fos = new FileOutputStream(filename, true);
                }
                i++;
                out = new ObjectOutputStream(fos);

                String data = myReader.nextLine();
                System.out.println(data);

                String[] memberArray = data.split(":");

                Member member1 = new Member(memberArray[0], memberArray[1], memberArray[2], memberArray[3]);

                try {
                    out.writeObject(member1);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
            out.close();
            System.out.println("Object Persisted");
            System.out.println();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UpdateMemberlistObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
}
