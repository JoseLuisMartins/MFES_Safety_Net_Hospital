package SafetyNetHospital;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Doctor extends Person {
  private Object specialty;
  private static Number currId = 0L;
  private Number id = Doctor.currId;

  public void cg_init_Doctor_1(final String n, final Number a, final Object s) {

    String atomicTmp_5 = n;
    Number atomicTmp_6 = a;
    Object atomicTmp_7 = s;
    Number atomicTmp_8 = Doctor.currId.longValue() + 1L;
    {
        /* Start of atomic statement */
      name = atomicTmp_5;
      age = atomicTmp_6;
      specialty = atomicTmp_7;
      currId = atomicTmp_8;
    } /* End of atomic statement */

    return;
  }

  public Doctor(final String n, final Number a, final Object s) {

    cg_init_Doctor_1(n, a, s);
  }

  public Number getId() {

    return id;
  }

  public Object getSpecialty() {

    return specialty;
  }

  public String getName() {

    return retName();
  }

  public Number getAge() {

    return retAge();
  }

  public Doctor() {}

  public String toString() {

    return "Doctor{"
        + "name := "
        + this.getName()
        + ", age:= "
        + this.getAge()
        + ", specialty := "
        + Utils.toString(specialty)
        + ", currId := "
        + Utils.toString(currId)
        + ", id := "
        + Utils.toString(id)
        + "}";
  }
}
