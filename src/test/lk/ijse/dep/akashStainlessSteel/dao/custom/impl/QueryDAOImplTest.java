package test.lk.ijse.dep.akashStainlessSteel.dao.custom.impl;


import lk.ijse.dep.akashStainlessSteel.dao.custom.impl.QueryDAOImpl;
import lk.ijse.dep.akashStainlessSteel.entity.CustomFnrOrderEntity;

import java.util.List;

class QueryDAOImplTest {
    public static void main(String[]args){
        QueryDAOImplTest queryDAOImplTest = new QueryDAOImplTest();
        queryDAOImplTest.loadAllDataToFnrOrder();
    }

    void loadAllDataToFnrOrder() {
        QueryDAOImpl queryDAO = new QueryDAOImpl();
        try {
            List<CustomFnrOrderEntity> customFnrOrderEntities = queryDAO.loadAllDataToFnrOrder();
            System.out.println(customFnrOrderEntities+"\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}