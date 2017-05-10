package services;

/**
 * Created by yoannlathuiliere on 10/05/2017.
 */
public class TurtleService {
    static TurtleService sharedInstance;

    private TurtleService() {}

    public TurtleService getInstance() {
        if (sharedInstance == null) {
            sharedInstance = new TurtleService();
        }

        return sharedInstance;
    }


}
