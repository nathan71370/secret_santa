import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.shuffle;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Génération in progress...");

        List<String> players = getPlayers();

        Map<String, String> draw = draw(players);

        generateFiles(draw);

        System.out.println("Génération done.");
    }

    private static List<String> getPlayers() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("resources/players.txt"));
        List<String> players = new ArrayList<>();
        String player;
        while ((player = bufferedReader.readLine()) != null) {
            players.add(player);
        }
        shuffle(players);
        return players;
    }

    private static Map<String, String> draw(List<String> players) throws Exception {
        if (players.size() % 2 != 0) {
            throw new Exception("You need to bee an pair number");
        }

        Map<String, String> draw = new HashMap<>();
        for (int i = 0; i < players.size() - 1; i++) {
            draw.put(players.get(i), players.get(i + 1));
        }
        draw.put(players.get(players.size() - 1), players.get(0));

        return draw;
    }

    private static void generateFiles(Map<String, String> draw) throws IOException {
        for (String line : draw.keySet()) {
            File file = new File("generated/" + line + ".txt");
            FileWriter fileWriter = new FileWriter(file);
            file.createNewFile();
            fileWriter.write(draw.get(line));
            fileWriter.close();
        }
    }
}