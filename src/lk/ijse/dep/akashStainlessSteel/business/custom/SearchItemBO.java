package lk.ijse.dep.akashStainlessSteel.business.custom;

import lk.ijse.dep.akashStainlessSteel.business.SuperBO;
import lk.ijse.dep.akashStainlessSteel.dto.ItemDTO;

import java.util.List;

public interface SearchItemBO extends SuperBO {
    public List<ItemDTO> searchItem(String searchTxt) throws Exception;
}
