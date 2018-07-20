package com.sample;


import java.util.ServiceLoader;

public class ServiceLoaderDemo {
    public static void main(String[] args) {
        ServiceLoader<CPService> serviceLoader =
                ServiceLoader.load(CPService.class);
        for (CPService cpService : serviceLoader) {
            cpService.show();
        }
    }
}
