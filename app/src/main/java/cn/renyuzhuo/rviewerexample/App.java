package cn.renyuzhuo.rviewerexample;

import android.app.Application;

import java.util.ArrayList;

import io.objectbox.BoxStore;

public class App extends Application {

    private BoxStore boxStore;
    private static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        boxStore = MyObjectBox.builder().androidContext(this).build();
        app = this;

        ArrayList<Class> classes = new ArrayList<>();
        classes.add(Note.class);
        ObjectViewBoxManager.getInstance().init(boxStore, classes);
    }

    public static App get() {
        return app;
    }

    public BoxStore boxStore() {
        return boxStore;
    }

}
