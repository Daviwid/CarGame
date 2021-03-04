package GameFiles;

/*
Interface for creating the observer pattern between model and view
*/
public interface Observer<T> {
    void update(T observable);
}
