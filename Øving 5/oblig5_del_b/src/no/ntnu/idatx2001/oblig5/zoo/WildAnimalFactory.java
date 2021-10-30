package no.ntnu.idatx2001.oblig5.zoo;

import java.time.LocalDate;

public class WildAnimalFactory {

    private static WildAnimalFactory single_instance = null;

    private WildAnimalFactory(){}



    public ScandinavianWildAnimal newMaleBear(String name,
                                              LocalDate birthDate,
                                              LocalDate arrivalDate,
                                              String address){
        return new MaleIndividual("Bjørn", "Ursus arctos", "Ursidae", arrivalDate, name, birthDate, true, address);
    }

    public ScandinavianWildAnimal newFemaleWolf(String name,
                                                LocalDate birthDate,
                                                LocalDate arrivalDate,
                                                String address,
                                                int noLitters) throws IllegalArgumentException{
        if (name.equals("")){
            throw new IllegalArgumentException("Animal must have a name");
        }
        else {
            return new FemaleIndividual("Ulv", "Canis lupus", "Canidae", arrivalDate, name, birthDate, true, address, noLitters);
        }
    }

    public ScandinavianWildAnimal newMaleWolf(String name,
                                              LocalDate birthDate,
                                              LocalDate arrivalDate,
                                              String address){
        return new MaleIndividual("Ulv", "Canis lupus", "Canidae", arrivalDate, name, birthDate, true, address);
    }

    public static WildAnimalFactory getInstance(){
        if (single_instance == null){
           single_instance = new WildAnimalFactory();
        }
        return single_instance;
    }

}
