/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contactbook;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAttribute;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Contact")
public class ContactResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String fname;
    private String lname;
    private String email;
    private String contact;
    private String city;
    private String message;

    public ContactResponse() {
    }

    public ContactResponse(int id , String Message) {
        this.id = id;
        this.message = Message;
        
    }
    
    public ContactResponse(String Message) {
        this.message = Message;
        
    }

    public ContactResponse(int id, String fname, String lname, String email, String contact, String city) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.contact = contact;
        this.city = city;
    }

    public String getMessage() {
        return message;
    }

    @XmlElement
    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    @XmlElement
    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    @XmlElement
    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    @XmlElement
    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    @XmlElement
    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    @XmlElement
    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCity() {
        return city;
    }

    @XmlElement
    public void setCity(String city) {
        this.city = city;
    }

}
