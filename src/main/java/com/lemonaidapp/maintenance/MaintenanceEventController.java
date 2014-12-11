package com.lemonaidapp.maintenance;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MaintenanceEventController extends HttpServlet {

	private InMemoryMaintenanceEventRepo eventRepo;
	
	public MaintenanceEventController() {
		this.eventRepo = InMemoryMaintenanceEventRepo.getInstance();

        Random RANDOM = new Random(System.currentTimeMillis());
        MaintenanceEvent oilChange = new MaintenanceEvent();
        Calendar oilChangeCal = Calendar.getInstance();
        oilChangeCal.set(2014, 4, 15);
        oilChange.setDate(oilChangeCal.getTime());
        oilChange.setTask("Changed Oil");
        oilChange.setComments("4 Quarts Mobil1");
        oilChange.setMileage(12345);
        oilChange.setVehicleName("Subaru Impreza");
        oilChange.setId(RANDOM.nextInt(Integer.MAX_VALUE));
        this.eventRepo.createEvent(oilChange);

        MaintenanceEvent brakeWork = new MaintenanceEvent();
        Calendar brakeWorkCal = Calendar.getInstance();
        brakeWorkCal.set(2014, 4, 15);
        brakeWork.setDate(brakeWorkCal.getTime());
        brakeWork.setTask("Changed Brake Pads");
        brakeWork.setComments("Life expectancy: 60,000 miles");
        brakeWork.setVehicleName("Subaru Impreza");
        brakeWork.setMileage(67890);
        brakeWork.setId(RANDOM.nextInt(Integer.MAX_VALUE));
        this.eventRepo.createEvent(brakeWork);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		MaintenanceEvent newEvent = new MaintenanceEvent();
        newEvent.setDate(new Date());
        newEvent.setTask(req.getParameter("task"));
        newEvent.setComments(req.getParameter("comment"));
        newEvent.setMileage(Integer.MAX_VALUE);
        newEvent.setVehicleName(req.getParameter("vehicleName"));
        newEvent.setId(new Random(System.currentTimeMillis()).nextInt(Integer.MAX_VALUE));;
        
        this.eventRepo.createEvent(newEvent);
        
		List<MaintenanceEvent> events = this.eventRepo.findAllEvents();

        req.setAttribute("events", events);
        getServletContext().getRequestDispatcher("/maintenance/listing.jsp").forward(req, resp);
	}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        MaintenanceEvent event;
        int id = -1;
        
        if (req.getParameter("id") != null) {
        	id = Integer.parseInt(req.getParameter("id"));
            if (id == -1) {
                resp.sendError(500, "Error loading maintenance event");
            }
        }

        event = this.eventRepo.findEventById(id);
        req.setAttribute("event", event);
        getServletContext().getRequestDispatcher("/maintenance/event.jsp").forward(req, resp);
    }
}
