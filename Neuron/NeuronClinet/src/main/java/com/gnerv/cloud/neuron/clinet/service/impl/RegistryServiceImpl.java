package com.gnerv.cloud.neuron.clinet.service.impl;

import com.gnerv.cloud.neuron.clinet.service.IRegistryService;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

@Service
public class RegistryServiceImpl implements IRegistryService {

    @Override
    public Registry getRegistry(String host, int port) {
        try {
            Registry registry = LocateRegistry.getRegistry(host, port);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

}
