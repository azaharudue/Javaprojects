package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

//Benutzer
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class User {
    //user name
    private String benutzername;
    //real name
    private String name;
    //create date
    private Timestamp eintrittsdatum;


}