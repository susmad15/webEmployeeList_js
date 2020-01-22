
package com.mycompany.web_employeelist;


public class Person implements IPerson{

    @Override
    public void setName(String name) {
        
    }
    
    public static void main(String[] args) {
        Person person = new Person();
        person.setName("abcde");
        
        IPerson iperson = (rene) -> System.out.println(rene);
        iperson.setName("Da Wagner Rene");
    }
}
