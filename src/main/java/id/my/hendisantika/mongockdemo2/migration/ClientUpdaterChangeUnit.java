package id.my.hendisantika.mongockdemo2.migration;

import id.my.hendisantika.mongockdemo2.MongockDemo2Application;
import id.my.hendisantika.mongockdemo2.client.Client;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import org.springframework.data.mongodb.core.MongoTemplate;

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
@ChangeUnit(id="client-updater", order = "2", author = "mongock")
public class ClientUpdaterChangeUnit {

    @Execution
    public void execution(MongoTemplate mongoTemplate) {

        mongoTemplate.findAll(Client.class, MongockDemo2Application.CLIENTS_COLLECTION_NAME)
                .stream()
                .map(client -> client.setName(client.getName() + "_updated"))
                .forEach(client -> mongoTemplate.save(client, MongockDemo2Application.CLIENTS_COLLECTION_NAME));
    }

    @RollbackExecution
    public void rollbackExecution(MongoTemplate mongoTemplate) {

        mongoTemplate.findAll(Client.class, MongockDemo2Application.CLIENTS_COLLECTION_NAME)
                .stream()
                .map(client -> client.setName(client.getName().substring(0, client.getName().length() - "_updated".length())))
                .forEach(client -> mongoTemplate.save(client, MongockDemo2Application.CLIENTS_COLLECTION_NAME));

    }
}
