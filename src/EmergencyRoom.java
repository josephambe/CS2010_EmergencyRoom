
import java.util.*;
import java.io.*;


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
        patientList.add(this.patient); //add element to bottom of heap.

        if(patientList.size() == 1){ //there is only one patient in the list
            return;
        }

        bubbleUp(patientList.size()-1);

    }


    public void bubbleUp(int index){
        //Find new patient at end of list
        Patient newPatient = patientList.get(index);

        //Find parent node of new patient
        int parentIndex = (index-1)/2;
        Patient parentPatient = patientList.get(parentIndex);

        //if new patient has emergency level more urgent than it's parent, stop.
        if(index == 0 || (parentPatient.getEmergLevel() - newPatient.getEmergLevel()) >= 0){
            return;
        }

        //Swap child and parent
        patientList.set(parentIndex,newPatient);
        patientList.set(index, parentPatient);

        bubbleUp(parentIndex);

    }

    /** Removes treated patients from patientList as they have been treated so are no longer "patients" */
    void Treat(String patientName) {

        //replace the root with the last patient in list
        Patient lowestPriority = patientList.get(patientList.size()-1);
        patientList.set(0, lowestPriority);

        //remove patient who has been shifted unless there is only one Patient in list
        if(patientList.size() == 0){
            return;
        } else {
            patientList.remove(patientList.size()-1);
        }

        sinkDown(0);
    }


    public void sinkDown(int index){
        //check that Patient at index has children
        if(isLeaf(index)){
            return;
        }

        Patient parentPatient = patientList.get(index);
        int rightChildIndex = index * 2 + 2;
        int leftChildIndex = index * 2 + 1;
        int minChildIndex;

        //if the right child doesn't exist then the left child has smallest emergency level.
        if(rightChildIndex > patientList.size()-1){
            minChildIndex = leftChildIndex;
       } else {

            //find child with smallest emergency level
            Patient leftChild = patientList.get(leftChildIndex);
            Patient rightChild = patientList.get(rightChildIndex);
            if(leftChild.getEmergLevel() > rightChild.getEmergLevel()){
                minChildIndex = leftChildIndex;
            } else {
                minChildIndex = rightChildIndex;
            }
        }

        Patient minPatient = patientList.get(minChildIndex);

        //check if parent is already in order.
        if(parentPatient.getEmergLevel() >= minPatient.getEmergLevel()){
            return;
        }

        patientList.set(minChildIndex, parentPatient);
        patientList.set(index, minPatient);

        sinkDown(minChildIndex);
    }

    public boolean isLeaf(int index){
        //checking if left child index is the same or greater than size of PatientList
        //if it is, child doesn't exist.
        return(index*2+1 >= patientList.size()-1);
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
