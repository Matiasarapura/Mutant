package meli.challenge.mutante;

import meli.challenge.mutante.persistence.RedisManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MutantApplication {

    @Value("${redis.host}")
    private String redisHost;

    @Value("${redis.port}")
    private Integer redisPort;

    public static void main(String[] args) {
        SpringApplication.run(MutantApplication.class, args);

    }
    @Bean
    public CommandLineRunner run() {
        RedisManager.init(redisHost,redisPort);


        return null;
    }

}
