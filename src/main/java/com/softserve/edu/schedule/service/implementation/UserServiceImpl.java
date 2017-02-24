package com.softserve.edu.schedule.service.implementation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.softserve.edu.schedule.aspects.PerfomanceLoggable;
import com.softserve.edu.schedule.dao.FileStorageDAO;
import com.softserve.edu.schedule.dao.MeetingDAO;
import com.softserve.edu.schedule.dao.UserConnectionDAO;
import com.softserve.edu.schedule.dao.UserDAO;
import com.softserve.edu.schedule.dto.UserDTO;
import com.softserve.edu.schedule.dto.UserDTOForChangePassword;
import com.softserve.edu.schedule.dto.UserDTOForRestorePassword;
import com.softserve.edu.schedule.dto.UserForAndroidDTO;
import com.softserve.edu.schedule.dto.filter.Paginator;
import com.softserve.edu.schedule.dto.filter.UserFilter;
import com.softserve.edu.schedule.entity.SocialMediaService;
import com.softserve.edu.schedule.entity.User;
import com.softserve.edu.schedule.entity.UserRole;
import com.softserve.edu.schedule.entity.UserStatus;
import com.softserve.edu.schedule.entity.User_;
import com.softserve.edu.schedule.service.UserService;
import com.softserve.edu.schedule.service.implementation.dtoconverter.UserDTOConverter;
import com.softserve.edu.schedule.service.implementation.dtoconverter.UserDTOForChangePasswordConverter;
import com.softserve.edu.schedule.service.implementation.dtoconverter.UserDTOForRestorePasswordConverter;
import com.softserve.edu.schedule.service.implementation.dtoconverter.UserForAndroidDTOConverter;
import com.softserve.edu.schedule.service.implementation.mailsenders.RestorePasswordMailServise;

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
public class UserServiceImpl implements UserService, SocialUserDetailsService {

    /**
     * UserDAO example to provide database operations.
     */
    @Autowired
    private UserDAO userDAO;

    /**
     * UserConnectionDAO example to provide database operations.
     */
    @Autowired
    private UserConnectionDAO userConnectionDAO;

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
     * UserForAndroidDTOConverter example to provide conversion.
     */
    @Autowired
    private UserForAndroidDTOConverter userForAndroidDTOConverter;

    /**
     * UserDAOForChangePassword example to provide conversion.
     */
    @Autowired
    private UserDTOForChangePasswordConverter userDTOForPasswordConverter;

    /**
     * UserDAOForRestorePassword example to provide conversion.
     */
    @Autowired
    private UserDTOForRestorePasswordConverter userDTOForRestorePasswordConverter;

    /**
     * BCryptPasswordEncoder example to provide encoder for password.
     */
    @Autowired
    private BCryptPasswordEncoder encoder;

    /**
     * RestorePasswordMailService example to provide send mail to user
     * operations.
     */
    @Autowired
    private RestorePasswordMailServise restorePasswordMailService;

    @Autowired
    private FileStorageDAO fileStorageDao;

    /**
     * Save new user entity into the database.
     *
     * @param userDTO
     *            a userDTO for to storage new user in database.
     * @throws FileNotFoundException
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
        if (status != UserStatus.ACTIVE) {
            userConnectionDAO.deleteConnectionsByUserId(user.getMail());
        }
        userDAO.update(user);
    }

    /**
     * Change field role at user entity in the database.
     *
     * @param id
     *            a user id in database.
     *
     * @param userRole
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
            userConnectionDAO
                    .deleteConnectionsByUserId(userDAO.getById(id).getMail());
            userDAO.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Find a userDTO in the database by mail.
     *
     * @param mail
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
    public void changePassword(final UserDTOForChangePassword userDTO) {
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
     * Find all user entities in the database with applied filter.
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
            return null;
        }
        return userDTOConverter.getDTO(user);
    }

    /**
     * @see UserDetailsService#loadUserByUsername(String)
     * @param userId
     *            the user ID used to lookup the user details
     * @return the SocialUserDetails requested
     */
    @Override
    @Transactional(readOnly = true)
    public SocialUserDetails loadUserByUserId(final String userId)
            throws UsernameNotFoundException {
        return loadUserByUsername(userId);
    }

    /**
     * Save image in database in the.
     *
     * @param principal
     *            a authorized userDTO.
     * 
     * @param multipartFile
     *            the picture.
     */
    @Override
    @Transactional
    public void saveImage(final Principal principal, final MultipartFile file)
            throws IOException {

        DBObject metadata = new BasicDBObject();
        metadata.put(USER_ID,
                Long.toString(userDAO.findByMail(principal.getName()).getId()));
        fileStorageDao.store(file.getInputStream(), file.getOriginalFilename(),
                metadata);
    }

    /**
     * Save default image in database after registration.
     * 
     * @param userDTO
     *            a userDTO from new user.
     */
    @Override
    @Transactional
    public void saveDefoultImage(final UserDTO userDTO) throws IOException {

        User user = userDTOConverter.getEntity(userDTO);
        DBObject metadata = new BasicDBObject();
        metadata.put(USER_ID, user.getId());
        File image = new File(USER_PHOTO_BY_DEFOULT);
        InputStream inputStream = new FileInputStream(image);
        fileStorageDao.store(inputStream, FILE_NAME, metadata);
    }

    /**
     * Show image in jsp.
     * 
     * @param id
     *            a user id.
     */
    @Override
    public String showUserFile(Long id) {
        return fileStorageDao
                .findByIdAndType(Long.toString(id), METADATA_USERID)
                .getFilename();
    }

    /**
     * Delete image from database by id.
     * 
     * @param id
     *            a user id.
     */
    @Override
    public void deleteUserPhotoById(Long id) {
        fileStorageDao.deleteById(METADATA_USERID, Long.toString(id));
    }

    /**
     * Retrive image from database.
     * 
     * @param id
     *            a user id.
     * 
     * @param fileName
     *            name of file in database.
     * 
     * @param response
     *            response.
     */
    @Override
    public void retrivePhotoById(Long id, String fileName,
            HttpServletResponse response) throws IOException {
        GridFSDBFile file = fileStorageDao.retriveByIdAndFileName(
                Long.toString(id), fileName, METADATA_USERID);
        if (file != null) {
            response.setContentType(file.getContentType());
            response.setContentLength((new Long(file.getLength()).intValue()));
            response.setHeader("content-Disposition",
                    "attachment; filename=" + file.getFilename());
            IOUtils.copyLarge(file.getInputStream(),
                    response.getOutputStream());
        }
    }

    /*
     * @see com.softserve.edu.schedule.service.UserService#getAllActiveUsers()
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getAllActiveUsers() {
        return userDAO.getAllActiveUsers().stream()
                .map(e -> userDTOConverter.getDTO(e))
                .collect(Collectors.toList());
    }

    /*
     * @see
     * com.softserve.edu.schedule.service.UserService#getAllManagers(java.util.
     * List)
     */
    @Override
    public List<UserDTO> getAllManagers(final List<UserDTO> listUserDTO) {
        List<UserDTO> listUserDTOForMeetingOwners = new ArrayList<UserDTO>();
        for (UserDTO userTEMP : listUserDTO) {
            if (userTEMP.getRole() != UserRole.ROLE_USER) {
                listUserDTOForMeetingOwners.add(userTEMP);
            }
        }
        return listUserDTOForMeetingOwners;
    }

    /**
     * Check if user with this mail exist.
     *
     * @param userDTO
     *            mail of user what want restore password
     * @return true if user exist
     */
    public boolean isUserWithMailExist(
            final UserDTOForRestorePassword userDTO) {
        User user = userDTOForRestorePasswordConverter.getEntity(userDTO);
        if (user != null) {
            return true;
        }
        return false;
    }

    /**
     * Restore password and sent mail about this.
     *
     * @param userDTO
     *            user DTO for restore password
     *
     * @return UserDTO
     */
    @Override
    @Transactional
    public UserDTO submitRestorePassword(
            final UserDTOForRestorePassword userDTO) {
        User user = userDTOForRestorePasswordConverter.getEntity(userDTO);

        String newPassword = RandomStringUtils.randomAscii(8);

        restorePasswordMailService.sendInfoMessageRestorePassword(userDTO,
                LocaleContextHolder.getLocale(), newPassword, user);

        user.setPassword(encoder.encode(newPassword));
        userDAO.update(user);

        newPassword = null;

        UserDTO userDTOFull = userDTOConverter.getDTO(user);
        return userDTOFull;
    }

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
    public UserForAndroidDTO getVerifiedUser(final String mail,
            final String password) {
        User user = userDAO.findByMail(mail);
        if (user != null && user.getStatus().equals(UserStatus.ACTIVE)
                && encoder.matches(password, user.getPassword())) {
            return userForAndroidDTOConverter.getDTO(user);
        }
        return null;
    }

    /**
     * Provide import data from social network account if user is signing in
     * with social network account.
     *
     * @param connection
     *            social network connection.
     *
     * @return UserDTO instance
     */
    @Override
    public UserDTO createRegistrationDTO(final Connection<?> connection) {
        UserDTO dto = new UserDTO();
        if (connection != null) {
            UserProfile socialMediaProfile = connection.fetchUserProfile();
            dto.setMail(socialMediaProfile.getEmail());
            dto.setFirstName(socialMediaProfile.getFirstName());
            dto.setLastName(socialMediaProfile.getLastName());
            ConnectionKey providerKey = connection.getKey();
            dto.setSignInProvider(SocialMediaService
                    .valueOf(providerKey.getProviderId().toUpperCase()));
        }
        return dto;
    }

    /**
     * Provide autologin for new users.
     *
     * @param userDTO
     *            user DTO of new user
     */
    @Override
    @Transactional(readOnly = true)
    public void autoLoginUser(UserDTO userDTO) {
        UserDetails userDetails = loadUserByUsername(userDTO.getMail());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
