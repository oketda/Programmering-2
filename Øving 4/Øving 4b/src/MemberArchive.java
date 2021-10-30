import java.time.LocalDate;
import java.util.Random;

class MemberArchive extends BonusMember {

    MemberArchive(int memberNo, Personals personals, LocalDate enrolledDate, int points) {
        super(memberNo, personals, enrolledDate, points);
    }

    int findPoints(int memberNo, String password) {
        for (BonusMember bonusMember : bonusMembers) {
            if (bonusMember.getMemberNo() == memberNo && bonusMember.getPersonals().getPassword().equals(password)) {
                return bonusMember.getPoints();
            }
        }
        return -1;
    }

    boolean registerPoints(int memberNo, int point) {
        for (BonusMember bonusMember : bonusMembers) {
            if (bonusMember.getMemberNo() == memberNo) {
                if (bonusMember instanceof BasicMember) {
                    bonusMember.addPoints(point);
                    return true;
                } else if (bonusMember instanceof SilverMember) {
                    point = (int) (point * 1.2);
                    bonusMember.addPoints(point);
                    return true;
                } else if (bonusMember instanceof GoldMember) {
                    point = (int) (point * 1.5);
                    bonusMember.addPoints(point);
                    return true;
                }
            }
        }
        return false;
    }

    public int newMember(String surname, String firstname, String epostadr, String password, LocalDate enrolledDate){
        Personals p = new Personals(surname, firstname, epostadr, password);
        new BasicMember(findAvailableMemberNo(), p, enrolledDate);

        return bonusMembers.get(bonusMembers.size()-1).getMemberNo();
    }

    static int findAvailableMemberNo() {
        Random random = new Random();
        boolean run = true;

        while(run) {
            int newMemberNo = random.nextInt(900)+100;
            for (int i = 0; i < bonusMembers.size(); i++) {
                if (bonusMembers.get(i).getMemberNo() != newMemberNo && i == bonusMembers.size()-1){
                    return newMemberNo;
                }
                else if(bonusMembers.get(i).getMemberNo() == newMemberNo){
                    i = bonusMembers.size();
                }
            }
        }
        return 0;
    }

    void checkMembers() {
        for (int i = 0; i < bonusMembers.size(); i++) {
            if (bonusMembers.get(i) instanceof BasicMember && bonusMembers.get(i).getPoints() >= 25000 && bonusMembers.get(i).getPoints() < 75000) {
                BonusMember b1 = bonusMembers.get(i);
                SilverMember s = new SilverMember(b1.getMemberNo(), b1.getPersonals(), b1.getEnrolledDate(), b1.getPoints());
                bonusMembers.set(i, s);
            } else if ((bonusMembers.get(i) instanceof BasicMember || bonusMembers.get(i) instanceof SilverMember) && bonusMembers.get(i).getPoints() >= 75000) {
                BonusMember b1 = bonusMembers.get(i);
                GoldMember g = new GoldMember(b1.getMemberNo(), b1.getPersonals(), b1.getEnrolledDate(), b1.getPoints());
                bonusMembers.set(i, g);
            }
        }
    }

    boolean checkMembersTestSilver(int memberNo){
        for (int i = 0; i < bonusMembers.size(); i++) {
            if (bonusMembers.get(i).getMemberNo() == memberNo && bonusMembers.get(i) instanceof SilverMember){
                return true;
            }
        }
        return false;
    }

    boolean checkMembersTestGold(int memberNo){
        for (int i = 0; i < bonusMembers.size(); i++) {
            if (bonusMembers.get(i).getMemberNo() == memberNo && bonusMembers.get(i) instanceof GoldMember){
                return true;
            }
        }
        return false;
    }

    String checkMembersTest(int memberNo){
        String retur = "Denne kunden er ";
        for (int i = 0; i < bonusMembers.size(); i++) {
            if (bonusMembers.get(i).getMemberNo() == memberNo){
                retur += bonusMembers.get(i).getClass();
            }
        }
        return retur;
    }
}
