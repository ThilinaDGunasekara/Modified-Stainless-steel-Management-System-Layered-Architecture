package lk.ijse.dep.akashStainlessSteel.tm;

public class SearchWorkerTM {
    private String workerId;
    private String name;
    private String address;
    private String contactNo;
    private double salary;

    public SearchWorkerTM(String workerId, String name, String address, String contactNo, double salary) {
        this.workerId = workerId;
        this.name = name;
        this.address = address;
        this.contactNo = contactNo;
        this.salary = salary;
    }

    public SearchWorkerTM() {
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "workerId='" + workerId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contactNo=" + contactNo +
                ", salary=" + salary +
                '}';
    }

}
