/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contactbook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tripti
 */
public class ContactDao {

    public List<ContactResponse> create(ContactRequest Crequest) {
        List<ContactResponse> contactList = new ArrayList<ContactResponse>();
        int id=0;
        try {
            DBConnection dbconnect = new DBConnection();
            Connection con = dbconnect.getDBConnection();
            if (con != null) {

                String insertTableSQL = "INSERT INTO contactbook"
                        + "(First_Name, Last_Name, Email_ID, Contact_Number, City) VALUES"
                        + "(?,?,?,?,?);";
                PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
                preparedStatement.setString(1, Crequest.getFname());
                preparedStatement.setString(2, Crequest.getLname());
                preparedStatement.setString(3, Crequest.getEmail());
                preparedStatement.setString(4, Crequest.getContact());
                preparedStatement.setString(5, Crequest.getCity());
                // execute insert SQL stetement
                preparedStatement.executeUpdate();
                
                Statement st = con.createStatement();
                String sql="select LAST_INSERT_ID()";
                ResultSet rs=st.executeQuery(sql);
                rs.next();
                id = rs.getInt(1);
                System.out.println("ID is "+rs.getInt(1));
                

            } else {
                System.out.println("Failed to connect!!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            String str = e.getMessage();
            ContactResponse c = new ContactResponse(id,str);
            contactList.add(c);
            return contactList;

        }
        ContactResponse c = new ContactResponse(id,"Contact Created");
        contactList.add(c);
        return contactList;
    }


    List<ContactResponse> getSingleContact(int id) {
        List<ContactResponse> contactList = new ArrayList<ContactResponse>();
        try {
            DBConnection dbconnect = new DBConnection();
            Connection con = dbconnect.getDBConnection();
            if (con != null) {
                //DB connection established
                System.out.print(id);
                String insertTableSQL = "(select * from contactbook where ID=?)";
                PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
                preparedStatement.setInt(1, id);
                // execute insert SQL stetement
                ResultSet rs = preparedStatement.executeQuery();
                int counter = 0;
                while (rs.next()) {
                    System.out.print("record");
                    ContactResponse cr = new ContactResponse(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                    contactList.add(cr);
                    counter++;
                }

                if (counter == 0) {
                    //user record does not exists
                    System.out.print("No record");
                    ContactResponse CR = new ContactResponse(id , "No records Exists");
                    contactList.add(CR);

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return contactList;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    List<ContactResponse> getContacts(int id, int start, int length, int index) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<ContactResponse> contactList = new ArrayList<ContactResponse>();
        try {
            DBConnection dbconnect = new DBConnection();
            Connection con = dbconnect.getDBConnection();
            if (con != null) {
                System.out.println("DB done!");
                //start+length*index to start+length*index+length-1
                int pageStart = start + length * (index - 1);
                String insertTableSQL = "select * from contactbook limit ? offset ?";
                PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
                preparedStatement.setInt(1, length);
                preparedStatement.setInt(2, pageStart);
                System.out.print(length);
                System.out.print(pageStart);
                // execute insert SQL stetement
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    System.out.print("record");
                    ContactResponse cr = new ContactResponse(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                    contactList.add(cr);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contactList;
    }

    public List<ContactResponse> updatecon(ContactRequest Urequest) {
        int ID = Urequest.getId();
        List<ContactResponse> contactList = new ArrayList<ContactResponse>();
        try {
            DBConnection dbconnect = new DBConnection();
            Connection con = dbconnect.getDBConnection();
            String fname = "", lname = "", contact = "", email = "", city = "";
            
            String temp = "";

            if (con != null) {

                if (Urequest.getFname() != null) {
                    fname = Urequest.getFname();
                    temp += "First_Name=?,";

                }
                if (Urequest.getLname() != null) {
                    fname = Urequest.getLname();
                    temp += "Last_Name=?,";
                }
                if (Urequest.getEmail() != null) {
                    fname = Urequest.getEmail();
                    temp += "Email_ID=?,";
                }
                if (Urequest.getContact() != null) {
                    fname = Urequest.getContact();
                    temp += "Contact_Number=?,";
                }
                if (Urequest.getCity() != null) {
                    fname = Urequest.getCity();
                    temp += "City=?,";
                }

                temp = temp.substring(0, temp.length() - 1);

                String sqlQuery = "update contactbook set " + temp + " where ID=?";
                System.out.print(sqlQuery);
                PreparedStatement preparedStatement = con.prepareStatement(sqlQuery);
                int queryCounter = 0;
                if (Urequest.getFname() != null) {
                    queryCounter++;
                    preparedStatement.setString(queryCounter, Urequest.getFname());
                }
                if (Urequest.getLname() != null) {
                    queryCounter++;
                    preparedStatement.setString(queryCounter, Urequest.getLname());
                }
                if (Urequest.getEmail() != null) {
                    queryCounter++;
                    preparedStatement.setString(queryCounter, Urequest.getEmail());
                }
                if (Urequest.getContact() != null) {
                    queryCounter++;
                    preparedStatement.setString(queryCounter, Urequest.getContact());
                }
                if (Urequest.getCity() != null) {
                    queryCounter++;
                    preparedStatement.setString(queryCounter, Urequest.getCity());
                }
                preparedStatement.setInt(++queryCounter, Urequest.getId());
                int rs = preparedStatement.executeUpdate();
                if (rs > 0) {
                    ContactResponse c = new ContactResponse(ID , "Contact Updated");
                    contactList.add(c);
                    return contactList;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            String str = e.getMessage();
            ContactResponse c = new ContactResponse(ID ,str);
            contactList.add(c);
            return contactList;
        }
        ContactResponse c = new ContactResponse(ID,"No such ID exsits");
        contactList.add(c);
        return contactList;
    }

    List<ContactResponse> delete(int id) {
        List<ContactResponse> contactList = new ArrayList<ContactResponse>();
        try {
            DBConnection dbconnect = new DBConnection();
            Connection con = dbconnect.getDBConnection();
            System.out.print("delete");
            if (con != null) {
                String insertTableSQL = "delete from contactbook where ID=?";
                PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
                preparedStatement.setInt(1, id);
                int rs = preparedStatement.executeUpdate();
                if (rs > 0) {
                    ContactResponse c = new ContactResponse(id,"Contact Deleted");
                    contactList.add(c);
                    return contactList;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            String str = e.getMessage();
            ContactResponse c = new ContactResponse(id,str);
            contactList.add(c);
            return contactList;
        }
        ContactResponse c = new ContactResponse(id,"No such contact ID found");
        contactList.add(c);
        return contactList;
    }

}
