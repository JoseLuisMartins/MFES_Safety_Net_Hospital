package SafetyNetHospital.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class OFTALMOLOGIAQuote {
  private static int hc = 0;
  private static OFTALMOLOGIAQuote instance = null;

  public OFTALMOLOGIAQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static OFTALMOLOGIAQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new OFTALMOLOGIAQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof OFTALMOLOGIAQuote;
  }

  public String toString() {

    return "<OFTALMOLOGIA>";
  }
}
