
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BonusMember{
    private final int memberNo;
    private final Personals personals;
    private final LocalDate enrolledDate;
    private int point = 0;


    public static ArrayList<BonusMember> bonusMembers = new ArrayList<>();


    public BonusMember(int memberNo, Personals personals, LocalDate enrolledDate, int point){
        if (personals == null || enrolledDate == null || memberNo < 1){
            throw new IllegalArgumentException();
        }
        else {
            this.memberNo = memberNo;
            this.personals = personals;
            this.enrolledDate = enrolledDate;
            this.point = point;
        }
        bonusMembers.add(this);
    }


    public int getMemberNo() {
        return memberNo;
    }

    public Personals getPersonals() {
        return personals;
    }

    public LocalDate getEnrolledDate() {
        return enrolledDate;
    }

    public int getPoints() {
        return point;
    }

    public int findQualificationPoints(LocalDate date){
        Long daysBetween = ChronoUnit.DAYS.between(enrolledDate, date);

        if (daysBetween < 365){
           return getPoints();
        }
        else {
            return 0;
        }
    }

    /**
     * Tests if the bonusmember actually has a password. If it is empty it throws an IllegalArgumentException.
     * It throws IllegalArgumentException because the content of the password is not allowed.
     * 
     * @param password String for the members password
     * @return method for checking if the password is ok.
     * @see Personals#okPassword(String) 
     */
    public boolean okPassword(String password){
        if (password.isBlank()){
            throw new IllegalArgumentException();
        }

        return this.personals.okPassword(password);
    }

    public int registerPoints(int point) {
        return 0;
    }

    public void addPoints(int point){
        this.point += point;
    }

}
