# gympro-app
This is the gym-pro backend application. This includes auth and organization
services.
Below are the instruction to setup the application
## Modes
There are two modes in which the application can be started:

- Backend
- Full

*BackendOnly* : To start the application in BackedOnly mode, follow the below instructions:

- Prerequisites : install java, set JAVA_HOME, install docker
- export ENCRYPT_KEY=*get the salt from any dev*
- run `docker-compose up`
- run `SPRING_PROFILES_ACTIVE=dev ./gradlew clean bootRun`

*Full* : The *Full* mode clones the `ui` code from the ui repository and deploys it with the
 backend server. It puts the `ui` code inside the `gym-client` folder. It checks out the `angular_ui`branch
   of the ui repository(you can change this setting in the `gradle.proerties` file). 
   The final ui built code is put inside `src/main/resources/static` folder. To start the application in *Full* mode follow
   the below instructions:

- Prerequisites : install java, set JAVA_HOME, install docker, install node
- export ENCRYPT_KEY=*get the salt from any dev*
- run `docker-compose up`
- configure all the properties in `gradle.prpeties` file. You need to add your git username/password to pull the ui
code. DO NOT PUSH `gradle.properties` having your cedentials. Always checkout the file before commiting any change.
- run `./gradlew clone` : This will clone the ui code and put it inside `gym-client` folder. Don't run it twice if
you don't make UI changes.
- run `export SPRING_PROFILES_ACTIVE=dev`
- run `./gradlew startFull` : This will run `npm install`, `npm run-script build` and copy the built resources
inside `src/main/resources/static` folder and start the application.
- now visit https://localhost:18443