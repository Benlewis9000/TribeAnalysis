import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Scanner;

public class TribeManager {

    // Current date (relative to TW servers)
    LocalDate date = LocalDate.now(ZoneId.of("GMT+00"));    // Todo: tribe manager will be serializable to json

    public static ArrayList<Tribe> loadTribes(String path) throws IOException {

        Scanner scanner = new Scanner(new File(path));

        ArrayList<Tribe> tribes = new ArrayList<>();
        Tribe tribe = null;

        while (scanner.hasNextLine()){

            String line = scanner.nextLine();

            // Check if tag or player data
            if (!line.contains("\t")) {

                // Add current tribe to list (if exists) before creating new one
                if (tribe != null) tribes.add(tribe);
                tribe = new Tribe((line));

            }

            // Make sure tribe has been set
            else if (tribe != null) {

                try {
                    tribe.addPlayer(Player.parsePlayer(line));
                }
                catch (NumberFormatException e){
                    System.out.println("Error: failed to parse Player from string \"" + line + "\". Ignoring.");
                    e.printStackTrace();
                    System.exit(1);
                }

            }

        }
        if (tribe != null) tribes.add(tribe);

        return tribes;
    }

}
