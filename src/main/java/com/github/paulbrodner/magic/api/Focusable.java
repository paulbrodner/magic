package com.github.paulbrodner.magic.api;

/**
 * Focused application
 * 
 * @author Paul Brodner
 */
public interface Focusable<App>
{
    App focus() throws Exception;
}
