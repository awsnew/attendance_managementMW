package com.mw.controller;

import com.mw.datastore.InMemoryDatabase;
import com.mw.model.User;
import com.mw.model.UserAttendance;
import java.util.ArrayList;
import java.util.Collection;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/attendance")
public class AttendanceController {
    
    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public WebReponse register(User user) {
        WebReponse wp = new WebReponse();
        InMemoryDatabase.userDB.put(user.getUserId(), user);
        wp.setMessage("User Registered Successfully");
        wp.setStatusCode(200);
        return wp;
    }
    
    @GET
    @Path("/getUserAttendance/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public WebReponse getUserAttendance(@PathParam("userId") String userId) {
        WebReponse wp = new WebReponse();
        
        User usr = InMemoryDatabase.userDB.get(userId);
        if (usr != null) {
            wp.setMessage("Success");
            wp.setStatusCode(200);
            wp.setResponseData(usr);
        } else {
            wp.setMessage("UserID not available");
            wp.setStatusCode(400);
        }        
        return wp;
    }
    
    @GET
    @Path("/getAllUser")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public WebReponse getAllUser() {
        WebReponse wp = new WebReponse();
        
        Collection<User> collUser = InMemoryDatabase.userDB.values();
        ArrayList<User> users = new ArrayList<User>(collUser);
        
        wp.setMessage("Success");
        wp.setStatusCode(200);
        wp.setResponseData(users);
        
        return wp;
    }
    
    @POST
    @Path("/markAttendance")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public WebReponse markAttendance(UserAttendance userAttendance) {
        WebReponse wp = new WebReponse();
        User usr = InMemoryDatabase.userDB.get(userAttendance.getUserId());
        usr.getUserAttendance().add(userAttendance);
        
        InMemoryDatabase.userDB.put(usr.getUserId(), usr);
        
        wp.setMessage("Attendance Marked Successfully");
        wp.setStatusCode(200);
        return wp;
    }
    
}
