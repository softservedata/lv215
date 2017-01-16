package com.softserve.edu.schedule.dao.implementation;

import org.springframework.stereotype.Repository;

import com.softserve.edu.schedule.dao.UserDAO;
import com.softserve.edu.schedule.entity.User;

@Repository("userDAO")
public class UserDAOImpl extends CrudDAOImpl<User> implements UserDAO{

    public UserDAOImpl() {
        super(User.class);
    }

    @Override
    public void dosmth() {
        // TODO Auto-generated method stub
        
    }
}
