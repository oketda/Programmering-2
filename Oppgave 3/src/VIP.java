public class VIP extends Tribune{

    private String[][] spectator; //tilskuere: antall rader * antall plasser pr rad
    private final int NO_ROWS;

    public VIP(String tribuneName, int capacity, int price, int NO_ROWS) {
        super(tribuneName, capacity, price);
        this.NO_ROWS = NO_ROWS;
        this.spectator = new String[this.NO_ROWS][capacity/this.NO_ROWS];
    }

    @Override
    public int findNumberOfSoldTickets(){
        int noSoldTickets = 0;
        for (int i = 0; i < spectator.length; i++) {
            for (int j = 0; j < spectator[i].length; j++) {
                if (spectator[i][j] != null){
                    noSoldTickets += 1;
                }
            }
        }
        return noSoldTickets;
    }

    @Override
    public int findIncome(){
        return super.findIncome();
    }

    @Override
    public Ticket[] buyTickets(int noTickets) {
        return null;
    }

    @Override
    public Ticket[] buyTickets(String[] names){
        SittingTicket[] sittingTickets = new SittingTicket[names.length];
        int nameNr = 0;
        for (int i = 0; i < spectator.length; i++) {
            for (int j = 0; j < spectator[i].length; j++) {
                if (spectator[i][j] == null && nameNr < sittingTickets.length && names[nameNr] != null){
                    spectator[i][j] = names[nameNr];
                    SittingTicket sittingTicket = new SittingTicket(getTribuneName(), getPrice(), i+1, j+1);
                    sittingTickets[nameNr] = sittingTicket;
                    nameNr++;
                }
                else if (nameNr == names.length){
                    return sittingTickets;
                }
            }
        }
        return null;
    }
}
