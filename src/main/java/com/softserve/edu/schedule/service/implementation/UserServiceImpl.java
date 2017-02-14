package com.softserve.edu.schedule.service.implementation;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.softserve.edu.schedule.aspects.PerfomanceLoggable;
import com.softserve.edu.schedule.dao.MeetingDAO;
import com.softserve.edu.schedule.dao.UserDAO;
import com.softserve.edu.schedule.dto.UserDTO;
import com.softserve.edu.schedule.dto.UserDTOForChangePassword;
import com.softserve.edu.schedule.dto.filter.Paginator;
import com.softserve.edu.schedule.dto.filter.UserFilter;
import com.softserve.edu.schedule.entity.User;
import com.softserve.edu.schedule.entity.UserRole;
import com.softserve.edu.schedule.entity.UserStatus;
import com.softserve.edu.schedule.entity.User_;
import com.softserve.edu.schedule.service.UserService;
import com.softserve.edu.schedule.service.implementation.dtoconverter.UserDTOConverter;
import com.softserve.edu.schedule.service.implementation.dtoconverter.UserDTOForChangePasswordConverter;

/**
 * An interface to provide service operations with User entity.
 *
 * @version 1.0 04 January 2017
 *
 * @author Serhiy Dudynsky
 *
 * @since 1.8
 */
@Service("userService")
@PerfomanceLoggable
public class UserServiceImpl implements UserService {

//    private static final String PATH = "E:/Programing/lv215/src/main/webapp/resources/images/";
//
//    private static final String SLASH = "/";
//    
//    private static final String NAME_OF_FILE = "/resources/images/";

    /**
     * UserDAO example to provide database operations.
     */
    @Autowired
    private UserDAO userDAO;

    /**
     * UserDAO example to provide database operations.
     */
    @Autowired
    private MeetingDAO meetingDAO;

    /**
     * UserDAOConverter example to provide conversion.
     */
    @Autowired
    private UserDTOConverter userDTOConverter;

    /**
     * UserDAOForChangePassword example to provide conversion.
     */
    @Autowired
    private UserDTOForChangePasswordConverter userDTOForPasswordConverter;

    /**
     * BCryptPasswordEncoder example to provide encoder for password.
     */
    @Autowired
    private BCryptPasswordEncoder encoder;
    
    /**
     * Save new user entity into the database.
     *
     * @param userDTO
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
        user.setPassword(userDTO.getPassword());
        user.setPathImage(userDAO.getById(user.getId()).getPathImage());
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
     * Return a UserDTO object if found.
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
     * Delete existed object from the database by id.
     *
     * @param id
     *            a user id to delete from database.
     */
    @Override
    @Transactional
    public boolean deleteById(final Long id) {
        if ((userDAO.getById(id).getGroups().stream()
                .noneMatch(e -> e.getCurator().getId().equals(id)))
                && (meetingDAO.getAll().stream()
                        .noneMatch(e -> e.getOwner().getId().equals(id)))) {
            userDAO.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Find a userDTO in the database by mail.
     *
     * @param user
     *            mail a user mail to find in the database.
     * 
     * @return list userDTO with given mail.
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> searchByMail(final String mail) {
        return userDAO.search(User_.mail.getName(), mail).stream()
                .map(e -> userDTOConverter.getDTO(e))
                .collect(Collectors.toList());
    }

    /**
     * Change password of user in the database.
     *
     * @param userDTO
     *            a UserDTOForChangePassword example to change password in the
     *            database.
     */
    @Override
    @Transactional
    public void changePassword(UserDTOForChangePassword userDTO) {
        User user = userDTOForPasswordConverter.getEntity(userDTO);
        user.setPassword(encoder.encode(userDTO.getSecondNewPassword()));
        userDAO.update(user);
    }

    /**
     * Return a UserDTOForChangePassword object if found.
     *
     * @param id
     *            of User transfer object
     * @return UserDTOForChangePassword transfer object
     */
    @Override
    @Transactional(readOnly = true)
    public UserDTOForChangePassword getByIdForPassword(final Long id) {
        return userDTOForPasswordConverter
                .getDTOForPassword(userDAO.getById(id));
    }

    /**
     * Find all user entities in the database with applied filter
     * 
     * @param userFilter
     *            a filter to apply.
     * @param userPaginator
     *            the userPaginator to set
     * @return List of the user DTO objects.
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getUsersPageWithFilter(final UserFilter userFilter,
            final Paginator userPaginator) {
        return userDAO.getUsersPageWithFilter(userFilter, userPaginator)
                .stream().map(e -> userDTOConverter.getDTO(e))
                .collect(Collectors.toList());
    }

    /**
     * Locates the user based on the user e-mail.
     *
     * @param userMail
     *            e-mail address identifying the user whose data is required.
     *
     * @return a fully populated user record
     *
     * @throws UsernameNotFoundException
     *             if the user could not be found or the user has no
     *             GrantedAuthority
     */
    @Override
    @Transactional(readOnly = true)
    public UserDTO loadUserByUsername(final String userMail)
            throws UsernameNotFoundException {
        User user = userDAO.findByMail(userMail);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return userDTOConverter.getDTO(user);
    }

    /**
     * Save image in database in the.
     * 
     * @param principal
     *            a authorized userDTO.
     * @param multipartFile
     *            the picture.
     */
    @Override
    @Transactional
    public void saveImage(Principal principal, MultipartFile multipartFile) {
//
//        User user = userDAO.findByMail(principal.getName());
//
//        String path = PATH + user.getMail() + SLASH
//                + multipartFile.getOriginalFilename();
//
//        user.setPathImage(NAME_OF_FILE + user.getMail() + SLASH
//                + multipartFile.getOriginalFilename());
//
//        File file = new File(path);
//
//        try {
//            file.mkdirs();
//            try {
//                FileUtils.cleanDirectory(
//                        new File(PATH + user.getMail() + SLASH));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            multipartFile.transferTo(file);
//        } catch (IOException e) {
//            System.out.println("error with file");
//        }
//        userDAO.update(user);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see com.softserve.edu.schedule.service.UserService#getAllActiveUsers()
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getAllActiveUsers() {
        return userDAO.getAllActiveUsers().stream().map(e -> userDTOConverter.getDTO(e)).collect(Collectors.toList());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.softserve.edu.schedule.service.UserService#getAllManagers(java.util.
     * List)
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getAllManagers(final List<UserDTO> listUserDTO) {
        List<UserDTO> listUserDTOForMeetingOwners = new ArrayList<UserDTO>();
        for (UserDTO userTEMP : listUserDTO) {
            if (userTEMP.getRole() != UserRole.ROLE_USER) {
                listUserDTOForMeetingOwners.add(userTEMP);
            }
        }
        return listUserDTOForMeetingOwners;
    }
}

