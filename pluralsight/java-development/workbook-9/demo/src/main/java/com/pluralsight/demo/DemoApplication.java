package com.pluralsight.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(DemoApplication.class, args);

/*		for(String bean: context.getBeanDefinitionNames()){
			System.out.println(bean);
		}*/

        /*Product product = context.getBean(Product.class);
        System.out.println(product);*/


        Product product = null;

        if (context.containsBean("pepsi")) {
            product = context.getBean("pepsi", Product.class);
        } else if (context.containsBean("coke")) {
            product = context.getBean("coke", Product.class);
        } else {
            product = context.getBean("hotChocolate", Product.class);
        }

        System.out.println(product);
    }

}
