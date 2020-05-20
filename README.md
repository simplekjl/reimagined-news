# reimagined-news
Let's read some news from  http://newsapi.org

# Libraries used 
- Koin for Dependency Injection 
- Retrofit && Adapter for RxJava
- Picasso 
- RxJava 2
- RxKotlin 

# Notes to run the project: 

Please add the following variable into your local 'gradle.properties' -> 'NEWS_API_KEY' and assing your API_KEY.
In case you do not want to add it in the local properties, you can also change the value directly inside 'build.gradle' file.

# Time spend 
8 hrs

# Reminding tasks
- show the articles in a new fragment with the given values
- add storage/cache mechanism 
- Unit Test for mapper as well as repository
- UI test
- Handle more Error scenarios based on the 'response.status' or the 'http error code' 

# Architecture 

- It follows very close the clean architecture using interactors to communicate the presentation layer with the domain and repositories to comunicate with the data layer.

- The project can be still improved if necessary to improve scalability

- MVVM and MVI using sealed classes for view states 





