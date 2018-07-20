package com.example.moduliths.moduleA;

import com.example.moduliths.moduleB.MyComponentB;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MyComponentA {
    private final MyComponentB MyComponentB;

    public String sayHelloA(){
        System.out.printf("this is A");
        return "############### this is A" + MyComponentB.sayHelloB();

    }

}
