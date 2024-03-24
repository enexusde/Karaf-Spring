package de.e_nexus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

import javax.inject.Named;

@Named
public class BasicKarafConfig implements FrameworkConfigContributor {

    @Override
    public void contributeKarafConfiguration(final Properties config,
            final Properties startup, final Properties features,
            final Properties paxurl, final Properties users) {
        Collection<String> jre9modules = new ArrayList<>();
        jre9modules.add("${jre-base}");
        jre9modules.add("java.lang.module");
        jre9modules.add("java.net.spi");
        addJavaXModules(jre9modules);
        jre9modules.add("javafx.animation");
        jre9modules.add("javafx.application");
        jre9modules.add("javafx.beans");
        jre9modules.add("javafx.beans.binding");
        jre9modules.add("javafx.beans.property");
        jre9modules.add("javafx.beans.property.adapter");
        jre9modules.add("javafx.beans.value");
        jre9modules.add("javafx.collections");
        jre9modules.add("javafx.collections.transformation");
        jre9modules.add("javafx.concurrent");
        jre9modules.add("javafx.css");
        jre9modules.add("javafx.embed.swing");
        jre9modules.add("javafx.embed.swt");
        jre9modules.add("javafx.event");
        jre9modules.add("javafx.fxml");
        jre9modules.add("javafx.geometry");
        jre9modules.add("javafx.print");
        jre9modules.add("javafx.scene");
        jre9modules.add("javafx.scene.canvas");
        jre9modules.add("javafx.scene.chart");
        jre9modules.add("javafx.scene.control");
        jre9modules.add("javafx.scene.control.cell");
        jre9modules.add("javafx.scene.effect");
        jre9modules.add("javafx.scene.image");
        jre9modules.add("javafx.scene.input");
        jre9modules.add("javafx.scene.layout");
        jre9modules.add("javafx.scene.media");
        jre9modules.add("javafx.scene.paint");
        jre9modules.add("javafx.scene.shape");
        jre9modules.add("javafx.scene.text");
        jre9modules.add("javafx.scene.transform");
        jre9modules.add("javafx.scene.web");
        jre9modules.add("javafx.stage");
        jre9modules.add("javafx.util");
        jre9modules.add("javafx.util.converter");
        jre9modules.add("netscape.javascript");
        jre9modules.add("org.ietf.jgss");
        jre9modules.add("org.w3c.dom");
        jre9modules.add("org.w3c.dom.bootstrap");
        jre9modules.add("org.w3c.dom.css");
        jre9modules.add("org.w3c.dom.events");
        jre9modules.add("org.w3c.dom.html");
        jre9modules.add("org.w3c.dom.ls");
        jre9modules.add("org.w3c.dom.ranges");
        jre9modules.add("org.w3c.dom.stylesheets");
        jre9modules.add("org.w3c.dom.traversal");
        jre9modules.add("org.w3c.dom.views");
        jre9modules.add("org.w3c.dom.xpath");
        jre9modules.add("org.xml.sax");
        jre9modules.add("org.xml.sax.ext");
        jre9modules.add("org.xml.sax.helpers");
        jre9modules.add("com.sun.security.sasl");
        jre9modules.add("com.sun.security.sasl.digest");
        jre9modules.add("com.sun.security.sasl.ntlm");
        jre9modules.add("com.sun.security.sasl.util");

        config.setProperty("jre-9", concatAll(jre9modules));
        for (int jdkVer = 10; jdkVer < 21; jdkVer++) {
            config.setProperty("jre-" + jdkVer, "${jre-9}");
        }

        Collection<String> baseModules = getBaseModules();
        baseModules.add("jdk.net");

        config.setProperty("jre-base", concatAll(baseModules));
        config.setProperty("eecap-18", join("; ", "osgi.ee",
                "osgi.ee=\"OSGi/Minimum\"",
                "version:List<Version>=\"1.0,1.1,1.2\", osgi.ee",
                "osgi.ee=\"JavaSE\"",
                "version:List<Version>=\"1.0,1.1,1.2,1.3,1.4,1.5,1.6,1.7,1.8,9.0,10.0,11.0,13.0,14.0,15.0,16.0,17.0,18.0\", osgi.ee",
                "osgi.ee=\"JRE\"", "version:List<Version>=\"1.0,1.1\", osgi.ee",
                "osgi.ee=\"JavaSE/compact1\"",
                "version:List<Version>=\"1.8,9.0,10.0,11.0,13.0,14.0,15.0,16.0,17.0,18.0\", osgi.ee",
                "osgi.ee=\"JavaSE/compact2\"",
                "version:List<Version>=\"1.8,9.0,10.0,11.0,13.0,14.0,15.0,16.0,17.0,18.0\", osgi.ee",
                "osgi.ee=\"JavaSE/compact3\"",
                "version:List<Version>=\"1.8,9.0,10.0,11.0,13.0,14.0,15.0,16.0,17.0,18.0\""));
        config.setProperty("eecap-1.8", join("; ", "osgi.ee",
                "osgi.ee=\"OSGi/Minimum\"",
                "version:List<Version>=\"1.0,1.1,1.2\", osgi.ee",
                "osgi.ee=\"JavaSE\"",
                "version:List<Version>=\"1.0,1.1,1.2,1.3,1.4,1.5,1.6,1.7,1.8\", osgi.ee",
                "osgi.ee=\"JRE\"", "version:List<Version>=\"1.0,1.1\", osgi.ee",
                "osgi.ee=\"JavaSE/compact1\"",
                "version:List<Version>=\"1.8\", osgi.ee",
                "osgi.ee=\"JavaSE/compact2\"",
                "version:List<Version>=\"1.8\", osgi.ee",
                "osgi.ee=\"JavaSE/compact3\"",
                "version:List<Version>=\"1.8\""));
        config.setProperty("eecap-19", join("; ", "osgi.ee",
                "osgi.ee=\"OSGi/Minimum\"",
                "version:List<Version>=\"1.0,1.1,1.2\", osgi.ee",
                "osgi.ee=\"JavaSE\"",
                "version:List<Version>=\"1.0,1.1,1.2,1.3,1.4,1.5,1.6,1.7,1.8,9.0,10.0,11.0,13.0,14.0,15.0,16.0,17.0,18.0,19.0\", osgi.ee",
                "osgi.ee=\"JRE\"", "version:List<Version>=\"1.0,1.1\", osgi.ee",
                "osgi.ee=\"JavaSE/compact1\"",
                "version:List<Version>=\"1.8,9.0,10.0,11.0,13.0,14.0,15.0,16.0,17.0,18.0,19.0\", osgi.ee",
                "osgi.ee=\"JavaSE/compact2\"",
                "version:List<Version>=\"1.8,9.0,10.0,11.0,13.0,14.0,15.0,16.0,17.0,18.0,19.0\", osgi.ee",
                "osgi.ee=\"JavaSE/compact3\"",
                "version:List<Version>=\"1.8,9.0,10.0,11.0,13.0,14.0,15.0,16.0,17.0,18.0,19.0\""));
        config.setProperty("eecap-16", join("; ", "osgi.ee",
                "osgi.ee=\"OSGi/Minimum\"",
                "version:List<Version>=\"1.0,1.1,1.2\", osgi.ee",
                "osgi.ee=\"JavaSE\"",
                "version:List<Version>=\"1.0,1.1,1.2,1.3,1.4,1.5,1.6,1.7,1.8,9.0,10.0,11.0,13.0,14.0,15.0,16.0\", osgi.ee",
                "osgi.ee=\"JRE\"", "version:List<Version>=\"1.0,1.1\", osgi.ee",
                "osgi.ee=\"JavaSE/compact1\"",
                "version:List<Version>=\"1.8,9.0,10.0,11.0,13.0,14.0,15.0,16.0\", osgi.ee",
                "osgi.ee=\"JavaSE/compact2\"",
                "version:List<Version>=\"1.8,9.0,10.0,11.0,13.0,14.0,15.0,16.0\", osgi.ee",
                "osgi.ee=\"JavaSE/compact3\"",
                "version:List<Version>=\"1.8,9.0,10.0,11.0,13.0,14.0,15.0,16.0\""));
        config.setProperty("eecap-17", join("; ", "osgi.ee",
                "osgi.ee=\"OSGi/Minimum\"",
                "version:List<Version>=\"1.0,1.1,1.2\", osgi.ee",
                "osgi.ee=\"JavaSE\"",
                "version:List<Version>=\"1.0,1.1,1.2,1.3,1.4,1.5,1.6,1.7,1.8,9.0,10.0,11.0,13.0,14.0,15.0,16.0,17.0\", osgi.ee",
                "osgi.ee=\"JRE\"", "version:List<Version>=\"1.0,1.1\", osgi.ee",
                "osgi.ee=\"JavaSE/compact1\"",
                "version:List<Version>=\"1.8,9.0,10.0,11.0,13.0,14.0,15.0,16.0,17.0\", osgi.ee",
                "osgi.ee=\"JavaSE/compact2\"",
                "version:List<Version>=\"1.8,9.0,10.0,11.0,13.0,14.0,15.0,16.0,17.0\", osgi.ee",
                "osgi.ee=\"JavaSE/compact3\"",
                "version:List<Version>=\"1.8,9.0,10.0,11.0,13.0,14.0,15.0,16.0,17.0\""));
        config.setProperty("ordg.osgi.framework.bootdelegation",
                "com.sun.*, javax.transaction, javax.transaction.xa, javax.xml.crypto, javax.xml.crypto.*, javax.security.cert, jdk.nashorn.*, sun.*, jdk.internal.reflect, jdk.internal.reflect.*, org.apache.karaf.jaas.boot, org.apache.karaf.jaas.boot.principal");
        config.setProperty("eecap-9", join("; ", "osgi.ee",
                "osgi.ee=\"OSGi/Minimum\"",
                "version:List<Version>=\"1.0,1.1,1.2\", osgi.ee",
                "osgi.ee=\"JavaSE\"",
                "version:List<Version>=\"1.0,1.1,1.2,1.3,1.4,1.5,1.6,1.7,1.8,9.0\", osgi.ee",
                "osgi.ee=\"JRE\"", "version:List<Version>=\"1.0,1.1\", osgi.ee",
                "osgi.ee=\"JavaSE/compact1\"",
                "version:List<Version>=\"1.8,9.0\", osgi.ee",
                "osgi.ee=\"JavaSE/compact2\"",
                "version:List<Version>=\"1.8,9.0\", osgi.ee",
                "osgi.ee=\"JavaSE/compact3\"",
                "version:List<Version>=\"1.8,9.0\""));
        config.setProperty("eecap-21", join("; ", "osgi.ee",
                "osgi.ee=\"OSGi/Minimum\"",
                "version:List<Version>=\"1.0,1.1,1.2\", osgi.ee",
                "osgi.ee=\"JavaSE\"",
                "version:List<Version>=\"1.0,1.1,1.2,1.3,1.4,1.5,1.6,1.7,1.8,9.0,10.0,11.0,13.0,14.0,15.0,16.0,17.0,18.0,19.0,20.0,21.0\", osgi.ee",
                "osgi.ee=\"JRE\"", "version:List<Version>=\"1.0,1.1\", osgi.ee",
                "osgi.ee=\"JavaSE/compact1\"",
                "version:List<Version>=\"1.8,9.0,10.0,11.0,13.0,14.0,15.0,16.0,17.0,18.0,19.0,20.0,21.0\", osgi.ee",
                "osgi.ee=\"JavaSE/compact2\"",
                "version:List<Version>=\"1.8,9.0,10.0,11.0,13.0,14.0,15.0,16.0,17.0,18.0,19.0,20.0,21.0\", osgi.ee",
                "osgi.ee=\"JavaSE/compact3\"",
                "version:List<Version>=\"1.8,9.0,10.0,11.0,13.0,14.0,15.0,16.0,17.0,18.0,19.0,20.0,21.0\""));
        config.setProperty("eecap-20", join("; ", "osgi.ee",
                "osgi.ee=\"OSGi/Minimum\"",
                "version:List<Version>=\"1.0,1.1,1.2\", osgi.ee",
                "osgi.ee=\"JavaSE\"",
                "version:List<Version>=\"1.0,1.1,1.2,1.3,1.4,1.5,1.6,1.7,1.8,9.0,10.0,11.0,13.0,14.0,15.0,16.0,17.0,18.0,19.0,20.0\", osgi.ee",
                "osgi.ee=\"JRE\"", "version:List<Version>=\"1.0,1.1\", osgi.ee",
                "osgi.ee=\"JavaSE/compact1\"",
                "version:List<Version>=\"1.8,9.0,10.0,11.0,13.0,14.0,15.0,16.0,17.0,18.0,19.0,20.0\", osgi.ee",
                "osgi.ee=\"JavaSE/compact2\"",
                "version:List<Version>=\"1.8,9.0,10.0,11.0,13.0,14.0,15.0,16.0,17.0,18.0,19.0,20.0\", osgi.ee",
                "osgi.ee=\"JavaSE/compact3\"",
                "version:List<Version>=\"1.8,9.0,10.0,11.0,13.0,14.0,15.0,16.0,17.0,18.0,19.0,20.0\""));
        config.setProperty("felix-capabilities",
                "osgi.service;objectClass:List<String>=org.osgi.service.packageadmin.PackageAdmin, osgi.service;objectClass:List<String>=org.osgi.service.resolver.Resolver, osgi.service;objectClass:List<String>=org.osgi.service.startlevel.StartLevel");
        config.setProperty("org.osgi.framework.system.packages",
                "org.osgi.dto;version=\"1.1\",org.osgi.resource;version=\"1.0\",org.osgi.resource.dto;version=\"1.0\";uses:=\"org.osgi.dto\",org.osgi.framework;version=\"1.10\",org.osgi.framework.dto;version=\"1.10\";uses:=\"org.osgi.dto\",org.osgi.framework.hooks.bundle;version=\"1.1\";uses:=\"org.osgi.framework\",org.osgi.framework.hooks.resolver;version=\"1.0\";uses:=\"org.osgi.framework.wiring\",org.osgi.framework.hooks.service;version=\"1.1\";uses:=\"org.osgi.framework\",org.osgi.framework.hooks.weaving;version=\"1.1\";uses:=\"org.osgi.framework.wiring\",org.osgi.framework.launch;version=\"1.2\";uses:=\"org.osgi.framework\",org.osgi.framework.namespace;version=\"1.1\";uses:=\"org.osgi.resource\",org.osgi.framework.startlevel;version=\"1.0\";uses:=\"org.osgi.framework\",org.osgi.framework.startlevel.dto;version=\"1.0\";uses:=\"org.osgi.dto\",org.osgi.framework.wiring;version=\"1.2\";uses:=\"org.osgi.framework,org.osgi.resource\",org.osgi.framework.wiring.dto;version=\"1.3\";uses:=\"org.osgi.dto,org.osgi.resource.dto\",org.osgi.service.condpermadmin;version=\"1.1.1\";uses:=\"org.osgi.framework,org.osgi.service.permissionadmin\",org.osgi.service.packageadmin;version=\"1.2\";uses:=\"org.osgi.framework\",org.osgi.service.permissionadmin;version=\"1.2\",org.osgi.service.resolver;version=\"1.1\";uses:=\"org.osgi.resource\",org.osgi.service.startlevel;version=\"1.1\";uses:=\"org.osgi.framework\",org.osgi.service.url;version=\"1.0\",org.osgi.util.tracker;version=\"1.5.2\";uses:=\"org.osgi.framework\",org.apache.karaf.version;version=\"4.4.5\",org.apache.karaf.jaas.boot.principal;uses:=javax.security.auth;version=\"4.4.5\",org.apache.karaf.jaas.boot;uses:=\"javax.security.auth,javax.security.auth.callback,javax.security.auth.login,javax.security.auth.spi,org.osgi.framework\";version=\"4.4.5\",org.apache.karaf.info;version=\"4.4.5\",${jre-${java.specification.version}}");
        config.setProperty("eecap-14", join("; ", "osgi.ee",
                "osgi.ee=\"OSGi/Minimum\"",
                "version:List<Version>=\"1.0,1.1,1.2\", osgi.ee",
                "osgi.ee=\"JavaSE\"",
                "version:List<Version>=\"1.0,1.1,1.2,1.3,1.4,1.5,1.6,1.7,1.8,9.0,10.0,11.0,13.0,14.0\", osgi.ee",
                "osgi.ee=\"JRE\"", "version:List<Version>=\"1.0,1.1\", osgi.ee",
                "osgi.ee=\"JavaSE/compact1\"",
                "version:List<Version>=\"1.8,9.0,10.0,11.0,13.0,14.0\", osgi.ee",
                "osgi.ee=\"JavaSE/compact2\"",
                "version:List<Version>=\"1.8,9.0,10.0,11.0,13.0,14.0\", osgi.ee",
                "osgi.ee=\"JavaSE/compact3\"",
                "version:List<Version>=\"1.8,9.0,10.0,11.0,13.0,14.0\""));
        config.setProperty("eecap-15", join("; ", "osgi.ee",
                "osgi.ee=\"OSGi/Minimum\"",
                "version:List<Version>=\"1.0,1.1,1.2\", osgi.ee",
                "osgi.ee=\"JavaSE\"",
                "version:List<Version>=\"1.0,1.1,1.2,1.3,1.4,1.5,1.6,1.7,1.8,9.0,10.0,11.0,13.0,14.0,15.0\", osgi.ee",
                "osgi.ee=\"JRE\"", "version:List<Version>=\"1.0,1.1\", osgi.ee",
                "osgi.ee=\"JavaSE/compact1\"",
                "version:List<Version>=\"1.8,9.0,10.0,11.0,13.0,14.0,15.0\", osgi.ee",
                "osgi.ee=\"JavaSE/compact2\"",
                "version:List<Version>=\"1.8,9.0,10.0,11.0,13.0,14.0,15.0\", osgi.ee",
                "osgi.ee=\"JavaSE/compact3\"",
                "version:List<Version>=\"1.8,9.0,10.0,11.0,13.0,14.0,15.0\""));
        config.setProperty("org.osgi.framework.system.packages.extra",
                "org.apache.karaf.branding, sun.misc, com.sun.jmx.remote.protocol, com.sun.jmx.remote.protocol.jmxmp, org.apache.karaf.jaas.boot;uses:=\"javax.security.auth,javax.security.auth.callback,javax.security.auth.login,javax.security.auth.spi,org.osgi.framework\";version=4.4.5, org.apache.karaf.jaas.boot.principal;uses:=javax.security.auth;version=4.4.5, org.apache.karaf.diagnostic.core;uses:=org.osgi.framework;version=4.4.5, org.apache.karaf.diagnostic.core.common;uses:=org.apache.karaf.diagnostic.core;version=4.4.5");
        config.setProperty("eecap-13", join("; ", "osgi.ee",
                "osgi.ee=\"OSGi/Minimum\"",
                "version:List<Version>=\"1.0,1.1,1.2\", osgi.ee",
                "osgi.ee=\"JavaSE\"",
                "version:List<Version>=\"1.0,1.1,1.2,1.3,1.4,1.5,1.6,1.7,1.8,9.0,10.0,11.0,13.0\", osgi.ee",
                "osgi.ee=\"JRE\"", "version:List<Version>=\"1.0,1.1\", osgi.ee",
                "osgi.ee=\"JavaSE/compact1\"",
                "version:List<Version>=\"1.8,9.0,10.0,11.0,13.0\", osgi.ee",
                "osgi.ee=\"JavaSE/compact2\"",
                "version:List<Version>=\"1.8,9.0,10.0,11.0,13.0\", osgi.ee",
                "osgi.ee=\"JavaSE/compact3\"",
                "version:List<Version>=\"1.8,9.0,10.0,11.0,13.0\""));
        config.setProperty("eecap-10", join("; ", "osgi.ee",
                "osgi.ee=\"OSGi/Minimum\"",
                "version:List<Version>=\"1.0,1.1,1.2\", osgi.ee",
                "osgi.ee=\"JavaSE\"",
                "version:List<Version>=\"1.0,1.1,1.2,1.3,1.4,1.5,1.6,1.7,1.8,9.0,10.0\", osgi.ee",
                "osgi.ee=\"JRE\"", "version:List<Version>=\"1.0,1.1\", osgi.ee",
                "osgi.ee=\"JavaSE/compact1\"",
                "version:List<Version>=\"1.8,9.0,10.0\", osgi.ee",
                "osgi.ee=\"JavaSE/compact2\"",
                "version:List<Version>=\"1.8,9.0,10.0\", osgi.ee",
                "osgi.ee=\"JavaSE/compact3\"",
                "version:List<Version>=\"1.8,9.0,10.0\""));
        config.setProperty("eecap-11", join("; ", "osgi.ee",
                "osgi.ee=\"OSGi/Minimum\"",
                "version:List<Version>=\"1.0,1.1,1.2\", osgi.ee",
                "osgi.ee=\"JavaSE\"",
                "version:List<Version>=\"1.0,1.1,1.2,1.3,1.4,1.5,1.6,1.7,1.8,9.0,10.0,11.0\", osgi.ee",
                "osgi.ee=\"JRE\"", "version:List<Version>=\"1.0,1.1\", osgi.ee",
                "osgi.ee=\"JavaSE/compact1\"",
                "version:List<Version>=\"1.8,9.0,10.0,11.0\", osgi.ee",
                "osgi.ee=\"JavaSE/compact2\"",
                "version:List<Version>=\"1.8,9.0,10.0,11.0\", osgi.ee",
                "osgi.ee=\"JavaSE/compact3\"",
                "version:List<Version>=\"1.8,9.0,10.0,11.0\""));

        config.setProperty("karaf.framework", "felix");
        config.setProperty("karaf.framework.felix",
                "mvn:org.apache.felix/org.apache.felix.framework/7.0.5");
        config.setProperty("org.ops4j.pax.logging.skipJUL", "true");
        config.setProperty("org.ops4j.pax.logging.DefaultServiceLog.level",
                "INFO");

        config.setProperty("karaf.startLocalConsole", "true");
        config.setProperty("karaf.startRemoteShell", "false");
        config.setProperty("karaf.startlevel.bundle", "80");
        config.setProperty("org.osgi.framework.startlevel.beginning", "100");
        config.setProperty("org.osgi.framework.bundle.parent", "framework");

        // config.setProperty("org.osgi.framework.system.packages.extra",
        // "de.e_nexus;version=1");
    }

    private Collection<String> getBaseModules() {
        Collection<String> baseModules = new ArrayList<>();
        baseModules.add("java.applet");
        baseModules.add("java.awt");
        baseModules.add("java.awt.color");
        baseModules.add("java.awt.datatransfer");
        baseModules.add("java.awt.dnd");
        baseModules.add("java.awt.event");
        baseModules.add("java.awt.font");
        baseModules.add("java.awt.geom");
        baseModules.add("java.awt.im");
        baseModules.add("java.awt.im.spi");
        baseModules.add("java.awt.image");
        baseModules.add("java.awt.image.renderable");
        baseModules.add("java.awt.print");
        baseModules.add("java.beans");
        baseModules.add("java.beans.beancontext");
        baseModules.add("java.io");
        baseModules.add("java.lang");
        baseModules.add("java.lang.annotation");
        baseModules.add("java.lang.constant");
        baseModules.add("java.lang.instrument");
        baseModules.add("java.lang.invoke");
        baseModules.add("java.lang.management");
        baseModules.add("java.lang.ref");
        baseModules.add("java.lang.reflect");
        baseModules.add("java.lang.runtime");
        baseModules.add("java.math");
        baseModules.add("java.net");
        baseModules.add("java.net.http");
        baseModules.add("java.nio");
        baseModules.add("java.nio.channels");
        baseModules.add("java.nio.channels.spi");
        baseModules.add("java.nio.charset");
        baseModules.add("java.nio.charset.spi");
        baseModules.add("java.nio.file");
        baseModules.add("java.nio.file.attribute");
        baseModules.add("java.rmi");
        baseModules.add("java.rmi.activation");
        baseModules.add("java.rmi.dgc");
        baseModules.add("java.rmi.registry");
        baseModules.add("java.rmi.server");
        baseModules.add("java.security");
        baseModules.add("java.security.acl");
        baseModules.add("java.security.cert");
        baseModules.add("java.security.interfaces");
        baseModules.add("java.security.spec");
        baseModules.add("java.sql");
        baseModules.add("java.text");
        baseModules.add("java.text.spi");
        baseModules.add("java.time");
        baseModules.add("java.time.chrono");
        baseModules.add("java.time.format");
        baseModules.add("java.time.temporal");
        baseModules.add("java.time.zone");
        baseModules.add("java.util");
        baseModules.add("java.util.concurrent");
        baseModules.add("java.util.concurrent.atomic");
        baseModules.add("java.util.concurrent.locks");
        baseModules.add("java.util.function");
        baseModules.add("java.util.random");
        baseModules.add("java.util.jar");
        baseModules.add("java.util.logging");
        baseModules.add("java.util.prefs");
        baseModules.add("java.util.regex");
        baseModules.add("java.util.spi");
        baseModules.add("java.util.stream");
        baseModules.add("java.util.zip");
        return baseModules;
    }

    private String concatAll(final Collection<String> mods) {
        StringBuilder sb = new StringBuilder();
        for (String mod : mods) {
            sb.append(", ");
            sb.append(mod);
        }
        return sb.substring(2);
    }

    private static void addJavaXModules(final Collection<String> jre9modules) {
        jre9modules.add("javax.accessibility");
        jre9modules.add("javax.activity");
        jre9modules.add("javax.annotation;version=\"1.3\"");
        jre9modules.add("javax.annotation.processing;version=\"1.0\"");
        jre9modules.add("javax.activation;version=\"1.2.1\"");
        jre9modules.add("javax.crypto");
        jre9modules.add("javax.crypto.interfaces");
        jre9modules.add("javax.crypto.spec");
        jre9modules.add("javax.imageio");
        jre9modules.add("javax.imageio.event");
        jre9modules.add("javax.imageio.metadata");
        jre9modules.add("javax.imageio.plugins.bmp");
        jre9modules.add("javax.imageio.plugins.jpeg");
        jre9modules.add("javax.imageio.spi");
        jre9modules.add("javax.imageio.stream");
        jre9modules.add("javax.lang.model");
        jre9modules.add("javax.lang.model.element");
        jre9modules.add("javax.lang.model.type");
        jre9modules.add("javax.lang.model.util");
        jre9modules.add("javax.management");
        jre9modules.add("javax.management.loading");
        jre9modules.add("javax.management.modelmbean");
        jre9modules.add("javax.management.monitor");
        jre9modules.add("javax.management.openmbean");
        jre9modules.add("javax.management.relation");
        jre9modules.add("javax.management.remote");
        jre9modules.add("javax.management.remote.rmi");
        jre9modules.add("javax.management.timer");
        jre9modules.add("javax.naming");
        jre9modules.add("javax.naming.directory");
        jre9modules.add("javax.naming.event");
        jre9modules.add("javax.naming.ldap");
        jre9modules.add("javax.naming.spi");
        jre9modules.add("javax.net");
        jre9modules.add("javax.net.ssl");
        jre9modules.add("javax.print");
        jre9modules.add("javax.print.attribute");
        jre9modules.add("javax.print.attribute.standard");
        jre9modules.add("javax.print.event");
        jre9modules.add("javax.rmi");
        jre9modules.add("javax.rmi.ssl");
        jre9modules.add("javax.script");
        jre9modules.add("javax.security.auth");
        jre9modules.add("javax.security.auth.callback");
        jre9modules.add("javax.security.auth.kerberos");
        jre9modules.add("javax.security.auth.login");
        jre9modules.add("javax.security.auth.spi");
        jre9modules.add("javax.security.auth.x500");
        jre9modules.add("javax.security.cert");
        jre9modules.add("javax.security.sasl");
        jre9modules.add("javax.sound.midi");
        jre9modules.add("javax.sound.midi.spi");
        jre9modules.add("javax.sound.sampled");
        jre9modules.add("javax.sound.sampled.spi");
        jre9modules.add("javax.sql");
        jre9modules.add("javax.sql.rowset");
        jre9modules.add("javax.sql.rowset.serial");
        jre9modules.add("javax.sql.rowset.spi");
        jre9modules.add("javax.swing");
        jre9modules.add("javax.swing.border");
        jre9modules.add("javax.swing.colorchooser");
        jre9modules.add("javax.swing.event");
        jre9modules.add("javax.swing.filechooser");
        jre9modules.add("javax.swing.plaf");
        jre9modules.add("javax.swing.plaf.basic");
        jre9modules.add("javax.swing.plaf.metal");
        jre9modules.add("javax.swing.plaf.multi");
        jre9modules.add("javax.swing.plaf.synth");
        jre9modules.add("javax.swing.table");
        jre9modules.add("javax.swing.text");
        jre9modules.add("javax.swing.text.html");
        jre9modules.add("javax.swing.text.html.parser");
        jre9modules.add("javax.swing.text.rtf");
        jre9modules.add("javax.swing.tree");
        jre9modules.add("javax.swing.undo");
        jre9modules.add("javax.tools");
        jre9modules.add(
                "javax.transaction.xa;version=\"1.1\";partial=true;mandatory:=partial");
        jre9modules.add("javax.transaction.xa;version=\"1.1\"");
        jre9modules.add("javax.transaction.xa;version=\"1.2\"");
        jre9modules.add("javax.transaction.xa;version=\"1.3\"");
        jre9modules.add("javax.xml");
        jre9modules.add("javax.xml.bind;version=\"2.3.0\"");
        jre9modules.add("javax.xml.bind.annotation;version=\"2.3.0\"");
        jre9modules.add("javax.xml.bind.annotation.adapters;version=\"2.3.0\"");
        jre9modules.add("javax.xml.bind.attachment;version=\"2.3.0\"");
        jre9modules.add("javax.xml.bind.helpers;version=\"2.3.0\"");
        jre9modules.add("javax.xml.bind.util;version=\"2.3.0\"");
        jre9modules.add("javax.xml.catalog");
        jre9modules.add("javax.xml.crypto");
        jre9modules.add("javax.xml.crypto.dom");
        jre9modules.add("javax.xml.crypto.dsig");
        jre9modules.add("javax.xml.crypto.dsig.dom");
        jre9modules.add("javax.xml.crypto.dsig.keyinfo");
        jre9modules.add("javax.xml.crypto.dsig.spec");
        jre9modules.add("javax.xml.datatype");
        jre9modules.add("javax.xml.namespace");
        jre9modules.add("javax.xml.parsers");
        jre9modules.add("javax.xml.stream;version=\"1.2\"");
        jre9modules.add("javax.xml.stream.events;version=\"1.2\"");
        jre9modules.add("javax.xml.stream.util;version=\"1.2\"");
        jre9modules.add("javax.xml.transform");
        jre9modules.add("javax.xml.transform.dom");
        jre9modules.add("javax.xml.transform.sax");
        jre9modules.add("javax.xml.transform.stax");
        jre9modules.add("javax.xml.transform.stream");
        jre9modules.add("javax.xml.validation");
        jre9modules.add("javax.xml.xpath");
    }

}
