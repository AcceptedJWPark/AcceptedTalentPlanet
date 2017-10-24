package com.example.accepted.acceptedtalentplanet.Home;

/**
 * Created by kwonhong on 2017-10-05.
 */

public class Item {

    int image;
    String name;
    String talent;


    public int getImage()
    {
        return image;
    }

    public String getName()
    {
        return name;
    }

    public String getTalent()
    {
        return talent;
    }

    public Item(int image, String name, String talent)
    {
        this.image=image;
        this.name=name;
        this.talent=talent;
    }


}