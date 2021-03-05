package GameFiles;

/*
Interface for creating the observer pattern between model and view
*/

/**
 * Is implemented by Model to be observed by View.
 * see Observer.java
 */
public interface Observable<T> {
    void addObserver(Observer<T> o);
    void removeObserver(Observer<T> o);
}
