package lk.ijse.dep.akashStainlessSteel.dao.custom;

import lk.ijse.dep.akashStainlessSteel.dao.SuperDAO;
import lk.ijse.dep.akashStainlessSteel.entity.*;

import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<CustomJobEntity> loadToJob() throws Exception;

    List<CustomEstimationEntity> loadJobsNotHaveEstimation() throws Exception;
    List<CustomFnrOrderEntity> loadAllDataToFnrOrder() throws Exception;
    List<SearchCustomer> searchCustomer(String searchTxt) throws Exception;
    List<Item> searchItem(String searchTxt) throws Exception;
    List<CustomJobEntity> searchAllJobs(String searchText) throws Exception;
    List<SearchOrderEntity> searchOrder(String searchText) throws Exception;
    List<SearchOrderEntity> searchOrder2(String searchText) throws Exception;
    List<SearchOrderEntity> findAllOrdersInFNRC() throws Exception;
    List<SearchOrderEntity> findAllOrdersInFRC() throws Exception;


}
