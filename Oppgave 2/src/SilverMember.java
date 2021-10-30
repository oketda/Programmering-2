import java.time.LocalDate;

public class SilverMember extends BonusMember {

    public SilverMember(int memberNo, Personals personals, LocalDate enrolledDate, int points){
        super(memberNo, personals, enrolledDate, points);
    }

    @Override
    public int registerPoints(int point) {
        point = (int)(point*1.2);
        addPoints(point);
        return getPoints();
    }
}
