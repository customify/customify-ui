package com.customify.desktop.components;

import com.customify.desktop.business.Business;
import com.customify.desktop.business.ReadBusiness;
import com.customify.desktop.enums.UserRoles;
import com.customify.desktop.layout.Layout;
import com.customify.desktop.sales.Sales;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

public class Sidebar extends JPanel {
    public Overview overview = new Overview();
    public Layout layout;
    public FeatureRegister featureRegister;
    public Socket socket;
    public Sidebar(Socket socket){
        this.socket = socket;
    }
    public Sidebar(UserRoles role, JFrame closableFrame) throws IOException {
        setBackground(new Color(53, 32, 88));
        setBounds(0, 0, 300, 1080);
        setLayout(null);

        JPanel navBarItems = new JPanel();
        navBarItems.setLayout(new BoxLayout(navBarItems, BoxLayout.Y_AXIS));
        navBarItems.setBounds(0, 180, 250, 500);
        navBarItems.setBackground(new Color(53, 32, 88));

        JButton overView = new SideBarListItem("Overview.png", "Overview");
        JButton sales = new SideBarListItem("4. contacts.png", "Sales");
        JButton employees = new SideBarListItem("4. contacts.png", "Employees");
        JButton customers = new SideBarListItem("4. contacts.png", "Customers");
        JButton report = new SideBarListItem("4. contacts.png", "Report");
        JButton feedback = new SideBarListItem("4. contacts.png", "Feedback");
        JButton settings = new SideBarListItem("4. contacts.png", "Settings");
        JButton subscription = new SideBarListItem("4. contacts.png", "Subscription");
        JButton features = new SideBarListItem("4. contacts.png", "Features");
        JButton plans = new SideBarListItem("4. contacts.png", "Plans");
        JButton billing = new SideBarListItem("4. contacts.png", "Billing");
        JButton business = new SideBarListItem("4. contacts.png", "Businesses");

        switch (role){
            case ADMIN:
                navBarItems.add(overView);
                navBarItems.add(business);
                navBarItems.add(features);
                navBarItems.add(plans);
                navBarItems.add(billing);
                navBarItems.add(report);
                navBarItems.add(subscription);
                navBarItems.add(settings);
                break;
            case BUSINESS_OWNER:
                navBarItems.add(overView);
                navBarItems.add(customers);
                navBarItems.add(employees);
                navBarItems.add(sales);
                navBarItems.add(report);
                navBarItems.add(subscription);
                navBarItems.add(feedback);
                navBarItems.add(settings);




                break;
            case EMPLOYEE:
                navBarItems.add(overView);
                navBarItems.add(sales);
                navBarItems.add(settings);
                break;
        }



        JPanel line = new JPanel();
        line.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(164, 166, 179)));
        line.setSize(100, 10);
        line.setBackground(new Color(53, 32, 88));


        ActionListener salesAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                new Sales();
            }
        };

        sales.addActionListener(salesAction);

        //open features event
        ActionListener triggerFeatures = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closableFrame.dispose();
                try {
                    featureRegister = new FeatureRegister();
                    featureRegister.init();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        };
        features.addActionListener(triggerFeatures);


        //open business event
        ActionListener triggerBusiness = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closableFrame.dispose();
                try {
                    ReadBusiness readBusiness = new ReadBusiness(socket,closableFrame);

                } catch (IOException | ClassNotFoundException ioException) {
                    ioException.printStackTrace();
                }
            }
        };
        business.addActionListener(triggerBusiness);

        JPanel logo = logo();

        add(logo);
        add(navBarItems);
    }

    public JPanel logo(){
        JPanel myLogo = new JPanel();
        myLogo.setBackground(new Color(53,32,88));
        myLogo.setBounds(53, 81, 200, 50);

        JLabel logoName = new JLabel("Customize");
        logoName.setFont(new Font("Montserrat", Font.BOLD, 29));
        logoName.setForeground(new Color(164, 166, 179));

        myLogo.add(logoName);
        return myLogo;
    }
}
