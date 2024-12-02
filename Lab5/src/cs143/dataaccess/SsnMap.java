package cs143.dataaccess;

import cs143.domain.Retiree;

/**
 * The class used to interact with the data of Retirees
 *
 */
public class SsnMap {

    private static int CAPACITY = 1024;
    private IDataAccess data = new DataAccessFileImpl();

    /**
     * Get the retiree in the map after being provided with ssn.
     *
     * @param ssn the ssn number to search
     * @return the retiree that owns the ssn
     */
    public Retiree get(long ssn) {
        int hashCode = hash(ssn);
        SsnAvl retirees = data.retrieveAvl(hashCode);
        if (retirees == null) {
            return null;
        }
        return retirees.get(ssn);
    }

    /**
     * Insert the ssn into an AVL tree
     *
     * @param ssn the ssn number of the Retiree
     * @param r Retiree to insert
     * @return true if the Retiree was inserted, false if not.
     */
    public boolean insert(long ssn, Retiree r) {
        int hashCode = hash(ssn);
        SsnAvl retirees = data.retrieveAvl(hashCode);
        if (retirees == null) {
            retirees = new SsnAvl();
        }
        if (this.get(ssn) != null) {
            return false;
        }
        retirees.add(r);
        return data.saveAvl(hashCode, retirees);
    }

    /**
     * Remove the ssn from an AVL tree
     *
     * @param ssn the ssn number of the Retiree
     * @return true if the Retiree was removed, false if not.
     */
    public boolean remove(long ssn) {
        int index = hash(ssn);
        SsnAvl avl = data.retrieveAvl(index);
        if (avl == null) {
            return false;
        }
        boolean check = avl.remove(get(ssn));
        data.saveAvl(index, avl);
        return check;
    }

    /**
     * hash method
     *
     * @param ssn social sec number
     * @return an integer depending on what its hashcode is
     */
    public int hash(long ssn) {
        int hashCode = (int) (ssn ^ (ssn >>> 32));
        return supplementalHash(hashCode) & (CAPACITY - 1);
    }

    /**
     * hash used to help come up with a hashCode
     *
     * @param h integer that's a parameter from hash Method
     * @return integer that helps come up with a hashCode
     */
    private static int supplementalHash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

}
