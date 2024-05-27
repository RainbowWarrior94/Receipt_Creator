import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Formatter;

@SuppressWarnings("serial")
public class WindowView extends JFrame {
		
	//deklaracja oraz inicjalizacja zmiennych
	private JButton add_product = new JButton(" Dodaj towar do paragonu ");
	private JButton create_receipt = new JButton(" Utwórz paragon ");
	private JButton all_receipts = new JButton(" Wszystkie paragony ");
	private JLabel product_name = new JLabel(" Nazwa produktu: ");
	private JTextField input_name = new JTextField("", 15);
	private JLabel netto_price = new JLabel(" Cena Netto: ");
	private JTextField input_netto_price = new JTextField("", 15);
	private JLabel product_quantity = new JLabel(" Ilość: ");
	private JTextField input_quantity = new JTextField("", 15);
	private JLabel VAT_tax_label = new JLabel(" VAT w procentach: ");
	private JTextField input_VAT_tax = new JTextField("", 15);
	private JLabel receipt_label = new JLabel(" Towary dodane do paragonu: ");
	private JTextArea product_list = new JTextArea("");
	
	String item;
	String name;
	double price;
	double quantity;
	int VAT_tax; //w tym projekcie VAT jest zmienną typu integer tylko dla przykładu pracy z typami danych całkowitych
	double netto_value;
	double VAT_value;
	double brutto_value;
	double totalNettoValue;
	double totalVatValue;
	double totalBruttoValue;
		
	// tworzenie obiektów klas
	Receipt  receipt = new Receipt();
	Receipts receipts = new Receipts();
	Valuescalc calculations = new Valuescalc();
	getData get_data = new getData();
	
	// Konstruktor klasy WindowView
	public WindowView () {
		super("Tworzenie paragonów");
		this.setBounds(200, 50, 950, 500);
		
		// tworzenie kontenera dla wszystkich elementów interfejsu
        JPanel panel = new JPanel(new GridBagLayout());
        this.setContentPane(panel);
		
		// obiekt do ustawiania właściwości komponentów
        GridBagConstraints setProperties =  new GridBagConstraints();
        
        // ustawianie właściwości komponentów
		setProperties.anchor = GridBagConstraints.WEST;
		setProperties.insets = new Insets(5, 5, 5, 5);
		
		setProperties.gridx = 0; 
		setProperties.gridy = 0;
		panel.add(product_name, setProperties);

		setProperties.gridx = 1; 
		setProperties.gridy = 0;
		panel.add(input_name, setProperties);

		setProperties.gridx = 0; 
		setProperties.gridy = 1;	
		panel.add(netto_price, setProperties);

		setProperties.gridx = 1; 
		setProperties.gridy = 1;
		panel.add(input_netto_price, setProperties);

		setProperties.gridx = 0; 
		setProperties.gridy = 2;
		panel.add(product_quantity, setProperties);

		setProperties.gridx = 1; 
		setProperties.gridy = 2;
		panel.add(input_quantity, setProperties);

		setProperties.gridx = 0; 
		setProperties.gridy = 3;
		panel.add(VAT_tax_label, setProperties);
		
		setProperties.gridx = 1; 
		setProperties.gridy = 3;
		panel.add(input_VAT_tax, setProperties);

		setProperties.gridx = 0; 
		setProperties.gridy = 4;
		panel.add(receipt_label, setProperties);
		
		setProperties.gridwidth = 10;
		setProperties.gridx = 0; 
		setProperties.gridy = 5;
		product_list.setPreferredSize(new Dimension(850, 250));
		panel.add(product_list, setProperties);
		product_list.setEditable(false);
		
		add_product.addActionListener(new AddProductEventListener ());
		setProperties.gridwidth = 3;
		setProperties.gridx = 2; 
		setProperties.gridy = 1;
		add_product.setPreferredSize(new Dimension(250,25));
		panel.add(add_product, setProperties);
		
		create_receipt.addActionListener(new CreateReceiptEventListener ());
		setProperties.gridwidth = 3;
		setProperties.gridx = 2; 
		setProperties.gridy = 2;
		create_receipt.setPreferredSize(new Dimension(250,25));
		panel.add(create_receipt, setProperties);
		
		all_receipts.addActionListener(new ShowReceiptsEventListener ());
		setProperties.gridwidth = 3;
		setProperties.gridx = 2; 
		setProperties.gridy = 3;
		all_receipts.setPreferredSize(new Dimension(250,25));
		panel.add(all_receipts, setProperties);
		
		panel.setVisible(true);		
	}
		
	class AddProductEventListener implements ActionListener {

		public void actionPerformed (ActionEvent e) {	
			
			// Walidacja wprowadzanych danych
			name = getData.getText(input_name.getText());	
			if (validateNameInput(name)) {
				JOptionPane.showMessageDialog(null, "Nazwa towaru musi składać się z co najmniej dwóch znaków i nie może zawierać tylko cyfr.");
            	return;
            }
			
			try{
				price = getData.getDouble(input_netto_price.getText());

			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Ilość musi być liczbą rzeczywistą");
				return;
			}
			
			if (validatePriceQuantityInput(price)) {
				JOptionPane.showMessageDialog(null, "Cena musi być większa od zera");
            	return;
            }
						
			try{
			quantity = getData.getDouble(input_quantity.getText());
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Ilość musi być liczbą rzeczywistą");
				return;
			}
			
			if (validatePriceQuantityInput(quantity)) {
				JOptionPane.showMessageDialog(null, "Ilość musi być większa od zera");
            	return;
            }
						
			try{
				VAT_tax = getData.getInt(input_VAT_tax.getText());
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "VAT musi być liczbą całkowitą");
				return;
			}
			
			if (validateVATInput(VAT_tax)) {
				JOptionPane.showMessageDialog(null, "VAT musi być większy lub równy zero");
            	return;
            }	
			
			netto_value = calculations.valueNetto(price, quantity);
			VAT_value = calculations.valueVAT(netto_value, VAT_tax);
			brutto_value = calculations.valueBrutto(netto_value, VAT_value);
			
			//tworzenie obiektu do formatowania zmiennych
			Formatter formatData = new Formatter();
			formatData.format("Towar: %s, Cena netto: %,.2f zł, Ilość: %,.2f, " , name, price, quantity);
			formatData.format("VAT: %2d %%, Wartość netto: %,.2f zł, Wartość VAT: %,.2f zł, ", VAT_tax, netto_value, VAT_value);
			formatData.format("Wartość brutto: %,.2f zł \n", brutto_value);
			item = formatData.toString();
			
			formatData.close();
			
			//czyszczenie pól tekstowych
		    input_name.setText("");
			input_netto_price.setText("");
			input_quantity.setText("");
			input_VAT_tax.setText("");

			product_list.append(item);

			ItemReceipt items_receipt = new ItemReceipt(name, price, quantity, VAT_tax, netto_value, VAT_value, brutto_value, totalNettoValue, totalVatValue, totalBruttoValue);
			items_receipt.setValues(name, price, quantity, VAT_tax, netto_value, VAT_value, brutto_value);
			items_receipt.setTotalValues(totalNettoValue, totalVatValue, totalBruttoValue);
			receipt.addItemReceipt(items_receipt);
		}
	}
	
	class CreateReceiptEventListener implements ActionListener {

		public void actionPerformed (ActionEvent e) {

			// pobieranie danych o towarach
			ArrayList<ItemReceipt> showReceipt = receipt.getReceipt();
			
			// przypisywanie wartości do zmiennych
			totalNettoValue = calculations.totalNettoV(showReceipt);
		    totalVatValue = calculations.totalVAT(showReceipt);
		    totalBruttoValue = calculations.totalBruttoV(showReceipt);
		    
		    for (ItemReceipt receiptItem : showReceipt) {
		        receiptItem.setTotalValues(totalNettoValue, totalVatValue, totalBruttoValue);
		        
		    }
			
			// sprawdzanie, czy lista nie jest pusta
			if(showReceipt.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Dodaj co najmniej jeden towar do paragonu");
			}else {			
				// formatowanie danych do wyświetlenia tabeli
				StringBuilder tableData = new StringBuilder(String.format("<html><body><table border='1' cellpadding='6'>"));
				tableData.append("<tr><th>Nr</th><th>Nazwa Towaru</th><th>Cena Netto</th><th>Ilość Sztuk</th><th>VAT Stawka</th><th>Wartość Netto</th><th>Wartość VAT</th><th>Wartość Brutto</th></tr>");
	
				// wypełnienie tabeli
				int i = 1;
				for (ItemReceipt receiptItem : showReceipt) {
				    tableData.append("<tr>");
				    tableData.append("<td ALIGN=center>").append(i).append("</td>");
				    tableData.append("<td ALIGN=center>").append(receiptItem.getName()).append("</td>");
				    tableData.append(String.format("<td ALIGN=center> %.2f zł </td>", receiptItem.getPrice()));
				    tableData.append(String.format("<td ALIGN=center> %.2f </td>", receiptItem.getQuantity()));
				    tableData.append(String.format("<td ALIGN=center> %2d %% </td>", receiptItem.getVAT_tax()));
				    tableData.append(String.format("<td ALIGN=center> %.2f zł </td>", receiptItem.getNetto_value()));
				    tableData.append(String.format("<td ALIGN=center> %.2f zł </td>", receiptItem.getVAT_value()));
				    tableData.append(String.format("<td ALIGN=center> %.2f zł </td>", receiptItem.getBrutto_value()));
				    i++;
					}   
			    tableData.append("<tr><td colspan='5' ALIGN=right>Podsumowanie: ").append("</td>");
			    tableData.append(String.format("<td ALIGN=center> %.2f zł </td>",totalNettoValue));
			    tableData.append(String.format("<td ALIGN=center> %.2f zł </td>",totalVatValue));
			    tableData.append(String.format("<td ALIGN=center> %.2f zł </td>",totalBruttoValue));
			    tableData.append("</table></body></html>");
		        
		        // wyświetlenie sformatowanych danych 
		        JOptionPane.showMessageDialog(null, tableData.toString(), "Receipt Data", JOptionPane.INFORMATION_MESSAGE);
		        	        
		        // dodawanie paragonu do listy paragonów
		        receipts.addReceipt(showReceipt);
	        }	

	        product_list.setText("");       
	        receipt = new Receipt();   
	    }	
	}
	
	class ShowReceiptsEventListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			// pobieranie danych o paragonach
			ArrayList<ArrayList<ItemReceipt>> listOfReceipts = receipts.getReceiptsList();
			
			// sprawdzanie, czy lista nie jest pusta
			if(listOfReceipts.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Dodaj co najmniej jeden paragon");
			}else {	
						
			// formatowanie danych do wyświetlenia tabeli
			StringBuilder tableData = new StringBuilder(String.format("<html><body><table border='1' cellpadding='6'>"));
		    
		        int i = 1;
		        // wypełnienie tabeli
		        for (ArrayList<ItemReceipt> receipt : listOfReceipts) {
		        	// inicjowanie zmiennych do przechowywania sumarycznych wartości paragonu
		        	double totalNettoValueR = 0.0; 
	                double totalVatValueR = 0.0; 
	                double totalBruttoValueR = 0.0;
		        	
		            tableData.append("<tr><td colspan='8'>Paragon ").append(i).append(": ").append("</td></tr>");
		            tableData.append("<tr><th>Nr</th><th>Nazwa Towaru</th><th>Cena Netto</th><th>Ilość Sztuk</th><th>VAT Stawka</th><th>Wartość Netto</th><th>Wartość VAT</th><th>Wartość Brutto</th></tr>");
		            int j = 1;
		            for (ItemReceipt itemReceipt : receipt) {
		            	// pobieramy wartości 
		            	totalNettoValueR = itemReceipt.getTotalNettoValue();
                        totalVatValueR = itemReceipt.getTotalVatValue();
                        totalBruttoValueR = itemReceipt.getTotalBruttoValue();

		                tableData.append("<tr>");
		                tableData.append("<td ALIGN=center>").append(j).append("</td>");
		                tableData.append("<td ALIGN=center>").append(itemReceipt.getName()).append("</td>");
		                tableData.append(String.format("<td ALIGN=center> %.2f zł </td>", itemReceipt.getPrice()));
		                tableData.append(String.format("<td ALIGN=center> %.2f </td>",itemReceipt.getQuantity()));
		                tableData.append(String.format("<td ALIGN=center> %2d %% </td>",itemReceipt.getVAT_tax()));
		                tableData.append(String.format("<td ALIGN=center> %.2f zł </td>",itemReceipt.getNetto_value()));
		                tableData.append(String.format("<td ALIGN=center> %.2f zł </td>",itemReceipt.getVAT_value()));
		                tableData.append(String.format("<td ALIGN=center> %.2f zł </td>",itemReceipt.getBrutto_value()));
		                tableData.append("</tr>");
		                j++;
		            }		            
                    tableData.append("<tr><td colspan='5' ALIGN=right>Podsumowanie: ").append("</td>");
                    tableData.append(String.format("<td ALIGN=center> %.2f zł </td>", totalNettoValueR));
                    tableData.append(String.format("<td ALIGN=center> %.2f zł </td>", totalVatValueR));
                    tableData.append(String.format("<td ALIGN=center> %.2f zł </td>", totalBruttoValueR));
                    i++;
		        }
		        tableData.append("</table></body></html>");
		        
		     // wyświetlenie sformatowanych danych
		    JOptionPane.showMessageDialog(null, tableData.toString(), "Receipt Data", JOptionPane.INFORMATION_MESSAGE);	
			}
		}
	}
	
// metody weryfikacji wprowadzanych danych	
    private boolean validateNameInput(String nameInput) {
        if(nameInput != null && nameInput.matches(".*[a-zA-Z\\p{L}'\\- ].*") && nameInput.length() >= 2) {
        	return false;
        } else {
        	return true;
        }	
    }
	private boolean validatePriceQuantityInput(double input) {
        if(input <= 0) {
        	return true;
        } else {
        	return false;
        }
	}
	private boolean validateVATInput(int vat) {
        if(vat < 0) {
        	return true;
        } else {
        	return false;
        }
	}	
}

