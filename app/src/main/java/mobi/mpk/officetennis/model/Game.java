package mobi.mpk.officetennis.model;

public class Game {
    private int host;
    private int partner;
    private boolean deleted;

    public Game() {
        host = 0;
        partner = 0;
        deleted = false;
    }

    public Game(int host, int partner, boolean deleted) {
        this.host = host;
        this.partner = partner;
        this.deleted = deleted;
    }

    public int getHost() {
        return host;
    }

    public void setHost(int host) {
        this.host = host;
    }

    public int getPartner() {
        return partner;
    }

    public void setPartner(int partner) {
        this.partner = partner;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
