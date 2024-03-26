/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tcpserver;

/**
 *
 * @author User
 */
public class Member {
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

    @Override
    public String toString() {
        return (firstName +":"+ lastName +":" + address + ":" + phNumber);
    }
    
    
}
