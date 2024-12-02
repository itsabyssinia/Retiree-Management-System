package cs143.dataaccess;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * A class that saves the data of Retirees via serialization
 *
 */
public class DataAccessFileImpl implements IDataAccess {

    /**
     * Save the entire AVL trees into another file as serialized objects
     *
     * @param index the index/hash number in the hash table to be saved
     * @param avl the entire AVL tree
     * @return true if successfully saved and false if not
     */
    @Override
    public boolean saveAvl(int index, SsnAvl avl) {
        String filename = "avl" + index + ".ser";
        try {
            // write object to file
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(avl);
            oos.close();
        } catch (IOException ie) {
            System.out.println("File cannot be written!");
            return false;
        }
        return true;
    }

    /**
     * Retrieve the entire AVL tree from a file
     *
     * @param index the index/hash number in the hash table to be retrieved
     * @return the AVL tree
     */
    @Override
    public SsnAvl retrieveAvl(int index) {
        String filename = "avl" + index + ".ser";
        SsnAvl result = new SsnAvl();
        try {
            // read object from file
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            result = (SsnAvl) ois.readObject();
            ois.close();
        } //check for file availability 
        catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        } catch (ClassNotFoundException ex) {
            return null;
        }
        return result;
    }
}
