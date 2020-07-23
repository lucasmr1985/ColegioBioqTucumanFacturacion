

package Clases;

public class UtilToDate {

    public UtilToDate() {
    } 
    

    public static java.sql.Date convert(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }

}