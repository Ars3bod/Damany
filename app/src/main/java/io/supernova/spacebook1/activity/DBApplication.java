package io.supernova.spacebook1.activity;

import android.app.Application;
import android.content.Context;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;


public class DBApplication {

   public static FirebaseDatabase secondaryDatabase;



    public static FirebaseDatabase getSecondaryInstance(Context context)
    {
        if(secondaryDatabase==null)
        {
            // Manually configure Firebase Options
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setApplicationId("1:588094321026:android:1334d6043f5526c2") // Required for Analytics.
                    .setApiKey("AIzaSyATg1JTokjqefoVB9ycMYBaTRb8QWtYPn4") // Required for Auth.
                    .setDatabaseUrl("https://damany-v2.firebaseio.com/") // Required for RTDB.
                    .build();


            // Initialize with secondary app.
            FirebaseApp.initializeApp(context /* Context */, options, "warranty_data");

// Retrieve secondary app.
            FirebaseApp secondary = FirebaseApp.getInstance("warranty_data");
// Get the database for the other app.
            secondaryDatabase = FirebaseDatabase.getInstance(secondary);
        }

        return secondaryDatabase;
    }


}
