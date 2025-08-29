package lk.ijse.mrGreen.BO;

import lk.ijse.mrGreen.BO.Custom.Impl.*;
import lk.ijse.mrGreen.BO.Custom.OrderDetailBO;

public class BOFactory {
    public static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getBoFactory() {
        return (boFactory == null) ? new BOFactory() : boFactory;
    }

    public enum BOTypes{
        CUSTOMER,EMPLOYEE,FERTILIZER,GREENHOUSE,LETTUCE,ORDER,ORDERDETAILS,SUPPLIER,USER
    }
    public SuperBO getBo(BOTypes boTypes){
        switch (boTypes){
            case CUSTOMER: return new CustomerBOImpl();
            case EMPLOYEE: return  new EmployeeBOImpl();
            case FERTILIZER: return new FertilizerBOImpl();
            case GREENHOUSE: return  new GreenhouseBOImpl();
            case LETTUCE: return new LettuceBOImpl();
            case ORDER: return new OrderBOImpl();
            case ORDERDETAILS: return new OrderDetailBOImpl();
            case SUPPLIER: return new SupplierBOImpl();
            case USER: return new UserBOImpl();
        }
        return null;
    }
}
