package com.softserve.edu.schedule.service;

import java.security.Principal;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import com.softserve.edu.schedule.dao.Order;
import com.softserve.edu.schedule.dto.UserDTO;
import com.softserve.edu.schedule.dto.UserDTOForChangePassword;
import com.softserve.edu.schedule.dto.UserForSubjectDTO;
import com.softserve.edu.schedule.dto.filter.Paginator;
import com.softserve.edu.schedule.dto.filter.UserFilter;
import com.softserve.edu.schedule.entity.UserRole;
import com.softserve.edu.schedule.entity.UserStatus;

/**
 * An interface to specify service operations with User entity.
 *
 * @version 1.0 04 January 2017
 *
 * @author Serhiy Dudynsky
 *
 * @since 1.8
 */
public interface UserService extends UserDetailsService {

	/**
	 * Save new user entity into the database.
	 *
	 * @param userDTO
	 *            a new userDTO for to storage new user in database.
	 */
	public void create(final UserDTO userDTO);

	/**
	 * Change field status at user entity in the database.
	 *
	 * @param id
	 *            a user id in database.
	 *
	 * @param status
	 *            a status from enum class UserStatus.
	 */
	public void changeStatus(final Long id, final UserStatus status);

	/**
	 * Change field role at user entity in the database.
	 *
	 * @param id
	 *            a user id in database.
	 *
	 * @param role
	 *            a role from enum class UserRole.
	 */
	public void changeRole(final Long id, final UserRole role);

	/**
	 * Change field at user entity in the database.
	 *
	 * @param id
	 *            a user id in database.
	 */
	public void update(final UserDTO userDTO);

	/**
	 * Get all users.
	 *
	 * @return List of the user objects.
	 */
	public List<UserDTO> getAll();

	/**
	 * Get all users.
	 *
	 * @return List of the user objects for SubjectDTO.
	 */
	public List<UserForSubjectDTO> getAllForSubject();

	/**
	 * Get all users by last name what was selected.
	 *
	 * @param pattern
	 *            a value of field in database.
	 *
	 * @return List of the user objects.
	 */
	public List<UserDTO> searchByLastName(final String pattern);

	/**
	 * Get all users by position what was selected.
	 *
	 * @param pattern
	 *            a value of field in database.
	 *
	 * @return List of the user objects.
	 */
	public List<UserDTO> searchByPosition(final String pattern);

	/**
	 * Sort all users by first name.
	 *
	 * @param order
	 *            a constant ASC or DESC.
	 *
	 * @return List of the user objects.
	 */
	public List<UserDTO> sortByLastName(final Order order);

	/**
	 * Sort all users by first name.
	 *
	 * @param order
	 *            a constant ASC or DESC.
	 *
	 * @return List of the user objects.
	 */
	public List<UserDTO> sortByPosition(final Order order);

	/**
	 * Return a User object if found.
	 *
	 * @param id
	 *            of User transfer object
	 * @return User transfer object
	 */
	public UserDTO getById(final Long id);

	/**
	 * Delete existed transfer object from the database by id.
	 *
	 * @param id
	 *            a user id to delete from database.
	 */
	public boolean deleteById(final Long id);

	/**
	 * Find a user in the database by mail.
	 *
	 * @param userMail
	 *            a user mail to find in the database.
	 * 
	 * @return a user DTO with given mail.
	 */
	public List<UserDTO> searchByMail(final String mail);

	/**
	 * Change password of user in the database.
	 *
	 * @param id
	 *            a user id to find in the database.
	 *
	 * @param password
	 *            a user password to verify if real owner of account want change
	 *            password.
	 * 
	 * @param firstNewPassword
	 *            a new password which user want save.
	 * 
	 * @param secondNewPassword
	 *            a new password which should be equal to firstNewPassword
	 *            field.
	 * 
	 * @return a user DTO with given mail.
	 */
	public void changePassword(UserDTOForChangePassword userDTO);

	/**
	 * Return a User object if found.
	 *
	 * @param id
	 *            of User transfer object
	 * @return User transfer object
	 */
	public UserDTOForChangePassword getByIdForPassword(final Long id);

	List<UserDTO> getUsersPageWithFilter(final UserFilter userFilter, final Paginator userPaginator);

	void saveImage(Principal principal, MultipartFile multipartFile);

	/**
	 * Method returns lists of users which have status - active
	 * 
	 * @return lists of users which have status - active
	 */
	List<UserDTO> getAllActiveUsers();

	/**
	 * Get all users, that can manage groups, meetings, etc.
	 * 
	 * @param listUserDTO
	 * @return List<UserDTO>
	 */
	List<UserDTO> getAllManagers(final List<UserDTO> listUserDTO);
}