package ro.agitman.ing.model;

/**
 * Created by edi on 27.08.15.
 */
public enum Months {

    ianuarie, februarie, martie, aprilie, mai, iunie, iulie, august, septembrie, octombrie, noiembrie, decembrie;

    public static int getMonthIndex(String name) {
        for (Months m : Months.values()) {
            if (name.equals(m.name())) {
                return m.ordinal();
            }
        }

        return -1;
    }

}
