# ClipChimp

Dockerized Kotlin Spring Boot application to facilitate Reddit downloads to Youtube uploads

Note that if you want/need to run this locally you will need to set up the following files:

* src/main/resources/application.properties
* src/main/kotlin/com/clipchimp/web/redditConstruct/RedditAuthClient.kt

You will also need to set up a MySQL local DB to handle the repositories. Since this is for personal use I have not
facilitated moving these to a cloud solution.