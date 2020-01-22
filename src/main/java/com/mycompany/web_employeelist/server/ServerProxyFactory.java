
package com.mycompany.web_employeelist.server;


public class ServerProxyFactory {
    
    private static IServerProxy theInstance;
    
    private ServerProxyFactory(){
        //the constructor is private --> so, no object of this class can be instantiated
        
    }
    
    public static synchronized IServerProxy getInstance(){
        
        if(theInstance == null)
            theInstance = new DBServerProxy(); //decides which subclass of IServerProxy is used ex. DBServer or MockServer
        return theInstance;
    }
    
    
}
