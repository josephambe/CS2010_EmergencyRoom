import java.util.Comparator;
import java.util.List;

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

    public int compareTo(Patient p1){
        //if new patient has greater priority, returned int will be greater than zero.
        return this.getEmergLevel() - p1.getEmergLevel();
    }

}
