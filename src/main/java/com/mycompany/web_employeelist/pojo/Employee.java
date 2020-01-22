
package com.mycompany.web_employeelist.pojo;

public class Employee {

    String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return "my name is " + name; 
    }

}