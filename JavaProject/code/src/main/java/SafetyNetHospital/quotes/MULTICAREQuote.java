package SafetyNetHospital.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class MULTICAREQuote {
  private static int hc = 0;
  private static MULTICAREQuote instance = null;

  public MULTICAREQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static MULTICAREQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new MULTICAREQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof MULTICAREQuote;
  }

  public String toString() {

    return "<MULTICARE>";
  }
}
