package id.my.hendisantika.mongockdemo2.migration;

import id.my.hendisantika.mongockdemo2.MongockDemo2Application;
import id.my.hendisantika.mongockdemo2.client.Client;
import id.my.hendisantika.mongockdemo2.client.ClientRepository;
import io.mongock.api.annotations.BeforeExecution;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackBeforeExecution;
import io.mongock.api.annotations.RollbackExecution;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by IntelliJ IDEA.
 * Project : mongock-demo2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 07/01/25
 * Time: 08.27
 * To change this template use File | Settings | File Templates.
 */
@ChangeUnit(id="client-initializer", order = "1", author = "mongock")
public class ClientInitializerChangeUnit {

    public final static int INITIAL_CLIENTS = 10;

    @BeforeExecution
    public void beforeExecution(MongoTemplate mongoTemplate) {
        mongoTemplate.createCollection(MongockDemo2Application.CLIENTS_COLLECTION_NAME);
    }

    @RollbackBeforeExecution
    public void rollbackBeforeExecution(MongoTemplate mongoTemplate) {

        mongoTemplate.dropCollection(MongockDemo2Application.CLIENTS_COLLECTION_NAME);
    }

    @Execution
    public void execution(ClientRepository clientRepository) {
        clientRepository.saveAll(
                IntStream.range(0, INITIAL_CLIENTS)
                        .mapToObj(ClientInitializerChangeUnit::getClient)
                        .collect(Collectors.toList())
        );
    }

    @RollbackExecution
    public void rollbackExecution(ClientRepository clientRepository) {

        clientRepository.deleteAll();
    }

    private static Client getClient(int i) {
        return new Client("name-" + i, "email-" + i, "phone" + i, "country" + i);
    }
}
