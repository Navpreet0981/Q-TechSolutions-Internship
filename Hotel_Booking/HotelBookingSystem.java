package Hotel_Booking;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class HotelBookingSystem extends JFrame implements ActionListener {
    JComboBox<String> roomType;
    JSpinner dateSpinner;
    JTextField guestNameField, guestContactField;
    JButton bookButton;
    JLabel confirmationLabel;
    JPanel mainPanel;

    public HotelBookingSystem() {
        setTitle("Hotel Booking System");
        setSize(450, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Use BorderLayout for a cleaner appearance
        setLayout(new BorderLayout());

        // Sidebar or header (branding)
        JLabel header = new JLabel("Welcome to Hotel Booking", JLabel.CENTER);
        header.setFont(new Font("SansSerif", Font.BOLD, 22));
        header.setForeground(new Color(48,87,181));
        add(header, BorderLayout.NORTH);

        // Main content panel
        mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Room type
        gbc.gridx = 0; gbc.gridy = 0;
        mainPanel.add(new JLabel("Room Type:"), gbc);
        gbc.gridx = 1;
        roomType = new JComboBox<>(new String[]{"Single", "Double", "Suite"});
        roomType.setFont(new Font("SansSerif", Font.PLAIN, 15));
        mainPanel.add(roomType, gbc);

        // Date picker
        gbc.gridx = 0; gbc.gridy = 1;
        mainPanel.add(new JLabel("Date:"), gbc);
        gbc.gridx = 1;
        SpinnerDateModel dateModel = new SpinnerDateModel();
        dateSpinner = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd");
        dateSpinner.setEditor(dateEditor);
        mainPanel.add(dateSpinner, gbc);

        // Guest name
        gbc.gridx = 0; gbc.gridy = 2;
        mainPanel.add(new JLabel("Guest Name:"), gbc);
        gbc.gridx = 1;
        guestNameField = new JTextField();
        mainPanel.add(guestNameField, gbc);

        // Guest contact
        gbc.gridx = 0; gbc.gridy = 3;
        mainPanel.add(new JLabel("Guest Contact:"), gbc);
        gbc.gridx = 1;
        guestContactField = new JTextField();
        mainPanel.add(guestContactField, gbc);

        // Book Button
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        bookButton = new JButton("Book Room");
        bookButton.setBackground(new Color(48,87,181));
        bookButton.setForeground(Color.WHITE);
        bookButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        bookButton.setFocusPainted(false);
        mainPanel.add(bookButton, gbc);

        // Confirmation Label
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        confirmationLabel = new JLabel("", JLabel.CENTER);
        confirmationLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        mainPanel.add(confirmationLabel, gbc);

        add(mainPanel, BorderLayout.CENTER);
        bookButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        String room = roomType.getSelectedItem().toString();
        String date = new java.text.SimpleDateFormat("yyyy-MM-dd")
                .format((java.util.Date) dateSpinner.getValue());
        String name = guestNameField.getText().trim();
        String contact = guestContactField.getText().trim();
        if(room.isEmpty() || date.isEmpty() || name.isEmpty() || contact.isEmpty()) {
            confirmationLabel.setText("Please fill out all fields.");
            confirmationLabel.setForeground(Color.RED);
            return;
        }
        String bookingInfo = room + "," + date + "," + name + "," + contact;
        try (FileWriter fw = new FileWriter("bookings.txt", true)) {
            fw.write(bookingInfo + "\n");
            confirmationLabel.setText("Booking Confirmed!");
            confirmationLabel.setForeground(new Color(0, 128, 0));
        } catch(IOException ex) {
            confirmationLabel.setText("Error saving booking.");
            confirmationLabel.setForeground(Color.RED);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HotelBookingSystem().setVisible(true));
    }
}
