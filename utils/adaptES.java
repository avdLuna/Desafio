import java.io.*;

public class adaptES {
    public static void main(String[] args) {
        String linha = "";
        String texto = "";
        int i = 1;

        try {
            FileReader reader = new FileReader("/Users/amandaluna/Downloads/Desafio/src/posts.json");
            BufferedReader leitor = new BufferedReader(reader);

            while (true) {
                linha = leitor.readLine();
                if (linha == null)
                    break;
                String output = String.format("{\"index\": {\"_index\": \"posts\", \"_type\": \"docs\", \"_id\": \"%d\"}}\n", i);
                texto += output + linha + "\n";

                i++;
            }
            System.out.println(texto);
            FileWriter fw=new FileWriter("postsFormatted.json");
            fw.write(texto);
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Success...");
    }
}