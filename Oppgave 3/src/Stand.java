public class Stand extends Tribune{

    private int noSoldTickets;

    public Stand(String tribuneName, int capacity, int price, int noSoldTickets){
        super(tribuneName, capacity, price);
        this.noSoldTickets += noSoldTickets;
    }

    @Override
    public int findNumberOfSoldTickets(){
        return noSoldTickets;
    }

    @Override
    public int findIncome(){
        return super.findIncome();
    }

    @Override
    public Ticket[] buyTickets(int noTickets){
        StandingTicket[] tickets = new StandingTicket[noTickets];

        if (noSoldTickets+noTickets < getCapacity()){
            noSoldTickets += noTickets;

            for (int i = 0; i < noTickets; i++) {
                StandingTicket ticket = new StandingTicket(getTribuneName(), getPrice());
                tickets[i] = (ticket);
            }

            return tickets;
        }
        return null;
    }

    @Override
    public Ticket[] buyTickets(String[] names){
        return null;
    }
}
