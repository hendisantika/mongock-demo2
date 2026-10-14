package id.my.hendisantika.mongockdemo2.client;

import id.my.hendisantika.mongockdemo2.MongockDemo2Application;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * Project : mongock-demo2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 07/01/25
 * Time: 08.26
 * To change this template use File | Settings | File Templates.
 */
@Repository(MongockDemo2Application.CLIENTS_COLLECTION_NAME)
public interface ClientRepository extends MongoRepository<Client, String> {
}
