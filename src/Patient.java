public class Patient {
    private String name;
    //int id;
    private int emergLevel;

    public Patient(String patientName, int emergencyLvl) {
        this.name = patientName;
        this.emergLevel = emergencyLvl;
    }



    //Setters
    public void setName(String name) { this.name = name; }
    //public void setId(int id) { this.id = id; }
    public void setEmergLevel(int emergLevel) { this.emergLevel = emergLevel; }

//Getters
    public String getName() { return this.name; }
    //public int getId() { return id; }
    public int getEmergLevel() { return this.emergLevel; }





}
