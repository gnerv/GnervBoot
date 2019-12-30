package com.gnerv.cloud.neuron.store;

import org.springframework.util.StringUtils;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RegistryStore {

    private static Registry registry;

    private RegistryStore() {
    }

    public static Registry getRegistry() {
        if (StringUtils.isEmpty(registry)) {
            try {
                registry = LocateRegistry.createRegistry(1099);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return registry;
    }
}