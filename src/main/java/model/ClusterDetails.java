package model;

import java.time.LocalDateTime;
public class ClusterDetails {
    private static String clusterId;
    private static String message;
    public static String getMessage() {return message;}
    public void setMessage(String message) {this.message = message;}
    public void setId(String clusterId){this.clusterId = clusterId;}
    public static String getId(){return clusterId;}
}
