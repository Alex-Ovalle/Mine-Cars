// Source code is decompiled from a .class file using FernFlower decompiler.
public class ConcreteCar extends Car {
   public ConcreteCar(int var1, String var2, String var3, String var4, String var5, int var6, double var7, String var9, String var10, String var11, double var12, int var14) {
      super(var1, var2, var3, var4, var5, var6, var7, var9, var10, var11, var12, var14);
   }

   public String getDetails() {
      return String.format("ID: %d, Type: %s, Model: %s, Condition: %s, Color: %s, Capacity: %d, Mileage: %.2f, Fuel Type: %s, Transmission: %s, VIN: %s, Price: %.2f, Cars Available: %d", this.getId(), this.getType(), this.getModel(), this.getCondition(), this.getColor(), this.getCapacity(), this.getMileage(), this.getFuelType(), this.getTransmission(), this.getVin(), this.getPrice(), this.getCarsAvailable());
   }

   public String toString() {
      return this.getDetails();
   }
}
