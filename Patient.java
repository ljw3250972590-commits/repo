/**
 * Class: CMSC203 CRN 21410
 * Program: Assignment 2 - Patient Application
 * Instructor: Huseyin Aygun
 * Student: Jiawei Lyu
 * Due Date: Not specified in the assignment description
 * Platform/Compiler: Windows 10 / Java javac
 *
 * Class Description: Stores and manages patient contact information.
 *
 * Integrity Pledge: I pledge that I have completed the programming assignment independently.
 * I have not copied the code from a student or any source.
 */
public class Patient {
    private String firstName;
    private String middleName;
    private String lastName;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private String phoneNumber;
    private String emergencyContactName;
    private String emergencyContactPhone;

    /** Creates an empty Patient object. */
    public Patient() {
        this("", "", "", "", "", "", "", "", "", "");
    }

    /** Creates a Patient object using only the patient's name. */
    public Patient(String firstName, String middleName, String lastName) {
        this(firstName, middleName, lastName, "", "", "", "", "", "", "");
    }

    /** Creates a Patient object using all patient attributes. */
    public Patient(String firstName, String middleName, String lastName,
                   String streetAddress, String city, String state, String zipCode,
                   String phoneNumber, String emergencyContactName,
                   String emergencyContactPhone) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.emergencyContactName = emergencyContactName;
        this.emergencyContactPhone = emergencyContactPhone;
    }

    /** Returns the patient's first name. */
    public String getFirstName() { return firstName; }

    /** Updates the patient's first name. */
    public void setFirstName(String firstName) { this.firstName = firstName; }

    /** Returns the patient's middle name. */
    public String getMiddleName() { return middleName; }

    /** Updates the patient's middle name. */
    public void setMiddleName(String middleName) { this.middleName = middleName; }

    /** Returns the patient's last name. */
    public String getLastName() { return lastName; }

    /** Updates the patient's last name. */
    public void setLastName(String lastName) { this.lastName = lastName; }

    /** Returns the street address. */
    public String getStreetAddress() { return streetAddress; }

    /** Updates the street address. */
    public void setStreetAddress(String streetAddress) { this.streetAddress = streetAddress; }

    /** Returns the city. */
    public String getCity() { return city; }

    /** Updates the city. */
    public void setCity(String city) { this.city = city; }

    /** Returns the state. */
    public String getState() { return state; }

    /** Updates the state. */
    public void setState(String state) { this.state = state; }

    /** Returns the ZIP code. */
    public String getZipCode() { return zipCode; }

    /** Updates the ZIP code. */
    public void setZipCode(String zipCode) { this.zipCode = zipCode; }

    /** Returns the patient's phone number. */
    public String getPhoneNumber() { return phoneNumber; }

    /** Updates the patient's phone number. */
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    /** Returns the emergency contact name. */
    public String getEmergencyContactName() { return emergencyContactName; }

    /** Updates the emergency contact name. */
    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    /** Returns the emergency contact phone number. */
    public String getEmergencyContactPhone() { return emergencyContactPhone; }

    /** Updates the emergency contact phone number. */
    public void setEmergencyContactPhone(String emergencyContactPhone) {
        this.emergencyContactPhone = emergencyContactPhone;
    }

    /** Builds the patient's full name in First Middle Last format. */
    public String buildFullName() {
        return (firstName + " " + middleName + " " + lastName).trim().replaceAll("\\s+", " ");
    }

    /** Builds the patient's address in Street City State ZIP format. */
    public String buildAddress() {
        return (streetAddress + " " + city + " " + state + " " + zipCode).trim().replaceAll("\\s+", " ");
    }

    /** Builds the emergency contact in Name Phone format. */
    public String buildEmergencyContact() {
        return (emergencyContactName + " " + emergencyContactPhone).trim().replaceAll("\\s+", " ");
    }

    /** Checks the patient's phone number for ###-###-#### format. */
    public boolean isValidPhoneNumber() {
        return phoneNumber != null && phoneNumber.matches("\\d{3}-\\d{3}-\\d{4}");
    }

    /** Checks the emergency phone number for ###-###-#### format. */
    public boolean isValidEmergencyPhoneNumber() {
        return emergencyContactPhone != null && emergencyContactPhone.matches("\\d{3}-\\d{3}-\\d{4}");
    }

    /** Returns the patient's name in Last, First Middle format. */
    public String getLastFirstMiddle() {
        String firstMiddle = (firstName + " " + middleName).trim().replaceAll("\\s+", " ");
        return lastName + ", " + firstMiddle;
    }

    /** Tests whether the patient has the supplied city and state. */
    public boolean hasSameCityState(String city, String state) {
        return this.city != null && this.state != null
                && this.city.equalsIgnoreCase(city)
                && this.state.equalsIgnoreCase(state);
    }

    /** Updates all address fields at one time. */
    public void updateAddress(String street, String city, String state, String zip) {
        this.streetAddress = street;
        this.city = city;
        this.state = state;
        this.zipCode = zip;
    }

    /** Returns a short summary of patient and emergency contact information. */
    public String getContactSummary() {
        return "Patient: " + buildFullName() + ", Phone: " + phoneNumber
                + ", Emergency Contact: " + buildEmergencyContact();
    }

    /** Returns all patient information using the required build methods. */
    @Override
    public String toString() {
        return "Name: " + buildFullName() + System.lineSeparator()
                + "Address: " + buildAddress() + System.lineSeparator()
                + "Phone Number: " + phoneNumber + System.lineSeparator()
                + "Emergency Contact: " + buildEmergencyContact();
    }
}
