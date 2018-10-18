package hello;

import org.springframework.stereotype.Service;

import static hello.HelloService.Messages.greetings;

@Service
public class HelloService {

    public static class Messages {
        public static final String greetings = "Greetings from yuanfudao!";
    }
    
    public void sayHello() {
        System.out.println(greetings);
    }
    
}
