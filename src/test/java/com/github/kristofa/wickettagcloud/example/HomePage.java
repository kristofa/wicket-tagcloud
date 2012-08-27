package com.github.kristofa.wickettagcloud.example;

import java.util.Arrays;
import java.util.Collection;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.link.PopupSettings;
import org.apache.wicket.model.IModel;

import com.github.kristofa.wickettagcloud.TagCloudPanel;
import com.github.kristofa.wickettagcloud.TagCloudTag;

/**
 * The tag cloud example app home page.
 * 
 * @author kristof
 */
public class HomePage extends WebPage {

    private static final long serialVersionUID = 1119127679504030126L;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new TagCloudPanel("tagcloud", new TagCloudModel(), 12, 48, "Today's popular websites"));
    }

    /**
     * The model that provides the tags. This example simply provides a fixed list of tags but by using a model you can get
     * tags from anywhere...
     * 
     * @author kristof
     */
    private class TagCloudModel implements IModel<Collection<TagCloudTag>> {

        private static final long serialVersionUID = 936449833892290813L;

        @Override
        public void detach() {

        }

        @Override
        public Collection<TagCloudTag> getObject() {
            return Arrays.<TagCloudTag>asList(new TagCloudTagImpl("google", "http://www.google.com", 18),
                new TagCloudTagImpl("facebook", "http://www.facebook.com", 20), new TagCloudTagImpl("github",
                    "http://www.github.com", 4), new TagCloudTagImpl("twitter", "http://www.twitter.com", 16),
                new TagCloudTagImpl("flickr", "http://www.flickr.com", 14), new TagCloudTagImpl("linkedin",
                    "http:/www.linkedin.com", 12), new TagCloudTagImpl("foursquare", "http://www.foursquare.com", 6),
                new TagCloudTagImpl("stackoverflow", "http://www.stackoverflow.com", 6), new TagCloudTagImpl("delicious",
                    "http://www.delicious.com", 8));
        }

        @Override
        public void setObject(final Collection<TagCloudTag> object) {

        }

    }

    /**
     * {@link TagCloudTag} implementation.
     * 
     * @author kristof
     */
    private class TagCloudTagImpl implements TagCloudTag {

        private static final long serialVersionUID = 2080387471025248720L;
        private final String name;
        private final String url;
        private final int weight;

        public TagCloudTagImpl(final String name, final String url, final int weight) {

            this.name = name;
            this.url = url;
            this.weight = weight;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public AbstractLink getLink(final String linkId) {
            final ExternalLink externalLink = new ExternalLink(linkId, url);
            final PopupSettings popupSettings = new PopupSettings();
            return externalLink.setPopupSettings(popupSettings);
        }

        @Override
        public int getWeight() {
            return weight;
        }

    }

}
