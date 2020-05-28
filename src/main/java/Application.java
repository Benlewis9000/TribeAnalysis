import java.io.IOException;
import java.util.ArrayList;
import java.io.File;

public class Application {

    public static void main(String[] args){

        try {

            ArrayList<Tribe> tribes = TribeManager.loadTribes("tribes.txt");

            for (Tribe tribe : tribes){

                tribe.printUnderPerformers(30, new File("tribe_analysis.txt"), true);

            }

        }
        catch (IOException e){
            System.out.println("Error: failed to read tribes from file \"tribes.txt\".");
        }

    }

}
