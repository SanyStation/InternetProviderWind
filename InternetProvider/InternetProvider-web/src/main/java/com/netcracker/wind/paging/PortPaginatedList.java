package com.netcracker.wind.paging;

import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IPortDAO;
import java.util.List;

/**
 * The {@code PortpaginatedList} class designed to creating paginated list of
 * ports for certain device.
 * 
 * @author Alexander Kovriga
 */
public class PortPaginatedList extends AbstractPaginatedList {
    
    private final IPortDAO portDAO
            = FactoryCreator.getInstance().getFactory().createPortDAO();
    private final int deviceId;

    public PortPaginatedList(int pageSize, int deviceId) {
        super(pageSize);
        this.deviceId = deviceId;
    }

    @Override
    public List getList() {
        return portDAO.findByDevice(deviceId, pageNumber, pageSize,
                sortCriterion, direction);
    }

    @Override
    public int getFullListSize() {
        return portDAO.countRows();
    }
    
}
