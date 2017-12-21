package SafetyNetHospital.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class MEDICAREQuote {
  private static int hc = 0;
  private static MEDICAREQuote instance = null;

  public MEDICAREQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static MEDICAREQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new MEDICAREQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof MEDICAREQuote;
  }

  public String toString() {

    return "<MEDICARE>";
  }
}
