package com.github.kristofa.wickettagcloud.example;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * Wicket Tag Cloud example application.
 * 
 * @author kristof
 */
public class WicketTagCloudApplication extends WebApplication {

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends Page> getHomePage() {
        return HomePage.class;
    }

}
