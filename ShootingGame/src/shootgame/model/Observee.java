package shootgame.model;

public interface Observee {
	public abstract void addObserver(Observer o);
	public abstract void removeObserver(Observer o);
	public abstract void notifyObserver();
}
