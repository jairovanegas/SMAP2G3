package sma.grupo3.Retailer.DistributedBehavior;

public enum Localities {
    CHAPINERO("CHAPINERO", true, 6, true),
    BARRIOSUNIDOS("BARRIOSUNIDOS", false, 0, true),
    TEUSAQUILLO("TEUSAQUILLO", true, 4, true),
    USAQUEN("USAQUEN", true, 4, true),
    SUBA("SUBA", false, 5, true),
    ENGATIVA("ENGATIVA", true, 5, true);

    public final String value;
    public final boolean isThereWarehouse;
    public final int fleetSize;
    public final boolean enable;

    Localities(String value, boolean isThereWarehouse, int fleetSize, boolean enable) {
        this.value = value;
        this.isThereWarehouse = isThereWarehouse;
        this.fleetSize = fleetSize;
        this.enable = enable;
    }
}
