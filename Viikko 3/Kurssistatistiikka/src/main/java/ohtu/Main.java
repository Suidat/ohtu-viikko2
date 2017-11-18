package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Main {

    public static void main(String[] args) throws IOException {
        // vaihda oma opiskelijanumerosi seuraavaan, ÄLÄ kuitenkaan laita githubiin omaa opiskelijanumeroasi
        String studentNr = "011120775";
        if ( args.length>0) {
            studentNr = args[0];
        }

        String url = "https://studies.cs.helsinki.fi/ohtustats/students/"+studentNr+"/submissions";
        String bodyText = Request.Get(url).execute().returnContent().asString();

        /*System.out.println("json-muotoinen data:");
        System.out.println( bodyText );*/

        url = "https://studies.cs.helsinki.fi/ohtustats/courseinfo";
        Gson mapper = new Gson();
        String newbBodyText = Request.Get(url).execute().returnContent().asString();


        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        courseInfo info = mapper.fromJson(newbBodyText, courseInfo.class);

        int num = 0;
        int time = 0;
        System.out.println("Kurssi: "+info.getName()+", "+info.getTerm()+"\n");
        System.out.println("Opiskelijanumero: "+studentNr+"\n");
        for (Submission submission : subs) {
            submission.setMax(info.getExercises()[submission.getWeek()-1]);
            System.out.println(submission);
            num += submission.getExercises().length;
            time += submission.getHours();
        }
        System.out.println("yhteensä: "+num+" tehtävää "+ time+ " tunnissa");

        url = "https://studies.cs.helsinki.fi/ohtustats/stats";
        String statsResponse = Request.Get(url).execute().returnContent().asString();

        JsonParser parser = new JsonParser();
        JsonObject parsittuData = parser.parse(statsResponse).getAsJsonObject();
        String s = parsittuData.get("1").toString();
        Stat stat = mapper.fromJson(s, Stat.class);
        System.out.println("\nkurssilla yhteensä "+stat.getStudents()+" palautusta, palautettuja tehtäviä "+ stat.getExercise_total() +" kpl");
    }
}