package cn.renyuzhuo.objectbox.viewer;

import java.util.ArrayList;

import io.objectbox.Box;
import io.objectbox.BoxStore;

public class ObjectViewBoxManager {

    private static ObjectViewBoxManager mObjectViewBoxManager;
    private BoxStore mBoxStore;
    private ArrayList<Box> boxes;

    private ArrayList<Class> classes;

    private ObjectViewBoxManager() {
    }

    public static ObjectViewBoxManager getInstance() {
        if (mObjectViewBoxManager == null) {
            synchronized (ObjectViewBoxManager.class) {
                if (mObjectViewBoxManager == null) {
                    mObjectViewBoxManager = new ObjectViewBoxManager();
                    mObjectViewBoxManager.boxes = new ArrayList<>();
                }
            }
        }
        return mObjectViewBoxManager;
    }

    public void init(BoxStore boxStore, ArrayList<Class> classes) {
        this.mBoxStore = boxStore;
        for (int i = 0; i < classes.size(); i++) {
            this.classes = classes;
            Box box = mBoxStore.boxFor(classes.get(i));
            boxes.add(box);
        }
    }

    public BoxStore getBoxStore() {
        return mBoxStore;
    }

    public ArrayList<Box> getBoxes() {
        if (boxes == null) {
            return new ArrayList<>();
        }
        return boxes;
    }

    public ArrayList<Class> getClasses() {
        if (classes == null) {
            classes = new ArrayList<>();
        }
        return classes;
    }
}
