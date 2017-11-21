package util;

import model.Alignment;

import java.util.Comparator;

/**
 * Created by nammi on 25/10/17.
 */
public class AlignmentComparator implements Comparator<Alignment>{

    public int compare(Alignment o1, Alignment o2) {
        if (o2.getScore() < o1.getScore())
        {
            return -1;
        }
        return 1;
    }
}
