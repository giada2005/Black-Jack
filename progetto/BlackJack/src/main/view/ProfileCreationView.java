package main.view;

import java.net.URL;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


import main.model.UserProfile;

public class ProfileCreationView extends JFrame {
	    private JTextField nicknameField;
	    private JComboBox<ImageIcon> avatarSelection;
	    private UserProfile userProfile;


		public ProfileCreationView(UserProfile userProfile) {
			this.userProfile = userProfile;
			setupPanels();
			initializeImageFrame();
			setVisible(true);
    }
		

		
	
		/**
		 * Metodo che mostra prima una schermata con l'immagine di benvenuto.
		 */
		private void initializeImageFrame() {
			setTitle("BLACKJACK");
			setSize(800, 600);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLocationRelativeTo(null);
			setLayout(new BorderLayout());
			getContentPane().setBackground(new java.awt.Color(0, 0, 0));
			
			JLabel blackjackLabel = createBlackjackLabel();
        	add(blackjackLabel, BorderLayout.NORTH);

        	JLabel welcomeImageLabel = createWelcomeImageLabel();
        	add(welcomeImageLabel, BorderLayout.CENTER);

        	JButton proceedButton = createProceedButton();
        	add(proceedButton, BorderLayout.SOUTH);

        	setVisible(true);
    }
		private JLabel createBlackjackLabel() {
			JLabel blackjackLabel = new JLabel("BLACKJACK");
			blackjackLabel.setFont(new Font("Arial", Font.BOLD, 50));
			blackjackLabel.setForeground(java.awt.Color.WHITE);
			blackjackLabel.setHorizontalAlignment(JLabel.CENTER);
			return blackjackLabel;
		}
		

			
		private JLabel createWelcomeImageLabel() {
			JLabel welcomeImageLabel = new JLabel();
       		 URL imageUrl = getClass().getResource("/cards/Back.ground.png");
        
        ImageIcon welcomeImage = new ImageIcon(imageUrl);
        BufferedImage bufferedImage = addTextToImage(welcomeImage.getImage(), "BLACKJACK");
        welcomeImage = new ImageIcon(bufferedImage);
        welcomeImageLabel.setIcon(welcomeImage);

        welcomeImageLabel.setHorizontalAlignment(JLabel.CENTER);
        return welcomeImageLabel;
    }
		
		private JButton createProceedButton() {
			JButton proceedButton = new JButton("Inizia");
			proceedButton.setFont(new Font("Arial", Font.BOLD, 20));
			proceedButton.addActionListener(e -> {
				getContentPane().removeAll();
								getContentPane().revalidate(); 
								getContentPane().repaint(); 
								setupPanels();
								
							});
							return proceedButton;
						
					}
					
				
						
				
					private BufferedImage addTextToImage(Image image, String text) {
					if (!(image instanceof BufferedImage)) {
          	  		BufferedImage bufferedImage = new BufferedImage(
                    image.getWidth(null),
                    image.getHeight(null),
                    BufferedImage.TYPE_INT_ARGB);
					Graphics2D g = bufferedImage.createGraphics();
					g.drawImage(image, 0, 0, null);
					g.dispose();
					image = bufferedImage;
				}
		
				BufferedImage bufferedImage = (BufferedImage) image;
				Graphics2D g = bufferedImage.createGraphics();
				g.drawImage(image, 0, 0, null);
				g.setFont(new Font("Arial", Font.BOLD, 50));
				g.setColor(Color.WHITE);
				g.drawString(text, 100, 100);
				g.dispose();
		
				return bufferedImage;
			}
					
			
			
    
	 /**
     * Metodo che inizializza le proprietà base della finestra per la selezione del profilo.
     */
   
	@SuppressWarnings("unused")
	private void initializeFrame() {
        setTitle("Creazione Profilo");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new java.awt.Color(139, 0, 0));
    }



	
	    /**
	     * Metodo che configura i pannelli principali della finestra utilizzando
	     * GridBagLayout.
	     */
	    
		private void setupPanels() {
			getContentPane().setBackground(new java.awt.Color(139, 0, 0));
	        setLayout(new GridBagLayout());
	        GridBagConstraints gbc = new GridBagConstraints();
	        gbc.insets = new Insets(10, 10, 10, 10);
	        gbc.fill = GridBagConstraints.HORIZONTAL;

	        // Pannello Nickname
	        gbc.gridx = 0;
	        gbc.gridy = 0;
	        gbc.gridwidth = 2;
	        gbc.anchor = GridBagConstraints.NORTH;
	        add(createNicknamePanel(), gbc);
			

	        // Pannello Avatar
	        gbc.gridx = 0;
	        gbc.gridy = 1;
	        gbc.gridwidth = 2;
	        gbc.anchor = GridBagConstraints.CENTER;
	        add(createAvatarPanel(), gbc);

	        // Bottone di conferma
	        gbc.gridx = 0;
	        gbc.gridy = 2;
	        gbc.gridwidth = 2;
	        gbc.anchor = GridBagConstraints.SOUTH;
	        add(createConfirmButton(), gbc);
	    }

	    /**
	     * Metodo che crea il JPanel per l'inserimento del nickname.
	     * 
	     * @return JPanel contenente il campo di input per il nickname.
	     */
	    private JPanel createNicknamePanel() {
	        JPanel nicknamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			nicknamePanel.setOpaque(true);
			nicknamePanel.setBackground(new java.awt.Color(139, 0, 0));
	        JLabel nicknameLabel = new JLabel("Nickname: ");
	        nicknameLabel.setFont(new Font("Arial", Font.BOLD, 20));
			nicknameLabel.setForeground(java.awt.Color.BLACK);
	        nicknameField = new JTextField(20);
			nicknameField.setBackground(java.awt.Color.WHITE);
	        nicknameField.setFont(new Font("Arial", Font.PLAIN, 18));
	        nicknamePanel.add(nicknameLabel);
	        nicknamePanel.add(nicknameField);
	        return nicknamePanel;
	    }

	    /**
	     * Metodo che crea il pannello per la selezione dell'avatar.
	     * 
	     * @return JPanel contenente la JComboBox per la selezione dell'avatar.
	     */
	    private JPanel createAvatarPanel() {
	        JPanel avatarPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			avatarPanel.setOpaque(true);
			avatarPanel.setBackground(new java.awt.Color(139, 0, 0));
	        JLabel avatarLabel = new JLabel("Seleziona Avatar:");
	        avatarLabel.setFont(new Font("Arial", Font.BOLD, 20));
			avatarLabel.setForeground(java.awt.Color.BLACK);


	        avatarSelection = new JComboBox<>();
		
			 String[] avatarFiles = {  "","mario.png", "sonic.png",  };
	        for (String fileName : avatarFiles) {
				ImageIcon temp = new ImageIcon("src/cards/"+fileName);
				ImageIcon resizedIcon = resizeIcon(temp, 150, 150);
	            avatarSelection.addItem(resizedIcon);
	        }
			// avatarSelection.setBackground(java.awt.Color.WHITE);
	        avatarSelection.setPreferredSize(new Dimension(150, 150));

	        avatarPanel.add(avatarLabel);
	        avatarPanel.add(avatarSelection);
			
	        return avatarPanel;
	    }

	    /**
	     * Metodo che aggiunge un avatar alla JComboBox di selezione.
	     * 
	     * @param fileName Il nome del file dell'avatar da aggiungere.
	     */
	    private void addAvatar(String fileName) {
			
	    	URL imageUr1 =getClass().getResource("/cards/"+fileName);
			System.out.println(imageUr1);

	    	try {
				ImageIcon  icon = new ImageIcon(imageUr1); 
				
				        avatarSelection.addItem(icon);

			} catch (Exception e) {
				
			}
	       
			
	    }

	    /**
	     * Metodo che crea il bottone di conferma per la creazione del profilo.
	     * 
	     * @return JButton configurato per la conferma.
	     */
	    private JButton createConfirmButton() {
	        JButton confirmButton = new JButton("Accedi");
	        confirmButton.setFont(new Font("Arial", Font.BOLD, 20));
	        confirmButton.addActionListener(e -> createProfile());
	        return confirmButton;
	    }

	    /**
	     * Metodo che ridimensiona un'icona alle dimensioni specificate.
	     * 
	     * @param icon   L'icona da ridimensionare.
	     * @param width  La larghezza desiderata.
	     * @param height L'altezza desiderata.
	     * @return ImageIcon ridimensionata.
	     */
	    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
	        Image img = icon.getImage();
	        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	        return new ImageIcon(resizedImg);
	    }

	    /**
	     * Metodo che crea o aggiorna il profilo utente con i dati inseriti.
	     */
	    private void createProfile() {
	        String nickname = nicknameField.getText();
			
			if (nickname == null || nickname.trim().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Inserisci un nickname valido.", "Errore", JOptionPane.ERROR_MESSAGE);
				return;
			}
	        ImageIcon selectedAvatar = (ImageIcon) avatarSelection.getSelectedItem();
	        String avatarPath = selectedAvatar.getDescription();

	        userProfile = new UserProfile(nickname, avatarPath);
	        userProfile.loadProfile();

	        dispose();
	        new MainMenuView(userProfile).setVisible(true);
	    }

	    /**
	     * Metodo che carica gli avatar presenti nella JComboBox di selezione.
	     */
	    
		@SuppressWarnings("unused")
		private void loadAvatars() {
	        String[] avatarFiles = {  "","mario.png", "sonic.png",  };
	        for (String fileName : avatarFiles) {
				
	            addAvatar(fileName);
	        }

			for (int index = 0; index < 3; index++) {
				
			}
			
	    }

	    /**
	     * Getter che restituisce il profilo utente creato o modificato.
	     * 
	     * @return UserProfile che rappresenta il profilo utente.
	     */
	    public UserProfile getUserProfile() {
	        return userProfile;
	    }
	}

