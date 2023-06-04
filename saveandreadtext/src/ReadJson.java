import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.*;



//import com.*;


// video to load jar
//https://www.youtube.com/watch?v=QAJ09o3Xl_0

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.imageio.ImageIO;

// Program for print data in JSON format.
    public class ReadJson {

    public static Swing layout;
    public static ArrayList<String> facts;
    public static void main(String args[]) throws ParseException {
            // In java JSONObject is used to create JSON object
            // which is a subclass of java.util.HashMap.

            JSONObject file = new JSONObject();
            file.put("Full Name", "Ritu Sharma");
            file.put("Roll No.", new Integer(1704310046));
            file.put("Tution Fees", new Double(65400));

            facts = new ArrayList<String>();

            // Welcome to my API project!
            // It pulls



            // To print in JSON format.
            System.out.print(file.get("Tution Fees"));
            layout = new Swing();
            //System.out.println("no way");
            while(true){
                if(layout.working){
                    //System.out.println("got here");
                    pull();
                    layout.working = false;
                    //break;
                }else{
                    //System.out.println(layout.working);
                    System.out.print("");
                }
            }



        }

        public static void pull() throws ParseException {
            String output = "abc";
            String totlaJson="";
            try {
                String pokemon = "https://pokeapi.co/api/v2/pokemon/" + layout.link.getText().toLowerCase();
                //System.out.println("testing");
                //System.out.println(layout.link.getText());
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
                    //System.out.println(output);
                    totlaJson+=output;
                }

                conn.disconnect();

            } catch (MalformedURLException e) {
                //System.out.println("catch got ran");
                e.printStackTrace();

            } catch (IOException e) {
                //System.out.println("ioexception");
                e.printStackTrace();
            }

            JSONParser parser = new JSONParser();
            //System.out.println(str);
            org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) parser.parse(totlaJson);
            //System.out.println("hi " + jsonObject);

            try {
                System.out.println(jsonObject.get("name"));
                String pokemonName = jsonObject.get("name").toString();
                pokemonName = pokemonName.substring(0, 1).toUpperCase() + pokemonName.substring(1);
                String finalResult = "Pokemon name: " + pokemonName;


                org.json.simple.JSONArray msg = (org.json.simple.JSONArray) jsonObject.get("abilities");
                finalResult += "\n\n" + pokemonName + "'s abilities:";
                int n =   msg.size(); //(msg).length();
                for (int i = 0; i < n; i++) {
                    //String test =(String) msg.get(i).toString();
                    org.json.simple.JSONObject test = (org.json.simple.JSONObject) msg.get(i);
                    org.json.simple.JSONObject test2 = (org.json.simple.JSONObject) test.get("ability");
                    String abilityName = (String) test2.get("name");
                    finalResult += "\n" + abilityName;
                    System.out.println(abilityName);
                   // System.out.println(person.getInt("key"));
                }
                String name= (String)jsonObject.get("height").toString();
                finalResult += "\n\n" + pokemonName + "'s height: " + jsonObject.get("height").toString();
                System.out.println(name);
                String linktoimg = (String)((HashMap<Object, Object>)jsonObject.get("sprites")).get("front_default");
                System.out.println(linktoimg);

                org.json.simple.JSONArray indexes = (org.json.simple.JSONArray) jsonObject.get("game_indices");
                org.json.simple.JSONObject specificindex = (org.json.simple.JSONObject) indexes.get(indexes.size()-1);
                Long index = (Long) specificindex.get("game_index");
                System.out.println(index);

                finalResult += "\n\n" + "If you want to see " + pokemonName + " for yourself, go to this link: " + linktoimg;

                finalResult += "\n\n" + pokemonName + "'s Pokedex index: " + index;
                // numbers stuff
                String numberfact = realPull(index);
                int count = 0;
                while(facts.contains(numberfact) && count < 20){
                    numberfact = realPull(index);
                    count++;
                }
                facts.add(numberfact);
                if(count >= 20){
                    finalResult += "\n\nYou already seem to know quite a bit about the number " + index + ".";
                }else{
                    finalResult += "\n\n" + numberfact;
                }
                System.out.println(numberfact);


                layout.result.setText(finalResult);
            }

            catch (Exception e) {
                e.printStackTrace();
            }



            //layout.working = false;
        }

    public static String realPull(Long index) throws ParseException {
        String output = "abc";
        String totlaJson="";
        try {

            URL url = new URL("http://numbersapi.com/" + index.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {

                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));


            //System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                //System.out.println(output);
                totlaJson+=output;
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //JSONParser parser = new JSONParser();
        //System.out.println(str);
        //org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) parser.parse(totlaJson);
        //System.out.println("hi " + jsonObject);


        return totlaJson;


    }



    }

