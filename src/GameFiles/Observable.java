package GameFiles;

/*
Interface for creating the observer pattern between model and view
*/
public interface Observable<T> {
    void addObserver(Observer<T> o);
    void removeObserver(Observer<T> o);
}
