import java.io.*;
import java.util.ArrayList;

public class saveRanking
{
    public saveRanking(String filename, ArrayList<String> r) throws IOException
    {
        ArrayList<String> ranking = r;
        String linea = "\n La classifica è \n" + "Al primo posto " + ranking.get(0) + "\n" + "Al secondo posto " + ranking.get(1) + "\n" + "Al terzo posto " + ranking.get(2) + "\n";
        
        PrintWriter output = new PrintWriter(new FileWriter(filename, true));
        BufferedWriter input = new BufferedWriter(output);

        input.write(linea);
        input.newLine();
        input.close();
    }
}