package SafetyNetHospital.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class GINECOLOGIAQuote {
  private static int hc = 0;
  private static GINECOLOGIAQuote instance = null;

  public GINECOLOGIAQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static GINECOLOGIAQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new GINECOLOGIAQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof GINECOLOGIAQuote;
  }

  public String toString() {

    return "<GINECOLOGIA>";
  }
}
