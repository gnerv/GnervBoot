package com.gnerv.cloud.neuron.service.impl;

import com.gnerv.cloud.neuron.service.IRegistryService;
import com.gnerv.cloud.neuron.store.RegistryStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

@Slf4j
@Service
public class RegistryServiceImpl implements IRegistryService {

    @Override
    public void rebindService(Remote remote) {
        // 创建一个远程对象
        try {
            RegistryStore.getRegistry().rebind("HelloRegistry", remote);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        System.out.println("======= 启动RMI服务成功! =======");
    }

}
