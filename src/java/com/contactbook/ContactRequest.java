/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contactbook;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAttribute;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Contact")
public class ContactRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    private String fname;
    private String lname;
    private String email;
    private String contact;
    private String city;
    private int id;

    public ContactRequest() {
    }

    public ContactRequest(int id, String fname, String lname, String email, String contact, String city) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.contact = contact;
        this.city = city;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
