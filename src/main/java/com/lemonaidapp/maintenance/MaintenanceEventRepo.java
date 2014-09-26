package com.lemonaidapp.maintenance;

import java.util.*;

/**
 * Created by ddcdanter on 7/30/14.
 */
public class MaintenanceEventRepo {

	private Random RANDOM = new Random(System.currentTimeMillis());
	private static MaintenanceEventRepo instance;
	
	private Map<Integer, MaintenanceEvent> events = new HashMap<Integer, MaintenanceEvent>();
	
	private MaintenanceEventRepo() {
        MaintenanceEvent oilChange = new MaintenanceEvent();
        Calendar oilChangeCal = Calendar.getInstance();
        oilChangeCal.set(2014, 4, 15);
        oilChange.setDate(oilChangeCal.getTime());
        oilChange.setTask("Changed Oil");
        oilChange.setComments("4 Quarts Mobil1");
        oilChange.setMileage(12345);
        oilChange.setVehicleName("Subaru Impreza");
        oilChange.setId(RANDOM.nextInt(Integer.MAX_VALUE));
        events.put(oilChange.getId(), oilChange);

        MaintenanceEvent brakeWork = new MaintenanceEvent();
        Calendar brakeWorkCal = Calendar.getInstance();
        brakeWorkCal.set(2014, 4, 15);
        brakeWork.setDate(brakeWorkCal.getTime());
        brakeWork.setTask("Changed Brake Pads");
        brakeWork.setComments("Life expectancy: 60,000 miles");
        brakeWork.setVehicleName("Subaru Impreza");
        brakeWork.setMileage(67890);
        brakeWork.setId(RANDOM.nextInt(Integer.MAX_VALUE));
        events.put(brakeWork.getId(), brakeWork);
	}
	
	public static MaintenanceEventRepo getInstance() {
		if (instance == null) {
			instance = new MaintenanceEventRepo();
		}
		
		return instance;
	}
	
	public List<MaintenanceEvent> findAllEvents() {
		return new ArrayList<MaintenanceEvent>(this.events.values());
	}
	
	public MaintenanceEvent findEventById(int id) {
        return this.events.get(id);
	}
	
	public List<MaintenanceEvent> findEventsForVehicle(String vehicleName) {
		// TODO: implement findEventsForVehicle
        List<MaintenanceEvent> list = new ArrayList<MaintenanceEvent>(this.events.values());
        List<MaintenanceEvent> list2 = new ArrayList<MaintenanceEvent>();
        for (MaintenanceEvent event : list) {
            if (event.getVehicleName().equals(vehicleName)){
                list2.add(event);
            }
        }
		return list2;
	}

    public List<MaintenanceEvent> findEventsByTask(String task) {
        List<MaintenanceEvent> list = new ArrayList<MaintenanceEvent>(this.events.values());
        List<MaintenanceEvent> list2 = new ArrayList<MaintenanceEvent>();
        for (MaintenanceEvent event : list) {
            if (event.getTask().equals(task)){
                list2.add(event);
            }
        }
        return list2;
    }

    public List<MaintenanceEvent> findEventsByMileage(int mileage) {
        List<MaintenanceEvent> list = new ArrayList<MaintenanceEvent>(this.events.values());
        List<MaintenanceEvent> list2 = new ArrayList<MaintenanceEvent>();
        for (MaintenanceEvent event : list) {
            if (event.getMileage() == mileage){
                list2.add(event);
            }
        }
        return list2;
    }
	
	public void createEvent(MaintenanceEvent event) {
		this.events.put(event.getId(), event);
	}
}
