import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Optional;

public class Java8Features {

    public static void main(String[] args) {
        System.out.println("Hello World!! Java 8 Test !!");

        List<String> fruitsList = new ArrayList<String>();
        fruitsList.add("Orange");
        fruitsList.add("Apple");
        fruitsList.add("Mango");
        fruitsList.add("Banana");
        fruitsList.add("Kiwi");

        //Method References
        fruitsList.forEach(System.out::println);

        Java8HelloWorld testObj = new Java8HelloWorld();

        //Lambda
        testObj.sortFruitsWithLambda(fruitsList);
        System.out.println(fruitsList);

        //Default method invocation
        Mango testMango = new Mango();
        testMango.print();

        //Stream implementation
        Random random = new Random();
        random.ints().limit(10).sorted().forEach(System.out::println);

        //Optional usage
        Integer intValue = new Integer(10);
        Optional<Integer> optionalObj = Optional.ofNullable(intValue);

        System.out.println("First parameter is present: " + optionalObj.isPresent());

        //Nashorn usage
        testObj.jsEngineUse();
    }

    //sort using Lambda & Functional interfaces
    private void sortFruitsWithLambda(List<String> fruitNames) {
        Collections.sort(fruitNames, (s1, s2) -> s1.compareTo(s2));
    }

    //Nashorn Javascript engine usage
    private void jsEngineUse() {

        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine nashorn = scriptEngineManager.getEngineByName("nashorn");

        String string1 = "Use Nashorn Javascript Engine";
        Integer result = null;

        try {
            nashorn.eval("print('" + string1 + "')");
            result = (Integer) nashorn.eval("10 + 2");

        }catch(ScriptException e){
            System.out.println("Error executing script: "+ e.getMessage());
        }

        System.out.println(result.toString());
    }
}

//Default Methods in Interfaces
interface Fruit {
    default void print(){
        System.out.println("It's a fruit !!");
    }
}

class Mango implements Fruit {
    public void print(){
        Fruit.super.print();
        System.out.println("It's a Mango !!");
    }
}