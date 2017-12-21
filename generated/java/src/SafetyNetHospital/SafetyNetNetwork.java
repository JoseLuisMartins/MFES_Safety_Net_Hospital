package SafetyNetHospital;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class SafetyNetNetwork {
  private VDMMap hospitals = MapUtil.map();
  private VDMMap doctors = MapUtil.map();
  private VDMSet appointments = SetUtil.set();
  private static SafetyNetNetwork networkInstance = new SafetyNetNetwork();

  private void cg_init_SafetyNetNetwork_1() {

    return;
  }

  public static void main() {

    SafetyNetNetwork safetyNet = SafetyNetNetwork.getInstance();
    Hospital hos1 = new Hospital("sao joao", "porto", 4L);
    Hospital hos2 = new Hospital("santo antonio", "lisboa", 4L);
    Doctor doc1 = new Doctor("jose", SafetyNetHospital.quotes.ORTOPEDIAQuote.getInstance());
    Doctor doc2 = new Doctor("marcelo", SafetyNetHospital.quotes.CARDIOLOGIAQuote.getInstance());
    Appointment ap1 =
        new Appointment(
            "paciente jose", new Utils.Date(2017L, 12L, 21L, 8L), hos1.getId(), doc1.getId());
    Appointment ap3 =
        new Appointment(
            "paciente susana", new Utils.Date(2017L, 12L, 21L, 9L), hos2.getId(), doc2.getId());
    safetyNet.addHospital(hos1);
    safetyNet.addHospital(hos2);
    safetyNet.addDoctor(doc1);
    safetyNet.addDoctor(doc2);
    safetyNet.associateDoctorToHospital(hos1.getId(), doc1.getId());
    safetyNet.associateDoctorToHospital(hos2.getId(), doc1.getId());
    safetyNet.associateDoctorToHospital(hos2.getId(), doc2.getId());
    safetyNet.addAppointment(ap1);
    safetyNet.addAppointment(ap3);
    IO.println("Network");
    IO.println(safetyNet);
    IO.println("All Hospitals");
    IO.println(safetyNet.getAllHospitals());
    IO.println("Hospitals by name");
    IO.println(safetyNet.getHospitalsByName("sao joao"));
    IO.println("Hospitals by location");
    IO.println(safetyNet.getHospitalsByLocation("lisboa"));
    IO.println("Hospitals specialties->1");
    IO.println(safetyNet.getHospitalSpecialties(hos1.getId()));
    IO.println("Hospitals specialties->2");
    IO.println(safetyNet.getHospitalSpecialties(hos2.getId()));
    IO.println("Hospitals number appointmens");
    IO.println(safetyNet.getHospitalNumberOfAppointments(hos1.getId()));
    IO.println("Hospitals where a doctor works");
    IO.println(safetyNet.getDoctorHospitals(doc1.getId()));
    IO.println("Removed doc1");
    safetyNet.removeDoctor(doc1);
    IO.println("Hospitals specialties->1");
    IO.println(safetyNet.getHospitalSpecialties(hos1.getId()));
    IO.println("Hospitals specialties->2");
    IO.println(safetyNet.getHospitalSpecialties(hos2.getId()));
    IO.println("Hospitals where a doctor works");
    IO.println(safetyNet.getDoctorHospitals(doc1.getId()));
  }

  private SafetyNetNetwork() {

    cg_init_SafetyNetNetwork_1();
  }

  public static SafetyNetNetwork getInstance() {

    return SafetyNetNetwork.networkInstance;
  }

  public static void clearInstance() {

    networkInstance = new SafetyNetNetwork();
  }

  public void addHospital(final Hospital hospital) {

    hospitals =
        MapUtil.override(
            Utils.copy(hospitals), MapUtil.map(new Maplet(hospital.getId(), hospital)));
  }

  public void removeHospital(final Hospital hospital) {

    hospitals = MapUtil.domResBy(SetUtil.set(hospital.getId()), Utils.copy(hospitals));
  }

  public void addDoctor(final Doctor doctor) {

    doctors =
        MapUtil.override(Utils.copy(doctors), MapUtil.map(new Maplet(doctor.getId(), doctor)));
  }

  public void removeDoctor(final Doctor doctor) {

    doctors = MapUtil.domResBy(SetUtil.set(doctor.getId()), Utils.copy(doctors));
    for (Iterator iterator_2 = MapUtil.rng(Utils.copy(hospitals)).iterator();
        iterator_2.hasNext();
        ) {
      Hospital h = (Hospital) iterator_2.next();
      if (SetUtil.inSet(doctor.getId(), h.getDoctorsIds())) {
        h.removeDoctor(doctor.getId());
      }
    }
  }

  public void associateDoctorToHospital(final Number hospitalId, final Number doctorId) {

    ((Hospital) Utils.get(hospitals, hospitalId)).addDoctor(doctorId);
  }

  public void disassociateDoctorFromHospital(final Number hospitalId, final Number doctorId) {

    ((Hospital) Utils.get(hospitals, hospitalId)).removeDoctor(doctorId);
  }

  public VDMSet getAllHospitals() {

    return MapUtil.rng(Utils.copy(hospitals));
  }

  public Hospital getHospitalsById(final Number hospitalId) {

    return ((Hospital) Utils.get(hospitals, hospitalId));
  }

  public VDMSet getHospitalsByLocation(final String location) {

    VDMSet res = SetUtil.set();
    for (Iterator iterator_3 = MapUtil.rng(Utils.copy(hospitals)).iterator();
        iterator_3.hasNext();
        ) {
      Hospital h = (Hospital) iterator_3.next();
      if (Utils.equals(h.getLocation(), location)) {
        res = SetUtil.union(Utils.copy(res), SetUtil.set(h));
      }
    }
    return Utils.copy(res);
  }

  public VDMSet getHospitalsByName(final String name) {

    VDMSet res = SetUtil.set();
    for (Iterator iterator_4 = MapUtil.rng(Utils.copy(hospitals)).iterator();
        iterator_4.hasNext();
        ) {
      Hospital h = (Hospital) iterator_4.next();
      if (Utils.equals(h.getName(), name)) {
        res = SetUtil.union(Utils.copy(res), SetUtil.set(h));
      }
    }
    return Utils.copy(res);
  }

  public VDMSet getHospitalSpecialties(final Number hospitalId) {

    VDMSet res = SetUtil.set();
    for (Iterator iterator_5 =
            ((Hospital) Utils.get(hospitals, hospitalId)).getDoctorsIds().iterator();
        iterator_5.hasNext();
        ) {
      Number doctorId = (Number) iterator_5.next();
      res =
          SetUtil.union(
              Utils.copy(res), SetUtil.set(((Doctor) Utils.get(doctors, doctorId)).getSpecialty()));
    }
    return Utils.copy(res);
  }

  public VDMSet getHospitalAppointments(final Number hospitalId) {

    VDMSet res = SetUtil.set();
    for (Iterator iterator_6 = appointments.iterator(); iterator_6.hasNext(); ) {
      Appointment a = (Appointment) iterator_6.next();
      if (Utils.equals(a.getHospitalId(), hospitalId)) {
        res = SetUtil.union(Utils.copy(res), SetUtil.set(a));
      }
    }
    return Utils.copy(res);
  }

  public Number getHospitalNumberOfAppointments(final Number hospitalId) {

    return getHospitalAppointments(hospitalId).size();
  }

  public VDMSet getAllDoctors() {

    return MapUtil.rng(Utils.copy(doctors));
  }

  public Doctor getDoctorById(final Number doctorId) {

    return ((Doctor) Utils.get(doctors, doctorId));
  }

  public VDMSet getDoctorHospitals(final Number doctorId) {

    VDMSet res = SetUtil.set();
    for (Iterator iterator_7 = MapUtil.rng(Utils.copy(hospitals)).iterator();
        iterator_7.hasNext();
        ) {
      Hospital h = (Hospital) iterator_7.next();
      if (SetUtil.inSet(doctorId, h.getDoctorsIds())) {
        res = SetUtil.union(Utils.copy(res), SetUtil.set(h));
      }
    }
    return Utils.copy(res);
  }

  public VDMSet getDoctorAppointments(final Number doctorId) {

    VDMSet res = SetUtil.set();
    for (Iterator iterator_8 = appointments.iterator(); iterator_8.hasNext(); ) {
      Appointment a = (Appointment) iterator_8.next();
      if (Utils.equals(a.getDoctorId(), doctorId)) {
        res = SetUtil.union(Utils.copy(res), SetUtil.set(a));
      }
    }
    return Utils.copy(res);
  }

  public void addAppointment(final Appointment a) {

    appointments = SetUtil.union(Utils.copy(appointments), SetUtil.set(a));
  }

  public String toString() {

    return "SafetyNetNetwork{"
        + "hospitals := "
        + Utils.toString(hospitals)
        + ", doctors := "
        + Utils.toString(doctors)
        + ", appointments := "
        + Utils.toString(appointments)
        + ", networkInstance := "
        + Utils.toString(networkInstance)
        + "}";
  }
}
