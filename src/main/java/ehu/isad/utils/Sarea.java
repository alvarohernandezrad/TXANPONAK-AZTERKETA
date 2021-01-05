package ehu.isad.utils;


import com.google.gson.Gson;
import ehu.isad.model.Txanpona;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class Sarea {

    private static Sarea nireSarea = null;

    private Sarea() {
    }

    public static Sarea getNireSarea() {
        if (nireSarea == null) {
            nireSarea = new Sarea();
        }
        return nireSarea;
    }

    public Txanpona liburuarenDatuakHasieratu(String txanpon) throws IOException {
        URL openlibrary = new URL("https://api.gdax.com/products/" + txanpon + "-eur/ticker");
        URLConnection yc = openlibrary.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        String inputLine = in.readLine();
        in.close();
        Gson gson = new Gson();
        return gson.fromJson(inputLine, Txanpona.class);
    }
}