import com.sun.org.apache.xpath.internal.SourceTree;

public class Player {

    private String name;
    private int points;
    private int globalRank;
    private int gathered;
    private int loot;
    private int oda;
    private int odd;
    private int ods;
    private double resRatio;

    private Player(String name, int points, int globalRank, int gathered, int loot, int oda, int odd, int ods){

        this.name = name;
        this.points = points;
        this.globalRank = globalRank;
        this.gathered = gathered;
        this.loot = loot;
        this.oda = oda;
        this.odd = odd;
        this.ods = ods;
        resRatio = (gathered + loot) / points;

    }

    /*
     * Accessor methods.
     */
    public String getName() {
        return name;
    }
    public int getGlobalRank() {
        return globalRank;
    }
    public int getPoints() {
        return points;
    }
    public int getGathered() {
        return gathered;
    }
    public int getLoot() {
        return loot;
    }
    public int getOda() {
        return oda;
    }
    public int getOdd() {
        return odd;
    }
    public int getOds() {
        return ods;
    }
    public double getResRatio() {
        return resRatio;
    }

    public static Player parsePlayer(String input) throws NumberFormatException{

        // Remove leading space
        input = input.substring(1);

        String[] data = input.split("\t");

        // Todo: exclude Titles by blocking " (*)"
        return new Player(
                data[0].replaceAll(" \\(([^)]+)\\)", ""),
                Integer.valueOf(data[2].replaceAll(",", "")),
                Integer.valueOf(data[3].replaceAll(",", "")),
                Integer.valueOf(data[5].replaceAll("\\.", "")),
                Integer.valueOf(data[6].replaceAll("\\.", "")),
                Integer.valueOf(data[7].replaceAll("\\.", "")),
                Integer.valueOf(data[8].replaceAll("\\.", "")),
                Integer.valueOf(data[9].replaceAll("\\.", ""))
        );

    }

}
