package com.tabular.tabular.endpoint;

import com.tabular.tabular.entity.Appointment;
import com.tabular.tabular.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public long createAppointment(Date datetime, int status, long tableId) {
        return appointmentService.createAppointment(datetime, status, tableId);
    }

    @RequestMapping(value = "/:id", method = RequestMethod.GET)
    public Appointment getAppointmentById(@RequestParam("id") long appointmentId) {
        return appointmentService.queryAppointmentById(appointmentId);
    }

    @RequestMapping(value = "/list:timeRange", method = RequestMethod.GET)
    public List<Appointment> getAppointmentWithinTimeRange(@RequestParam("appointmentId") long appointmentId, @RequestParam("startTime") Date startTime, @RequestParam("endTime") Date endTime) {
        return appointmentService.queryAppointmentByTimeRange(appointmentId, startTime, endTime);
    }
}
