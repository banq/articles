package com.example.moduliths.moduleB;

import com.example.moduliths.moduleB.internal.InternalComponentB;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MyComponentB {

    private final InternalComponentB internalComponentB;

    public String sayHelloB(){
        System.out.printf("this is B");
        return "this is B" + internalComponentB.sayHelloBIn();


    }
}
