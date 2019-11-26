package lk.ijse.dep.akashStainlessSteel.dao;

import lk.ijse.dep.akashStainlessSteel.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getInstance() {
        return (daoFactory == null) ? (daoFactory = new DAOFactory()) : daoFactory;
    }

    public <T extends SuperDAO> T getDAO(DAOTypes daoType) {
        switch (daoType) {
            case ESTIMATION:
                return (T) new EstimationDAOImpl();
            case ESTIMATION_DETAIL:
                return (T) new EstimationDetailDAOImpl();
            case FNR_CUSTOMER:
                return (T) new FnrCustomerDAOImpl();
            case FNR_ORDER:
                return (T) new FnrOrderDAOImpl();
            case FR_CUSTOMER:
                return (T) new FrCustomerDAOImpl();
            case FR_ORDER:
                return (T) new FrOrderDAOImpl();
            case ITEM:
                return (T) new ItemDAOImpl();
            case JOB:
                return (T) new JobDAOImpl();
            case JOB_DETAIL:
                return (T) new JobDetailDAOImpl();
            case ORDER_DETAIL:
                return (T) new OrderDetailDAOImpl();
            case WORKER:
                return (T) new WorkerDAOImpl();
            case QUERY:
                return (T) new QueryDAOImpl();
            case REGISTER:
                return (T) new RegisterDAOImpl();
            case SEARCH_ITEM:
                return (T) new SearchItemDAOImpl();
            case SEARCH_WORKERS:
                return (T) new SearchWorkerDAOImpl();
            case SEARCH_CUSTOMER:
                return (T) new SearchCustomerDAOImpl();
            default:
                return null;
        }
    }
}
