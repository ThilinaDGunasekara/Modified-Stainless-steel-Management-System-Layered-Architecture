package test.dao;

import lk.ijse.dep.akashStainlessSteel.dao.custom.impl.WorkerDAOImpl;

class WorkerDAOImplTest {
    public static void main(String[] args) {
        WorkerDAOImplTest workerDAOImplTest = new WorkerDAOImplTest();
        workerDAOImplTest.getLastWorkerId();

    }
    void getLastWorkerId() {
        WorkerDAOImpl workerDAO = new WorkerDAOImpl();
        try {
            String lastWorkerId = workerDAO.getLastWorkerId();
            System.out.println(lastWorkerId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}