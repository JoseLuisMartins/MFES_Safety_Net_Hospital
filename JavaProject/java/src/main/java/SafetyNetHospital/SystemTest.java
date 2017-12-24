package SafetyNetHospital;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class SystemTest extends MyTestCase {
  private SafetyNetNetwork safetyNet = SafetyNetNetwork.getInstance();

  public static void main() {

    SystemTest systemTest = new SystemTest();
    IO.println("network ");
    IO.print("test associateADoctorToAnHospital -> ");
    systemTest.testAssociateDoctorToAnHospital();
    IO.println("Success");
    IO.print("test disassociateADoctorToAnHospital -> ");
    systemTest.testDisassociateDoctorToAnHospital();
    IO.println("Success");
    IO.print("test addHospital -> ");
    systemTest.testAddHospital();
    IO.println("Success");
    IO.print("test removeHospital -> ");
    systemTest.testRemoveHospital();
    IO.println("Success");
    IO.print("test getAllHospitalsByLocation -> ");
    systemTest.testGetHospitalsByLocation();
    IO.println("Success");
    IO.print("test getAllHospitals -> ");
    systemTest.testGetAllHospitals();
    IO.println("Success");
    IO.print("test getHospitalsByName -> ");
    systemTest.testGetHospitalsByName();
    IO.println("Success");
    IO.print("test getHospitalsById -> ");
    systemTest.testGetHospitalsById();
    IO.println("Success");
    IO.print("test getHospitalSpecialties -> ");
    systemTest.testGetHospitalSpecialties();
    IO.println("Success");
    IO.print("test addDoctor -> ");
    systemTest.testAddDoctor();
    IO.println("Success");
    IO.print("test getDoctors -> ");
    systemTest.testGetAllDoctors();
    IO.println("Success");
    IO.print("test removeDoctor -> ");
    systemTest.testRemoveDoctor();
    IO.println("Success");
    IO.print("test getDoctorHospitals -> ");
    systemTest.testGetDoctorHospitals();
    IO.println("Success");
    IO.print("test getDoctorBySpecialtie-> ");
    systemTest.testGetDoctorBySpecialtie();
    IO.println("Success");
    IO.print("test getDoctorById -> ");
    systemTest.testGetDoctorById();
    IO.println("Success");
    IO.print("test addPatient -> ");
    systemTest.testAddPatient();
    IO.println("Success");
    IO.print("test removePatient -> ");
    systemTest.testRemovePatient();
    IO.println("Success");
    IO.print("test addAppointment -> ");
    systemTest.testAddAppointment();
    IO.println("Success");
    IO.print("test removeAppointment -> ");
    systemTest.testRemoveAppointment();
    IO.println("Success");
    IO.print("test addAgreement-> ");
    systemTest.testAddAgreement();
    IO.println("Success");
    IO.print("test removeAgreement -> ");
    systemTest.testRemoveAgreement();
    IO.println("Success");
  }

  private void testAddDoctor() {

    Doctor doc1 = new Doctor("jose", 35L, SafetyNetHospital.quotes.ORTOPEDIAQuote.getInstance());
    Doctor doc2 =
        new Doctor("marcelo", 40L, SafetyNetHospital.quotes.CARDIOLOGIAQuote.getInstance());
    safetyNet = SafetyNetNetwork.getInstance();
    safetyNet.addDoctor(doc1);
    safetyNet.addDoctor(doc2);
    assertEqual(doc1.getName(), "jose");
    assertEqual(doc2.getName(), "marcelo");
    assertEqual(doc1.getAge(), 35L);
    assertEqual(doc2.getAge(), 40L);
    assertEqual(
        ((Object) doc1.getSpecialty()), SafetyNetHospital.quotes.ORTOPEDIAQuote.getInstance());
    assertEqual(
        ((Object) doc2.getSpecialty()), SafetyNetHospital.quotes.CARDIOLOGIAQuote.getInstance());
    assertEqual(
        safetyNet.getDoctors(),
        MapUtil.map(new Maplet(doc1.getId(), doc1), new Maplet(doc2.getId(), doc2)));
    safetyNet.clearInstance();
  }

  private void testGetAllDoctors() {

    Doctor doc1 = new Doctor("jose", 35L, SafetyNetHospital.quotes.ORTOPEDIAQuote.getInstance());
    Doctor doc2 =
        new Doctor("marcelo", 40L, SafetyNetHospital.quotes.CARDIOLOGIAQuote.getInstance());
    safetyNet = SafetyNetNetwork.getInstance();
    assertEqual(MapUtil.rng(safetyNet.getDoctors()), SetUtil.set());
    safetyNet.addDoctor(doc1);
    safetyNet.addDoctor(doc2);
    assertEqual(
        safetyNet.getDoctors(),
        MapUtil.map(new Maplet(doc1.getId(), doc1), new Maplet(doc2.getId(), doc2)));
    safetyNet.clearInstance();
  }

  private void testRemoveDoctor() {

    Doctor doc1 = new Doctor("jose", 35L, SafetyNetHospital.quotes.ORTOPEDIAQuote.getInstance());
    Doctor doc2 =
        new Doctor("marcelo", 40L, SafetyNetHospital.quotes.CARDIOLOGIAQuote.getInstance());
    Hospital hos1 =
        new Hospital(
            "Hospital Sao Joao",
            new ModelUtils.Location("Porto", "Alameda Prof. Hernâni Monteiro", "4200-319"),
            SetUtil.set(
                SafetyNetHospital.quotes.ADSEQuote.getInstance(),
                SafetyNetHospital.quotes.MEDICAREQuote.getInstance()));
    Hospital hos2 =
        new Hospital(
            "Hospital da Luz Lisboa",
            new ModelUtils.Location("Lisboa", "Avenida Lusíada, nº 100", "4700-959"),
            SetUtil.set(
                SafetyNetHospital.quotes.ADSEQuote.getInstance(),
                SafetyNetHospital.quotes.MEDISQuote.getInstance(),
                SafetyNetHospital.quotes.MULTICAREQuote.getInstance()));
    safetyNet = SafetyNetNetwork.getInstance();
    safetyNet.addHospital(hos1);
    safetyNet.addHospital(hos2);
    safetyNet.addDoctor(doc1);
    safetyNet.addDoctor(doc2);
    safetyNet.associateDoctorToHospital(hos1.getId(), doc1.getId());
    safetyNet.associateDoctorToHospital(hos2.getId(), doc2.getId());
    safetyNet.removeDoctor(doc2);
    assertEqual(safetyNet.getDoctors(), MapUtil.map(new Maplet(doc1.getId(), doc1)));
    for (Iterator iterator_30 = MapUtil.rng(safetyNet.getHospitals()).iterator();
        iterator_30.hasNext();
        ) {
      Hospital hs = (Hospital) iterator_30.next();
      assertFalse(SetUtil.inSet(doc2.getId(), hs.getDoctorsIds()));
    }
    safetyNet.clearInstance();
  }

  private void testAddHospital() {

    Hospital hos1 =
        new Hospital(
            "Hospital Sao Joao",
            new ModelUtils.Location("Porto", "Alameda Prof. Hernâni Monteiro", "4200-319"),
            SetUtil.set(
                SafetyNetHospital.quotes.ADSEQuote.getInstance(),
                SafetyNetHospital.quotes.MEDICAREQuote.getInstance()));
    Hospital hos2 =
        new Hospital(
            "Hospital da Luz Lisboa",
            new ModelUtils.Location("Lisboa", "Avenida Lusíada, nº 100", "4700-959"),
            SetUtil.set(
                SafetyNetHospital.quotes.ADSEQuote.getInstance(),
                SafetyNetHospital.quotes.MEDISQuote.getInstance(),
                SafetyNetHospital.quotes.MULTICAREQuote.getInstance()));
    safetyNet = SafetyNetNetwork.getInstance();
    safetyNet.addHospital(hos1);
    safetyNet.addHospital(hos2);
    assertEqual(
        safetyNet.getHospitals(),
        MapUtil.map(new Maplet(hos1.getId(), hos1), new Maplet(hos2.getId(), hos2)));
    safetyNet.clearInstance();
  }

  private void testRemoveHospital() {

    Hospital hos1 =
        new Hospital(
            "Hospital Sao Joao",
            new ModelUtils.Location("Porto", "Alameda Prof. Hernâni Monteiro", "4200-319"),
            SetUtil.set(
                SafetyNetHospital.quotes.ADSEQuote.getInstance(),
                SafetyNetHospital.quotes.MEDICAREQuote.getInstance()));
    Hospital hos2 =
        new Hospital(
            "Hospital da Luz Lisboa",
            new ModelUtils.Location("Lisboa", "Avenida Lusíada, nº 100", "4700-959"),
            SetUtil.set(
                SafetyNetHospital.quotes.ADSEQuote.getInstance(),
                SafetyNetHospital.quotes.MEDISQuote.getInstance(),
                SafetyNetHospital.quotes.MULTICAREQuote.getInstance()));
    safetyNet = SafetyNetNetwork.getInstance();
    safetyNet.addHospital(hos1);
    safetyNet.addHospital(hos2);
    safetyNet.removeHospital(hos1);
    assertEqual(safetyNet.getHospitals(), MapUtil.map(new Maplet(hos2.getId(), hos2)));
    safetyNet.clearInstance();
  }

  private void testAssociateDoctorToAnHospital() {

    Hospital hos1 =
        new Hospital(
            "Hospital Sao Joao",
            new ModelUtils.Location("Porto", "Alameda Prof. Hernâni Monteiro", "4200-319"),
            SetUtil.set(
                SafetyNetHospital.quotes.ADSEQuote.getInstance(),
                SafetyNetHospital.quotes.MEDICAREQuote.getInstance()));
    Hospital hos2 =
        new Hospital(
            "Hospital da Luz Lisboa",
            new ModelUtils.Location("Lisboa", "Avenida Lusíada, nº 100", "4700-959"),
            SetUtil.set(
                SafetyNetHospital.quotes.ADSEQuote.getInstance(),
                SafetyNetHospital.quotes.MEDISQuote.getInstance(),
                SafetyNetHospital.quotes.MULTICAREQuote.getInstance()));
    Doctor doc1 = new Doctor("jose", 35L, SafetyNetHospital.quotes.ORTOPEDIAQuote.getInstance());
    Doctor doc2 =
        new Doctor("marcelo", 40L, SafetyNetHospital.quotes.CARDIOLOGIAQuote.getInstance());
    safetyNet = SafetyNetNetwork.getInstance();
    safetyNet.addHospital(hos1);
    safetyNet.addHospital(hos2);
    safetyNet.addDoctor(doc1);
    safetyNet.addDoctor(doc2);
    safetyNet.associateDoctorToHospital(hos1.getId(), doc1.getId());
    safetyNet.associateDoctorToHospital(hos2.getId(), doc1.getId());
    safetyNet.associateDoctorToHospital(hos2.getId(), doc2.getId());
    assertEqual(hos1.getDoctorsIds(), SetUtil.set(doc1.getId()));
    assertEqual(hos2.getDoctorsIds(), SetUtil.set(doc1.getId(), doc2.getId()));
    assertEqual(
        safetyNet.getDoctors(),
        MapUtil.map(new Maplet(doc1.getId(), doc1), new Maplet(doc2.getId(), doc2)));
    safetyNet.clearInstance();
  }

  private void testDisassociateDoctorToAnHospital() {

    Hospital hos1 =
        new Hospital(
            "Hospital Sao Joao",
            new ModelUtils.Location("Porto", "Alameda Prof. Hernâni Monteiro", "4200-319"),
            SetUtil.set(
                SafetyNetHospital.quotes.ADSEQuote.getInstance(),
                SafetyNetHospital.quotes.MEDICAREQuote.getInstance()));
    Hospital hos2 =
        new Hospital(
            "Hospital da Luz Lisboa",
            new ModelUtils.Location("Lisboa", "Avenida Lusíada, nº 100", "4700-959"),
            SetUtil.set(
                SafetyNetHospital.quotes.ADSEQuote.getInstance(),
                SafetyNetHospital.quotes.MEDISQuote.getInstance(),
                SafetyNetHospital.quotes.MULTICAREQuote.getInstance()));
    Doctor doc1 = new Doctor("jose", 35L, SafetyNetHospital.quotes.ORTOPEDIAQuote.getInstance());
    Doctor doc2 =
        new Doctor("marcelo", 40L, SafetyNetHospital.quotes.CARDIOLOGIAQuote.getInstance());
    safetyNet = SafetyNetNetwork.getInstance();
    safetyNet.addHospital(hos1);
    safetyNet.addHospital(hos2);
    safetyNet.addDoctor(doc1);
    safetyNet.addDoctor(doc2);
    safetyNet.associateDoctorToHospital(hos1.getId(), doc1.getId());
    safetyNet.associateDoctorToHospital(hos2.getId(), doc1.getId());
    safetyNet.associateDoctorToHospital(hos2.getId(), doc2.getId());
    safetyNet.disassociateDoctorFromHospital(hos2.getId(), doc1.getId());
    assertEqual(hos1.getDoctorsIds(), SetUtil.set(doc1.getId()));
    assertEqual(hos2.getDoctorsIds(), SetUtil.set(doc2.getId()));
    assertEqual(
        safetyNet.getDoctors(),
        MapUtil.map(new Maplet(doc1.getId(), doc1), new Maplet(doc2.getId(), doc2)));
    safetyNet.clearInstance();
  }

  private void testGetHospitalsByLocation() {

    Hospital hos1 =
        new Hospital(
            "Hospital Sao Joao",
            new ModelUtils.Location("Porto", "Alameda Prof. Hernâni Monteiro", "4200-319"),
            SetUtil.set(
                SafetyNetHospital.quotes.ADSEQuote.getInstance(),
                SafetyNetHospital.quotes.MEDICAREQuote.getInstance()));
    Hospital hos2 =
        new Hospital(
            "Hospital da Luz Lisboa",
            new ModelUtils.Location("Lisboa", "Avenida Lusíada, nº 100", "4700-959"),
            SetUtil.set(
                SafetyNetHospital.quotes.ADSEQuote.getInstance(),
                SafetyNetHospital.quotes.MEDISQuote.getInstance(),
                SafetyNetHospital.quotes.MULTICAREQuote.getInstance()));
    Hospital hos3 =
        new Hospital(
            "Hospital de Santo Antonio",
            new ModelUtils.Location("Lisboa", "Avenida de Santo antonio, n� 300", "4750-559"),
            SetUtil.set(SafetyNetHospital.quotes.ADSEQuote.getInstance()));
    safetyNet = SafetyNetNetwork.getInstance();
    safetyNet.addHospital(hos1);
    safetyNet.addHospital(hos2);
    safetyNet.addHospital(hos3);
    assertEqual(safetyNet.getHospitalsByCity("Porto"), SetUtil.set(hos1));
    assertEqual(safetyNet.getHospitalsByCity("Lisboa"), SetUtil.set(hos2, hos3));
    safetyNet.clearInstance();
  }

  private void testGetHospitalsById() {

    Hospital hos1 =
        new Hospital(
            "Hospital Sao Joao",
            new ModelUtils.Location("Porto", "Alameda Prof. Hernâni Monteiro", "4200-319"),
            SetUtil.set(
                SafetyNetHospital.quotes.ADSEQuote.getInstance(),
                SafetyNetHospital.quotes.MEDICAREQuote.getInstance()));
    Hospital hos2 =
        new Hospital(
            "Hospital da Luz Lisboa",
            new ModelUtils.Location("Lisboa", "Avenida Lusíada, nº 100", "4700-959"),
            SetUtil.set(
                SafetyNetHospital.quotes.ADSEQuote.getInstance(),
                SafetyNetHospital.quotes.MEDISQuote.getInstance(),
                SafetyNetHospital.quotes.MULTICAREQuote.getInstance()));
    Hospital hos3 =
        new Hospital(
            "Hospital de Santo Antonio",
            new ModelUtils.Location("Lisboa", "Avenida de Santo antonio, n� 300", "4750-559"),
            SetUtil.set(SafetyNetHospital.quotes.ADSEQuote.getInstance()));
    safetyNet = SafetyNetNetwork.getInstance();
    safetyNet.addHospital(hos1);
    safetyNet.addHospital(hos2);
    safetyNet.addHospital(hos3);
    assertEqual(safetyNet.getHospitalsById(hos1.getId()), hos1);
    assertEqual(safetyNet.getHospitalsById(hos2.getId()), hos2);
    assertEqual(safetyNet.getHospitalsById(hos3.getId()), hos3);
    safetyNet.clearInstance();
  }

  private void testGetAllHospitals() {

    Hospital hos1 =
        new Hospital(
            "Hospital Sao Joao",
            new ModelUtils.Location("Porto", "Alameda Prof. Hernâni Monteiro", "4200-319"),
            SetUtil.set(
                SafetyNetHospital.quotes.ADSEQuote.getInstance(),
                SafetyNetHospital.quotes.MEDICAREQuote.getInstance()));
    Hospital hos2 =
        new Hospital(
            "Hospital da Luz Lisboa",
            new ModelUtils.Location("Lisboa", "Avenida Lusíada, nº 100", "4700-959"),
            SetUtil.set(
                SafetyNetHospital.quotes.ADSEQuote.getInstance(),
                SafetyNetHospital.quotes.MEDISQuote.getInstance(),
                SafetyNetHospital.quotes.MULTICAREQuote.getInstance()));
    Hospital hos3 =
        new Hospital(
            "Hospital de Santo Antonio",
            new ModelUtils.Location("Lisboa", "Avenida de Santo antonio, n� 300", "4750-559"),
            SetUtil.set(SafetyNetHospital.quotes.ADSEQuote.getInstance()));
    safetyNet = SafetyNetNetwork.getInstance();
    safetyNet.addHospital(hos1);
    safetyNet.addHospital(hos2);
    safetyNet.addHospital(hos3);
    assertEqual(
        safetyNet.getHospitals(),
        MapUtil.map(
            new Maplet(hos1.getId(), hos1),
            new Maplet(hos2.getId(), hos2),
            new Maplet(hos3.getId(), hos3)));
    safetyNet.clearInstance();
  }

  private void testGetHospitalsByName() {

    Hospital hos1 =
        new Hospital(
            "Hospital Sao Joao",
            new ModelUtils.Location("Porto", "Alameda Prof. Hernâni Monteiro", "4200-319"),
            SetUtil.set(
                SafetyNetHospital.quotes.ADSEQuote.getInstance(),
                SafetyNetHospital.quotes.MEDICAREQuote.getInstance()));
    Hospital hos2 =
        new Hospital(
            "Hospital da Luz Lisboa",
            new ModelUtils.Location("Lisboa", "Avenida Lusíada, nº 100", "4700-959"),
            SetUtil.set(
                SafetyNetHospital.quotes.ADSEQuote.getInstance(),
                SafetyNetHospital.quotes.MEDISQuote.getInstance(),
                SafetyNetHospital.quotes.MULTICAREQuote.getInstance()));
    Hospital hos3 =
        new Hospital(
            "Hospital de Santo Antonio",
            new ModelUtils.Location("Lisboa", "Avenida de Santo antonio, n� 300", "4750-559"),
            SetUtil.set(SafetyNetHospital.quotes.ADSEQuote.getInstance()));
    safetyNet = SafetyNetNetwork.getInstance();
    safetyNet.addHospital(hos1);
    safetyNet.addHospital(hos2);
    safetyNet.addHospital(hos3);
    assertEqual(safetyNet.getHospitalsByName("Hospital Sao Joao"), SetUtil.set(hos1));
    assertEqual(safetyNet.getHospitalsByName("Hospital da Luz Lisboa"), SetUtil.set(hos2));
    assertEqual(safetyNet.getHospitalsByName("Hospital de Santo Antonio"), SetUtil.set(hos3));
    safetyNet.clearInstance();
  }

  private void testGetDoctorHospitals() {

    Hospital hos1 =
        new Hospital(
            "Hospital Sao Joao",
            new ModelUtils.Location("Porto", "Alameda Prof. Hernâni Monteiro", "4200-319"),
            SetUtil.set(
                SafetyNetHospital.quotes.ADSEQuote.getInstance(),
                SafetyNetHospital.quotes.MEDICAREQuote.getInstance()));
    Hospital hos2 =
        new Hospital(
            "Hospital da Luz Lisboa",
            new ModelUtils.Location("Lisboa", "Avenida Lusíada, nº 100", "4700-959"),
            SetUtil.set(
                SafetyNetHospital.quotes.ADSEQuote.getInstance(),
                SafetyNetHospital.quotes.MEDISQuote.getInstance(),
                SafetyNetHospital.quotes.MULTICAREQuote.getInstance()));
    Doctor doc1 = new Doctor("jose", 35L, SafetyNetHospital.quotes.ORTOPEDIAQuote.getInstance());
    Doctor doc2 =
        new Doctor("marcelo", 40L, SafetyNetHospital.quotes.CARDIOLOGIAQuote.getInstance());
    Doctor doc3 =
        new Doctor("joaquim", 50L, SafetyNetHospital.quotes.CARDIOLOGIAQuote.getInstance());
    safetyNet = SafetyNetNetwork.getInstance();
    safetyNet.addHospital(hos1);
    safetyNet.addHospital(hos2);
    safetyNet.addDoctor(doc1);
    safetyNet.addDoctor(doc2);
    safetyNet.addDoctor(doc3);
    safetyNet.associateDoctorToHospital(hos1.getId(), doc1.getId());
    safetyNet.associateDoctorToHospital(hos2.getId(), doc1.getId());
    safetyNet.associateDoctorToHospital(hos2.getId(), doc2.getId());
    assertEqual(safetyNet.getDoctorHospitals(doc1.getId()), SetUtil.set(hos1, hos2));
    assertEqual(safetyNet.getDoctorHospitals(doc2.getId()), SetUtil.set(hos2));
    assertEqual(safetyNet.getDoctorHospitals(doc3.getId()), SetUtil.set());
    safetyNet.clearInstance();
  }

  private void testGetDoctorBySpecialtie() {

    Doctor doc1 = new Doctor("jose", 35L, SafetyNetHospital.quotes.ORTOPEDIAQuote.getInstance());
    Doctor doc2 =
        new Doctor("marcelo", 40L, SafetyNetHospital.quotes.CARDIOLOGIAQuote.getInstance());
    Doctor doc3 =
        new Doctor("joaquim", 50L, SafetyNetHospital.quotes.CARDIOLOGIAQuote.getInstance());
    safetyNet = SafetyNetNetwork.getInstance();
    safetyNet.addDoctor(doc1);
    safetyNet.addDoctor(doc2);
    safetyNet.addDoctor(doc3);
    assertEqual(
        safetyNet.getDoctorsBySpecialty(SafetyNetHospital.quotes.ORTOPEDIAQuote.getInstance()),
        SetUtil.set(doc1));
    assertEqual(
        safetyNet.getDoctorsBySpecialty(SafetyNetHospital.quotes.OFTALMOLOGIAQuote.getInstance()),
        SetUtil.set());
    assertEqual(
        safetyNet.getDoctorsBySpecialty(SafetyNetHospital.quotes.CARDIOLOGIAQuote.getInstance()),
        SetUtil.set(doc2, doc3));
    safetyNet.clearInstance();
  }

  private void testGetHospitalSpecialties() {

    Hospital hos1 =
        new Hospital(
            "Hospital Sao Joao",
            new ModelUtils.Location("Porto", "Alameda Prof. Hernâni Monteiro", "4200-319"),
            SetUtil.set(
                SafetyNetHospital.quotes.ADSEQuote.getInstance(),
                SafetyNetHospital.quotes.MEDICAREQuote.getInstance()));
    Hospital hos2 =
        new Hospital(
            "Hospital da Luz Lisboa",
            new ModelUtils.Location("Lisboa", "Avenida Lusíada, nº 100", "4700-959"),
            SetUtil.set(
                SafetyNetHospital.quotes.ADSEQuote.getInstance(),
                SafetyNetHospital.quotes.MEDISQuote.getInstance(),
                SafetyNetHospital.quotes.MULTICAREQuote.getInstance()));
    Doctor doc1 = new Doctor("jose", 35L, SafetyNetHospital.quotes.ORTOPEDIAQuote.getInstance());
    Doctor doc2 =
        new Doctor("marcelo", 40L, SafetyNetHospital.quotes.CARDIOLOGIAQuote.getInstance());
    Doctor doc3 =
        new Doctor("joaquim", 50L, SafetyNetHospital.quotes.CARDIOLOGIAQuote.getInstance());
    safetyNet = SafetyNetNetwork.getInstance();
    safetyNet.addHospital(hos1);
    safetyNet.addHospital(hos2);
    safetyNet.addDoctor(doc1);
    safetyNet.addDoctor(doc2);
    safetyNet.addDoctor(doc3);
    safetyNet.associateDoctorToHospital(hos1.getId(), doc1.getId());
    safetyNet.associateDoctorToHospital(hos2.getId(), doc1.getId());
    safetyNet.associateDoctorToHospital(hos2.getId(), doc2.getId());
    assertEqual(
        safetyNet.getHospitalSpecialties(hos1.getId()),
        SetUtil.set(SafetyNetHospital.quotes.ORTOPEDIAQuote.getInstance()));
    assertEqual(
        safetyNet.getHospitalSpecialties(hos2.getId()),
        SetUtil.set(
            SafetyNetHospital.quotes.ORTOPEDIAQuote.getInstance(),
            SafetyNetHospital.quotes.CARDIOLOGIAQuote.getInstance()));
    safetyNet.clearInstance();
  }

  private void testGetDoctorById() {

    Doctor doc1 = new Doctor("jose", 35L, SafetyNetHospital.quotes.ORTOPEDIAQuote.getInstance());
    Doctor doc2 =
        new Doctor("marcelo", 40L, SafetyNetHospital.quotes.CARDIOLOGIAQuote.getInstance());
    Doctor doc3 =
        new Doctor("joaquim", 50L, SafetyNetHospital.quotes.CARDIOLOGIAQuote.getInstance());
    safetyNet = SafetyNetNetwork.getInstance();
    safetyNet.addDoctor(doc1);
    safetyNet.addDoctor(doc2);
    safetyNet.addDoctor(doc3);
    assertEqual(safetyNet.getDoctorById(doc1.getId()), doc1);
    assertEqual(safetyNet.getDoctorById(doc2.getId()), doc2);
    assertEqual(safetyNet.getDoctorById(doc3.getId()), doc3);
    safetyNet.clearInstance();
  }

  private void testAddPatient() {

    Patient pat1 = new Patient("Susana", 26L, "Gripe");
    Patient pat2 = new Patient("Maria", 38L, "Doença pulmonar");
    safetyNet = SafetyNetNetwork.getInstance();
    safetyNet.addPatient(pat1);
    safetyNet.addPatient(pat2);
    assertEqual(pat1.getName(), "Susana");
    assertEqual(pat2.getName(), "Maria");
    assertEqual(pat1.getAge(), 26L);
    assertEqual(pat2.getAge(), 38L);
    assertEqual(
        safetyNet.getPatients(),
        MapUtil.map(new Maplet(pat1.getId(), pat1), new Maplet(pat2.getId(), pat2)));
    safetyNet.clearInstance();
  }

  private void testRemovePatient() {

    Patient pat1 = new Patient("Susana", 26L, "Gripe");
    Patient pat2 = new Patient("Maria", 38L, "Doença pulmonar");
    safetyNet = SafetyNetNetwork.getInstance();
    safetyNet.addPatient(pat1);
    safetyNet.addPatient(pat2);
    safetyNet.removePatient(pat2);
    assertEqual(safetyNet.getPatients(), MapUtil.map(new Maplet(pat1.getId(), pat1)));
    safetyNet.clearInstance();
  }

  private void testAddAppointment() {

    Hospital hos1 =
        new Hospital(
            "Hospital Sao Joao",
            new ModelUtils.Location("Porto", "Alameda Prof. Hernâni Monteiro", "4200-319"),
            SetUtil.set(
                SafetyNetHospital.quotes.ADSEQuote.getInstance(),
                SafetyNetHospital.quotes.MEDICAREQuote.getInstance()));
    Hospital hos2 =
        new Hospital(
            "Hospital da Luz Lisboa",
            new ModelUtils.Location("Lisboa", "Avenida Lusíada, nº 100", "4700-959"),
            SetUtil.set(
                SafetyNetHospital.quotes.ADSEQuote.getInstance(),
                SafetyNetHospital.quotes.MEDISQuote.getInstance(),
                SafetyNetHospital.quotes.MULTICAREQuote.getInstance()));
    Doctor doc1 = new Doctor("jose", 35L, SafetyNetHospital.quotes.ORTOPEDIAQuote.getInstance());
    Doctor doc2 =
        new Doctor("marcelo", 40L, SafetyNetHospital.quotes.CARDIOLOGIAQuote.getInstance());
    Doctor doc3 =
        new Doctor("joaquim", 50L, SafetyNetHospital.quotes.CARDIOLOGIAQuote.getInstance());
    Patient pat1 = new Patient("Susana", 26L, "Gripe");
    Patient pat2 = new Patient("Maria", 38L, "Doença pulmonar");
    VDMSet dt1 = SetUtil.set();
    VDMSet dt2 = SetUtil.set();
    VDMSet pt1 = SetUtil.set();
    VDMSet pt2 = SetUtil.set();
    safetyNet = SafetyNetNetwork.getInstance();
    safetyNet.addHospital(hos1);
    safetyNet.addHospital(hos2);
    safetyNet.addDoctor(doc1);
    safetyNet.addDoctor(doc2);
    safetyNet.addDoctor(doc3);
    safetyNet.addPatient(pat1);
    safetyNet.addPatient(pat2);
    safetyNet.associateDoctorToHospital(hos1.getId(), doc1.getId());
    safetyNet.associateDoctorToHospital(hos2.getId(), doc1.getId());
    safetyNet.associateDoctorToHospital(hos2.getId(), doc2.getId());
    assertEqual(safetyNet.getDoctorAppointments(doc1.getId()).size(), 0L);
    assertEqual(safetyNet.getDoctorAppointments(doc2.getId()).size(), 0L);
    safetyNet.addAppointment(
        new Appointment(
            new ModelUtils.Date(2018L, 12L, 21L, 8L, 30L),
            hos1.getId(),
            doc1.getId(),
            pat1.getId()));
    safetyNet.addAppointment(
        new Appointment(
            new ModelUtils.Date(2018L, 12L, 21L, 8L, 30L),
            hos2.getId(),
            doc2.getId(),
            pat2.getId()));
    assertEqual(safetyNet.getDoctorAppointments(doc1.getId()).size(), 1L);
    for (Iterator iterator_31 = safetyNet.getDoctorAppointments(doc1.getId()).iterator();
        iterator_31.hasNext();
        ) {
      Appointment a = (Appointment) iterator_31.next();
      dt1 = SetUtil.union(Utils.copy(dt1), SetUtil.set(a.getDate()));
      assertEqual(a.getDoctorId(), doc1.getId());
      Boolean andResult_25 = false;

      if (a.getDate().year.longValue() > 2017L) {
        Boolean andResult_26 = false;

        if (a.getDate().month.longValue() <= 12L) {
          Boolean andResult_27 = false;

          if (a.getDate().day.longValue() < 31L) {
            Boolean andResult_28 = false;

            if (a.getDate().hour.longValue() < 24L) {
              if (a.getDate().min.longValue() < 60L) {
                andResult_28 = true;
              }
            }

            if (andResult_28) {
              andResult_27 = true;
            }
          }

          if (andResult_27) {
            andResult_26 = true;
          }
        }

        if (andResult_26) {
          andResult_25 = true;
        }
      }

      assertTrue(andResult_25);
    }
    assertEqual(dt1.size(), safetyNet.getDoctorAppointments(doc1.getId()).size());
    assertEqual(safetyNet.getDoctorAppointments(doc2.getId()).size(), 1L);
    for (Iterator iterator_32 = safetyNet.getDoctorAppointments(doc2.getId()).iterator();
        iterator_32.hasNext();
        ) {
      Appointment a = (Appointment) iterator_32.next();
      dt2 = SetUtil.union(Utils.copy(dt2), SetUtil.set(a.getDate()));
      assertEqual(a.getDoctorId(), doc2.getId());
      Boolean andResult_29 = false;

      if (a.getDate().year.longValue() > 2017L) {
        Boolean andResult_30 = false;

        if (a.getDate().month.longValue() <= 12L) {
          Boolean andResult_31 = false;

          if (a.getDate().day.longValue() < 31L) {
            Boolean andResult_32 = false;

            if (a.getDate().hour.longValue() < 24L) {
              if (a.getDate().min.longValue() < 60L) {
                andResult_32 = true;
              }
            }

            if (andResult_32) {
              andResult_31 = true;
            }
          }

          if (andResult_31) {
            andResult_30 = true;
          }
        }

        if (andResult_30) {
          andResult_29 = true;
        }
      }

      assertTrue(andResult_29);
    }
    assertEqual(dt2.size(), safetyNet.getDoctorAppointments(doc2.getId()).size());
    assertEqual(safetyNet.getDoctorAppointments(doc3.getId()).size(), 0L);
    assertEqual(safetyNet.getPatientAppointments(pat1.getId()).size(), 1L);
    for (Iterator iterator_33 = safetyNet.getPatientAppointments(pat1.getId()).iterator();
        iterator_33.hasNext();
        ) {
      Appointment a = (Appointment) iterator_33.next();
      pt1 = SetUtil.union(Utils.copy(pt1), SetUtil.set(a.getDate()));
      assertEqual(a.getPatientId(), pat1.getId());
      Boolean andResult_33 = false;

      if (a.getDate().year.longValue() > 2017L) {
        Boolean andResult_34 = false;

        if (a.getDate().month.longValue() <= 12L) {
          Boolean andResult_35 = false;

          if (a.getDate().day.longValue() < 31L) {
            Boolean andResult_36 = false;

            if (a.getDate().hour.longValue() < 24L) {
              if (a.getDate().min.longValue() < 60L) {
                andResult_36 = true;
              }
            }

            if (andResult_36) {
              andResult_35 = true;
            }
          }

          if (andResult_35) {
            andResult_34 = true;
          }
        }

        if (andResult_34) {
          andResult_33 = true;
        }
      }

      assertTrue(andResult_33);
    }
    assertEqual(pt1.size(), safetyNet.getPatientAppointments(pat1.getId()).size());
    assertEqual(safetyNet.getPatientAppointments(pat2.getId()).size(), 1L);
    for (Iterator iterator_34 = safetyNet.getPatientAppointments(pat2.getId()).iterator();
        iterator_34.hasNext();
        ) {
      Appointment a = (Appointment) iterator_34.next();
      pt2 = SetUtil.union(Utils.copy(pt2), SetUtil.set(a.getDate()));
      assertEqual(a.getPatientId(), pat2.getId());
      Boolean andResult_37 = false;

      if (a.getDate().year.longValue() > 2017L) {
        Boolean andResult_38 = false;

        if (a.getDate().month.longValue() <= 12L) {
          Boolean andResult_39 = false;

          if (a.getDate().day.longValue() < 31L) {
            Boolean andResult_40 = false;

            if (a.getDate().hour.longValue() < 24L) {
              if (a.getDate().min.longValue() < 60L) {
                andResult_40 = true;
              }
            }

            if (andResult_40) {
              andResult_39 = true;
            }
          }

          if (andResult_39) {
            andResult_38 = true;
          }
        }

        if (andResult_38) {
          andResult_37 = true;
        }
      }

      assertTrue(andResult_37);
    }
    assertEqual(pt2.size(), safetyNet.getPatientAppointments(pat2.getId()).size());
    assertEqual(safetyNet.getAppointments().size(), 2L);
    assertEqual(safetyNet.getHospitalNumberOfAppointments(hos1.getId()), 1L);
    for (Iterator iterator_35 = safetyNet.getHospitalAppointments(hos1.getId()).iterator();
        iterator_35.hasNext();
        ) {
      Appointment a = (Appointment) iterator_35.next();
      pt1 = SetUtil.union(Utils.copy(pt1), SetUtil.set(a.getDate()));
      assertEqual(a.getHospitalId(), hos1.getId());
      assertTrue(SetUtil.inSet(a.getDoctorId(), hos1.getDoctorsIds()));
      Boolean andResult_41 = false;

      if (a.getDate().year.longValue() > 2017L) {
        Boolean andResult_42 = false;

        if (a.getDate().month.longValue() <= 12L) {
          Boolean andResult_43 = false;

          if (a.getDate().day.longValue() < 31L) {
            Boolean andResult_44 = false;

            if (a.getDate().hour.longValue() < 24L) {
              if (a.getDate().min.longValue() < 60L) {
                andResult_44 = true;
              }
            }

            if (andResult_44) {
              andResult_43 = true;
            }
          }

          if (andResult_43) {
            andResult_42 = true;
          }
        }

        if (andResult_42) {
          andResult_41 = true;
        }
      }

      assertTrue(andResult_41);
    }
    assertEqual(safetyNet.getHospitalNumberOfAppointments(hos2.getId()), 1L);
    for (Iterator iterator_36 = safetyNet.getHospitalAppointments(hos2.getId()).iterator();
        iterator_36.hasNext();
        ) {
      Appointment a = (Appointment) iterator_36.next();
      pt1 = SetUtil.union(Utils.copy(pt1), SetUtil.set(a.getDate()));
      assertEqual(a.getHospitalId(), hos2.getId());
      assertTrue(SetUtil.inSet(a.getDoctorId(), hos2.getDoctorsIds()));
      Boolean andResult_45 = false;

      if (a.getDate().year.longValue() > 2017L) {
        Boolean andResult_46 = false;

        if (a.getDate().month.longValue() <= 12L) {
          Boolean andResult_47 = false;

          if (a.getDate().day.longValue() < 31L) {
            Boolean andResult_48 = false;

            if (a.getDate().hour.longValue() < 24L) {
              if (a.getDate().min.longValue() < 60L) {
                andResult_48 = true;
              }
            }

            if (andResult_48) {
              andResult_47 = true;
            }
          }

          if (andResult_47) {
            andResult_46 = true;
          }
        }

        if (andResult_46) {
          andResult_45 = true;
        }
      }

      assertTrue(andResult_45);
    }
    safetyNet.clearInstance();
  }

  private void testRemoveAppointment() {

    Hospital hos1 =
        new Hospital(
            "Hospital Sao Joao",
            new ModelUtils.Location("Porto", "Alameda Prof. Hernâni Monteiro", "4200-319"),
            SetUtil.set(
                SafetyNetHospital.quotes.ADSEQuote.getInstance(),
                SafetyNetHospital.quotes.MEDICAREQuote.getInstance()));
    Hospital hos2 =
        new Hospital(
            "Hospital da Luz Lisboa",
            new ModelUtils.Location("Lisboa", "Avenida Lusíada, nº 100", "4700-959"),
            SetUtil.set(
                SafetyNetHospital.quotes.ADSEQuote.getInstance(),
                SafetyNetHospital.quotes.MEDISQuote.getInstance(),
                SafetyNetHospital.quotes.MULTICAREQuote.getInstance()));
    Doctor doc1 = new Doctor("jose", 35L, SafetyNetHospital.quotes.ORTOPEDIAQuote.getInstance());
    Doctor doc2 =
        new Doctor("marcelo", 40L, SafetyNetHospital.quotes.CARDIOLOGIAQuote.getInstance());
    Doctor doc3 =
        new Doctor("joaquim", 50L, SafetyNetHospital.quotes.CARDIOLOGIAQuote.getInstance());
    Patient pat1 = new Patient("Susana", 26L, "Gripe");
    Patient pat2 = new Patient("Maria", 38L, "Doença pulmonar");
    Appointment app1 = null;
    Appointment app2 = null;
    Appointment app3 = null;
    Appointment app4 = null;
    safetyNet = SafetyNetNetwork.getInstance();
    safetyNet.addHospital(hos1);
    safetyNet.addHospital(hos2);
    safetyNet.addDoctor(doc1);
    safetyNet.addDoctor(doc2);
    safetyNet.addDoctor(doc3);
    safetyNet.addPatient(pat1);
    safetyNet.addPatient(pat2);
    safetyNet.associateDoctorToHospital(hos1.getId(), doc1.getId());
    safetyNet.associateDoctorToHospital(hos2.getId(), doc1.getId());
    safetyNet.associateDoctorToHospital(hos2.getId(), doc2.getId());
    assertEqual(safetyNet.getDoctorAppointments(doc1.getId()).size(), 0L);
    assertEqual(safetyNet.getDoctorAppointments(doc2.getId()).size(), 0L);
    app1 =
        new Appointment(
            new ModelUtils.Date(2018L, 12L, 21L, 8L, 30L),
            hos1.getId(),
            doc1.getId(),
            pat1.getId());
    app2 =
        new Appointment(
            new ModelUtils.Date(2018L, 12L, 21L, 8L, 30L),
            hos2.getId(),
            doc2.getId(),
            pat2.getId());
    safetyNet.addAppointment(app1);
    safetyNet.addAppointment(app2);
    assertTrue(SetUtil.inSet(app2, safetyNet.getAppointments()));
    safetyNet.removeAppointment(app2);
    assertTrue(!(SetUtil.inSet(app2, safetyNet.getAppointments())));
    assertEqual(safetyNet.getDoctorAppointments(doc1.getId()), SetUtil.set(app1));
    assertEqual(safetyNet.getDoctorAppointments(doc2.getId()), SetUtil.set());
    assertEqual(safetyNet.getDoctorAppointments(doc3.getId()), SetUtil.set());
    assertEqual(safetyNet.getPatientAppointments(pat1.getId()), SetUtil.set(app1));
    assertEqual(safetyNet.getPatientAppointments(pat2.getId()), SetUtil.set());
    assertEqual(safetyNet.getAppointments().size(), 1L);
    assertEqual(safetyNet.getAppointments(), SetUtil.set(app1));
    assertEqual(safetyNet.getHospitalNumberOfAppointments(hos1.getId()), 1L);
    assertEqual(safetyNet.getHospitalNumberOfAppointments(hos2.getId()), 0L);
    app3 =
        new Appointment(
            new ModelUtils.Date(2018L, 1L, 21L, 8L, 30L), hos2.getId(), doc2.getId(), pat1.getId());
    safetyNet.addAppointment(app3);
    safetyNet.removeDoctor(doc2);
    assertEqual(safetyNet.getAppointments(), SetUtil.set(app1));
    safetyNet.addDoctor(doc2);
    safetyNet.associateDoctorToHospital(hos2.getId(), doc2.getId());
    app2 =
        new Appointment(
            new ModelUtils.Date(2018L, 12L, 21L, 8L, 30L),
            hos2.getId(),
            doc2.getId(),
            pat2.getId());
    app4 =
        new Appointment(
            new ModelUtils.Date(2018L, 1L, 15L, 8L, 30L), hos2.getId(), doc1.getId(), pat1.getId());
    safetyNet.addAppointment(app2);
    safetyNet.addAppointment(app4);
    safetyNet.removePatient(pat1);
    assertEqual(safetyNet.getAppointments(), SetUtil.set(app2));
    safetyNet.removeAppointment(app2);
    safetyNet.addPatient(pat1);
    safetyNet.addAppointment(app3);
    safetyNet.addAppointment(app4);
    assertEqual(safetyNet.getAppointments(), SetUtil.set(app3, app4));
    safetyNet.removeHospital(hos2);
    assertEqual(safetyNet.getAppointments(), SetUtil.set());
    safetyNet.clearInstance();
  }

  private void testAddAgreement() {

    Hospital hos1 =
        new Hospital(
            "Hospital Sao Joao",
            new ModelUtils.Location("Porto", "Alameda Prof. Hernâni Monteiro", "4200-319"),
            SetUtil.set(
                SafetyNetHospital.quotes.ADSEQuote.getInstance(),
                SafetyNetHospital.quotes.MEDICAREQuote.getInstance()));
    Hospital hos2 =
        new Hospital(
            "Hospital da Luz Lisboa",
            new ModelUtils.Location("Lisboa", "Avenida Lusíada, nº 100", "4700-959"),
            SetUtil.set(
                SafetyNetHospital.quotes.ADSEQuote.getInstance(),
                SafetyNetHospital.quotes.MEDISQuote.getInstance(),
                SafetyNetHospital.quotes.MULTICAREQuote.getInstance()));
    safetyNet = SafetyNetNetwork.getInstance();
    safetyNet.addHospital(hos1);
    safetyNet.addHospital(hos2);
    assertEqual(
        hos1.getAgreements(),
        SetUtil.set(
            SafetyNetHospital.quotes.ADSEQuote.getInstance(),
            SafetyNetHospital.quotes.MEDICAREQuote.getInstance()));
    hos1.addAgreement(SafetyNetHospital.quotes.MULTICAREQuote.getInstance());
    assertEqual(
        hos1.getAgreements(),
        SetUtil.set(
            SafetyNetHospital.quotes.ADSEQuote.getInstance(),
            SafetyNetHospital.quotes.MEDICAREQuote.getInstance(),
            SafetyNetHospital.quotes.MULTICAREQuote.getInstance()));
    safetyNet.clearInstance();
  }

  private void testRemoveAgreement() {

    Hospital hos1 =
        new Hospital(
            "Hospital Sao Joao",
            new ModelUtils.Location("Porto", "Alameda Prof. Hernâni Monteiro", "4200-319"),
            SetUtil.set(
                SafetyNetHospital.quotes.ADSEQuote.getInstance(),
                SafetyNetHospital.quotes.MEDICAREQuote.getInstance()));
    Hospital hos2 =
        new Hospital(
            "Hospital da Luz Lisboa",
            new ModelUtils.Location("Lisboa", "Avenida Lusíada, nº 100", "4700-959"),
            SetUtil.set(
                SafetyNetHospital.quotes.ADSEQuote.getInstance(),
                SafetyNetHospital.quotes.MEDISQuote.getInstance(),
                SafetyNetHospital.quotes.MULTICAREQuote.getInstance()));
    safetyNet = SafetyNetNetwork.getInstance();
    safetyNet.addHospital(hos1);
    safetyNet.addHospital(hos2);
    hos1.removeAgreement(SafetyNetHospital.quotes.ADSEQuote.getInstance());
    assertEqual(
        hos1.getAgreements(), SetUtil.set(SafetyNetHospital.quotes.MEDICAREQuote.getInstance()));
    safetyNet.clearInstance();
  }

  public SystemTest() {}

  public String toString() {

    return "SystemTest{" + "safetyNet := " + Utils.toString(safetyNet) + "}";
  }
}
