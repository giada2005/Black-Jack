package main.model;
/**
 * La classe  Card rappresenta una carta da gioco.
 * 
 * <p>
 * Le classi che implementano questa interfaccia devono fornire le
 * implementazioni concrete per i metodi {@link #getSuit()},
 * {@link #getSuit()}, e {@link #getRank()}.
 * </p>
 * 
 * <p>
 * Pattern adottati:
 * - Template Method: tramite il metodo {@link #getImageFileName()},
 * implementato come
 * default, ed altri metodi dell'interfaccia per
 * costruire il nome del file immagine associato alla carta.
 * - Strategy: l'interfaccia {@link Card} rappresenta una strategia per la
 * gestione delle carte, con implementazioni concrete che definiscono il
 * comportamento specifico per il valore, il seme e il rango delle carte.
 * </p>
 */
public class Card {
	private String rank;
	private String suit;
	
	/**
     * Costruttore della classe {@code Card}.
     * <p>
     * Inizializza il rango e il seme della carta.
     * </p>
     *
     * @param suit Il seme della carta (es. "hearts", "clubs").
     * @param rank Il rango della carta (es. "A", "10", "K").
     */	
public Card(String suit, String rank){
		this.rank= rank;
		this.suit= suit;
	}
/**
 * Restituisce il valore numerico della carta.
 * <p>
 * Le carte con rango "J", "Q" e "K" valgono 10, l'asso ("A") vale 11,
 * e le altre carte hanno il loro valore numerico corrispondente.
 * In caso di rango non valido, restituisce 0 e stampa un messaggio di errore.
 * </p>
 *
 * @return Il valore numerico della carta.
 */
 public int getCardValue() {
	 if ("JQK".contains(rank)) {
         return 10; 
     } else if ("A".equals(rank)) {
         return 11; 
     } else {
    	 try {
         return Integer.parseInt(rank); 
     }
    	 catch (NumberFormatException e) {
    		 System.out.println("Errore :il rango non è valido ("+rank+")");
    		 return 0;
    		}
 }
 }
 /**
  * Getter restituisce il seme della carta.
  *
  * @return Il seme della carta (es. "hearts", "clubs", "spades", "diamonds").
  */
	        

	    
 public String getSuit() {
	        return suit;
	    }



 /**
  * Determina se la carta è un asso.
  *
  * @return {@code true} se la carta è un asso ("A"), {@code false} altrimenti.
  */
	public  boolean isAce() {
		return "A".equals(rank);
		
		
	}
	 /**
     * Getter che restituisce il rango della carta (es. ace, king, queen, ecc.).
     *
     * @return Il rango della carta (es. "ace", "king", "queen", ecc.).
     */
	public String getRank() {
		
		return rank;
	}
	 /**
     * Metodo di default che restituisce il nome del file immagine associato alla
     * carta.
     * <p>
     * Questo metodo è implementato con una logica predefinita che costruisce il
     * nome del file immagine utilizzando il rango e il seme della carta. (es. per
     * un Asso di cuori, il nome del file sarà "ace_of_hearts.png").
     * </p>
     *
     * @return Il nome del file immagine della carta.
     */
	public String getImageFileName() {
		
		return getRank() + "-" + getSuit() + ".png";
		
	}
	 @Override
	 /**
	     * Metodo di default che restituisce il nome del file immagine associato alla
	     * carta.
	     * <p>
	     * Questo metodo è implementato con una logica predefinita che costruisce il
	     * nome del file immagine utilizzando il rango e il seme della carta. (es. per
	     * un Asso di cuori, il nome del file sarà "ace_of_hearts.png").
	     * </p>
	     *
	     * @return Il nome del file immagine della carta.
	     */
	    public String toString() {
	        return rank + " of " + suit;
	    }
		
}





