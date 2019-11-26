package lk.ijse.dep.akashStainlessSteel.business.custom.impl;
import lk.ijse.dep.akashStainlessSteel.business.custom.CustomJobBO;
import lk.ijse.dep.akashStainlessSteel.dao.DAOFactory;
import lk.ijse.dep.akashStainlessSteel.dao.DAOTypes;
import lk.ijse.dep.akashStainlessSteel.dao.custom.QueryDAO;
import lk.ijse.dep.akashStainlessSteel.dto.CustomJobDTO;
import lk.ijse.dep.akashStainlessSteel.entity.CustomJobEntity;
import java.util.ArrayList;
import java.util.List;

public class CustomJobBOImpl implements CustomJobBO {

    QueryDAO queryDAO = DAOFactory.getInstance().getDAO(DAOTypes.QUERY);
    @Override
    public List<CustomJobDTO> loadToJob() throws Exception {

        List<CustomJobDTO> list = new ArrayList<>();
        List<CustomJobEntity> customJobEntities = queryDAO.loadToJob();

        for (CustomJobEntity customJobEntity : customJobEntities) {
            list.add(new CustomJobDTO(
                    customJobEntity.getJobId(),
                    customJobEntity.getCustomerId(),
                    customJobEntity.getName(),
                    customJobEntity.getDescription()
            ));
        }
        return list;
    }

    @Override
    public List<CustomJobDTO> searchAllJobs(String searchText) throws Exception {
        List<CustomJobEntity> customJobEntities = queryDAO.searchAllJobs(searchText);
        List<CustomJobDTO>  customJobDTOS = new ArrayList<>();

        for (CustomJobEntity customJobEntity : customJobEntities) {
            customJobDTOS.add(new CustomJobDTO(
                    customJobEntity.getJobId(),
                    customJobEntity.getCustomerId(),
                    customJobEntity.getName(),
                    customJobEntity.getDescription()
            ));
        }

        return customJobDTOS;
    }
}
