package com.vinsys.Project.Service;

import com.vinsys.Project.beans.User;

public interface UserService {
    User findByUsername(String username);
    void registerUser(User user);     
    void registerAdmin(User user);       
}
