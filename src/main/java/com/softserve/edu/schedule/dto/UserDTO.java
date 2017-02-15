package com.softserve.edu.schedule.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.softserve.edu.schedule.entity.UserRole;
import com.softserve.edu.schedule.entity.UserStatus;
import com.softserve.edu.schedule.service.implementation.validators.Validate;

/**
 * A data transfer object for user entity.
 *
 * @version 1.0 15 January 2017
 *
 * @author Serhiy Dudynsky
 *
 * @since 1.8
 */
@Validate
public class UserDTO implements UserDetails {

    /**
     * Serial version UID for serialization.
     */
    private static final long serialVersionUID = -2904288046344286004L;

    private Long id;
    private String firstName;
    private String lastName;
    private String mail;
    private String password;
    private String confirmPassword;
    private String phone;
    private String pathImage;
    private String position;
    private UserStatus status;
    private UserRole role;
    private List<UserGroupForUserDTO> groups = new ArrayList<>();
    private List<SubjectForUserDTO> subjects = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public List<UserGroupForUserDTO> getGroups() {
        return groups;
    }

    public void setGroups(List<UserGroupForUserDTO> groups) {
        this.groups = groups;
    }

    public List<SubjectForUserDTO> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectForUserDTO> subjects) {
        this.subjects = subjects;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    /**
     * Returns the authorities granted to the user.
     *
     * @return the authorities, sorted by natural key
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority sga = new SimpleGrantedAuthority(getRole().name());
        List<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>(1);
        roles.add(sga);
        return roles;
    }

    /**
     * Returns the username used to authenticate the user.
     *
     * @return the username
     */
    @Override
    public String getUsername() {
        return getMail();
    }

    /**
     * Indicates whether the user's account has expired. An expired account
     * cannot be authenticated.
     *
     * @return true if the user's account is valid (ie non-expired), false if no
     *         longer valid (ie expired)
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is locked or unlocked. A locked user cannot be
     * authenticated.
     *
     * @return true if the user is not locked, false otherwise
     */
    @Override
    public boolean isAccountNonLocked() {
        return getStatus().equals(UserStatus.ACTIVE);
    }

    /**
     * Indicates whether the user's credentials (password) has expired. Expired
     * credentials prevent authentication.
     *
     * @return true if the user's credentials are valid (ie non-expired), false
     *         if no longer valid (ie expired)
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled or disabled. A disabled user cannot
     * be authenticated.
     *
     * @return true if the user is enabled, false otherwise
     */
    @Override
    public boolean isEnabled() {
        return getStatus().equals(UserStatus.ACTIVE);
    }

    /**
     * Check if users are equal by email(if it's the same person).
     */
    @Override
    public boolean equals(final Object o) {

        if (o == this) {
            return true;
        }
        if (!(o instanceof UserDTO)) {
            return false;
        }
        UserDTO user = (UserDTO) o;
        return Objects.equals(mail, user.mail);
    }

    /**
     * Calculate hashcode of given user.
     */
    @Override
    public int hashCode() {
        return Objects.hash(mail);
    }
}
