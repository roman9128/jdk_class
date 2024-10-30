import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeesList {

    private List<Employee> team;

    public EmployeesList() {
        team = new ArrayList<>();
    }

    public void addToList(Employee employee) {
        team.add(employee);
    }

    public void removeFromList(Employee employee) {
        team.remove(employee);
    }

    public List<Employee> findByExactExperience(int experience) {
        return team.stream().filter(employee -> employee.getExperience() == experience).collect(Collectors.toList());
    }

    public List<Employee> findByApproximateExperience(int from, int to) {
        int smallerNumber;
        int largerNumber;
        if (from > to) {
            largerNumber = from;
            smallerNumber = to;
        } else {
            smallerNumber = from;
            largerNumber = to;
        }
        return team.stream().filter(
                employee -> employee.getExperience() >= smallerNumber && employee.getExperience() <= largerNumber)
                .collect(Collectors.toList());
    }

    public String findNumberByName(String name) {
        StringBuilder builder = new StringBuilder();
        List<Long> result = team.stream().filter(employee -> employee.getName().equals(name))
                .collect(Collectors.toList()).stream().map(Employee::getPhoneNumber).toList();

        if (isInList(name)) {
            builder.append(name);
            builder.append("'s number(s):");
            builder.append(System.lineSeparator());
            for (Long num : result) {
                builder.append(num);
                builder.append(System.lineSeparator());
            }
            return builder.toString();
        } else
            return "No information about " + name;

    }

    public List<Long> findNumberByNameAndGetOnlyDigits(String name) {
        return team.stream().filter(employee -> employee.getName().equals(name)).collect(Collectors.toList()).stream()
                .map(Employee::getPhoneNumber).toList();
    }

    public Employee findByWorkerNumber(int number) {
        try {
            return team.stream().filter(employee -> employee.getWorkerNumber() == number).collect(Collectors.toList()).getFirst();
        } catch (Exception e) {
            return null;
        }
    }

    public void printCommonList() {
        for (Employee employee : team) {
            System.out.println(employee);
        }
    }

    public void printList(List<Employee> list) {
        System.out.println("Result:");
        for (Employee employee : list) {
            System.out.println(employee);
        }
    }

    private boolean isInList(String name) {
        return !team.stream().filter(employee -> employee.getName().equals(name)).collect(Collectors.toList()).isEmpty();
    }
}

// что-то я увлёкся стримами...