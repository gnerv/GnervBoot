package com.gnerv.cloud.neuron.client;

import com.gnerv.cloud.neuron.service.IHelloRegistryFacade;

import java.lang.reflect.Method;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;

public class RegistryClient {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("172.23.32.102", 1099);
            IHelloRegistryFacade hello = (IHelloRegistryFacade) registry.lookup("HelloRegistry");
            String response = hello.helloWorld("ZhenJin");

            System.out.println("=======> " + response + " <=======");
            Remote remote = registry.lookup("HelloRegistry");
            Method[] methods = remote.getClass().getMethods();
            String name = remote.getClass().getName();
            Class<? extends Remote> aClass = remote.getClass();
            boolean anInterface = remote.getClass().isInterface();
            Map map = new HashMap();
            map.put("name", "HelloRegistry");
            String reflectInvokeMethod = reflectInvokeMethod(remote.getClass(), "helloWorld", map);
            System.out.println("=======> " + reflectInvokeMethod + " <=======");
        } catch (NotBoundException | RemoteException e) {
            e.printStackTrace();
        }
    }

    public static String reflectInvokeMethod(Class<?> clazz, String mn, Map params) {
        String res = "";
        try {
            Method method = clazz.getMethod(mn, Map.class);
            method.setAccessible(true);
            res = method.invoke(clazz.newInstance(), params).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

}
