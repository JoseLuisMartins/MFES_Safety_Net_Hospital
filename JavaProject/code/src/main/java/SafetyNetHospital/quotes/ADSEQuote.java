package SafetyNetHospital.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class ADSEQuote {
  private static int hc = 0;
  private static ADSEQuote instance = null;

  public ADSEQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static ADSEQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new ADSEQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof ADSEQuote;
  }

  public String toString() {

    return "<ADSE>";
  }
}
