package SafetyNetHospital;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Hospital {
  private String name = SeqUtil.toStr(SeqUtil.seq());
  private String location = SeqUtil.toStr(SeqUtil.seq());
  private Number max_doctors = 1L;
  private VDMSet doctorsIds = SetUtil.set();
  private static Number currId = 0L;
  private Number id = Hospital.currId;

  public void cg_init_Hospital_1(final String n, final String l, final Number m_d) {

    name = n;
    location = l;
    max_doctors = m_d;
    currId = Hospital.currId.longValue() + 1L;
    return;
  }

  public Hospital(final String n, final String l, final Number m_d) {

    cg_init_Hospital_1(n, l, m_d);
  }

  public String getName() {

    return name;
  }

  public Number getId() {

    return id;
  }

  public VDMSet getDoctorsIds() {

    return Utils.copy(doctorsIds);
  }

  public String getLocation() {

    return location;
  }

  public void addDoctor(final Number doctorId) {

    doctorsIds = SetUtil.union(Utils.copy(doctorsIds), SetUtil.set(doctorId));
  }

  public void removeDoctor(final Number doctorId) {

    doctorsIds = SetUtil.diff(Utils.copy(doctorsIds), SetUtil.set(doctorId));
  }

  public Hospital() {}

  public String toString() {

    return "Hospital{"
        + "name := "
        + Utils.toString(name)
        + ", location := "
        + Utils.toString(location)
        + ", max_doctors := "
        + Utils.toString(max_doctors)
        + ", doctorsIds := "
        + Utils.toString(doctorsIds)
        + ", currId := "
        + Utils.toString(currId)
        + ", id := "
        + Utils.toString(id)
        + "}";
  }
}
