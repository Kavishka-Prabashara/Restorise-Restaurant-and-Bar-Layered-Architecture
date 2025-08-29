package lk.ijse.mrGreen.DAO;

import lk.ijse.mrGreen.DAO.Custom.Impl.*;

public class DAOFactory {
    public static DAOFactory daoFactory;

    public DAOFactory() {

    }

    public static DAOFactory getDaoFactory(){
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DTOTypes{
        CUSTOMER,EMPLOYEE,FERTILIZER,GREENHOUSE,LETTUCE,ORDERDETAILS,ORDER,SUPPLIER,USER
    }

    public SuperDAO getDAO(DTOTypes dtoTypes){
        switch (dtoTypes){
            case CUSTOMER: return new CustomerDAOImpl();
            case EMPLOYEE: return new EmployeeDAOImpl();
            case FERTILIZER: return new FertilizerDAOImpl();
            case GREENHOUSE: return new GreenHouseDAOImpl();
            case LETTUCE: return new LettuceDAOImpl();
            case ORDERDETAILS:return new OrderDetailDAOImpl();
            case ORDER: return new OrderDAOImpl();
            case SUPPLIER: return new SupplierDAOImpl();
            case USER: return new UserDAOImpl();
        }
        return null;
    }
}
