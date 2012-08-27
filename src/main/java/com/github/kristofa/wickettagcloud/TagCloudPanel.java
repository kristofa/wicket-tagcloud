package com.github.kristofa.wickettagcloud;

import java.util.Collection;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

/**
 * {@link Panel} that displqys tag cloud. Tags are displayed in different font sizes depending on their weight. The higher
 * the weight for a the larger it will be displayed. Each Tag will also be displayed as a link.
 * 
 * @author kristof
 */
public class TagCloudPanel extends Panel {

    private static final long serialVersionUID = -3526836739276546183L;

    /**
     * Creates new instance.
     * 
     * @param id Id for panel.
     * @param tagModel Model that provides a {@link Collection} of {@link TagCloudTag tags}. If the order in which the tags
     *            will be displayed is important you must take care of this in your model. The {@link TagCloudPanel} will
     *            display the tags in the order as being returned by the model.
     * @param minFontSizeInPixels The minimum font size in pixels. The Tag with lowest weight will be displayed in this font
     *            size.
     * @param maxFontSizeInPixels The maximum font size in pixels. The tag with highest weight will be displayed in this font
     *            size.
     * @param title Panel title.
     */
    public TagCloudPanel(final String id, final IModel<Collection<TagCloudTag>> tagModel, final int minFontSizeInPixels,
        final int maxFontSizeInPixels, final String title) {
        super(id);

        // setOutputMarkupId(true);

        add(new Label("title", title));

        final TagRenderDataModel tagRenderDataModel =
            new TagRenderDataModel(tagModel, minFontSizeInPixels, maxFontSizeInPixels);

        add(new ListView<TagRenderData>("tags", tagRenderDataModel) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final ListItem<TagRenderData> item) {
                final TagRenderData tagRenderData = item.getModelObject();

                final TagLinkPanel tagPanel = new TagLinkPanel("tag", tagRenderData);
                item.add(tagPanel);
                // item.setRenderBodyOnly(true);
            }
        });
    }

}
