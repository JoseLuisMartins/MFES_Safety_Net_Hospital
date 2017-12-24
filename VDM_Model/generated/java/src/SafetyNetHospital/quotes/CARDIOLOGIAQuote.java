package SafetyNetHospital.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class CARDIOLOGIAQuote {
  private static int hc = 0;
  private static CARDIOLOGIAQuote instance = null;

  public CARDIOLOGIAQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static CARDIOLOGIAQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new CARDIOLOGIAQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof CARDIOLOGIAQuote;
  }

  public String toString() {

    return "<CARDIOLOGIA>";
  }
}
