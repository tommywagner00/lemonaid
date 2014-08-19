package com.lemonaidapp.maintenance;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MaintenanceListingController extends HttpServlet {

	//private final Logger log = LoggerFactory.getLogger(MaintenanceListingController.class);

	private MaintenanceEventRepo eventRepo;
	
	public MaintenanceListingController() {
		this.eventRepo = MaintenanceEventRepo.getInstance();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<MaintenanceEvent> events = this.eventRepo.findAllEvents();
		
        req.setAttribute("events", events);
        getServletContext().getRequestDispatcher("/maintenance/listing.jsp").forward(req, resp);
	}
}
