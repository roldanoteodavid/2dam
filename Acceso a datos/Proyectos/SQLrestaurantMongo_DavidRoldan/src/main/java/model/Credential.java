package model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Credential {
    private ObjectId _id;
    private String user_name;
    private String password;

    public ObjectId getId() {
        return _id;
    }
}
