package sujj.concurrent;

public class deadLockSample {
    static class Friend {
        private final String name;
        public Friend(String name) {
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
        public synchronized void bow(Friend bower) {
        	//synchronized(this)
        	{
        	System.out.format("%s: %s"
                + "  has bowed to me!%n", 
                this.name, bower.getName());
        	}
            bower.bowBack("me");
        }
        public synchronized void bowBack(String name) {
            System.out.format("%s: %s"
                + " has bowed back to me!%n",
                this.name, name);
        }
    }

    public static void main(String[] args) {
        final Friend alphonse =
            new Friend("Alphonse");
        final Friend gaston =
            new Friend("Gaston");
        new Thread(new Runnable() {
            public void run() { alphonse.bow(gaston); }
        }).start();
        new Thread(new Runnable() {
            public void run() { gaston.bow(alphonse); }
        }).start();
    }
}