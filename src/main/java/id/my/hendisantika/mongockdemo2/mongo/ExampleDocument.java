package id.my.hendisantika.mongockdemo2.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "exampleCollection")
public class ExampleDocument {

    @Id
    private String id;
    private String name;

    public ExampleDocument(String name) {
        this.name = name;
    }
}
