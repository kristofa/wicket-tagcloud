package com.github.kristofa.wickettagcloud;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * {@link Panel} responsible for displaying a single tag through a {@link TagRenderData} object. It will display the tag name
 * as a link and will change the font size of the link based on the font size specified in given {@link TagRenderData}
 * 
 * @author kristof
 */
class TagLinkPanel extends Panel {

    private static final long serialVersionUID = -1040174512799071388L;
    private static final String TAG_LINK_COMPONENT_ID = "link";
    private static final String TAG_NAME_LABEL_COMPONENT_ID = "tagName";

    private final TagRenderData tagRenderData;

    /**
     * Creates a new Panel responsible for displaying a single Tag.
     * 
     * @param id Component id.
     * @param tagRenderData All the data needed for rendering/displaying the tag.
     */
    public TagLinkPanel(final String id, final TagRenderData tagRenderData) {
        super(id);
        this.tagRenderData = tagRenderData;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onBeforeRender() {
        if (!hasBeenRendered()) {
            final AbstractLink link = tagRenderData.getLink(TAG_LINK_COMPONENT_ID);

            link.add(new Label(TAG_NAME_LABEL_COMPONENT_ID, tagRenderData.getTagName()));
            link.add(AttributeModifier.replace("style", "font-size: " + tagRenderData.getFontSizeInPixels() + "px;"));
            // link.add(new SimpleAttributeModifier("style", "font-size: " + tagRenderData.getFontSizeInPixels() + "px;"));
            add(link);
        }
        super.onBeforeRender();
    }
}
