import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class testKlient {



    public static void main(String[] args) {

        Stand standObjNorth = new Stand("North end", 450, 120, 10);
        Stand standObjSouth = new Stand("South end", 380, 150, 5);
        Sit sitObj = new Sit("East end", 300, 135, 25);
        VIP vipObj = new VIP("VIP", 250, 220, 25);

        Tribune[] tribunes = new Tribune[4];

        tribunes[0] = standObjNorth;
        tribunes[1] = standObjSouth;
        tribunes[2] = sitObj;
        tribunes[3] = vipObj;

        System.out.println();
        System.out.println("Test for buyTickets() in Sit.java:");
        Ticket[] ticketsTest = sitObj.buyTickets(15);


        for (int i = 0; i < ticketsTest.length; i++) {
            System.out.println(ticketsTest[i].toString());
        }

        System.out.println();

        System.out.println("Test for buyTickets() multiple times in Sit.java:");
        System.out.println();
        ticketsTest = sitObj.buyTickets(10);

        for (int i = 0; i < ticketsTest.length; i++) {
            System.out.println(ticketsTest[i].toString());
        }


        String[] nameTest = {"Herman Aaagaard", "Bjørn Helseth", "Michael Westerby"};
        ticketsTest = vipObj.buyTickets(nameTest);
        System.out.println();
        System.out.println("Test for buyTickets() in VIP");

        for (int i = 0; i < ticketsTest.length; i++) {
            System.out.println(ticketsTest[i].toString());
        }


        String[] nameTest2 = {"Odne Kvåle", "Brage Ramsrud", "Mathias Helseth", "Jon Langtangen", "Odne Kvåle", "Brage Ramsrud", "Mathias Helseth", "Jon Langtangen"};
        ticketsTest = vipObj.buyTickets(nameTest2);
        System.out.println();
        System.out.println("Test for buyTickets() in VIP multiple times");

        for (int i = 0; i < ticketsTest.length; i++) {
            System.out.println(ticketsTest[i].toString());
        }


        ticketsTest = vipObj.buyTickets(nameTest2);
        System.out.println();
        System.out.println("Test for buyTickets() in VIP");

        for (int i = 0; i < ticketsTest.length; i++) {
            System.out.println(ticketsTest[i].toString());
        }

        System.out.println();
        System.out.println("Test for buyTickets() in Stand");
        ticketsTest = standObjSouth.buyTickets(20);
        for (int i = 0; i < ticketsTest.length; i++) {
            System.out.println(ticketsTest[i].toString());
        }

        System.out.println();
        System.out.println("Test for findIncome():");
        System.out.println("Test for object 1 of Stand");
        System.out.println(standObjNorth.findIncome());
        System.out.println("Test for object 2 of Stand");
        System.out.println(standObjSouth.findIncome());
        System.out.println("Test for object of Sit");
        System.out.println(sitObj.findIncome());
        System.out.println("Test for object of VIP");
        System.out.println(vipObj.findIncome());

        System.out.println();
        System.out.println("List of all tribunes:");
        for (int i = 0; i < tribunes.length; i++) {
            System.out.println(tribunes[i].tooString());
        }

        Arrays.sort(tribunes, Comparator.comparingInt(Tribune::findIncome).reversed());

        System.out.println();
        System.out.println("Sorted list:");
        for (int i = 0; i < tribunes.length; i++) {
            System.out.println(tribunes[i].tooString());
        }
    }
}
