package Enumeration;

public class Implementation {
    public static void main(String[] args) {
        Days days = Days.valueOf("TUESDAY");
        System.out.println(days);
        for(Days day : Days.values()){
            System.out.println(day.name());
            System.out.println(day.ordinal());
        }
        days.toLowerCase(Days.TUESDAY);

    }
}
