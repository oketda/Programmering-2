public class Crocodile extends Oviparous implements Swimmable, Walkable {

    public Crocodile(String name, int code) {
        super(name, code);
    }

    public boolean swim(){
        return true;
    }

    public boolean walk(){
        return true;
    }
}
