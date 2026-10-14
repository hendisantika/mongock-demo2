package id.my.hendisantika.mongockdemo2.config;

import io.mongock.driver.api.driver.ConnectionDriver;
import io.mongock.driver.mongodb.springdata.v3.SpringDataMongoV3Driver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by IntelliJ IDEA.
 * Project : mongock-demo2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 07/01/25
 * Time: 16.25
 * To change this template use File | Settings | File Templates.
 */
@Configuration
public class MongockConfig {

    @Bean
    public ConnectionDriver connectionDriver(MongoTemplate mongoTemplate) {
        return SpringDataMongoV3Driver.withDefaultLock(mongoTemplate);
    }
//    @Bean
//    public ConnectionDriver connectionDriver(MongoTemplate mongoTemplate) {
//        SpringDataMongoV3Driver driver = SpringDataMongoV3Driver.withDefaultLock(mongoTemplate);
//        driver.setTransactionEnabled(false); // Disable transactions
//        return driver;
//    }
}
