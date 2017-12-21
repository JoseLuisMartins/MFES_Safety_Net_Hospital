package SafetyNetHospital;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class SystemTest extends MyTestCase {
  private SafetyNetNetwork safetyNet = SafetyNetNetwork.getInstance();

  public static void main() {

    SystemTest systemTest = new SystemTest();
    IO.println("network ");
    IO.print("test addDoctor -> ");
    systemTest.testAddDoctor();
    IO.println("Success");
    IO.print("test addDoctor -> ");
    systemTest.testGetAllDoctors();
    IO.println("Success");
    IO.print("test removeDoctor -> ");
    systemTest.testRemoveDoctor();
    IO.println("Success");
    IO.print("test addHospital -> ");
    systemTest.testAddHospital();
    IO.println("Success");
    IO.print("test removeHospital -> ");
    systemTest.testRemoveHospital();
    IO.println("Success");
    IO.print("test associateADoctorToAnHospital -> ");
    systemTest.testAssociateDoctorToAnHospital();
    IO.println("Success");
    IO.print("test disassociateADoctorToAnHospital -> ");
    systemTest.testDisassociateDoctorToAnHospital();
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
    IO.print("test getDoctorHospitals -> ");
    systemTest.testGetDoctorHospitals();
    IO.println("Success");
    IO.print("test getHospitalSpecialties -> ");
    systemTest.testGetHospitalSpecialties();
    IO.println("Success");
  }

  private void testAddDoctor() {

    Doctor doc1 = new Doctor("jose", SafetyNetHospital.quotes.ORTOPEDIAQuote.getInstance());
    Doctor doc2 = new Doctor("marcelo", SafetyNetHospital.quotes.CARDIOLOGIAQuote.getInstance());
    safetyNet = SafetyNetNetwork.getInstance();
    safetyNet.addDoctor(doc1);
    safetyNet.addDoctor(doc2);
    assertEqual(safetyNet.getAllDoctors(), SetUtil.set(doc1, doc2));
    safetyNet.clearInstance();
  }

  private void testGetAllDoctors() {

    Doctor doc1 = new Doctor("jose", SafetyNetHospital.quotes.ORTOPEDIAQuote.getInstance());
    Doctor doc2 = new Doctor("marcelo", SafetyNetHospital.quotes.CARDIOLOGIAQuote.getInstance());
    safetyNet = SafetyNetNetwork.getInstance();
    assertEqual(safetyNet.getAllDoctors(), SetUtil.set());
    safetyNet.addDoctor(doc1);
    safetyNet.addDoctor(doc2);
    assertEqual(safetyNet.getAllDoctors(), SetUtil.set(doc1, doc2));
    safetyNet.clearInstance();
  }

  private void testRemoveDoctor() {

    Doctor doc1 = new Doctor("jose", SafetyNetHospital.quotes.ORTOPEDIAQuote.getInstance());
    Doctor doc2 = new Doctor("marcelo", SafetyNetHospital.quotes.CARDIOLOGIAQuote.getInstance());
    Hospital hos1 = new Hospital("sao joao", "porto", 4L);
    Hospital hos2 = new Hospital("santo antonio", "lisboa", 4L);
    safetyNet = SafetyNetNetwork.getInstance();
    safetyNet.addHospital(hos1);
    safetyNet.addHospital(hos2);
    safetyNet.addDoctor(doc1);
    safetyNet.addDoctor(doc2);
    safetyNet.associateDoctorToHospital(hos1.getId(), doc1.getId());
    safetyNet.associateDoctorToHospital(hos2.getId(), doc2.getId());
    safetyNet.removeDoctor(doc2);
    assertEqual(safetyNet.getAllDoctors(), SetUtil.set(doc1));
    for (Iterator iterator_9 = safetyNet.getAllHospitals().iterator(); iterator_9.hasNext(); ) {
      Hospital hs = (Hospital) iterator_9.next();
      assertTrue(!(SetUtil.inSet(doc2.getId(), hs.getDoctorsIds())));
    }
    safetyNet.clearInstance();
  }

  private void testAddHospital() {

    Hospital hos1 = new Hospital("sao joao", "porto", 4L);
    Hospital hos2 = new Hospital("santo antonio", "lisboa", 4L);
    safetyNet = SafetyNetNetwork.getInstance();
    safetyNet.addHospital(hos1);
    safetyNet.addHospital(hos2);
    assertEqual(safetyNet.getAllHospitals(), SetUtil.set(hos1, hos2));
    safetyNet.clearInstance();
  }

  private void testRemoveHospital() {

    Hospital hos1 = new Hospital("sao joao", "porto", 4L);
    Hospital hos2 = new Hospital("santo antonio", "lisboa", 4L);
    safetyNet = SafetyNetNetwork.getInstance();
    safetyNet.addHospital(hos1);
    safetyNet.addHospital(hos2);
    safetyNet.removeHospital(hos1);
    assertEqual(safetyNet.getAllHospitals(), SetUtil.set(hos2));
    safetyNet.clearInstance();
  }

  private void testAssociateDoctorToAnHospital() {

    Hospital hos1 = new Hospital("sao joao", "porto", 4L);
    Hospital hos2 = new Hospital("santo antonio", "lisboa", 4L);
    Doctor doc1 = new Doctor("jose", SafetyNetHospital.quotes.ORTOPEDIAQuote.getInstance());
    Doctor doc2 = new Doctor("marcelo", SafetyNetHospital.quotes.CARDIOLOGIAQuote.getInstance());
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
    assertEqual(safetyNet.getAllDoctors(), SetUtil.set(doc1, doc2));
    safetyNet.clearInstance();
  }

  private void testDisassociateDoctorToAnHospital() {

    Hospital hos1 = new Hospital("sao joao", "porto", 4L);
    Hospital hos2 = new Hospital("santo antonio", "lisboa", 4L);
    Doctor doc1 = new Doctor("jose", SafetyNetHospital.quotes.ORTOPEDIAQuote.getInstance());
    Doctor doc2 = new Doctor("marcelo", SafetyNetHospital.quotes.CARDIOLOGIAQuote.getInstance());
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
    assertEqual(safetyNet.getAllDoctors(), SetUtil.set(doc1, doc2));
    safetyNet.clearInstance();
  }

  private void testGetHospitalsByLocation() {

    Hospital hos1 = new Hospital("sao joao", "porto", 4L);
    Hospital hos2 = new Hospital("santo antonio", "lisboa", 4L);
    Hospital hos3 = new Hospital("hospital da luz", "lisboa", 5L);
    safetyNet = SafetyNetNetwork.getInstance();
    safetyNet.addHospital(hos1);
    safetyNet.addHospital(hos2);
    safetyNet.addHospital(hos3);
    assertEqual(safetyNet.getHospitalsByLocation("porto"), SetUtil.set(hos1));
    assertEqual(safetyNet.getHospitalsByLocation("lisboa"), SetUtil.set(hos2, hos3));
    safetyNet.clearInstance();
  }

  private void testGetAllHospitals() {

    Hospital hos1 = new Hospital("sao joao", "porto", 4L);
    Hospital hos2 = new Hospital("santo antonio", "lisboa", 4L);
    Hospital hos3 = new Hospital("hospital da luz", "lisboa", 5L);
    safetyNet = SafetyNetNetwork.getInstance();
    safetyNet.addHospital(hos1);
    safetyNet.addHospital(hos2);
    safetyNet.addHospital(hos3);
    assertEqual(safetyNet.getAllHospitals(), SetUtil.set(hos1, hos2, hos3));
    safetyNet.clearInstance();
  }

  private void testGetHospitalsByName() {

    Hospital hos1 = new Hospital("sao joao", "porto", 4L);
    Hospital hos2 = new Hospital("santo antonio", "lisboa", 4L);
    Hospital hos3 = new Hospital("hospital da luz", "lisboa", 5L);
    safetyNet = SafetyNetNetwork.getInstance();
    safetyNet.addHospital(hos1);
    safetyNet.addHospital(hos2);
    safetyNet.addHospital(hos3);
    assertEqual(safetyNet.getHospitalsByName("sao joao"), SetUtil.set(hos1));
    assertEqual(safetyNet.getHospitalsByName("santo antonio"), SetUtil.set(hos2));
    assertEqual(safetyNet.getHospitalsByName("hospital da luz"), SetUtil.set(hos3));
    safetyNet.clearInstance();
  }

  private void testGetDoctorHospitals() {

    Hospital hos1 = new Hospital("sao joao", "porto", 4L);
    Hospital hos2 = new Hospital("santo antonio", "lisboa", 4L);
    Doctor doc1 = new Doctor("jose", SafetyNetHospital.quotes.ORTOPEDIAQuote.getInstance());
    Doctor doc2 = new Doctor("marcelo", SafetyNetHospital.quotes.CARDIOLOGIAQuote.getInstance());
    Doctor doc3 = new Doctor("joaquim", SafetyNetHospital.quotes.CARDIOLOGIAQuote.getInstance());
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

  private void testGetHospitalSpecialties() {

    Hospital hos1 = new Hospital("sao joao", "porto", 4L);
    Hospital hos2 = new Hospital("santo antonio", "lisboa", 4L);
    Doctor doc1 = new Doctor("jose", SafetyNetHospital.quotes.ORTOPEDIAQuote.getInstance());
    Doctor doc2 = new Doctor("marcelo", SafetyNetHospital.quotes.CARDIOLOGIAQuote.getInstance());
    Doctor doc3 = new Doctor("joaquim", SafetyNetHospital.quotes.CARDIOLOGIAQuote.getInstance());
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

  public SystemTest() {}

  public String toString() {

    return "SystemTest{" + "safetyNet := " + Utils.toString(safetyNet) + "}";
  }
}
