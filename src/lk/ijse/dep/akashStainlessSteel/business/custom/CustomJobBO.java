package lk.ijse.dep.akashStainlessSteel.business.custom;

import lk.ijse.dep.akashStainlessSteel.business.SuperBO;
import lk.ijse.dep.akashStainlessSteel.dto.CustomJobDTO;

import java.util.List;

public interface CustomJobBO extends SuperBO {
    List<CustomJobDTO> loadToJob() throws Exception;
    List<CustomJobDTO> searchAllJobs(String searchText) throws Exception;
}
