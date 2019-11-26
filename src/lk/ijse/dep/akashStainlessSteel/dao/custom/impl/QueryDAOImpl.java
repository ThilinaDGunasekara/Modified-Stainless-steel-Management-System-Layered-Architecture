package lk.ijse.dep.akashStainlessSteel.dao.custom.impl;

import lk.ijse.dep.akashStainlessSteel.dao.CrudUtil;
import lk.ijse.dep.akashStainlessSteel.dao.custom.QueryDAO;
import lk.ijse.dep.akashStainlessSteel.entity.*;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public List<CustomJobEntity> loadToJob() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT j.jobId,c.customerId,c.name,j.description FROM fnrCustomer c INNER JOIN job j ON c.customerId = j.customerId");
        List<CustomJobEntity> jobList = new ArrayList<>();
        while (resultSet.next()){
            jobList.add(new CustomJobEntity(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }
        return jobList;
    }

    @Override
    public List<CustomEstimationEntity> loadJobsNotHaveEstimation() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT j.jobId,j.description FROM job j LEFT OUTER JOIN estimation e ON j.jobId = e.jobId WHERE e.jobId IS NULL");
        List<CustomEstimationEntity> customEstimationEntityList = new ArrayList<>();
        while (resultSet.next()){
            customEstimationEntityList.add(new CustomEstimationEntity(
                    resultSet.getString(1),
                    resultSet.getString(2)
            ));
        }
        return customEstimationEntityList;
    }

    @Override
    public List<CustomFnrOrderEntity> loadAllDataToFnrOrder() throws Exception {

        ResultSet resultSet = CrudUtil.execute("SELECT e.jobId,j.customerId,c.name,e.price FROM fnrCustomer c INNER JOIN job j ON c.customerId = j.customerId INNER JOIN estimation e on j.jobId = e.jobId" );
        List<CustomFnrOrderEntity> customFnrOrderEntities = new ArrayList<>();

        while(resultSet.next()){
            customFnrOrderEntities.add(new CustomFnrOrderEntity(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            ));
        }
        return customFnrOrderEntities;
    }

    @Override
    public List<SearchCustomer> searchCustomer(String searchTxt) throws Exception {

        ResultSet resultSet = CrudUtil.execute("SELECT * FROM frCustomer WHERE customerId LIKE ? OR name LIKE  ? OR address LIKE ? OR contactNo LIKE ?",searchTxt,searchTxt,searchTxt,searchTxt);
        List<SearchCustomer> searchCustomers = new ArrayList<>();
        while (resultSet.next()){
            searchCustomers.add(new SearchCustomer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }
        return searchCustomers;
    }

    @Override
    public List<Item> searchItem(String searchTxt) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM item i WHERE i.itemCode  LIKE ? OR i.description LIKE  ? OR i.unitPrice LIKE ? OR i.qtyOnHand LIKE ?",searchTxt,searchTxt,searchTxt,searchTxt);
        List<Item> item = new ArrayList<>();
        while (resultSet.next()){
            item.add(new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4)
            ));
        }
        return item;
    }

    @Override
    public List<CustomJobEntity> searchAllJobs(String searchText) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT j.jobId,j.customerId,c.name,j.description FROM job j INNER JOIN fnrCustomer c ON j.customerId = c.customerId WHERE j.jobId LIKE ? OR j.description LIKE ? OR j.customerId LIKE ? OR c.name LIKE ?",searchText,searchText,searchText,searchText);
        List<CustomJobEntity> customJobEntities = new ArrayList<>();
        while (resultSet.next()){
            customJobEntities.add(new CustomJobEntity(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }
        return customJobEntities;
    }

    @Override
    public List<SearchOrderEntity> searchOrder(String searchText) throws Exception {

        ResultSet resultSet = CrudUtil.execute("SELECT o.orderId,o.date,j.customerId,fC.name,o.price,(o.price-o.advance) AS remainningPayment " +
                "FROM fnrOrder o INNER JOIN job j ON o.jobId = j.jobId INNER JOIN fnrCustomer fC ON j.customerId = fC.customerId " +
                "HAVING remainningPayment LIKE ? OR o.orderId LIKE ? OR o.date LIKE ? OR j.customerId LIKE ? OR fC.name Like ? OR o.price LIKE ?",searchText,searchText,searchText,searchText,searchText,searchText);
        List<SearchOrderEntity> searchOrderEntities = new ArrayList<>();
        while (resultSet.next()){
            searchOrderEntities.add(new SearchOrderEntity(
                    resultSet.getString(1),
                    resultSet.getDate(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5),
                    resultSet.getDouble(6)
            ));
        }
        return searchOrderEntities;
    }

    @Override
    public List<SearchOrderEntity> searchOrder2(String searchText) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT o.orderId,o.date,fC.customerId,fC.name,SUM(oD.unitPrice*oD.quantity) AS total\n" +
                "FROM frOrder o INNER JOIN frCustomer fC ON o.frCustomerId = fC.customerId INNER JOIN orderDetail oD ON o.orderId = oD.orderId  GROUP BY o.orderId\n" +
                "HAVING o.orderId LIKE ? OR o.date LIKE ? OR fC.customerId LIKE ? OR fC.name LIKE ? OR total LIKE ?;",searchText,searchText,searchText,searchText,searchText);
        List<SearchOrderEntity> searchOrderEntities = new ArrayList<>();
        while (resultSet.next()){
            searchOrderEntities.add(new SearchOrderEntity(
                    resultSet.getString(1),
                    resultSet.getDate(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5)
            ));
        }
        return searchOrderEntities;
    }

    @Override
    public List<SearchOrderEntity> findAllOrdersInFNRC() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT o.orderId,o.date,j.customerId,fC.name,o.price,(o.price-o.advance) AS remainningPayment " +
                "FROM fnrOrder o INNER JOIN job j ON o.jobId = j.jobId INNER JOIN fnrCustomer fC ON j.customerId = fC.customerId ");
        List<SearchOrderEntity> searchOrderEntities = new ArrayList<>();
        while (resultSet.next()){
            searchOrderEntities.add(new SearchOrderEntity(
                    resultSet.getString(1),
                    resultSet.getDate(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5),
                    resultSet.getDouble(6)
            ));
        }
        return searchOrderEntities;
    }

    @Override
    public List<SearchOrderEntity> findAllOrdersInFRC() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT o.orderId,o.date,fC.customerId,fC.name,SUM(oD.unitPrice*oD.quantity) AS total\n" +
                "FROM frOrder o INNER JOIN frCustomer fC ON o.frCustomerId = fC.customerId INNER JOIN orderDetail oD ON o.orderId = oD.orderId  GROUP BY o.orderId");
        List<SearchOrderEntity> searchOrderEntities = new ArrayList<>();
        while (resultSet.next()){
            searchOrderEntities.add(new SearchOrderEntity(
                    resultSet.getString(1),
                    resultSet.getDate(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5)
            ));
        }
        return searchOrderEntities;
    }
}
