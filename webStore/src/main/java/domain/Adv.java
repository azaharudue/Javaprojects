package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;


@Data
@NoArgsConstructor
@AllArgsConstructor
public final class Adv {
//Anzeige
//    id SMALLINT NOT NULL GENERATED ALWAYS AS IDENTITY,
//    titel VARCHAR(100) NOT NULL,
//    text CLOB(1M) NOT NULL,
//    preis DECIMAL(5,2) CHECK (preis >=0),
//    erstellungsdatum timestamp DEFAULT CURRENT TIMESTAMP,
//    ersteller VARCHAR(20) NOT NULL,
//    status CHAR(8) CHECK (status IN ('aktiv','verkauft')),
//    PRIMARY KEY (id),
//    FOREIGN KEY (ersteller) REFERENCES Benutzer(benutzername) ON DELETE CASCADE


    private String titel;
    private String text;

    private BigDecimal preis;

    private Timestamp erstellungsdatum;
    //correponding to benutzername
    private String ersteller;

    //status IN ('aktiv','verkauft')
    private String status;
    private String id;

}
