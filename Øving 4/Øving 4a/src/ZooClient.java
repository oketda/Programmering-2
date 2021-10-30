import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
public class ZooClient {

    public static void main(String[] args) {

        Zoo zoo = new Zoo("Kristiansand Dyrepark");

        Collection<Animal> animals = new ArrayList<Animal>();

        animals.add(new Crocodile("Crocodylus niloticus", 1001));
        animals.add(new Crocodile("Crocodylus niloticus", 1002));
        animals.add(new Crocodile("Crocodylus porosus", 1101));
        animals.add(new Crocodile("Crocodylus porosus", 1102));
        animals.add(new Pelican("Brown Pelican  ", 4001));
        animals.add(new Pelican("Dalmatian Pelican  ", 4101));


        animals.add(new Whale("Blue whale", 2001));
        animals.add(new Whale("Blue whale", 2002));
        animals.add(new Whale("Minke whale", 2101));
        animals.add(new Whale("Minke whale", 2102));
        animals.add(new Bat("Acerodon ", 3001));
        animals.add(new Bat("Cistugo  ", 3002));
        zoo.setAnimals(animals);

        System.out.println(animals.stream().filter(p -> p instanceof Flyable).collect(Collectors.toList()));
        List<Object> flyer = animals.stream().filter(p -> p instanceof Flyable).collect(Collectors.toList());
        System.out.println();

        flyer.stream().forEach(p -> {
            System.out.println(((Flyable) p).fly());
        });

        System.out.println();
        System.out.println(animals.stream().filter(p -> p instanceof Mammal && p instanceof Jumpable).collect(Collectors.toList()));
        List<Object> mammalJumper = animals.stream().filter(p -> p instanceof Mammal && p instanceof Jumpable).collect(Collectors.toList());
        System.out.println();

        mammalJumper.stream().forEach(p -> {
            System.out.println(((Jumpable) p).jump());
        });

        List<Object> walker = zoo.getAnimals().stream().filter(p -> p instanceof Walkable).collect(Collectors.toList());
        System.out.println();
        try {
            walker.stream().forEach(p -> {
                ((Flyable) p).fly();
            });
        }
        catch (Exception e){
            e = new ZooException("Animal cant fly.");
            System.out.println(e);
            System.exit(1);
        }

        System.out.println("Test");
    }
}