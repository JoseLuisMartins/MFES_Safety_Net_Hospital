package SafetyNetHospital;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Hospital {
  private String name = SeqUtil.toStr(SeqUtil.seq());
  private ModelUtils.Location location;
  private VDMSet agreements = SetUtil.set();
  private VDMSet doctorsIds = SetUtil.set();
  private static Number currId = 0L;
  private Number id = Hospital.currId;

  public void cg_init_Hospital_1(final String n, final ModelUtils.Location l, final VDMSet a) {

    String atomicTmp_9 = n;
    ModelUtils.Location atomicTmp_10 = Utils.copy(l);
    VDMSet atomicTmp_11 = Utils.copy(a);
    Number atomicTmp_12 = Hospital.currId.longValue() + 1L;
    {
        /* Start of atomic statement */
      name = atomicTmp_9;
      location = Utils.copy(atomicTmp_10);
      agreements = Utils.copy(atomicTmp_11);
      currId = atomicTmp_12;
    } /* End of atomic statement */

    return;
  }

  public Hospital(final String n, final ModelUtils.Location l, final VDMSet a) {

    cg_init_Hospital_1(n, Utils.copy(l), Utils.copy(a));
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

  public ModelUtils.Location getLocation() {

    return Utils.copy(location);
  }

  public VDMSet getAgreements() {

    return Utils.copy(agreements);
  }

  public void addAgreement(final Object a) {

    agreements = SetUtil.union(Utils.copy(agreements), SetUtil.set(a));
  }

  public void removeAgreement(final Object a) {

    agreements = SetUtil.diff(Utils.copy(agreements), SetUtil.set(a));
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
        + ", agreements := "
        + Utils.toString(agreements)
        + ", doctorsIds := "
        + Utils.toString(doctorsIds)
        + ", currId := "
        + Utils.toString(currId)
        + ", id := "
        + Utils.toString(id)
        + "}";
  }
}
