import java.util.ArrayList;

public class ItemReceipt  {
	
    //prywatne zmienne klasy itemReceipt
	private String name;
    private double price;
    private double quantity;
    private int VAT_tax;
    private double netto_value;
    private double VAT_value;
    private double brutto_value;
    private double totalNettoValue;
    private double totalVatValue;
    private double totalBruttoValue;

    // Konstruktor klasy itemReceipt i inicjalizacja pól obiektu
    public ItemReceipt(String name, double price, double quantity, int VAT_tax, double netto_value,
            double VAT_value, double brutto_value, 	double totalNettoValue, double totalVatValue, double totalBruttoValue) {
	 this.name = name;
	 this.price = price;
	 this.quantity = quantity;
	 this.VAT_tax = VAT_tax;
	 this.netto_value = netto_value;
	 this.VAT_value = VAT_value;
	 this.brutto_value = brutto_value;
	 this.totalNettoValue = totalNettoValue;
	 this.totalVatValue = totalVatValue;
	 this.totalBruttoValue = totalBruttoValue;
	 }

    // metody pobierania wartości pól obiektu item Receipt (gettery)
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public double getQuantity() {
        return quantity;
    }
    public int getVAT_tax() {
        return VAT_tax;
    }
    public double getNetto_value() {
        return netto_value;
    }
    public double getVAT_value() {
        return VAT_value;
    }
    public double getBrutto_value() {
        return brutto_value;
    }
    public double getTotalNettoValue() {
        return totalNettoValue;
    }
    public double getTotalVatValue() {
        return totalVatValue;
    }
    public double getTotalBruttoValue() {
        return totalBruttoValue;
    }

    // metody ustawiania wartości pól i inicjowanie pól obiektu ItemReceipt
    public void setValues(String name, double price, double quantity, int VAT_tax, double netto_value,
            double VAT_value, double brutto_value) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.VAT_tax = VAT_tax;
        this.netto_value = netto_value;
        this.VAT_value = VAT_value;
        this.brutto_value = brutto_value;
    }
    public void setTotalValues(double totalNettoValue, double totalVatValue, double totalBruttoValue) {
	   	this.totalNettoValue = totalNettoValue;
	   	this.totalVatValue = totalVatValue;
	   	this.totalBruttoValue = totalBruttoValue;
    }
}

// prywatna klasa Receipt
class Receipt {
    private ArrayList<ItemReceipt> receipt;

    // Konstruktor klasy itemReceipt
    public Receipt() {
    	receipt = new ArrayList<>();
    }

    // Metoda dodawania obiektu itemReceipt do listy receipt
    public void addItemReceipt(ItemReceipt itemReceipt) {
    	receipt.add(itemReceipt);
    }

    //Metoda pobierania listy obiektów itemReceipt (getter)
    public ArrayList<ItemReceipt> getReceipt() {
        return receipt;
    }
}