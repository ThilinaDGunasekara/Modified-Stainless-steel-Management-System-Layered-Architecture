package test.lk.ijse.dep.akashStainlessSteel.dao;


import lk.ijse.dep.akashStainlessSteel.dao.custom.impl.FrCustomerDAOImpl;
import lk.ijse.dep.akashStainlessSteel.entity.FrCustomer;

import java.util.List;

class FrCustomerDAOTest {
    public static void main(String[] args) throws Exception {
        FrCustomerDAOTest test = new FrCustomerDAOTest();

        //test.saveFrCustomer(new FrCustomer("FR003","Kumara","Maththegoda","0384256852"));
        //test.findAllFrCustomers();
        test.findFrCustomer("FR001");
        //test.updateFrCustomer(new FrCustomer("FR002","Kamal","Piliyandala","0788553400"));
        test.deleteFrCustomer("FRC001");
        //test.getLastCustomerId();

    }

    void findAllFrCustomers() throws Exception {
        FrCustomerDAOImpl dao = new FrCustomerDAOImpl();
        List<FrCustomer> allFrCustomers = dao.findAll();

        for (FrCustomer customer : allFrCustomers) {
            System.out.println(customer);
        }
    }

    void findFrCustomer(String customerId) throws Exception {

        FrCustomerDAOImpl dao = new FrCustomerDAOImpl();
        FrCustomer frCustomer = dao.find("FR001");
        System.out.println(frCustomer);
    }

    void saveFrCustomer(FrCustomer customer) throws Exception {
        FrCustomerDAOImpl dao = new FrCustomerDAOImpl();
        boolean b = dao.save(customer);
        System.out.println(b);
    }

    void updateFrCustomer(FrCustomer customer) throws Exception {
        FrCustomerDAOImpl dao = new FrCustomerDAOImpl();
        boolean b = dao.update(customer);
        System.out.println(b);
    }

    void deleteFrCustomer(String customerId) throws Exception {
        FrCustomerDAOImpl dao = new FrCustomerDAOImpl();
        boolean b = dao.delete(customerId);
        System.out.println(b);
    }

    void getLastCustomerId() throws Exception {
        FrCustomerDAOImpl dao = new FrCustomerDAOImpl();
        String lastCustomerId = dao.getLastCustomerId();
        System.out.println(lastCustomerId);

    }
}