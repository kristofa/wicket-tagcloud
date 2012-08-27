package com.github.kristofa.wickettagcloud;

import java.io.Serializable;

import org.apache.wicket.markup.html.link.AbstractLink;

/**
 * Represents a Tag that can be diplayed by the {@link TagCloudPanel}.
 * 
 * @author kristof
 */
public interface TagCloudTag extends Serializable {

    /**
     * Gets the name of the tag.
     * 
     * @return tag name.
     */
    String getName();

    /**
     * The link to which this tag should point in case user clicks on tag.
     * 
     * @param linkId The wicket link component id. The {@link AbstractLink} that is returned by this method should have this
     *            id.
     * @return Initialized link with given linkId as identifier.
     */
    AbstractLink getLink(final String linkId);

    /**
     * The weight is used to compare tags with each other and will determine how large the tag is displayed.
     * 
     * @return weight for tag.
     */
    int getWeight();

}
