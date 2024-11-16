import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInterface extends JFrame {
    private BusManagementSystem busManagementSystem;

    // Panels for different sections
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JLabel headingLabel;
    private JButton viewBusesButton, bookTicketButton, searchBusButton, viewReservationsButton, authoritiesButton;
    private JButton addBusButton, removeBusButton;

    public UserInterface(BusManagementSystem busManagementSystem) {
        this.busManagementSystem = busManagementSystem;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Bus Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700); // Increased frame size
        setLocationRelativeTo(null);

        // Main panel with GridBagLayout for centering
        mainPanel = new JPanel(new GridBagLayout());

        // Create and set heading label
        headingLabel = new JLabel("Bus Management System");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 36));
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Button panel with vertical BoxLayout
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        // Create buttons
        viewBusesButton = new JButton("View Buses");
        bookTicketButton = new JButton("Book Ticket");
        searchBusButton = new JButton("Search Bus");
        viewReservationsButton = new JButton("View Reservations");
        authoritiesButton = new JButton("Authorities");
        addBusButton = new JButton("Add Bus");
        removeBusButton = new JButton("Remove Bus");

        // Set all buttons to the same size
        Dimension buttonSize = new Dimension(300, 80); // Consistent button size
        viewBusesButton.setPreferredSize(buttonSize);
        bookTicketButton.setPreferredSize(buttonSize);
        searchBusButton.setPreferredSize(buttonSize);
        viewReservationsButton.setPreferredSize(buttonSize);
        authoritiesButton.setPreferredSize(buttonSize);
        addBusButton.setPreferredSize(buttonSize);
        removeBusButton.setPreferredSize(buttonSize);

        // Center all buttons
        viewBusesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        bookTicketButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchBusButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewReservationsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        authoritiesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addBusButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        removeBusButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add buttons to the button panel with spacing
        buttonPanel.add(viewBusesButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        buttonPanel.add(bookTicketButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        buttonPanel.add(searchBusButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        buttonPanel.add(viewReservationsButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        buttonPanel.add(authoritiesButton);

        // Add admin buttons below the Authorities button
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        buttonPanel.add(addBusButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        buttonPanel.add(removeBusButton);

        // Initially hide the admin buttons
        addBusButton.setVisible(false);
        removeBusButton.setVisible(false);

        // Main panel layout setup
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(headingLabel, gbc); // Add heading to the main panel

        // Add a spacer to create space between the title and the button panel
        gbc.gridy = 1;
        mainPanel.add(Box.createRigidArea(new Dimension(0, 50)), gbc); // 50px spacer

        gbc.gridy = 2;
        mainPanel.add(buttonPanel, gbc); // Add button panel below the heading

        // Add action listeners
        viewBusesButton.addActionListener(e -> showAvailableBuses());
        bookTicketButton.addActionListener(e -> showBookingForm());
        searchBusButton.addActionListener(e -> showSearchBusForm());
        viewReservationsButton.addActionListener(e -> showAdminReservations());
        authoritiesButton.addActionListener(e -> toggleAdminButtons());
        addBusButton.addActionListener(e -> showAddBusForm());
        removeBusButton.addActionListener(e -> showRemoveBusForm());

        // Set the main panel to the frame
        setContentPane(mainPanel);
    }

    private void toggleAdminButtons() {
        // Toggle visibility: show admin buttons and hide other buttons
        boolean showAdminButtons = !addBusButton.isVisible();
        addBusButton.setVisible(showAdminButtons);
        removeBusButton.setVisible(showAdminButtons);

        viewBusesButton.setVisible(!showAdminButtons);
        bookTicketButton.setVisible(!showAdminButtons);
        searchBusButton.setVisible(!showAdminButtons);
        viewReservationsButton.setVisible(!showAdminButtons);
    }

    private void showAvailableBuses() {
        StringBuilder availableBuses = new StringBuilder("Available Buses:\n");
        for (BusRecord bus : busManagementSystem.getBuses()) {
            availableBuses.append(bus.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(this, availableBuses.toString(), "Available Buses", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showBookingForm() {
        String[] busOptions = busManagementSystem.getBuses().stream()
                .map(BusRecord::toString)
                .toArray(String[]::new);
    
        if (busOptions.length == 0) {
            JOptionPane.showMessageDialog(this, "No buses available for booking.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        String selectedBus = (String) JOptionPane.showInputDialog(this, "Select a bus to book a ticket:", "Book Ticket",
                JOptionPane.QUESTION_MESSAGE, null, busOptions, busOptions[0]);
    
        if (selectedBus != null) {
            // Add reservation logic here
            busManagementSystem.addReservation(selectedBus);
            JOptionPane.showMessageDialog(this, "You have booked a ticket on: " + selectedBus);
        }
    }
    

    private void showSearchBusForm() {
        String busNumber = JOptionPane.showInputDialog(this, "Enter Bus Number to Search:");
        if (busNumber != null) {
            for (BusRecord bus : busManagementSystem.getBuses()) {
                if (bus.getBusNumber().equals(busNumber)) {
                    JOptionPane.showMessageDialog(this, "Bus Found: " + bus.toString());
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Bus not found.");
        }
    }

    private void showAdminReservations() {
        StringBuilder reservationsList = new StringBuilder("Current Reservations:\n");
        for (String reservation : busManagementSystem.getReservations()) {
            reservationsList.append(reservation).append("\n");
        }
        if (reservationsList.toString().trim().isEmpty()) {
            reservationsList.append("No current reservations.");
        }
        JOptionPane.showMessageDialog(this, reservationsList.toString(), "Reservations", JOptionPane.INFORMATION_MESSAGE);
    }
    

    private void showAddBusForm() {
        String busNumber = JOptionPane.showInputDialog(this, "Enter Bus Number:");
        String busName = JOptionPane.showInputDialog(this, "Enter Bus Name:");
        String source = JOptionPane.showInputDialog(this, "Enter Source:");
        String destination = JOptionPane.showInputDialog(this, "Enter Destination:");
    
        if (busNumber != null && busName != null && source != null && destination != null) {
            BusRecord newBus = new BusRecord(busNumber, busName, source, destination);
            busManagementSystem.addBus(newBus);
            JOptionPane.showMessageDialog(this, "Bus added successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Bus Number, Name, Source, and Destination cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    

    private void showRemoveBusForm() {
        String busNumber = JOptionPane.showInputDialog(this, "Enter Bus Number to Remove:");
        if (busNumber != null) {
            busManagementSystem.removeBus(busNumber);
            JOptionPane.showMessageDialog(this, "Bus removed successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Bus Number cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
