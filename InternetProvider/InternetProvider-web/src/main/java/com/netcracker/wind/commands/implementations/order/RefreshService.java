package com.netcracker.wind.commands.implementations.order;

import com.netcracker.wind.commands.ICommand;
import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import com.netcracker.wind.dao.interfaces.IPriceDAO;
import com.netcracker.wind.dao.interfaces.IProviderLocationDAO;
import com.netcracker.wind.entities.Price;
import com.netcracker.wind.entities.ProviderLocation;
import com.netcracker.wind.entities.Service;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Anatolii
 */
public class RefreshService implements ICommand {
    
    private static final String X = "x";
    private static final String Y = "y";
    private static final String STATUS = "status";
    private static final String ERROR = "error";
    private static final String OK = "ok";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String PRICE = "price";
    private static final String SERVICES = "services";
    private static final String ADDRESS = "address";
    private static final String PROVIDER_LOCATION = "providerLocation";

    public String execute(HttpServletRequest request,
            HttpServletResponse response) {
        JSONObject jsono = new JSONObject();
        String sX = request.getParameter(X);
        String sY = request.getParameter(Y);
        double actualX;
        double actualY;
        if ("".equals(sX) || "".equals(sY)) {
            try {
                jsono.put(STATUS, ERROR);
            } catch (JSONException ex) {
                Logger.getLogger(RefreshService.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            actualX = Double.parseDouble(sX);
            actualY = Double.parseDouble(sY);

            //Get FactoryDAO
            AbstractFactoryDAO factoryDAO = FactoryCreator.getInstance().getFactory();
            //Get needed DAO
            IProviderLocationDAO providerLocationDAO = factoryDAO.createProviderLocationDAO();
            IPriceDAO priceDAO = factoryDAO.createPriceDAO();

            //Find nearest ProviderLocation
            List<ProviderLocation> providerLocations
                    = providerLocationDAO.findAll(
                            AbstractOracleDAO.DEFAULT_PAGE_NUMBER,
                            AbstractOracleDAO.ALL_RECORDS);
            ProviderLocation nearestProviderLocation = OrderUtilities.findNearestProviderLocation(
                    providerLocations, actualX, actualY);

            if (nearestProviderLocation == null) {
                try {
                    jsono.put(STATUS, ERROR);
                } catch (JSONException ex) {
                    Logger.getLogger(RefreshService.class.getName()).log(Level.SEVERE, null, ex);
                }
                return jsono.toString();
            }

            //Find available Services and Prices
            List<Price> prices = priceDAO.findByProviderLoc(
                    nearestProviderLocation.getId(),
                    AbstractOracleDAO.DEFAULT_PAGE_NUMBER,
                    AbstractOracleDAO.ALL_RECORDS);

            try {
                jsono.put(STATUS, OK);
                JSONObject providerLocationJSON = new JSONObject();
                JSONArray servicesJSONArray = new JSONArray();
                for (Price price : prices) {
                    Service service = price.getService();
                    JSONObject serviceJSONObject = new JSONObject();
                    serviceJSONObject.put(ID, service.getId());
                    serviceJSONObject.put(NAME, service.getName());
                    serviceJSONObject.put(PRICE, price.getPrice());
                    servicesJSONArray.put(serviceJSONObject);
                }
                providerLocationJSON.put(SERVICES, servicesJSONArray);
                providerLocationJSON.put(ADDRESS, nearestProviderLocation.getAddress());
//                providerLocation.put("name", nearestProviderLocation.getName());
                providerLocationJSON.put(Y, nearestProviderLocation.getPosY());
                providerLocationJSON.put(X, nearestProviderLocation.getPosX());
                jsono.put(PROVIDER_LOCATION, providerLocationJSON);
            } catch (JSONException ex) {
                Logger.getLogger(RefreshService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return jsono.toString();
    }

}
