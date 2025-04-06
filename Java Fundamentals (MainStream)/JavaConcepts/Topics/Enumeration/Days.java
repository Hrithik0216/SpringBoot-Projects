package Enumeration;

public enum Days implements DaysInterface{
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;
//
//    String value;
//
//    Days(String value){
//        this.value = value;
//    }

    @Override
    public void toLowerCase(Days day) {
        for(Days d: Days.values()){
            if(d==day){
                System.out.println(d.name().toLowerCase());
            }
        }
    }
}
