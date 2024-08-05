import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        //establish connection
        String url = "jdbc:mysql://localhost:3306/railwaybookingdb";
        String username = "root";
        String password = "";
        try {
            Connection connection = DriverManager.getConnection(url,username,password);
            System.out.println("DB connected");

            JFrame frame = new JFrame();

            JLabel personalDetailLabel = new JLabel("Personal Details");
            personalDetailLabel.setForeground(Color.green);
            personalDetailLabel.setBounds(30, 10, 120, 50);
            frame.add(personalDetailLabel);

            JLabel ticketDetailsLabel = new JLabel("Ticket Details");
            ticketDetailsLabel.setForeground(Color.green);
            ticketDetailsLabel.setBounds(300, 10, 120, 50);
            frame.add(ticketDetailsLabel);

            JLabel usernameLabel = new JLabel("Username");
            usernameLabel.setBounds(10, 50, 120, 40);
            frame.add(usernameLabel);

            JLabel dateOfJourneyLabel = new JLabel("DOJ");
            dateOfJourneyLabel.setBounds(260, 50, 120, 40);
            frame.add(dateOfJourneyLabel);

            JTextField usernameTextField = new JTextField();
            usernameTextField.setBounds(100, 60, 120, 30);
            frame.add(usernameTextField);

            JTextField dateOfJourneyTextField = new JTextField();
            dateOfJourneyTextField.setBounds(360, 60, 120, 30);
            frame.add(dateOfJourneyTextField);

            JLabel ageLabel = new JLabel("Age");
            ageLabel.setBounds(10, 90, 120, 40);
            frame.add(ageLabel);

            JLabel sourceLabel = new JLabel("Source");
            sourceLabel.setBounds(260, 90, 120, 40);
            frame.add(sourceLabel);

            JTextField ageTextField = new JTextField();
            ageTextField.setBounds(100, 100, 120, 30);
            frame.add(ageTextField);

            JTextField sourceTextField = new JTextField();
            sourceTextField.setBounds(360, 100, 120, 30);
            frame.add(sourceTextField);

            JLabel genderLabel = new JLabel("Gender");
            genderLabel.setBounds(10, 130, 120, 40);
            frame.add(genderLabel);

            JLabel destinationLabel = new JLabel("Destination");
            destinationLabel.setBounds(260, 130, 120, 40);
            frame.add(destinationLabel);

            JTextField genderTextField = new JTextField();
            genderTextField.setBounds(100, 140, 120, 30);
            frame.add(genderTextField);

            JTextField destinationTextField = new JTextField();
            destinationTextField.setBounds(360, 140, 120, 30);
            frame.add(destinationTextField);

            JLabel mobileNumberLabel = new JLabel("Mobile No.");
            mobileNumberLabel.setBounds(10, 170, 120, 40);
            frame.add(mobileNumberLabel);

            JLabel ticketPriceLabel = new JLabel("Ticket Price");
            ticketPriceLabel.setBounds(260, 170, 120, 40);
            frame.add(ticketPriceLabel);

            JTextField mobileNumberTextField = new JTextField();
            mobileNumberTextField.setBounds(100, 180, 120, 30);
            frame.add(mobileNumberTextField);

            JTextField ticketPriceTextField = new JTextField();
            ticketPriceTextField.setBounds(360, 180, 120, 30);
            frame.add(ticketPriceTextField);

            JLabel emailLabel = new JLabel("Email");
            emailLabel.setBounds(10, 210, 120, 40);
            frame.add(emailLabel);

            JLabel seatPreferenceLabel = new JLabel("Seat Preference");
            seatPreferenceLabel.setBounds(260, 210, 120, 40);
            frame.add(seatPreferenceLabel);

            JTextField emailTextField = new JTextField();
            emailTextField.setBounds(100, 220, 120, 30);
            frame.add(emailTextField);

            JTextField seatPreferenceTextField = new JTextField();
            seatPreferenceTextField.setBounds(360, 220, 120, 30);
            frame.add(seatPreferenceTextField);

            JLabel messageLabel = new JLabel("Booking ID");
            messageLabel.setBounds(10, 360, 120, 30);
            frame.add(messageLabel);

            JTextField bookingIdTextField = new JTextField();
            bookingIdTextField.setBounds(80, 360, 50, 30);
            frame.add(bookingIdTextField);

            JButton bookTicketButton = new JButton("Book Ticket");
            bookTicketButton.setBounds(120, 280, 120, 30);
            bookTicketButton.setBackground(Color.pink);
            frame.add(bookTicketButton);

            bookTicketButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String insertDetails = "INSERT INTO ticketbookingtb (username,age,gender,MobileNumber,Email,DOJ,Source,Destination,TicketPrice,SeatPreference) VALUES (?,?,?,?,?,?,?,?,?,?)";
                        PreparedStatement statement = connection.prepareStatement(insertDetails);
                        statement.setString(1, usernameTextField.getText());
                        statement.setInt(2, Integer.parseInt(ageTextField.getText()));
                        statement.setString(3, genderTextField.getText());
                        statement.setString(4, mobileNumberTextField.getText());
                        statement.setString(5, emailLabel.getText());
                        statement.setString(6, dateOfJourneyTextField.getText());
                        statement.setString(7, sourceTextField.getText());
                        statement.setString(8, destinationTextField.getText());
                        statement.setString(9, ticketPriceTextField.getText());
                        statement.setString(10, seatPreferenceTextField.getText());
                        statement.execute();
                        JOptionPane.showMessageDialog(null,"Ticket booked");
                        usernameTextField.setText(" ");
                        ageTextField.setText(" ");
                        genderTextField.setText(" ");
                        mobileNumberTextField.setText(" ");
                        emailTextField.setText(" ");
                        dateOfJourneyTextField.setText(" ");
                        sourceTextField.setText(" ");
                        destinationTextField.setText(" ");
                        ticketPriceTextField.setText(" ");
                        seatPreferenceTextField.setText(" ");
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });

            JButton deleteTicketButton = new JButton("Delete Ticket");
            deleteTicketButton.setBounds(250, 280, 120, 30);
            deleteTicketButton.setBackground(Color.GRAY);
            frame.add(deleteTicketButton);

            deleteTicketButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int bookingid =Integer.parseInt(bookingIdTextField.getText());
                    String deleteQuery = "DELETE FROM ticketbookingtb WHERE bookingid =?";
                    if(bookingid!=0){
                        try {
                            PreparedStatement statement = connection.prepareStatement(deleteQuery);
                            statement.setInt(1,bookingid);
                            statement.execute();
                            JOptionPane.showMessageDialog(null,"Ticket deleted" );
                            bookingIdTextField.setText(" ");
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }else JOptionPane.showMessageDialog(null,"the value can't be emply");
                }
            });
            JButton updateTicketButton = new JButton("Update Ticket");
            updateTicketButton.setBounds(120, 320, 120, 30);
            updateTicketButton.setBackground(Color.lightGray);
            frame.add(updateTicketButton);

            updateTicketButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        int bookingid =Integer.parseInt(bookingIdTextField.getText());
                        String updateQuery = "UPDATE ticketbookingtb SET username=?,age=?,gender=?,MobileNumber=?,Email=?,DOJ=?,Source=?,Destination=?,TicketPrice=?,SeatPreference=? WHERE bookingid =?";
                        PreparedStatement statement =connection.prepareStatement(updateQuery);
                        statement.setString(1, usernameTextField.getText());
                        statement.setInt(2, Integer.parseInt(ageTextField.getText()));
                        statement.setString(3, genderTextField.getText());
                        statement.setString(4, mobileNumberTextField.getText());
                        statement.setString(5, emailLabel.getText());
                        statement.setString(6, dateOfJourneyTextField.getText());
                        statement.setString(7, sourceTextField.getText());
                        statement.setString(8, destinationTextField.getText());
                        statement.setString(9, ticketPriceTextField.getText());
                        statement.setString(10, seatPreferenceTextField.getText());
                        statement.setInt(11,bookingid);
                        int rowAffected = statement.executeUpdate();
                        if (rowAffected>0){
                            JOptionPane.showMessageDialog(null,"Ticket is updated");
                        }
                        else {
                            JOptionPane.showMessageDialog(null,"Ticket not found");
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });


            JLabel blankLabel = new JLabel(" ");
            blankLabel.setBounds(280, 10, 120, 40);
            frame.add(blankLabel);

            frame.setVisible(true);
            frame.setLayout(null);
            frame.setSize(550, 500);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}