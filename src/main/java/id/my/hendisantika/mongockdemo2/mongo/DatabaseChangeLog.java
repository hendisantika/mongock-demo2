package id.my.hendisantika.mongockdemo2.mongo;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by IntelliJ IDEA.
 * Project : mongock-demo2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 07/01/25
 * Time: 08.21
 * To change this template use File | Settings | File Templates.
 */
@ChangeLog(order = "001")
public class DatabaseChangeLog {

    @ChangeSet(order = "001", id = "initialSetup", author = "hendisantika")
    public void initialSetup(MongoTemplate mongoTemplate) {
        // Example: Create a collection and insert initial documents
        mongoTemplate.createCollection("exampleCollection");
        mongoTemplate.save(new ExampleDocument("Initial Document"), "exampleCollection");
    }

    @ChangeSet(order = "002", id = "addMoreDocuments", author = "hendisantika")
    public void addMoreDocuments(MongoTemplate mongoTemplate) {
        // Example: Insert more documents
        mongoTemplate.save(new ExampleDocument("Another Document"), "exampleCollection");
    }
}
