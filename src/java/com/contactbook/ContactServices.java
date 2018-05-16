/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contactbook;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/Contacts")
public class ContactServices {

    /*GET*/
    ContactDao contactdao = new ContactDao();

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<ContactResponse> getUsers(@DefaultValue("0") @QueryParam("id") String id,
            @QueryParam("start") int start,
            @DefaultValue("10") @QueryParam("length") String length,
            @DefaultValue("1") @QueryParam("index") String index) {
        int pageLength = Integer.parseInt(length);
        int pageIndex = Integer.parseInt(index);
        int userid=Integer.parseInt(id);
        if (userid != 0) {  //url?id=10
            return contactdao.getSingleContact(userid);
        } else if (userid == 0 && start == 0) {  //url
            //Error
            return contactdao.getSingleContact(userid);
        } else if (userid == 0) {
            return contactdao.getContacts(userid, start, pageLength, pageIndex);
        }
        return null;

    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public List<ContactResponse> createContact(ContactRequest Crequest)
    {
        return contactdao.create(Crequest);
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public List<ContactResponse> updateContact(ContactRequest Urequest){
        return contactdao.updatecon(Urequest);
        
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public List<ContactResponse> deletecontact(@PathParam("id") int id){
        System.out.print("sahihe");
        return contactdao.delete(id);
    }

}
