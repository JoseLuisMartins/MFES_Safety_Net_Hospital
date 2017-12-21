package SafetyNetHospital;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Doctor {
  private String name = SeqUtil.toStr(SeqUtil.seq());
  private Object specialty;
  private static Number currId = 0L;
  private Number id = Doctor.currId;

  public void cg_init_Doctor_1(final String n, final Object s) {

    name = n;
    specialty = s;
    currId = Doctor.currId.longValue() + 1L;
    return;
  }

  public Doctor(final String n, final Object s) {

    cg_init_Doctor_1(n, s);
  }

  public Number getId() {

    return id;
  }

  public Object getSpecialty() {

    return specialty;
  }

  public Doctor() {}

  public String toString() {

    return "Doctor{"
        + "name := "
        + Utils.toString(name)
        + ", specialty := "
        + Utils.toString(specialty)
        + ", currId := "
        + Utils.toString(currId)
        + ", id := "
        + Utils.toString(id)
        + "}";
  }
}
