package lk.ijse.dep.akashStainlessSteel.dto;

public class WorkerDTO {

    private String workerId;
    private String name;
    private String address;
    private int contactNo;
    private double salary;

    public WorkerDTO(String workerId, String name, String address, int contactNo, double salary) {
        this.workerId = workerId;
        this.name = name;
        this.address = address;
        this.contactNo = contactNo;
        this.salary = salary;
    }

    public WorkerDTO() {
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

    public int getContactNo() {
        return contactNo;
    }

    public void setContactNo(int contactNo) {
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
        return "WorkerDTO{" +
                "workerId='" + workerId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contactNo=" + contactNo +
                ", salary=" + salary +
                '}';
    }
}
