package model;

public class Climber {

    private int climberId;
    private String name;
    private String baseline;
    private String climbTypePreference;
    private boolean isMember;

    public Climber() {}

    public Climber(int climberId, String name, String baseline, String climbTypePreference, boolean isMember) {
        this.climberId = climberId;
        this.name = name;
        this.baseline = baseline;
        this.climbTypePreference = climbTypePreference;
        this.isMember = isMember;
    }

    public int getClimberId() { return climberId; }

    public void setClimberId(int id) { this.climberId = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBaseline() {
        return baseline;
    }

    public void setBaseline(String baseline) {
        this.baseline = baseline;
    }

    public String getClimbTypePreference() {
        return climbTypePreference;
    }

    public void setClimbTypePreference(String climbTypePreference) {
        this.climbTypePreference = climbTypePreference;
    }

    public boolean isMember() {
        return isMember;
    }

    public void setMember(boolean member) {
        isMember = member;
    }

    @Override
    public String toString() {
        return "Climber{" +
                "climberId=" + climberId +
                "name='" + name + '\'' +
                ", baseline='" + baseline + '\'' +
                ", climbTypePreference='" + climbTypePreference + '\'' +
                ", isMember=" + isMember +
                '}';
    }
}
