package main.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.Observable;


/**
 * La classe BlackjackModel implementa la logica del gioco.
 * 
 * <p>
 * Pattern utlizzati:
 * - MVC (Model-View-Controller): come parte del Model per gestire i giocatori
 * in partita, il mazzo di carte e lo stato della partita stessa.
 * - Observer: consente alla View di essere notificata ogni volta che avviene un
 * cambiamento di stato;
 * </p>
 * 
 * <p>
 * La logica include il mescolamento e la distribuzione delle carte, le
 * azioni "Pesca Carta" e "Stai", la gestione dei turni dei giocatori e il
 * determinare a fine partita il vincitore.
 * </p>
 */
@SuppressWarnings("deprecation")
public class BlackjackModel extends Observable{
	private ArrayList<Player> players;
    private Deck deck;
    private int currentPlayerIndex;
    private UserProfile userProfile;
    private Player dealer;
    private boolean isFinalRound = false; 


    /**
     * Costruttore della classe BlackjackModel.
     * 
     * <p>
     * Inizializza il giocatore umano, i bot, il Banco, il mazzo di carte e carica
     * il profilo utente del giocatore umano per gestirne le statistiche.
     * </p>
     * 
     * @param userProfile Il profilo utente del giocatore umano, compreso
     *                    di tutte le sue informazioni.
     */
    public BlackjackModel(UserProfile userProfile) {
        players = new ArrayList<>();
        players.add(new Player(userProfile.getNickname(), new HumanPlayerStrategy(), true));
        players.add(new Player("Sara", new BotStrategy(), false));
        players.add(new Player("Michele", new BotStrategy(), false));
        dealer = new Player("Banco", new DealerStrategy(), false);
        deck = Deck.getInstance();
        currentPlayerIndex = 0;
        this.userProfile = userProfile;
        userProfile.loadProfile();
    }

    /**
     * Metodo che avvia il gioco, mescola il mazzo di carte e distribuisce le
     * due carte iniziali a tutti i giocatori.
     */
    public void startGame() {
        deck.shuffle();
        dealInitialCards();
        displayPlayerScores();
        setChanged();
        notifyObservers();
    }

    /**
     * Metodo che distribuisce le carte iniziali ai giocatori e al Banco.
     * 
     * <p>
     * Ogni giocatore riceve due carte all'inizio del gioco.
     * </p>
     */
    private void dealInitialCards() {
        IntStream.range(0, 2).forEach(i -> {
            players.forEach(player -> player.addCard(deck.drawCard()));
            dealer.addCard(deck.drawCard());
        });
        setChanged();
        notifyObservers();
    }
    /**
     * Metodo che assegna la terza carta a Sara, Michele e al Banco.
     * 
     * <p>
     * La terza carta viene pescata automaticamente e assegnata ai giocatori non
     * umani e al banco.
     * </p>
     */
    private void dealThirdCard() {
        players.stream()
               .filter(player -> !player.isHuman()) 
               .forEach(player -> player.addCard(deck.drawCard())); 
        dealer.addCard(deck.drawCard()); 

        setChanged();
        notifyObservers("THIRD_CARD"); 
    }
    /**
     * Metodo che visualizza i punteggi di tutti i giocatori.
     */

    
    private void displayPlayerScores() { 
    	players.forEach(player -> {
    		
    		}); }
    
    /**
     * Metodo che gestisce i turni di gioco.
     * 
     * <p>
     * Ogni giocatore, incluso il Banco, effettua il proprio turno in base alla
     * propria strategia. Il turno termina quando tutti hanno giocato.
     * </p>
     */
    public void playTurns() {
    	while (currentPlayerIndex < players.size()) {
    		Player currentPlayer = players.get(currentPlayerIndex);
    		if (currentPlayer.isHuman()) {
    		
    			} else {
    				  if (currentPlayer.getHandValue() < 17) {
    		                hit();
    				  }else {
    		                    break; 
    			}
    				 }
    			
    		stand(); 
    		}
    	
        playDealerTurn();

    	
    	endRound(); 
    	}

    /**
     * Metodo che esegue l'azione "Pesca Carta" per il giocatore corrente,
     * aggiungendo una nuova carta alla sua mano.
     * 
     * <p>
     * Se il giocatore sballa (ovvero supera il valore di 21), il metodo
     * restituisce true, altrimenti false.
     * </p>
     * 
     * @return true se il giocatore ha sballato, altrimenti false.
     */
    public boolean hit() {
    	 if (isFinalRound) {
    	        return false; 
    	    }
        Player currentPlayer = players.get(currentPlayerIndex);
        currentPlayer.addCard(deck.drawCard());
        setChanged();
        notifyObservers();
        return currentPlayer.getHandValue() > 21;
    }

    /**
     * Metodo che esegue l'azione "Stai" per il giocatore corrente,
     * terminando il suo turno e passando al giocatore successivo.
     */
    public void stand() {
    	if (isFinalRound) {
            return; 
        }
        currentPlayerIndex++;
        setChanged();
        notifyObservers();
        if (currentPlayerIndex >= players.size()) {
            
            endRound();
        } else {
           
            Player bot = players.get(currentPlayerIndex);
            if (!bot.isHuman()) {
                bot.addCard(deck.drawCard()); 
            }
            setChanged();
            notifyObservers(); 
        }
    }
    

    /**
     * Metodo che verifica se è il turno del giocatore umano.
     * 
     * @return true se è il turno del giocatore umano, altrimenti false.
     */
    public boolean isHumanTurn() {
        return currentPlayerIndex == 0;
    }

    /**
     * Metodo che determina se il bot corrente voglia eseguire l'azione
     * "Pesca Carta".
     * 
     * @return true se il bot vuole pescare una carta, altrimenti false.
     */
    public boolean botWantsToHit() {
        Player currentPlayer = players.get(currentPlayerIndex);
        return currentPlayer.wantsToHit();
    }

    /**
     * Metodo che termina il round, esegue il turno del Banco
     * e determina il vincitore.
     * Salva il profilo utente del giocatore umano e notifica gli
     * Observer che la partita è terminata tramite il segnale "GAME_OVER".
     */
    public void endRound() {
    	if (isFinalRound) {
            return; 
        }
    	while (dealer.getHandValue() < 17) {
            dealer.addCard(deck.drawCard());
            if (dealer.getHandValue() > 21) {
                break; 
            }
        }
    
    	dealThirdCard(); 
        playDealerTurn();
        determineWinner();
        userProfile.saveProfile();
        setChanged();
        notifyObservers("GAME_OVER");
        isFinalRound = true;
    }

    /**
     * Metodo che esegue il turno del Banco.
     * Il Banco continua a pescare carte finché non decide
     * di fermarsi o sballa (ossia che supera 21).
     */
    private void playDealerTurn() {
        while (dealer.getHandValue() < 17){
            dealer.addCard(deck.drawCard());
            
            if (dealer.getHandValue() > 21) {
            	
                
                break;
            }
        }
    }

    /**
     * Metodo che determina il vincitore del round confrontando il valore
     * della mano del giocatore umano e del Banco.
     * Aggiorna le statistiche del profilo giocatore
     * in base al risultato della partita.
     */
    private void determineWinner() {
        Player humanPlayer = players.get(0);
        int humanHandValue = humanPlayer.getHandValue();
        int dealerHandValue = dealer.getHandValue();

        if (humanHandValue > 21) {
            userProfile.incrementGamesLost();
        } else if (dealerHandValue > 21) {
            userProfile.incrementGamesWon();
        } else if (humanHandValue > dealerHandValue) {
            userProfile.incrementGamesWon();
        } else if (humanHandValue < dealerHandValue) {
            userProfile.incrementGamesLost();
        }
        userProfile.incrementGamesPlayed();
    }

    /**
     * Metodo che ripristina il round corrente,
     * ripristinando le mani dei giocatori e del Banco.
     * Mescola il mazzo e distribuisce nuove carte.
     */
    public void resetRound() {
        players.forEach(Player::clearHand);
        dealer.clearHand();
        currentPlayerIndex = 0;
        deck = Deck.getInstance();
        deck.shuffle();
        isFinalRound = false;
        dealInitialCards();
        displayPlayerScores();
        setChanged();
        notifyObservers();
    }

    /**
     * Metodo che verifica se il gioco è terminato.
     * 
     * @return true se tutti i giocatori hanno completato il loro turno,
     *         altrimenti false.
     */
    public boolean isGameOver() {
        return currentPlayerIndex >= players.size();
    }

    /**
     * Metodo che verifica se è il turno di un bot o del Banco.
     * 
     * @return true se è il turno di un bot o del Banco, altrimenti false.
     */
    public boolean isBotOrDealerTurn() {
        if (currentPlayerIndex < 0 || currentPlayerIndex >= players.size()) {
            return false;
        }
        Player currentPlayer = players.get(currentPlayerIndex);
        return !currentPlayer.isHuman() || currentPlayer == dealer;
    }

    /**
     * Getter che restituisce la lista dei giocatori in gioco.
     * 
     * @return una lista di oggetti {@link Player}.
     */
    public ArrayList<Player> getPlayer() {
        return players;
    }

    /**
     * Getter che restituisce il Banco.
     * 
     * @return l'oggetto {@link Player} che rappresenta il Banco.
     */
    public Player getDealer() {
        return dealer;
    }
    
    /**
     * Metodo che restituisce i punteggi di tutti i giocatori e del Banco.
     * 
     * @return una lista di stringhe che contiene i nomi dei giocatori e i rispettivi punteggi.
     */
    public List<String> getAllScores() {
        List<String> scores = new ArrayList<>();
        for (Player player : players) {
            scores.add(player.getName() + ": " + player.getHandValue());
        }
        scores.add(dealer.getName() + ": " + dealer.getHandValue());
        return scores;
    }

    /**
     * Getter che restituisce il profilo utente del giocatore umano.
     * 
     * @return il profilo utente del giocatore umano {@link UserProfile}.
     */
    public UserProfile getUserProfile() {
        return userProfile;
    }

    /**
     * Getter che restituisce l'indice del giocatore corrente.
     * 
     * @return l'indice del giocatore corrente.
     */
    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    /**
     * Metodo che restituisce un messaggio che indica il vincitore della mano.
     * 
     * @return una stringa che rappresenta il messaggio del vincitore.
     */
    public String getWinnerMessage() {
    	StringBuilder resultMessage = new StringBuilder();

        if (dealer.getHandValue() > 21) {
            
            resultMessage.append("Il Banco ha sballato.\n");
            Player winner = players.stream()
                    .filter(player -> player.getHandValue() <= 21) 
                    .max(Comparator.comparingInt(Player::getHandValue)) 
                    .orElse(null);

            if (winner != null) {
                resultMessage.append(winner.getName()).append(" ha vinto!");
            } else {
                resultMessage.append("Nessuno ha vinto.");
            }
        } else {
            
            Player winner = players.stream()
                    .filter(player -> player.getHandValue() <= 21) 
                    .max(Comparator.comparingInt(Player::getHandValue)) 
                    .orElse(null);

            if (winner != null && winner.getHandValue() > dealer.getHandValue()) {
                resultMessage.append(winner.getName()).append(" ha vinto contro il banco!");
            } else if (winner != null && winner.getHandValue() == dealer.getHandValue()) {
                resultMessage.append("Pareggio con il banco!");
            } else {
                resultMessage.append("Il Banco ha vinto la partita!");
            }
        }

        return resultMessage.toString();
    }
    }
    	     	
    
    	