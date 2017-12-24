package SafetyNetHospital;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Person {
  protected String name = SeqUtil.toStr(SeqUtil.seq());
  protected Number age;

  protected String retName() {

    return name;
  }

  protected Number retAge() {

    return age;
  }

  public Person() {}

  public String toString() {

    return "Person{" + "name := " + Utils.toString(name) + ", age := " + Utils.toString(age) + "}";
  }
}
