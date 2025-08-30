import java.util.*;

class Room {
    String num,type; double price; boolean available=true; int max;
    Room(String n,String t,double p,int m){num=n;type=t;price=p;max=m;}
}

class Guest {
    String id,name,phone,email; List<String> history=new ArrayList<>();
    Guest(String i,String n,String ph,String e){id=i;name=n;phone=ph;email=e;}
    void addBooking(String b){history.add(b);}
}

class Booking {
    String id,in,out; Guest g; Room r; double amount;
    static int total=0; static double revenue=0; static String hotel="GenZ Hotels";
    Booking(String i,Guest g,Room r,String in,String out,int nights){
        id=i;this.g=g;this.r=r;this.in=in;this.out=out;
        amount=nights*r.price; total++; revenue+=amount; r.available=false; g.addBooking(i);
    }
    void cancel(){r.available=true;revenue-=amount;amount=0;}
}

public class HotelReservationSystem {
    public static void main(String[] args){
        List<Room> rooms=Arrays.asList(
            new Room("101","Deluxe",3000,2), new Room("102","Deluxe",3000,2),
            new Room("201","Suite",5000,4), new Room("301","Standard",2000,2)
        );
        Guest g1=new Guest("G1","Alice","123","a@mail"), g2=new Guest("G2","Bob","456","b@mail");
        List<Booking> bookings=new ArrayList<>();

        // Make bookings
        for(Room r:rooms) if(r.available&&r.type.equals("Deluxe")){bookings.add(new Booking("B1",g1,r,"9/1","9/3",2));break;}
        for(Room r:rooms) if(r.available&&r.type.equals("Suite")){bookings.add(new Booking("B2",g2,r,"9/5","9/7",2));break;}

        // Reports
        long occ=rooms.stream().filter(r->!r.available).count();
        System.out.println("Occupancy: "+(100.0*occ/rooms.size())+"%");
        System.out.println("Revenue: ₹"+Booking.revenue);

        // Cancel one booking
        bookings.get(0).cancel();
        occ=rooms.stream().filter(r->!r.available).count();
        System.out.println("After cancel -> Occupancy: "+(100.0*occ/rooms.size())+"%, Revenue: ₹"+Booking.revenue);
    }
}
