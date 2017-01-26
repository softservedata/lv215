package com.softserve.edu.schedule.service.implementation.dtoconverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.edu.schedule.dao.MeetingDAO;
import com.softserve.edu.schedule.dao.UserDAO;
import com.softserve.edu.schedule.dto.MeetingDTO;
import com.softserve.edu.schedule.dto.UserDTO;
import com.softserve.edu.schedule.dto.UserGroupDTO;
import com.softserve.edu.schedule.entity.UserGroup;

@Service
public class UserGroupDTOConverter {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private MeetingDAO meetingDAO;

	@Autowired
	private SubjectDTOConverter subjectConverter;

	@Autowired
	private UserDTOConverter userConverter;

	@Autowired
	private RoomDTOConverter roomConverter;

	public UserGroup getEntity(final UserGroupDTO userGroupDTO) {
		UserGroup userGroup = new UserGroup();

		userGroup.setId(userGroupDTO.getId());
		userGroup.setName(userGroupDTO.getName());
		userGroup.setDescription(userGroupDTO.getDescription());
		userGroup.setLevel(userGroupDTO.getLevel());
		userGroup.setCurator(userDAO.getById(userGroupDTO.getCurator().getId()));
		userGroupDTO.getUsers().forEach(e -> userGroup.getUsers().add(userDAO.getById(e.getId())));
		userGroupDTO.getMeetings().forEach(e -> userGroup.getMeetings().add(meetingDAO.getById(e.getId())));

		return userGroup;
	}

	public UserGroupDTO getDTO(final UserGroup userGroup) {
		UserGroupDTO userGroupDTO = new UserGroupDTO();
		userGroupDTO.setId(userGroup.getId());
		userGroupDTO.setName(userGroup.getName());
		userGroupDTO.setDescription(userGroup.getDescription());
		userGroupDTO.setLevel(userGroup.getLevel());

		UserDTO curator = new UserDTO();
		curator.setId(userGroup.getCurator().getId());
		curator.setFirstName(userGroup.getCurator().getFirstName());
		curator.setLastName(userGroup.getCurator().getLastName());
		userGroupDTO.setCurator(curator);

		userGroup.getUsers().forEach(e -> {
			UserDTO user = new UserDTO();
			user.setId(e.getId());
			user.setFirstName(e.getFirstName());
			user.setLastName(e.getLastName());
			userGroupDTO.getUsers().add(user);
		});

		userGroup.getMeetings().forEach(e -> {
			MeetingDTO meeting = new MeetingDTO();
			meeting.setId(e.getId());
			meeting.setSubject(subjectConverter.getDTO(e.getSubject()));
			meeting.setRoom(roomConverter.getDTO(e.getRoom()));
			meeting.setDate(e.getDate());
			meeting.setStartTime(e.getStartTime());
			meeting.setEndTime(e.getEndTime());

			// List<UserGroup> groups = e.getGroups();
			// List<UserGroupDTO> groupsDTO = new ArrayList<>();
			//
			// for (int i = 0; i < groups.size(); i++) {
			// groupsDTO.add(getDTO(groups.get(i)));
			// }
			// meeting.setGroups(groupsDTO);

			meeting.setOwner(userConverter.getDTO(e.getOwner()));
			meeting.setStatus(e.getStatus());
			meeting.setLevel(e.getLevel());
			meeting.setDescription(e.getDescription());

			userGroupDTO.getMeetings().add(meeting);
		});

		return userGroupDTO;
	}
}
