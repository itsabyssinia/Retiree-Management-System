package cs143.domain;

import java.io.Serializable;

/**
 * A class for holding a single Retiree
 *
 */
public class Retiree implements Comparable<Retiree>, Serializable {

    private long ssn;
    private String fullName;
    private double monthlyBenefit;

    /**
     * A full constructor that adds a Retiree
     *
     * @param ssn
     * @param fullName
     * @param monthlyBenefit
     */
    public Retiree(long ssn, String fullName, double monthlyBenefit) {
        this.ssn = ssn;
        this.fullName = fullName;
        this.monthlyBenefit = monthlyBenefit;
    }

    /**
     * A method to get the SSN of retiree
     *
     * @return ssn - the retiree's ssn number
     */
    public long getSsn() {
        return ssn;
    }

    /**
     * A method to get the full name of a retiree
     *
     * @return fullName - the retiree's full name
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * A method to get the Retiree's monthly benefit
     *
     * @return monthlyBenefit - the retiree's monthly sum of benefits
     */
    public double getMonthlyBenefit() {
        return monthlyBenefit;
    }

    /**
     * A method to output the Retiree, their SSN number and benefits
     *
     * @return string representing a retiree's information
     */
    @Override
    public String toString() {
        return ssn + " : " + fullName + "\n"
                + "monthlyBenefit = $" + monthlyBenefit;
    }

    /**
     * A method to calculate a unique hash code for a retiree object
     *
     * @return hash - the hash code value
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (int) (this.ssn ^ (this.ssn >>> 32));
        return hash;
    }

    /**
     * This method is used to compare objects of this class
     *
     * @param obj the object to compare to the current one
     * @return false - if the objects differ, true - if the objects are the same
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Retiree other = (Retiree) obj;
        return this.ssn == other.ssn;
    }

    /**
     * A method to compare Retiree objects by ssn for sorting
     *
     * @param o - the Retiree object to compare with
     * @return -1 if the current ssn is less than the sent ssn 1 if the current
     * ssn is greater than the sent ssn 0 if the ssn numbers are equal
     */
    @Override
    public int compareTo(Retiree o) {
        if (this.ssn < o.ssn) {
            return -1;
        } else if (this.ssn > o.ssn) {
            return 1;
        } else {
            return 0;
        }
    }

}
