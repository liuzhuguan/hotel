package hotel.service;

import hotel.bean.User;
import hotel.dao.UserDao;
import hotel.dao.UserDaoImp;

public class UserServiceImp implements UserService {
    private UserDao userDao = new UserDaoImp();
    @Override
    public User findByLoginNameAndPass(String loginname, String password) {
        return userDao.findByLoginNameAndPass(loginname,password);
    }

    @Override
    public User findByLoginName(String loginName) {
        return userDao.findByLoginName(loginName);
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }
}
