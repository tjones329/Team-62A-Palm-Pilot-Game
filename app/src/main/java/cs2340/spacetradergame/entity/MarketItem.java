package cs2340.spacetradergame.entity;

/* BASED OFF OF THIS CHART:
Name	MTLP MTLU TTP BasePrice  IPL	Var	     IE	      CR	     ER    	MTL	MTH
Water	  0	  0	  2	    30	    3	    4	    DROUGHT	LOTSOFWATER	DESERT	 30	 50
Furs	  0	  0	  0	    250	    10	    10	    COLD	RICHFAUNA	LIFELESS	230	280
Food	  1	  0	  1	    100	    5	    5	   CROPFAIL	RICHSOIL	POORSOIL	 90	160
Ore	      2	  2	  3	    350	    20	    10	   WAR	MINERALRICH	MINERALPOOR	350	420
Games	  3	  1	  6	    250	    -10	    5	   BOREDOM	ARTISTIC	Never	160	270
Firearms  3	  1	  5	    1250	-75	    100	   WAR	WARLIKE	Never	600	1100
Medicine  4	  1	  6 	 650	-20	    10	   PLAGUE	LOTSOFHERBS	Never	400	700
Machines  4	  3	  5	     900	-30 	5	   LACKOFWORKERS	Never	Never	600	800
Narcotics 5	  0	  5	    3500	-125	150	     BOREDOM	WEIRDMUSHROOMS	Never	2000	3000
Robots	  6	  4	  7 	5000	 -150	100	    LACKOFWORKERS	Never	Never	3500	5000
 */

public enum MarketItem {
    WATER(0,0,30,3,4),
    FURS(0,0,250,10,10),
    FOOD(1,0,100,5,5),
    ORE(2,2,350,20,10),
    GAMES(3,1,250,-10,5),
    FIREARMS(3,1,1250,-75,100),
    MEDICINE(4,1,650,-20,10),
    MACHINES(4,3,900,-30,5),
    NARCOTICS(5,0,900,-30,5),
    ROBOTS(6,4,5000,-150,100);

    private int MTLP;
    private int MTLU;
    private int basePrice;
    private int IPL;
    private int var;

    MarketItem(int MTLP, int MTLU, int basePrice, int IPL, int var) {
        this.MTLP = MTLP;
        this.MTLU = MTLU;
        this.basePrice = basePrice;
        this.IPL = IPL;
        this.var = var;
    }

    public boolean canProduce(Planet.TechLevel techLevel) {
        return MTLP <= techLevel.ordinal();
    }

    public boolean canSell(Planet.TechLevel techLevel) {
        return MTLU <= techLevel.ordinal();
    }

    public int getMTLP() {
        return MTLP;
    }

    public int getMTLU() {
        return MTLU;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public int getIPL() {
        return IPL;
    }

    public int getVar() {
        return var;
    }
}
