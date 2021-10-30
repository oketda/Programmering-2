package no.ntnu.idatx2001.oblig5.zoo;

import java.time.LocalDate;

public interface ScandinavianWildAnimal {

    String getName();
    LocalDate getDateOfBirth();
    int getAge();
    String getAddress();
    void move(String newAddress);
    String printInfo();
}
