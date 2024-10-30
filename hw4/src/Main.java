public class Main {
    public static void main(String[] args) {

        EmployeesList employeesList = new EmployeesList();
        employeesList.addToList(new Employee("Mike", 123456, 5));
        employeesList.addToList(new Employee("Don", 235689, 6));
        employeesList.addToList(new Employee("Leo", 785321, 5));
        employeesList.addToList(new Employee("Raph", 956320, 4));

        Employee employee = new Employee("Splinter", 6846846, 20);

        employeesList.addToList(employee);
        employeesList.printCommonList();
        employeesList.printList(employeesList.findByExactExperience(5));
        employeesList.printList(employeesList.findByApproximateExperience(6, 10));
        System.out.println(employeesList.findNumberByName("Leo"));
        System.out.println(employeesList.findNumberByNameAndGetOnlyDigits("Don"));
        System.out.println(employeesList.findByWorkerNumber(1));

    }
}
