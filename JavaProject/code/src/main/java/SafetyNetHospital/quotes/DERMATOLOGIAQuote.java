package SafetyNetHospital.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class DERMATOLOGIAQuote {
  private static int hc = 0;
  private static DERMATOLOGIAQuote instance = null;

  public DERMATOLOGIAQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static DERMATOLOGIAQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new DERMATOLOGIAQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof DERMATOLOGIAQuote;
  }

  public String toString() {

    return "<DERMATOLOGIA>";
  }
}
