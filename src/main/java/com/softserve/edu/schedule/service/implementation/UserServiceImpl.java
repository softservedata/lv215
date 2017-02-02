package com.softserve.edu.schedule.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.schedule.aspect.Loggable;
import com.softserve.edu.schedule.aspect.PerfomanceLoggable;
import com.softserve.edu.schedule.dao.Order;
import com.softserve.edu.schedule.dao.UserDAO;
import com.softserve.edu.schedule.dto.UserDTO;
import com.softserve.edu.schedule.dto.UserDTOForChangePassword;
import com.softserve.edu.schedule.dto.UserForSubjectDTO;
import com.softserve.edu.schedule.entity.User;
import com.softserve.edu.schedule.entity.UserRole;
import com.softserve.edu.schedule.entity.UserStatus;
import com.softserve.edu.schedule.entity.User_;
import com.softserve.edu.schedule.service.UserService;
import com.softserve.edu.schedule.service.implementation.dtoconverter.UserDTOConverter;
import com.softserve.edu.schedule.service.implementation.dtoconverter.UserDTOForChangePasswordConverter;
import com.softserve.edu.schedule.service.implementation.dtoconverter.UserForSubjectDTOConverter;

/**
 * An interface to provide service operations with User entity.
 *
 * @version 1.0 04 January 2017
 *
 * @author Serhiy Dudynsky
 *
 * @since 1.8
 */
@Loggable
@Service("userService")
@PerfomanceLoggable
public class UserServiceImpl implements UserService {

    /**
     * UserDAO example to provide database operations.
     */
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserDTOConverter userDTOConverter;

    @Autowired
    private UserDTOForChangePasswordConverter userDTOForPasswordConverter;

    @Autowired
    private UserForSubjectDTOConverter userForSubjectDTOConverter;

    @Autowired
    private BCryptPasswordEncoder encoder;

    /**
     * Save new user entity into the database.
     *
     * @param user
     *            a userDTO for to storage new user in database.
     */
    @Override
    @Transactional
    public void create(final UserDTO userDTO) {
        userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        userDAO.create(userDTOConverter.getEntity(userDTO));
    }

    /**
     * Change field status at user entity in the database.
     *
     * @param id
     *            a user id in database.
     *
     * @param status
     *            a status from enum class UserStatus.
     */
    @Override
    @Transactional
    public void changeStatus(final Long id, final UserStatus status) {
        User user = userDAO.getById(id);
        user.setStatus(status);
        userDAO.update(user);
    }

    /**
     * Change field role at user entity in the database.
     *
     * @param id
     *            a user id in database.
     *
     * @param role
     *            a role from enum class UserRole.
     */
    @Override
    @Transactional
    public void changeRole(final Long id, final UserRole userRole) {
        User user = userDAO.getById(id);
        user.setRole(userRole);
        userDAO.update(user);
    }

    /**
     * Change field at user entity in the database.
     *
     * @param userDTO
     *            a new user to storage in database.
     */
    @Override
    @Transactional
    public void update(final UserDTO userDTO) {
        User user = userDTOConverter.getEntity(userDTO);
        // user.setPassword(userDAO.getById(user.getId()).getPassword());
        user.setPassword(encoder.encode(userDTO.getPassword()));
        user.setStatus(userDAO.getById(user.getId()).getStatus());
        user.setRole(userDAO.getById(user.getId()).getRole());
        user.setSubjects(userDAO.getById(user.getId()).getSubjects());
        user.setGroups(userDAO.getById(user.getId()).getGroups());
        userDAO.update(user);
    }

    /**
     * Get all usersDTO.
     *
     * @return List of the userDTO objects.
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getAll() {
        return userDAO.getAll().stream().map(e -> userDTOConverter.getDTO(e))
                .collect(Collectors.toList());
    }

    /**
     * Get all users.
     *
     * @return List of the userDTO objects for SubjectDTO.
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserForSubjectDTO> getAllForSubject() {
        return userDAO.getAll().stream()
                .map(u -> userForSubjectDTOConverter.getDTO(u))
                .collect(Collectors.toList());
    }

    /**
     * Get all users by last name what was selected.
     *
     * @param position
     *            a value of name field in database.
     *
     * @return List of the userDTO objects.
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> searchByLastName(final String pattern) {
        return userDAO.search(User_.lastName.getName(), pattern).stream()
                .map(e -> userDTOConverter.getDTO(e))
                .collect(Collectors.toList());
    }

    /**
     * Get all users by position what was selected.
     *
     * @param position
     *            a value of position field in database.
     *
     * @return List of the userDTO objects.
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> searchByPosition(final String pattern) {
        return userDAO.search(User_.position.getName(), pattern).stream()
                .map(e -> userDTOConverter.getDTO(e))
                .collect(Collectors.toList());
    }

    /**
     * Sort all users by last name.
     *
     * @return List of the userDTO objects.
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> sortByLastName(final Order order) {
        return userDAO.sort(User_.lastName.getName(), order).stream()
                .map(e -> userDTOConverter.getDTO(e))
                .collect(Collectors.toList());
    }

    /**
     * Sort all users by last name.
     *
     * @return List of the userDTO objects.
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> sortByPosition(final Order order) {
        return userDAO.sort(User_.position.getName(), order).stream()
                .map(e -> userDTOConverter.getDTO(e))
                .collect(Collectors.toList());
    }

    /**
     * Return a User object if found.
     *
     * @param id
     *            of User transfer object
     * @return UserDTO transfer object
     */
    @Override
    @Transactional(readOnly = true)
    public UserDTO getById(final Long id) {
        return userDTOConverter.getDTO(userDAO.getById(id));
    }

    /**
     * Delete existed transfer object from the database by id.
     *
     * @param id
     *            a user id to delete from database.
     */
    @Override
    @Transactional
    public boolean deleteById(final Long id) {
        if (userDAO.getById(id).getGroups().stream()
                .noneMatch(e -> e.getCurator().getId().equals(id))) {
            userDAO.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Find a user DTO in the database by mail.
     *
     * @param userMail
     *            a user mail to find in the database.
     * 
     * @return a user DTO with given mail.
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> searchByMail(final String mail) {
        return userDAO.search(User_.mail.getName(), mail).stream()
                .map(e -> userDTOConverter.getDTO(e))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO loadUserByUsername(String userMail)
            throws UsernameNotFoundException {
        User user = userDAO.findByMail(userMail);
        if (user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return userDTOConverter.getDTO(user);
    }

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
    @Override
    @Transactional
    public void changePassword(UserDTOForChangePassword userDTO) {

        User user = userDTOForPasswordConverter.getEntity(userDTO);
        user.setPassword(encoder.encode(userDTO.getSecondNewPassword()));
        user.setStatus(userDAO.getById(user.getId()).getStatus());
        user.setRole(userDAO.getById(user.getId()).getRole());
        userDAO.update(user);
    }

    /**
     * Return a User object if found.
     *
     * @param id
     *            of User transfer object
     * @return User transfer object
     */
    @Override
    @Transactional(readOnly = true)
    public UserDTOForChangePassword getByIdForPassword(final Long id) {
        return userDTOForPasswordConverter
                .getDTOForPassword(userDAO.getById(id));
    }

}
