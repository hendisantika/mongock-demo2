package id.my.hendisantika.mongockdemo2;

import id.my.hendisantika.mongockdemo2.client.ClientRepository;
import io.mongock.runner.springboot.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongock
@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = ClientRepository.class)
public class MongockDemo2Application {

    public final static String CLIENTS_COLLECTION_NAME = "clientCollection";

    public static void main(String[] args) {
//        SpringApplication.run(MongockDemo2Application.class, args);
        getSpringAppBuilder().run(args);
    }

    public static SpringApplicationBuilder getSpringAppBuilder() {
        return new SpringApplicationBuilder().sources(MongockDemo2Application.class);
    }
}
