import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NioBufferedReader {

    public static void main(String[] args) {


        Path path = Paths.get("NioReadFile/data/Profile.txt");


        try (BufferedReader reader = Files.newBufferedReader(path, Charset.forName("UTF-8"))) {


            String currentLine = null;
            while ((currentLine = reader.readLine()) != null) {//while there is content on the current line
                System.out.println(currentLine); // print the current line
            }
        } catch (IOException ex) {
            ex.printStackTrace(); //handle an exception here
        }

        Path path2 = Paths.get("NioReadFile/data/Profile2.txt");
        try{

            List contents = Files.readAllLines(path2);
            String contentsString = Files.readString(path2);
//            StringBuilder stringProfile = new StringBuilder();
            //Read from the stream
            for(Object content:contents){//for each line of content in contents
                System.out.println(content);// print the line
            }

            System.out.println(contentsString);
            String formattedData = contentsString.
                    replaceAll("\\n", ",")
                    .replaceAll("\\s", "");

            System.out.println(formattedData);

            Map<String, String> stringToMap = Arrays
                    .stream(formattedData.split(","))
                    .map(entry -> entry.split(":"))
                    .collect(Collectors.toMap(entry -> entry[0], entry -> entry[1]));

            System.out.println(stringToMap);

            Profile profile = new Profile();

            profile.setAge(Integer.parseInt(stringToMap.get("Age")));
            profile.setName(stringToMap.get("Name"));
            profile.setEmail(stringToMap.get("Email"));
            profile.setPhone(Long.parseLong(stringToMap.get("Phone")));

            System.out.println(profile);

        }catch(IOException ex){
            ex.printStackTrace();//handle exception here
        }



    }
}

