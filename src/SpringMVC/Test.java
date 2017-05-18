package SpringMVC;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class Test {
    @RequestMapping(value = "/test", produces = "application/json")
    public @ResponseBody ArrayList<Person> test(){
        ArrayList<Person> list = new ArrayList<>();
        list.add(new Person(1,"a"));
        list.add(new Person(2,"b"));
        return list;
    }
}

class Person{
    private int id;
    private String name;

    Person(int id, String name){this.id = id; this.name = name;}

    public int getId(){return this.id;}
    public void setId(int id){this.id = id;}

    public String getName(){return this.name;}
    public void setName(String name){this.name = name;}
}
