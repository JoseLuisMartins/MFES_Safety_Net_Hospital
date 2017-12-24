package SafetyNetHospital.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class NEUROLOGIAQuote {
  private static int hc = 0;
  private static NEUROLOGIAQuote instance = null;

  public NEUROLOGIAQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static NEUROLOGIAQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new NEUROLOGIAQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof NEUROLOGIAQuote;
  }

  public String toString() {

    return "<NEUROLOGIA>";
  }
}
