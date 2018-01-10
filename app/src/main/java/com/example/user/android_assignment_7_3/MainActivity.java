package com.example.user.android_assignment_7_3;
//Package objects contain version information about the implementation and specification of a Java package.
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    //public is a method and fields can be accessed by the members of any class.
    //class is a collections of objects.
    //created MainActivity and extends with AppCompatActivity which is Parent class.

    ImageView image;
    private ImageView imageView;

    @Override
    //we use override to tells the compiler that the following method overrides a method of its superclass.
    protected void onCreate(Bundle savedInstanceState) {
        //protected can be accessed by within the package and class and subclasses
        //The Void class is an uninstantiable placeholder class to hold a reference to the Class object
        //representing the Java keyword void.
        //The savedInstanceState is a reference to a Bundle object that is passed into the onCreate method of every Android Activity
        // Activities have the ability, under special circumstances, to restore themselves to a previous state using the data stored in this bundle.
        super.onCreate(savedInstanceState);
        //Android class works in same.You are extending the Activity class which have onCreate(Bundle bundle) method in which meaningful code is written
        //So to execute that code in our defined activity. You have to use super.onCreate(bundle)
        setContentView(R.layout.activity_main);
        //R means Resource
        //layout means design
        //main is the xml you have created under res->layout->main.xml
        //Whenever you want to change your current Look of an Activity or when you move from one Activity to another .
        //he other Activity must have a design to show . So we call this method in onCreate and this is the second statement to set
        //the design


        image=(ImageView)findViewById(R.id.imageView);
        imageView=(ImageView)findViewById(R.id.imageView);



        //creating the object of image view
    }
    public void Click(View view)
    //This will be invoked when the click method is used
    {
        //The intent itself, an Intent object, is a passive data structure holding an abstract description of an operation to be
        // performed.
        //Display information about the person

        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_PICK);
        //setAction to perforn the action
        //ACTION_PICK: Pick an item from the data, returning what was selected.
        intent.setType("image/*");
        //setType is for setting the format we want
        startActivityForResult(intent,100);
        // startActivityForResult:Starting another activity doesn't have to be one-way. You can also start another activity and
        // receive a result back
        //Activity is started with requestCode 100
    }

    @Override
    //// Call Back method  to get the Message form other Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 100
        // onActivityResult method that is invoked automatically when second activity returns result.
        if(requestCode==100 && resultCode==RESULT_OK)
        {
            //Uri  Uniform Resource Identifier
            //gets the data using uri
            Uri uri= data.getData();
            try {
                //InputStream:This abstract class is the superclass of all classes representing an input stream of bytes.
                //Android provides Bitmap class to handle images. This can be found under android.graphics.bitmap. There are many ways through which you can instantiate bitmap. We are creating a bitmap of image from the imageView.
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap map= BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(map);
            } catch (FileNotFoundException e) {
                //if image was not found
                e.printStackTrace();
            }
        }
        else
        {

        }
    }
}