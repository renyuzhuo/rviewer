package cn.renyuzhuo.viewer;

import java.util.ArrayList;

import io.objectbox.BoxStore;

@SuppressWarnings("unused")
public class ObjectViewBoxManager {

    private static ObjectViewBoxManager mObjectViewBoxManager;

    private ObjectViewBoxManager() {
    }

    public static ObjectViewBoxManager getInstance() {
        if (mObjectViewBoxManager == null) {
            synchronized (ObjectViewBoxManager.class) {
                if (mObjectViewBoxManager == null) {
                    mObjectViewBoxManager = new ObjectViewBoxManager();
                }
            }
        }
        return mObjectViewBoxManager;
    }

    public void init(BoxStore boxStore, ArrayList<Class> classes) {
        mObjectViewBoxManager = null;
    }
}
