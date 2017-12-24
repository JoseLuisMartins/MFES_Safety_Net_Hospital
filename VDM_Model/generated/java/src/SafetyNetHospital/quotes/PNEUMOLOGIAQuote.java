package SafetyNetHospital.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class PNEUMOLOGIAQuote {
  private static int hc = 0;
  private static PNEUMOLOGIAQuote instance = null;

  public PNEUMOLOGIAQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static PNEUMOLOGIAQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new PNEUMOLOGIAQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof PNEUMOLOGIAQuote;
  }

  public String toString() {

    return "<PNEUMOLOGIA>";
  }
}
