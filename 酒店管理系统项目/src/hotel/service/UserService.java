package hotel.service;

import hotel.bean.User;

public interface UserService {
    User findByLoginNameAndPass(String loginname, String password);

    User findByLoginName(String loginName);

    void save(User user);
}
