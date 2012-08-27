wicket-tagcloud
===============

Tag cloud component for [Wicket](http://wicket.apache.org).

The tag cloud component is based on an implementation by Uwe Schaefer, see [here](http://www.codesmell.org/org.codesmell.wicket.tagcloud/). 
I copied and changed the original component in a way that it is compatible with Wicket 1.5.x and I also changed the way how the 
link for a tag is handled.

The project is setup using Maven so you can do

    mvn eclipse:eclipse

and import the project in Eclipse.

There is an example application included: src/test/java/com/github/kristofa/wickettagcloud/example/Start.java

If you run the example app as a Java app it will start a Jetty server. Next you can access the example app by opening your
browser at http://localhost:8081/ .  The code that builds up the page with the tag cloud is in src/test/java/com/github/kristofa/wickettagcloud/example/HomePage.java

