import java.util.Arrays;

public class Sit extends Tribune{

    private int[] noBusy;
    private final int NO_ROWS;

    public Sit(String tribuneName, int capacity, int price, int NO_ROWS) {
        super(tribuneName, capacity, price);
        this.NO_ROWS = NO_ROWS;
        this.noBusy = new int[this.NO_ROWS];
    }

    @Override
    public int findNumberOfSoldTickets(){
        return Arrays.stream(noBusy).sum();
    }

    @Override
    public int findIncome(){
        return super.findIncome();
    }

   @Override
   public Ticket[] buyTickets(int noTickets){
       SittingTicket[] sittingTickets = new SittingTicket[noTickets];

       int seatsPerRow = getCapacity()/NO_ROWS;
       int seatNr = 0;

       for (int i = 0; i < noBusy.length; i++) {
           for (int j = 0; j < seatsPerRow; j++) {
               if (noTickets > 0 && noBusy[i] < seatsPerRow) {
                    noBusy[i] += 1;
                    SittingTicket sittingTicket = new SittingTicket(getTribuneName(), getPrice(), i+1, noBusy[i]);
                    sittingTickets[seatNr] = sittingTicket;
                    noTickets = noTickets-1;
                    seatNr++;
               }
               else if (noTickets == 0){
                   return sittingTickets;
               }
           }
       }

       return null;
   }

    @Override
    public Ticket[] buyTickets(String[] names){
        return null;
    }
}
