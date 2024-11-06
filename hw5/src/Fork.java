public class Fork {

    private static int forkCount;

    private boolean isOnTable;
    private int position;

    public Fork() {
        this.isOnTable = true;
        this.position = ++forkCount;
    }

    public synchronized boolean isOnTable() {
        return isOnTable;
    }

    public void setOnTable(boolean isOnTable) {
        synchronized (this) {
            if (this.isOnTable != isOnTable) {
                this.isOnTable = isOnTable;
            }
        }
    }

    public int getPosition() {
        return position;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Fork # ");
        builder.append(position);
        if (isOnTable) {
            builder.append(" is on table");
        } else {
            builder.append(" is taken");
        }
        return builder.toString();
    }
}