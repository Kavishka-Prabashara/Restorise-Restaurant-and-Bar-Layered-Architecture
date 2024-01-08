package lk.restorise.restorisems.util;

public class Regex {
//    private static final String MOBILE_REGEX = "^\\+?\\d{10,}$";
    private static final String EmployeeId_REGEX = "\\d+";


//    public static boolean validateContact(String contact) {
//        return contact.matches(MOBILE_REGEX);
//    }
    public static boolean validateEmployeeId(String EMPLOYEE_ID) {
        return EMPLOYEE_ID.matches(EmployeeId_REGEX);
    }
}