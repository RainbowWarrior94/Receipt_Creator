import java.util.ArrayList;

public class Valuescalc {
	// metoda, która liczy wartość Netto
	public double valueNetto(double price, double quantity) {
		return price * quantity;
	}
	
	// metoda, która liczy wartość Vat
	public double valueVAT(double netto_value, int VAT_tax) {
		return netto_value * (VAT_tax / 100.0);
	}
	
	// metoda, która liczy wartość Brutto
	public double valueBrutto (double netto_value, double VAT_value) {
		return netto_value + VAT_value;
	}
	
	// metoda, która oblicza całkowitą wartość netto
    public double totalNettoV(ArrayList<ItemReceipt> itemOfReceipt) {
    	double totalNetto = 0;
        for (ItemReceipt itemReceipt : itemOfReceipt) {
        	totalNetto += itemReceipt.getNetto_value();
        }
        return totalNetto;
    }
	
	// metoda, która oblicza całkowitą wartość Vat
    public double totalVAT (ArrayList<ItemReceipt> itemOfReceipt) {
		double totalVatV = 0;
        for (ItemReceipt itemReceipt : itemOfReceipt) {
        	totalVatV += itemReceipt.getVAT_value();
        }
		return totalVatV;
	}
	
	// metoda, która oblicza całkowitą wartość Brutto
    public double totalBruttoV (ArrayList<ItemReceipt> itemOfReceipt) {
		double totalBrutto = 0;
        for (ItemReceipt itemReceipt : itemOfReceipt) {
        	totalBrutto += itemReceipt.getBrutto_value();;
        }
		return totalBrutto;
	}
}
