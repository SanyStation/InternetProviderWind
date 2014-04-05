package com.netcracker.wind.reports.test;

import com.netcracker.wind.reports.factories.ReportFactory;
import com.netcracker.wind.reports.interfaces.RiReport;
import com.netcracker.wind.reports.interfaces.SiReport;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Alexander Kovriga
 */
public class Test {

    public static void main(String[] args) {
        List<Router> routers = new ArrayList();
        routers.add(new Router("Cisco 7606", 60, 10));
        routers.add(new Router("Cisco 7606", 60, 45));
        routers.add(new Router("Cisco 7606", 60, 60));
        routers.add(new Router("Cisco 7606", 60, 0));
        routers.add(new Router("Cisco 7606", 60, 15));
        routers.add(new Router("Cisco 7606", 60, 23));
        
        List<Order> orders = new ArrayList();
        orders.add(new Order(100, "Silver internet", "01.01.2014", 25));
        orders.add(new Order(101, "Golden internet", "01.01.2014", 40));
        orders.add(new Order(110, "Golden internet", "01.01.2014", 40));
        orders.add(new Order(111, "Golden internet", "09.01.2014", 40));
        orders.add(new Order(112, "Silver internet", "10.01.2014", 25));
        orders.add(new Order(113, "Silver internet", "12.01.2014", 25));
        orders.add(new Order(201, "Silver internet", "13.01.2014", 25));
        orders.add(new Order(242, "Platinum internet", "20.01.2014", 55));
        orders.add(new Order(301, "Platinum internet", "20.01.2014", 55));
        orders.add(new Order(302, "Silver internet", "20.01.2014", 25));
        orders.add(new Order(501, "Silver internet", "29.01.2014", 25));
        
        Calendar calendarFrom = Calendar.getInstance();
        Calendar calendarTo = Calendar.getInstance();
        calendarFrom.set(2014, 0, 1);
        calendarTo.set(2014, 0, 31);
        
        ReportFactory rf = ReportFactory.getReportFactory(ReportFactory.XLS);
        RiReport riReport = rf.getRiReport();
        SiReport siReport = rf.getSiReport();
        riReport.generateRoutersUtilizationAndCapacityReport(routers);
        siReport.generateNewOrdersPerPeriodReport(orders, calendarFrom, 
                calendarTo);
    }

}
