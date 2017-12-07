# RViewer

A library to check the data saved by [ObjectBox](https://github.com/greenrobot/ObjectBox).

打开、查看 ObjectBox 存储的 data.mdb 文件。

## Getting started

In your `build.gradle`:

```gradle
 dependencies {
   debugCompile 'cn.renyuzhuo.rviewer:rviewer:1.0.2'
   releaseCompile 'cn.renyuzhuo.rviewer:rviewer-no-op:1.0.2'
 }
```

In your `Application` class:

```java
public class App extends Application {

    private BoxStore boxStore;
    private static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        boxStore = MyObjectBox.builder().androidContext(this).build();
        app = this;

        ArrayList<Class> classes = new ArrayList<>();
        classes.add(Note.class); // Your Entitys
        ObjectViewBoxManager.getInstance().init(boxStore, classes);
    }
}
```

Notice: **You Should all Add toString() like:**

```
    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
```

All the things is done.

<img width="300" src="https://github.com/renyuzhuo/rviewer/blob/master/gif/operation.gif?raw=true" />

## Thanks 

[ObjectBox-viewer](https://github.com/mreichelt/ObjectBox-viewer)

## License

MIT
