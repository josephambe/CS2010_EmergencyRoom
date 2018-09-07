

public class Patient {
    private String name;
    private int emergLevel;

    public Patient(String patientName, int emergencyLvl) {
        this.name = patientName;
        this.emergLevel = emergencyLvl;
    }

    //Setters
    public void setEmergLevel(int emergLevel) {
        this.emergLevel = emergLevel;
    }

    //Getters
    public String getName() {
        return this.name;
    }
    public int getEmergLevel() {
        return this.emergLevel;
    }


}
