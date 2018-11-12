package cn.ac.iie.dgraph;


public class DgraphConf {

    private String ips;
    private int port;

    public DgraphConf(String ips, int port) {
        super();
        this.ips = ips;
        this.port = port;
    }

    public String getIps() {
        return ips;
    }

    public void setIps(String ips) {
        this.ips = ips;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
