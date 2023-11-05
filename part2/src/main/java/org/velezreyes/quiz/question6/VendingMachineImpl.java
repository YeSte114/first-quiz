package org.velezreyes.quiz.question6;

public class VendingMachineImpl implements VendingMachine {

  private static VendingMachineImpl instance = null;

  private int moneyInserted;

  private VendingMachineImpl() {
    this.moneyInserted = 0;
  }

  public static VendingMachine getInstance() {
    if (instance == null) {
      instance = new VendingMachineImpl();
    }

    return instance;
  }

  @Override
  public void insertQuarter() {
    this.moneyInserted += 25;
  }

  @Override
  public Drink pressButton(String name) throws NotEnoughMoneyException, UnknownDrinkException {
    int price = getPrice(name);

    if (this.moneyInserted < price) {
      throw new NotEnoughMoneyException();
    }

    if (!name.equals("ScottCola") && !name.equals("KarenTea")) {
      throw new UnknownDrinkException();
    }

    boolean isFizzy = name.equals("ScottCola");
    Drink drink = new DrinkImpl(name, isFizzy);
    this.moneyInserted = 0;
    return drink;
  }

  private int getPrice(String name) throws UnknownDrinkException {
    if (name.equals("ScottCola")) {
      return 75;
    } else if (name.equals("KarenTea")) {
      return 100;
    } else {
      throw new UnknownDrinkException();
    }
  }

  private class DrinkImpl implements Drink {

    private final String name;
    private final boolean isFizzy;

    public DrinkImpl(String name, boolean isFizzy) {
      this.name = name;
      this.isFizzy = isFizzy;
    }

    @Override
    public String getName() {
      return this.name;
    }

    @Override
    public boolean isFizzy() {
      return this.isFizzy;
    }
  }
}