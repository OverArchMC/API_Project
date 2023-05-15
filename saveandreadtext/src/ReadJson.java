import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


// video to load jar
//https://www.youtube.com/watch?v=QAJ09o3Xl_0

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// Program for print data in JSON format.
    public class ReadJson {
        public static Swing layout;
        public static void main(String args[]) throws ParseException {
            // In java JSONObject is used to create JSON object
            // which is a subclass of java.util.HashMap.

            JSONObject file = new JSONObject();
            file.put("Full Name", "Ritu Sharma");
            file.put("Roll No.", new Integer(1704310046));
            file.put("Tution Fees", new Double(65400));


            // To print in JSON format.
            System.out.print(file.get("Tution Fees"));
            layout = new Swing();
            while(true){
                if(layout.working){
                    pull();
                }
            }



        }

        public static void pull() throws ParseException {
            String output = "abc";
            String totlaJson="";
            try {
                String pokemon = "https://pokeapi.co/api/v2/pokemon/" + layout.ta2.toString();
                URL url = new URL(pokemon);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");

                if (conn.getResponseCode() != 200) {

                    throw new RuntimeException("Failed : HTTP error code : "
                            + conn.getResponseCode());
                }

                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream())));


                System.out.println("Output from Server .... \n");
                while ((output = br.readLine()) != null) {
                    System.out.println(output);
                    totlaJson+=output;
                }

                conn.disconnect();

            } catch (MalformedURLException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }

            JSONParser parser = new JSONParser();
            //System.out.println(str);
            org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) parser.parse(totlaJson);
            //System.out.println("hi " + jsonObject);

            try {
                System.out.println(jsonObject.get("name"));

                org.json.simple.JSONArray msg = (org.json.simple.JSONArray) jsonObject.get("abilities");
                int n =   msg.size(); //(msg).length();
                for (int i = 0; i < n; i++) {
                    //String test =(String) msg.get(i).toString();
                    org.json.simple.JSONObject test = (org.json.simple.JSONObject) msg.get(i);
                    org.json.simple.JSONObject test2 = (org.json.simple.JSONObject) test.get("ability");
                    String abilityName = (String) test2.get("name");
                    System.out.println(abilityName);
                   // System.out.println(person.getInt("key"));
                }
                String name= (String)jsonObject.get("height").toString();
                System.out.println(name);
            }

            catch (Exception e) {
                e.printStackTrace();
            }



            layout.working = false;
        }

    public static void realPull() throws ParseException {
        String output = "abc";
        String totlaJson="";
        try {

            URL url = new URL("https://pokeapi.co/api/v2/pokemon/ditto");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {

                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));


            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                totlaJson+=output;
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONParser parser = new JSONParser();
        //System.out.println(str);
        org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) parser.parse(totlaJson);
        System.out.println("hi " + jsonObject);

        try {
            System.out.println(jsonObject.get("name"));

            org.json.simple.JSONArray msg = (org.json.simple.JSONArray) jsonObject.get("abilities");
            int n =   msg.size(); //(msg).length();
            for (int i = 0; i < n; i++) {
                //String test =(String) msg.get(i).toString();
                org.json.simple.JSONObject test = (org.json.simple.JSONObject) msg.get(i);
                org.json.simple.JSONObject test2 = (org.json.simple.JSONObject) test.get("ability");
                String abilityName = (String) test2.get("name");
                System.out.println(abilityName);
                // System.out.println(person.getInt("key"));
            }
            String name= (String)jsonObject.get("height").toString();
            System.out.println(name);
        }

        catch (Exception e) {
            e.printStackTrace();
        }




    }



    }

