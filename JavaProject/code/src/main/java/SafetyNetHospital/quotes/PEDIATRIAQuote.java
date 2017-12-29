package SafetyNetHospital.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class PEDIATRIAQuote {
  private static int hc = 0;
  private static PEDIATRIAQuote instance = null;

  public PEDIATRIAQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static PEDIATRIAQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new PEDIATRIAQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof PEDIATRIAQuote;
  }

  public String toString() {

    return "<PEDIATRIA>";
  }
}
