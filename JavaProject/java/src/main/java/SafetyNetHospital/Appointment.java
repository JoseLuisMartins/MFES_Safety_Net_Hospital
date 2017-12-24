package SafetyNetHospital;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Appointment {
  public static final Number APPOINTMENT_TIME_MIN = 30L;
  private ModelUtils.Date date;
  private Number hospitalId;
  private Number doctorId;
  private Number patientId;

  public void cg_init_Appointment_1(
      final ModelUtils.Date d, final Number hos, final Number doc, final Number pat) {

    ModelUtils.Date atomicTmp_1 = Utils.copy(d);
    Number atomicTmp_2 = hos;
    Number atomicTmp_3 = doc;
    Number atomicTmp_4 = pat;
    {
        /* Start of atomic statement */
      date = Utils.copy(atomicTmp_1);
      hospitalId = atomicTmp_2;
      doctorId = atomicTmp_3;
      patientId = atomicTmp_4;
    } /* End of atomic statement */

    return;
  }

  public Appointment(
      final ModelUtils.Date d, final Number hos, final Number doc, final Number pat) {

    cg_init_Appointment_1(Utils.copy(d), hos, doc, pat);
  }

  public ModelUtils.Date getDate() {

    return Utils.copy(date);
  }

  public Number getHospitalId() {

    return hospitalId;
  }

  public Number getDoctorId() {

    return doctorId;
  }

  public Number getPatientId() {

    return patientId;
  }

  public Appointment() {}

  public static Boolean appointmentDatesDontOverlap(
      final ModelUtils.Date d1, final ModelUtils.Date d2) {

    Boolean orResult_1 = false;

    if (ModelUtils.dateToNat(getNextAppointmentDate(Utils.copy(d1))).longValue()
        <= ModelUtils.dateToNat(Utils.copy(d2)).longValue()) {
      orResult_1 = true;
    } else {
      orResult_1 =
          ModelUtils.dateToNat(getNextAppointmentDate(Utils.copy(d2))).longValue()
              <= ModelUtils.dateToNat(Utils.copy(d1)).longValue();
    }

    return orResult_1;
  }

  public static ModelUtils.Date getNextAppointmentDate(final ModelUtils.Date d) {

    Boolean andResult_1 = false;

    if (Utils.equals(d.month, 12L)) {
      Boolean andResult_2 = false;

      if (Utils.equals(d.day, 30L)) {
        Boolean andResult_3 = false;

        if (Utils.equals(d.hour, 23L)) {
          if (d.min.longValue()
              >= ModelUtils.HOUR_MIN.longValue() - Appointment.APPOINTMENT_TIME_MIN.longValue()) {
            andResult_3 = true;
          }
        }

        if (andResult_3) {
          andResult_2 = true;
        }
      }

      if (andResult_2) {
        andResult_1 = true;
      }
    }

    if (andResult_1) {
      return new ModelUtils.Date(
          d.year.longValue() + 1L,
          1L,
          1L,
          0L,
          Utils.mod(
              d.min.longValue() + Appointment.APPOINTMENT_TIME_MIN.longValue(),
              ModelUtils.HOUR_MIN.longValue()));

    } else {
      Boolean andResult_4 = false;

      if (Utils.equals(d.day, 30L)) {
        Boolean andResult_5 = false;

        if (Utils.equals(d.hour, 23L)) {
          if (d.min.longValue()
              >= ModelUtils.HOUR_MIN.longValue() - Appointment.APPOINTMENT_TIME_MIN.longValue()) {
            andResult_5 = true;
          }
        }

        if (andResult_5) {
          andResult_4 = true;
        }
      }

      if (andResult_4) {
        return new ModelUtils.Date(
            d.year,
            d.month.longValue() + 1L,
            1L,
            0L,
            Utils.mod(
                d.min.longValue() + Appointment.APPOINTMENT_TIME_MIN.longValue(),
                ModelUtils.HOUR_MIN.longValue()));

      } else {
        Boolean andResult_6 = false;

        if (Utils.equals(d.hour, 23L)) {
          if (d.min.longValue()
              >= ModelUtils.HOUR_MIN.longValue() - Appointment.APPOINTMENT_TIME_MIN.longValue()) {
            andResult_6 = true;
          }
        }

        if (andResult_6) {
          return new ModelUtils.Date(
              d.year,
              d.month,
              d.day.longValue() + 1L,
              0L,
              Utils.mod(
                  d.min.longValue() + Appointment.APPOINTMENT_TIME_MIN.longValue(),
                  ModelUtils.HOUR_MIN.longValue()));

        } else {
          if (d.min.longValue()
              >= ModelUtils.HOUR_MIN.longValue() - Appointment.APPOINTMENT_TIME_MIN.longValue()) {
            return new ModelUtils.Date(
                d.year,
                d.month,
                d.day,
                d.hour.longValue() + 1L,
                Utils.mod(
                    d.min.longValue() + Appointment.APPOINTMENT_TIME_MIN.longValue(),
                    ModelUtils.HOUR_MIN.longValue()));

          } else {
            return new ModelUtils.Date(
                d.year,
                d.month,
                d.day,
                d.hour,
                d.min.longValue() + Appointment.APPOINTMENT_TIME_MIN.longValue());
          }
        }
      }
    }
  }

  public String toString() {

    return "Appointment{"
        + "APPOINTMENT_TIME_MIN = "
        + Utils.toString(APPOINTMENT_TIME_MIN)
        + ", date := "
        + Utils.toString(date)
        + ", hospitalId := "
        + Utils.toString(hospitalId)
        + ", doctorId := "
        + Utils.toString(doctorId)
        + ", patientId := "
        + Utils.toString(patientId)
        + "}";
  }
}
