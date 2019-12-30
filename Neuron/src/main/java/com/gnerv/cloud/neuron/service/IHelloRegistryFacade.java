package com.gnerv.cloud.neuron.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IHelloRegistryFacade extends Remote {

    String helloWorld(String name) throws RemoteException;

}