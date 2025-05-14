package exercise;

// BEGIN
public class ListThread extends Thread {
    private SafetyList safetyList;

    public ListThread(SafetyList list) {
        this.safetyList = list;
    }

    @Override
    public void run() {
        for (int i = 0; i <= 999; i++) {
            safetyList.add(i);
        }
    }
}
// END
