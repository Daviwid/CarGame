package GameFiles;

/*
Interface for creating the observer pattern between model and view
*/

/**
 * Is implemented by Model to be observed by View.
 * see Observer.java
 * @author David
 * @version 2.1.3.0
 * @since 2021-03-05 
 */
public interface Observable<T> {
    void addObserver(Observer<T> o);
    void removeObserver(Observer<T> o);
}
