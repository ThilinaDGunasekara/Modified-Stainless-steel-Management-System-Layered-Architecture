package lk.ijse.dep.akashStainlessSteel.business.custom;

import lk.ijse.dep.akashStainlessSteel.business.SuperBO;
import lk.ijse.dep.akashStainlessSteel.dto.SearchOrderDTO;

import java.util.List;

public interface SearchOrdersBO extends SuperBO {
    List<SearchOrderDTO> searchOrder(String searchText) throws Exception;
    List<SearchOrderDTO> searchOrder2(String searchText) throws Exception;
    List<SearchOrderDTO> findAllOrdersInFRC() throws Exception;
    List<SearchOrderDTO> findAllOrdersInFNRC() throws Exception;
}
