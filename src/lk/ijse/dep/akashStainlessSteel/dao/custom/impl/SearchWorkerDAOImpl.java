package lk.ijse.dep.akashStainlessSteel.dao.custom.impl;

import lk.ijse.dep.akashStainlessSteel.dao.CrudUtil;
import lk.ijse.dep.akashStainlessSteel.dao.custom.SearchWorkerDAO;
import lk.ijse.dep.akashStainlessSteel.entity.Worker;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SearchWorkerDAOImpl implements SearchWorkerDAO {

    @Override
    public List<Worker> searchWorker(String searchText) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM worker WHERE workerId LIKE ? OR name LIKE ? OR address LIKE ? OR contactNo LIKE ? OR salary LIKE ? ",searchText,searchText,searchText,searchText,searchText);
        List<Worker> workers = new ArrayList<>();
        while (resultSet.next()){
            workers.add(new Worker(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getDouble(5)
            ));
        }
        return workers;
    }
}
