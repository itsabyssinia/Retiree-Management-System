package cs143.dataaccess;

/**
 * This class is an interface for the data access class.
 *
 */
public interface IDataAccess {
    
    /**
     * A method that saves data of an AVL tree to a file
     *
     * @param index index that will determine the name of the file
     * @param avl an AVL Tree that will be saved to a file
     * @return a boolean variable true if saved, and false otherwise
     */
    boolean saveAvl(int index, SsnAvl avl);
    
    /**
     * A method to retrieve the contents of a file and return an AVL Tree
     *
     * @param index an index that will determine the name of the file
     * @return An AVL Tree if there exists a file or null if there is no file
     */
    SsnAvl retrieveAvl(int index);
}
