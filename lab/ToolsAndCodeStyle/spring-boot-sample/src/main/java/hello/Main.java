package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class Main implements CommandLineRunner {

    @Autowired
    private HelloService helloService;

    @Override
    public void run(String... args) {
        helloService.sayHello();
        System.exit(0);
    }

}
