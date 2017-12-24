package SafetyNetHospital.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class MEDISQuote {
  private static int hc = 0;
  private static MEDISQuote instance = null;

  public MEDISQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static MEDISQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new MEDISQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof MEDISQuote;
  }

  public String toString() {

    return "<MEDIS>";
  }
}
