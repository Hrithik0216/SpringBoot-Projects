package GenericClass.GenericClasses.WildCardAndGeneric;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class WildCard {
    /*
    * Wildcards Method are used to add restrictions to individual variable variable.
    * It is used when we want to add restriction on individual variable. [use Case]
    * Upprebound and lowerbound.
    * Upperbound -> Number is upper bound(extends for it)
    * LowerBound -> Object is the superclass for all . (Super is for lower bound)
    * */
    public void wildCardMethod(List<? extends Number> value, List<?extends String> value3){
        for(Number number: value){
            System.out.println(number);
        }
        for(String i: value3){
            System.out.println(i);
        }
    }

    /*
     * Generic Method are used to add common restrictions to all variables.
     * It is used when we want to add restriction on all variables. [use Case]
     * */
    public <K> void wildCardMethod(List<K> value){
        for(K k : value){
            System.out.println(k);
        }
    }

    public static void main(String[] args) {
        WildCard wildCard = new WildCard();
        List<Number> numbers = new ArrayList<>(Arrays.asList(1,2,4,4));
        List<String> string = new ArrayList<>(Arrays.asList("stasdas","asdsad"));
        wildCard.wildCardMethod(numbers,string);
        wildCard.wildCardMethod(numbers);
    }
}
