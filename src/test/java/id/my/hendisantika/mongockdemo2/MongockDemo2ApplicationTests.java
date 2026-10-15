package id.my.hendisantika.mongockdemo2;

import id.my.hendisantika.mongockdemo2.client.Client;
import id.my.hendisantika.mongockdemo2.client.ClientRepository;
import id.my.hendisantika.mongockdemo2.migration.ClientInitializerChangeUnit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.mongodb.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
class MongockDemo2ApplicationTests {

    @Container
    @ServiceConnection
    static MongoDBContainer mongo = new MongoDBContainer(composeMongoImage()).withReplicaSet();

    @Autowired
    private ClientRepository clientRepository;

    @Test
    void migrationsInsertAndUpdateClients() {
        List<Client> clients = clientRepository.findAll();

        assertThat(clients).hasSize(ClientInitializerChangeUnit.INITIAL_CLIENTS);
        assertThat(clients).allMatch(client -> client.getName().endsWith("_updated"));
    }

    /**
     * Uses the same MongoDB image as compose.yaml, so Dependabot's compose updates also apply to this test.
     */
    private static String composeMongoImage() {
        try {
            Matcher matcher = Pattern.compile("^\\s*image:\\s*(mongo:\\S+)", Pattern.MULTILINE)
                    .matcher(Files.readString(Path.of("compose.yaml")));
            if (!matcher.find()) {
                throw new IllegalStateException("No mongo image found in compose.yaml");
            }
            return matcher.group(1);
        } catch (IOException e) {
            throw new IllegalStateException("Cannot read compose.yaml", e);
        }
    }

}
