<a href="http://maprice.github.io/PointA/">
![logo](https://raw.github.com/maprice/PointA/gh-pages/images/pointA_logo.png)
</a>


##Overview

The PointA Eclipse Plugin is an Eclipse GUI that allows users to configure the settings for each service providers and save the settings to an xml file which the PointA library can parse the data. It also takes care of dependencies to run the service such as downloading jars to the correct folder, building the file paths and modifying xml files. The goal is to minimize the amount of redundant work that a developer has to do to set up the service, so he/she can focus on developing his/her application. 

##Functional Features

The Eclipse plugin providers the folllowoing functional features to the developers:

* The plugin provides a simple and user-friendly GUI that allows users to enter specific configuration settings for each provider, including disabling/enabling the provider, setting its priority level and the App ID
* The plugin will save the user input data to an xml file, which will be parsed by the PointA library
* The plugin will automatically download all jars fiels that the services needs and place them in the correct directories
* The plugin will build the file path and modify any xml files neeeded to get the servies up and running


##Architecture of the PointA Project 

<a href="http://maprice.github.io/PointA/">
![logo](https://raw.github.com/maprice/PointA/gh-pages/images/PointA_Arch.png)
</a>
**Figure1: Architecture of PointA Project**


From a high level perspective, the PointA project consists of three main components: the PointA library, the PointA Eclipse Plugin and the PointA Android App. As shown in the component diagram, the most crucial component of the  project is the PointA library, and its main purpose is to abstract away all the details of these service providers and provide one simple and user-friendly interface for users use these service providers.  

The PointA Android app is a simple android application that utilizes the services that the PointA library provides (e.g. ads, crash reporting, analytics, etc.). It consists of a listview of all the services and the basic functionalities of each service such as “showAd” for ads or logEvent for analytics. In order for our PointA library to know what services need to be supported and what providers are needed. We need a way for the users to inform the PointA library of the data specific to each application’s configuration. We accomplish this through the Eclipse plugin and a config.xml file, which minimizes the dependencies between the PointA Library and the Eclipse Plugin. 

The Eclipse plugin is an Eclipse extensible application that allows users to input configuration data through a GUI, which the plugin will then write it to an xml file. It also takes care of other dependencies such as downloading jars, setting up build path and modify/generate the appropriate xml files. Therefore the plugin updates the PointA library as shown in the component diagram through user inputs.


<a href="http://maprice.github.io/PointA/">
![logo](https://raw.github.com/maprice/PointA/gh-pages/images/pointA_Physical_Arch.png)
</a>
**Figure2: Physical Architecture of PointA Project**


From the physical perspective, all of the PointA project code will be open sourced and stored in Github repositories. As shown on the physical diagram, in order for a developer to deploy an app using our PointA Library, he/she will pull the Eclipse Plugin code and the PointA Library code from the repository, the user will then link PointA as a library to their application and as well installing the Eclipse Plugin.  

The user then can enter all the configuration data through the provided GUI. Upon clicking “submit” button on GUI, the plugin will automatically download all the jar files, which are 3rd party libraries stored on the service providers’ servers. The plugin will then put the jar files into the appropriate folders and set up any other dependencies PointA Library needs to use these services. The app developer can then access all those services through the PointA library and once the app is deployed on the mobile phone, it will access the 3rd party services stored on these service provider’s servers though the network. 









