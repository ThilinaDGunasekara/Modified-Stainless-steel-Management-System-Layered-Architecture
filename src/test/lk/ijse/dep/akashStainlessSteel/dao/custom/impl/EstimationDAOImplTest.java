package test.lk.ijse.dep.akashStainlessSteel.dao.custom.impl;


import lk.ijse.dep.akashStainlessSteel.dao.custom.impl.EstimationDAOImpl;
import lk.ijse.dep.akashStainlessSteel.entity.Estimation;

import java.util.List;

class EstimationDAOImplTest {

    public static void main(String[] args) {
        EstimationDAOImplTest estimationDAOImplTest = new EstimationDAOImplTest();
        estimationDAOImplTest.find();
        //estimationDAOImplTest.findALL();

    }

    void find() {
        EstimationDAOImpl estimationDAO= new EstimationDAOImpl();
        try {
            Estimation e001 = estimationDAO.find("E001");
            System.out.println(e001);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void findALL(){
        EstimationDAOImpl estimationDAO= new EstimationDAOImpl();
        try {
            List<Estimation> all = estimationDAO.findAll();
            System.out.println(all);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}