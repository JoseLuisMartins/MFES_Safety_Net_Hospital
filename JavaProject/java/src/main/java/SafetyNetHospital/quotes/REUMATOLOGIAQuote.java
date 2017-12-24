package SafetyNetHospital.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class REUMATOLOGIAQuote {
  private static int hc = 0;
  private static REUMATOLOGIAQuote instance = null;

  public REUMATOLOGIAQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static REUMATOLOGIAQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new REUMATOLOGIAQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof REUMATOLOGIAQuote;
  }

  public String toString() {

    return "<REUMATOLOGIA>";
  }
}
