
import java.util.*;
import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

// write your matric number here:
// write your name here:
// write list of collaborators here:
// year 2018 hash code: tPW3cEr39msnZUTL2L5J (do NOT delete this line)

class EmergencyRoom {
    Patient patient;
    ArrayList<Patient> patientList;

    public EmergencyRoom() {
        patientList = new ArrayList<>();
    }

    /** Adds patients to patient list when they arrive at Hospital */
    void ArriveAtHospital(String patientName, int emergencyLvl) {

        this.patient = new Patient(patientName, emergencyLvl);
        patientList.add(this.patient);

        if(patientList.size() == 1){
            return;
        }

        Patient newPatient = this.patient;

        for(int i=patientList.size()-1; i>0; i--){ //-1 since the last index will hold the new patient.

            if(newPatient.compareTo(patientList.get(i-1)) > 0){
                patientList.set(i,patientList.get(i-1));
                patientList.set(i-1, newPatient);
            }
        }

    }



    /** Updates the Emergency Level of patients */
    void UpdateEmergencyLvl(String patientName, int incEmergencyLvl) {

        for(Patient patient: patientList){
            if(patient.getName().equals(patientName)){
                int newEmergencyLvl = patient.getEmergLevel()+incEmergencyLvl;
                patient.setEmergLevel(newEmergencyLvl);
            }
        }
    }

    /** Removes treated patients from patientList as they have been treated so are no longer "patients" */
    void Treat(String patientName) {

        patientList.remove(0);

        /*Iterator<Patient> iter = this.patientList.iterator();

        while (iter.hasNext()) {
            Patient p = iter.next();

            if (p.getName().equals(patientName))
                iter.remove();
        }*/


    }

    /** Reports the name of the patient to the doctor that needs the most attention. If there is no more patient to
     // be taken care of, return a String "The emergency suite is empty" */
    String Query() {

        //Checks if there are patients
        String noPatientsMessage = "The emergency suite is empty";
        if(patientList.isEmpty()){
            return noPatientsMessage;
        }

        Patient highestPriority = patientList.get(0);
        return highestPriority.getName();

/*        //Checks emergency level of all patients to find the highest priority patient.
        int highestEmergencyLevel = 1;
        String priorityPatientName = "";

        for(Patient p: this.patientList){
            if(p.getEmergLevel() > highestEmergencyLevel){
                priorityPatientName = p.getName();
                highestEmergencyLevel = p.getEmergLevel();
            }
        }

        return priorityPatientName;*/
    }

    void run() throws Exception {
        // do not alter this method

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int numCMD = Integer.parseInt(br.readLine()); // note that numCMD is >= N
        while (numCMD-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            switch (command) {
                case 0: ArriveAtHospital(st.nextToken(), Integer.parseInt(st.nextToken())); break;
                case 1: UpdateEmergencyLvl(st.nextToken(), Integer.parseInt(st.nextToken())); break;
                case 2: Treat(st.nextToken()); break;
                case 3: pr.println(Query()); break;
            }
        }
        pr.close();
    }

    public static void main(String[] args) throws Exception {
        // do not alter this method
        EmergencyRoom ps1 = new EmergencyRoom();
        ps1.run();
    }
}
