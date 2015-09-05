package ua.at.programmers.netorg;

import javax.swing.SwingUtilities;

import java.net.*;
import java.io.*;

//import ua.at.programmers.netorg.interfaces.IntGui;
//import ua.at.programmers.netorg.interfaces.IntMenu;
//import ua.at.programmers.netorg.interfaces.IntSocketTU;

import ua.at.programmers.netorg.gui.Gui;
import ua.at.programmers.netorg.console.Menu;
import ua.at.programmers.netorg.logic.SocketTU;

public class Main {

    public void getUrl(String sUrl) throws IOException {
        URL url = new URL(sUrl);
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "Cp1251"));

        String s;
        while ((s = reader.readLine()) != null) {
            System.out.println(s);
        }

        if (reader == null) {
            reader.close();
        }
    }

    public void scanUrl(String sUrl, int i) throws IOException {
        SocketTU sock1 = new SocketTU(sUrl, i);
        sock1.open();
        sock1.close();
    }

    public void scanUrl(String sUrl) throws IOException {
        for (int i = 1; i < 1000; i++) {
            scanUrl(sUrl, i);
        }
    }

    public static void main(String[] args) throws IOException {
        Main main1 = new Main();
        if (args.length == 0) {
            SwingUtilities.invokeLater(new Runnable() {
                    
                    @Override
                    public void run() {
                        Gui gui = new Gui();
                    }
                });
        }
        else if (args.length == 1) {
            if (args[0].equals("-h")) {
                System.out.println("for run GUI run without parameters");
                System.out.println("-h for print help");
                System.out.println("-url http://i.ua for print page from url");
                System.out.println("192.168.0.1 22 for scan open port");
            }
            else {
                main1.scanUrl(args[0]);
            }
        }
        else if (args.length == 2) {
            if (args[0].equals("-url"))
                main1.getUrl(args[1]);
            else {
                main1.scanUrl(args[0], Integer.parseInt(args[1]));
            }
        }
    }
}
