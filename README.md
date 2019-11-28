# Jmz Place

This is just something I put together real quick to grab some nearby places.  It is sorted by distance from current location


# Usage

Just add JitPack and library to your build.gradle
```
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}

dependencies {
    implementation 'com.github.JmzTaylor:JmzPlaces:1.0'
}
```

You will need to ask for location permission before running anything or it will always return ``` null ``` .
 
Implement the listener
```java
public class MainActivity extends AppCompatActivity implements OnPlacesListener {

@Override  
public void onPlacesCompleted(ArrayList<MyPlaces> myPlaces) {  
    // myPlaces variable has the data. 
}
```
Then just initialize the classes and do what you want with the data.
You will need to replace API_KEY with your Google Places API key.
```java
new JmzPlaces(this,"restaurant", API_KEY);

new JmzPlaces.AsyncTaskRunner(this).execute();
```