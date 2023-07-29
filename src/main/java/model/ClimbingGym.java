package model;

public class ClimbingGym {

    private int gymId;
    private String gymName;
    private String address;
    private String phoneNumber;
    private boolean topRopeAvailable;
    private boolean boulderAvailable;

    public ClimbingGym() {}
    
    public ClimbingGym(int gymId, String gymName, String address, String phoneNumber, boolean topRopeAvailable, boolean boulderAvailable) {
        this.gymId = gymId;
        this.gymName = gymName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.topRopeAvailable = topRopeAvailable;
        this.boulderAvailable = boulderAvailable;
    }

    public int getGymId() {
        return gymId;
    }

    public void setGymId(int gymId) {
        this.gymId = gymId;
    }

    public String getGymName() {
        return gymName;
    }

    public void setGymName(String gymName) {
        this.gymName = gymName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isTopRopeAvailable() {
        return topRopeAvailable;
    }

    public void setTopRopeAvailable(boolean topRopeAvailable) {
        this.topRopeAvailable = topRopeAvailable;
    }

    public boolean isboulderAvailable() {
        return boulderAvailable;
    }

    public void setboulderAvailable(boolean boulderAvailable) {
        this.boulderAvailable = boulderAvailable;
    }

    public boolean hasBothClimbTypesAvailable() {
        return (this.topRopeAvailable && this.boulderAvailable);
    }
    
    @Override
    public String toString() {
        return "ClimbingGym{" +
                "gymId=" + gymId +
                ", gymName='" + gymName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", topRopeAvailable=" + topRopeAvailable +
                ", boulderAvailable=" + boulderAvailable +
                '}';
    }
}
