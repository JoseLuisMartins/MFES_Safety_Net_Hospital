package SafetyNetHospital;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Appointment {
  private String patientName = SeqUtil.toStr(SeqUtil.seq());
  private Utils.Date date;
  private Number hospitalId;
  private Number doctorId;

  public void cg_init_Appointment_1(
      final String n, final Utils.Date d, final Number hos, final Number doc) {

    patientName = n;
    date = Utils.copy(d);
    hospitalId = hos;
    doctorId = doc;
    return;
  }

  public Appointment(final String n, final Utils.Date d, final Number hos, final Number doc) {

    cg_init_Appointment_1(n, Utils.copy(d), hos, doc);
  }

  public Utils.Date getDate() {

    return Utils.copy(date);
  }

  public Number getDoctorId() {

    return doctorId;
  }

  public Number getHospitalId() {

    return hospitalId;
  }

  public Appointment() {}

  public String toString() {

    return "Appointment{"
        + "patientName := "
        + Utils.toString(patientName)
        + ", date := "
        + Utils.toString(date)
        + ", hospitalId := "
        + Utils.toString(hospitalId)
        + ", doctorId := "
        + Utils.toString(doctorId)
        + "}";
  }
}
