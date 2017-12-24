package cli;


import SafetyNetHospital.Doctor;
import SafetyNetHospital.Hospital;
import SafetyNetHospital.ModelUtils;
import SafetyNetHospital.SafetyNetNetwork;
import SafetyNetHospital.quotes.*;
import cli.menus.*;
import cli.vdm_enum.AGREEMENT;
import cli.vdm_enum.SPECIALTY;
import com.sun.xml.internal.bind.v2.util.QNameMap;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;
import org.overture.codegen.runtime.VDMMap;
import org.overture.codegen.runtime.VDMSet;

import javax.print.Doc;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


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

        this.start();
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
        cls();
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
        cls();
        this.terminal.printf("--------------------------Hospitals Management--------------------------\n");

        HOSPITAL_MENU val = textIO.newEnumInputReader(HOSPITAL_MENU.class)
                .read("Select an option!");

        switch (val){

            case ASSOCIATE_DOCTOR:
                break;
            case DISASSOCIATE_DOCTOR:
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
                break;
            case REMOVE:
                break;
            case SEARCH:
                HashMap<Number,Hospital> map = new HashMap(safetyNet.getHospitals());

                for (Map.Entry<Number,Hospital> entry : map.entrySet())
                {
                    this.terminal.printf("--------------------------Hospitals--------------------------\n");
                    this.terminal.printf("key:" + entry.getKey() + " -- Data: " +  entry.getValue() + "\n");
                }
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

    public void manageDoctorToHospital(){

    }


    public void doctors(){
        cls();
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
        cls();
        this.terminal.printf("--------------------------Search Doctors--------------------------\n");

        SEARCH_OPTIONS_MENU val = textIO.newEnumInputReader(SEARCH_OPTIONS_MENU.class)
                .read("Select an option!");

        switch (val){

            case SEE_ALL:
                HashMap<Number,Doctor> map = new HashMap(safetyNet.getDoctors());

                for (Map.Entry<Number,Doctor> entry : map.entrySet())
                {
                    this.terminal.printf("--------------------------Doctors--------------------------\n");
                    this.terminal.printf("key:" + entry.getKey() + " -- Data: " +  entry.getValue() + "\n");
                }
                break;
            case BY_NAME:
                break;
            case BY_SPECIALTY:
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


    public void appointments(){
        cls();
        this.terminal.printf("--------------------------Appointments Management--------------------------\n");

        APPOINTMENTS_MENU val = textIO.newEnumInputReader(APPOINTMENTS_MENU .class)
                .read("Select an option!");

        switch (val){
            case ADD:
                break;
            case REMOVE:
                break;
            case SEE:
                break;
            case GET_FIRST_AVAILABLE_DATE_FOR_AN_APPOINTMENT:
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


    public void patients(){
        cls();
        this.terminal.printf("--------------------------Patients Management--------------------------\n");

        PATIENTS_MENU val = textIO.newEnumInputReader(PATIENTS_MENU.class)
                .read("Select an option!");

        switch (val){
            case ADD:
                break;
            case REMOVE:
                break;
            case SEARCH:
                start();
            case BACK:
                start();
                break;
            case EXIT:
                System.exit(0);
                break;
        }
    }

    public void cls(){
        this.terminal.printf("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

}
