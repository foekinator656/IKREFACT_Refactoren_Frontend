package nl.hsleiden.ipsen2.bouncer.front.interfaces;

public interface Model {
  void registerObserver(View v);

  void unregisterObserver(View v);

  void notifyObservers();
}
