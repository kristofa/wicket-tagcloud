wicket-tagcloud
===============

Tag cloud component for [Wicket](http://wicket.apache.org).
Released under MIT license, see LICENSE for details.

The tag cloud component is based on an implementation by Uwe Schaefer, see [here](http://www.codesmell.org/org.codesmell.wicket.tagcloud/). 
I copied and changed the original component in a way that it is compatible with Wicket 6.x (and 1.5.x) and I also changed the way how the 
link for a tag is handled.

As of now a snapshot version is available from the OSS Sonatype snapshot repo: [https://oss.sonatype.org/content/repositories/snapshots/](https://oss.sonatype.org/content/repositories/snapshots/)
No release version are available yet.

Once you configured the snapshot repository you can import the component by updating your pom.xml:

<dependency>
        <groupId>com.github.kristofa</groupId>
        <artifactId>wickettagcloud</artifactId>
        <version>1.0-SNAPSHOT</version>
</dependency>

There is an example application available [here](https://github.com/kristofa/wicket-tagcloud-example).


