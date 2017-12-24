package cli;


import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;



public class SafetyNetHospital_CLI {

    public static void main(String[] args) {
        SafetyNetHospital_CLI safetyNet = new SafetyNetHospital_CLI();
    }

    private TextIO textIO;
    private TextTerminal terminal;

    public SafetyNetHospital_CLI() {
        this.textIO = TextIoFactory.getTextIO();
        this.terminal = textIO.getTextTerminal();
        this.terminal.printf("-----------------------------------------------------------------------\n" +
                        "--------------------------Safety Net Hospital--------------------------\n" +
                        "-----------------------------------------------------------------------\n"
                        );
        this.start();
    }

    public void start(){
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
            default:
                System.exit(0);
                break;
        }
    }

    public void hospitals(){
        this.terminal.printf("--------------------------Hospitals Managment--------------------------\n");
    }


    public void doctors(){
        this.terminal.printf("--------------------------Doctors Managment--------------------------\n");
    }


    public void appointments(){
        this.terminal.printf("--------------------------Appointments Managment--------------------------\n");
    }


    public void patients(){
        this.terminal.printf("--------------------------Patients Managment--------------------------\n");
    }

}
