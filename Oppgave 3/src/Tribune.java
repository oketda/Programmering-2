public abstract class Tribune {

    private final String tribuneName;
    private final int capacity;
    private final int price;

    public Tribune(String tribuneName, int capacity, int price) {
        this.tribuneName = tribuneName;
        this.capacity = capacity;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getTribuneName() {
        return tribuneName;
    }

    public abstract int findNumberOfSoldTickets();


    public int findIncome(){
        return getPrice()*findNumberOfSoldTickets();
    }

    public abstract Ticket[] buyTickets(int noTickets);

    public abstract Ticket[] buyTickets(String[] names);

    public String tooString(){
        return "Tribune: " + getTribuneName() + ", capacity: " + getCapacity() + ", sold tickets: " + findNumberOfSoldTickets() + ", income: " + findIncome();
    }

}
