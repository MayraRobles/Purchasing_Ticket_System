public class Concessions {
    private float popcornPrice;
    private float hotDogPrice;
    private float sodaPrice;

    public Concessions () {
        popcornPrice = 6.00f;
        hotDogPrice = 4.00f;
        sodaPrice = 1.50f;
    }
    // Setters
    public void setPopcornPrice (float popcornPrice) {
        this.popcornPrice = popcornPrice;
    }
    public void setHotdogPrice (float hotDogPrice) {
        this.hotDogPrice = hotDogPrice;
    }
    public void setSodaPrice (float sodaPrice) {
        this.sodaPrice = sodaPrice;
    }
    // Getters
    public float getPopcornPrice () {
        return popcornPrice;
    }
    public float getHotdogPrice () {
        return hotDogPrice;
    }
    public float getSodaPrice () {
        return sodaPrice;
    }
    // This multiplies every item by the tax
    public void setTaxToPrice (float tax) {
        this.popcornPrice = this.popcornPrice  * tax;
        this.hotDogPrice = this.hotDogPrice * tax;
        this.sodaPrice = this.sodaPrice * tax;
    }
    // To string Method
    public String toString (String nameEvent) {
        System.out.format("+-------------------------------+%n");
        System.out.printf("| %-29s |%n", nameEvent);
        System.out.format("+----------------------+--------+%n");
        System.out.printf("| %-20s | $%5.2f |\n", "POPCORN", this.popcornPrice);
        System.out.format("+----------------------+--------+%n");
        System.out.printf("| %-20s | $%5.2f |\n", "SODA", this.sodaPrice);
        System.out.format("+----------------------+--------+%n");
        System.out.printf("| %-20s | $%5.2f |\n", "HOT DOG", this.hotDogPrice);
        System.out.format("+-------------------------------+%n");
        return "";
    }
}
