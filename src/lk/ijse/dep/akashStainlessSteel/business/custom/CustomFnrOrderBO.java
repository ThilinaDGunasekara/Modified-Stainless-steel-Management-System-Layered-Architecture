package lk.ijse.dep.akashStainlessSteel.business.custom;

import lk.ijse.dep.akashStainlessSteel.business.SuperBO;
import lk.ijse.dep.akashStainlessSteel.dto.CustomFnrOrderDTO;

import java.util.List;

public interface CustomFnrOrderBO extends SuperBO {
    List<CustomFnrOrderDTO> loadAllDataToFnrOrder() throws Exception;
}
