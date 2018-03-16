package com.tabular.tabular.endpoint;

import com.tabular.tabular.entity.Appointment2User;
import com.tabular.tabular.service.Appointment2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/appointment-user")
public class Appointment2UserController {

    private final Appointment2UserService appointment2UserService;

    @Autowired
    public Appointment2UserController(Appointment2UserService appointment2UserService) {
        this.appointment2UserService = appointment2UserService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public long signUp(long appointmentId, long userId) {
        return appointment2UserService.createRelation(appointmentId, userId);
    }

    @RequestMapping(value = "/:id", method = RequestMethod.GET)
    public Appointment2User getRelationByRelationId(@RequestParam("id") long relationId) {
        return appointment2UserService.queryRelationByRelationId(relationId);
    }

    @RequestMapping(value = "/list:appointmentId", method = RequestMethod.GET)
    public List<Appointment2User> getRelationByAppointmentId(@RequestParam("appointmentId") long appointmentId) {
        return appointment2UserService.queryByAppointmentId(appointmentId);
    }

    @RequestMapping(value = "/list:userId", method = RequestMethod.GET)
    public List<Appointment2User> getRelationByUserId(@RequestParam("userId") long userId) {
        return appointment2UserService.queryByUserId(userId);
    }

}
