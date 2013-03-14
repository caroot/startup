package run;

public interface AdressScannerObservable {
	public void addAdressScannerObserver(AdressScannerObserver observer);
	public void removeAdressScannerObserver(AdressScannerObserver observer);
}
