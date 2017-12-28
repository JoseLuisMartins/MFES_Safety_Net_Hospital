package SafetyNetHospital;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class SafetyNetNetwork {
  private VDMMap hospitals = MapUtil.map();
  private VDMMap doctors = MapUtil.map();
  private VDMMap patients = MapUtil.map();
  private VDMSet appointments = SetUtil.set();
  private static SafetyNetNetwork networkInstance = new SafetyNetNetwork();

  private void cg_init_SafetyNetNetwork_1() {

    return;
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

  public void associateDoctorToHospital(final Number hospitalId, final Number doctorId) {

    ((Hospital) Utils.get(hospitals, hospitalId)).addDoctor(doctorId);
  }

  public void disassociateDoctorFromHospital(final Number hospitalId, final Number doctorId) {

    ((Hospital) Utils.get(hospitals, hospitalId)).removeDoctor(doctorId);
  }

  public VDMMap getHospitals() {

    return Utils.copy(hospitals);
  }

  public void addHospital(final Hospital hospital) {

    hospitals =
        MapUtil.override(
            Utils.copy(hospitals), MapUtil.map(new Maplet(hospital.getId(), hospital)));
  }

  public void removeHospital(final Hospital hospital) {

    hospitals = MapUtil.domResBy(SetUtil.set(hospital.getId()), Utils.copy(hospitals));
    for (Iterator iterator_14 = appointments.iterator(); iterator_14.hasNext(); ) {
      Appointment a = (Appointment) iterator_14.next();
      if (Utils.equals(a.getHospitalId(), hospital.getId())) {
        removeAppointment(a);
      }
    }
  }

  public void addAgreementToHospital(final Number hospitalId, final Object agreement) {

    ((Hospital) Utils.get(hospitals, hospitalId)).addAgreement(agreement);
  }

  public void removeAgreementFromHospital(final Number hospitalId, final Object agreement) {

    ((Hospital) Utils.get(hospitals, hospitalId)).removeAgreement(agreement);
  }

  public Hospital getHospitalsById(final Number hospitalId) {

    return ((Hospital) Utils.get(hospitals, hospitalId));
  }

  public VDMSet getHospitalsByCity(final String city) {

    VDMSet res = SetUtil.set();
    for (Iterator iterator_15 = MapUtil.rng(Utils.copy(hospitals)).iterator();
        iterator_15.hasNext();
        ) {
      Hospital h = (Hospital) iterator_15.next();
      if (Utils.equals(h.getLocation().city, city)) {
        res = SetUtil.union(Utils.copy(res), SetUtil.set(h));
      }
    }
    return Utils.copy(res);
  }

  public VDMSet getHospitalsByName(final String name) {

    VDMSet res = SetUtil.set();
    for (Iterator iterator_16 = MapUtil.rng(Utils.copy(hospitals)).iterator();
        iterator_16.hasNext();
        ) {
      Hospital h = (Hospital) iterator_16.next();
      if (Utils.equals(h.getName(), name)) {
        res = SetUtil.union(Utils.copy(res), SetUtil.set(h));
      }
    }
    return Utils.copy(res);
  }

  public VDMSet getHospitalsByAgreement(final Object agreement) {

    VDMSet res = SetUtil.set();
    for (Iterator iterator_17 = MapUtil.rng(Utils.copy(hospitals)).iterator();
        iterator_17.hasNext();
        ) {
      Hospital h = (Hospital) iterator_17.next();
      if (SetUtil.inSet(agreement, h.getAgreements())) {
        res = SetUtil.union(Utils.copy(res), SetUtil.set(h));
      }
    }
    return Utils.copy(res);
  }

  public VDMSet getHospitalsBySpecialty(final Object specialty) {

    VDMSet res = SetUtil.set();
    for (Iterator iterator_18 = MapUtil.rng(Utils.copy(hospitals)).iterator();
        iterator_18.hasNext();
        ) {
      Hospital h = (Hospital) iterator_18.next();
      for (Iterator iterator_19 = h.getDoctorsIds().iterator(); iterator_19.hasNext(); ) {
        Number d = (Number) iterator_19.next();
        if (Utils.equals(specialty, ((Doctor) Utils.get(doctors, d)).getSpecialty())) {
          res = SetUtil.union(Utils.copy(res), SetUtil.set(h));
        }
      }
    }
    return Utils.copy(res);
  }

  public VDMSet getHospitalSpecialties(final Number hospitalId) {

    VDMSet res = SetUtil.set();
    for (Iterator iterator_20 =
            ((Hospital) Utils.get(hospitals, hospitalId)).getDoctorsIds().iterator();
        iterator_20.hasNext();
        ) {
      Number doctorId = (Number) iterator_20.next();
      res =
          SetUtil.union(
              Utils.copy(res), SetUtil.set(((Doctor) Utils.get(doctors, doctorId)).getSpecialty()));
    }
    return Utils.copy(res);
  }

  public VDMMap getDoctors() {

    return Utils.copy(doctors);
  }

  public void addDoctor(final Doctor doctor) {

    doctors =
        MapUtil.override(Utils.copy(doctors), MapUtil.map(new Maplet(doctor.getId(), doctor)));
  }

  public void removeDoctor(final Doctor doctor) {

    doctors = MapUtil.domResBy(SetUtil.set(doctor.getId()), Utils.copy(doctors));
    for (Iterator iterator_21 = MapUtil.rng(Utils.copy(hospitals)).iterator();
        iterator_21.hasNext();
        ) {
      Hospital h = (Hospital) iterator_21.next();
      if (SetUtil.inSet(doctor.getId(), h.getDoctorsIds())) {
        h.removeDoctor(doctor.getId());
      }
    }
    for (Iterator iterator_22 = appointments.iterator(); iterator_22.hasNext(); ) {
      Appointment a = (Appointment) iterator_22.next();
      if (Utils.equals(a.getDoctorId(), doctor.getId())) {
        removeAppointment(a);
      }
    }
  }

  public VDMSet getDoctorsBySpecialty(final Object s) {

    VDMSet res = SetUtil.set();
    for (Iterator iterator_23 = MapUtil.rng(Utils.copy(doctors)).iterator();
        iterator_23.hasNext();
        ) {
      Doctor d = (Doctor) iterator_23.next();
      if (Utils.equals(d.getSpecialty(), s)) {
        res = SetUtil.union(Utils.copy(res), SetUtil.set(d));
      }
    }
    return Utils.copy(res);
  }

  public Doctor getDoctorById(final Number doctorId) {

    return ((Doctor) Utils.get(doctors, doctorId));
  }

  public VDMSet getDoctorHospitals(final Number doctorId) {

    VDMSet res = SetUtil.set();
    for (Iterator iterator_24 = MapUtil.rng(Utils.copy(hospitals)).iterator();
        iterator_24.hasNext();
        ) {
      Hospital h = (Hospital) iterator_24.next();
      if (SetUtil.inSet(doctorId, h.getDoctorsIds())) {
        res = SetUtil.union(Utils.copy(res), SetUtil.set(h));
      }
    }
    return Utils.copy(res);
  }

  public void addPatient(final Patient patient) {

    patients =
        MapUtil.override(Utils.copy(patients), MapUtil.map(new Maplet(patient.getId(), patient)));
  }

  public void removePatient(final Patient patient) {

    for (Iterator iterator_25 = appointments.iterator(); iterator_25.hasNext(); ) {
      Appointment a = (Appointment) iterator_25.next();
      if (Utils.equals(a.getPatientId(), patient.getId())) {
        removeAppointment(a);
      }
    }
    patients = MapUtil.domResBy(SetUtil.set(patient.getId()), Utils.copy(patients));
  }

  public void addClinicalObservation(final Number patientId, final String obs) {

    ((Patient) Utils.get(patients, patientId)).addObservation(obs);
  }

  public VDMMap getPatients() {

    return Utils.copy(patients);
  }

  public Patient getPatientById(final Number patientId) {

    return ((Patient) Utils.get(patients, patientId));
  }

  public VDMSet getAppointments() {

    return Utils.copy(appointments);
  }

  public VDMSet getHospitalAppointments(final Number hospitalId) {

    VDMSet res = SetUtil.set();
    for (Iterator iterator_26 = appointments.iterator(); iterator_26.hasNext(); ) {
      Appointment a = (Appointment) iterator_26.next();
      if (Utils.equals(a.getHospitalId(), hospitalId)) {
        res = SetUtil.union(Utils.copy(res), SetUtil.set(a));
      }
    }
    return Utils.copy(res);
  }

  public Number getHospitalNumberOfAppointments(final Number hospitalId) {

    return getHospitalAppointments(hospitalId).size();
  }

  public VDMSet getDoctorAppointments(final Number doctorId) {

    VDMSet res = SetUtil.set();
    for (Iterator iterator_27 = appointments.iterator(); iterator_27.hasNext(); ) {
      Appointment a = (Appointment) iterator_27.next();
      if (Utils.equals(a.getDoctorId(), doctorId)) {
        res = SetUtil.union(Utils.copy(res), SetUtil.set(a));
      }
    }
    return Utils.copy(res);
  }

  public VDMSet getPatientAppointments(final Number patientId) {

    VDMSet res = SetUtil.set();
    for (Iterator iterator_28 = appointments.iterator(); iterator_28.hasNext(); ) {
      Appointment a = (Appointment) iterator_28.next();
      if (Utils.equals(a.getPatientId(), patientId)) {
        res = SetUtil.union(Utils.copy(res), SetUtil.set(a));
      }
    }
    return Utils.copy(res);
  }

  public VDMSet getSpecialtyAppointments(final Object specialty) {

    VDMSet res = SetUtil.set();
    for (Iterator iterator_29 = appointments.iterator(); iterator_29.hasNext(); ) {
      Appointment a = (Appointment) iterator_29.next();
      if (Utils.equals(((Doctor) Utils.get(doctors, a.getDoctorId())).getSpecialty(), specialty)) {
        res = SetUtil.union(Utils.copy(res), SetUtil.set(a));
      }
    }
    return Utils.copy(res);
  }

  public void addAppointment(final Appointment a) {

    appointments = SetUtil.union(Utils.copy(appointments), SetUtil.set(a));
  }

  public void removeAppointment(final Appointment a) {

    appointments = SetUtil.diff(Utils.copy(appointments), SetUtil.set(a));
  }

  public ModelUtils.Date_DoctorId getHospitalClosestAvailableDate(final VDMSet appointmentSet) {

    ModelUtils.Date minDate = ModelUtils.getMaxDate();
    Number doctorId = 0L;
    VDMSet availableDoctors = SetUtil.set();
    VDMSet occupiedDates = SetUtil.set();
    for (Iterator iterator_30 = appointmentSet.iterator(); iterator_30.hasNext(); ) {
      Appointment ap = (Appointment) iterator_30.next();
      availableDoctors = SetUtil.union(Utils.copy(availableDoctors), SetUtil.set(ap.getDoctorId()));
      occupiedDates = SetUtil.union(Utils.copy(occupiedDates), SetUtil.set(ap.getDate()));
    }
    occupiedDates = SetUtil.union(Utils.copy(occupiedDates), SetUtil.set(ModelUtils.getMinDate()));
    for (Iterator iterator_31 = occupiedDates.iterator(); iterator_31.hasNext(); ) {
      ModelUtils.Date date = (ModelUtils.Date) iterator_31.next();
      ModelUtils.Date auxDate = Appointment.getNextAppointmentDate(Utils.copy(date));
      if (ModelUtils.isDateLower(Utils.copy(auxDate), Utils.copy(minDate))) {
        for (Iterator iterator_32 = availableDoctors.iterator(); iterator_32.hasNext(); ) {
          Number docId = (Number) iterator_32.next();
          Boolean forAllExpResult_11 = true;
          VDMSet set_12 = getDoctorAppointments(docId);
          for (Iterator iterator_12 = set_12.iterator();
              iterator_12.hasNext() && forAllExpResult_11;
              ) {
            Appointment docAp = ((Appointment) iterator_12.next());
            forAllExpResult_11 =
                Appointment.appointmentDatesDontOverlap(docAp.getDate(), Utils.copy(auxDate));
          }
          if (forAllExpResult_11) {
            doctorId = docId;
            minDate = Utils.copy(auxDate);
          }
        }
      }
    }
    return new ModelUtils.Date_DoctorId(minDate, doctorId);
  }

  public String toString() {

    return "SafetyNetNetwork{"
        + "hospitals := "
        + Utils.toString(hospitals)
        + ", doctors := "
        + Utils.toString(doctors)
        + ", patients := "
        + Utils.toString(patients)
        + ", appointments := "
        + Utils.toString(appointments)
        + ", networkInstance := "
        + Utils.toString(networkInstance)
        + "}";
  }
}
