package SafetyNetHospital;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Patient extends Person {
  private VDMSeq clinicalObservations = SeqUtil.seq();
  private static Number currId = 0L;
  private Number id = Patient.currId;

  public void cg_init_Patient_1(final String n, final Number a, final String d) {

    String atomicTmp_13 = n;
    Number atomicTmp_14 = a;
    VDMSeq atomicTmp_15 = SeqUtil.conc(Utils.copy(clinicalObservations), SeqUtil.seq(d));
    Number atomicTmp_16 = Patient.currId.longValue() + 1L;
    {
        /* Start of atomic statement */
      name = atomicTmp_13;
      age = atomicTmp_14;
      clinicalObservations = Utils.copy(atomicTmp_15);
      currId = atomicTmp_16;
    } /* End of atomic statement */

    return;
  }

  public Patient(final String n, final Number a, final String d) {

    cg_init_Patient_1(n, a, d);
  }

  public Number getId() {

    return id;
  }

  public String getName() {

    return retName();
  }

  public Number getAge() {

    return retAge();
  }

  public VDMSeq getClinicalObservations() {

    return Utils.copy(clinicalObservations);
  }

  public void addObservation(final String d) {

    clinicalObservations = SeqUtil.conc(Utils.copy(clinicalObservations), SeqUtil.seq(d));
  }

  public Patient() {}

  public String toString() {

    return "Patient{"
        + "clinicalObservations := "
        + Utils.toString(clinicalObservations)
        + ", currId := "
        + Utils.toString(currId)
        + ", id := "
        + Utils.toString(id)
        + "}";
  }
}
