public class Employee {

    private static int workerCount = 1;

    private String name;
    private int workerNumber;
    private long phoneNumber;
    private int experience;

    public Employee(String name, long phoneNumber, int experience) {
        this.name = name;
        this.workerNumber = workerCount++;
        this.phoneNumber = phoneNumber;
        this.experience = experience;
    }

    public String getName() {
        return name;
    }

    public int getWorkerNumber() {
        return workerNumber;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public int getExperience() {
        return experience;
    }

    @Override
    public String toString() {
        return "Employee: " + name + ", # " + workerNumber + ", phone: " + phoneNumber
                + ", experience: " + experience + " months";
    }

}