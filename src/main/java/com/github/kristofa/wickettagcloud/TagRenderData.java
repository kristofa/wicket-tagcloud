package com.github.kristofa.wickettagcloud;

import java.io.Serializable;

import org.apache.wicket.markup.html.link.AbstractLink;

/**
 * Contains all the data required for displaying a {@link TagCloudTag}. It basically wraps a {@link TagCloudTag} and extends
 * it with a font size.
 * 
 * @author kristof
 */
class TagRenderData implements Serializable {

    private static final long serialVersionUID = -9016826573319674759L;

    private final TagCloudTag tag;
    private final int fontSizeInPixels;

    /**
     * Creates a new {@link TagRenderData} object.
     * 
     * @param tag Tag for which this object contains rendering information.
     * @param fontSizeInPixels The font size in pixels for rendering this tag.
     */
    public TagRenderData(final TagCloudTag tag, final int fontSizeInPixels) {
        this.tag = tag;
        this.fontSizeInPixels = fontSizeInPixels;
    }

    /**
     * Gets the tag name.
     * 
     * @return tag name.
     */
    public String getTagName() {
        return tag.getName();
    }

    /**
     * Gets the link for this tag. The user will be forwarded to this link in case he/she clicks on tag.
     * 
     * @param linkId The wicket link component id. The {@link AbstractLink} that is returned should have this id as component
     *            id.
     * @return link for tag.
     */
    public AbstractLink getLink(final String linkId) {
        return tag.getLink(linkId);
    }

    /**
     * Gets font size display property for tag.
     * 
     * @return font size in pixels for tag.
     */
    public int getFontSizeInPixels() {
        return fontSizeInPixels;
    }

}
