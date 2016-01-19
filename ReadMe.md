Sample Camel WMQ with SSL Project
===========================

To build this project use

    mvn install

To run this project with Maven use

    mvn camel:run

For more help see the Apache Camel documentation

    http://camel.apache.org/
    
Install the IBM WMQ JMS OSGi Bundles
------------------------------------

A list of standard OSGi bundles must be installed to run this sample. These bundles, located in the java/lib/OSGi subdirectory of the MQ installation, are part of the WebSphere MQ classes for JMS:
* com.ibm.mq.osgi.directip_7.5.0.2.jar
* com.ibm.msg.client.osgi.commonservices.j2se_7.5.0.2.jar
* com.ibm.msg.client.osgi.jms.prereq_7.5.0.2.jar
* com.ibm.msg.client.osgi.jms_7.5.0.2.jar
* com.ibm.msg.client.osgi.nls_7.5.0.2.jar
* com.ibm.msg.client.osgi.wmq.nls_7.5.0.2.jar
* com.ibm.msg.client.osgi.wmq.prereq_7.5.0.2.jar
* com.ibm.msg.client.osgi.wmq_7.5.0.2.jar

These bundles must be installed with the osgi:install command via the karaf console in the following order:

``osgi:install -s file:[PATH]/com.ibm.msg.client.osgi.jms.prereq_7.5.0.2.jar``

``osgi:install -s file:[PATH]/com.ibm.msg.client.osgi.wmq.prereq_7.5.0.2.jar``

``osgi:install -s file:[PATH]/com.ibm.mq.osgi.directip_7.5.0.2.jar``

``osgi:install -s file:[PATH]/com.ibm.msg.client.osgi.jms_7.5.0.2.jar``

``osgi:install -s file:[PATH]/com.ibm.msg.client.osgi.nls_7.5.0.2.jar``

``osgi:install -s file:[PATH]/com.ibm.msg.client.osgi.wmq.nls_7.5.0.2.jar``

``osgi:install -s file:[PATH]/com.ibm.msg.client.osgi.wmq_7.5.0.2.jar``

``osgi:install -s file:[PATH]/com.ibm.msg.client.osgi.commonservices.j2se_7.5.0.2.jar``

