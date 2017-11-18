package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

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

        System.out.println("Kurssi: "+info.getName()+", "+info.getTerm()+"\n");
        System.out.println("Opiskelijanumero: "+studentNr+"\n");
        for (Submission submission : subs) {
            System.out.println(submission);
        }

    }
}