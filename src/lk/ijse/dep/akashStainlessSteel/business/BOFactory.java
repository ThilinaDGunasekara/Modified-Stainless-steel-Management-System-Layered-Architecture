package lk.ijse.dep.akashStainlessSteel.business;

import lk.ijse.dep.akashStainlessSteel.business.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getInstance() {
        return (boFactory == null) ? (boFactory = new BOFactory()) : boFactory;
    }

    public <T extends SuperBO> T getBO(BOTypes boType) {
        switch (boType) {
            case ESTIMATION:
                return (T) new EstimationBOImpl();
            case FNR_CUSTOMER:
                return (T) new FnrCustomerBOImpl();
            case FNR_ORDER:
                return (T) new FnrOrderBOImpl();
            case FR_CUSTOMER:
                return (T) new FrCustomerBOImpl();
            case FR_ORDER:
                return (T) new FrOrderBOImpl();
            case ITEM:
                return (T) new ItemBOImpl();
            case JOB:
                return (T) new JobBOImpl();
            case WORKER:
                return (T) new WorkerBOImpl();
            case QUERY_JOB:
                return (T) new CustomJobBOImpl();
            case QUERY_JOB_WHO_NOT_HAVE_ESTIMATION:
                return (T) new CustomJobWhoNotHaveEstimationImpl();
            case ESTIMATION_DETAIL:
                return (T) new EstimationDetailBOImpl();
            case ORDER_DETAIL:
                return (T) new FrOrderDetailBOImpl();
            case QUERY_FNR_ORDER:
                return (T) new CustomFnrOrderBOImpl();
            case REGISTER:
                return (T) new RegisterBOImpl();
            case SEARCH_CUSTOMER:
                return (T) new SearchCustomerBOImpl();
            case SEARCH_ITEM:
                return (T) new SearchItemBOImpl();
            case SEARCH_WORKERS:
                return (T) new SearchWorkerBOImpl();
            case SEARCH_ORDER:
                return (T) new SearchOrdersBOImpl();
            default:
                return null;
        }
    }
}
