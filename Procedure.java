/**
 * Class: CMSC203 CRN 21410
 * Program: Assignment 2 - Patient Application
 * Instructor: Huseyin Aygun
 * Student: Jiawei Lyu
 * Due Date: Not specified in the assignment description
 * Platform/Compiler: Windows 10 / Java javac
 *
 * Class Description: Stores and manages medical procedure information.
 *
 * Integrity Pledge: I pledge that I have completed the programming assignment independently.
 * I have not copied the code from a student or any source.
 */
public class Procedure {
    private String procedureName;
    private String procedureDate;
    private String practitionerName;
    private double charges;

    /** Creates an empty Procedure object. */
    public Procedure() {
        this("", "", "", 0.0);
    }

    /** Creates a Procedure object with a procedure name and date. */
    public Procedure(String procedureName, String procedureDate) {
        this(procedureName, procedureDate, "", 0.0);
    }

    /** Creates a Procedure object with all procedure attributes. */
    public Procedure(String procedureName, String procedureDate,
                     String practitionerName, double charges) {
        this.procedureName = procedureName;
        this.procedureDate = procedureDate;
        this.practitionerName = practitionerName;
        this.charges = charges;
    }

    /** Returns the procedure name. */
    public String getProcedureName() { return procedureName; }

    /** Updates the procedure name. */
    public void setProcedureName(String procedureName) { this.procedureName = procedureName; }

    /** Returns the procedure date. */
    public String getProcedureDate() { return procedureDate; }

    /** Updates the procedure date. */
    public void setProcedureDate(String procedureDate) { this.procedureDate = procedureDate; }

    /** Returns the practitioner name. */
    public String getPractitionerName() { return practitionerName; }

    /** Updates the practitioner name. */
    public void setPractitionerName(String practitionerName) { this.practitionerName = practitionerName; }

    /** Returns the procedure charge. */
    public double getCharges() { return charges; }

    /** Updates the procedure charge. */
    public void setCharges(double charges) { this.charges = charges; }

    /** Determines whether the procedure costs at least $1,000.00. */
    public boolean isExpensiveProcedure() {
        return charges >= 1000.00;
    }

    /** Applies a percentage discount when the percent is between 0 and 100 inclusive. */
    public void applyDiscount(double percent) {
        if (percent >= 0.0 && percent <= 100.0) {
            charges -= charges * (percent / 100.0);
        }
    }

    /** Returns Low, Medium, or High based on the procedure charge. */
    public String getChargeCategory() {
        if (charges < 500.00) {
            return "Low";
        } else if (charges < 1000.00) {
            return "Medium";
        }
        return "High";
    }

    /** Tests whether this procedure was performed by the supplied practitioner. */
    public boolean isPerformedBy(String practitionerName) {
        return this.practitionerName != null
                && this.practitionerName.equalsIgnoreCase(practitionerName);
    }

    /** Returns the charge with a dollar sign, commas, and two decimal places. */
    public String getFormattedCharge() {
        return String.format("$%,.2f", charges);
    }

    /** Returns all procedure information. */
    @Override
    public String toString() {
        return "Procedure: " + procedureName + System.lineSeparator()
                + "Procedure Date: " + procedureDate + System.lineSeparator()
                + "Practitioner: " + practitionerName + System.lineSeparator()
                + "Charge: " + getFormattedCharge();
    }
}
