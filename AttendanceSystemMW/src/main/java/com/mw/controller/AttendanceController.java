package com.mw.controller;

import com.mw.datastore.InMemoryDatabase;
import com.mw.model.User;
import com.mw.model.UserAttendance;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
    public WebReponse addLight(User user) {
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
    public WebReponse getLight(@PathParam("userId") String userId) {
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
    public WebReponse getAllLight() {
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
        System.out.println("----** MW mark Attendance " + userAttendance.getUserId() + " Status  " + userAttendance.getStatus());
        WebReponse wp = new WebReponse();
        User usr = InMemoryDatabase.userDB.get(userAttendance.getUserId());
        if (usr != null) {
            List<UserAttendance> userAttend = usr.getUserAttendance();
            for (UserAttendance userAttendance1 : userAttend) {
                if (userAttendance1.getAttendanceDate().equals(userAttendance.getAttendanceDate())) {
                    wp.setMessage("User Attendance for date " + userAttendance.getAttendanceDate() + " is marked already");
                    wp.setStatusCode(400);
                    return wp;
                }
            }
           userAttendance.setStatus("Presente");
            usr.getUserAttendance().add(userAttendance);

            InMemoryDatabase.userDB.put(usr.getUserId(), usr);

            wp.setMessage("Attendance Marked Successfully");
            wp.setStatusCode(200);
            return wp;
        } else {
            wp.setMessage("User not registered in system");
            wp.setStatusCode(200);
            return wp;
        }
    }

}
