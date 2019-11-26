package lk.ijse.dep.akashStainlessSteel.entity;

public class SearchCustomer {
    private String customerId;
    private String name;
    private String address;
    private String contactNo;

    public SearchCustomer(String customerId, String name, String address, String contactNo) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.contactNo = contactNo;
    }

    public SearchCustomer() {
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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

    @Override
    public String toString() {
        return "SearchCustomer{" +
                "customerId='" + customerId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contactNo=" + contactNo +
                '}';
    }
}
