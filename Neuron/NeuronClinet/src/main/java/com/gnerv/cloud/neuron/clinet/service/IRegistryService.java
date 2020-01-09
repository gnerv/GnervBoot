package com.gnerv.cloud.neuron.clinet.service;

import java.rmi.registry.Registry;

public interface IRegistryService {

    Registry getRegistry(String host, int port);

}
