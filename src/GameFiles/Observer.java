package GameFiles;

/**
 * Interface for creating the observer pattern between model and view.
 * Model creates a hashset of observers
 * Controller adds View as an observer into the hashset
 * Model can then use the function updateObservers() to notify View that variables in model has changed
 * View will then perform its update
 * @author David
 * @version 2.1.3.0
 * @since 2021-03-05 
 */
public interface Observer<T> {
    void update(T observable);
}
