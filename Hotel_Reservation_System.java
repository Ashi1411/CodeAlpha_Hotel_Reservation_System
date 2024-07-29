package CodeAlpha_Hotel_Reservation_System;

import java.util.Date;
import java.util.ArrayList;
public class Hotel_Reservation_System {

    static double hotelAccount = 0;//this is hotel account in which customers will transfer room booking price

    //info of each room
    static class RoomInfo{
        int RoomNo;
        String type;
        double price;
        boolean isAvailable;
        public RoomInfo(int RoomNo, String type, double price){
            this.RoomNo = RoomNo;
            this.type = type;
            this.price = price;
            this.isAvailable = true;
        }
    }

    //info of each user
    static class UserInfo{
        String name;
        RoomInfo room;
        Date checkInDate;
        Date checkOutDate;
        String paymentMode;
        boolean paymentDone;
        public UserInfo(String name, Date checkin, Date checkout, String paymentMode){
            this.name = name;
            this.room = null;
            this.checkInDate = checkin;
            this.checkOutDate = checkout;
            this.paymentMode = paymentMode;
            this.paymentDone = false;
        }
    }

    //user interface
    static class UserInterface{
        static ArrayList<RoomInfo> rooms;
        static ArrayList<UserInfo> users;
        public UserInterface(){
            rooms = new ArrayList<>();
            users = new ArrayList<>();
        }

        //user window **********

        //add user
        static void addUser(UserInfo user){
            users.add(user);
            System.out.println("User added");
        }

        //get available rooms
        static void getAvailableRooms(){
            System.out.println("******Rooms Available******");
            for (RoomInfo room : rooms){
                if (room.isAvailable){
                    System.out.println("Room No : " + room.RoomNo + "   Type : " + room.type +"   Price : " + room.price);
                }
            }
        }

        //get rooms of a perticular type
        static void getRoomsOfType(String type){
            System.out.println("******Rooms Available******");
            for (RoomInfo room : rooms){
                if (room.isAvailable && room.type.equals(type)){
                    System.out.println("Room No : " + room.RoomNo + " Type : " + room.type );
                }
            }
        }

        //get rooms of a perticular price range
        static void getRoomsOfRange(double maxPrice){
            System.out.println("******Rooms Available******");
            for (RoomInfo room : rooms){
                if (room.isAvailable && room.price <= maxPrice){
                    System.out.println("Room No : " + room.RoomNo + " Type : " + room.type +" Price : " + room.price);
                }
            }
        }

        //get room type
        static String getType(int RoomNo){
            for (RoomInfo room: rooms){
                if (room.RoomNo == RoomNo){
                    return room.type;
                }
            }
            return "this Room No. is invalid";
        }

        //get price of room for 24 hrs
        static double getPrice(int RoomNo){
            for (RoomInfo room: rooms){
                if (room.RoomNo == RoomNo){
                    return room.price;
                }
            }
            return 0.0;
        }

        //get availability of a room
        static boolean getIsAvailable(int RoomNo){
            for (RoomInfo room: rooms){
                if (room.RoomNo == RoomNo){
                    return room.isAvailable;
                }
            }
            System.out.println("This Room No. is invalid");
            return false;
        }

        // get room booked by user
        static RoomInfo getRoom(String name){
            for (UserInfo user: users){
                if (user.name == name){
                    return user.room;
                }
            }
            return null;
        }

        //get check in date
        static Date getCheckInDate(String name){
            for (UserInfo user: users){
                if (user.name == name){
                    return user.checkInDate;
                }
            }
            return null;
        }
        
        //update check in date
        static void updateCheckInDate(String name, Date newCheckInDate){
            for (UserInfo user: users){
                if (user.name == name){
                    user.checkInDate = newCheckInDate;
                }
            }
            System.out.println("Check in date is updated");
        }

        //get check out date
        static Date getCheckOutDate(String name){
            for (UserInfo user: users){
                if (user.name == name){
                    return user.checkOutDate;
                }
            }
            return null;
        }

        //update check out date
        static void updateCheckOutDate(String name, Date newCheckOutDate){
            for (UserInfo user: users){
                if (user.name == name){
                    user.checkOutDate = newCheckOutDate;
                }
            }
            System.out.println("Check out date is updated");
        }

        //get payment mode 
        static String getPaymentMode(String name){
            for (UserInfo user: users){
                if (user.name == name){
                    return user.paymentMode;
                }
            }
            return null;
        }

        //get payment status
        static void getPaymentStatus(String name){
            for (UserInfo user: users){
                if (user.name == name){
                    if (user.paymentDone){
                        System.out.println("Payment is done");
                    }
                    else{
                        System.out.println("Payment is not done");
                    }
                }
            }
        }

        //book a room
        static void bookRoom(int RoomNo, String username){
            RoomInfo room = null;
            UserInfo user = null;
            for (UserInfo u: users){
                if (u.name == username){
                    user = u;
                }
            }
            for (RoomInfo r : rooms){
                if (r.RoomNo == RoomNo){
                    room = r;
                }
            }
            double totalPrice = (Math.abs(user.checkOutDate.getTime() - user.checkInDate.getTime()) / (1000 * 60 * 60 * 24)) * room.price;
            System.out.println("Please make payment of Rs " + totalPrice + " to confirm your reservation");
            paynow(user, totalPrice , room);
            System.out.println("Congratulations!! Your reservation is done");
        }

        //pay for reservation confirmation
        static void paynow(UserInfo user, double price , RoomInfo room){
            hotelAccount += price;
            System.out.println("Payment is done successfully");
            System.out.println(price + " Rs is transferred to Hotel by " + user.name);
            room.isAvailable = false;
            user.room = room;
            user.paymentDone = true;
            displayDetails(user.name);
        }

        // display user details
        static void displayDetails(String name){
            System.out.println();
            System.out.println("******User Details******");
            for (UserInfo user : users){
                if (user.name.equals(name) && user.room!=null){
                    System.out.println("Name : " + name);
                    System.out.println("Room No : " + user.room.RoomNo);
                    System.out.println("Room Type : " + user.room.type);
                    System.out.println("Check In Date : " + user.checkInDate);
                    System.out.println("Check Out Date : " + user.checkOutDate);
                    System.out.println("Payment Mode : " + user.paymentMode);
                    System.out.println("Payment Done : "+ user.paymentDone);
                    System.out.println("Room Type : " + user.room.type);
                    System.out.println("Room Price per day : " + user.room.price);
                }
            }
        }

        //cancel reservation
        static void cancelReservation(String name){
            for (UserInfo user : users){
                if (user.name.equals(name)){
                    if (user.paymentDone){
                        double returnAmount = ((Math.abs(user.checkOutDate.getTime() - user.checkInDate.getTime()) / (1000 * 60 * 60 * 24)) * user.room.price) * 0.8;
                        hotelAccount -= returnAmount;
                    }
                    user.room.isAvailable = true;
                    user.room = null;
                    user.paymentDone = false;
                    user.paymentMode = "";
                }
            }
            System.out.println("Your reservation is cancelled");
        }


        //hotel admin window*********
        //add more rooms
        static void addRoom(String name, int RoomNo, String type, double price){
            if (name.equals("Admin")){
                rooms.add(new RoomInfo(RoomNo, type, price));
                System.out.println("Room added with room no : " + RoomNo);
            }
            else{
                System.out.println("Only admin can add rooms");
            }
        }

        //delete room
        static void deleteRoom(String username, int RoomNo){
            if (username.equals("Admin")){
                for (RoomInfo room : rooms){
                    if (room.RoomNo == RoomNo){
                        rooms.remove(room);
                        System.out.println("Room : " + RoomNo + " is deleted");
                        return;
                    }
                }
            }
        }
        //update price
        static void updatePrice(int RoomNo , String username, double newPrice){
            if (username.equals("Admin")){
                for (RoomInfo room : rooms){
                    if (room.RoomNo == RoomNo){
                        room.price = newPrice;
                    }
                }
                System.out.println("Per day Price updated");
            }
        }

        //update availability
        static void  updateAvailability(int RoomNo, String username, boolean newAvailability){
            if (username.equals("Admin")){
                for (RoomInfo room : rooms){
                    if (room.RoomNo == RoomNo){
                        room.isAvailable = newAvailability;
                    }
                }
                System.out.println("Availability of room is updated");
            }
        }

        //update type of room
        static void updateType(int RoomNo, String username, String newType){
            if (username.equals("Admin")){
                for (RoomInfo room : rooms){
                    if (room.RoomNo == RoomNo){
                        room.type = newType;
                    }
                }
                System.out.println("Room type is updated");
            }
        }
    }
    @SuppressWarnings({ "deprecation", "static-access" })
    public static void main(String[] args) {
        UserInterface user = new UserInterface();
        user.addRoom("Admin", 101, "Deluxe", 500);
        user.addRoom("Admin", 102, "Deluxe", 500);
        user.addRoom("Admin", 103, "Deluxe", 500);
        user.addRoom("Admin", 104, "Deluxe", 500);
        user.addRoom("Abc", 105, "Single", 200);
        user.addRoom("Admin", 105, "Single", 200);
        user.addRoom("Admin", 106, "Single", 200);
        user.addRoom("Admin", 107, "Single", 200);
        user.addRoom("Admin", 108, "Single", 200);
        user.addRoom("Admin", 109, "Queen", 1000);
        user.addRoom("Admin", 110, "Queen", 1000);
        user.updatePrice(108, "Admin", 1000);
        user.updateType(108, "Admin", "Queen");
        user.updateAvailability(110, "Admin", false);
        user.getAvailableRooms();
        user.getRoomsOfType("Queen");
        user.getRoomsOfRange(700);
        System.out.println();
        user.addUser(new UserInfo("Abc", new Date(2024, 0, 15), new Date(2024, 0, 17), "UPI"));
        user.bookRoom(108, "Abc");
        System.out.println();
        user.addUser(new UserInfo("Xyz", new Date(2024, 1, 20, 7, 00), new Date(2024, 1, 23 , 7 , 00), "UPI"));
        user.bookRoom(105, "Xyz");
        System.out.println();
        System.out.println("Type : " + user.getType(109));
        System.out.println("Price : " + user.getPrice(109));
        System.out.println("Availability of Room : " + user.getIsAvailable(109));
        System.out.println("Check-In Date : " + user.getCheckInDate("Abc"));
        System.out.println("Check-Out Date : " + user.getCheckOutDate("Abc"));
        user.getPaymentStatus("Abc");
        System.out.println("Payment Mode : " + user.getPaymentMode("Abc"));
        user.cancelReservation("Xyz");
        System.out.println("Room No. : " + user.getRoom("Xyz"));
        user.deleteRoom("Admin", 110);
        user.displayDetails("Abc");
    }
}
