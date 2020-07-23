package main;


import models.Anime;
import utilities.WebUtils;

import java.util.ArrayList;
import java.util.List;

//Main Class - Init Program.
public class Main {


    public static void main(String[] args) {

        //Find SQLite DB if present.
        //to be added later.

        //Creates the HorribleSub Wrapper that grabs all shows on the site.
        HorribleSubWrapper horribleSubWrapper = new HorribleSubWrapper();

        System.out.println("Program Loaded");

        horribleSubWrapper.searchAnime();


    }
}
