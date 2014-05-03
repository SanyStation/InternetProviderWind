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

    public String execute(HttpServletRequest request,
            HttpServletResponse response) {
        JSONObject jsono = new JSONObject();
        String sX = request.getParameter("x");
        String sY = request.getParameter("y");
        double actualX;
        double actualY;
        if ("".equals(sX) || "".equals(sY)) {
            try {
                jsono.put("status", "error");
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
                    jsono.put("status", "error");
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
                jsono.put("status", "ok");
                JSONObject providerLocationJSON = new JSONObject();
                JSONArray servicesJSONArray = new JSONArray();
                for (Price price : prices) {
                    Service service = price.getService();
                    JSONObject serviceJSONObject = new JSONObject();
                    serviceJSONObject.put("id", service.getId());
                    serviceJSONObject.put("name", service.getName());
                    serviceJSONObject.put("price", price.getPrice());
                    servicesJSONArray.put(serviceJSONObject);
                }
                providerLocationJSON.put("services", servicesJSONArray);
                providerLocationJSON.put("address", nearestProviderLocation.getAddress());
//                providerLocation.put("name", nearestProviderLocation.getName());
                providerLocationJSON.put("y", nearestProviderLocation.getPosY());
                providerLocationJSON.put("x", nearestProviderLocation.getPosX());
                jsono.put("providerLocation", providerLocationJSON);
            } catch (JSONException ex) {
                Logger.getLogger(RefreshService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return jsono.toString();
    }

}
