package com.gnerv.cloud.neuron.service;

import java.rmi.Remote;
import java.rmi.registry.Registry;

public interface IRegistryService {

    void rebindService(Remote remote);

}
