package com.github.paulbrodner.magic.app;

import org.sikuli.script.App;
import org.springframework.stereotype.Component;

import com.github.paulbrodner.magic.api.Applicationable;
import com.github.paulbrodner.magic.api.GuiScreen;

@Component
public class Finder extends GuiScreen
{
    public Applicationable open() throws Exception
    {
        App.open("Finder");
        return this;
    }

    public Applicationable close() throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

    public String getProcessName()
    {
        // TODO Auto-generated method stub
        return null;
    }

    public GuiScreen focus() throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }

}
