package dolata.patryk;

import java.math.BigInteger;

/**
 * Created by Patryk Dolata on 29.01.2018.
 */
public class AlertData implements Comparable<AlertData>{
    private String id;
    private String deviceId;

    public String getId() {
        return id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public AlertData(String id, String deviceId) {
        this.id = id;
        this.deviceId = deviceId;
    }

    @Override
    public int compareTo(AlertData o) {
        return new BigInteger(o.getId()).compareTo(new BigInteger(this.id));
//        return new BigInteger(this.id).compareTo(new BigInteger(o.id));
//        return o.getId().compareTo(this.id);
//        return this.id.compareTo(o.getId());
    }
}
