package main.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * La classe Deck rappresenta il mazzo di carte.
 * 
 * <p>
 * Pattern adottati:
 * - Singleton: serve ad assicurare che esista una sola istanza del mazzo
 * durante l'esecuzione del gioco.
 * </p>
 */
public class Deck {
	/** Istanza della classe Deck */
    private static Deck instance;

    /** Lista di carte nel mazzo */
    private List<Card> cards;

    /**
     * Costruttore privato per prevenire l'istanziazione diretta.
     * Inizializza il mazzo di carte.
     */
    private Deck() {
        initializeDeck();
    }

    /**
     * Getter che restituisce l'istanza Singleton del mazzo.
     * 
     * <p>
     * Se l'istanza non esiste, viene creata. Altrimenti, viene restituita
     * l'istanza esistente.
     * </p>
     * 
     * @return L'unica istanza di Deck.
     */
    public static Deck getInstance() {
        if (instance == null) {
            instance = new Deck();
        }
        return instance;
    }

    /**
     * Metodo che inizializza il mazzo di carte.
     * 
     * <p>
     * Utilizza gli Stream per creare tutte le combinazioni possibili
     * di semi e ranghi, creando le carte tramite la classe CardFactory.
     * </p>
     */
    private void initializeDeck() {
        cards = new ArrayList<>();
        String[] suits = { "C", "D", "C", "S" };
        String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };

        for(String suit:suits) {
        	for (String rank:ranks) {
        		cards.add(new Card(suit,rank));        	}
        }
    }

    /**
     * Metodo che mescola le carte nel mazzo.
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Metodo che estrae una carta dal mazzo.
     * 
     * <p>
     * Se il mazzo dovesse essere vuoto, viene reinizializzato e mescolato
     * prima di estrarre una carta.
     * </p>
     * 
     * @return La carta estratta.
     */
    public Card drawCard() {
        if (cards.isEmpty()) {
            initializeDeck();
            shuffle();
        }
        return cards.remove(cards.size() - 1);
    }
}


