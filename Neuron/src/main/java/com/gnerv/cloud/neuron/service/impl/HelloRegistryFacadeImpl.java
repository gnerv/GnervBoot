package com.gnerv.cloud.neuron.service.impl;

import com.gnerv.cloud.neuron.service.IHelloRegistryFacade;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

@Service
public class HelloRegistryFacadeImpl extends UnicastRemoteObject implements IHelloRegistryFacade {

    public HelloRegistryFacadeImpl() throws RemoteException {
        super();
    }

    @Override
    public String helloWorld(String name) {
        return "[Registry] 你好! " + name;
    }

}