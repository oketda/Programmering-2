import java.time.LocalDate;

public class GoldMember extends BonusMember {

    public GoldMember(int memberNo, Personals personals, LocalDate enrolledDate, int points){
        super(memberNo, personals, enrolledDate, points);
    }

    public int registerPoints(int point) {
        point = (int)(point*1.5);
        addPoints(point);
        return getPoints();
    }
}
