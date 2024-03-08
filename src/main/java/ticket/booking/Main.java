package ticket.booking;

import ticket.booking.entities.Train;
import ticket.booking.entities.User;
import ticket.booking.services.UserBookingService;
import ticket.booking.util.UserServiceUtil;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Train Booking System");
        Scanner scanner = new Scanner(System.in);

        int option = 0;
        UserBookingService userBookingService;

        try{
            userBookingService = new UserBookingService();
        }catch (IOException ex){
            System.out.println("There is something wrong: " + ex);
            return;
        }
        while(option!=7){
            System.out.println("1. Login");
            System.out.println("2. Sign Up");
            System.out.println("3. Book Ticket");
            System.out.println("4. Cancel Ticket");
            System.out.println("5. Print Tickets");
            System.out.println("6. Print Train Info");
            System.out.println("7. Exit");
            System.out.println("Enter your choice");
            Train trainSelectedForBooking = new Train();
            option = scanner.nextInt();

            switch (option){
                case 1:
                    System.out.println("Enter name to signup");
                    String nameToSignUp = scanner.next();
                    System.out.println("Enter password to signup");
                    String passwordToSignUp = scanner.next();
                    User userToSignUp = new User(nameToSignUp , passwordToSignUp,
                            UserServiceUtil.hashPassword(passwordToSignUp),
                            new ArrayList<>() , UUID.randomUUID().toString());
                    userBookingService.signUp(userToSignUp);
                    break;

                case 2:
                    System.out.println("Enter name to login");
                    String nameToLogin = scanner.next();
                    System.out.println("Enter password to login");
                    String passwordToLogin = scanner.next();
                    User userToLogin = new User(nameToLogin , passwordToLogin,
                            UserServiceUtil.hashPassword(passwordToLogin),
                            new ArrayList<>() , UUID.randomUUID().toString());
                    try{
                        userBookingService = new UserBookingService(userToLogin);
                    }catch(IOException ex){
                        System.out.println("There is something wrong");
                        return;
                    }
                    break;

                case 3:
                    System.out.println("Fetching Booking");
                    userBookingService.fetchBookings();
                    break;
                case 4:
                    System.out.println("Type source station");
                    String source = scanner.next();
                    System.out.println("Type destination station");
                    String dest = scanner.next();
                    List<Train> trains = userBookingService.getTrains(source , dest);
                    int index = 1;
                    for(Train t:trains){
                        System.out.println(index+"Train id: "+t.getTrainId());
                        for (Map.Entry<String , String> entry: t.getStationTimes().entrySet()){
                            System.out.println("station" + entry.getKey()+" time: "+entry.getValue());
                        }
                    }
                    System.out.println("Select a train by typing 1,2,3....");
                    trainSelectedForBooking = trains.get(scanner.nextInt());
                    break;
                case 5:
                    System.out.println("Select a seat out of these seats");
                    List<List<Integer>> seats = userBookingService.fetchSeats(trainSelectedForBooking);
                    for (List<Integer> row: seats){
                        for (Integer val: row){
                            System.out.print(val+" ");
                        }
                        System.out.println();
                    }
                    System.out.println("Select the seat by typing the row and column");
                    System.out.println("Enter the row");
                    int row = scanner.nextInt();
                    System.out.println("Enter the column");
                    int col = scanner.nextInt();
                    System.out.println("Booking your seat....");
                    Boolean booked = userBookingService.bookTrainSeat(trainSelectedForBooking, row, col);
                    if(booked.equals(Boolean.TRUE)){
                        System.out.println("Booked! Enjoy your journey");
                    }else{
                        System.out.println("Can't book this seat");
                    }
                    break;
                default:
                    break;
            }

            }
        }

    }
