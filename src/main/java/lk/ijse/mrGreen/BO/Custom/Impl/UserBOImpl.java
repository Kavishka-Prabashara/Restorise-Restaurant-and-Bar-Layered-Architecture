package lk.ijse.mrGreen.BO.Custom.Impl;

import lk.ijse.mrGreen.BO.Custom.UserBO;
import lk.ijse.mrGreen.DAO.Custom.UserDAO;
import lk.ijse.mrGreen.DAO.DAOFactory;
import lk.ijse.mrGreen.Entity.User;
import lk.ijse.mrGreen.dto.UserDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {
    private UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DTOTypes.USER);

    @Override
    public boolean saveUser(UserDto dto) throws SQLException {
        return userDAO.save(new User(dto.getId(),dto.getName(),dto.getPassword(),dto.getJob_role()));
    }

    @Override
    public boolean deleteUser(String id) throws SQLException {
        return userDAO.delete(id);
    }

    @Override
    public boolean updateUser(UserDto dto) throws SQLException {
        return userDAO.update(new User(dto.getId(),dto.getName(),dto.getPassword(),dto.getJob_role()));
    }

    @Override
    public List<UserDto> loadAllUser() throws SQLException {
        List<User> user = userDAO.loadAll();
        ArrayList<UserDto> dtoList = new ArrayList<>();

        for (User dto: user) {
            dtoList.add(new UserDto(dto.getId(),dto.getName(),dto.getPassword(),dto.getJob_role()));
        }
        return dtoList;
    }

    @Override
    public int getUserCount() throws SQLException {
        return userDAO.getCount();
    }

    @Override
    public String getUserName(String id) throws SQLException {
        return userDAO.getName(id);
    }

    @Override
    public UserDto searchUser(String cusId) throws SQLException {
        User user = userDAO.search(cusId);

        return new UserDto(user.getId(),user.getName(),user.getPassword(),user.getJob_role());
    }

    @Override
    public String getUserPassword(String name) throws SQLException {
        return userDAO.getPassword(name);
    }

    @Override
    public String getUserEmail(String name) throws SQLException {
        return userDAO.getEmail(name);
    }
}
