package cs143.business;

import cs143.dataaccess.SsnMap;
import cs143.domain.Retiree;

/**
 * A business class for managing Retirees through the application UI
 *
 */
public class RetireeManager {

    private SsnMap map;

    /**
     * Constructor that instantiates the map
     */
    public RetireeManager() {
        map = new SsnMap();
    }
    
    /**
     * This method adds a new Retiree to an SsnMap
     *
     * @param retiree an object of Referee Type to be added
     * @return true if added false otherwise
     */
    public boolean add(Retiree retiree) {
        return map.insert(retiree.getSsn(), retiree);
    }
    
    /**
     * This method deletes retiree if the ssn matches the parameter. It returns 
     * true is the retiree was deleted, false if not
     *
     * @param ssn a value of long data type which is the social security
     * number that is in the map
     * @return true if deleted false otherwise
     */
    public boolean delete(long ssn) {
        return map.remove(ssn);
    }
    
    /**
     * This method gets a Retiree with a ssn. It returns true if the retiree was 
     * found and null if no retiree was not found
     *
     * @param ssn a value of long data type which is the social security
     * number that is in the map
     * @return the Retiree if the ssn is in the map, null otherwise
     */
    public Retiree get(long ssn) {
        return map.get(ssn);
    }

}
