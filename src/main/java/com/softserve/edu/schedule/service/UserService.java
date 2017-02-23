package com.softserve.edu.schedule.service;

import java.io.File;
import java.security.Principal;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.connect.Connection;
import org.springframework.web.multipart.MultipartFile;

import com.softserve.edu.schedule.dto.UserDTO;
import com.softserve.edu.schedule.dto.UserDTOForChangePassword;
import com.softserve.edu.schedule.dto.UserDTOForRestorePassword;
import com.softserve.edu.schedule.dto.UserForAndroidDTO;
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
     * @param userDTO
     *            a userDTO from UI.
     */
    public void update(final UserDTO userDTO);

    /**
     * Get all usersDTO.
     *
     * @return List of the userDTO objects.
     */
    public List<UserDTO> getAll();

    /**
     * Return a UserDTO object if found.
     *
     * @param id
     *            of User transfer object
     * @return UserDTO transfer object
     */
    public UserDTO getById(final Long id);

    /**
     * Delete existed object from the database by id.
     *
     * @param id
     *            a user id to delete from database.
     */
    public boolean deleteById(final Long id);

    /**
     * Find a userDTO in the database by mail.
     *
     * @param user
     *            mail a user mail to find in the database.
     * 
     * @return list userDTO with given mail.
     */
    public List<UserDTO> searchByMail(final String mail);

    /**
     * Change password of user in the database.
     *
     * @param userDTO
     *            a UserDTOForChangePassword example to change password in the
     *            database.
     */
    public void changePassword(UserDTOForChangePassword userDTO);

    /**
     * Return a UserDTOForChangePassword object if found.
     *
     * @param id
     *            of User transfer object
     * @return UserDTOForChangePassword transfer object
     */
    public UserDTOForChangePassword getByIdForPassword(final Long id);

    /**
     * Find all user entities in the database with applied filter
     * 
     * @param userFilter
     *            a filter to apply.
     * @param userPaginator
     *            the userPaginator to set
     * @return List of the user DTO objects.
     */
    List<UserDTO> getUsersPageWithFilter(final UserFilter userFilter,
            final Paginator userPaginator);

    /**
     * Save image in database in the.
     * 
     * @param principal
     *            a authorized userDTO.
     * @param multipartFile
     *            the picture.
     */
    void saveImage(Principal principal, MultipartFile multipartFile);
    
    File getUserPhotoById(Long id);

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

    /**
     * Class used for android application user identification.
     *
     * @param mail
     *            User mail
     *
     * @param password
     *            User password
     *
     * @return UserForAndroidDTO instance if user credentials are correct
     */
    UserForAndroidDTO getVerifiedUser(String mail, String password);

    /**
     * Check if user with this mail exist.
     * 
     * @param userDTO
     *            mail of user what want restore password
     */
    boolean isUserWithMailExist(UserDTOForRestorePassword userDTO);

    /**
     * Restore password and sent mail about this.
     * 
     * @param mail
     *            mail of user what want restore password
     * 
     * @return UserDTO
     */
    UserDTO submitRestorePassword(UserDTOForRestorePassword userDTO);

    /**
     * Provide import data from social network account if user is signing in
     * with social network account.
     *
     * @param connection
     *            social network connection.
     *
     * @return UserDTO instance
     */
    UserDTO createRegistrationDTO(Connection<?> connection);

    /**
     * Provide autologin for new users.
     *
     * @param userDTO
     *            user DTO of new user
     */
    void autoLoginUser(UserDTO userDTO);

}