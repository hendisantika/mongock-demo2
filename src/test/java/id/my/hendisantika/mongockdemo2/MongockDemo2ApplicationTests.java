package id.my.hendisantika.mongockdemo2;

import id.my.hendisantika.mongockdemo2.client.Client;
import id.my.hendisantika.mongockdemo2.client.ClientRepository;
import id.my.hendisantika.mongockdemo2.migration.ClientInitializerChangeUnit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
class MongockDemo2ApplicationTests {

    @Container
    @ServiceConnection
    static MongoDBContainer mongo = new MongoDBContainer("mongo:8.0.4");

    @Autowired
    private ClientRepository clientRepository;

    @Test
    void migrationsInsertAndUpdateClients() {
        List<Client> clients = clientRepository.findAll();

        assertThat(clients).hasSize(ClientInitializerChangeUnit.INITIAL_CLIENTS);
        assertThat(clients).allMatch(client -> client.getName().endsWith("_updated"));
    }

}
