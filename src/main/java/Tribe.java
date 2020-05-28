import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.io.File;

public class Tribe {

    String tag;
    private ArrayList<Player> tribesmates;

    /**
     * Empty tribe constructor.
     * @param tag of tribe.
     */
    public Tribe(String tag){

        this.tag = tag;
        tribesmates = new ArrayList<>();

    }

    /**
     * Tribe constructor.
     * @param tag of tribe.
     * @param players in tribe.
     */
    public Tribe(String tag, ArrayList<Player> players){

        this.tag = tag;
        this.tribesmates = players;

    }

    public void addPlayer(Player p){

        this.tribesmates.add(p);

    }

    public void sortByResRatio(){

        Collections.sort(this.tribesmates, (Player a, Player b) -> Double.compare(a.getResRatio(), b.getResRatio()));

    }

    public void printUnderPerformers(int minRatio, File output, boolean append){

        this.sortByResRatio();

        try {

            PrintWriter printer = new PrintWriter(new FileWriter(output, append));

            printer.println("[ally]" + this.tag + "[/ally]");
            printer.println("[table]");
            printer.println("[**]Player[||]Points[||]Income[||]Ratio[/**]");

            for (Player p : this.tribesmates){

                if (p.getResRatio() > minRatio) break;

                printer.println("[*][player]" + p.getName() + "[/player][|]" + p.getPoints() + "[|]"
                        + Integer.sum(p.getGathered(), p.getLoot()) + "[|]" + String.format("%.2f", p.getResRatio()));

            }
            printer.println("[/table]");

            printer.close();

        }
        catch (IOException e){

            e.printStackTrace();

        }

    }

    /*
        todo:
            make serialiazable to json
            when loading in data can look for
                indicate improvement by more than 3%
                indicate decrease by more than 3%
                e.g. Tashi200: 34 [+]
                    maybe display the percentage change? colour code?
                repeat offences
     */

}
