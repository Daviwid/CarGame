package GameFiles;

public interface Observable<T> {
    void addObserver(Observer<T> o);
    void removeObserver(Observer<T> o);
}
