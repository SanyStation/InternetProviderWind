package com.netcracker.wind.entities.reports;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author Anatolii
 */
public class DeviceProfit implements Serializable {

    private static final long serialVersionUID = -2872596414817265055L;

    private Integer profit;
    private int deviceId;

    public DeviceProfit() {
    }

    public Integer getProfit() {
        return profit;
    }

    public void setProfit(Integer profit) {
        this.profit = profit;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(deviceId);
        builder.append(profit);

        return builder.toHashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (!(object instanceof DeviceProfit)) {
            return false;
        }
        DeviceProfit rhs = (DeviceProfit) object;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(deviceId, rhs.getDeviceId());
        builder.append(profit, rhs.getProfit());

        return builder.isEquals();
    }

}
