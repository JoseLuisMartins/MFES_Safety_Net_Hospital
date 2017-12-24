package SafetyNetHospital.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class ORTOPEDIAQuote {
  private static int hc = 0;
  private static ORTOPEDIAQuote instance = null;

  public ORTOPEDIAQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static ORTOPEDIAQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new ORTOPEDIAQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof ORTOPEDIAQuote;
  }

  public String toString() {

    return "<ORTOPEDIA>";
  }
}
