package com.netcracker.wind.paging;

import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.interfaces.IPortDAO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Alexander Kovriga
 */
public class PortPaginatedList extends AbstractPaginatedList {
    
    private final IPortDAO portDAO
            = FactoryCreator.getInstance().getFactory().createPortDAO();
    private final int deviceId;

    public PortPaginatedList(HttpServletRequest request, int pageSize,
            int deviceId) {
        super(request, pageSize);
        this.deviceId = deviceId;
    }

    @Override
    public List getList() {
        return portDAO.findByDevice(deviceId, pageNumber, pageSize);
    }

    @Override
    public int getFullListSize() {
        return portDAO.countRows();
    }
    
}
