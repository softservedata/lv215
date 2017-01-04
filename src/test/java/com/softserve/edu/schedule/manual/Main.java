package com.softserve.edu.schedule.manual;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.softserve.edu.schedule.entity.Location;
import com.softserve.edu.schedule.entity.Room;
import com.softserve.edu.schedule.entity.RoomEquipment;
import com.softserve.edu.schedule.service.RoomService;

public class Main {

    public static void main(String[] args) {

        @SuppressWarnings("resource")
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:/WEB-INF/applicationContext.xml");
        ctx.refresh();
        RoomService roomService = ctx.getBean("roomService", RoomService.class);
        EntityManagerFactory emf = ctx.getBean("entityManagerFactory",
                EntityManagerFactory.class);
        EntityManager em = emf.createEntityManager();

        RoomEquipment roomEquipment1 = em.find(RoomEquipment.class, 1L);
        RoomEquipment roomEquipment2 = em.find(RoomEquipment.class, 2L);
        RoomEquipment roomEquipment3 = em.find(RoomEquipment.class, 3L);
        RoomEquipment roomEquipment4 = em.find(RoomEquipment.class, 4L);
        Location location1 = em.find(Location.class, 1L);
        Location location2 = em.find(Location.class, 2L);
        Location location3 = em.find(Location.class, 3L);
        Location location4 = em.find(Location.class, 4L);

        System.out.println("Rooms before test");
        for (Room room : roomService.findAll()) {
            System.out.println("Room name: " + room.getName()
                    + ", Room Capacity: " + room.getCapacity());
        }

        System.out.println("Creating room Room1");
        Room room1 = new Room();
        room1.setLocation(location1);
        room1.setName("Room1");
        room1.setCapacity(50);
        List<RoomEquipment> equipments = new ArrayList<>();
        equipments.add(roomEquipment1);
        equipments.add(roomEquipment2);
        room1.setEquipments(equipments);
        roomService.add(room1);

        System.out.println("Rooms after creating Room1");
        for (Room room : roomService.findAll()) {
            System.out.println("Room name: " + room.getName()
                    + ", Room Capacity: " + room.getCapacity());
        }

        System.out.println("Creating room Room2");
        Room room2 = new Room();
        room2.setLocation(location2);
        room2.setName("Room2");
        room2.setCapacity(10);
        equipments = new ArrayList<>();
        equipments.add(roomEquipment3);
        equipments.add(roomEquipment4);
        room2.setEquipments(equipments);
        roomService.add(room2);

        System.out.println("Rooms after creating Room2");
        for (Room room : roomService.findAll()) {
            System.out.println("Room name: " + room.getName()
                    + ", Room Capacity: " + room.getCapacity());
        }

        System.out.println("Creating room Room3");
        Room room3 = new Room();
        room3.setLocation(location3);
        room3.setName("Room3");
        room3.setCapacity(25);
        equipments = new ArrayList<>();
        equipments.add(roomEquipment1);
        equipments.add(roomEquipment3);
        equipments.add(roomEquipment4);
        room3.setEquipments(equipments);
        roomService.add(room3);

        System.out.println("Rooms after creating Room3");
        for (Room room : roomService.findAll()) {
            System.out.println("Room name: " + room.getName()
                    + ", Room Capacity: " + room.getCapacity());
        }

        System.out.println("Creating room Room4");
        Room room4 = new Room();
        room4.setLocation(location3);
        room4.setName("Room4");
        room4.setCapacity(8);
        roomService.add(room4);

        System.out.println("Rooms after creating Room4");
        for (Room room : roomService.findAll()) {
            System.out.println("Room name: " + room.getName()
                    + ", Room Capacity: " + room.getCapacity());
        }

        System.out.println("Updating info about Room4");
        Room updated = new Room();
        updated.setLocation(location4);
        updated.setName("Room4upd");
        updated.setCapacity(9);
        equipments = new ArrayList<>();
        equipments.add(roomEquipment1);
        updated.setEquipments(equipments);
        roomService.update(room4, updated);

        System.out.println(
                "Detailed information about rooms after room 4 info update");
        for (Room room : roomService.findAllWithDetails()) {
            System.out.println(
                    "Room name: " + room.getName() + ", Room Capacity: "
                            + room.getCapacity() + ", Room ID: " + room.getId()
                            + ", Room Location: " + room.getLocation().getName()
                            + " " + room.getLocation().getAddress());
            System.out.print("Room equipment: ");
            for (RoomEquipment roomEquipment : room.getEquipments()) {
                System.out.print(roomEquipment.getName() + " ");
            }
            System.out.println();
        }

        System.out.println("Find rooms by equipment (X-Box, PS4)");
        equipments = new ArrayList<>();
        equipments.add(roomEquipment3);
        equipments.add(roomEquipment4);
        for (Room room : roomService.findRoomsWithEquipments(equipments)) {
            System.out.println(
                    "Room name: " + room.getName() + ", Room Capacity: "
                            + room.getCapacity() + ", Room ID: " + room.getId()
                            + ", Room Location: " + room.getLocation().getName()
                            + " " + room.getLocation().getAddress());
            System.out.print("Room equipment: ");
            for (RoomEquipment roomEquipment : room.getEquipments()) {
                System.out.print(roomEquipment.getName() + " ");
            }
            System.out.println();
        }

        System.out.println("Find rooms by capacity 10-50");
        for (Room room : roomService.findRoomsByCapacity(10, 50)) {
            System.out.println(
                    "Room name: " + room.getName() + ", Room Capacity: "
                            + room.getCapacity() + ", Room ID: " + room.getId()
                            + ", Room Location: " + room.getLocation().getName()
                            + " " + room.getLocation().getAddress());
            System.out.print("Room equipment: ");
            for (RoomEquipment roomEquipment : room.getEquipments()) {
                System.out.print(roomEquipment.getName() + " ");
            }
            System.out.println();
        }

        System.out.println("Find rooms by Location3");
        for (Room room : roomService.findRoomsByLocation(location3)) {
            System.out.println(
                    "Room name: " + room.getName() + ", Room Capacity: "
                            + room.getCapacity() + ", Room ID: " + room.getId()
                            + ", Room Location: " + room.getLocation().getName()
                            + " " + room.getLocation().getAddress());
            System.out.print("Room equipment: ");
            for (RoomEquipment roomEquipment : room.getEquipments()) {
                System.out.print(roomEquipment.getName() + " ");
            }
            System.out.println();
        }

        System.out.println("Find rooms by Name - Room2");
        Room finded = roomService.findByName("Room2");
        System.out
                .println("Room name: " + finded.getName() + ", Room Capacity: "
                        + finded.getCapacity() + ", Room ID: " + finded.getId()
                        + ", Room Location: " + finded.getLocation().getName()
                        + " " + finded.getLocation().getAddress());

        System.out.println("Delete room Room1");
        roomService.delete(room1);
        System.out.println("Rooms after deleting Room1");
        for (Room room : roomService.findAll()) {
            System.out.println("Room name: " + room.getName()
                    + ", Room Capacity: " + room.getCapacity());
        }

        System.out.println("Clear testdata from database");
        for (Room room : roomService.findAllWithDetails()) {
            roomService.delete(room);
        }
        System.out.println("Rooms after test");
        for (Room room : roomService.findAll()) {
            System.out.println("Room name: " + room.getName()
                    + ", Room Capacity: " + room.getCapacity());
        }

    }

}
