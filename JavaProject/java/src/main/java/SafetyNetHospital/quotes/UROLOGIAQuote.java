package SafetyNetHospital.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class UROLOGIAQuote {
  private static int hc = 0;
  private static UROLOGIAQuote instance = null;

  public UROLOGIAQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static UROLOGIAQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new UROLOGIAQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof UROLOGIAQuote;
  }

  public String toString() {

    return "<UROLOGIA>";
  }
}
