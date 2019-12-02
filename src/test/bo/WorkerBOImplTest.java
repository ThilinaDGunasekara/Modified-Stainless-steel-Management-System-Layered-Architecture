package test.bo;

import lk.ijse.dep.akashStainlessSteel.business.custom.impl.WorkerBOImpl;

class WorkerBOImplTest {

    public static void main(String[] args) {
        WorkerBOImplTest workerBOImplTest = new WorkerBOImplTest();
        workerBOImplTest.getLastWorkerId();
    }

    void getLastWorkerId() {
        WorkerBOImpl workerBO = new WorkerBOImpl();
        try {
            String lastWorkerId = workerBO.getLastWorkerId();
            System.out.println(lastWorkerId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}