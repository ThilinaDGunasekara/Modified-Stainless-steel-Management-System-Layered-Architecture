package lk.ijse.dep.akashStainlessSteel.dto;

public class SearchCustomerDTO {
    private String customerId;
    private String name;
    private String address;
    private String contactNo;

    public SearchCustomerDTO(String customerId, String name, String address, String contactNo) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.contactNo = contactNo;
    }

    public SearchCustomerDTO() {
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
        return "SearchCustomerDTO{" +
                "customerId='" + customerId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contactNo=" + contactNo +
                '}';
    }
}
