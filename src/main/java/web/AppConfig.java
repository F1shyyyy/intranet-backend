package web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;


@Configuration
public class AppConfig {
    @Bean
    public RmiProxyFactoryBean userService() {
        RmiProxyFactoryBean factory = new RmiProxyFactoryBean();
    }
}
