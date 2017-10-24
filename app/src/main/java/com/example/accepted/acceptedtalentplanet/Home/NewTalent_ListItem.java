package com.example.accepted.acceptedtalentplanet.Home;

/**
 * Created by Accepted on 2017-09-29.
 */

public class NewTalent_ListItem {

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

    public NewTalent_ListItem(int image, String talent, String name)
    {
        this.image=image;
        this.talent=talent;
        this.name=name;
    }


}
