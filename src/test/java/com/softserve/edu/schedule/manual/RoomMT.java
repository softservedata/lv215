package com.softserve.edu.schedule.manual;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.softserve.edu.schedule.dto.LocationDTO;
import com.softserve.edu.schedule.dto.RoomDTO;
import com.softserve.edu.schedule.dto.RoomEquipmentDTO;
import com.softserve.edu.schedule.service.RoomService;

public class RoomMT {

    public static void main(String[] args) {

        @SuppressWarnings("resource")
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:/WEB-INF/applicationContext.xml");
        ctx.refresh();
        RoomService roomService = ctx.getBean("roomService", RoomService.class);

        RoomEquipmentDTO roomEquipment1 = new RoomEquipmentDTO();
        roomEquipment1.setId(1L);
        roomEquipment1.setName("Projector");
        RoomEquipmentDTO roomEquipment2 = new RoomEquipmentDTO();
        roomEquipment2.setId(2L);
        roomEquipment2.setName("Computer");
        RoomEquipmentDTO roomEquipment3 = new RoomEquipmentDTO();
        roomEquipment3.setId(3L);
        roomEquipment2.setName("X-Box");
        RoomEquipmentDTO roomEquipment4 = new RoomEquipmentDTO();
        roomEquipment4.setId(4L);
        roomEquipment2.setName("PS4");
        LocationDTO location1 = new LocationDTO();
        location1.setId(1L);
        LocationDTO location2 = new LocationDTO();
        location2.setId(2L);
        LocationDTO location3 = new LocationDTO();
        location3.setId(3L);
        LocationDTO location4 = new LocationDTO();
        location4.setId(4L);

        System.out.println("Rooms before test");
        for (RoomDTO room : roomService.getAllWithDetails()) {
            System.out.println("Room name: " + room.getName()
                    + ", Room Capacity: " + room.getCapacity());
        }

        System.out.println("Creating room Room1");
        RoomDTO room1 = new RoomDTO();
        room1.setLocation(location1);
        room1.setName("Room1");
        room1.setCapacity("50");
        List<RoomEquipmentDTO> equipments = new ArrayList<>();
        equipments.add(roomEquipment1);
        equipments.add(roomEquipment2);
        room1.setEquipments(equipments);
        roomService.create(room1);

        System.out.println("Rooms after creating Room1");
        for (RoomDTO room : roomService.getAllWithDetails()) {
            System.out.println("Room name: " + room.getName()
                    + ", Room Capacity: " + room.getCapacity());
        }

        System.out.println("Creating room Room2");
        RoomDTO room2 = new RoomDTO();
        room2.setLocation(location2);
        room2.setName("Room2");
        room2.setCapacity("10");
        equipments = new ArrayList<>();
        equipments.add(roomEquipment3);
        equipments.add(roomEquipment4);
        room2.setEquipments(equipments);
        roomService.create(room2);

        System.out.println("Rooms after creating Room2");
        for (RoomDTO room : roomService.getAllWithDetails()) {
            System.out.println("Room name: " + room.getName()
                    + ", Room Capacity: " + room.getCapacity());
        }

        System.out.println("Creating room Room3");
        RoomDTO room3 = new RoomDTO();
        room3.setLocation(location3);
        room3.setName("Room3");
        room3.setCapacity("25");
        equipments = new ArrayList<>();
        equipments.add(roomEquipment1);
        equipments.add(roomEquipment3);
        equipments.add(roomEquipment4);
        room3.setEquipments(equipments);
        roomService.create(room3);

        System.out.println("Rooms after creating Room3");
        for (RoomDTO room : roomService.getAllWithDetails()) {
            System.out.println("Room name: " + room.getName()
                    + ", Room Capacity: " + room.getCapacity());
        }

        System.out.println("Creating room Room4");
        RoomDTO room4 = new RoomDTO();
        room4.setLocation(location3);
        room4.setName("Room4");
        room4.setCapacity("8");
        roomService.create(room4);

        System.out.println("Rooms after creating Room4");
        for (RoomDTO room : roomService.getAllWithDetails()) {
            System.out.println("Room name: " + room.getName()
                    + ", Room Capacity: " + room.getCapacity());
        }

        System.out.println("Updating info about Room4");
        room4.setId(roomService.getByName("Room4").getId());
        room4.setLocation(location4);
        room4.setName("Room4upd");
        room4.setCapacity("9");
        equipments = new ArrayList<>();
        equipments.add(roomEquipment1);
        room4.setEquipments(equipments);
        roomService.update(room4);

        System.out.println(
                "Detailed information about rooms after room 4 info update");
        for (RoomDTO room : roomService.getAllWithDetails()) {
            System.out.println(
                    "Room name: " + room.getName() + ", Room Capacity: "
                            + room.getCapacity() + ", Room ID: " + room.getId()
                            + ", Room Location: " + room.getLocation().getName()
                            + " " + room.getLocation().getAddress());
            System.out.print("Room equipment: ");
            for (RoomEquipmentDTO roomEquipment : room.getEquipments()) {
                System.out.print(roomEquipment.getName() + " ");
            }
            System.out.println();
        }

        System.out.println("Find rooms by equipment (X-Box, PS4)");
        equipments = new ArrayList<>();
        equipments.add(roomEquipment3);
        equipments.add(roomEquipment4);
        for (RoomDTO room : roomService.getRoomsWithEquipments(equipments)) {
            System.out.println(
                    "Room name: " + room.getName() + ", Room Capacity: "
                            + room.getCapacity() + ", Room ID: " + room.getId()
                            + ", Room Location: " + room.getLocation().getName()
                            + " " + room.getLocation().getAddress());
            System.out.print("Room equipment: ");
            for (RoomEquipmentDTO roomEquipment : room.getEquipments()) {
                System.out.print(roomEquipment.getName() + " ");
            }
            System.out.println();
        }

        System.out.println("Find rooms by capacity 10-50");
        for (RoomDTO room : roomService.getRoomsByCapacity(10, 50)) {
            System.out.println(
                    "Room name: " + room.getName() + ", Room Capacity: "
                            + room.getCapacity() + ", Room ID: " + room.getId()
                            + ", Room Location: " + room.getLocation().getName()
                            + " " + room.getLocation().getAddress());
            System.out.print("Room equipment: ");
            for (RoomEquipmentDTO roomEquipment : room.getEquipments()) {
                System.out.print(roomEquipment.getName() + " ");
            }
            System.out.println();
        }

        System.out.println("Find rooms by Location3");
        for (RoomDTO room : roomService.getRoomsByLocation(location3)) {
            System.out.println(
                    "Room name: " + room.getName() + ", Room Capacity: "
                            + room.getCapacity() + ", Room ID: " + room.getId()
                            + ", Room Location: " + room.getLocation().getName()
                            + " " + room.getLocation().getAddress());
            System.out.print("Room equipment: ");
            for (RoomEquipmentDTO roomEquipment : room.getEquipments()) {
                System.out.print(roomEquipment.getName() + " ");
            }
            System.out.println();
        }

        System.out.println("Find rooms by Name - Room2");
        RoomDTO finded = roomService.getByName("Room2");
        System.out
                .println("Room name: " + finded.getName() + ", Room Capacity: "
                        + finded.getCapacity() + ", Room ID: " + finded.getId()
                        + ", Room Location: " + finded.getLocation().getName()
                        + " " + finded.getLocation().getAddress());

        System.out.println("Delete room Room1");
        room1.setId(roomService.getByName("Room1").getId());
        roomService.delete(room1);
        System.out.println("Rooms after deleting Room1");
        for (RoomDTO room : roomService.getAllWithDetails()) {
            System.out.println(
                    "Room name: " + room.getName() + ", Room Capacity: "
                            + room.getCapacity() + ", Room ID: " + room.getId()
                            + ", Room Location: " + room.getLocation().getName()
                            + " " + room.getLocation().getAddress());
            System.out.print("Room equipment: ");
            for (RoomEquipmentDTO roomEquipment : room.getEquipments()) {
                System.out.print(roomEquipment.getName() + " ");
            }
            System.out.println();
        }
        /*System.out.println("Clear testdata from database");
        for (RoomDTO room : roomService.getAllWithDetails()) {
            roomService.deleteById(room.getId());
        }*/
        System.out.println("Rooms after test");
        for (RoomDTO room : roomService.getAllWithDetails()) {
            System.out.println("Room name: " + room.getName()
                    + ", Room Capacity: " + room.getCapacity());
        }

    }

}
