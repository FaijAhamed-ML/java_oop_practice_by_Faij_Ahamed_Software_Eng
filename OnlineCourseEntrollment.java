class Course {
    protected String courseID = "N/A";
    protected String courseName = "N/A";
    protected int durationInWeeks = 0;

    public Course(String courseID, String courseName, int durationInWeeks) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.durationInWeeks = durationInWeeks;
    }

    void displayCourseDetails() {
        System.out.println("Course ID: " + courseID);
        System.out.println("Course Name: " + courseName);
        System.out.println("Duration: " + durationInWeeks + " weeks");
    }

    public double calculateTotalFee(){
        return 10000.0;
    }

    public double calculateTotalFee(double discountPercentage){
        double basic = calculateTotalFee();
        double discount = (discountPercentage / 100) * basic;
        return basic - discount;
    }

}

class RegularCourse extends Course {
    protected boolean hasLiveSessions;
    public RegularCourse() {
        super( "N/A", "N/A", 0);
        hasLiveSessions = false;
    }
    public RegularCourse( String courseID, String courseName, int durationInWeeks, boolean hasLiveSessions) {
        super(courseID, courseName, durationInWeeks);
        this.hasLiveSessions = hasLiveSessions;
    }

    @Override
    public double calculateTotalFee(){
        return 100.0 * super.durationInWeeks;
    }

    @Override
    public double calculateTotalFee(double discountPercentage){
        double basic = calculateTotalFee();
        double discount = (discountPercentage / 100.0) * basic;
        return basic - discount;
    }

    @Override
    void displayCourseDetails() {
        super.displayCourseDetails();
        System.out.println("Live Sessions Included: " + hasLiveSessions);
    }
}

class PremiumCourse extends Course {
    private boolean includesCertification;

    public PremiumCourse() {
        super( "N/A", "N/A", 0);
        this.includesCertification = false;
    }

    public PremiumCourse(String courseID, String courseName, int durationInWeeks, boolean includesCertification) {
        super(courseID, courseName, durationInWeeks);
        this.includesCertification = includesCertification;
    }
    @Override
    public double calculateTotalFee(){
        double basic = (150.0 * super.durationInWeeks) + (includesCertification ? 50.0 : 0.0);
        return basic;
    }

    @Override
    public double calculateTotalFee(double discountPercentage){
        double basic = calculateTotalFee();
        double discount = (discountPercentage / 100.0) * basic;
        return basic - discount;
    }

    @Override
    void displayCourseDetails() {
        super.displayCourseDetails();
        System.out.println("Certification Included: " + includesCertification);
    }
}

public class OnlineCourseEntrollment {
    public static void main(String[] args) {
        RegularCourse regularCourse = new RegularCourse(); // Default constructor
        PremiumCourse premiumCourse = new PremiumCourse("PC202", "Machine Learning", 6, true); // Parameterized constructor

        System.out.println("=== Regular Course (Default Constructor) ===");
        regularCourse.displayCourseDetails();
        double regTotal = regularCourse.calculateTotalFee();
        double regDiscounted = regularCourse.calculateTotalFee(10);
        System.out.println("Total Fee: $" + regTotal);
        System.out.println("Basic Fee: $" + regTotal);
        System.out.println("Discounted Fee (10%): $" + regDiscounted);

        System.out.println("\n*** Premium Course (Parameterized Constructor) ***");
        premiumCourse.displayCourseDetails();
        double premTotal = premiumCourse.calculateTotalFee();
        double premDiscounted = premiumCourse.calculateTotalFee(20);
        System.out.println("Total Fee: $" + premTotal);
        System.out.println("Basic Fee: $" + premTotal);
        System.out.println("Discounted Fee (20%): $" + premDiscounted);
    }
}