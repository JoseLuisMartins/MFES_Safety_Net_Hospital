package cli;


import SafetyNetHospital.*;
import SafetyNetHospital.quotes.*;
import cli.menus.*;
import cli.vdm_enum.AGREEMENT;
import cli.vdm_enum.SPECIALTY;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;
import org.overture.codegen.runtime.VDMSet;

import javax.print.Doc;
import java.util.*;


public class SafetyNetHospital_CLI {

    public static void main(String[] args) {
        SafetyNetHospital_CLI safetyNet = new SafetyNetHospital_CLI();
    }

    private SafetyNetNetwork safetyNet;
    private TextIO textIO;
    private TextTerminal terminal;

    public SafetyNetHospital_CLI() {
        this.safetyNet = SafetyNetNetwork.getInstance();
        this.textIO = TextIoFactory.getTextIO();
        this.terminal = textIO.getTextTerminal();

        VDMSet agreement = new VDMSet();
        agreement.add(getAgreement(AGREEMENT.ADSE));
        agreement.add(getAgreement(AGREEMENT.MEDIS));
        safetyNet.addHospital(new Hospital("Sao Joao",new ModelUtils.Location("Porto","rua x n y","4520-885"), agreement));
        VDMSet agreement1 = new VDMSet();
        agreement1.add(getAgreement(AGREEMENT.MULTICARE));
        agreement1.add(getAgreement(AGREEMENT.MEDICARE));
        safetyNet.addHospital(new Hospital("Santa Maria",new ModelUtils.Location("Lisboa","Rua x blablabla","4880-724"),agreement1));


        safetyNet.addDoctor(new Doctor("Jose",20,getSpecialty(SPECIALTY.ORTOPEDIA)));
        safetyNet.addDoctor(new Doctor("Marcelo",19,getSpecialty(SPECIALTY.CARDIOLOGIA)));

        safetyNet.addPatient(new Patient("Maria",14,"gripe"));
        safetyNet.addPatient(new Patient("Joana",20,"tosse"));
        safetyNet.addPatient(new Patient("Felizberta",22,"asma"));



        this.start();
    }


    public SPECIALTY getSpecialty(Object specialty){

        if( specialty instanceof  ORTOPEDIAQuote)
            return SPECIALTY.ORTOPEDIA;
        else if(specialty instanceof  CARDIOLOGIAQuote)
            return SPECIALTY.CARDIOLOGIA;
        else if(specialty instanceof  OFTALMOLOGIAQuote)
            return SPECIALTY.OFTALMOLOGIA;
        else if(specialty instanceof  DERMATOLOGIAQuote)
            return SPECIALTY.DERMATOLOGIA;
        else if(specialty instanceof  GINECOLOGIAQuote)
            return SPECIALTY.GINECOLOGIA;
        else if(specialty instanceof  NEUROLOGIAQuote)
            return SPECIALTY.NEUROLOGIA;
        else if(specialty instanceof  PEDIATRIAQuote)
            return SPECIALTY.PEDIATRIA;
        else if(specialty instanceof  REUMATOLOGIAQuote)
            return SPECIALTY.REUMATOLOGIA;
        else if(specialty instanceof  UROLOGIAQuote)
            return SPECIALTY.UROLOGIA;
        else if(specialty instanceof  PNEUMOLOGIAQuote)
            return SPECIALTY.PNEUMOLOGIA;

        return null;
    }


    public Object getSpecialty(SPECIALTY specialty){
        switch (specialty) {
            case ORTOPEDIA:
                return new ORTOPEDIAQuote();
            case CARDIOLOGIA:
                return new CARDIOLOGIAQuote();
            case OFTALMOLOGIA:
                return new OFTALMOLOGIAQuote();
            case DERMATOLOGIA:
                return new DERMATOLOGIAQuote();
            case GINECOLOGIA:
                return new GINECOLOGIAQuote();
            case NEUROLOGIA:
                return new NEUROLOGIAQuote();
            case PEDIATRIA:
                return new PEDIATRIAQuote();
            case REUMATOLOGIA:
                return new REUMATOLOGIAQuote();
            case UROLOGIA:
                return new UROLOGIAQuote();
            case PNEUMOLOGIA:
                return new PNEUMOLOGIAQuote();
        }

        return null;
    }

    public Object getAgreement(AGREEMENT agreement){
        switch (agreement) {
            case ADSE:
                return new ADSEQuote();
            case MEDICARE:
                return new MEDICAREQuote();
            case MEDIS:
                return new MEDISQuote();
            case MULTICARE:
                return new MULTICAREQuote();
            case NONE:
                break;
        }
        return null;
    }

    public void start(){
        separator();
        this.terminal.printf("-----------------------------------------------------------------------\n" +
                             "----------------------Safety Net Hospital---------------------\n" +
                             "-----------------------------------------------------------------------\n"
        );

        START_MENU val = textIO.newEnumInputReader(START_MENU.class)
                .read("Select an option!");

        switch (val){
            case HOSPITALS:
                hospitals();
                break;
            case DOCTORS:
                doctors();
                break;
            case APPOINTMENTS:
                appointments();
                break;
            case PATIENTS:
                patients();
                break;
            case EXIT:
            default:
                System.exit(0);
                break;
        }
    }

    public void hospitals(){
        separator();
        this.terminal.printf("--------------------------Hospitals Management--------------------------\n");

        HOSPITAL_MENU val = textIO.newEnumInputReader(HOSPITAL_MENU.class)
                .read("Select an option!");
        int i = 0;

        switch (val){

            case ASSOCIATE_DOCTOR:
                this.terminal.printf("\nSelect an Hospital\n");
                Number hosId = getMapSelectedElement(safetyNet.getHospitals());
                separator();
                this.terminal.printf("\nSelect a Doctor\n");
                Number docId = getMapSelectedElement( safetyNet.getDoctors());

                if(hosId.intValue() == -1 || docId.intValue() == -1)
                    hospitals();

                safetyNet.associateDoctorToHospital(hosId,docId);
                hospitals();
                break;
            case DISASSOCIATE_DOCTOR:
                this.terminal.printf("\nSelect an Hospital\n");
                Number hospId = getMapSelectedElement(safetyNet.getHospitals());
                separator();
                this.terminal.printf("\nSelect a Doctor\n");
                Hospital h = safetyNet.getHospitalsById(hospId);
                List<Integer> list = new ArrayList<Integer>(h.getDoctorsIds());
                HashMap<Number,Object> hospitalDocs =new HashMap();

                for (int j = 0; j < list.size(); j++) {
                   hospitalDocs.put(list.get(j),safetyNet.getDoctorById(list.get(j)));
                }

                Number doctId = getMapSelectedElement(hospitalDocs);

                if(hospId.intValue() == -1 || doctId.intValue() == -1)
                    hospitals();

                safetyNet.disassociateDoctorFromHospital(hospId,doctId);
                hospitals();
                break;
            case ADD:
                String name = textIO.newStringInputReader()
                        .read("Hospital Name");

                String city = textIO.newStringInputReader()
                        .read("City");

                String address = textIO.newStringInputReader()
                        .read("Address");

                String postalCode = textIO.newStringInputReader()
                        .read("Postal-Code");

                VDMSet agreements = new VDMSet();
                boolean parsingAgreements = true;
                do {

                AGREEMENT agreement = textIO.newEnumInputReader(AGREEMENT.class)
                        .read("Select an option!");

                if (agreement == AGREEMENT.NONE)
                    parsingAgreements=false;
                else
                    agreements.add(getAgreement(agreement));
                }while(parsingAgreements);

                safetyNet.addHospital(new Hospital(name,new ModelUtils.Location(city,address,postalCode),agreements));
                hospitals();

                break;
            case REMOVE:
                HashMap<Number,Object> m = safetyNet.getHospitals();
                Number rmId = getMapSelectedElement(m);
                if(rmId.intValue() == -1)
                    hospitals();
                else {
                    safetyNet.removeHospital((Hospital) m.get(rmId));
                    hospitals();
                }
                break;
            case SEARCH:
                searchHospital();
                break;
            case BACK:
                start();
                break;
            case EXIT:
            default:
                System.exit(0);
                break;
        }
    }


    public void searchHospital(){
        separator();
        this.terminal.printf("--------------------------Search Hospitals--------------------------\n");

        SEARCH_HOSPITALS_OPTIONS_MENU val = textIO.newEnumInputReader(SEARCH_HOSPITALS_OPTIONS_MENU.class)
                .read("Select an option!");

        List<Hospital> list;
        int i = 0;

        switch (val){
            case SEE_ALL:
                i=0;
                for (Map.Entry<Number,Hospital> entry : new HashMap<Number,Hospital>(safetyNet.getHospitals()).entrySet())
                {
                    displayHospital(i,entry.getValue());
                    i++;
                }
                backToHospitalSearchMenu();
                break;
            case BY_NAME:
                String name = textIO.newStringInputReader()
                        .read("Hospital Name");

                list = new ArrayList<Hospital>(safetyNet.getHospitalsByName(name));

                i=0;
                for (int j = 0; j < list.size(); j++) {
                   displayHospital(i,list.get(j));
                   i++;
                }
                backToHospitalSearchMenu();
                break;
            case BY_SPECIALTY:
                SPECIALTY specialty = textIO.newEnumInputReader(SPECIALTY.class)
                        .read("Select a Specialty!");


                list = new ArrayList<Hospital>(safetyNet.getHospitalsBySpecialty(getSpecialty(specialty)));

                i=0;
                for (int j = 0; j < list.size(); j++) {
                    displayHospital(i,list.get(j));
                    i++;
                }
                backToHospitalSearchMenu();
                break;
            case BY_AGREEMENT:
                AGREEMENT agreement = textIO.newEnumInputReader(AGREEMENT.class)
                        .read("Select an Agreement!");


                list = new ArrayList<Hospital>(safetyNet.getHospitalsBySpecialty(getAgreement(agreement)));

                i=0;
                for (int j = 0; j < list.size(); j++) {
                    displayHospital(i,list.get(j));
                    i++;
                }
                backToHospitalSearchMenu();
                break;
            case BY_CITY:
                String city = textIO.newStringInputReader()
                        .read("Hospital City");

                list = new ArrayList<Hospital>(safetyNet.getHospitalsByCity(city));

                i=0;
                for (int j = 0; j < list.size(); j++) {
                    displayHospital(i,list.get(j));
                    i++;
                }
                backToHospitalSearchMenu();
                break;
            case BACK:
                hospitals();
                break;
            case EXIT:
                System.exit(0);
                break;
        }
    }

    private void backToHospitalSearchMenu(){
        this.terminal.printf("\n\n");

        BACK v = textIO.newEnumInputReader(BACK.class)
                .read("Select an option!");

        switch (v) {
            case BACK:
                searchHospital();
                break;
        }
    }

    public Number getMapSelectedElement(HashMap<Number,Object> map){

        List<Integer> values= new ArrayList<Integer>();

        int i=0;
        for (Map.Entry<Number,Object> entry : map.entrySet())
        {
            if(entry.getValue() instanceof Hospital)
                displayHospital(i,(Hospital)entry.getValue());
            else if (entry.getValue() instanceof Doctor)
                displayDoctor(i,(Doctor)entry.getValue());
            else if (entry.getValue() instanceof Patient)
                displayPatient(i,(Patient) entry.getValue());
            else if (entry.getValue() instanceof Appointment)
                displayAppointment(i,(Appointment) entry.getValue());
            values.add(i);
            i++;
        }

        if(values.size() == 0) {
            this.terminal.printf( "\n\n --------------- No available Items ------------- \n\n\n");
            BACK v = textIO.newEnumInputReader(BACK.class)
                    .read("Select an option!");
            return -1;
        }

        this.terminal.printf("\nChoose an element\n");
        int rmId = textIO.newIntInputReader()
                .withInlinePossibleValues(values)
                .read("Key");



        return (Number) map.keySet().toArray()[rmId];
    }

    public Object getSetSelectedElement(ArrayList<Object> set){

        List<Integer> values= new ArrayList<Integer>();

        int i=0;
        for (int j = 0; j < set.size(); j++)
        {
            Object val = set.get(j);

            if(val instanceof Hospital)
                displayHospital(i,(Hospital) val);
            else if (val instanceof Doctor)
                displayDoctor(i,(Doctor) val);
            else if (val instanceof Patient)
                displayPatient(i,(Patient) val);
            else if (val instanceof Appointment)
                displayAppointment(i,(Appointment) val);
            values.add(i);
            i++;
        }

        if(values.size() == 0) {
            this.terminal.printf( "\n\n --------------- No available Items ------------- \n\n\n");
            BACK v = textIO.newEnumInputReader(BACK.class)
                    .read("Select an option!");
            return -1;
        }

        this.terminal.printf("\nChoose an element\n");
        int rmId = textIO.newIntInputReader()
                .withInlinePossibleValues(values)
                .read("Key");


        return set.get(rmId);
    }
    public void displayHospital(int i, Hospital hos){
        this.terminal.printf( i + "- \n " +
                            "Hospital Details" + hos + "\n");

        List<Object> list = new ArrayList<Object>(safetyNet.getHospitalSpecialties(hos.getId()));
        this.terminal.printf("Hospital Specialties:\n");
        for (int j = 0; j < list.size(); j++){
            this.terminal.printf("   " + getSpecialty(list.get(j)));
        }

        if(list.size() == 0)
            this.terminal.printf("No specialties available in this hospital\n\n");

    }


    public void doctors(){
        separator();
        this.terminal.printf("--------------------------Doctors Management--------------------------\n");

        DOCTOR_MENU val = textIO.newEnumInputReader(DOCTOR_MENU.class)
                .read("Select an option!");

        switch (val){

            case ADD:
                String user = textIO.newStringInputReader()
                        .read("Name");

                int age = textIO.newIntInputReader()
                        .withMinVal(18)
                        .read("Age");


                SPECIALTY specialty = textIO.newEnumInputReader(SPECIALTY.class)
                        .read("Select an option!");

                safetyNet.addDoctor(new Doctor(user,age,getSpecialty(specialty)));

                doctors();
                break;
            case REMOVE:
                HashMap<Number,Object> m = safetyNet.getDoctors();
                Number rmId = getMapSelectedElement(m);
                if(rmId.intValue() == -1)
                    doctors();
                else {
                    safetyNet.removeDoctor((Doctor) m.get(rmId));
                    doctors();
                }

                break;
            case SEARCH:
                searchDoctor();
                break;
            case BACK:
                start();
                break;
            case EXIT:
            default:
                System.exit(0);
                break;
        }
    }

    public void searchDoctor(){
        separator();
        this.terminal.printf("--------------------------Search Doctors--------------------------\n");

        SEARCH_DOCTORS_OPTIONS_MENU val = textIO.newEnumInputReader(SEARCH_DOCTORS_OPTIONS_MENU.class)
                .read("Select an option!");

        List<Doctor> list;
        int i= 0;
        switch (val){
            case SEE_ALL:
                i=0;
                for (Map.Entry<Number,Doctor> entry : new HashMap<Number,Doctor>(safetyNet.getDoctors()).entrySet())
                {
                    displayDoctor(i, entry.getValue());
                    i++;
                }
                backToDoctorSearchMenu();
                break;
            case BY_SPECIALTY:
                SPECIALTY specialty = textIO.newEnumInputReader(SPECIALTY.class)
                        .read("Doctor Specialty");

                list = new ArrayList<Doctor>(safetyNet.getDoctorsBySpecialty(specialty));

                i=0;
                for (int j = 0; j < list.size(); j++) {
                    displayDoctor(i, list.get(j));
                    i++;
                }
                backToDoctorSearchMenu();
                break;
            case BACK:
                doctors();
                break;
            case EXIT:
            default:
                System.exit(0);
                break;
        }
    }

    private void backToDoctorSearchMenu(){
        this.terminal.printf("\n\n");

        BACK v = textIO.newEnumInputReader(BACK.class)
                .read("Select an option!");

        switch (v) {
            case BACK:
                searchDoctor();
                break;
        }
    }

    public void displayDoctor(int i, Doctor doc){
        this.terminal.printf( i + "- \n " +
                "Doctor Details" + doc + "\n");

        List<Hospital> list = new ArrayList<Hospital>(safetyNet.getDoctorHospitals(doc.getId()));
        this.terminal.printf("Hospitals where" + doc.getName() + " works: \n");
        for (int j = 0; j < list.size(); j++){
            this.terminal.printf(j + " - " + list.get(j).getName());
        }
        if(list.size() == 0)
            this.terminal.printf("Currently not working in any hospital\n");

    }


    public void appointments(){
        separator();
        this.terminal.printf("--------------------------Appointments Management--------------------------\n");

        APPOINTMENTS_MENU val = textIO.newEnumInputReader(APPOINTMENTS_MENU .class)
                .read("Select an option!");

        switch (val){
            case ADD:
                Number hosId = getMapSelectedElement(safetyNet.getHospitals());
                Number docId = getMapSelectedElement(safetyNet.getDoctors());
                Number patId = getMapSelectedElement(safetyNet.getPatients());

                if(hosId.intValue() == -1 || docId.intValue() == -1 || patId.intValue() == -1){
                    appointments();
                }

                int year = textIO.newIntInputReader()
                        .withMinVal(2017)
                        .read("Year");

                int month = textIO.newIntInputReader()
                        .withMaxVal(12)
                        .read("Month");

                int day = textIO.newIntInputReader()
                        .withMaxVal(31)
                        .read("Day");

                int hour = textIO.newIntInputReader()
                        .withMaxVal(24)
                        .read("Hour");

                int min = textIO.newIntInputReader()
                        .withMinVal(60)
                        .read("Minutes");


                safetyNet.addAppointment(new Appointment(new ModelUtils.Date(year,month,day,hour,min),hosId,docId,patId));

                break;
            case REMOVE:
                ArrayList<Object> appointments = new ArrayList<Object>(safetyNet.getAppointments());
                this.terminal.printf("--------------- Select an Appointment----------------------");
                Object appointment = getSetSelectedElement(appointments);
                safetyNet.removeAppointment((Appointment) appointment);
                break;
            case SEE_ALL:
                List<Appointment> list = new ArrayList<Appointment>(safetyNet.getAppointments());
                this.terminal.printf("--------------- See Appointments----------------------");

                int i=0;
                for (int j = 0; j < list.size(); j++){
                    displayAppointment(i, list.get(j));
                    i++;
                }
                break;
            case GET_FIRST_AVAILABLE_DATE_FOR_AN_APPOINTMENT:
                SPECIALTY specialty = textIO.newEnumInputReader(SPECIALTY.class)
                        .read("Select a Specialty!");

                ArrayList<Object> hospitals = new ArrayList<Object>(safetyNet.getAppointments());
                this.terminal.printf("--------------- Select an Hospital----------------------");
                //Object hospital = getSetSelectedElement(appointments);

                break;
            case BACK:
                start();
                break;
            case EXIT:
            default:
                System.exit(0);
                break;
        }
    }

    public void displayAppointment(int i, Appointment appointment){
        this.terminal.printf( i + "- \n " +
                             "Appointment Details:" + "\n" +
                              "Hospital: " + safetyNet.getHospitalsById(appointment.getHospitalId()).getName()+
                              "Doctor: " + safetyNet.getDoctorById(appointment.getDoctorId()).getName() +
                              "Patient: " + safetyNet.getPatientById(appointment.getPatientId()).getName() +
                              "Date: " + appointment.getDate());
    }

    public void patients(){
        separator();
        this.terminal.printf("--------------------------Patients Management--------------------------\n");

        PATIENTS_MENU val = textIO.newEnumInputReader(PATIENTS_MENU.class)
                .read("Select an option!");

        switch (val){
            case ADD:
                String user = textIO.newStringInputReader()
                        .read("Name");

                int age = textIO.newIntInputReader()
                        .withMinVal(18)
                        .read("Age");


                String observation = textIO.newStringInputReader()
                        .read("Observation");

                safetyNet.addPatient(new Patient(user,age,observation));

                patients();
                break;
            case REMOVE:
                HashMap<Number,Object> m = safetyNet.getPatients();
                Number rmId = getMapSelectedElement(m);
                if(rmId.intValue() == -1)
                    patients();
                else {
                    safetyNet.removePatient((Patient) m.get(rmId));
                    patients();
                }
                break;
            case SEE_ALL:
                int i=0;
                for (Map.Entry<Number,Patient> entry : new HashMap<Number,Patient>(safetyNet.getPatients()).entrySet())
                {
                    displayPatient(i, entry.getValue());
                    i++;
                }

                this.terminal.printf("\n\n");

                BACK v = textIO.newEnumInputReader(BACK.class)
                        .read("Select an option!");

                switch (v) {
                    case BACK:
                        patients();
                        break;
                }
            case BACK:
                start();
                break;
            case EXIT:
                System.exit(0);
                break;
        }
    }

    public void displayPatient(int i, Patient pat){
        this.terminal.printf( i + "- \n " +
                "Patient Details" + pat + "\n");

    }

    public void separator(){
        this.terminal.printf("------------------------------------------------------------------------------\n" +
                             "------------------------------------------------------------------------------\n" +
                             "------------------------------------------------------------------------------\n");
    }


}
