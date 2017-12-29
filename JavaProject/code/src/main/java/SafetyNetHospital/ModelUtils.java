package SafetyNetHospital;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class ModelUtils {
  public static final Number HOUR_MIN = 60L;
  public static final Number DAY_HOURS = 24L;
  public static final Number MONTH_DAYS = 30L;
  public static final Number YEAR_MONTHS = 12L;

  public ModelUtils() {}

  public static Boolean isDateLower(final Date d1, final Date d2) {

    return dateToNat(Utils.copy(d1)).longValue() < dateToNat(Utils.copy(d2)).longValue();
  }

  public static Date getMaxDate() {

    return new Date(99999L, 12L, 30L, 23L, 59L);
  }

  public static Date getMinDate() {

    return new Date(2018L, 1L, 1L, 8L, 0L);
  }

  public static Number dateToNat(final Date d) {

    return d.year.longValue() * 100000000L
        + d.month.longValue() * 1000000L
        + d.day.longValue() * 10000L
        + d.hour.longValue() * 100L
        + d.min.longValue();
  }

  public String toString() {

    return "ModelUtils{"
        + "HOUR_MIN = "
        + Utils.toString(HOUR_MIN)
        + ", DAY_HOURS = "
        + Utils.toString(DAY_HOURS)
        + ", MONTH_DAYS = "
        + Utils.toString(MONTH_DAYS)
        + ", YEAR_MONTHS = "
        + Utils.toString(YEAR_MONTHS)
        + "}";
  }

  public static class Location implements Record {
    public String city;
    public String address;
    public String postalCode;

    public Location(final String _city, final String _address, final String _postalCode) {

      city = _city != null ? _city : null;
      address = _address != null ? _address : null;
      postalCode = _postalCode != null ? _postalCode : null;
    }

    public boolean equals(final Object obj) {

      if (!(obj instanceof Location)) {
        return false;
      }

      Location other = ((Location) obj);

      return (Utils.equals(city, other.city))
          && (Utils.equals(address, other.address))
          && (Utils.equals(postalCode, other.postalCode));
    }

    public int hashCode() {

      return Utils.hashCode(city, address, postalCode);
    }

    public Location copy() {

      return new Location(city, address, postalCode);
    }

    public String toString() {

      return "mk_ModelUtils`Location" + Utils.formatFields(city, address, postalCode);
    }
  }

  public static class Date implements Record {
    public Number year;
    public Number month;
    public Number day;
    public Number hour;
    public Number min;

    public Date(
        final Number _year,
        final Number _month,
        final Number _day,
        final Number _hour,
        final Number _min) {

      year = _year;
      month = _month;
      day = _day;
      hour = _hour;
      min = _min;
    }

    public boolean equals(final Object obj) {

      if (!(obj instanceof Date)) {
        return false;
      }

      Date other = ((Date) obj);

      return (Utils.equals(year, other.year))
          && (Utils.equals(month, other.month))
          && (Utils.equals(day, other.day))
          && (Utils.equals(hour, other.hour))
          && (Utils.equals(min, other.min));
    }

    public int hashCode() {

      return Utils.hashCode(year, month, day, hour, min);
    }

    public Date copy() {

      return new Date(year, month, day, hour, min);
    }

    public String toString() {

      return "mk_ModelUtils`Date" + Utils.formatFields(year, month, day, hour, min);
    }
  }

  public static Boolean inv_Date(final Date d) {

    Boolean andResult_49 = false;

    if (d.year.longValue() > 2017L) {
      Boolean andResult_50 = false;

      if (d.month.longValue() <= ((Number) ModelUtils.YEAR_MONTHS).doubleValue()) {
        Boolean andResult_51 = false;

        if (d.day.longValue() <= ((Number) ModelUtils.MONTH_DAYS).doubleValue()) {
          Boolean andResult_52 = false;

          if (d.hour.longValue() < ((Number) ModelUtils.DAY_HOURS).doubleValue()) {
            if (d.min.longValue() < ((Number) ModelUtils.HOUR_MIN).doubleValue()) {
              andResult_52 = true;
            }
          }

          if (andResult_52) {
            andResult_51 = true;
          }
        }

        if (andResult_51) {
          andResult_50 = true;
        }
      }

      if (andResult_50) {
        andResult_49 = true;
      }
    }

    return andResult_49;
  }

  public static class Date_DoctorId implements Record {
    public Date date;
    public Number doctorId;

    public Date_DoctorId(final Date _date, final Number _doctorId) {

      date = _date != null ? Utils.copy(_date) : null;
      doctorId = _doctorId;
    }

    public boolean equals(final Object obj) {

      if (!(obj instanceof Date_DoctorId)) {
        return false;
      }

      Date_DoctorId other = ((Date_DoctorId) obj);

      return (Utils.equals(date, other.date)) && (Utils.equals(doctorId, other.doctorId));
    }

    public int hashCode() {

      return Utils.hashCode(date, doctorId);
    }

    public Date_DoctorId copy() {

      return new Date_DoctorId(date, doctorId);
    }

    public String toString() {

      return "mk_ModelUtils`Date_DoctorId" + Utils.formatFields(date, doctorId);
    }
  }
}
