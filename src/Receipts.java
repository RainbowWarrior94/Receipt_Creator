import java.util.ArrayList;

public class Receipts {
	
	//deklaracja listy ReceiptsList
	private ArrayList<ArrayList<ItemReceipt>> ReceiptsList;

    // Konstruktor klasy Receipts
    public Receipts() {
    	ReceiptsList = new ArrayList<>();
    }

    // Metoda dodawania paragonu do listy
    public void addReceipt(ArrayList<ItemReceipt> receipt) {
    	ReceiptsList.add(receipt);
    }

    // Metoda pobierania listy paragonów (getter)
    public ArrayList<ArrayList<ItemReceipt>> getReceiptsList() {
        return ReceiptsList;
    }
}


