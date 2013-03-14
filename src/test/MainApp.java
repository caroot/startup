package test;

public class MainApp {
	public static void main(String args[]) {
		System.out.println("Scanning...");

		final ObserverNotifier observer1 = new ObserverNotifier();
		final PortScanner observable1 = init("yahoo.com", 80, 90, observer1);

		final ObserverNotifier observer2 = new ObserverNotifier();
		final PortScanner observable2 = init("hexican.com", 0, 200, observer2);

		Thread thread1, thread2;

		thread1 = new Thread(observable1);
		thread1.start();

		thread2 = new Thread(observable2);
		thread2.start();
	}

	private static PortScanner init(String ipAddress, int fromPort, int toPort,
			ObserverNotifier observer) {
		PortScanner observable = new PortScanner();
		observable.setTarget(ipAddress);
		observable.setFromPort(fromPort);
		observable.setToPort(toPort);
		observable.addObserver(observer);
		return observable;
	}
}