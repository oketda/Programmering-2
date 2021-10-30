public class Whale extends Mammal implements Swimmable, Jumpable {

    public Whale(String name, int code) {
        super(name, code);
    }

    public boolean swim(){
        return true;
    }

    public boolean jump(){
        return true;
    }
}
