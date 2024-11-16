import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Run the UI in the Event Dispatch Thread for thread safety
        SwingUtilities.invokeLater(() -> {
            BusManagementSystem busManagementSystem = new BusManagementSystem();
            UserInterface ui = new UserInterface(busManagementSystem);
            ui.setVisible(true); // Make the UI visible
        });
    }
}
